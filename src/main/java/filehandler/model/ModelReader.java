package filehandler.model;

import exceptions.ModelReadingException;
import filehandler.FileChecker;
import filehandler.ResourcePath;
import filemodel.Model;

import java.io.*;
import java.util.Arrays;

public class ModelReader {

    public Model readModel(String model) throws ModelReadingException {
        System.out.println("Reading pre-trained GloVe model...");

        // UN-COMMENT TO USE SERIALIZED MODEL OBJECTS. THIS SEEMS TO BE SLOWER THAN READING THE FILE!
//        FileChecker checker = new FileChecker();
//        if (checker.checkModelObjExists(model)) {
//            // Use the serialized/saved model
//            return readModelObj(model);
//        }

        return readModelFile(model);
    }

    private Model readModelObj(String model) throws ModelReadingException {
        System.out.println("Loading pre-saved serialized model...");
        String path = ResourcePath.MODEL_OBJ_FOLDER_PATH.path() + model.substring(0, model.lastIndexOf('.')) + ".ser";

        try {
            FileInputStream fi = new FileInputStream(new File(path));
            ObjectInputStream oi = new ObjectInputStream(fi);

            Model modelObj = (Model) oi.readObject();

            fi.close();
            oi.close();

            return modelObj;

        } catch (Exception e) {
            throw new ModelReadingException("Could not read serialized model object from file: " + path + "\n" + e.getMessage());
        }
    }

    private Model readModelFile(String model) throws ModelReadingException {
        System.out.println("Reading and serializing new model...");
        String path = ResourcePath.MODEL_FOLDER_PATH.path() + model;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            String[] wordAndVector = line.split(" ");
            int dimension = wordAndVector.length - 1;

            Model modelObj = new Model(model, dimension);
            double[] vector = parseVector(Arrays.copyOfRange(wordAndVector, 1, dimension + 1), dimension);
            modelObj.addWordVector(wordAndVector[0], vector);

            while ((line = br.readLine()) != null) {
                wordAndVector = line.split(" ");
                vector = parseVector(Arrays.copyOfRange(wordAndVector, 1, dimension + 1), dimension);
                modelObj.addWordVector(wordAndVector[0], vector);
            }

            br.close();

            System.out.println("DONE READING MODEL!!! : " + modelObj.getName());

            ModelWriter mr = new ModelWriter();
            mr.write(modelObj);

            return modelObj;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ModelReadingException("Could not read model from file: " + path + "\n" + e.getMessage());
        }
    }

    private double[] parseVector(String[] strVec, int dimension) {
        double[] vector = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            vector[i] = Double.parseDouble(strVec[i]);
        }

        return vector;
    }
}
