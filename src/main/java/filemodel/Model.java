package filemodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final long serialVersionUID = 1177001011983527203L;

    private final String name; // Name of the model file - without an extension
    private final int dimension;
    private final Map<String, float[]> vectors = new HashMap<>();

    public Model(String name, int dimension) {
        this.name = name.substring(0, name.lastIndexOf('.'));
        this.dimension = dimension;
    }

    public void addWordVector(String word, float[] vector) {
        vectors.put(word, vector);
    }

    public float[] getWordVector(String word) {
        return vectors.get(word);
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

}
