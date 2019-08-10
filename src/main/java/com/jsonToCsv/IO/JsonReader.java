package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        readJsonInternal(rootNode);

        for (var cell : csvData.getCsvHeaders()) {
            System.out.print(cell + ",");
        }
        System.out.println();
    }

    private void populateHeaders(JsonNode rootNode, List<String> headers) {
        var firstJsonEmpty = rootNode.fields().next();
        readNode(firstJsonEmpty, "", headers, false);
    }

    private void readJsonInternal(JsonNode rootNode) {
        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            List<String> csvRow = new ArrayList<>();
            var node = nodeIterator.next();
            readNode(node, config.rootElement, csvRow, true);
            csvData.getCsvRows().add(csvRow);
        }
    }

    private void readNode(Map.Entry<String, JsonNode> jsonEmpty, String name, List<String> csvList, boolean writeValues) {
        var fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            var field = fieldsIterator.next();
            csvList.add(writeValues ? field.getValue().toString() : name + "_" + field.getKey());
        }

        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            var node = nodeIterator.next();
            readNode(node, name, csvList, writeValues);
        }
    }


}
