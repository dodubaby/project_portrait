package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class TargetData implements Serializable {
    private long id;
    private long fileId;
    private String targetData;
    private long targetDataLine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getTargetData() {
        return targetData;
    }

    public void setTargetData(String targetData) {
        this.targetData = targetData;
    }

    public long getTargetDataLine() {
        return targetDataLine;
    }

    public void setTargetDataLine(long targetDataLine) {
        this.targetDataLine = targetDataLine;
    }
}
