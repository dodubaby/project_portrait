package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.dao.ReferenceDao;
import com.rat.entity.local.Reference;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.ReferenceFindAllRspInfo;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 引用服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class ReferenceService {

    private static Logger logger = LoggerFactory.getLogger(ReferenceService.class);
    @Resource
    private ReferenceDao referenceDao;
    @Resource
    private FileDao fileDao;

    public ReferenceService() {
    }

    public ReferenceFindAllRspInfo findAll(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<Reference> referenceList = referenceDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        // TODO by L.jinzhu for sql 待优化
        for (Reference reference : referenceList) {
            reference.setFileName(fileDao.findNameById(reference.getFileId()));
            reference.setReferenceDataName(fileDao.findNameById(reference.getReferenceDataId()));
        }
        ReferenceFindAllRspInfo rspInfo = new ReferenceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setReferenceList(referenceList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(referenceList.size()));
        return rspInfo;
    }
}
