package com.jsonToCsv;

import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.Results;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.jsonToCsv.JsonToCsvConsts.CONFIG_FILE_NAME;

public class JsonToCsv {

    static private String jsonFile;
    static private String outputFile;
    static private Config config = new Config();

    public static void main(String[] args) {
        try {
            System.out.println("Implementation version: 0.4-Snapshot");
            System.out.println("System encoding: " + java.nio.charset.Charset.defaultCharset());
            readConfig(args);
            printWelcomeMessage();
            var jsonReader = new JsonReader(config);
            System.out.println("Reading json file...");
            jsonReader.read2(jsonFile);
//            Results results = jsonReader.read(jsonFile);
//
//            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile)))
//            {
//                CsvWriter csvWriter = new CsvWriter(writer, config, results);
//                System.out.println("Writing csv output file...");
//                csvWriter.write();
//            }
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
        Properties properties = new Properties();
        try (InputStream configStream = CreateConfigInputStream()) {
            properties.load(configStream);
            config.writeBomToCsv = Boolean.parseBoolean(properties.getProperty("csv.writebom"));
            config.rootElement = properties.getProperty("csv.rootElement");
        }
        catch (Exception e) {
            System.out.println("Error reading configuration: " + e.getMessage());
            System.out.println("Using default values");
        }
        System.out.println("Configuration: write bom: " + config.writeBomToCsv);
    }

    private static InputStream CreateConfigInputStream() {

        try {
            System.out.println("Trying to read configuration: " + CONFIG_FILE_NAME);
            return new FileInputStream(CONFIG_FILE_NAME);
        }
        catch (FileNotFoundException e) {
            System.out.println("Config file not found (" + CONFIG_FILE_NAME + "), Loading default configuration");
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return loader.getResourceAsStream(CONFIG_FILE_NAME);
        }
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
