package com.course.utils.outClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.course.base.Ret;
import com.course.utils.HttpDelegate;
import com.course.utils.OkHttpUtil;
import com.course.utils.feignClient.ContactFeignClient;
import com.course.utils.feignClient.GateWayFeignClient;
import com.course.utils.feignDto.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author David
 */
@Component
public class MTCotactApiRequestClient {

    private static final Logger logger = LoggerFactory.getLogger(MTCotactApiRequestClient.class);

    public static String zxClientType = "app";

    public static Long zxAccountId = (long)1;

    public static String ACCESS_TOKEN = "";

    @Autowired
    private OkHttpUtil okHttpUtil;

    @Value("${mt.api}")
    private String api;

    @Value("${server.type}")
    private String serverType;

    @Autowired
    private ContactFeignClient contactFeignClient;
    @Autowired
    private GateWayFeignClient gateWayFeignClient;

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    public MTMyInfoRspDTO userGetByToken(String token){
        Map<String, String> headerParams = new HashMap<>(2);
        headerParams.put("clientType", "app");
        headerParams.put("Authorization", "Bearer " + token);
        String json = HttpDelegate.sendGet(api + "contact/v1/account/get_my_info", headerParams);
        //String json = okHttpUtil.get(api + "contact/v1/account/get_my_info", null, headerParams);
        if(StringUtils.isNotEmpty(json)) {
            return JSON.parseObject(json, new TypeReference<Ret<MTMyInfoRspDTO>>() {}).getData();
        }
        return null;
    }

