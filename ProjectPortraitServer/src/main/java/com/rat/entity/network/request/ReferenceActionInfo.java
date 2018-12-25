package com.rat.entity.network.request;


import com.rat.entity.local.Reference;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class ReferenceActionInfo extends ActionInfo {
    public static final int FOLLOW_TYPE_OK = 1;// 关注
    public static final int FOLLOW_TYPE_CANCLE = 2;// 取消关注

    private int type;
    private Reference reference;

    public ReferenceActionInfo(int actionId, int type, Reference reference) {
        super(actionId);
        this.type = type;
        this.reference = reference;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}