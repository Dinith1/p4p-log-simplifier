package filehandler;

import exceptions.InvalidLogException;
import filemodel.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogReader {

    public Log readLog(String log) throws InvalidLogException {
        System.out.println("Reading log...");
        String path = ResourcePath.RAW_LOG_FOLDER_PATH.path() + log;

        // TODO: OPTIMIZE THIS LOOP!?!?
        Log logObj;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Do one pass to count the number of lines - assumes there's no blank lines and that the header is the only non-data line
            int row = -1;

            while (br.readLine() != null) {
                row++;
            }

            logObj = new Log(row);
            br.close();

            br = new BufferedReader(new FileReader(path));

            // On the second pass read the log
            String line = br.readLine();

            String[] headers = line.split("\t");
            for (String header : headers) {
                logObj.createHeader(header);
            }

            row = 0;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                logObj.putFullRow(data, row);
                row++;
            }

            br.close();

            return logObj;

        } catch (
                IOException e) {
            // TODO: HANDLE THIS BETTER!?!?
            throw new InvalidLogException("Invalid log file");
        }
    }
}
