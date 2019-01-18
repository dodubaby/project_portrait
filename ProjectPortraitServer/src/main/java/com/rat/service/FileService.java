package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.entity.local.File;
import com.rat.entity.local.ParentChild;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.FileFindBySuffixOrderByLineCountActionInfo;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.DataPageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class FileService extends BaseService {
    @Resource
    private FileDao fileDao;

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


    public static ParentChild addChildList(ParentChild root, String[] strArray) {
        ParentChild current = root;
        for (String newStr : strArray) {
            ParentChild newChild = new ParentChild(newStr);
            current = current.addChild(newChild);
        }
        return root;
    }
}

