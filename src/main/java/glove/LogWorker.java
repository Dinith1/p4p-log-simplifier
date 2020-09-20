package glove;

import exceptions.NoWordFoundException;
import filemodel.Log;
import filemodel.Model;
import filemodel.VectorMath;

import java.util.*;

/**
 * This class carries out the actual processing of the input log to produce the 'simplified' output.
 */
public class LogWorker implements Runnable {
    private final int threadNum;
    private final int fromRow;
    private final int toRow;
    private final Log rawLog;
    private final Log processedLog;
    private final Model gloveModel;
    private Set<String> wordsUsedInAlgorithm = new HashSet<>();


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

            String[] closestWords = gloveModel.findClosestWords(vector, 10);
            System.out.println(Arrays.toString(closestWords));

            try {
                String newWord = getClosestWord(closestWords);
                processedLog.putSingleValue(newWord, "Activity", i);
            } catch (NoWordFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * The algorithm for finding the desired output Activity.
     *
     * @param row The row of the input log to process
     * @return A vector representing what the output word should be for the Activity
     */
    private double[] determineActivityVector(int row) {
        int dimension = gloveModel.getDimension();

        String p = rawLog.getValue("Place", row);
        p += (p.equals("living")) ? "room" : "";

//        List<double[]> vectors = getWordVectors(p, "action");
//        double[] output = VectorMath.addAll(dimension, vectors);

        double[] output = VectorMath.add(wordVector(p), VectorMath.subtract(wordVector("Eating"), wordVector("Kitchen"), dimension), dimension);

        return output;
    }

    private List<double[]> getWordVectors(String... words) {
        List<double[]> vectors = new ArrayList<>();

        for (String w : words) {
            vectors.add(wordVector(w));
        }

        return vectors;
    }

    private double[] wordVector(String word) {
        wordsUsedInAlgorithm.add(word.toLowerCase());

        return gloveModel.getWordVector(word.toLowerCase());
    }

    private String getClosestWord(String[] words) throws NoWordFoundException {
        for (String w : words) {
            if (!wordsUsedInAlgorithm.contains(w)) {
                return w;
            }
        }

        throw new NoWordFoundException("No appropriate word found from list: " + Arrays.toString(words));
    }
}
