import org.apache.commons.cli.ParseException;

public class LogProcessor {

    public static void main(String[] args) {
        CLIParser parser = new CLIParser();

        try {
            parser.parseArgs(args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(parser.getLogFilePath());
        System.out.println(parser.getModelFilePath());
        System.out.println(parser.getNumThreads());
    }
}
