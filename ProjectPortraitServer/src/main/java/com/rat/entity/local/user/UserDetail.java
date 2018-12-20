package com.rat.entity.local.user;

import com.rat.entity.local.video.Video;

import java.util.List;

/**
 * 短视频和用户实体类
 *
 * @author shisheng.zhao
 * @date 2017-03-31 17:56
 */
public class UserDetail {
    private User user;
    private Video defultVideo;// 默认视频
    private List<Video> videoList; // 用户视频列表
    private List<User> followUserList; // 用户关注列表
    private int followStatus; // 用户关注状态 FollowStatus

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getDefultVideo() {
        return defultVideo;
    }

    public void setDefultVideo(Video defultVideo) {
        this.defultVideo = defultVideo;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public List<User> getFollowUserList() {
        return followUserList;
    }

    public void setFollowUserList(List<User> followUserList) {
        this.followUserList = followUserList;
    }

    public int getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(int followStatus) {
        this.followStatus = followStatus;
    }
}
