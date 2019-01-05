package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfoWithPageData;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class TagFindByTypeActionInfo extends ActionInfoWithPageData {
    private String type;

    public TagFindByTypeActionInfo(int actionId, int pageNumber, int dataGetType, String type) {
        super(actionId, pageNumber, dataGetType);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}