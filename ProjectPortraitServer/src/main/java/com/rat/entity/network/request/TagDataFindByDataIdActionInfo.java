package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class TagDataFindByDataIdActionInfo extends ActionInfo {
    private long dataId;
    private String dataType;

    public TagDataFindByDataIdActionInfo(int actionId, long dataId, String dataType) {
        super(actionId);
        this.dataId = dataId;
        this.dataType = dataType;
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
}