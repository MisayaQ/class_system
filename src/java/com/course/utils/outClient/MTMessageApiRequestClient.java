package com.course.utils.outClient;

import com.course.base.Ret;
import com.course.utils.feignClient.MessageFeignClient;
import com.course.utils.feignDto.GroupAddPersonRDTO;
import com.course.utils.feignDto.GroupCreateRDTO;
import com.course.utils.feignDto.GroupQuitPersonRDTO;
import com.course.utils.feignDto.MTUserInfoRspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MTMessageApiRequestClient {

    @Autowired
    private MessageFeignClient messageFeignClient;

    @Autowired
    private MTCotactApiRequestClient mtCotactApiRequestClient;

    private static final Logger logger = LoggerFactory.getLogger(MTMessageApiRequestClient.class);

    public static String zxClientType = "app";

    public static Long zxAccountId = (long)1;

    public String createGroup(GroupCreateRDTO groupCreateRDTO){
        groupCreateRDTO.setDeptId(null);
        groupCreateRDTO.setOnlyOwnerAtAll(1);
        groupCreateRDTO.setOnlyOwnerManage(1);
        groupCreateRDTO.setOnlyOwnerUpdate(1);
        groupCreateRDTO.setUserLimit(3000);
        groupCreateRDTO.setComment("");
        groupCreateRDTO.setFreshViewHistory(1);
        groupCreateRDTO.setType(1);
        groupCreateRDTO.setGroupAdmins(new ArrayList<>());
        Ret ret = messageFeignClient.create(groupCreateRDTO,zxClientType,zxAccountId);
        if("M0000".equalsIgnoreCase(ret.getCode())){
            return ret.getData().toString();
        }
        return "-1";
    }

    public boolean addPerson(GroupAddPersonRDTO groupAddPersonRDTO){
        List<String> accountIds = new ArrayList<>();
        Set<String> userSet = new HashSet<>();
        for(String userId : groupAddPersonRDTO.getAccountIds()){
            userSet.add(userId);
        }
        Map<String,Object> userObject = mtCotactApiRequestClient.userObjectGets(userSet,groupAddPersonRDTO.getCorpId());
        for(Map.Entry<String,Object> entry : userObject.entrySet()){
            accountIds.add(((MTUserInfoRspDTO)entry.getValue()).getAccountId());
        }
        groupAddPersonRDTO.setAccountIds(accountIds);
        /*Ret ret = messageFeignClient.join(groupAddPersonRDTO,zxClientType,zxAccountId);
        if("M0000".equalsIgnoreCase(ret.getCode())){
            return true;
        }*/
        return false;
    }

    public boolean quitPerson(GroupQuitPersonRDTO groupQuitPersonRDTO){
        List<String> accountIds = new ArrayList<>();
        Set<String> userSet = new HashSet<>();
        for(String userId : groupQuitPersonRDTO.getAccountIds()){
            userSet.add(userId);
        }
        Map<String,Object> userObject = mtCotactApiRequestClient.userObjectGets(userSet,groupQuitPersonRDTO.getCorpId());
        for(Map.Entry<String,Object> entry : userObject.entrySet()){
            accountIds.add(((MTUserInfoRspDTO)entry.getValue()).getAccountId());
        }
        groupQuitPersonRDTO.setAccountIds(accountIds);
        /*Ret ret = messageFeignClient.quit(groupQuitPersonRDTO,zxClientType,zxAccountId);
        if("M0000".equalsIgnoreCase(ret.getCode())){
            return true;
        }*/
        return false;
    }
}
