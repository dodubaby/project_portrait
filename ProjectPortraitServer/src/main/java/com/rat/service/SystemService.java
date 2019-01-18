package com.rat.service;

import com.rat.common.Constant;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.NewVersionRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.StringUtil;
import org.springframework.stereotype.Service;

/**
 * 系统服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class SystemService extends BaseService {
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
}
