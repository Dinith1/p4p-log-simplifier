package filehandler;

import exceptions.InvalidLogException;
import filemodel.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogReader {

    public Log readLog(String log) throws InvalidLogException, FileNotFoundException {
        String path = ResourcePath.RAW_LOG_FOLDER_PATH.path() + log;

        // TODO: FIX THIS LOOP!
        Log logObj = new Log(0);

        try {
            for (int i = 0; i < 2; i++) {
                BufferedReader br = new BufferedReader(new FileReader(path));

                if (i == 0) {
                    // Do one pass to count the number of lines - assumes there's no blank lines and that the header is the only non-data line
                    int count = -1;

                    while (br.readLine() != null) {
                        count++;
                    }

                    logObj = new Log(count);

                } else {
                    // On the second pass read the log
                    String line = br.readLine();

                    String[] headers = line.split("\t");
                    for (String header : headers) {
                        logObj.createHeader(header);
                    }

                    int row = 0;

                    while ((line = br.readLine()) != null) {
                        String[] data = line.split("\t");
                        logObj.putFullRow(data, row);
                        row++;
                    }
                }

                br.close();
            }
        } catch (IOException e) {
            // TODO: HANDLE THIS!?!?
            e.printStackTrace();
        }

        throw new InvalidLogException("TODO!!!");
    }
}
