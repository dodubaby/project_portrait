package com.rat.entity.enums;

/**
 * Created by liangjinzhu on 17/4/10.
 */
public enum ResponseType {
    SUCCESS(100, "success"),
    ERROR(-1, "服务器错误，请稍后重试"),
    ERROR_4_PARAM(-2, "请求参数有误"),
    ERROR_4_OTHER_PLATFORM(-3, "第三方平台异常，请稍后重试"),

    ERROR_4_SECRITY_CODE_ERROR(-4, "验证码错误"),
    ERROR_4_USER_IS_NOT_EXIST(-5, "用户不存在或已被屏蔽");

    private int code;
    private String message;

    ResponseType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
