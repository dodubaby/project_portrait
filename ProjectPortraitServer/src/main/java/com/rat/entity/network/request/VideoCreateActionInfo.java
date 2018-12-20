package com.rat.entity.network.request;


import com.rat.entity.local.video.Video;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class VideoCreateActionInfo extends ActionInfo {
    private Video video;

    public VideoCreateActionInfo(int actionId, Video video) {
        super(actionId);
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}