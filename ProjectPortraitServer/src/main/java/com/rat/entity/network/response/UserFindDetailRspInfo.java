package com.rat.entity.network.response;

import com.rat.entity.local.UserDetail;
import com.rat.entity.network.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class UserFindDetailRspInfo extends ResponseInfo {
    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
}