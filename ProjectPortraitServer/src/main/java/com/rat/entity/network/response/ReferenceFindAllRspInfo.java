package com.rat.entity.network.response;

import com.rat.entity.local.File;
import com.rat.entity.local.Reference;
import com.rat.entity.network.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2018/12/11
 * introduce : 响应实体
 */
public class ReferenceFindAllRspInfo extends ResponseInfo {
    private File file;
    private List<Reference> referenceList;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Reference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<Reference> referenceList) {
        this.referenceList = referenceList;
    }
}