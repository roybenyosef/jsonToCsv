
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
    "nickname",
    "anonflg",
    "active",
    "userid",
    "photostamp",
    "online"
})
public class ItemProfile {

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("anonflg")
    private Boolean anonflg;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("userid")
    private Integer userid;
    @JsonProperty("photostamp")
    private String photostamp;
    @JsonProperty("online")
    private Boolean online;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonProperty("anonflg")
    public Boolean getAnonflg() {
        return anonflg;
    }

    @JsonProperty("anonflg")
    public void setAnonflg(Boolean anonflg) {
        this.anonflg = anonflg;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("userid")
    public Integer getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @JsonProperty("photostamp")
    public String getPhotostamp() {
        return photostamp;
    }

    @JsonProperty("photostamp")
    public void setPhotostamp(String photostamp) {
        this.photostamp = photostamp;
    }

    @JsonProperty("online")
    public Boolean getOnline() {
        return online;
    }

    @JsonProperty("online")
    public void setOnline(Boolean online) {
        this.online = online;
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
