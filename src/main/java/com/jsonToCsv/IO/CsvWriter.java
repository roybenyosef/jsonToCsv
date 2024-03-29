package com.jsonToCsv.IO;

import com.jsonToCsv.JsonToCsvConsts;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvWriter {

    private Appendable writer;
    private Results results;
    private Config config;

    public CsvWriter(Appendable writer, Config config, Results results) {
        this.writer = writer;
        this.results = results;
        this.config = config;
    }

    public void write() throws IOException {

        CSVFormat csvFormat = getCsvFormat();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for(Datum datum : results.getData()) {
                prepareDatum(datum);

                String fullUrl = getFullUrl(datum.getExtra().getUrl());

                csvPrinter.printRecord(
                        datum.getObjType(),
                        datum.getData().getId(),
                        sanitizeString(datum.getData().getQ()),
                        sanitizeString(datum.getData().getTextContent()),
                        sanitizeString(datum.getData().getName()),
                        sanitizeString(datum.getData().getTagslist().get(0)),
                        sanitizeString(datum.getData().getTagslist().get(1)),
                        sanitizeString(datum.getData().getTagslist().get(2)),
                        sanitizeString(datum.getData().getTagslist().get(3)),
                        sanitizeString(datum.getData().getTagslist().get(4)),
                        extractDate(datum.getData().getTime()),
                        extractTime(datum.getData().getTime()),
                        datum.getData().getArchive(),
                        datum.getData().getAnonflg(),
                        datum.getData().getSuperAnonflg(),
                        datum.getData().getUserid(),
                        sanitizeString(datum.getData().getPhoto()),
                        datum.getData().getAskType(),
                        datum.getData().getPhotoType(),
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
                        datum.getExtra().getContentImage().getUrlMedium(),
                        sanitizeString(datum.getExtra().getContentImage().getCreatorName()),
                        sanitizeString(datum.getExtra().getContentImage().getCreatorUsername()),
                        datum.getExtra().getHebrewTime(),
                        datum.getExtra().getUrl(),
                        fullUrl,
                        sanitizeString(datum.getExtra().getUrlTitle()),
                        sanitizeString(datum.getExtra().getItemProfile().getNickname()),
                        datum.getExtra().getItemProfile().getAnonflg(),
                        datum.getExtra().getItemProfile().getActive(),
                        datum.getMeta().getActive(),
                        datum.getMeta().getPermissions().getEdit(),
                        datum.getMeta().getPermissions().getDelete(),
                        datum.getMeta().getPermissions().getDeleteWithoutMsg(),
                        datum.getMeta().getPermissions().getBan(),
                        datum.getMeta().getPermissions().getReport(),
                        datum.getMeta().getPermissions().getShowAdminMsgs(),
                        datum.getMeta().getPermissions().getItemOwner(),
                        datum.getMeta().getPermissions().getRestore(),
                        datum.getMeta().getPermissions().getRemoveFromChannel(),
                        datum.getMeta().getMoreUserDetails().getUserid(),
                        datum.getMeta().getMoreUserDetails().getIp());
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

        return CSVFormat.EXCEL
                .withNullString("null")
                .withHeader(
                        fileBytesHeader + "objType",
                        "data_id",
                        "data_q",
                        "data_text_content",
                        "data_name",
                        "data_tagslist_item1",
                        "data_tagslist_item2",
                        "data_tagslist_item3",
                        "data_tagslist_item4",
                        "data_tagslist_item5",
                        "time",
                        "date",
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
                        "extra_hebrew_time",
                        "extra_url",
                        "extra_full_url",
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
                        "meta_permissions_itemOwner",
                        "meta_permissions_restore",
                        "meta_permissions_removeFromChannel",
                        "meta_moreUserDetails_userid",
                        "meta_moreUserDetails_ip");
    }

    private String extractTime(String dateTime) {
        return dateTime.substring(0, dateTime.indexOf(' '));
    }

    private String extractDate(String dateTime) {
        return dateTime.substring(dateTime.indexOf(' ') + 1);
    }

    private void prepareDatum(Datum datum) {
        fillNulls(datum);
        padTags(datum);
    }

    private void padTags(Datum datum) {
        List<String> tagsList = datum.getData().getTagslist();
        for (int i = tagsList.size(); i < config.MAX_TAGS; ++i) {
            tagsList.add("");
        }
    }

    private String prepareTagList(List<String> tagslist) {
        List<String> paddedTagsList = new ArrayList<>(Collections.nCopies(Math.max(tagslist.size(), config.MAX_TAGS), "-"));
        return String.join("|", tagslist);
    }

    private void fillNulls(Datum datum) {
        if (datum.getData() == null) {
            datum.setData(new Data());
        }

        if (datum.getExtra() == null) {
            datum.setExtra(new Extra());
        }

        if (datum.getExtra().getContentImage() == null) {
            datum.getExtra().setContentImage(new ContentImage());
        }

        if (datum.getExtra().getItemProfile() == null) {
            datum.getExtra().setItemProfile(new ItemProfile());
        }

        if (datum.getMeta() == null) {
            datum.setMeta(new Meta());
        }

        if (datum.getMeta().getMoreUserDetails() == null) {
            datum.getMeta().setMoreUserDetails(new MoreUserDetails());
        }

        if (datum.getMeta().getPermissions() == null) {
            datum.getMeta().setPermissions(new Permissions());
        }
    }

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
