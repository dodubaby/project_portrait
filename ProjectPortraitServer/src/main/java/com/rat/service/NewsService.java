package com.rat.service;

import com.rat.dao.ReferenceDao;
import com.rat.dao.FileDao;
import com.rat.dao.ResourceDao;
import com.rat.entity.local.File;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.UserDetail;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.*;
import com.rat.entity.network.response.*;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class NewsService {

    private static Logger logger = LoggerFactory.getLogger(NewsService.class);
    @Resource
    private FileDao fileDao;
    @Resource
    private ReferenceDao referenceDao;
    @Resource
    private ResourceDao resourceDao;

    public NewsService() {
    }

    public NewsFindAllRspInfo findAll(FileFindAllActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<UserDetail> userDetailList = new ArrayList<>();
        // 获取我关注的人
        List<File> fileList = referenceDao.findByUserId(null);
        // 我关注的人为空，则获取默认视频
        if (null == fileList || 0 == fileList.size()) {
            return findDefault(actionInfo);
        }
        // 增加自己
        File my = fileDao.findById(null);
        fileList.add(my);
        // 放在map中，后续使用
        Map<Long, File> userMap = new HashMap<>();
        for (File file : fileList) {
            userMap.put(file.getId(), file);
        }
        // 获取所有人的所有视频
        List<ResourceData> resourceList = resourceDao.findAllByUserList(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd(), fileList);
        // 所有人的所有视频为空，则获取默认视频
        if (null == resourceList || 0 == resourceList.size()) {
            return findDefault(actionInfo);
        }
        // 拼接视频和人物
        for (ResourceData resource : resourceList) {
            UserDetail userDetail = new UserDetail();
            userDetail.setFile(userMap.get(resource.getUserId()));
            userDetail.setDefultResourceData(resource);
            userDetailList.add(userDetail);
        }

        NewsFindAllRspInfo rspInfo = new NewsFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetailList(userDetailList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(userDetailList.size()));
        return rspInfo;
    }

    /**
     * 获取视频库最新的五条视频
     *
     * @param actionInfo
     * @return
     */
    private NewsFindAllRspInfo findDefault(FileFindAllActionInfo actionInfo) {
        File my = fileDao.findById(null);
        String sex = (null == my) ? "1" : my.getFullName();
        List<UserDetail> userDetailList = new ArrayList<>();
        List<ResourceData> resourceList = resourceDao.findCount5BySex(sex);
        for (ResourceData resource : resourceList) {
            UserDetail userDetail = new UserDetail();
            userDetail.setFile(fileDao.findById(resource.getUserId()));
            userDetail.setDefultResourceData(resource);
            userDetailList.add(userDetail);
        }
        NewsFindAllRspInfo rspInfo = new NewsFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetailList(userDetailList);
        rspInfo.setCurrentPage(1);
        rspInfo.setIsEndPage(true);
        return rspInfo;
    }
}

