package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.dao.ReferenceDao;
import com.rat.dao.ResourceDao;
import com.rat.dao.TargetDataDao;
import com.rat.entity.enums.ReferenceStatus;
import com.rat.entity.enums.ResponseType;
import com.rat.entity.local.File;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.UserDetail;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.FileFindBySuffixOrderByLineCountActionInfo;
import com.rat.entity.network.request.UserFindDetailActionInfo;
import com.rat.entity.network.request.UserUpdateActionInfo;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.entity.network.response.UserFindDetailRspInfo;
import com.rat.entity.network.response.UserUpdateRspInfo;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class FileService {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);
    @Resource
    private FileDao fileDao;
    @Resource
    private ResourceDao resourceDao;
    @Resource
    private ReferenceDao referenceDao;
    @Resource
    private TargetDataDao targetDataDao;

    public FileService() {
    }

    public FileFindAllRspInfo findAll(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<File> fileList = fileDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(fileList.size()));
        return rspInfo;
    }

    public FileFindAllRspInfo findAllBySuffixOrderByLineCount(FileFindBySuffixOrderByLineCountActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<File> fileList = fileDao.findBySuffixOrderByLineCount(actionInfo.getSuffix(), dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(fileList.size()));
        return rspInfo;
    }


    public UserUpdateRspInfo update(UserUpdateActionInfo actionInfo) {
        UserUpdateRspInfo rspInfo = new UserUpdateRspInfo();
        File file = actionInfo.getFile();
        if (null == file) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }

        fileDao.update(actionInfo.getFile());

        rspInfo.initSuccess(actionInfo.getActionId());
        return rspInfo;
    }

    public UserFindDetailRspInfo findDetail(UserFindDetailActionInfo actionInfo) {
        UserFindDetailRspInfo rspInfo = new UserFindDetailRspInfo();
        UserDetail userDetail = new UserDetail();
        File file = fileDao.findById(actionInfo.getUserId());
        if (null == file) {
            rspInfo.init(actionInfo.getActionId(), ResponseType.ERROR_4_USER_IS_NOT_EXIST);
            return rspInfo;
        }
        // 綁定用户
        userDetail.setFile(file);
        // 绑定视频列表
        List<ResourceData> resourceList = resourceDao.findByUser(file.getId());
        userDetail.setResourceDataList(resourceList);
        // 绑定关注状态(当前登录的用户与待查询的用户的关注关系)
        if (0 == actionInfo.getCurrentLoginUserId()) {
            userDetail.setReferenceStatus(ReferenceStatus.NONE.getCode());// 未登录，互相不关注
        } else {
            int count1 = referenceDao.findCountByUserAndReferenceedUser(actionInfo.getUserId(), actionInfo.getCurrentLoginUserId());
            int count2 = referenceDao.findCountByUserAndReferenceedUser(actionInfo.getCurrentLoginUserId(), actionInfo.getUserId());
            if (count1 > 0 && count2 > 0) {// 互相关注
                userDetail.setReferenceStatus(ReferenceStatus.EACH_OTHER.getCode());
            } else {
                if (count1 > 0) {
                    userDetail.setReferenceStatus(ReferenceStatus.FOLLOW_LOGIN_USER.getCode());
                } else if (count2 > 0) {
                    userDetail.setReferenceStatus(ReferenceStatus.FOLLOWED_BY_LOGIN_USER.getCode());
                } else {
                    userDetail.setReferenceStatus(ReferenceStatus.NONE.getCode()); // 互相不关注
                }
            }
        }
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetail(userDetail);
        return rspInfo;
    }
}

