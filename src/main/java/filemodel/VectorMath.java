package filemodel;

import java.util.List;

public class VectorMath {

    public static double[] add(double[] v1, double[] v2, int dimension) {
        double[] out = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            out[i] = v1[i] + v2[i];
        }

        return out;
    }

    public static double[] addAll(int dimension, double[]... vectors) {
        double[] out = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            for (double[] vec : vectors) {
                out[i] += vec[i];
            }
        }

        return out;
    }

    public static double[] addAll(int dimension, List<double[]> vectors) {
        double[] out = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            for (double[] vec : vectors) {
                out[i] += vec[i];
            }
        }

        return out;
    }

    public static double[] subtract(double[] v1, double[] v2, int dimension) {
        double[] out = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            out[i] = v1[i] - v2[i];
        }

        return out;
    }

    public static double euclideanDistance(double[] v1, double[] v2, int dimension) {
        double sum = 0;

        for (int i = 0; i < dimension; i++) {
            sum += Math.pow(v1[i] - v2[i], 2);
        }

        return Math.sqrt(sum);
    }
}
