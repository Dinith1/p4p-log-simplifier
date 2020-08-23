package filehandler.log;

import filehandler.ResourcePath;
import filemodel.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogWriter {

    public void writeLog(Log log, String logName, String modelName) {
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");
        String dateStr = df.format(date);

        String[] startTimes = log.getColumn("Start time");
        String[] endTimes = log.getColumn("End time");
        String[] activities = log.getColumn("Activity");

        try {
            String path = ResourcePath.PROCESSED_LOG_FOLDER_PATH.path() + logName + "__" + modelName + "__" + dateStr + ".txt";
            File newLog = new File(path);

            System.out.println(path);

            if (newLog.createNewFile()) {
                FileWriter fw = new FileWriter(path);
                String header = getHeaderLine(log);
                fw.write(header);

                for (int i = 0; i < log.getNumRows(); i++) {
                    fw.write("\n");
                    fw.write(startTimes[i] + "\t" + endTimes[i] + "\t" + activities[i]);
                }

                fw.close();
            } else {
                System.err.println("Cannot write, file already exists: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHeaderLine(Log log) {
        int numHeaders = log.getNumHeaders();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numHeaders; i++) {
            sb.append(log.getHeader(i));
            if (i != numHeaders - 1) {
                sb.append("\t");
            }
        }

        return sb.toString();
    }

}
