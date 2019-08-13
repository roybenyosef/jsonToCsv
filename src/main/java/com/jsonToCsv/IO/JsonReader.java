package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonReader {

    private CsvData csvData = new CsvData();
    private Config config;

    private Map<String, Integer> arrayColumnNameToMaxSize = new HashMap<>();

    public JsonReader(Config config) {
        this.config = config;
    }

    public CsvData getCsvData() {
        return csvData;
    }

    public void readFromFile(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonPath);
        JsonNode jsonDoc = objectMapper.readTree(jsonFile);
        readJsonDoc(jsonDoc);
    }

    public void readFromString(String jsonContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonDoc = objectMapper.readTree(jsonContent);
        readJsonDoc(jsonDoc);
    }

    private void readJsonDoc(JsonNode jsonDoc) {
        System.out.println("Traversing json element: " + config.rootElement);
        JsonNode rootNode = jsonDoc.get(config.rootElement);
        populateHeaders(rootNode, csvData.getCsvHeaders());
        readJsonData(rootNode);
    }

    private void populateHeaders(JsonNode rootNode, List<String> headers) {
        var firstJsonEntry = rootNode.elements().next();
        readNode(firstJsonEntry, "", headers, false);
    }

    private void readJsonData(JsonNode rootNode) {
        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            List<String> csvRow = new ArrayList<>();
            var node = nodeIterator.next();
            readNode(node, "", csvRow, true);
            csvData.getCsvRows().add(csvRow);
        }
    }

    private void readNode(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        if (node.isObject()) {
            readJsonObject(node, name, csvList, writeValues);
        }
        else if (node.isArray()) {
            readJsonArray(node, name, csvList, writeValues);
        }
        else {
            readJsonValue(node, name, csvList, writeValues);
        }
    }

    private void readJsonObject(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        var fields = node.fields();
        while (fields.hasNext()) {
            var field = fields.next();
            String prefixedName = name + (name.isEmpty() ? "" : "_");
            readNode(field.getValue(), prefixedName + field.getKey(), csvList, writeValues);
        }
    }

    private void readJsonArray(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        Integer itemIndex = -1;
        for (var arrayItem : node) {
            itemIndex++;
            readNode(arrayItem, name + itemIndex, csvList, writeValues);
        }

        Integer maxArrayIndex = arrayColumnNameToMaxSize.get(name);

        if (maxArrayIndex == null) {
            arrayColumnNameToMaxSize.put(name, itemIndex);
        }
        else if (itemIndex > maxArrayIndex) {
            maxArrayIndex = itemIndex;
            addEmptyColumnsToAddOtherRows(name, maxArrayIndex);
        }
        else if (itemIndex < maxArrayIndex) {
            String[] arrayToAdd = new String[maxArrayIndex - itemIndex];
            Arrays.fill(arrayToAdd, "");
            csvList.addAll(Arrays.asList(arrayToAdd));
        }
    }

    private void addEmptyColumnsToAddOtherRows(String name, Integer maxArrayIndex) {
        Integer currentArrayIndex = arrayColumnNameToMaxSize.get(name);
        arrayColumnNameToMaxSize.put(name, maxArrayIndex);
        var indexOfCurrentMaxArrayIndex = csvData.getCsvHeaders().indexOf(name + currentArrayIndex);
        for (int i = indexOfCurrentMaxArrayIndex + 1; i <= maxArrayIndex; ++i) {
            csvData.getCsvHeaders().add(indexOfCurrentMaxArrayIndex + 1, name + i);
            for (var row : csvData.getCsvRows()) {
                row.add(indexOfCurrentMaxArrayIndex + 1, "");
            }
        }
    }

    private void readJsonValue(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        String value = node.toString().replaceAll("^\"|\"$", "");
        value = sanitizeString(value);
        value = applyPrefix(name, value);
        csvList.addAll(writeValues ? CreateValueToAdd(value, name) : CreateNameToAdd(value, name));
    }

    private List<String> CreateNameToAdd(String value, String name) {
        var processedData = new ArrayList<String>();
        if (!config.columnsToSplit.containsKey(name)) {
            processedData.add(name);
        }
        else {
            for (int i = 0; i < value.split(config.columnsToSplit.get(name)).length; ++i) {
                processedData.add(name + i);
            }
        }
        return processedData;
    }

    private List<String> CreateValueToAdd(String value, String name) {
        var processedData = new ArrayList<String>();
        if (!config.columnsToSplit.containsKey(name)) {
            processedData.add(value);
        }
        else {
            processedData.addAll(Arrays.asList(value.split(config.columnsToSplit.get(name))));
        }
        return processedData;
    }

    private String applyPrefix(String name, String value) {
        if (config.columnsPrefix.containsKey(name)) {
            value = config.columnsPrefix.get(name) + value;
        }
        return value;
    }

    private String sanitizeString(String value) {
        if (value == null) {
            return "null";
        }
        return value.replace('\r', ' ')
                .replace('\n', ' ')
                .replace('"', '\'');
    }
}

