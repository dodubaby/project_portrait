package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 请求实体
 */
public class ResourceDeleteActionInfo extends ActionInfo {
    private Long userId;
    private List<Long> resourceIdList;

    public ResourceDeleteActionInfo(int actionId, Long userId, List<Long> resourceIdList) {
        super(actionId);
        this.userId = userId;
        this.resourceIdList = resourceIdList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}