package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonToCsv.dataObjects.Results;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public Results read(String jsonFile)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonFile), Results.class);
    }
}
