package com.rat.service;

import com.rat.dao.TagDao;
import com.rat.entity.local.Tag;
import com.rat.entity.network.request.TagFindByTypeActionInfo;
import com.rat.entity.network.response.TagFindByTypeRspInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签服务
 *
 * @author L.jinzhu 2017/3/30
 */
@Service
public class TagService {

    private static Logger logger = LoggerFactory.getLogger(TagService.class);
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

