package com.rat.service;

import com.rat.dao.ReferenceDao;
import com.rat.entity.local.Reference;
import com.rat.entity.local.File;
import com.rat.entity.network.request.ReferenceActionInfo;
import com.rat.entity.network.request.ReferenceFindAllActionInfo;
import com.rat.entity.network.response.ReferenceFindAllRspInfo;
import com.rat.entity.network.response.ReferenceRspInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关注服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class ReferenceService {

    private static Logger logger = LoggerFactory.getLogger(ReferenceService.class);
    @Resource
    private ReferenceDao referenceDao;

    public ReferenceService() {
    }

    /**
     * 关注、取消关注
     *
     * @param actionInfo
     * @return
     */
    public ReferenceRspInfo reference(ReferenceActionInfo actionInfo) {
        int type = actionInfo.getType();
        Reference reference = actionInfo.getReference();
        if (null == reference) {
            ReferenceRspInfo rspInfo = new ReferenceRspInfo();
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }
        switch (type) {
            // 关注
            case ReferenceActionInfo.FOLLOW_TYPE_OK:
                referenceDao.create(reference.getUserId(), reference.getReferenceUserId());
                break;
            // 取消关注
            case ReferenceActionInfo.FOLLOW_TYPE_CANCLE:
                referenceDao.delete(reference.getUserId(), reference.getReferenceUserId());
                break;
            default:
                ReferenceRspInfo rspInfo = new ReferenceRspInfo();
                rspInfo.initError4Param(actionInfo.getActionId());
                return rspInfo;
        }
        ReferenceRspInfo rspInfo = new ReferenceRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());

        return rspInfo;
    }

    /**
     * 通过关注人查找
     *
     * @param actionInfo
     * @return
     */
    public ReferenceFindAllRspInfo findAllByUserId(ReferenceFindAllActionInfo actionInfo) {
        List<File> fileList = referenceDao.findByUserId(actionInfo.getUserId());

        ReferenceFindAllRspInfo rspInfo = new ReferenceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);

        return rspInfo;
    }

    /**
     * 通过被关注人查找
     *
     * @param actionInfo
     * @return
     */
    public ReferenceFindAllRspInfo findAllByReferenceedUserId(ReferenceFindAllActionInfo actionInfo) {
        List<File> fileList = referenceDao.findByReferenceedUserId(actionInfo.getUserId());

        ReferenceFindAllRspInfo rspInfo = new ReferenceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);

        return rspInfo;
    }
}

