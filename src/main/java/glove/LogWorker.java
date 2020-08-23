package glove;

import filemodel.Log;
import filemodel.Model;
import filemodel.VectorMath;

/**
 *
 */
public class LogWorker implements Runnable {
    private final int threadNum;
    private final int fromRow;
    private final int toRow;
    private final Log rawLog;
    private final Log processedLog;
    private final Model gloveModel;


    public LogWorker(int thread, int from, int to, Log log, Log newLog, Model model) {
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

            double[] vector = determineActivityVector(i);

            String[] closestWords = gloveModel.findClosestWords(vector, 5);
            String newWord = closestWords[0];

            processedLog.putSingleValue(newWord, "Activity", i);
        }
    }

    /**
     * 'Algorithm' for finding the desired output Activity
     *
     * @param row The row of the input log to process
     * @return A vector representing what the output word should be for the Activity
     */
    private double[] determineActivityVector(int row) {
        double[] place = gloveModel.getWordVector(rawLog.getValue("Place", row).toLowerCase());
        double[] action = gloveModel.getWordVector("action");
        double[] vector = VectorMath.add(place, action, gloveModel.getDimension());

        return vector;
    }
}
