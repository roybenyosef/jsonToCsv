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

        populateHeaders(rootNode);
        //readJsonInternal(rootNode);
    }

    private void populateHeaders(JsonNode rootNode) {
        var firstNode = rootNode.elements().next();
        readNode(firstNode, config.rootElement);
    }

    private void readJsonInternal(JsonNode rootNode) {
        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            var node = nodeIterator.next();
            readNode(node, config.rootElement);
            System.out.println("***********************************");
        }
    }

    private void readNode(JsonNode rootNode, String name) {
        var fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            var field = fieldsIterator.next();
            System.out.println(name + "_" + field.getKey() + " = " + field.getValue());
        }

        var nodeIterator = rootNode.elements();
        while (nodeIterator.hasNext()) {
            var node = nodeIterator.next();
            readNode(node, name);
        }
    }


}
