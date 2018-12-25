package com.rat.entity.network.response;

import com.rat.entity.local.ResourceData;
import com.rat.entity.network.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResourceCreateRspInfo extends ResponseInfo {
    private ResourceData resourceData;

    public ResourceData getResourceData() {
        return resourceData;
    }

    public void setResourceData(ResourceData resourceData) {
        this.resourceData = resourceData;
    }
}