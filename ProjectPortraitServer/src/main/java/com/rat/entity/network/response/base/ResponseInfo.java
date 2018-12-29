package com.rat.entity.network.response.base;

import com.rat.common.Constant;
import com.rat.controller.RequestController;
import com.rat.entity.enums.ResponseType;
import com.rat.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ResponseInfo extends AbstractResponseInfo {
    private static Logger logger = LoggerFactory.getLogger(RequestController.class);

    protected int actionId;
    protected int statusCode;
    protected String statusMsg;

    public void init(int actionId, ResponseType responseType) {
        this.actionId = actionId;
        this.statusCode = responseType.getCode();
        this.statusMsg = responseType.getMessage();
    }

    public void initSuccess(int actionId) {
        init(actionId, ResponseType.SUCCESS);
    }

    public void initError4System(int actionId) {
        init(actionId, ResponseType.ERROR);
    }

    public void initError4Param(int actionId) {
        init(actionId, ResponseType.ERROR_4_PARAM);
    }

    public static String getErrorResponse4Param(int actionId) {
        ResponseInfo response = new ResponseInfo();
        response.initError4Param(actionId);
        logger.error(Constant.LOG_RESPONSE + ": " + response.toString());
        return GsonUtil.toJson(response);
    }
}