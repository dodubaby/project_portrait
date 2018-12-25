package com.rat.entity.network.response;

import com.rat.entity.local.File;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class ReferenceFindAllRspInfo extends ResponseInfo {
    List<File> fileList;

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        return "ReferenceFindAllRspInfo{" +
                "actionId=" + actionId +
                ", statusCode=" + statusCode +
                ", statusMsg='" + statusMsg + '\'' +
                ", fileList=" + fileList +
                '}';
    }
}