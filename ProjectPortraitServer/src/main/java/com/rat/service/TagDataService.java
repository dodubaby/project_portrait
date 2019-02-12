package com.rat.service;

import com.rat.dao.TagDao;
import com.rat.dao.TagDataDao;
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

    public TagDataService() {
    }

    public TagDataFindByDataIdRspInfo findByDataId(TagDataFindByDataIdActionInfo actionInfo) {
        List<Tag> tagList4Owner = tagDao.findByType("owner");
        List<Tag> tagList4Function = tagDao.findByType("function");
        List<Tag> tagList4Other = tagDao.findByType("other");
        List<String> tagValueList4Owner = tagDataDao.findTagListByParam("owner", actionInfo.getDataType(), actionInfo.getDataId());
        List<String> tagValueList4Function = tagDataDao.findTagListByParam("function", actionInfo.getDataType(), actionInfo.getDataId());
        List<String> tagValueList4Other = tagDataDao.findTagListByParam("other", actionInfo.getDataType(), actionInfo.getDataId());

        TagDataFindByDataIdRspInfo rspInfo = new TagDataFindByDataIdRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTagList4Owner(tagList4Owner);
        rspInfo.setTagList4Function(tagList4Function);
        rspInfo.setTagList4Other(tagList4Other);
        rspInfo.setTagValueList4Owner(tagValueList4Owner);
        rspInfo.setTagValueList4Function(tagValueList4Function);
        rspInfo.setTagValueList4Other(tagValueList4Other);

        return rspInfo;
    }

    public ResponseInfo updateTagList(TagDataUpdateTagsActionInfo actionInfo) {
        // 删除旧tag
        tagDataDao.deleteTagsByDataId(actionInfo.getDataType(), actionInfo.getDataId());
        // 新增新tag
        String[] tagArray = actionInfo.getTagArray();
        if (null != tagArray && tagArray.length > 0) {
            for (String tagValue : tagArray) {
                if (StringUtil.isNullOrBlank(tagValue)) continue;
                Long tagId = tagDao.findIdByValue(tagValue);
                tagDataDao.create(actionInfo.getDataType(), actionInfo.getDataId(), tagId);
            }
        }
        ResponseInfo rspInfo = new ResponseInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        return rspInfo;
    }
}
