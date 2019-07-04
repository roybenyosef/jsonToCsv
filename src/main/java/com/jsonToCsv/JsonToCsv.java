package com.jsonToCsv;
import com.jsonToCsv.dataObjects.Results;

public class JsonToCsv {

    static String jsonFile;
    static String outputFile;

    public static void main(String[] args) {
        try {
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
        System.out.println("|￣￣￣￣￣￣ |");
        System.out.println("|     WHAT       |");
        System.out.println("|    IS SIGN      | ");
        System.out.println("|     BUNNY     |");
        System.out.println("| ＿＿＿＿＿__|");
        System.out.println("                (\\__/) ||");
        System.out.println("        (•ㅅ•) || ");
        //System.out.println("/ 　 づ");
    }
}
