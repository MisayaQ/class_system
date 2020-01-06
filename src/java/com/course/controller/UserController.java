package com.course.controller;

import com.course.base.Ret;
import com.course.utils.feignDto.*;
import com.course.utils.outClient.MTCotactApiRequestClient;
import com.course.utils.outClient.MTMessageApiRequestClient;
import com.course.utils.outClient.MTNotificationApiRequestClient;
import com.course.utils.outClient.MTOauthApiRequestClient;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/schedule/tb-user")
public class UserController {

    @Autowired
    private MTCotactApiRequestClient mtCotactApiRequestClient;

    @Autowired
    private MTOauthApiRequestClient mtOauthApiRequestClient;

    @Autowired
    private MTNotificationApiRequestClient mtNotificationApiRequestClient;

    @Autowired
    private MTMessageApiRequestClient messageApiRequestClient;


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/loginByCode")
    @ApiOperation(value = "根据code免登")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "用户code", required = true)
    })
    public Ret loginByCode(String code) {
        MTMyInfoRspDTO mtMyInfoRspDTO;
        try{
            Map<String, String> tokens = mtOauthApiRequestClient.getTokenByCode(code);
            String token = tokens.get("token");
            String refreshToken = tokens.get("refreshToken");
            mtMyInfoRspDTO = mtCotactApiRequestClient.userGetByToken(token);
            mtMyInfoRspDTO.setAccess_token(token);
            mtMyInfoRspDTO.setRefresh_token(refreshToken);
            return Ret.ok().setData(mtMyInfoRspDTO);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return Ret.error().setMsg("免登失败");
    }

    @GetMapping("/refreshToken")
    @ApiOperation(value = "刷新token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refreshToken", value = "刷新token", required = true)
    })
    public Ret refreshToken(String refreshToken) {
        try{
            RefreshDTO refreshDTO = mtCotactApiRequestClient.refreshToken(refreshToken);
            return Ret.ok().setData(refreshDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Ret.error().setMsg("免登失败");
    }



    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public void test() {
        GroupCreateRDTO rdto = GroupCreateRDTO.builder()
                .corpId("6")
                .creator("1130439676516462593")
                .owner("1130439676516462593")
                .name("李孝威的群组")
                .groupAdmins(new ArrayList<>())
                .groupMembers(new ArrayList<>())
                .build();
        messageApiRequestClient.createGroup(rdto);
        /*List<RoleGetListRspDTO> list =  mtCotactApiRequestClient.roleList("调度","6");
        MTNotificationSendReqDTO scheduleMessage = MTNotificationSendReqDTO.builder()
                .receiverIds(Arrays.asList("1130439676516462593"))
                .title("您的日程收到"+3+"条新留言。")
                .content("您的日程收到"+3+"条新留言。")
                .updateCode("1130439676516462593")
                .properties(new HashMap<>())
                .corpId("6")
                .build();
        mtNotificationApiRequestClient.send(scheduleMessage, ScheduleType.SCHEDULE.toString());*/

        List<UserGetPageListByDeptCascadeRspDTO> list = mtCotactApiRequestClient.allUserListByDept("6","1042237414280511489");
        System.out.println("");
    }





}
