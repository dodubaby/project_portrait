package com.rat.entity.network.request;


import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class TargetDataSendActionInfo extends ActionInfo {
    private String phoneNumber;
    private String type;

    public TargetDataSendActionInfo(int actionId, String phoneNumber, String type) {
        super(actionId);
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}