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

    public long getReferenceDataId() {
        return referenceDataId;
    }

    public void setReferenceDataId(long referenceDataId) {
        this.referenceDataId = referenceDataId;
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
}
