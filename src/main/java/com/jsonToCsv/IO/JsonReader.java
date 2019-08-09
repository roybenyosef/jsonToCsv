package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.config.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private List<List<String>> jsonRows = new ArrayList<>();
    Config config;

    public JsonReader(Config config) {
        this.config = config;
    }

    public void read(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonPath);
        JsonNode jsonDoc = objectMapper.readTree(jsonFile);

        System.out.println("Traversing json element: " + config.rootElement);
        JsonNode rootNode = jsonDoc.get(config.rootElement);

        jsonRows.add(populateHeaders(rootNode));
        readJsonInternal(rootNode);

        for(var row : jsonRows) {
            for (var cell : row) {
                System.out.print(cell + ",");
            }
            System.out.println();
        }
    }

    private List<String> populateHeaders(JsonNode rootNode) {
        List<String> headers = new ArrayList<>();
        var firstNode = rootNode.elements().next();
        readNode(firstNode, config.rootElement, headers, false);
        return headers;
    }

    private void readJsonInternal(JsonNode rootNode) {
        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            List<String> csvRow = new ArrayList<>();
            var node = nodeIterator.next();
            readNode(node, config.rootElement, csvRow, true);
            jsonRows.add(csvRow);
            //System.out.println("***********************************");
        }
    }

    private void readNode(JsonNode rootNode, String name, List<String> csvList, boolean writeValues) {
        var fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            var field = fieldsIterator.next();
            //System.out.println(name + "_" + field.getKey() + " = " + field.getValue());
            csvList.add(writeValues ? field.getValue().toString() : name + "_" + field.getKey());
        }

        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            var node = nodeIterator.next();
            readNode(node, name, csvList, writeValues);
        }
    }


}
