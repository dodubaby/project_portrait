package com.rat.entity.local;

import com.rat.common.Constant;
import com.rat.utils.StringUtil;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class Reference implements Serializable {
    private long id;
    private long fileId;// 文件id
    private String fileName;// 文件名
    private long referenceDataId;// 引用内容ID（类：类名、资源：无）
    private String referenceDataName;// 引用内容名字（类：类名、资源：资源名）
    private String referenceDataType;// 引用内容类型（java、resource）
    private String referenceData;// 引用内容所在的原文
    private long referenceLine;// 引用内容所在的行数

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (StringUtil.isNullOrBlank(source)) {
            source = Constant.DATA_ERROR;
        }
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        if (StringUtil.isNullOrBlank(target)) {
            target = Constant.DATA_ERROR;
        }
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        setSource(String.valueOf(fileName));
    }

    public String getReferenceDataName() {
        return referenceDataName;
    }

    public void setReferenceDataName(String referenceDataName) {
        this.referenceDataName = referenceDataName;
        setTarget(String.valueOf(referenceDataName));
        setType("");
    }

    public void setType(String type) {
        this.type = "suit";
    }
}
