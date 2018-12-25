package com.rat.controller;

import com.rat.common.RequestCode;
import com.rat.entity.enums.DataGetType;
import com.rat.entity.local.ResourceData;
import com.rat.entity.local.TargetaData;
import com.rat.entity.local.File;
import com.rat.entity.local.Reference;
import com.rat.entity.network.request.*;
import com.rat.entity.network.request.base.RequestInfo;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.utils.GsonUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试接口
 *
 * @author L.jinzhu 2017/3/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring/applicationContext.xml"})
public class UnitTestController {

    @Autowired
    RequestController requestController;

    @Autowired
    ServletContext context;

    MockMvc mockMvc;

    // 测试相关
    RequestInfo requestInfo;
    String requestUrl = "/projectportrait/request";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
        requestInfo = new RequestInfo();
    }

    @org.junit.Test
    public void fileFindAllTest() throws Exception {
        FileFindAllActionInfo actionInfo = new FileFindAllActionInfo(RequestCode.FILE_FIND_ALL, 1, DataGetType.DOWN.getCode());
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }


    @org.junit.Test
    public void sendSMS() throws Exception {
        TargetDataSendActionInfo actionInfo = new TargetDataSendActionInfo(RequestCode.SEND_SECURITY_CODE, "15810592135", TargetaData.CODE_TYPE_REGISTER_LOGIN);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void userRegisterLoginTest() throws Exception {
        // 第三方登录
        // dataFromOtherPlatform   {"ret":0,"msg":"","is_lost":0,"nickname":"IT鼠部落","gender":"男","province":"北京","city":"平谷","figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/30","figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/50","figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/100","figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/40","figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1105974837\/17EFDCF4DE2101561D27119C0D1CE2E7\/100","is_yellow_vip":"0","vip":"0","yellow_vip_level":"0","level":"0","is_yellow_year_vip":"0"}
        UserRegisterLoginActionInfo actionInfo = new UserRegisterLoginActionInfo(
                RequestCode.USER_REGISTER_LOGIN, "17EFDCF4DE2101561223423423342C0D1CE2E7", File.ACCOUNT_TYPE_QQ,
                "{\"ret\":0,\"msg\":\"\",\"is_lost\":0,\"nickname\":\"sfsd\",\"gender\":\"男\",\"province\":\"北京\",\"city\":\"平谷\",\"figureurl\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/30\",\"figureurl_1\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/50\",\"figureurl_2\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/100\",\"figureurl_qq_1\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/40\",\"figureurl_qq_2\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}"
        );
        // 手机号登录
        // UserRegisterLoginActionInfo actionInfo = new UserRegisterLoginActionInfo(RequestCode.USER_REGISTER_LOGIN, File.ACCOUNT_TYPE_PHONE, "15810592135", "3769");
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void userFindAllTest() throws Exception {
//        UserFindAllActionInfo actionInfo = new UserFindAllActionInfo(RequestCode.USER_FIND_ALL, 1, 33, "", "");
//        UserFindAllActionInfo actionInfo = new UserFindAllActionInfo(RequestCode.USER_FIND_ALL, 1, 33, "1", "");
        UserFindAllActionInfo actionInfo = new UserFindAllActionInfo(RequestCode.USER_FIND_ALL, 0, DataGetType.DOWN.getCode(), 1, 0, 0, "", "");
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        FileFindAllRspInfo rsp = GsonUtil.fromJson(result, FileFindAllRspInfo.class);
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + rsp.getFileList().size() + " | " + result);
    }

    @org.junit.Test
    public void userUpdateTest() throws Exception {
        File file = new File();
        file.setUserId(1);
        file.setNickName("啥的");
        file.setWorkName("啥的");
        file.setAge(22);
        file.setBigImg("啥的");
        file.setCityCode("啥的");
        file.setCityName("啥的");
        file.setEducationCode("啥的");
        file.setEducationName("啥的");
        file.setReferenceCount(11);
        file.setReferenceedCount(121);
        file.setHouseCode("啥的");
        file.setHouseName("啥的");
        file.setIntroduce("啥的");
        file.setMarriageCode("啥的");
        file.setMarriageName("啥的");
        file.setRemark("啥的");
        file.setSex("啥的");
        file.setResourceCount(1212);

        UserUpdateActionInfo actionInfo = new UserUpdateActionInfo(RequestCode.USER_UPDATE, file);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void userFindDetailTest() throws Exception {
        UserFindDetailActionInfo actionInfo = new UserFindDetailActionInfo(RequestCode.USER_FIND_DETAIL, 1, 2);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void resourceCreateTest() throws Exception {
        ResourceData resourceData = new ResourceData();
        resourceData.setUserId(1);
        resourceData.setIsDefault("1");
        resourceData.setResourceTitle("resource7");
        resourceData.setResourceImg("http://a.hiphotos.baidu.com/baike/whfpf%3D180%2C140%2C50/sign=20fced2fde2a283443f3654b3d88f8d2/4bed2e738bd4b31cc7a469898dd6277f9e2ff86c.jpg");
        resourceData.setResourceTime("212112");
        resourceData.setResourceUrl("http://7xrjck.com1.z0.glb.clouddn.com/FtB4jjP1vmy27u2aHgVuVGf1GDXt");
        resourceData.setResourcePlayTime("23423432");

        ResourceCreateActionInfo actionInfo = new ResourceCreateActionInfo(RequestCode.VIDEO_CREATE, resourceData);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void resourceDeleteTest() throws Exception {
        List<Long> resourceIdList = new ArrayList<>();
        resourceIdList.add(10L);
        resourceIdList.add(12L);

        ResourceDeleteActionInfo actionInfo = new ResourceDeleteActionInfo(RequestCode.VIDEO_DELETE, 1L, resourceIdList);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void referenceTest() throws Exception {
        Reference reference = new Reference();
        reference.setUserId(1);
        reference.setReferenceUserId(2);
        ReferenceActionInfo actionInfo = new ReferenceActionInfo(RequestCode.FOLLOW, ReferenceActionInfo.FOLLOW_TYPE_CANCLE, reference);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }


    @org.junit.Test
    public void referenceFindAllByUserTest() throws Exception {
        ReferenceFindAllActionInfo actionInfo = new ReferenceFindAllActionInfo(RequestCode.FOLLOW_FIND_BY_USER, 1);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void referenceFindAllByReferenceedUserTest() throws Exception {
        ReferenceFindAllActionInfo actionInfo = new ReferenceFindAllActionInfo(RequestCode.FOLLOW_FIND_BY_FOLLOWED_USER, 1);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }


    @org.junit.Test
    public void newVersion() throws Exception {
        NewVersionActionInfo actionInfo = new NewVersionActionInfo(RequestCode.SYSTEM_NEW_VERSION);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }

    @org.junit.Test
    public void resourceNames() throws Exception {
        ResourceNamesActionInfo actionInfo = new ResourceNamesActionInfo(RequestCode.SYSTEM_VIDEO_NAMES);
        requestInfo.setActionInfo(actionInfo);
        String postJson = GsonUtil.toJson(requestInfo);
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====" + postJson);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .content(postJson));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + result);
    }
}