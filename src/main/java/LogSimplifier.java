import exceptions.InvalidLogException;
import exceptions.ModelReadingException;
import filehandler.CLIParser;
import filehandler.FileChecker;
import filehandler.LogReader;
import filehandler.ModelReader;
import filemodel.Log;
import filemodel.Model;
import glove.LogProcessor;
import org.apache.commons.cli.ParseException;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class LogSimplifier {

    // TODO: Refactor and put separate parts into different methods
    public static void main(String[] args) {
        CLIParser parser = new CLIParser();

        try {
            parser.parseArgs(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        String logFile = parser.getLogFilePath();
        String modelFile = parser.getModelFilePath();
        FileChecker checker = new FileChecker();

        try {
            checker.checkLogExists(logFile);
            checker.checkModelExists(modelFile);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.err.println("Make sure logs are placed in src/main/resources/logs/raw/");
            System.err.println("Make sure models are placed in src/main/resources/trained_vectors/embeddings/");
            System.exit(1);
        }

        try {
            LogReader lr = new LogReader();
            Log log = lr.readLog(logFile);

            ModelReader mr = new ModelReader();
            Model model = mr.readModel(modelFile);

            LogProcessor lp = new LogProcessor();
            Log simplifiedLog = lp.process(log, model, parser.getNumThreads());

            System.out.println(Arrays.toString(simplifiedLog.getColumn("Activity")));

            // TODO: Save the simplified log to a CSV file

        } catch (InvalidLogException | ModelReadingException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
