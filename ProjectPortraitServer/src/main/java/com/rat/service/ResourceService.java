package com.rat.service;

import com.rat.dao.ResourceDao;
import com.rat.entity.local.ResourceData;
import com.rat.entity.network.request.ResourceCreateActionInfo;
import com.rat.entity.network.request.ResourceDeleteActionInfo;
import com.rat.entity.network.response.ResourceCreateRspInfo;
import com.rat.entity.network.response.ResourceDeleteRspInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频服务
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

    public ResourceCreateRspInfo create(ResourceCreateActionInfo actionInfo) {
        ResourceCreateRspInfo rspInfo = new ResourceCreateRspInfo();
        ResourceData resource = actionInfo.getResourceData();
        if (null == resource) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }

        // 保存resource
        resourceDao.create(resource);
        // 更新之前的所有视频为非默认视频
        resourceDao.updateUserResourceSetAllNotDefault(resource.getUserId());
        // 保存user_resource,并设定为默认视频
        resourceDao.createUserResource(resource.getResourceId(), resource.getUserId(), "1");

        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setResourceData(resource);
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
                if ("1".equals(resource.getIsDefault())) {
                    isExistDefaultResource = true;
                    break;
                }
            }
            // 存在视频但不存在默认视频
            if (!isExistDefaultResource) {
                resourceDao.updateUserResourceSetDefault(actionInfo.getUserId(), resourceList.get(resourceList.size() - 1).getResourceId());
            }
        }
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setResourceIdList(resourceIdList);
        return rspInfo;
    }
}

