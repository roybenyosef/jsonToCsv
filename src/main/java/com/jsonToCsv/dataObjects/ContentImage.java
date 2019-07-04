
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
    "url_small",
    "url_medium",
    "creator_name",
    "creator_username"
})
public class ContentImage {

    @JsonProperty("url_small")
    private String urlSmall;
    @JsonProperty("url_medium")
    private String urlMedium;
    @JsonProperty("creator_name")
    private String creatorName;
    @JsonProperty("creator_username")
    private String creatorUsername;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("url_small")
    public String getUrlSmall() {
        return urlSmall;
    }

    @JsonProperty("url_small")
    public void setUrlSmall(String urlSmall) {
        this.urlSmall = urlSmall;
    }

    @JsonProperty("url_medium")
    public String getUrlMedium() {
        return urlMedium;
    }

    @JsonProperty("url_medium")
    public void setUrlMedium(String urlMedium) {
        this.urlMedium = urlMedium;
    }

    @JsonProperty("creator_name")
    public String getCreatorName() {
        return creatorName;
    }

    @JsonProperty("creator_name")
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @JsonProperty("creator_username")
    public String getCreatorUsername() {
        return creatorUsername;
    }

    @JsonProperty("creator_username")
    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
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
