
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
    "objType",
    "data",
    "extra",
    "meta"
})
public class Datum {

    @JsonProperty("objType")
    private String objType;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("extra")
    private Extra extra;
    @JsonProperty("meta")
    private Meta meta;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("objType")
    public String getObjType() {
        return objType;
    }

    @JsonProperty("objType")
    public void setObjType(String objType) {
        this.objType = objType;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("extra")
    public Extra getExtra() {
        return extra;
    }

    @JsonProperty("extra")
    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
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
