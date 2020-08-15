package filehandler;

import exceptions.ModelReadingException;
import filemodel.Model;

import java.io.FileNotFoundException;

public class ModelReader {

    public Model readModel(String model) throws ModelReadingException, FileNotFoundException {
        String path = ResourcePath.MODEL_FOLDER_PATH.path() + model;

        // TODO: Check for serialized model first

        throw new ModelReadingException("TODO!!!");
    }
}
