package com.rat.entity.network.response;

import com.rat.entity.local.UserDetail;
import com.rat.entity.network.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class UserRegisterLoginRspInfo extends ResponseInfo {
    private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        return "UserRegisterLoginRspInfo{" +
                "actionId=" + actionId +
                ", statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                '}';
    }
}