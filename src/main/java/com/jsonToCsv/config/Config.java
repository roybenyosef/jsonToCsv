package com.jsonToCsv.config;

import com.jsonToCsv.JsonToCsvConsts;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public final int MAX_TAGS = 5;
    public boolean writeBomToCsv = true;
    public String baseUrl = JsonToCsvConsts.DEFAULT_BASE_URL;
    public String rootElement;
    public Map<String, Character> columnToSplit = new HashMap<>();
}
