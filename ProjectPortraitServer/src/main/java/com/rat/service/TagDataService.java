package com.rat.service;

import com.rat.common.Constant;
import com.rat.dao.FileDao;
import com.rat.dao.TagDao;
import com.rat.dao.TagDataDao;
import com.rat.entity.local.File;
import com.rat.entity.local.Tag;
import com.rat.entity.network.request.TagDataFindByDataIdActionInfo;
import com.rat.entity.network.request.TagDataUpdateTagsActionInfo;
import com.rat.entity.network.response.TagDataFindByDataIdRspInfo;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签对应数据服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class TagDataService extends BaseService {
    @Resource
    private TagDao tagDao;
    @Resource
    private TagDataDao tagDataDao;
    @Resource
    private FileDao fileDao;

    public TagDataService() {
    }

    public TagDataFindByDataIdRspInfo findByDataId(TagDataFindByDataIdActionInfo actionInfo) {
        List<Tag> tagList4Owner = tagDao.findByType(TagService.TAG_OWNER);
        List<Tag> tagList4Function = tagDao.findByType(TagService.TAG_FUNCTION);
        List<Tag> tagList4Common = tagDao.findByType(TagService.TAG_COMMON);
        List<Tag> tagList4Other = tagDao.findByType(TagService.TAG_OTHER);
        List<String> tagValueList4Owner = tagDataDao.findTagListByParam(TagService.TAG_OWNER, actionInfo.getDataType(), actionInfo.getDataId());
        List<String> tagValueList4Function = tagDataDao.findTagListByParam(TagService.TAG_FUNCTION, actionInfo.getDataType(), actionInfo.getDataId());
        List<String> tagValueList4Common = tagDataDao.findTagListByParam(TagService.TAG_COMMON, actionInfo.getDataType(), actionInfo.getDataId());
        List<String> tagValueList4Other = tagDataDao.findTagListByParam(TagService.TAG_OTHER, actionInfo.getDataType(), actionInfo.getDataId());

        TagDataFindByDataIdRspInfo rspInfo = new TagDataFindByDataIdRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTagList4Owner(tagList4Owner);
        rspInfo.setTagList4Function(tagList4Function);
        rspInfo.setTagList4Common(tagList4Common);
        rspInfo.setTagList4Other(tagList4Other);
        rspInfo.setTagValueList4Owner(tagValueList4Owner);
        rspInfo.setTagValueList4Function(tagValueList4Function);
        rspInfo.setTagValueList4Common(tagValueList4Common);
        rspInfo.setTagValueList4Other(tagValueList4Other);

        return rspInfo;
    }

    public ResponseInfo updateTagList(TagDataUpdateTagsActionInfo actionInfo) {
        long dataId = actionInfo.getDataId();
        String dataType = actionInfo.getDataType();
        String[] tagArray = actionInfo.getTagArray();
        if (null == tagArray) {
            tagArray = new String[0];
        }
        // 处理自身TagData:删除旧的
        tagDataDao.deleteTagsByDataId(dataType, dataId);
        // 处理自身TagData:新增新的
        for (String tagValue : tagArray) {
            if (StringUtil.isNullOrBlank(tagValue)) continue;
            Long tagId = tagDao.findIdByValue(tagValue);
            //直接新增tagData
            tagDataDao.create(dataType, dataId, tagId, Constant.DATA_CREATE_TYPE_MANUAL);
        }
        // 处理子文件、文件夹TagData:删除旧的
        if (Constant.DIR_TYPE.equals(dataType)) {
            //获取文件夹全路径
            String fileFullName = fileDao.findFullNameById(dataId);
            //获取所有子文件
            List<File> files = fileDao.findIdsByFullName(fileFullName);
            for (File file : files) {
                //删除(自动创建的tag)
                tagDataDao.deleteTagsByFileId(file.getId(), Constant.DATA_CREATE_TYPE_AUTO);
                for (String tagValue : tagArray) {
                    if (StringUtil.isNullOrBlank(tagValue)) continue;
                    Long tagId = tagDao.findIdByValue(tagValue);
                    //添加(自动创建的tag)
                    tagDataDao.create(file.getType(), file.getId(), tagId, Constant.DATA_CREATE_TYPE_AUTO);
                }
            }
        }

        // 返回请求
        ResponseInfo rspInfo = new ResponseInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        return rspInfo;
    }
}