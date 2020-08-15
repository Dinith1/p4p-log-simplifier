package filehandler;

import exceptions.ModelReadingException;
import filemodel.Model;

public class ModelReader {

    public Model readModel(String model) throws ModelReadingException {
        String path = ResourcePath.MODEL_FOLDER_PATH + model;

        throw new ModelReadingException("TODO!!!");
    }
}
