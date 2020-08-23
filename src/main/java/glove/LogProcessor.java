package glove;

import filemodel.Log;
import filemodel.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogProcessor {
    private final int MAX_THREADS = 10;

    public Log process(Log log, Model model, int numThreads) {
        int numRows = log.getNumRows();

        int numThreadsToUse = (numThreads > numRows) ? numRows : numThreads;

        int[][] toFrom = new int[numThreadsToUse][2];

        // Determine which part of the input log each thread will work on
        if (numThreadsToUse == numRows) {
            for (int i = 0; i < numRows; i++) {
                toFrom[i][0] = i;
                toFrom[i][1] = i + 1;
            }

        } else {
            // Each thread should process atLeast many rows
            int atLeast = numRows / numThreadsToUse;

            // Number of threads that will process 1 extra row
            int extra = Math.round(((float) numRows / (float) numThreadsToUse - (float) atLeast) * (float) numThreadsToUse);

            int from = 0;
            int to = atLeast;

            for (int i = 0; i < numThreadsToUse; i++) {
                if (extra > 0) {
                    to++;
                    extra--;
                }

                toFrom[i][0] = from;
                toFrom[i][1] = to;

                from = to;
                to = from + atLeast;
            }
        }

        System.out.println("Starting log processing using " + numThreadsToUse + " thread(s)...");

        Log simplifiedLog = new Log(log.getNumRows());
        simplifiedLog.createHeader(log.getHeader(0));
        simplifiedLog.createHeader(log.getHeader(1));
        simplifiedLog.createHeader("Activity");

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

        for (int i = 0; i < numThreadsToUse; i++) {
            int from = toFrom[i][0];
            int to = toFrom[i][1];
            Runnable worker = new LogWorker(i, from, to, log, simplifiedLog, model);

            executor.execute(worker);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            // TODO: Handle better
            e.printStackTrace();
        }

        System.out.println("Finished processing log!");

        return simplifiedLog;
    }
}
