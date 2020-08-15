import filehandler.CLIParser;
import filehandler.FileChecker;
import org.apache.commons.cli.ParseException;

import java.io.FileNotFoundException;

public class LogSimplifier {

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

        System.out.println(parser.getLogFilePath());
        System.out.println(parser.getModelFilePath());
        System.out.println(parser.getNumThreads());
    }
}
