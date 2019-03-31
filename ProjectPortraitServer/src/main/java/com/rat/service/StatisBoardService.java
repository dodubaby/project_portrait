package com.rat.service;

import com.rat.dao.FileDao;
import com.rat.dao.ResourceDao;
import com.rat.dao.RuleDataDao;
import com.rat.dao.TagDataDao;
import com.rat.entity.network.response.statisboard.BadRuleAnalysis;
import com.rat.entity.network.response.statisboard.FunctionAnalysis;
import com.rat.entity.network.response.statisboard.QualityAnalysis;
import com.rat.entity.network.response.statisboard.StatisticBoardRspInfo;
import com.rat.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author : he
 * date : 2019-02-25
 * introduce : 统计面板服务
 */
@Service
public class StatisBoardService extends BaseService {
    @Resource
    private TagDataDao tagDataDao;
    @Resource
    private RuleDataDao ruleDataDao;
    @Resource
    private FileDao fileDao;
    @Resource
    private ResourceDao resourceDao;

    public StatisticBoardRspInfo findStatisBoardInfo(int actionId) {
//        StatisticBoardRspInfo info = new StatisticBoardRspInfo();
//        int hardCodeCount = ruleDataDao.findRuleCountByGroup("hardcode");
//        int errorCount = ruleDataDao.findRuleCountByGroup("error");
//        int warnCount = ruleDataDao.findRuleCountByGroup("warn");
//        BadRuleAnalysis badRuleAnalysis = new BadRuleAnalysis(hardCodeCount, errorCount, warnCount);
//        info.setBadRuleAnalysis(badRuleAnalysis);
//
//        QualityAnalysis qualityAnalysis = new QualityAnalysis();
//        qualityAnalysis.setStringReplicationCount(resourceDao.findResourceCountByType("string"));
//        qualityAnalysis.setColorReplicationCount(resourceDao.findResourceCountByType("color"));
//        qualityAnalysis.setFileLongCount(fileDao.findFileCountByLine(500));
//        info.setQualityaAnalysis(qualityAnalysis);
//
//        FunctionAnalysis functionAnalysis = new FunctionAnalysis();
//        functionAnalysis.setCommonFunctionCounts(tagDataDao.findTagCountByType("common"));
//        functionAnalysis.setFunctionNumbers(tagDataDao.findTagCountByType("owner"));
//        functionAnalysis.setCoreFunctionCounts(tagDataDao.findTagCountByType("function"));
//        info.setFunctionAnalysis(functionAnalysis);
//        info.initSuccess(actionId);

        // TestData
        StatisticBoardRspInfo info = new StatisticBoardRspInfo();
        BadRuleAnalysis badRuleAnalysis = new BadRuleAnalysis(134, 52, 33);
        info.setBadRuleAnalysis(badRuleAnalysis);
        QualityAnalysis qualityAnalysis = new QualityAnalysis();
        qualityAnalysis.setStringReplicationCount(140);
        qualityAnalysis.setColorReplicationCount(38);
        qualityAnalysis.setFileLongCount(20);
        info.setQualityaAnalysis(qualityAnalysis);
        FunctionAnalysis functionAnalysis = new FunctionAnalysis();
        functionAnalysis.setCommonFunctionCounts(6);
        functionAnalysis.setFunctionNumbers(8);
        functionAnalysis.setCoreFunctionCounts(13);
        info.setFunctionAnalysis(functionAnalysis);
        info.initSuccess(actionId);
        return info;
    }
}
