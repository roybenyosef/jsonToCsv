package com.jsonToCsv.config;

import com.google.common.base.Splitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jsonToCsv.JsonToCsvConsts.CONFIG_FILE_NAME;

public class Config {

    public boolean writeBomToCsv = true;
    public String rootElement;
    public Map<String, String> columnsToSplit = new HashMap<>();
    public Map<String, String> columnsPrefix = new HashMap<>();

    public Config() throws IOException, IllegalArgumentException {
        System.out.println("Reading app configuration");
        Properties properties = new Properties();
        try (InputStream configStream = CreateConfigInputStream()) {
            properties.load(configStream);
            writeBomToCsv = Boolean.parseBoolean(properties.getProperty("csv.writebom"));
            rootElement = properties.getProperty("csv.rootElement");

            readPairsParameter(properties, "csv.splitColumnNamesAndDelimiter", columnsToSplit);
            readPairsParameter(properties, "csv.prefixAdditionForColumns", columnsPrefix);
        }
        catch (Exception e) {
            System.out.println("Error reading configuration: " + e.getMessage());
            throw e;
        }
        System.out.println("Configuration: write bom: " + writeBomToCsv);
    }

    private void readPairsParameter(Properties properties, String paramName, Map<String, String> mapOfPairs) {
        String pairString = properties.getProperty(paramName);
        splitIntoPairs(pairString, "][", ",", true);

        if (!pairString.startsWith("[") || !pairString.endsWith("]") ) {
            throw new IllegalArgumentException(paramName + " must use square brackets for items");
        }

        pairString = pairString.substring(1, pairString.length() - 2);
        mapOfPairs = Splitter.on("\\]\\[").withKeyValueSeparator("\\,").split(pairString);
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
    Map<String, String> splitIntoPairs(String pairsString, String pairDelimiter, String itemsDelimiter, boolean removeEdges) {
        if (removeEdges) {
            pairsString = pairsString.substring(1, pairsString.length() - 2);
        }

        var list =
                Stream.of(pairsString.split(pairDelimiter, 1))
                .collect(Collectors.toList())
                .stream();

        var pairs = new HashMap<String, String>();

        int afterKeyIndex = getNextIndexByDelimiter(pairsString, itemsDelimiter);
        String key = pairsString.substring(0, afterKeyIndex);
        int afterValueIndex = getNextIndexByDelimiter(pairsString, pairDelimiter);
        String value = pairsString.substring(afterKeyIndex, afterValueIndex);
        pairs.put(key, value);
        //pairsString = pairsString.substring(afterValueIndex)

        return pairs;
    }

    int getNextIndexByDelimiter(String input, String delimiter) {
        int nextIndex = input.indexOf(delimiter);
        if (nextIndex == -1) {
            nextIndex = input.length() - 1;
        }

        int updateNextIndex = nextIndex + delimiter.length();
        return updateNextIndex < input.length() ? updateNextIndex : input.length() - 1;
    }

}
