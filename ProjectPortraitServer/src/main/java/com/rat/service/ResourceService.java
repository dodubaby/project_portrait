package com.rat.service;

import com.rat.dao.ResourceDao;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.ResourceDataStatistics;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.ResourceCreateActionInfo;
import com.rat.entity.network.request.ResourceDeleteActionInfo;
import com.rat.entity.network.request.ResourceFindByValueActionInfo;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.ResourceDeleteRspInfo;
import com.rat.entity.network.response.ResourceFindAllRspInfo;
import com.rat.entity.network.response.ResourceFindStatisticsForCountRspInfo;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class ResourceService {

    private static Logger logger = LoggerFactory.getLogger(ResourceService.class);
    @Resource
    private ResourceDao resourceDao;

    public ResourceService() {
    }

    public ResourceFindAllRspInfo findAll(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<ResourceData> resourceList = resourceDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        ResourceFindAllRspInfo rspInfo = new ResourceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setResourceList(resourceList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(resourceList.size()));
        return rspInfo;
    }

    public ResourceFindStatisticsForCountRspInfo findStatisticsForCount(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<ResourceDataStatistics> statisticsList = resourceDao.findStatisticsForCount(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        ResourceFindStatisticsForCountRspInfo rspInfo = new ResourceFindStatisticsForCountRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setResourceDataStatisticsList(statisticsList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(statisticsList.size()));
        return rspInfo;
    }

    public ResourceFindAllRspInfo findByValue(ResourceFindByValueActionInfo actionInfo) {
        List<ResourceData> resourceDataList = resourceDao.findByValue(actionInfo.getValue());
        ResourceFindAllRspInfo rspInfo = new ResourceFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setResourceList(resourceDataList);
        return rspInfo;
    }

    public ResourceFindAllRspInfo create(ResourceCreateActionInfo actionInfo) {
        ResourceFindAllRspInfo rspInfo = new ResourceFindAllRspInfo();
        ResourceData resource = actionInfo.getResourceData();
        if (null == resource) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }

        return rspInfo;
    }

    public ResourceDeleteRspInfo delete(ResourceDeleteActionInfo actionInfo) {
        ResourceDeleteRspInfo rspInfo = new ResourceDeleteRspInfo();
        List<Long> resourceIdList = actionInfo.getResourceIdList();
        if (null == resourceIdList || 0 == resourceIdList.size()) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }

        // 删除resource
        resourceDao.deleteResource(resourceIdList);
        // 删除resource_user
        resourceDao.deleteUserResource(resourceIdList);
        // 查询删除操作后，用户是否存在默认视频，如果不存在（手动删除的是默认视频），则新增一个新的默认视频
        List<ResourceData> resourceList = resourceDao.findUserResourceByUser(actionInfo.getUserId());
        if (null != resourceList && resourceList.size() > 0) {
            boolean isExistDefaultResource = false;
            for (ResourceData resource : resourceList) {
            }
        }
        rspInfo.initSuccess(actionInfo.getActionId());
        return rspInfo;
    }
}

