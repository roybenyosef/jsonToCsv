package com.jsonToCsv;

import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.Results;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class JsonToCsv {

    static private String jsonFile;
    static private String outputFile;
    static private Config config = new Config();

    public static void main(String[] args) {
        try {
            System.out.println("Implementation version: 0.3-Snapshot");
            System.out.println("System encoding: " + java.nio.charset.Charset.defaultCharset());
            readConfig(args);
            printWelcomeMessage();
            var jsonReader = new JsonReader();
            System.out.println("Reading json file...");
            Results results = jsonReader.read(jsonFile);

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile)))
            {
                CsvWriter csvWriter = new CsvWriter(writer, config, results);
                System.out.println("Writing csv output file...");
                csvWriter.write();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("All done.");
    }

    private static void readConfig(String[] args) {
        readCommandLineArgs(args);
        readApplicationConfig();
    }

    private static void readApplicationConfig() {
        System.out.println("Reading app configuration");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
            config.writeBomToCsv = Boolean.parseBoolean(properties.getProperty("csv.writebom"));
        }
        catch (Exception e) {
            System.out.println("Error reading configuration: " + e.getMessage());
            System.out.println("Using default values");
        }
        System.out.println("Configuration: write bom: " + config.writeBomToCsv);
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
