
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
    "status",
    "error_code",
    "data",
    "sqls",
    "exec_time"
})
public class Results {

    @JsonProperty("status")
    private String status;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("data")
    private List<Datum> data = null;
    @JsonProperty("sqls")
    private List<Sql> sqls = null;
    @JsonProperty("exec_time")
    private String execTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("error_code")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    @JsonProperty("sqls")
    public List<Sql> getSqls() {
        return sqls;
    }

    @JsonProperty("sqls")
    public void setSqls(List<Sql> sqls) {
        this.sqls = sqls;
    }

    @JsonProperty("exec_time")
    public String getExecTime() {
        return execTime;
    }

    @JsonProperty("exec_time")
    public void setExecTime(String execTime) {
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
