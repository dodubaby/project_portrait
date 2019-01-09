package com.rat.entity.network.response;

import com.rat.entity.local.ResourceData;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class ResourceDeleteRspInfo extends ResponseInfo {
    private List<ResourceData> resourceList;

    public List<ResourceData> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceData> resourceList) {
        this.resourceList = resourceList;
    }
}