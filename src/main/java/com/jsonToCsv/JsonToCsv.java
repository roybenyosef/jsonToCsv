package com.jsonToCsv;

import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToCsv {

    static private String jsonFile = "";
    static private String outputFile;

    public static void main(String[] args) {
        try {
            System.out.println("Implementation version: 0.4-Snapshot");
            System.out.println("System encoding: " + java.nio.charset.Charset.defaultCharset());
            readCommandLineArgs(args);
            Config config = new Config();
            printWelcomeMessage();

            var jsonReader = new JsonReader(config);

            System.out.println("Reading json file...");
            jsonReader.readFromFile(jsonFile);

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile)))
            {
                CsvWriter csvWriter = new CsvWriter(writer, config, jsonReader.getCsvData());
                System.out.println("Writing csv output file...");
                csvWriter.write();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("All done.");
    }

    private static void readCommandLineArgs(String[] args) {
        System.out.println("Reading command line arguments");
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: JsonToCsv <jsonfile> <output file name>");
        }

        jsonFile = args[0];
        outputFile = args[1];
    }

    private static void printWelcomeMessage() {

    }
}
