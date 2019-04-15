package com.rat.service;

import com.rat.dao.RuleDao;
import com.rat.dao.RuleDataDao;
import com.rat.entity.local.Rule;
import com.rat.entity.network.request.RuleDeleteActionInfo;
import com.rat.entity.network.request.RuleInsertActionInfo;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.SafeParseUtils;
import com.rat.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : he
 * date : 2019-03-04
 * introduce :
 */
@Service
public class RuleService extends BaseService {
    @Resource
    private RuleDao ruleDao;
    @Resource
    private RuleDataDao ruleDataDao;

    public ResponseInfo deleteRuleById(RuleDeleteActionInfo actionInfo) {
        ResponseInfo info = new ResponseInfo();
        int ruleId = SafeParseUtils.parseInt(actionInfo.getRuleId());
        ruleDataDao.deleteDataByRuleId(ruleId);
        ruleDao.deleteRuleById(ruleId);
        info.initSuccess(actionInfo.getActionId());
        return info;
    }

    public ResponseInfo insertRule(RuleInsertActionInfo actionInfo) {
        ResponseInfo info = new ResponseInfo();
        Rule rule = actionInfo.getRule();
        // 补充正则表达式
        StringBuffer reguar = new StringBuffer();
        String keyLeft = rule.getKeyLeft();
        String keyRight = rule.getKeyRight();
        if (StringUtil.isNullOrBlank(keyLeft) && StringUtil.isNullOrBlank(keyRight)) {
            info.initError4Param(actionInfo.getActionId());
            return info;
        }
        if (StringUtil.isNotBlank(keyLeft) && StringUtil.isNotBlank(keyRight)) {
            reguar.append("(?<=").append(keyLeft).append(").+?(?=").append(keyRight).append(")");
        } else if (StringUtil.isNotBlank(keyLeft)) {
            reguar.append("(?<=").append(keyLeft).append(").+");
        } else if (StringUtil.isNotBlank(keyRight)) {
            reguar.append(".+?(?=").append(keyRight).append(")");
        }
        rule.setRegular(reguar.toString());
        // 补充创建时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rule.setCreateTime(df.format(new Date()));
        ruleDao.insertRule(rule.getRegular(), rule.getScanFileSuffix(), rule.getKeyLeft(),
                rule.getKeyRight(), rule.getRuleGroup(), rule.getRemark(), rule.getCreater(), rule.getCreateTime());
        info.initSuccess(actionInfo.getActionId());
        return info;
    }
}
