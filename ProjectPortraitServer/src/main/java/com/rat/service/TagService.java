package com.rat.service;

import com.rat.dao.TagDao;
import com.rat.entity.local.Tag;
import com.rat.entity.network.request.TagFindByTypeActionInfo;
import com.rat.entity.network.response.TagFindByTypeRspInfo;
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
    @Resource
    private TagDao tagDao;

    public TagService() {
    }


    public TagFindByTypeRspInfo findByType(TagFindByTypeActionInfo actionInfo) {
        List<Tag> tagList = tagDao.findByType(actionInfo.getType());
        TagFindByTypeRspInfo rspInfo = new TagFindByTypeRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setTagList(tagList);
        return rspInfo;
    }
}

