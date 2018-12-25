package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class Reference implements Serializable {
    private long userId; // 用户id,唯一标示
    private long referenceUserId; // 关注的用户id,唯一标示

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReferenceUserId() {
        return referenceUserId;
    }

    public void setReferenceUserId(long referenceUserId) {
        this.referenceUserId = referenceUserId;
    }
}
