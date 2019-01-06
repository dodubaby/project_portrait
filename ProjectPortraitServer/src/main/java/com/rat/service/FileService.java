package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.dao.ReferenceDao;
import com.rat.dao.ResourceDao;
import com.rat.dao.TargetDataDao;
import com.rat.entity.enums.ReferenceStatus;
import com.rat.entity.enums.ResponseType;
import com.rat.entity.local.File;
import com.rat.entity.local.ParentChild;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.UserDetail;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.FileFindBySuffixOrderByLineCountActionInfo;
import com.rat.entity.network.request.UserFindDetailActionInfo;
import com.rat.entity.network.request.UserUpdateActionInfo;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.entity.network.response.UserFindDetailRspInfo;
import com.rat.entity.network.response.UserUpdateRspInfo;
import com.rat.utils.DataPageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件服务
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

    public FileFindAllRspInfo findAll(ActionInfoWithPageData actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        // 获取文件列表
        List<File> fileList = fileDao.findAll(dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        // 获取文件列表（带层级）
        ParentChild root = new ParentChild("root");
        for (File file : fileList) {
            String str = file.getClassFullName();
            String[] strArray = str.split("\\.");
            root = FileService.addChildList(root, strArray);
        }
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setFileListWithHierarchy(root);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(fileList.size()));
        return rspInfo;
    }

    public FileFindAllRspInfo findAllBySuffixOrderByLineCount(FileFindBySuffixOrderByLineCountActionInfo actionInfo) {
        DataPage dataPage = DataPageUtil.getPage(actionInfo.getPageNumber(), actionInfo.getDataGetType());
        List<File> fileList = fileDao.findBySuffixOrderByLineCount(actionInfo.getSuffix(), actionInfo.getMaxLineCount(), dataPage.getDataIndexStart(), dataPage.getDataIndexEnd());
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setCurrentPage(dataPage.getCurrentPage());
        rspInfo.setIsEndPage(DataPageUtil.isEndPage(fileList.size()));
        return rspInfo;
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
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setUserDetail(userDetail);
        return rspInfo;
    }

    public static ParentChild addChildList(ParentChild root, String[] strArray) {
        ParentChild current = root;
        for (String newStr : strArray) {
            ParentChild newChild = new ParentChild(newStr);
            current = current.addChild(newChild);
        }
        return root;
    }
}