    /**
     * 根据用户id获取用户信息
     *
     * @return
     */
    public UserGetRspDTO accountGetMyInfo(String corpId, String userId) {
        if("dev".equalsIgnoreCase(serverType)){
            login();

            Map<String, String> queries = new HashMap<>(2);
            queries.put("corpId", corpId);
            queries.put("id", userId);


            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/user/get", queries, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<UserGetRspDTO>>() {
                }).getData();
            }
            return null;
        }
        return contactFeignClient.accountGetMyInfo(corpId,userId,zxClientType,zxAccountId).getData();
    }

    /**
     * 角色组&角色列表查询
     *
     * @param corpId
     * @return
     */
    public List<MTRoleGroupInfoRspDTO> roleListRoleGroup(String corpId) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> queries = new HashMap<>(1);
            queries.put("corpId", corpId);
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/role/listRoleGroup", queries, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<List<MTRoleGroupInfoRspDTO>>>() {
                }).getData();
            }
            return new ArrayList<>();
        }
        RoleGroupGetListReqDTO roleGroupGetListReqDTO = new RoleGroupGetListReqDTO();
        roleGroupGetListReqDTO.setCorpId(Long.parseLong(corpId));
        return contactFeignClient.listRoleGroup(corpId,zxClientType,zxAccountId).getData();
    }


    public List<RoleGetListRspDTO> roleList(String name,String corpId) {
        return contactFeignClient.listRole(name,corpId,zxClientType,zxAccountId).getData();
    }



    /**
     * 根据公司id和用户ids 获取用户信息
     *
     * @return
     */
    public List<MTUserInfoRspDTO> userGets(MTUserGetsReqDTO mtUserGetsReqDTO) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.postForJson(api + "contact/v1/user/gets", JSON.toJSONString(mtUserGetsReqDTO), headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<List<MTUserInfoRspDTO>>>() {
                }).getData();
            }
            return new ArrayList<>();
        }
        return contactFeignClient.userGets(mtUserGetsReqDTO,zxClientType,zxAccountId).getData();
    }

    public List<GetAllRspDTO> userAll(String corpId) {
        return contactFeignClient.userAll(corpId,zxClientType,zxAccountId).getData();
    }

    /**
     * 根据公司id和用户ids获取用户id对应姓名的map集合
     * @return
     */
    public List<MTUserInfoRspDTO> userListGets(Set<String> userSet, String corpId){
        List<String> userIds = new ArrayList<>(userSet);
        MTUserGetsReqDTO mtUserGetsReqDTO = new MTUserGetsReqDTO();
        mtUserGetsReqDTO.setIds(userIds);
        mtUserGetsReqDTO.setCorpId(corpId);
        List<MTUserInfoRspDTO> list = this.userGets(mtUserGetsReqDTO);
        if(list == null){
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 根据公司id和用户ids获取用户id对应姓名的map集合
     * @return
     */
    public Map<String,String> userMapGets(Set<String> userSet, String corpId){
        List<String> userIds = new ArrayList<>(userSet);
        MTUserGetsReqDTO mtUserGetsReqDTO = new MTUserGetsReqDTO();
        mtUserGetsReqDTO.setIds(userIds);
        mtUserGetsReqDTO.setCorpId(corpId);
        List<MTUserInfoRspDTO> list = this.userGets(mtUserGetsReqDTO);
        Map<String,String> result = new HashMap<>();
        if(list != null){
            for(MTUserInfoRspDTO mtUserInfoRspDTO : list){
                result.put(mtUserInfoRspDTO.getId(),mtUserInfoRspDTO.getName());
            }
        }
        return result;
    }

    /**
     * 根据公司id和用户ids获取用户id对应姓名的map集合
     * @return
     */
    public Map<String,String> userAccountMapGets(Set<String> userSet, String corpId){
        List<String> userIds = new ArrayList<>(userSet);
        MTUserGetsReqDTO mtUserGetsReqDTO = new MTUserGetsReqDTO();
        mtUserGetsReqDTO.setIds(userIds);
        mtUserGetsReqDTO.setCorpId(corpId);
        List<MTUserInfoRspDTO> list = this.userGets(mtUserGetsReqDTO);
        Map<String,String> result = new HashMap<>();
        if(list != null){
            for(MTUserInfoRspDTO mtUserInfoRspDTO : list){
                result.put(mtUserInfoRspDTO.getId(),mtUserInfoRspDTO.getAccountId());
            }
        }
        return result;
    }

    /**
     * 根据公司id和用户ids获取用户id对应姓名的map集合
     * @return
     */
    public Map<String,Object> userObjectGets(Set<String> userSet, String corpId){
        List<String> userIds = new ArrayList<>(userSet);
        MTUserGetsReqDTO mtUserGetsReqDTO = new MTUserGetsReqDTO();
        mtUserGetsReqDTO.setIds(userIds);
        mtUserGetsReqDTO.setCorpId(corpId);
        List<MTUserInfoRspDTO> list = this.userGets(mtUserGetsReqDTO);
        Map<String,Object> result = new HashMap<>();
        if(list != null){
            for(MTUserInfoRspDTO mtUserInfoRspDTO : list){
                result.put(mtUserInfoRspDTO.getId(),mtUserInfoRspDTO);
            }
        }
        return result;
    }

    /**
     * 根据公司id和角色id 获取该角色下的所有人员
     *
     * @param corpId
     * @param roleId
     * @return
     */
    public List<MTRoleUserInfoRspDTO> userGetRoleInfoList(String corpId, String roleId) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> queries = new HashMap<>(1);
            queries.put("corpId", corpId);
            queries.put("roleId", roleId);
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/user/getRoleInfoList", queries, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<List<MTRoleUserInfoRspDTO>>>() {
                }).getData();
            }
            return new ArrayList<>();
        }
        return contactFeignClient.getRoleInfoList(roleId,corpId,zxClientType,zxAccountId).getData();
    }

    /**
     * 根据公司获取公司下的所有部门(tree)
     *
     * @param corpId
     * @return
     */
    public MTDeptTreeUserInfoRspDTO deptGetTreeByCorpId(String corpId) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/dept/get_tree?corpId=" + corpId, null, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<MTDeptTreeUserInfoRspDTO>>() {
                }).getData();
            }
        }
        return contactFeignClient.getDeptTree(Long.parseLong(corpId),zxClientType,zxAccountId).getData();
    }

    /**
     * 根据id获取单个部门
     * @param id
     * @param corpId
     * @return
     */
    public DeptGetRspDTO deptGetById(String id,String corpId) {
        return contactFeignClient.getDeptById(id,Long.parseLong(corpId),zxClientType,zxAccountId).getData();
    }

    /**
     * 根据公司id和部门ids获取用户列表
     * @param corpId
     * @return
     */
    public List<MTGetUserByDeptIdsRspDTO> userGetUserByDeptIds(String corpId, List<Long> deptIds) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            GetUserByDeptIdReqDTO mtGetUserByDeptIdsReqDTO = new GetUserByDeptIdReqDTO();
            mtGetUserByDeptIdsReqDTO.setCorpId(Long.parseLong(corpId));
            mtGetUserByDeptIdsReqDTO.setDeptIds(deptIds);
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.postForJson(api + "contact/v1/user/get_user_by_deptids", JSON.toJSONString(mtGetUserByDeptIdsReqDTO), headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<List<MTGetUserByDeptIdsRspDTO>>>() {
                }).getData();
            }
            return new ArrayList<>();
        }
        GetUserByDeptIdReqDTO getUserByDeptIdReqDTO = new GetUserByDeptIdReqDTO();
        getUserByDeptIdReqDTO.setCorpId(Long.parseLong(corpId));
        getUserByDeptIdReqDTO.setDeptIds(deptIds);
        return contactFeignClient.userGetUserByDeptIds(getUserByDeptIdReqDTO,zxClientType,zxAccountId).getData();
    }

    /**
     * 获取某个部门的下级部门和人员
     * @param corpId
     * @param pid
     * @return
     */
    public MTDeptSubDeptUserPagelistRspDTO deptSubDeptUserPagelist(String corpId, String pid) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> queries = new HashMap<>(4);
            queries.put("corpId", corpId);
            queries.put("pid", pid);
            queries.put("pageNum", "1");
            queries.put("pageSize", "1000");
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/dept/pagelist", queries, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<MTDeptSubDeptUserPagelistRspDTO>>() {
                }).getData();
            }
        }
        return contactFeignClient.deptSubDeptUserPagelist(Long.parseLong(corpId),
                Long.parseLong(pid),
                1,
                1000,
                zxClientType,zxAccountId).getData();
    }

    /**
     * 根据公司id和用户id查询该用户的角色
     * @param corpId
     * @param userId
     * @return
     */
    public List<MTGetUserRoleRspDTO> userGetUserRole(String corpId, String userId) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> queries = new HashMap<>(4);
            queries.put("corpId", corpId);
            queries.put("userId", userId);
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.postForJson(api + "contact/v1/user/getUserRole", JSON.toJSONString(queries), headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<List<MTGetUserRoleRspDTO>>>() {
                }).getData();
            }
            return new ArrayList<>();
        }
        GetUserRoleReqDTO getUserRoleReqDTO = new GetUserRoleReqDTO();
        getUserRoleReqDTO.setCorpId(corpId);
        getUserRoleReqDTO.setUserId(userId);
        return contactFeignClient.userGetUserRole(getUserRoleReqDTO,zxClientType,zxAccountId).getData();
    }

    /**
     * 获取用户部门
     * @param corpId
     * @param userId
     * @return
     */
    public DeptGetRspDTO getUserDept(String corpId,String userId) {
        UserGetRspDTO userGetRspDTO = accountGetMyInfo(corpId,userId);
        if(userGetRspDTO != null){
            List<DeptGetRspDTO> depts = userGetRspDTO.getDepts();
            if(depts != null && !depts.isEmpty()){
                return depts.get(depts.size()-1);
            }
        }
        return null;
    }

    /**
     * 获取用户部门id
     * @param corpId
     * @param userId
     * @return
     */
    public MTGetUserRoleRspDTO getUserRole(String corpId,String userId) {
        List<MTGetUserRoleRspDTO> mtGetUserRoleRspDTOList = userGetUserRole(corpId,userId);
        if(mtGetUserRoleRspDTOList != null && !mtGetUserRoleRspDTOList.isEmpty()){
            return mtGetUserRoleRspDTOList.get(0);
        }
        return null;
    }

    public List<UserGetPageListByDeptRspDTO> userSearchByName(String corpId, String name) {
        if("dev".equalsIgnoreCase(serverType)){
            login();
            Map<String, String> queries = new HashMap<>(4);
            queries.put("corpId", corpId);
            queries.put("name", name);
            queries.put("pageNum", "1");
            queries.put("pageSize", "1000");
            Map<String, String> headerParams = new HashMap<>(2);
            headerParams.put("clientType", "app");
            headerParams.put("Authorization", "Bearer " + ACCESS_TOKEN);
            String json = okHttpUtil.get(api + "contact/v1/user/search_by_name", queries, headerParams);
            if(StringUtils.isNotEmpty(json)) {
                return JSON.parseObject(json, new TypeReference<Ret<MTUserGetPageListRspDTO>>() {
                }).getData().getList();
            }
            return new ArrayList<>();
        }
        return contactFeignClient.userSearchByName(Long.parseLong(corpId),
                name,
                1,
                1000,
                zxClientType,zxAccountId).getData().getList();
    }

    /**
     * 获取部门下所有子部门内人员
     * @param corpId
     * @param deptId
     * @return
     */
    public List<UserGetPageListByDeptCascadeRspDTO> allUserListByDept(String corpId,String deptId){
        return contactFeignClient.allUserListByDept(Long.parseLong(corpId),
                deptId,1,10000,zxClientType,zxAccountId).getData().getList();
    }

    /**
     * 根据多个部门id获取部门内人员
     * @param corpId
     * @param deptIds
     * @return
     */
    public List<GetAllRspDTO> getUserByDeptIds(String corpId,String deptIds){
        if(StringUtils.isBlank(deptIds)){
            return new ArrayList<>();
        }
        GetUserByDeptIdReqDTO getUserByDeptIdReqDTO = new GetUserByDeptIdReqDTO();
        getUserByDeptIdReqDTO.setCorpId(Long.parseLong(corpId));
        String[] deptArr = deptIds.split(",");
        List<Long> deptIdList = new ArrayList<>();
        for(int i=0;i<deptArr.length;i++){
            deptIdList.add(Long.parseLong(deptArr[i]));
        }
        getUserByDeptIdReqDTO.setDeptIds(deptIdList);
        return contactFeignClient.getUserByDeptIds(getUserByDeptIdReqDTO,zxClientType,zxAccountId).getData();
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    public RefreshDTO refreshToken(String refreshToken) {
        return gateWayFeignClient.refreshToken(refreshToken,zxClientType,zxAccountId).getData();
    }

    public RoleInfoRspDTO getRoleById(String roleId) {
        return contactFeignClient.getRoleById(roleId,zxClientType,zxAccountId).getData();
    }

    public void login() {
        Map<String, String> queries = new HashMap<>(1);
        queries.put("username", "15202226641");
        queries.put("password", "whh111222");
        Map<String, String> headerParams = new HashMap<>(2);
        headerParams.put("clientType", "app");
        String json = okHttpUtil.get(api + "app/login", queries, headerParams);
        if(StringUtils.isNotEmpty(json)) {
            Ret<Map<String, Object>> ret = JSON.parseObject(json, new TypeReference<Ret<Map<String, Object>>>() {});
            if(ret.getData() != null) {
                ACCESS_TOKEN = ret.getData().get("access_token").toString();
            } else {
                logger.error("token获取异常");
            }
        }
    }

    public List<MTUserInfoRspDTO> getUsers(String corpId, List<String> deptIds, List<String> roleIds) {
        GetUsersReqDTO getUsersReqDTO = new GetUsersReqDTO();
        getUsersReqDTO.setCorpId(Long.parseLong(corpId));
        List<Long> deptList = new ArrayList<>();
        List<Long> roleIdList = new ArrayList<>();
        if(deptIds != null){
            for(String deptId : deptIds){
                deptList.add(Long.parseLong(deptId));
            }
        }
        if(roleIds != null){
            for(String roleId : roleIds){
                roleIdList.add(Long.parseLong(roleId));
            }
        }
        getUsersReqDTO.setDeptIds(deptList);
        getUsersReqDTO.setRoleIds(roleIdList);
        logger.error(JSON.toJSONString(getUsersReqDTO));
        return contactFeignClient.getUsers(getUsersReqDTO,zxClientType,zxAccountId).getData();
    }
}
