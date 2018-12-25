package com.rat.entity.local;

import java.io.Serializable;

/**
 * 验证码实体类
 *
 * @author L.jinzhu
 * @date 2017-03-31 18:07
 */
public class TargetaData implements Serializable {
    public static final String CODE_TYPE_REGISTER_LOGIN = "1";// 1、登录注册
    public static final String CODE_TYPE_BIND_PHONE = "2";// 2、绑定手机号

    private String phoneNumber; // 手机号
    private String code; // 验证码
    private String type;// 验证码类型 this.CODE_TYPE
    private String sendTime;// 发送时间

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
