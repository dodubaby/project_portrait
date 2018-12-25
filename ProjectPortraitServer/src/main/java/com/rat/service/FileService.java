package com.rat.service;

import com.rat.common.Constant;
import com.rat.dao.FileDao;
import com.rat.dao.ReferenceDao;
import com.rat.dao.TargetDataDao;
import com.rat.dao.ResourceDao;
import com.rat.entity.enums.ReferenceStatus;
import com.rat.entity.enums.ResponseType;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.TargetaData;
import com.rat.entity.local.File;
import com.rat.entity.local.UserDetail;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.entity.User4QQ;
import com.rat.entity.network.entity.User4Wechat;
import com.rat.entity.network.request.FileFindAllActionInfo;
import com.rat.entity.network.request.UserFindDetailActionInfo;
import com.rat.entity.network.request.UserRegisterLoginActionInfo;
import com.rat.entity.network.request.UserUpdateActionInfo;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.entity.network.response.UserFindDetailRspInfo;
import com.rat.entity.network.response.UserRegisterLoginRspInfo;
import com.rat.entity.network.response.UserUpdateRspInfo;
import com.rat.utils.DataPageUtil;
import com.rat.utils.GsonUtil;
import com.rat.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务
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


    public FileFindAllRspInfo findAll(FileFindAllActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<File> fileList = fileDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(fileList.size()));
        return rspInfo;
    }

    public UserRegisterLoginRspInfo login(UserRegisterLoginActionInfo actionInfo) {
        UserRegisterLoginRspInfo rspInfo = new UserRegisterLoginRspInfo();
        if (0 == actionInfo.getAccountType()) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }
        // 查询是否已经存在用户
        File file = null;
        UserDetail userDetail = null;
        switch (actionInfo.getAccountType()) {
            case File.ACCOUNT_TYPE_PHONE:
                // 验证码校验失败
                TargetaData targetaData = targetDataDao.find(actionInfo.getPhoneNumber(), TargetaData.CODE_TYPE_REGISTER_LOGIN, actionInfo.getCode());
                if (null == targetaData) {
                    rspInfo.initError(actionInfo.getActionId(), ResponseType.ERROR_4_SECRITY_CODE_ERROR);
                    return rspInfo;
                }
                // 验证码校验成功
                file = fileDao.findByAccount(File.ACCOUNT_TYPE_PHONE, actionInfo.getPhoneNumber());
                // 清理同类型旧验证码
                targetDataDao.delete(actionInfo.getPhoneNumber(), TargetaData.CODE_TYPE_REGISTER_LOGIN);
                break;
            case File.ACCOUNT_TYPE_WECHAT:
                file = fileDao.findByAccount(File.ACCOUNT_TYPE_WECHAT, actionInfo.getOpenId());
                break;
            case File.ACCOUNT_TYPE_QQ:
                file = fileDao.findByAccount(File.ACCOUNT_TYPE_QQ, actionInfo.getOpenId());
                break;
        }

        // 存在用户，登录
        if (null != file) {
            userDetail = new UserDetail();
            // 绑定视频数、关注数、被关注数
            file.setResourceCount(resourceDao.findUserResourceCount(file.getUserId()));
            file.setReferenceCount(referenceDao.findCountByUserId(file.getUserId()));
            file.setReferenceedCount(referenceDao.findCountByReferenceedUserId(file.getUserId()));
            // 綁定用户
            userDetail.setFile(file);
            // 绑定视频列表
            List<ResourceData> resourceList = resourceDao.findByUser(file.getUserId());
            userDetail.setResourceDataList(resourceList);

            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setUserDetail(userDetail);
            return rspInfo;
        }
        // 不存在用户，注册
        else {
            file = new File();
            switch (actionInfo.getAccountType()) {
                case File.ACCOUNT_TYPE_PHONE:
                    file.setAccountType(File.ACCOUNT_TYPE_PHONE);
                    file.setAccount(actionInfo.getPhoneNumber());
                    String nickName = actionInfo.getPhoneNumber();
                    if (StringUtil.isNotBlank(nickName) && nickName.length() > 10) {
                        nickName = nickName.substring(0, 4) + "****" + nickName.substring(8, nickName.length());
                    }
                    file.setNickName(nickName);
                    break;
                case File.ACCOUNT_TYPE_WECHAT:
                    //  TODO by L.jinzhu  for 待解析
                    if (StringUtil.isNullOrBlank(actionInfo.getDataFromOtherPlatform())) {
                        rspInfo.initError4Param(actionInfo.getActionId());
                        return rspInfo;
                    }
                    User4Wechat user4Wechat = GsonUtil.fromJson(actionInfo.getDataFromOtherPlatform(), User4Wechat.class);
                    file.setAccountType(File.ACCOUNT_TYPE_WECHAT);
                    file.setAccount(actionInfo.getOpenId());
                    file.setNickName(user4Wechat.getNickName());
                    file.setHeadUrl(user4Wechat.getHeadUrl());
                    file.setSex(user4Wechat.getSex());
                    file.setCityName(user4Wechat.getCityName());
                    break;
                case File.ACCOUNT_TYPE_QQ:
                    if (StringUtil.isNullOrBlank(actionInfo.getDataFromOtherPlatform())) {
                        rspInfo.initError4Param(actionInfo.getActionId());
                        return rspInfo;
                    }
                    User4QQ user4QQ = GsonUtil.fromJson(actionInfo.getDataFromOtherPlatform(), User4QQ.class);
                    file.setAccountType(File.ACCOUNT_TYPE_QQ);
                    file.setAccount(actionInfo.getOpenId());
                    file.setNickName(user4QQ.getNickName());
                    file.setHeadUrl(user4QQ.getHeadUrl());
                    file.setSex(user4QQ.getSex());
                    file.setCityName(user4QQ.getCityName());
                    break;
            }
            file.setBigImg(Constant.userDefaultBigImage);// 设置默认背景大图
            fileDao.create(file);

            // 获取融云token并更新数据库
            file.setHeadUrl(StringUtil.isNullOrBlank(file.getHeadUrl()) ? "temp.jpg" : file.getHeadUrl());

            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setUserDetail(userDetail);
            return rspInfo;
        }
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
            rspInfo.initError(actionInfo.getActionId(), ResponseType.ERROR_4_USER_IS_NOT_EXIST);
            return rspInfo;
        }
        // 绑定视频数、关注数、被关注数
        file.setResourceCount(resourceDao.findUserResourceCount(file.getUserId()));
        file.setReferenceCount(referenceDao.findCountByUserId(file.getUserId()));
        file.setReferenceedCount(referenceDao.findCountByReferenceedUserId(file.getUserId()));
        // 綁定用户
        userDetail.setFile(file);
        // 绑定视频列表
        List<ResourceData> resourceList = resourceDao.findByUser(file.getUserId());
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

