package com.jsonToCsv.IO;

import com.jsonToCsv.JsonToCsvConsts;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
                //prepareDatum(datum); //TODO - fix
                //String fullUrl = getFullUrl(datum.getExtra().getUrl()); //TODO - fix
                csvPrinter.printRecord((Object)row.toArray(new String[row.size()]));
            }
        }
    }

    private String getFullUrl(String relativeURl) {
        if (config.baseUrl.endsWith("/") || relativeURl.startsWith("/")) {
            return config.baseUrl + relativeURl;
        }
        else {
            return  config.baseUrl + "/" + relativeURl;
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

    private String extractTime(String dateTime) {
        return dateTime.substring(0, dateTime.indexOf(' '));
    }

    private String extractDate(String dateTime) {
        return dateTime.substring(dateTime.indexOf(' ') + 1);
    }

    //TODO - fix
//    private void prepareDatum(Datum datum) {
//        fillNulls(datum);
//        padTags(datum);
//    }

    //TODO - fix
//    private void padTags(Datum datum) {
//        List<String> tagsList = datum.getData().getTagslist();
//        for (int i = tagsList.size(); i < config.MAX_TAGS; ++i) {
//            tagsList.add("");
//        }
//    }


    //TODO - fix
//    private String prepareTagList(List<String> tagslist) {
//        List<String> paddedTagsList = new ArrayList<>(Collections.nCopies(Math.max(tagslist.size(), config.MAX_TAGS), "-"));
//        return String.join("|", tagslist);
//    }

    //TODO - fix
//    private void fillNulls(Datum datum) {
//        if (datum.getData() == null) {
//            datum.setData(new Data());
//        }
//
//        if (datum.getExtra() == null) {
//            datum.setExtra(new Extra());
//        }
//
//        if (datum.getExtra().getContentImage() == null) {
//            datum.getExtra().setContentImage(new ContentImage());
//        }
//
//        if (datum.getExtra().getItemProfile() == null) {
//            datum.getExtra().setItemProfile(new ItemProfile());
//        }
//
//        if (datum.getMeta() == null) {
//            datum.setMeta(new Meta());
//        }
//
//        if (datum.getMeta().getMoreUserDetails() == null) {
//            datum.getMeta().setMoreUserDetails(new MoreUserDetails());
//        }
//
//        if (datum.getMeta().getPermissions() == null) {
//            datum.getMeta().setPermissions(new Permissions());
//        }
//    }

    private String sanitizeString(String value) {
        if (value == null) {
            return "null";
        }
        return value.replace('\r', ' ')
                .replace('\n', ' ')
                .replace('"', '\'');
    }

    private Object sanitizeObject(Object obj) {
        return obj == null ? "null" : obj;
    }
}
