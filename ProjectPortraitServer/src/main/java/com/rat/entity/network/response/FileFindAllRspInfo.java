package com.rat.entity.network.response;

import com.rat.entity.local.File;
import com.rat.entity.local.ParentChild;
import com.rat.entity.network.response.base.ResponseInfoWithPageData;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class FileFindAllRspInfo extends ResponseInfoWithPageData {
    private List<File> fileList;// 文件列表
    private ParentChild fileListWithHierarchy;// 文件列表（带层级）

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public ParentChild getFileListWithHierarchy() {
        return fileListWithHierarchy;
    }

    public void setFileListWithHierarchy(ParentChild fileListWithHierarchy) {
        this.fileListWithHierarchy = fileListWithHierarchy;
    }
}