package com.jsonToCsv;

import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.Results;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JsonToCsv {

    static private String jsonFile;
    static private String outputFile;
    static private Config config = new Config();

    public static void main(String[] args) {
        try {
            System.out.println("System encoding: " + java.nio.charset.Charset.defaultCharset());
            readConfig(args);
            printWelcomeMessage();
            var jsonReader = new JsonReader();
            Results results = jsonReader.read(jsonFile);
            CsvWriter csvWriter = new CsvWriter(config, results, outputFile);
            csvWriter.write();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void readConfig(String[] args) {
        readCommandLineArgs(args);
        readApplicationConfig();
    }

    private static void readApplicationConfig() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";
            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
            config.maxTags = Integer.parseInt(appProps.getProperty("csv.maxtags"));
            config.writeBomToCsv = Boolean.parseBoolean(appProps.getProperty("csv.writebom"));
        }
        catch (IOException | NullPointerException ex) {
            System.out.println("Error reading configuration: " + ex.getMessage());
            System.out.println("Using default values");
            System.out.println("maxtags = " + config.maxTags + ", writebom: " + config.writeBomToCsv);
        }
    }

    private static void readCommandLineArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: JsonToCsv <jsonfile> <output file name>");
        }

        jsonFile = args[0];
        outputFile = args[1];
    }

    private static void printWelcomeMessage() {

    }
}
