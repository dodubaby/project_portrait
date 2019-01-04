package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class Reference implements Serializable {
    private long id;
    private long fileId;
    private long referenceDataId;
    private String referenceDataType;
    private String referenceData;
    private long referenceLine;

    // TODO by L.jinzhu for 待优化
    // 仅用于UI展示
    private String source;
    private String target;
    private String type;

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
        setSource(String.valueOf(fileId));
    }

    public long getReferenceDataId() {
        return referenceDataId;
    }

    public void setReferenceDataId(long referenceDataId) {
        this.referenceDataId = referenceDataId;
        setTarget(String.valueOf(referenceDataId));
    }

    public String getReferenceDataType() {
        return referenceDataType;
    }

    public void setReferenceDataType(String referenceDataType) {
        this.referenceDataType = referenceDataType;
    }

    public String getReferenceData() {
        return referenceData;
    }

    public void setReferenceData(String referenceData) {
        this.referenceData = referenceData;
    }

    public long getReferenceLine() {
        return referenceLine;
    }

    public void setReferenceLine(long referenceLine) {
        this.referenceLine = referenceLine;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = "suit";
    }
}
