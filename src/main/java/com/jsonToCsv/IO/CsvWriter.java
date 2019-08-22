package com.jsonToCsv.IO;

import com.jsonToCsv.JsonToCsvConsts;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.List;

public class CsvWriter {

    private CsvData csvData;
    private Appendable writer;
    private Config config;

    public CsvWriter(Appendable writer, Config config, CsvData csvData) {
        this.writer = writer;
        this.csvData = csvData;
        this.config = config;
    }

    public void write() throws IOException {

        CSVFormat csvFormat = getCsvFormat();
        var csvRow = csvData.getCsvRows();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for(List<String> row : csvRow) {
                csvPrinter.printRecord(row.toArray(new String[row.size()]));
            }
        }
    }

    public CSVFormat getCsvFormat() {

        String fileBytesHeader = config.writeBomToCsv ? JsonToCsvConsts.UTF8_BOM : "";
        var headers = csvData.getCsvHeaders();
        headers.set(0, fileBytesHeader + headers.get(0));

        return CSVFormat.EXCEL
                .withNullString("null")
                .withHeader(headers.toArray(new String[headers.size()]));
    }
}
