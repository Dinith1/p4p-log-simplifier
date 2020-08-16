package filehandler;

import java.io.File;
import java.io.FileNotFoundException;

public class FileChecker {

    public void checkLogExists(String log) throws FileNotFoundException {
        checkExists(ResourcePath.RAW_LOG_FOLDER_PATH.path() + log);
    }

    public void checkModelExists(String model) throws FileNotFoundException {
        checkExists(ResourcePath.MODEL_FOLDER_PATH.path() + model);
    }

    public boolean checkModelObjExists(String model) {
        String modelObj = model.substring(0, model.lastIndexOf('.')) + ".ser";

        try {
            checkExists(ResourcePath.MODEL_OBJ_FOLDER_PATH.path() + modelObj);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    private void checkExists(String path) throws FileNotFoundException {
        File f = new File(path);

        if (!f.isFile()) {
            throw new FileNotFoundException("File not found: " + path);
        }
    }
}
