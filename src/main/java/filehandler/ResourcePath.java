package filehandler;

public enum ResourcePath {
    RAW_LOG_FOLDER_PATH("src/main/resources/logs/raw/"),
    PROCESSED_LOG_FOLDER_PATH("src/main/resources/logs/processed/"),
    MODEL_FOLDER_PATH("src/main/resources/trained_vectors/embeddings/"),
    MODEL_OBJ_FOLDER_PATH("src/main/resources/trained_vectors/java_obj");

    private final String folderPath;

    ResourcePath(String path) {
        folderPath = path;
    }

    public String path() {
        return folderPath;
    }
}
