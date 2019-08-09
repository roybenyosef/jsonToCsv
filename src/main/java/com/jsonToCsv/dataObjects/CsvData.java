package com.jsonToCsv.dataObjects;

import java.util.ArrayList;
import java.util.List;

public class CsvData {

    public List<String> getCsvHeaders() {
        return csvHeaders;
    }

    public List<List<String>> getCsvRows() {
        return csvRows;
    }

    private List<String> csvHeaders = new ArrayList<>();
    private List<List<String>> csvRows = new ArrayList<>();

}
