package com.rat.controller;

import com.alibaba.fastjson.JSONObject;
import com.rat.common.Constant;
import com.rat.common.RequestCode;
import com.rat.entity.enums.DataGetType;
import com.rat.entity.network.request.*;
import com.rat.entity.network.request.base.ActionInfo;
import com.rat.entity.network.request.base.ActionInfoWithPageData;
import com.rat.entity.network.request.base.RequestInfo;
import com.rat.entity.network.response.base.ResponseInfo;
import com.rat.service.*;
import com.rat.utils.GsonUtil;
import com.rat.utils.SafeParseUtils;
import com.rat.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

/**
 * 请求回调接口
 *
 * @author L.jinzhu 2018/3/30
 */
@Controller
@RequestMapping("/pp")
public class RequestController {

    private static Logger logger = LoggerFactory.getLogger(RequestController.class);
    @Resource
    private FileService fileService;
    @Resource
    private ResourceService resourceService;
    @Resource
    private ReferenceService referenceService;
    @Resource
    private RuleService ruleService;
    @Resource
    private RuleDataService ruleDataService;
    @Resource
    private SystemService systemService;
    @Resource
    private TagService tagService;
    @Resource
    private TagDataService tagDataService;
    @Resource
    private StatisBoardService statisBoardService;

    public RequestController() {
        logger.info("rat server start");
        initSystemProfiles();
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决 js ajax跨域访问
    }

