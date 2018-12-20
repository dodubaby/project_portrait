package com.rat.service;

import com.rat.dao.FollowDao;
import com.rat.dao.UserDao;
import com.rat.dao.VideoDao;
import com.rat.entity.local.user.User;
import com.rat.entity.local.user.UserDetail;
import com.rat.entity.local.video.Video;
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
    private UserDao userDao;
    @Resource
    private FollowDao followDao;
    @Resource
    private VideoDao videoDao;

    public NewsService() {
    }

    public NewsFindAllRspInfo findAll(NewsFindAllActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<UserDetail> userDetailList = new ArrayList<>();
        // 获取我关注的人
        List<User> userList = followDao.findByUserId(actionInfo.getUserId());
        // 我关注的人为空，则获取默认视频
        if (null == userList || 0 == userList.size()) {
            return findDefault(actionInfo);
        }
        // 增加自己
        User my = userDao.findById(actionInfo.getUserId());
        userList.add(my);
        // 放在map中，后续使用
        Map<Long, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getUserId(), user);
        }
        // 获取所有人的所有视频
        List<Video> videoList = videoDao.findAllByUserList(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd(), userList);
        // 所有人的所有视频为空，则获取默认视频
        if (null == videoList || 0 == videoList.size()) {
            return findDefault(actionInfo);
        }
        // 拼接视频和人物
        for (Video video : videoList) {
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(userMap.get(video.getUserId()));
            userDetail.setDefultVideo(video);
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
    private NewsFindAllRspInfo findDefault(NewsFindAllActionInfo actionInfo) {
        User my = userDao.findById(actionInfo.getUserId());
        String sex = (null == my) ? "1" : my.getSex();
        List<UserDetail> userDetailList = new ArrayList<>();
        List<Video> videoList = videoDao.findCount5BySex(sex);
        for (Video video : videoList) {
            UserDetail userDetail = new UserDetail();
            userDetail.setUser(userDao.findById(video.getUserId()));
            userDetail.setDefultVideo(video);
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

