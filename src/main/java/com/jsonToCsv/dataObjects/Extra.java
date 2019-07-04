
package com.jsonToCsv.dataObjects;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "photo_id",
    "content_image",
    "hebrew_time",
    "url",
    "url_title",
    "item_profile"
})
public class Extra {

    @JsonProperty("photo_id")
    private Object photoId;
    @JsonProperty("content_image")
    private ContentImage contentImage;
    @JsonProperty("hebrew_time")
    private String hebrewTime;
    @JsonProperty("url")
    private String url;
    @JsonProperty("url_title")
    private String urlTitle;
    @JsonProperty("item_profile")
    private ItemProfile itemProfile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("photo_id")
    public Object getPhotoId() {
        return photoId;
    }

    @JsonProperty("photo_id")
    public void setPhotoId(Object photoId) {
        this.photoId = photoId;
    }

    @JsonProperty("content_image")
    public ContentImage getContentImage() {
        return contentImage;
    }

    @JsonProperty("content_image")
    public void setContentImage(ContentImage contentImage) {
        this.contentImage = contentImage;
    }

    @JsonProperty("hebrew_time")
    public String getHebrewTime() {
        return hebrewTime;
    }

    @JsonProperty("hebrew_time")
    public void setHebrewTime(String hebrewTime) {
        this.hebrewTime = hebrewTime;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("url_title")
    public String getUrlTitle() {
        return urlTitle;
    }

    @JsonProperty("url_title")
    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    @JsonProperty("item_profile")
    public ItemProfile getItemProfile() {
        return itemProfile;
    }

    @JsonProperty("item_profile")
    public void setItemProfile(ItemProfile itemProfile) {
        this.itemProfile = itemProfile;
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
