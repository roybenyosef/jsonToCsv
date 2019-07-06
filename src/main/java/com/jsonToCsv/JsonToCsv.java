package com.jsonToCsv;
import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.dataObjects.Results;

public class JsonToCsv {

    static private String jsonFile;
    static private String outputFile;

    public static void main(String[] args) {
        try {
            System.out.println("System encoding: " + java.nio.charset.Charset.defaultCharset());
            readCommandLineArgs(args);
            printWelcomeMessage();
            var jsonReader = new JsonReader();
            Results results = jsonReader.read(jsonFile);
            CsvWriter csvWriter = new CsvWriter(results, outputFile);
            csvWriter.write();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
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
