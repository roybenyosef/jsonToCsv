package com.jsonToCsv.IO;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonDocProvider {
    JsonNode getJavaDoc();
}
