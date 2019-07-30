package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.dataObjects.Results;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    List<List<String>> jsonRows = new ArrayList<>();

    public Results read(String jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonFile), Results.class);
    }

    public void read2(String jsonPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonPath);
        JsonNode jsonDoc = objectMapper.readTree(jsonFile);

        var fieldsIterator = jsonDoc.fields();
        while (fieldsIterator.hasNext()) {
            var field = fieldsIterator.next();
            System.out.println("name: " + field.getKey() + ", value: " + field.getValue());
            jsonRows.add()
        }
    }


}
