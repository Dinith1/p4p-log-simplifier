import exceptions.InvalidLogException;
import exceptions.ModelReadingException;
import filehandler.CLIParser;
import filehandler.FileChecker;
import filehandler.LogReader;
import filehandler.ModelReader;
import filemodel.Log;
import filemodel.Model;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;

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

        Log log;
        try {
            LogReader lr = new LogReader();
            log = lr.readLog(logFile);
        } catch (InvalidLogException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        Model model;
        try {
            ModelReader mr = new ModelReader();
            model = mr.readModel(modelFile);
        } catch (ModelReadingException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // TODO: Produce simplified log from the log, using the model

        // TODO: Save the simplified log to a CSV file
    }
}
