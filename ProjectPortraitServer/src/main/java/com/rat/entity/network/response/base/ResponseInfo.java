package com.rat.entity.network.response.base;

import com.rat.entity.enums.ResponseType;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResponseInfo extends AbstractResponseInfo {
    protected int actionId;
    protected int statusCode;
    protected String statusMsg;

    public void initSuccess(int actionId) {
        this.actionId = actionId;
        this.statusCode = ResponseType.SUCCESS.getCode();
        this.statusMsg = ResponseType.SUCCESS.getMessage();
    }

    public void initError(int actionId, ResponseType responseType) {
        this.actionId = actionId;
        this.statusCode = responseType.getCode();
        this.statusMsg = responseType.getMessage();
    }

    public void initError4System(int actionId) {
        this.actionId = actionId;
        this.statusCode = ResponseType.ERROR.getCode();
        this.statusMsg = ResponseType.ERROR.getMessage();
    }

    public void initError4Param(int actionId) {
        this.actionId = actionId;
        this.statusCode = ResponseType.ERROR_4_PARAM.getCode();
        this.statusMsg = ResponseType.ERROR_4_PARAM.getMessage();
    }

    public void initError4OtherPlatform(int actionId) {
        this.actionId = actionId;
        this.statusCode = ResponseType.ERROR_4_OTHER_PLATFORM.getCode();
        this.statusMsg = ResponseType.ERROR_4_OTHER_PLATFORM.getMessage();
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}