package filehandler;

import java.io.File;
import java.io.FileNotFoundException;

public class FileChecker {
    private final String RAW_LOG_FOLDER_PATH = "src/main/resources/logs/raw/";
    private final String MODEL_FOLDER_PATH = "src/main/resources/trained_vectors/embeddings/";

    public void checkLogExists(String log) throws FileNotFoundException {
        checkExists(RAW_LOG_FOLDER_PATH + log);
    }

    public void checkModelExists(String model) throws FileNotFoundException {
        checkExists(MODEL_FOLDER_PATH + model);
    }

    private void checkExists(String path) throws FileNotFoundException {
        File f = new File(path);

        if (!f.isFile()) {
            throw new FileNotFoundException("File not found: " + path);
        }
    }
}
