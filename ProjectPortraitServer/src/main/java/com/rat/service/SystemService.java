package com.rat.service;

import com.rat.common.Constant;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.NewVersionRspInfo;
import com.rat.entity.network.response.ResourceNamesRspInfo;
import com.rat.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 系统服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class SystemService {

    private static Logger logger = LoggerFactory.getLogger(SystemService.class);

    public SystemService() {
    }

    /**
     * 最新版本
     *
     * @param actionInfo
     * @return
     */
    public NewVersionRspInfo getNewVersion(ActionInfoWithPageData actionInfo) {
        NewVersionRspInfo rspInfo = new NewVersionRspInfo();
        if (StringUtil.isNullOrBlank(Constant.versionCode) || StringUtil.isNullOrBlank(Constant.versionName)) {
            rspInfo.initError4System(actionInfo.getActionId());
        } else {
            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setVersionCode(Constant.versionCode);
            rspInfo.setVersionName(Constant.versionName);
            rspInfo.setIsForced(Constant.isForced);
            rspInfo.setDownloadUrl(Constant.downloadUrl);
            rspInfo.setDes(Constant.des);
        }
        return rspInfo;
    }

    /**
     * 视频名称列表
     *
     * @param actionInfo
     * @return
     */
    public ResourceNamesRspInfo getResourceNames(ActionInfoWithPageData actionInfo) {
        ResourceNamesRspInfo rspInfo = new ResourceNamesRspInfo();
        if (StringUtil.isNullOrBlank(Constant.resourceNames)) {
            rspInfo.initError4System(actionInfo.getActionId());
        } else {
            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setResourceNames(Constant.resourceNames);
        }
        return rspInfo;
    }
}

