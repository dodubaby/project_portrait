package com.rat.service;

import com.rat.common.Constant;
import com.rat.dao.FileDao;
import com.rat.dao.TagDao;
import com.rat.dao.TagDataDao;
import com.rat.entity.local.File;
import com.rat.entity.local.ParentChild;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.FileFindAllActionInfo;
import com.rat.entity.network.request.FileFindBySuffixOrderByLineCountActionInfo;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.DataCheckUtil;
import com.rat.utils.DataPageUtil;
import com.rat.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private TagDataDao tagDataDao;

    public FileService() {
    }

    public FileFindAllRspInfo findAll(FileFindAllActionInfo actionInfo) {
        // 获取"文件"列表结构
        List<File> tempFileList = fileDao.findAll(actionInfo.getSuffix(), actionInfo.getRootKey());
        // 根据tags过滤文件列表
        List<File> fileList = filteFileListByTagList(tempFileList, actionInfo.getTags());
        // 获取"文件"层级结构
        ParentChild root = new ParentChild(Constant.DATA_ROOT, Constant.DATA_ROOT);
        String rootKey = actionInfo.getRootKey();// 查询的起始根节点
        for (File file : fileList) {
            String fullNameStr = file.getFullName();
            fullNameStr = DataCheckUtil.clearIgnoreData(fullNameStr);
            if (StringUtil.isNotBlank(rootKey)) {  // 起始根节点存在，展示起始根节点后面的内容
                if (!fullNameStr.contains(rootKey)) {
                    continue;
                }
                fullNameStr = fullNameStr.substring(fullNameStr.indexOf(rootKey));
            }
            String[] fullNameArr = fullNameStr.split("/");
            root = addChildList(root, fullNameArr, String.valueOf(file.getId()), file.getType());
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

    /**
     * 处理层级结构
     *
     * @param root
     * @param strArray
     * @param lastItemId 因为整个结构，是用路径逐级拆分出来的，所以只给末节点添加id
     * @return
     */
    public static ParentChild addChildList(ParentChild root, String[] strArray, String lastItemId, String itemType) {
        ParentChild current = root;
        for (int i = 0; i < strArray.length; i++) {
            ParentChild newChild = new ParentChild(strArray[i], itemType);
            // 因为整个结构，是用路径逐级拆分出来的，所以只给末节点添加id
            if (i == strArray.length - 1) {
                newChild.setDataId(lastItemId);
            }
            current = current.addChild(newChild);
        }
        return root;
    }

    /**
     * 根据tagList过滤文件列表
     */
    private List<File> filteFileListByTagList(List<File> fileList, String tags) {
        if (CollectionUtils.isEmpty(fileList)) {
            return fileList;
        }
        // tags内容为空，不做处理
        if (StringUtil.isNullOrBlank(tags)) {
            return fileList;
        }
        if (StringUtil.isNullOrBlank(tags.replace(",", ""))) {
            return fileList;
        }
        // 逐步处理Tag各种类型
        fileList = filteFileListByTagType(TagService.TAG_OWNER, TagService.TAG_OWNER_IGNORE, TagService.TAG_OWNER_NO, fileList, tags);
        fileList = filteFileListByTagType(TagService.TAG_FUNCTION, TagService.TAG_FUNCTION_IGNORE, TagService.TAG_FUNCTION_NO, fileList, tags);
        fileList = filteFileListByTagType(TagService.TAG_COMMON, TagService.TAG_COMMON_IGNORE, TagService.TAG_COMMON_NO, fileList, tags);
        fileList = filteFileListByTagType(TagService.TAG_OTHER, TagService.TAG_OTHER_IGNORE, TagService.TAG_OTHER_NO, fileList, tags);
        return fileList;
    }

    /**
     * 根据tag类型过滤文件列表
     */
    private List<File> filteFileListByTagType(String tagType, String tagTypeIgnore, String tagTypeNo, List<File> tempFileList, String tags) {
        if (CollectionUtils.isEmpty(tempFileList)) {
            return tempFileList;
        }
        // 包含此类tag的"忽略标识"，不作处理
        if (tags.contains(tagTypeIgnore)) {
            return tempFileList;
        }
        // 包含此类tag的"无tag标识"，保留不含此类tag的文件
        if (tags.contains(tagTypeNo)) {
            List<Long> dataIdList = tagDataDao.findDataIdListByTagType(tagType, "file");
            ArrayList<File> delList = new ArrayList<>();
            for (File file : tempFileList) {
                if (dataIdList.contains(file.getId())) {
                    delList.add(file);
                }
            }
            tempFileList.removeAll(delList);
            return tempFileList;
        }
        // 未选择此类tag的"任何标识"，不作处理
        String tagsForSql = tags.replaceAll(",", "','");
        tagsForSql = "'" + tagsForSql + "'";
        int tagCount = tagDao.findCountByTagValues(tagType, tagsForSql);
        if (tagCount == 0) {
            return tempFileList;
        }
        // 选择此类tag的"具体标识"，保留含有具体tag的文件
        List<Long> dataIdList = tagDataDao.findDataIdListByTags(tagType, tagsForSql, "file");
        ArrayList<File> delList = new ArrayList<>();
        for (File file : tempFileList) {
            if (!dataIdList.contains(file.getId())) {
                delList.add(file);
            }
        }
        tempFileList.removeAll(delList);
        return tempFileList;
    }
}