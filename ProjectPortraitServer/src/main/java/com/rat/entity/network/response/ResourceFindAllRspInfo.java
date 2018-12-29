package com.rat.entity.network.response;

import com.rat.entity.local.ResourceData;
import com.rat.entity.network.response.base.ResponseInfoWithPageData;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResourceFindAllRspInfo extends ResponseInfoWithPageData {
    private List<ResourceData> resourceList;

    public List<ResourceData> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceData> resourceList) {
        this.resourceList = resourceList;
    }
}