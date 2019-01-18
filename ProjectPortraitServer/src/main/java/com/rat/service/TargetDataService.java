package com.rat.service;

import com.rat.dao.TargetDataDao;
import com.rat.entity.local.TargetData;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.TargetDataFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.DataPageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 目标数据服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class TargetDataService extends BaseService {
    @Resource
    private TargetDataDao targetDataDao;

    public TargetDataService() {
    }

    public TargetDataFindAllRspInfo findAll(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<TargetData> targetDataList = targetDataDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        TargetDataFindAllRspInfo rspInfo = new TargetDataFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTargetDataList(targetDataList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(targetDataList.size()));
        return rspInfo;
    }
}

