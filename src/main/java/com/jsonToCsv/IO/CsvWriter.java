package com.jsonToCsv.IO;

import com.jsonToCsv.dataObjects.Datum;
import com.jsonToCsv.dataObjects.Results;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CsvWriter {

    private Results results;
    private String outputFile;

    public CsvWriter(Results results, String outputFile) {
        this.results = results;
        this.outputFile = outputFile;
    }

    public void write() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CSVFormat csvFormat = CSVFormat.EXCEL
                .withHeader(
                        "objType",
                        "data_id",
                        "data_q",
                        "data_text_content",
                        "data_name",
                        "data_tagslist",
                        "time",
                        "archive",
                        "anonflg",
                        "super_anonflg",
                        "userid",
                        "photo",
                        "ask_type",
                        "photo_type",
                        "bit_settings",
                        "q_link",
                        "revision_flg",
                        "city_id",
                        "answer_count",
                        "pin_count",
                        "user_pinned",
                        "safefilter",
                        "channel_id",
                        "extra_photo_id",
                        "extra_content_image_url_small",
                        "extra_content_image_url_medium",
                        "extra_content_image_creator_name",
                        "extra_content_image_creator_username",
                        "extra_content_image_",
                        "extra_hebrew_time",
                        "extra_url",
                        "extra_url_title",
                        "extra_item_profile_nickname",
                        "extra_item_profile_anonflg",
                        "extra_item_profile_active",
                        "meta_active",
                        "meta_permissions_edit",
                        "meta_permissions_delete",
                        "meta_permissions_deleteWithoutMsg",
                        "meta_permissions_ban",
                        "meta_permissions_report",
                        "meta_permissions_showAdminMsgs",
                        "meta_permissions_",
                        "meta_permissions_itemOwner",
                        "meta_permissions_restore",
                        "meta_permissions_removeFromChannel",
                        "meta_moreUserDetails_userid",
                        "meta_moreUserDetails_ip");

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile));
             CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

            for(Datum datum : results.getData()) {
                csvPrinter.printRecord(
                        datum.getObjType(),
                        datum.getData().getId(),
                        sanitizeString(datum.getData().getQ()),
                        sanitizeString(datum.getData().getTextContent()),
                        sanitizeString(String.join("|", datum.getData().getTagslist())),
                        datum.getData().getTime(),
                        datum.getData().getArchive(),
                        datum.getData().getAnonflg(),
                        datum.getData().getSuperAnonflg(),
                        datum.getData().getUserid(),
                        sanitizeString(datum.getData().getPhoto()),
                        datum.getData().getBitSettings(),
                        sanitizeString(datum.getData().getQLink()),
                        datum.getData().getRevisionFlg(),
                        datum.getData().getCityId(),
                        datum.getData().getAnswerCount(),
                        datum.getData().getPinCount(),
                        datum.getData().getUserPinned(),
                        datum.getData().getSafefilter(),
                        datum.getData().getChannelId(),
                        sanitizeObject(datum.getExtra().getPhotoId()),
                        datum.getExtra().getContentImage().getUrlSmall(),
                        datum.getExtra().getContentImage().getUrlMedium());
                        //sanitizeString(datum.getExtra().getContentImage().getCreatorName()),
                        //sanitizeString(datum.getExtra().getContentImage().getCreatorUsername()));
//                        datum.getExtra().getHebrewTime(),
//                        datum.getExtra().getUrl(),
//                        sanitizeString(datum.getExtra().getUrlTitle()),
//                        sanitizeString(datum.getExtra().getItemProfile().getNickname()),
//                        datum.getExtra().getItemProfile().getAnonflg(),
//                        datum.getExtra().getItemProfile().getActive(),
//                        datum.getMeta().getActive(),
//                        datum.getMeta().getPermissions().getEdit(),
//                        datum.getMeta().getPermissions().getDelete(),
//                        datum.getMeta().getPermissions().getDeleteWithoutMsg(),
//                        datum.getMeta().getPermissions().getBan(),
//                        datum.getMeta().getPermissions().getReport(),
//                        datum.getMeta().getPermissions().getShowAdminMsgs(),
//                        datum.getMeta().getPermissions().getItemOwner(),
//                        datum.getMeta().getPermissions().getRestore(),
//                        datum.getMeta().getPermissions().getRemoveFromChannel(),
//                        datum.getMeta().getMoreUserDetails().getUserid(),
//                        datum.getMeta().getMoreUserDetails().getIp());
            }
        }
    }

    String sanitizeString(String value) {
        if (value == null) {
            return "null";
        }
        return value.replace('\r', ' ')
                .replace('\n', ' ')
                .replace('"', '\'');
    }

    String sanitizeObject(Object obj) {
        return obj == null ? "null" : obj.toString();
    }
}
