package com.rat.service;

import com.rat.common.Constant;
import com.rat.dao.FollowDao;
import com.rat.dao.SecurityCodeDao;
import com.rat.dao.UserDao;
import com.rat.dao.VideoDao;
import com.rat.entity.enums.FollowStatus;
import com.rat.entity.enums.ResponseType;
import com.rat.entity.local.system.SecurityCode;
import com.rat.entity.local.user.User;
import com.rat.entity.local.user.UserDetail;
import com.rat.entity.local.video.Video;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.entity.User4QQ;
import com.rat.entity.network.entity.User4Wechat;
import com.rat.entity.network.request.UserFindAllActionInfo;
import com.rat.entity.network.request.UserFindDetailActionInfo;
import com.rat.entity.network.request.UserRegisterLoginActionInfo;
import com.rat.entity.network.request.UserUpdateActionInfo;
import com.rat.entity.network.response.UserFindAllRspInfo;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用户服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserDao userDao;
    @Resource
    private VideoDao videoDao;
    @Resource
    private FollowDao followDao;
    @Resource
    private SecurityCodeDao securityCodeDao;

    public UserService() {
    }

    public UserRegisterLoginRspInfo login(UserRegisterLoginActionInfo actionInfo) {
        UserRegisterLoginRspInfo rspInfo = new UserRegisterLoginRspInfo();
        if (0 == actionInfo.getAccountType()) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }
        // 查询是否已经存在用户
        User user = null;
        UserDetail userDetail = null;
        switch (actionInfo.getAccountType()) {
            case User.ACCOUNT_TYPE_PHONE:
                // 验证码校验失败
                SecurityCode securityCode = securityCodeDao.find(actionInfo.getPhoneNumber(), SecurityCode.CODE_TYPE_REGISTER_LOGIN, actionInfo.getCode());
                if (null == securityCode) {
                    rspInfo.initError(actionInfo.getActionId(), ResponseType.ERROR_4_SECRITY_CODE_ERROR);
                    return rspInfo;
                }
                // 验证码校验成功
                user = userDao.findByAccount(User.ACCOUNT_TYPE_PHONE, actionInfo.getPhoneNumber());
                // 清理同类型旧验证码
                securityCodeDao.delete(actionInfo.getPhoneNumber(), SecurityCode.CODE_TYPE_REGISTER_LOGIN);
                break;
            case User.ACCOUNT_TYPE_WECHAT:
                user = userDao.findByAccount(User.ACCOUNT_TYPE_WECHAT, actionInfo.getOpenId());
                break;
            case User.ACCOUNT_TYPE_QQ:
                user = userDao.findByAccount(User.ACCOUNT_TYPE_QQ, actionInfo.getOpenId());
                break;
        }

        // 存在用户，登录
        if (null != user) {
            userDetail = new UserDetail();
            // 绑定视频数、关注数、被关注数
            user.setVideoCount(videoDao.findUserVideoCount(user.getUserId()));
            user.setFollowCount(followDao.findCountByUserId(user.getUserId()));
            user.setFollowedCount(followDao.findCountByFollowedUserId(user.getUserId()));
            // 綁定用户
            userDetail.setUser(user);
            // 绑定视频列表
            List<Video> videoList = videoDao.findByUser(user.getUserId());
            userDetail.setVideoList(videoList);

            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setUserDetail(userDetail);
            return rspInfo;
        }
        // 不存在用户，注册
        else {
            user = new User();
            switch (actionInfo.getAccountType()) {
                case User.ACCOUNT_TYPE_PHONE:
                    user.setAccountType(User.ACCOUNT_TYPE_PHONE);
                    user.setAccount(actionInfo.getPhoneNumber());
                    String nickName = actionInfo.getPhoneNumber();
                    if (StringUtil.isNotBlank(nickName) && nickName.length() > 10) {
                        nickName = nickName.substring(0, 4) + "****" + nickName.substring(8, nickName.length());
                    }
                    user.setNickName(nickName);
                    break;
                case User.ACCOUNT_TYPE_WECHAT:
                    //  TODO by L.jinzhu  for 待解析
                    if (StringUtil.isNullOrBlank(actionInfo.getDataFromOtherPlatform())) {
                        rspInfo.initError4Param(actionInfo.getActionId());
                        return rspInfo;
                    }
                    User4Wechat user4Wechat = GsonUtil.fromJson(actionInfo.getDataFromOtherPlatform(), User4Wechat.class);
                    user.setAccountType(User.ACCOUNT_TYPE_WECHAT);
                    user.setAccount(actionInfo.getOpenId());
                    user.setNickName(user4Wechat.getNickName());
                    user.setHeadUrl(user4Wechat.getHeadUrl());
                    user.setSex(user4Wechat.getSex());
                    user.setCityName(user4Wechat.getCityName());
                    break;
                case User.ACCOUNT_TYPE_QQ:
                    if (StringUtil.isNullOrBlank(actionInfo.getDataFromOtherPlatform())) {
                        rspInfo.initError4Param(actionInfo.getActionId());
                        return rspInfo;
                    }
                    User4QQ user4QQ = GsonUtil.fromJson(actionInfo.getDataFromOtherPlatform(), User4QQ.class);
                    user.setAccountType(User.ACCOUNT_TYPE_QQ);
                    user.setAccount(actionInfo.getOpenId());
                    user.setNickName(user4QQ.getNickName());
                    user.setHeadUrl(user4QQ.getHeadUrl());
                    user.setSex(user4QQ.getSex());
                    user.setCityName(user4QQ.getCityName());
                    break;
            }
            user.setBigImg(Constant.userDefaultBigImage);// 设置默认背景大图
            userDao.create(user);

            // 获取融云token并更新数据库
            user.setHeadUrl(StringUtil.isNullOrBlank(user.getHeadUrl()) ? "temp.jpg" : user.getHeadUrl());

            rspInfo.initSuccess(actionInfo.getActionId());
            rspInfo.setUserDetail(userDetail);
            return rspInfo;
        }
    }

    public UserUpdateRspInfo update(UserUpdateActionInfo actionInfo) {
        UserUpdateRspInfo rspInfo = new UserUpdateRspInfo();
        User user = actionInfo.getUser();
        if (null == user) {
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }

        userDao.update(actionInfo.getUser());

        rspInfo.initSuccess(actionInfo.getActionId());
        return rspInfo;
    }

    public UserFindAllRspInfo findAll(UserFindAllActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<UserDetail> userDetailList = new ArrayList<>();
        List<User> userList = userDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd(), actionInfo.getAgeStart(), actionInfo.getAgeEnd(), actionInfo.getSex(), actionInfo.getCityCode());
        // 打乱顺序
        if (null != userList) {
            Collections.shuffle(userList);
        }
        // 拼接数据
        for (User user : userList) {
//            if (user.getUserId() == actionInfo.getUserId()) {
//                continue;// 用户自己不予展示
//            }
            UserDetail userDetail = new UserDetail();
            // 綁定用户
            userDetail.setUser(user);
            // 绑定默认视频
            Video defaultVideo = videoDao.findDefault(user.getUserId());
            if (null == defaultVideo) {
                continue;// 没有默认视频的用户不予展示
            }
            userDetail.setDefultVideo(defaultVideo);

            userDetailList.add(userDetail);
        }

        UserFindAllRspInfo rspInfo = new UserFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetailList(userDetailList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(userList.size()));
        return rspInfo;
    }

    public UserFindDetailRspInfo findDetail(UserFindDetailActionInfo actionInfo) {
        UserFindDetailRspInfo rspInfo = new UserFindDetailRspInfo();
        UserDetail userDetail = new UserDetail();
        User user = userDao.findById(actionInfo.getUserId());
        if (null == user) {
            rspInfo.initError(actionInfo.getActionId(), ResponseType.ERROR_4_USER_IS_NOT_EXIST);
            return rspInfo;
        }
        // 绑定视频数、关注数、被关注数
        user.setVideoCount(videoDao.findUserVideoCount(user.getUserId()));
        user.setFollowCount(followDao.findCountByUserId(user.getUserId()));
        user.setFollowedCount(followDao.findCountByFollowedUserId(user.getUserId()));
        // 綁定用户
        userDetail.setUser(user);
        // 绑定视频列表
        List<Video> videoList = videoDao.findByUser(user.getUserId());
        userDetail.setVideoList(videoList);
        // 绑定关注状态(当前登录的用户与待查询的用户的关注关系)
        if (0 == actionInfo.getCurrentLoginUserId()) {
            userDetail.setFollowStatus(FollowStatus.NONE.getCode());// 未登录，互相不关注
        } else {
            int count1 = followDao.findCountByUserAndFollowedUser(actionInfo.getUserId(), actionInfo.getCurrentLoginUserId());
            int count2 = followDao.findCountByUserAndFollowedUser(actionInfo.getCurrentLoginUserId(), actionInfo.getUserId());
            if (count1 > 0 && count2 > 0) {// 互相关注
                userDetail.setFollowStatus(FollowStatus.EACH_OTHER.getCode());
            } else {
                if (count1 > 0) {
                    userDetail.setFollowStatus(FollowStatus.FOLLOW_LOGIN_USER.getCode());
                } else if (count2 > 0) {
                    userDetail.setFollowStatus(FollowStatus.FOLLOWED_BY_LOGIN_USER.getCode());
                } else {
                    userDetail.setFollowStatus(FollowStatus.NONE.getCode()); // 互相不关注
                }
            }
        }
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetail(userDetail);
        return rspInfo;
    }
}

