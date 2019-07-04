
package com.jsonToCsv.dataObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "q",
    "text_content",
    "name",
    "tagslist",
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
    "channel_id"
})
public class Data {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("q")
    private String q;
    @JsonProperty("text_content")
    private String textContent;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tagslist")
    private List<String> tagslist = null;
    @JsonProperty("time")
    private String time;
    @JsonProperty("archive")
    private Boolean archive;
    @JsonProperty("anonflg")
    private Boolean anonflg;
    @JsonProperty("super_anonflg")
    private Boolean superAnonflg;
    @JsonProperty("userid")
    private Integer userid;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("ask_type")
    private Integer askType;
    @JsonProperty("photo_type")
    private Integer photoType;
    @JsonProperty("bit_settings")
    private Integer bitSettings;
    @JsonProperty("q_link")
    private String qLink;
    @JsonProperty("revision_flg")
    private Boolean revisionFlg;
    @JsonProperty("city_id")
    private Integer cityId;
    @JsonProperty("answer_count")
    private Integer answerCount;
    @JsonProperty("pin_count")
    private Integer pinCount;
    @JsonProperty("user_pinned")
    private Integer userPinned;
    @JsonProperty("safefilter")
    private Boolean safefilter;
    @JsonProperty("channel_id")
    private Integer channelId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("q")
    public String getQ() {
        return q;
    }

    @JsonProperty("q")
    public void setQ(String q) {
        this.q = q;
    }

    @JsonProperty("text_content")
    public String getTextContent() {
        return textContent;
    }

    @JsonProperty("text_content")
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("tagslist")
    public List<String> getTagslist() {
        return tagslist;
    }

    @JsonProperty("tagslist")
    public void setTagslist(List<String> tagslist) {
        this.tagslist = tagslist;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("archive")
    public Boolean getArchive() {
        return archive;
    }

    @JsonProperty("archive")
    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    @JsonProperty("anonflg")
    public Boolean getAnonflg() {
        return anonflg;
    }

    @JsonProperty("anonflg")
    public void setAnonflg(Boolean anonflg) {
        this.anonflg = anonflg;
    }

    @JsonProperty("super_anonflg")
    public Boolean getSuperAnonflg() {
        return superAnonflg;
    }

    @JsonProperty("super_anonflg")
    public void setSuperAnonflg(Boolean superAnonflg) {
        this.superAnonflg = superAnonflg;
    }

    @JsonProperty("userid")
    public Integer getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @JsonProperty("photo")
    public String getPhoto() {
        return photo;
    }

    @JsonProperty("photo")
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty("ask_type")
    public Integer getAskType() {
        return askType;
    }

    @JsonProperty("ask_type")
    public void setAskType(Integer askType) {
        this.askType = askType;
    }

    @JsonProperty("photo_type")
    public Integer getPhotoType() {
        return photoType;
    }

    @JsonProperty("photo_type")
    public void setPhotoType(Integer photoType) {
        this.photoType = photoType;
    }

    @JsonProperty("bit_settings")
    public Integer getBitSettings() {
        return bitSettings;
    }

    @JsonProperty("bit_settings")
    public void setBitSettings(Integer bitSettings) {
        this.bitSettings = bitSettings;
    }

    @JsonProperty("q_link")
    public String getQLink() {
        return qLink;
    }

    @JsonProperty("q_link")
    public void setQLink(String qLink) {
        this.qLink = qLink;
    }

    @JsonProperty("revision_flg")
    public Boolean getRevisionFlg() {
        return revisionFlg;
    }

    @JsonProperty("revision_flg")
    public void setRevisionFlg(Boolean revisionFlg) {
        this.revisionFlg = revisionFlg;
    }

    @JsonProperty("city_id")
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("answer_count")
    public Integer getAnswerCount() {
        return answerCount;
    }

    @JsonProperty("answer_count")
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    @JsonProperty("pin_count")
    public Integer getPinCount() {
        return pinCount;
    }

    @JsonProperty("pin_count")
    public void setPinCount(Integer pinCount) {
        this.pinCount = pinCount;
    }

    @JsonProperty("user_pinned")
    public Integer getUserPinned() {
        return userPinned;
    }

    @JsonProperty("user_pinned")
    public void setUserPinned(Integer userPinned) {
        this.userPinned = userPinned;
    }

    @JsonProperty("safefilter")
    public Boolean getSafefilter() {
        return safefilter;
    }

    @JsonProperty("safefilter")
    public void setSafefilter(Boolean safefilter) {
        this.safefilter = safefilter;
    }

    @JsonProperty("channel_id")
    public Integer getChannelId() {
        return channelId;
    }

    @JsonProperty("channel_id")
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
