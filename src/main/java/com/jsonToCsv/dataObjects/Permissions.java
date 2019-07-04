
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
    "edit",
    "delete",
    "deleteWithoutMsg",
    "ban",
    "report",
    "showAdminMsgs",
    "itemOwner",
    "restore",
    "removeFromChannel"
})
public class Permissions {

    @JsonProperty("edit")
    private Boolean edit;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("deleteWithoutMsg")
    private Boolean deleteWithoutMsg;
    @JsonProperty("ban")
    private Boolean ban;
    @JsonProperty("report")
    private Boolean report;
    @JsonProperty("showAdminMsgs")
    private Boolean showAdminMsgs;
    @JsonProperty("itemOwner")
    private Boolean itemOwner;
    @JsonProperty("restore")
    private Boolean restore;
    @JsonProperty("removeFromChannel")
    private Boolean removeFromChannel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("edit")
    public Boolean getEdit() {
        return edit;
    }

    @JsonProperty("edit")
    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @JsonProperty("delete")
    public Boolean getDelete() {
        return delete;
    }

    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @JsonProperty("deleteWithoutMsg")
    public Boolean getDeleteWithoutMsg() {
        return deleteWithoutMsg;
    }

    @JsonProperty("deleteWithoutMsg")
    public void setDeleteWithoutMsg(Boolean deleteWithoutMsg) {
        this.deleteWithoutMsg = deleteWithoutMsg;
    }

    @JsonProperty("ban")
    public Boolean getBan() {
        return ban;
    }

    @JsonProperty("ban")
    public void setBan(Boolean ban) {
        this.ban = ban;
    }

    @JsonProperty("report")
    public Boolean getReport() {
        return report;
    }

    @JsonProperty("report")
    public void setReport(Boolean report) {
        this.report = report;
    }

    @JsonProperty("showAdminMsgs")
    public Boolean getShowAdminMsgs() {
        return showAdminMsgs;
    }

    @JsonProperty("showAdminMsgs")
    public void setShowAdminMsgs(Boolean showAdminMsgs) {
        this.showAdminMsgs = showAdminMsgs;
    }

    @JsonProperty("itemOwner")
    public Boolean getItemOwner() {
        return itemOwner;
    }

    @JsonProperty("itemOwner")
    public void setItemOwner(Boolean itemOwner) {
        this.itemOwner = itemOwner;
    }

    @JsonProperty("restore")
    public Boolean getRestore() {
        return restore;
    }

    @JsonProperty("restore")
    public void setRestore(Boolean restore) {
        this.restore = restore;
    }

    @JsonProperty("removeFromChannel")
    public Boolean getRemoveFromChannel() {
        return removeFromChannel;
    }

    @JsonProperty("removeFromChannel")
    public void setRemoveFromChannel(Boolean removeFromChannel) {
        this.removeFromChannel = removeFromChannel;
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
