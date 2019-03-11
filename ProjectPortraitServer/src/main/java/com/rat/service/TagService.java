package com.rat.service;

import com.rat.dao.TagDao;
import com.rat.dao.TagDataDao;
import com.rat.entity.local.Tag;
import com.rat.entity.network.request.TagFindByTypeActionInfo;
import com.rat.entity.network.request.TagInsertDeleteActionInfo;
import com.rat.entity.network.request.base.ActionInfo;
import com.rat.entity.network.response.TagFindAllRspInfo;
import com.rat.entity.network.response.TagFindByTypeRspInfo;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class TagService extends BaseService {
    // 用于筛选的tag条目
    public static final String TAG_OWNER = "owner";
    public static final String TAG_OWNER_NO = "[无Owner]";
    public static final String TAG_OWNER_IGNORE = "[忽略Owner]";
    public static final String TAG_FUNCTION = "function";
    public static final String TAG_FUNCTION_NO = "[无Function]";
    public static final String TAG_FUNCTION_IGNORE = "[忽略Function]";
    public static final String TAG_COMMON = "common";
    public static final String TAG_COMMON_NO = "[无Common]";
    public static final String TAG_COMMON_IGNORE = "[忽略Common]";
    public static final String TAG_OTHER = "other";
    public static final String TAG_OTHER_NO = "[无Other]";
    public static final String TAG_OTHER_IGNORE = "[忽略Other]";

    @Resource
    private TagDao tagDao;
    @Resource
    private TagDataDao tagDataDao;
    public TagService() {
    }

    public TagFindAllRspInfo findAll(ActionInfo actionInfo) {
        List<Tag> tagList4Owner = tagDao.findByType(TAG_OWNER);
        List<Tag> tagList4Function = tagDao.findByType(TAG_FUNCTION);
        List<Tag> tagList4Common = tagDao.findByType(TAG_COMMON);
        List<Tag> tagList4Other = tagDao.findByType(TAG_OTHER);

        tagList4Owner.add(0, new Tag(TAG_OWNER_NO));
        tagList4Owner.add(0, new Tag(TAG_OWNER_IGNORE));
        tagList4Function.add(0, new Tag(TAG_FUNCTION_NO));
        tagList4Function.add(0, new Tag(TAG_FUNCTION_IGNORE));
        tagList4Common.add(0, new Tag(TAG_COMMON_NO));
        tagList4Common.add(0, new Tag(TAG_COMMON_IGNORE));
        tagList4Other.add(0, new Tag(TAG_OTHER_NO));
        tagList4Other.add(0, new Tag(TAG_OTHER_IGNORE));

        TagFindAllRspInfo rspInfo = new TagFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTagList4Owner(tagList4Owner);
        rspInfo.setTagList4Function(tagList4Function);
        rspInfo.setTagList4Common(tagList4Common);
        rspInfo.setTagList4Other(tagList4Other);
        return rspInfo;
    }

    public TagFindByTypeRspInfo findByType(TagFindByTypeActionInfo actionInfo) {
        List<Tag> tagList = tagDao.findByType(actionInfo.getType());
        TagFindByTypeRspInfo rspInfo = new TagFindByTypeRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTagList(tagList);
        return rspInfo;
    }

    /**
     * 新增标签
     * @param actionInfo
     * @return
     */
    public ResponseInfo insertTag(TagInsertDeleteActionInfo actionInfo) {
        tagDao.insertTag(actionInfo.getType(), actionInfo.getValue());
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.initSuccess(actionInfo.getActionId());
        return responseInfo;
    }

    /**
     * 根据value删除标签
     * @param actionInfo
     * @return
     */
    public ResponseInfo deleteTagByValue(TagInsertDeleteActionInfo actionInfo) {
        String type = actionInfo.getType();
        ResponseInfo responseInfo = new ResponseInfo();
        if (TAG_OWNER.equals(type)) {
            responseInfo.initError4System(actionInfo.getActionId());
            return responseInfo;
        }
        Long tagId = tagDao.findIdByValue(actionInfo.getValue());
        tagDataDao.deleteTagDataByTagId(tagId);
        tagDao.deleteTagByValue(actionInfo.getType(), actionInfo.getValue());
        responseInfo.initSuccess(actionInfo.getActionId());
        return responseInfo;
    }

}

