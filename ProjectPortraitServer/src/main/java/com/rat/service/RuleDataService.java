package com.rat.service;

import com.rat.dao.RuleDao;
import com.rat.dao.RuleDataDao;
import com.rat.entity.local.Rule;
import com.rat.entity.local.RuleData;
import com.rat.entity.network.entity.DataPage;
import com.rat.entity.network.request.RuleDataFindAllActionInfo;
import com.rat.entity.network.response.RuleDataFindAllRspInfo;
import com.rat.service.base.BaseService;
import com.rat.utils.DataPageUtil;
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
    private RuleDataDao ruleDataDao;
    @Resource
    private RuleDao ruleDao;

    public RuleDataService() {
    }

    public RuleDataFindAllRspInfo findAll(RuleDataFindAllActionInfo actionInfo) {
        // 获取规则
        List<Rule> ruleList = ruleDao.findAll(actionInfo.getRuleType());
        // 获取规则对应数据（by ruleIdList）
        String ruleIdList = "-1";// 代表不检索任何rule对应的ruleData
        if (!CollectionUtils.isEmpty(ruleList)) {
            ruleIdList = "";
            for (int i = 0; i < ruleList.size(); i++) {
                Rule rule = ruleList.get(i);
                ruleIdList += rule.getId();
                if (i != ruleList.size() - 1) {
                    ruleIdList += ",";
                }
            }
        }
        List<RuleData> ruleDataList = ruleDataDao.findAll(actionInfo.getRuleDataStatus(), ruleIdList);
        RuleDataFindAllRspInfo rspInfo = new RuleDataFindAllRspInfo();
        rspInfo.initSuccess(actionInfo.getActionId());
        rspInfo.setRuleList(ruleList);
        rspInfo.setRuleDataList(ruleDataList);
        return rspInfo;
    }
}

