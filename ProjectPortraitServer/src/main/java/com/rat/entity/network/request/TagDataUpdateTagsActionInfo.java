package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class TagDataUpdateTagsActionInfo extends ActionInfo {
    private long dataId;
    private String dataType;
    private String[] tagArray;
    //文件类型
    public static final String FILE_TYPE = "file";
    public static final String DIR_TYPE = "dir";

    public TagDataUpdateTagsActionInfo(int actionId, long dataId, String dataType, String[] tagArray) {
        super(actionId);
        this.dataId = dataId;
        this.dataType = dataType;
        this.tagArray = tagArray;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String[] getTagArray() {
        return tagArray;
    }

    public void setTagArray(String[] tagArray) {
        this.tagArray = tagArray;
    }
}

