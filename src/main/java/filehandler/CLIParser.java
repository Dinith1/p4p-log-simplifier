package filehandler;

import org.apache.commons.cli.*;


public class CLIParser {
    private final int DEFAULT_THREAD_COUNT = 1;

    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();
    private HelpFormatter formatter = new HelpFormatter();

    private String logFilePath;
    private String modelFilePath;
    private int numThreads;

    public CLIParser() {
        Option log = new Option("l", "log", true, "Filename of the input log");
        log.setRequired(true);
        options.addOption(log);

        Option model = new Option("m", "model", true, "Filename of the pre-trained GloVe model");
        model.setRequired(true);
        options.addOption(model);

        Option threads = new Option("t", "threads", true, "Number of threads to use");
        threads.setRequired(false);
        threads.setType(Integer.class);
        options.addOption(threads);
    }

    public void parseArgs(String[] args) throws ParseException {
        CommandLine cmd = parser.parse(options, args);

        logFilePath = cmd.getOptionValue("log");
        modelFilePath = cmd.getOptionValue("model");

        String threads = cmd.getOptionValue("threads");
        numThreads = threads != null ? Integer.parseInt(threads) : DEFAULT_THREAD_COUNT;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public int getNumThreads() {
        return numThreads;
    }
}
