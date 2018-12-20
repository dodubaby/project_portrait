package com.rat.entity.network.request;


import com.rat.entity.local.user.User;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class UserUpdateActionInfo extends ActionInfo {
    private User user;

    public UserUpdateActionInfo(int actionId, User user) {
        super(actionId);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}