    /**
     * 拦截GET请求
     */
    @RequestMapping(value = "/request", method = {RequestMethod.GET})
    @ResponseBody()
    public String run(@RequestParam(required = false) Map<String, String> map) {
        logger.info(Constant.LOG_REQUEST + " [GET] param size: " + map.size());
        ResponseInfo responseBody;
        int actionId;
        try {
            actionId = Integer.parseInt(map.get("actionId"));
        } catch (Throwable e) {
            // 请求解析异常
            return ResponseInfo.getErrorResponse4Param(0);
        }
        ActionInfo actionInfo;
        String suffix;// 文件后缀
        String dataType;// 数据类型
        String dataId;// 数据Id
        String tags;// 数据对应的tag列表
        String ruleGroup;
        switch (actionId) {
            // 用户login
            case RequestCode.SYSTEM_USER_LOGIN:
                // 直接返回，暂不控制权限
                responseBody = new ResponseInfo();
                responseBody.initSuccess(actionId);
                break;
            // 文件获取全部
            case RequestCode.FILE_FIND_ALL:
                String rootKey = map.get("rootKey");
                tags = map.get("tags");
                suffix = map.get("suffix");
                actionInfo = new FileFindAllActionInfo(actionId, suffix, rootKey, tags);
                responseBody = fileService.findAll((FileFindAllActionInfo) actionInfo);
                break;
            // 文件获取:by类型for行数
            case RequestCode.FILE_FIND_BY_SUFFIX_ORDER_BY_LINE_COUNT:
                suffix = map.get("suffix");
                int maxLineCount = 0;
                if (StringUtil.isNullOrBlank(suffix)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                if (map.containsKey("maxLineCount")) {
                    maxLineCount = SafeParseUtils.parseInt(map.get("maxLineCount"));
                }
                actionInfo = new FileFindBySuffixOrderByLineCountActionInfo(actionId, 0, DataGetType.DOWN.getCode(), suffix, maxLineCount);
                responseBody = fileService.findAllBySuffixOrderByLineCount((FileFindBySuffixOrderByLineCountActionInfo) actionInfo);
                break;
            // 资源获取全部
            case RequestCode.RESOURCE_FIND_ALL:
                actionInfo = new ActionInfoWithPageData(actionId, 0, DataGetType.DOWN.getCode());
                responseBody = resourceService.findAll((ActionInfoWithPageData) actionInfo);
                break;
            // 资源获取:统计by数量
            case RequestCode.RESOURCE_FIND_STATISTICS_BY_COUNT:
                actionInfo = new ActionInfoWithPageData(actionId, 0, DataGetType.DOWN.getCode());
                responseBody = resourceService.findStatisticsForCount((ActionInfoWithPageData) actionInfo);
                break;
            // 资源获取:by value
            case RequestCode.RESOURCE_FIND_BY_VALUE:
                String value = map.get("value");
                if (StringUtil.isNullOrBlank(value)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new ResourceFindByValueActionInfo(actionId, value);
                responseBody = resourceService.findByValue((ResourceFindByValueActionInfo) actionInfo);
                break;
            // 引用获取全部
            case RequestCode.REFERENCE_FIND_ALL:
                String key = map.get("key");
                actionInfo = new ReferenceActionInfo(actionId, key);
                responseBody = referenceService.findAll((ReferenceActionInfo) actionInfo);
                break;
            // Rule对应数据获取全部
            case RequestCode.RULE_DATA_FIND_ALL:
                ruleGroup = map.get("ruleGroup");
                actionInfo = new RuleDataFindAllActionInfo(actionId, ruleGroup);
                responseBody = ruleDataService.findAll((RuleDataFindAllActionInfo) actionInfo);
                break;
            // Rule删除
            case RequestCode.RULE_DELETE_BY_ID:
                String ruleId = map.get("ruleId");
                if (StringUtil.isNullOrBlank(ruleId)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new RuleDeleteActionInfo(actionId, ruleId);
                responseBody = ruleService.deleteRuleById((RuleDeleteActionInfo) actionInfo);
                break;
            // Rule新增
            case RequestCode.RULE_INSERT:
                String scanFileSuffix = map.get("scanFileSuffix");
                String keyLeft = map.get("keyLeft");
                String keyRight = map.get("keyRight");
                ruleGroup = map.get("ruleGroup");
                String remark = map.get("remark");
                String creater = map.get("creater");
                if (StringUtil.isNullOrBlank(scanFileSuffix) || StringUtil.isNullOrBlank(ruleGroup) || StringUtil.isNullOrBlank(remark)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new RuleInsertActionInfo(actionId, scanFileSuffix, keyLeft, keyRight, ruleGroup, remark, creater);
                responseBody = ruleService.insertRule((RuleInsertActionInfo) actionInfo);
                break;
            // Tag获取:by type
            case RequestCode.TAG_FIND_BY_TYPE:
                String type = map.get("type");
                if (StringUtil.isNullOrBlank(type)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new TagFindByTypeActionInfo(actionId, 0, DataGetType.DOWN.getCode(), type);
                responseBody = tagService.findByType((TagFindByTypeActionInfo) actionInfo);
                break;
            // Tag获取全部
            case RequestCode.TAG_FIND_ALL:
                actionInfo = new ActionInfo(actionId);
                responseBody = tagService.findAll(actionInfo);
                break;
            // tag新增
            case RequestCode.TAG_INSERT:
                String insertType = map.get("type");
                String insertValue = map.get("value");
                if (StringUtil.isNullOrBlank(insertType) || StringUtil.isNullOrBlank(insertValue)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new TagInsertDeleteActionInfo(actionId, insertType, insertValue);
                responseBody = tagService.insertTag((TagInsertDeleteActionInfo) actionInfo);
                break;
            // tag根据value删除
            case RequestCode.TAG_DELETE_BY_VALUE:
                String delType = map.get("type");
                String delValue = map.get("value");
                if (StringUtil.isNullOrBlank(delType) || StringUtil.isNullOrBlank(delValue)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new TagInsertDeleteActionInfo(actionId, delType, delValue);
                responseBody = tagService.deleteTagByValue((TagInsertDeleteActionInfo) actionInfo);
                break;
            // TagData获取:by dataId
            case RequestCode.TAG_DATA_FIND_BY_DATA_ID:
                dataType = map.get("dataType");
                dataId = map.get("dataId");
                if (StringUtil.isNullOrBlank(dataType) || StringUtil.isNullOrBlank(dataId)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                actionInfo = new TagDataFindByDataIdActionInfo(actionId, SafeParseUtils.parseLong(dataId), dataType);
                responseBody = tagDataService.findByDataId((TagDataFindByDataIdActionInfo) actionInfo);
                break;
            // TagData更新
            case RequestCode.TAG_DATA_UPDATE_TAGS:
                dataType = map.get("dataType");
                dataId = map.get("dataId");
                tags = map.get("tags");
                if (StringUtil.isNullOrBlank(dataType) || StringUtil.isNullOrBlank(dataId)) {
                    return ResponseInfo.getErrorResponse4Param(actionId);
                }
                String[] tagArray = tags.split(",");
                actionInfo = new TagDataUpdateTagsActionInfo(actionId, SafeParseUtils.parseLong(dataId), dataType, tagArray);
                responseBody = tagDataService.updateTagList((TagDataUpdateTagsActionInfo) actionInfo);
                break;
            // 数据面板统计
            case RequestCode.STATIS_DATABOARD_INDEX:
                responseBody = statisBoardService.findStatisBoardInfo(actionId);
                break;
            // 请求解析异常
            default:
                // 请求解析异常
                return ResponseInfo.getErrorResponse4Param(0);
        }
        logger.info(Constant.LOG_RESPONSE + ": " + responseBody.toString());
        return GsonUtil.toJson(responseBody);
    }

    /**
     * 拦截POST请求
     */
    @RequestMapping(value = "/request", method = {RequestMethod.POST})
    @ResponseBody
    public String run(@RequestBody(required = false) String json) {
        logger.info(Constant.LOG_REQUEST + " [POST] " + json);

        RequestInfo requestInfo = GsonUtil.fromJson(json, RequestInfo.class);
        ResponseInfo response = null;
        // 请求解析异常
        if (null == requestInfo || null == requestInfo.getActionInfo()) {
            response = new ResponseInfo();
            response.initError4Param(0);
            logger.info(Constant.LOG_RESPONSE + ": " + response.toString());
            return GsonUtil.toJson(response);
        }
        // 请求解析正常
        JSONObject jsonObject = JSONObject.parseObject(json);
        String actionInfoStr = jsonObject.getString("actionInfo");
        int actionId = requestInfo.getActionInfo().getActionId();
        switch (actionId) {
//            // 用户更新
//            case RequestCode.USER_UPDATE:
//                UserUpdateActionInfo userUpdateActionInfo = GsonUtil.fromJson(actionInfoStr, UserUpdateActionInfo.class);
//                response = fileService.update(userUpdateActionInfo);
//                break;
//            // 获取用户详情
//            case RequestCode.USER_FIND_DETAIL:
//                UserFindDetailActionInfo userFindDetailActionInfo = GsonUtil.fromJson(actionInfoStr, UserFindDetailActionInfo.class);
//                response = fileService.findDetail(userFindDetailActionInfo);
//                break;
            default:
                response = new ResponseInfo();
                response.initError4Param(requestInfo.getActionInfo().getActionId());
                break;
        }
        logger.info(Constant.LOG_RESPONSE + ": " + response.toString());
        return GsonUtil.toJson(response);
    }

    /**
     * 初始化系统配置文件
     */

    private void initSystemProfiles() {
        Properties prop;
        String propFile;
        // 系统配置
        prop = new Properties();
        propFile = getClass().getResource("/").getPath() + File.separator + "system" + File.separator + "system.properties";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(propFile), "UTF-8");
            prop.load(in);
            Constant.DATA_COUNT_OF_PAGE = Integer.parseInt(prop.getProperty("dataCountOfPage"));
            in.close();
            logger.info("rat init system profiles success: dataCountOfPage: " + Constant.DATA_COUNT_OF_PAGE);
        } catch (Throwable e) {
            logger.error("rat init system profiles error", e);
        }
        // 新版本配置
        prop = new Properties();
        propFile = getClass().getResource("/").getPath() + File.separator + "system" + File.separator + "newVersion.properties";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(propFile), "UTF-8");
            prop.load(in);
            Constant.versionCode = prop.getProperty("versionCode");
            Constant.versionName = prop.getProperty("versionName");
            Constant.isForced = prop.getProperty("isForced");
            Constant.downloadUrl = prop.getProperty("downloadUrl");
            Constant.des = prop.getProperty("des");
            in.close();
            logger.info("rat init new version profiles success: versionCode: " + Constant.versionCode + " | versionName: " + Constant.versionName + " | downloadUrl: " + Constant.downloadUrl);
        } catch (Throwable e) {
            logger.error("rat init new version profiles error", e);
        }
    }
}