package com.rat.service;

import com.rat.dao.RuleDao;
import com.rat.dao.RuleDataDao;
import com.rat.entity.local.Rule;
import com.rat.entity.local.RuleData;
import com.rat.entity.network.request.RuleDataFindAllActionInfo;
import com.rat.entity.network.response.RuleDataFindAllRspInfo;
import com.rat.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则对应数据服务
 *
 * @author L.jinzhu 2018/3/30
 */
@Service
public class RuleDataService extends BaseService {
    @Resource
    private RuleDao ruleDao;
    @Resource
    private RuleDataDao ruleDataDao;

    public RuleDataService() {
    }

    public RuleDataFindAllRspInfo findAll(RuleDataFindAllActionInfo actionInfo) {
        // 获取规则
        List<Rule> ruleList = ruleDao.findAll(actionInfo.getRuleType());
        // 获取规则对应数据
        if (!CollectionUtils.isEmpty(ruleList)) {
            for (Rule rule : ruleList) {
                List<RuleData> ruleDataList = ruleDataDao.findAll(actionInfo.getRuleDataStatus(), rule.getId());
                rule.setRuleDataList(ruleDataList);
                rule.setRuleDataListSize(null == ruleDataList ? 0 : ruleDataList.size());
            }
        }
        RuleDataFindAllRspInfo rspInfo = new RuleDataFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setRuleList(ruleList);
        return rspInfo;
    }
}