package com.jsonToCsv;

import com.jsonToCsv.dataObjects.Datum;
import com.jsonToCsv.dataObjects.Results;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    private Results results;
    private String outputFile;

    public CsvWriter(Results results, String outputFile) {
        this.results = results;
        this.outputFile = outputFile;
    }

    public void write() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String header = getHeader(stringBuilder);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(header);
            for(Datum datum : results.getData()) {
                stringBuilder.setLength(0);
                stringBuilder
                        .append(datum.getObjType())
                        .append(datum.getData().getId()).append(',')
                        .append(datum.getData().getQ()).append(',')
                        .append(datum.getData().getTextContent()).append(',')
                        .append(String.join(",", datum.getData().getTagslist())).append(',')
                        .append(datum.getData().getTime()).append(',')
                        .append(datum.getData().getArchive()).append(',')
                        .append(datum.getData().getAnonflg()).append(',')
                        .append(datum.getData().getSuperAnonflg()).append(',')
                        .append(datum.getData().getUserid()).append(',')
                        .append(datum.getData().getPhoto()).append(',')
                        .append(datum.getData().getBitSettings()).append(',')
                        .append(datum.getData().getQLink()).append(',')
                        .append(datum.getData().getRevisionFlg()).append(',')
                        .append(datum.getData().getCityId()).append(',')
                        .append(datum.getData().getAnswerCount()).append(',')
                        .append(datum.getData().getPinCount()).append(',')
                        .append(datum.getData().getUserPinned()).append(',')
                        .append(datum.getData().getSafefilter()).append(',')
                        .append(datum.getData().getChannelId()).append(',');

                writer.write(stringBuilder.toString());
            }
        }
    }

    private String getHeader(StringBuilder stringBuilder) {
        return stringBuilder
                .append("objType,")
                .append("data_id,")
                .append("data_q,")
                .append("data_text_content,")
                .append("data_name,")
                .append("data_tagslist,")
                .append("time,")
                .append("archive,")
                .append("anonflg,")
                .append("super_anonflg,")
                .append("userid,")
                .append("photo,")
                .append("ask_type,")
                .append("photo_type,")
                .append("bit_settings,")
                .append("q_link,")
                .append("revision_flg,")
                .append("city_id,")
                .append("answer_count,")
                .append("pin_count")
                .append("user_pinned")
                .append("safefilter")
                .append("channel_id")
                .append("extra_photo_id,")
                .append("extra_content_image_url_small,")
                .append("extra_content_image_url_medium,")
                .append("extra_content_image_creator_name,")
                .append("extra_content_image_creator_username,")
                .append("extra_content_image_,")
                .append("extra_hebrew_time,")
                .append("extra_url,")
                .append("extra_url_title,")
                .append("extra_item_profile_nickname,")
                .append("extra_item_profile_anonflg,")
                .append("extra_item_profile_active,")
                .append("meta_active,")
                .append("meta_permissions_edit,")
                .append("meta_permissions_delete,")
                .append("meta_permissions_deleteWithoutMsg,")
                .append("meta_permissions_ban,")
                .append("meta_permissions_report,")
                .append("meta_permissions_showAdminMsgs,")
                .append("meta_permissions_,")
                .append("meta_permissions_itemOwner,")
                .append("meta_permissions_restore,")
                .append("meta_permissions_removeFromChannel,")
                .append("meta_moreUserDetails_userid,")
                .append("meta_moreUserDetails_ip,").toString();
    }

}
