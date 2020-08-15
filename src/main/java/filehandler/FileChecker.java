package filehandler;

import java.io.File;
import java.io.FileNotFoundException;

public class FileChecker {

    public void checkLogExists(String log) throws FileNotFoundException {
        checkExists(ResourcePath.RAW_LOG_FOLDER_PATH + log);
    }

    public void checkModelExists(String model) throws FileNotFoundException {
        checkExists(ResourcePath.MODEL_FOLDER_PATH + model);
    }

    public boolean checkModelObjExists(String model) {
        // TODO: IMPLEMENT
        return false;
    }

    private void checkExists(String path) throws FileNotFoundException {
        File f = new File(path);

        if (!f.isFile()) {
            throw new FileNotFoundException("File not found: " + path);
        }
    }
}
