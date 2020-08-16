package glove;

import filemodel.Log;
import filemodel.Model;

public class Worker implements Runnable {
    private final int threadNum;
    private final int fromRow;
    private final int toRow;
    private final Log rawLog;
    private final Log processedLog;
    private final Model gloveModel;


    public Worker(int thread, int from, int to, Log log, Log newLog, Model model) {
        this.threadNum = thread;
        fromRow = from;
        toRow = to;
        rawLog = log;
        processedLog = newLog;
        gloveModel = model;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadNum + " starting...");

        for (int i = fromRow; i < toRow; i++) {
            System.out.println("Thread " + threadNum + " processing row " + i);

            processedLog.putSingleValue(rawLog.getValue("Start time", i), "Start time", i);
            processedLog.putSingleValue(rawLog.getValue("End time", i), "End time", i);
            processedLog.putSingleValue("testActivity123", "Activity", i);
        }
    }
}
