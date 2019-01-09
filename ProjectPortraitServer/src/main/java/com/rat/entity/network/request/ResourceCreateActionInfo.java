package com.rat.entity.network.request;


import com.rat.entity.local.ResourceData;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class ResourceCreateActionInfo extends ActionInfo {
    private ResourceData resourceData;

    public ResourceCreateActionInfo(int actionId, ResourceData resourceData) {
        super(actionId);
        this.resourceData = resourceData;
    }

    public ResourceData getResourceData() {
        return resourceData;
    }

    public void setResourceData(ResourceData resourceData) {
        this.resourceData = resourceData;
    }
}