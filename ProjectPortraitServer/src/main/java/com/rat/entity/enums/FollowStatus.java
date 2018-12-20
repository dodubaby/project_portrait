package com.rat.entity.enums;

/**
 * Created by liangjinzhu on 17/4/10.
 */
public enum FollowStatus {
    NONE(0), // 互相不关注
    FOLLOWED_BY_LOGIN_USER(1), // 当前登录用户已关注此User
    FOLLOW_LOGIN_USER(2), // 此User已关注当前登录用户
    EACH_OTHER(3); // 互相关注
    private int code;

    FollowStatus(int code) {
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
