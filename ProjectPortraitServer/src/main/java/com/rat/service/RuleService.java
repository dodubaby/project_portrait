package com.rat.service;

import com.rat.dao.RuleDao;
import com.rat.dao.RuleDataDao;
import com.rat.entity.local.Rule;
import com.rat.entity.network.request.RuleDeleteActionInfo;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public ResponseInfo deleteRuleByRegular(RuleDeleteActionInfo actionInfo) {
        ResponseInfo info = new ResponseInfo();
        int ruleId = ruleDao.selectIdByRegular(actionInfo.getRegular());
        ruleDataDao.deleteDataByRuleId(ruleId);
        ruleDao.deleteRuleByRegular(actionInfo.getRegular());
        info.initSuccess(actionInfo.getActionId());
        return info;
    }

    public ResponseInfo insertRule(Rule rule, int actionId) {
        ResponseInfo info = new ResponseInfo();
        ruleDao.insertRule(rule.getRegular(), rule.getScanFileSuffix(), rule.getKeyLeft(),
                rule.getKeyRight(), rule.getRuleGroup(), rule.getRemark(), rule.getCreater(), rule.getCreateTime());
        info.initSuccess(actionId);
        return info;
    }
}
