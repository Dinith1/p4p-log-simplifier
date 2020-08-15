package filehandler;

import exceptions.InvalidLogException;
import filemodel.Log;

public class LogReader {

    public Log readLog(String log) throws InvalidLogException {
        String path = ResourcePath.RAW_LOG_FOLDER_PATH + log;

        throw new InvalidLogException("TODO!!!");
    }
}
