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

    private void readJsonDoc(JsonNode jsonDoc) throws IOException {
        System.out.println("Traversing json element: " + config.rootElement);
        JsonNode rootNode = jsonDoc.get(config.rootElement);

        populateHeaders(rootNode, csvData.getCsvHeaders());
        readJsonData(rootNode);

        for (var cell : csvData.getCsvHeaders()) {
            System.out.print(cell + ",");
        }
        System.out.println();
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
            readNode(node, config.rootElement, csvRow, true);
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

        //TODO remove this
        //        csvData.getArrayNameToSize().merge(name, node.size(),
        //        (oldValue, newValue) -> Math.max(newValue, oldValue));

        Integer itemIndex = 0;
        for (var arrayItem : node) {
            readNode(arrayItem, name + itemIndex, csvList, writeValues);
            itemIndex++;
        }

        Integer maxArrayIndex = arrayColumnNameToMaxSize.get(name);

        if (maxArrayIndex == null || itemIndex > maxArrayIndex) {
            maxArrayIndex = itemIndex;
            addEmptyColumnsToAddOtherRows(name, maxArrayIndex);
        }
        else if (itemIndex < maxArrayIndex) {
            String arrayToAdd[] = new String[maxArrayIndex - itemIndex];
            Arrays.fill(arrayToAdd, "");
            csvList.addAll(Arrays.asList(arrayToAdd));
        }
    }

    private void addEmptyColumnsToAddOtherRows(String name, Integer maxArrayIndex) {
        arrayColumnNameToMaxSize.put(name, maxArrayIndex);
        var indexOfCurrentMaxArrayIndex = csvData.getCsvHeaders().indexOf(name + (maxArrayIndex - 1));
        for (int i = indexOfCurrentMaxArrayIndex + 1; i <= maxArrayIndex; ++i) {
            csvData.getCsvHeaders().add(indexOfCurrentMaxArrayIndex, name + i);
            for (var row : csvData.getCsvRows()) {
                row.add(indexOfCurrentMaxArrayIndex, "");
            }
        }
    }

    private void readJsonValue(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        csvList.add(writeValues ? node.textValue() : name);
    }
}

