
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
    "sql",
    "source",
    "exec_time"
})
public class Sql {

    @JsonProperty("sql")
    private String sql;
    @JsonProperty("source")
    private String source;
    @JsonProperty("exec_time")
    private Integer execTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sql")
    public String getSql() {
        return sql;
    }

    @JsonProperty("sql")
    public void setSql(String sql) {
        this.sql = sql;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("exec_time")
    public Integer getExecTime() {
        return execTime;
    }

    @JsonProperty("exec_time")
    public void setExecTime(Integer execTime) {
        this.execTime = execTime;
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
