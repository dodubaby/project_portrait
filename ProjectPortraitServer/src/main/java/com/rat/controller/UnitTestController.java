package com.rat.controller;

import com.rat.common.RequestCode;
import com.rat.entity.enums.DataGetType;
import com.rat.entity.local.system.SecurityCode;
import com.rat.entity.local.user.Follow;
import com.rat.entity.local.user.User;
import com.rat.entity.local.video.Video;
import com.rat.entity.network.request.*;
import com.rat.entity.network.request.base.RequestInfo;
import com.rat.entity.network.response.UserFindAllRspInfo;
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
    String requestUrl = "/seelove/request";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
        requestInfo = new RequestInfo();
    }

    @org.junit.Test
    public void sendSMS() throws Exception {
        SecurityCodeSendActionInfo actionInfo = new SecurityCodeSendActionInfo(RequestCode.SEND_SECURITY_CODE, "15810592135", SecurityCode.CODE_TYPE_REGISTER_LOGIN);
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
                RequestCode.USER_REGISTER_LOGIN, "17EFDCF4DE2101561223423423342C0D1CE2E7", User.ACCOUNT_TYPE_QQ,
                "{\"ret\":0,\"msg\":\"\",\"is_lost\":0,\"nickname\":\"sfsd\",\"gender\":\"男\",\"province\":\"北京\",\"city\":\"平谷\",\"figureurl\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/30\",\"figureurl_1\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/50\",\"figureurl_2\":\"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/100\",\"figureurl_qq_1\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/40\",\"figureurl_qq_2\":\"http:\\/\\/q.qlogo.cn\\/qqapp\\/1105974837\\/17EFDCF4DE2101561D27119C0D1CE2E7\\/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}"
        );
        // 手机号登录
        // UserRegisterLoginActionInfo actionInfo = new UserRegisterLoginActionInfo(RequestCode.USER_REGISTER_LOGIN, User.ACCOUNT_TYPE_PHONE, "15810592135", "3769");
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
        UserFindAllRspInfo rsp = GsonUtil.fromJson(result, UserFindAllRspInfo.class);
        System.out.println("=============== 请求获得响应 =============================================");
        System.out.println("====" + rsp.getUserDetailList().size() + " | " + result);
    }

    @org.junit.Test
    public void userUpdateTest() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setNickName("啥的");
        user.setWorkName("啥的");
        user.setAge(22);
        user.setBigImg("啥的");
        user.setCityCode("啥的");
        user.setCityName("啥的");
        user.setEducationCode("啥的");
        user.setEducationName("啥的");
        user.setFollowCount(11);
        user.setFollowedCount(121);
        user.setHouseCode("啥的");
        user.setHouseName("啥的");
        user.setIntroduce("啥的");
        user.setMarriageCode("啥的");
        user.setMarriageName("啥的");
        user.setRemark("啥的");
        user.setSex("啥的");
        user.setVideoCount(1212);

        UserUpdateActionInfo actionInfo = new UserUpdateActionInfo(RequestCode.USER_UPDATE, user);
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
    public void videoCreateTest() throws Exception {
        Video video = new Video();
        video.setUserId(1);
        video.setIsDefault("1");
        video.setVideoTitle("video7");
        video.setVideoImg("http://a.hiphotos.baidu.com/baike/whfpf%3D180%2C140%2C50/sign=20fced2fde2a283443f3654b3d88f8d2/4bed2e738bd4b31cc7a469898dd6277f9e2ff86c.jpg");
        video.setVideoTime("212112");
        video.setVideoUrl("http://7xrjck.com1.z0.glb.clouddn.com/FtB4jjP1vmy27u2aHgVuVGf1GDXt");
        video.setVideoPlayTime("23423432");

        VideoCreateActionInfo actionInfo = new VideoCreateActionInfo(RequestCode.VIDEO_CREATE, video);
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
    public void videoDeleteTest() throws Exception {
        List<Long> videoIdList = new ArrayList<>();
        videoIdList.add(10L);
        videoIdList.add(12L);

        VideoDeleteActionInfo actionInfo = new VideoDeleteActionInfo(RequestCode.VIDEO_DELETE, 1L, videoIdList);
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
    public void followTest() throws Exception {
        Follow follow = new Follow();
        follow.setUserId(1);
        follow.setFollowUserId(2);
        FollowActionInfo actionInfo = new FollowActionInfo(RequestCode.FOLLOW, FollowActionInfo.FOLLOW_TYPE_CANCLE, follow);
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
    public void followFindAllByUserTest() throws Exception {
        FollowFindAllActionInfo actionInfo = new FollowFindAllActionInfo(RequestCode.FOLLOW_FIND_BY_USER, 1);
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
    public void followFindAllByFollowedUserTest() throws Exception {
        FollowFindAllActionInfo actionInfo = new FollowFindAllActionInfo(RequestCode.FOLLOW_FIND_BY_FOLLOWED_USER, 1);
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
    public void newsFindAllTest() throws Exception {
        NewsFindAllActionInfo actionInfo = new NewsFindAllActionInfo(RequestCode.NEWS_FIND_ALL, 1, DataGetType.DOWN.getCode(), 1);
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
    public void videoNames() throws Exception {
        VideoNamesActionInfo actionInfo = new VideoNamesActionInfo(RequestCode.SYSTEM_VIDEO_NAMES);
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