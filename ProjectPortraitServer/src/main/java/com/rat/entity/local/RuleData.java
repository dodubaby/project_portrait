package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class RuleData implements Serializable {
    private long id;
    private long ruleId;
    private long fileId;
    private String data;
    private long dataLine;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRuleId() {
        return ruleId;
    }

    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getDataLine() {
        return dataLine;
    }

    public void setDataLine(long dataLine) {
        this.dataLine = dataLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
