package com.rat.service;

import com.rat.common.Constant;
import com.rat.dao.FileDao;
import com.rat.dao.ReferenceDao;
import com.rat.entity.local.File;
import com.rat.entity.local.Reference;
import com.rat.entity.network.request.ReferenceActionInfo;
import com.rat.entity.network.response.ReferenceFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 引用服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class ReferenceService extends BaseService {
    @Resource
    private ReferenceDao referenceDao;
    @Resource
    private FileDao fileDao;

    List<Reference> referenceList;
    List<Long> cacheDataList;

    public ReferenceService() {
    }

    public ReferenceFindAllRspInfo findAll(ReferenceActionInfo actionInfo) {
        String key = actionInfo.getKey();
        File file = null;
        // 无key，获取所有
        if (StringUtil.isNullOrBlank(key)) {
            file = new File(0, Constant.DATA_ALL);
            referenceList = referenceDao.findAll(file.getId());
        }
        // 有key，获取key对应数据
        else {
            referenceList = new ArrayList<>();
            cacheDataList = new ArrayList<>();
            // 先通过key（文件名）获取文件
            file = fileDao.findByFullName(key);
            if (null == file) {
                file = new File(0, Constant.DATA_ERROR);
            }
            Long id = file.getId();
            // 循环获取引用数据
            findByReferenceIdCirculation(id);
        }
        // 为最终的引用关系列表，添加文件名称、引用数据名称等
        for (Reference reference : referenceList) {
            reference.setFileName(fileDao.findNameById(reference.getFileId()));
            reference.setReferenceDataName(fileDao.findNameById(reference.getReferenceDataId()));
        }
        ReferenceFindAllRspInfo rspInfo = new ReferenceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFile(file);
        rspInfo.setReferenceList(referenceList);
        return rspInfo;
    }

    /**
     * 循环获取引用数据
     *
     * @param referenceId
     * @return
     */
    private void findByReferenceIdCirculation(Long referenceId) {
        if (null == referenceId || 0 == referenceId) {
            return;
        }
        // 重复数据不查询
        if (cacheDataList.contains(referenceId)) {
            logger.info(Constant.LOG_TAG + ": 查询一次，结果：old data，ignore");
            return;
        }
        cacheDataList.add(referenceId);
        List<Reference> tempList = referenceDao.findAll(referenceId);
        if (CollectionUtils.isEmpty(tempList)) {
            return;
        }
        logger.info(Constant.LOG_TAG + ": 查询一次，结果：" + tempList.size());
        referenceList.addAll(tempList);
        for (Reference reference : tempList) {
            findByReferenceIdCirculation(reference.getFileId());
        }
    }
}
