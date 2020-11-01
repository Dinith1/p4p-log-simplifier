# P4P - Project 81

- Easiest way to use:
  - Install Java 11
  - Open the project in IntelliJ
  - Make sure raw logs are stored in the `src/main/resources/logs/raw/` folder
    - Processed logs will be automatically stored in the `src/main/resources/logs/processed/` folder
  - Make sure the GloVe model is stored in the `src/main/resources/trained_vectors/embeddings/` folder
    - If desired, the model can be serilized and stored as an object in a binary file by uncommenting lines 71-73 of the `ModelReader` class
  - If necessary - `> cd p4p-log-simplifier`
  - Set up the run configuration to run the `LogSimplifier` class with the following arguments:
    - `-l <raw log> -m <model> -t 10`
      - `-l` is the log file to process
      - `-m` is the GloVe model to use
      - `-t` is the number of threads to use
    - E.g. `-l OrdonezB_Sensors_new.txt -m glove.6B.200d.txt -t 10`
  - Run the file
  
  - The output will be saved to `src/main/java/resources/logs/processed/<output_log>`
