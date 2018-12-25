package com.rat.entity.network.request;


import com.rat.entity.local.File;
import com.rat.entity.network.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class UserUpdateActionInfo extends ActionInfo {
    private File file;

    public UserUpdateActionInfo(int actionId, File file) {
        super(actionId);
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}