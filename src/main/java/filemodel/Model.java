package filemodel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final long serialVersionUID = 1177001011983527203L;

    private final String name; // Name of the model file - without an extension
    private final int dimension;
    private final Map<String, double[]> vectors = new HashMap<>();

    public Model(String name, int dimension) {
        this.name = name.substring(0, name.lastIndexOf('.'));
        this.dimension = dimension;
    }

    public void addWordVector(String word, double[] vector) {
        vectors.put(word, vector);
    }

    public double[] getWordVector(String word) {
        double[] vec = vectors.get(word);
        return vec;
    }

    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public int getNumWords() {
        return vectors.size();
    }

    public String[] findClosestWords(double[] vector, int num) {
        // use [num][0] for distance and [num][1] for the word representing that distance
        Object[][] topDistanceAndWord = new Object[num][2];

        for (int i = 0; i < num; i++) {
            topDistanceAndWord[i][0] = Double.MAX_VALUE;
        }

        for (String word : vectors.keySet()) {
            double[] wordVec = vectors.get(word);
            double euclideanDistance = VectorMath.euclideanDistance(vector, wordVec, dimension);

            if (euclideanDistance < (double) topDistanceAndWord[num - 1][0]) {
                topDistanceAndWord[num - 1][0] = euclideanDistance;
                topDistanceAndWord[num - 1][1] = word;

                Arrays.sort(topDistanceAndWord, Comparator.comparingDouble(o -> (double) o[0]));
            }
        }

        String[] topWords = new String[num];

        for (int i = 0; i < num; i++) {
            topWords[i] = (String) topDistanceAndWord[i][1];
        }

        return topWords;
    }
    
}
