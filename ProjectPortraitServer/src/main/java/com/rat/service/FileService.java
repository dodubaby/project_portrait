package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.dao.TagDao;
import com.rat.entity.local.File;
import com.rat.entity.local.ParentChild;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.FileFindAllActionInfo;
import com.rat.entity.network.request.FileFindBySuffixOrderByLineCountActionInfo;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.DataPageUtil;
import com.rat.utils.StringUtil;
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
    @Resource
    private TagDao tagDao;

    public FileService() {
    }

    public FileFindAllRspInfo findAll(FileFindAllActionInfo actionInfo) {
        // 获取文件列表
        List<File> fileList = fileDao.findAll(actionInfo.getSuffix(), actionInfo.getRootKey());

        // 过滤掉不包含指定tag的文件
        String tags = actionInfo.getTags();
        tags = StringUtil.isNullOrBlank(tags) ? "" : tags;
        String[] tagArray = tags.split(",");
        if (tagArray.length > 0) {
            StringBuffer tagIds = new StringBuffer();
            for (String tagValue : tagArray) {
                Long tagId = tagDao.findIdByValue(tagValue);
                tagIds.append(tagId);
                tagIds.append(",");
            }
        }
        // 获取文件列表（带层级，只有Java文件）
        ParentChild root = new ParentChild("root");
        String rootKey = actionInfo.getRootKey();// 查询的起始根节点
        for (File file : fileList) {
            String str = file.getClassFullName();
            if (StringUtil.isNullOrBlank(str)) {
                continue;
            }
            if (StringUtil.isNotBlank(rootKey)) {  // 起始根节点存在，展示起始根节点后面的内容
                if (!str.contains(rootKey)) {
                    continue;
                }
                str = str.substring(str.indexOf(rootKey));
            }
            String[] strArray = str.split("\\.");
            root = addChildList(root, strArray);
        }
        FileFindAllRspInfo rspInfo = new FileFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setFileList(fileList);
        rspInfo.setFileListWithHierarchy(root);
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

