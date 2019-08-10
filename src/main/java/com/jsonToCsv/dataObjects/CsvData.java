package com.jsonToCsv.dataObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvData {

    public List<String> getCsvHeaders() {
        return csvHeaders;
    }

    public List<List<String>> getCsvRows() {
        return csvRows;
    }

    public Map<String, Integer> getArrayNameToSize() {
        return arrayNameToSize;
    }

    private List<String> csvHeaders = new ArrayList<>();
    private List<List<String>> csvRows = new ArrayList<>();
    private Map<String, Integer> arrayNameToSize = new HashMap<>();

}
