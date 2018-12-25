package com.rat.entity.network.response;

import com.rat.entity.network.response.base.ResponseInfo;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResourceNamesRspInfo extends ResponseInfo {
    private String resourceNames;

    public String getResourceNames() {
        return resourceNames;
    }

    public void setResourceNames(String resourceNames) {
        this.resourceNames = resourceNames;
    }
}