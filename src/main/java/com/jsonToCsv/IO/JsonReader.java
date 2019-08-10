package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {

    private CsvData csvData = new CsvData();
    private Config config;

    public JsonReader(Config config) {
        this.config = config;
    }

    public CsvData getCsvData() {
        return csvData;
    }

    public void read(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonPath);
        JsonNode jsonDoc = objectMapper.readTree(jsonFile);

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

    private void readJsonArray(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        csvData.getArrayNameToSize().merge(name, node.size(),
                (oldValue, newValue) -> Math.max(newValue, oldValue));

        int itemIndex = 0;
        for (var arrayItem : node) {
            readNode(arrayItem, name + itemIndex, csvList, writeValues);
            itemIndex++;
        }
    }

    private void readJsonValue(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        csvList.add(writeValues ? node.textValue() : name);
    }

    private void readJsonObject(JsonNode node, String name, List<String> csvList, boolean writeValues) {
        var fields = node.fields();
        while (fields.hasNext()) {
            var field = fields.next();
            String prefixedName = name + (name.isEmpty() ? "" : "_");
            readNode(field.getValue(), prefixedName + field.getKey(), csvList, writeValues);
        }
    }


}
