package com.rat.service;

import com.rat.dao.FollowDao;
import com.rat.entity.local.user.Follow;
import com.rat.entity.local.user.User;
import com.rat.entity.network.request.FollowActionInfo;
import com.rat.entity.network.request.FollowFindAllActionInfo;
import com.rat.entity.network.response.FollowFindAllRspInfo;
import com.rat.entity.network.response.FollowRspInfo;
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
public class FollowService {

    private static Logger logger = LoggerFactory.getLogger(FollowService.class);
    @Resource
    private FollowDao followDao;

    public FollowService() {
    }

    /**
     * 关注、取消关注
     *
     * @param actionInfo
     * @return
     */
    public FollowRspInfo follow(FollowActionInfo actionInfo) {
        int type = actionInfo.getType();
        Follow follow = actionInfo.getFollow();
        if (null == follow) {
            FollowRspInfo rspInfo = new FollowRspInfo();
            rspInfo.initError4Param(actionInfo.getActionId());
            return rspInfo;
        }
        switch (type) {
            // 关注
            case FollowActionInfo.FOLLOW_TYPE_OK:
                followDao.create(follow.getUserId(), follow.getFollowUserId());
                break;
            // 取消关注
            case FollowActionInfo.FOLLOW_TYPE_CANCLE:
                followDao.delete(follow.getUserId(), follow.getFollowUserId());
                break;
            default:
                FollowRspInfo rspInfo = new FollowRspInfo();
                rspInfo.initError4Param(actionInfo.getActionId());
                return rspInfo;
        }
        FollowRspInfo rspInfo = new FollowRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());

        return rspInfo;
    }

    /**
     * 通过关注人查找
     *
     * @param actionInfo
     * @return
     */
    public FollowFindAllRspInfo findAllByUserId(FollowFindAllActionInfo actionInfo) {
        List<User> userList = followDao.findByUserId(actionInfo.getUserId());

        FollowFindAllRspInfo rspInfo = new FollowFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserList(userList);

        return rspInfo;
    }

    /**
     * 通过被关注人查找
     *
     * @param actionInfo
     * @return
     */
    public FollowFindAllRspInfo findAllByFollowedUserId(FollowFindAllActionInfo actionInfo) {
        List<User> userList = followDao.findByFollowedUserId(actionInfo.getUserId());

        FollowFindAllRspInfo rspInfo = new FollowFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserList(userList);

        return rspInfo;
    }
}

