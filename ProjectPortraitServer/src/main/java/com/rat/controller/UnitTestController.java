package com.rat.controller;

import com.rat.common.RequestCode;
import com.rat.entity.enums.DataGetType;
import com.rat.entity.local.ParentChild;
import com.rat.entity.local.Reference;
import com.rat.entity.local.ResourceData;
import com.rat.entity.network.request.*;
import com.rat.entity.network.request.base.ActionInfo;
import com.rat.entity.network.request.base.RequestInfo;
import com.rat.entity.network.response.FileFindAllRspInfo;
import com.rat.service.FileService;
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
    String requestUrl = "/pp/request";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
        requestInfo = new RequestInfo();
    }

    @org.junit.Test
    public void fileFindAllTest() throws Exception {
        System.out.println("=============== 参数准备完成 =============================================");
        System.out.println("====");

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(requestUrl)
                .accept(MediaType.APPLICATION_JSON)
                .param("actionId", "1001"));
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
    public void newVersionTest() throws Exception {
        ActionInfo actionInfo = new ActionInfo(RequestCode.SYSTEM_NEW_VERSION);
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
    public void parentChildTest() throws Exception {
        ParentChild root = new ParentChild("root");
        String str1 = "com.a.b1.c1.d1";
        String str2 = "com.a.b.c.d";
        String[] strArray1 = str1.split("\\.");
        String[] strArray2 = str2.split("\\.");

        root = FileService.addChildList(root, strArray1);
        root = FileService.addChildList(root, strArray2);

        System.out.println("====");
        System.out.println(root);
    }
}