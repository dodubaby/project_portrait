package com.rat.service;

import com.rat.dao.TargetDataDao;
import com.rat.entity.local.TargetData;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.TargetDataFindAllActionInfo;
import com.rat.entity.network.response.TargetDataFindAllRspInfo;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 目标数据服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class TargetDataService {

    private static Logger logger = LoggerFactory.getLogger(TargetDataService.class);
    @Resource
    private TargetDataDao targetDataDao;

    public TargetDataService() {
    }

    public TargetDataFindAllRspInfo findAll(TargetDataFindAllActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<TargetData> targetDataList = targetDataDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        TargetDataFindAllRspInfo rspInfo = new TargetDataFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTargetDataList(targetDataList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(targetDataList.size()));
        return rspInfo;
    }

    /**
     * 通过关注人查找
     *
     * @param actionInfo
     * @return
     */
    public TargetDataFindAllRspInfo findAllByUserId(TargetDataFindAllActionInfo actionInfo) {

        TargetDataFindAllRspInfo rspInfo = new TargetDataFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());

        return rspInfo;
    }

    /**
     * 通过被关注人查找
     *
     * @param actionInfo
     * @return
     */
    public TargetDataFindAllRspInfo findAllByTargetDataedUserId(TargetDataFindAllActionInfo actionInfo) {
        TargetDataFindAllRspInfo rspInfo = new TargetDataFindAllRspInfo();

        return rspInfo;
    }
}

