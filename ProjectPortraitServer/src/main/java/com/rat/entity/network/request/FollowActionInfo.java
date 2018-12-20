package com.rat.entity.network.request;


import com.rat.entity.local.user.Follow;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class FollowActionInfo extends ActionInfo {
    public static final int FOLLOW_TYPE_OK = 1;// 关注
    public static final int FOLLOW_TYPE_CANCLE = 2;// 取消关注

    private int type;
    private Follow follow;

    public FollowActionInfo(int actionId, int type, Follow follow) {
        super(actionId);
        this.type = type;
        this.follow = follow;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}