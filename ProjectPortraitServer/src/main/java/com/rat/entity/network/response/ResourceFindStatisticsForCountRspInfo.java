package com.rat.entity.network.response;

import com.rat.entity.local.ResourceDataStatistics;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.entity.network.response.base.ResponseInfoWithPageData;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResourceFindStatisticsForCountRspInfo extends ResponseInfoWithPageData {
    private List<ResourceDataStatistics> resourceDataStatisticsList;

    public List<ResourceDataStatistics> getResourceDataStatisticsList() {
        return resourceDataStatisticsList;
    }

    public void setResourceDataStatisticsList(List<ResourceDataStatistics> resourceDataStatisticsList) {
        this.resourceDataStatisticsList = resourceDataStatisticsList;
    }
}