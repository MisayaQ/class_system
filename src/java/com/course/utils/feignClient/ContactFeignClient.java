package com.course.utils.feignClient;

import com.course.base.Ret;
import com.course.utils.feignDto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通讯录客户端接口
 */
@FeignClient(value = "ZX-CONTACT")
public interface ContactFeignClient {

    /**
     * 根据userId获取用户信息
     * @return
     */
    @RequestMapping(value = "/user/get",method = RequestMethod.GET)
    Ret<UserGetRspDTO> accountGetMyInfo(@RequestParam("corpId") String corpId,
                                        @RequestParam("id") String id,
                                        @RequestParam("zxClientType") String zxClientType,
                                        @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取角色组
     */
    @RequestMapping(value = "/role/listRoleGroup",method = RequestMethod.GET)
    Ret<List<MTRoleGroupInfoRspDTO>> listRoleGroup(@RequestParam("corpId") String corpId,
                                                   @RequestParam("zxClientType") String zxClientType,
                                                   @RequestParam("zxAccountId") Long zxAccountId);


    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/role/list",method = RequestMethod.GET)
    Ret<List<RoleGetListRspDTO>> listRole(@RequestParam("name") String name,
                                                   @RequestParam("corpId") String corpId,
                                                   @RequestParam("zxClientType") String zxClientType,
                                                   @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 根据用户id获取用户信息（多个）
     */
    @RequestMapping(value = "/user/gets",method = RequestMethod.POST)
    Ret<List<MTUserInfoRspDTO>> userGets(@RequestBody MTUserGetsReqDTO mtUserGetsReqDTO,
                                         @RequestParam("zxClientType") String zxClientType,
                                         @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取所有用户
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/user/all",method = RequestMethod.GET)
    Ret<List<GetAllRspDTO>> userAll(@RequestParam("corpId") String corpId,
                                         @RequestParam("zxClientType") String zxClientType,
                                         @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取角色下人员列表
     * @return
     */
    @RequestMapping(value = "/user/getRoleInfoList",method = RequestMethod.GET)
    Ret<List<MTRoleUserInfoRspDTO>> getRoleInfoList(@RequestParam("roleId") String roleId,
                                                    @RequestParam("corpId") String corpId,
                                                    @RequestParam("zxClientType") String zxClientType,
                                                    @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 通过角色ID获取角色信息
     * @return
     */
    @RequestMapping(value = "/role/get/{id}",method = RequestMethod.GET)
    Ret<RoleInfoRspDTO> getRoleById(@PathVariable("id") String id,
                                    @RequestParam("zxClientType") String zxClientType,
                                    @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取部门树
     * @return
     */
    @RequestMapping(value = "/dept/get_tree",method = RequestMethod.GET)
    Ret<MTDeptTreeUserInfoRspDTO> getDeptTree(@RequestParam("corpId") Long corpId,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取单个部门
     * @param id
     * @param corpId
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/dept/get",method = RequestMethod.GET)
    Ret<DeptGetRspDTO> getDeptById(@RequestParam("id") String id,
                                              @RequestParam("corpId") Long corpId,
                                              @RequestParam("zxClientType") String zxClientType,
                                              @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 根据部门id获取部门下人员列表
     */
    @RequestMapping(value = "/user/get_user_by_deptids",method = RequestMethod.POST)
    Ret<List<MTGetUserByDeptIdsRspDTO>> userGetUserByDeptIds(@RequestBody GetUserByDeptIdReqDTO getUserByDeptIdReqDTO,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取部门列表
     */
    @RequestMapping(value = "/dept/pagelist",method = RequestMethod.GET)
    Ret<MTDeptSubDeptUserPagelistRspDTO> deptSubDeptUserPagelist(@RequestParam("corpId") Long corpId,
            @RequestParam("pid") Long pid,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取用户角色
     */
    @RequestMapping(value = "/user/getUserRole",method = RequestMethod.POST)
    Ret<List<MTGetUserRoleRspDTO>> userGetUserRole(@RequestBody GetUserRoleReqDTO req,
            @RequestParam("zxClientType") String zxClientType,
            @RequestParam("zxAccountId") Long zxAccountId
    );

    /**
     * 通过名称模糊搜索人员列表
     */
    @RequestMapping(value = "/user/search_by_name",method = RequestMethod.GET)
    Ret<MTUserGetPageListRspDTO> userSearchByName(@RequestParam("corpId") Long corpId,
             @RequestParam("name") String name,
             @RequestParam("pageNum") Integer pageNum,
             @RequestParam("pageSize") Integer pageSize,
             @RequestParam("zxClientType") String zxClientType,
             @RequestParam("zxAccountId") Long zxAccountId);


    /**
     * 通过部门id获取部门下所有子成员
     * @param corpId
     * @param deptId
     * @param pageNum
     * @param pageSize
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/user/pagelist_by_dept_cascade",method = RequestMethod.GET)
    Ret<DeptAllUserPageRspDTO<UserGetPageListByDeptCascadeRspDTO>> allUserListByDept(@RequestParam("corpId") Long corpId,
                                                  @RequestParam("deptId") String deptId,
                                                  @RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize") Integer pageSize,
                                                  @RequestParam("zxClientType") String zxClientType,
                                                  @RequestParam("zxAccountId") Long zxAccountId);

    /**
     * 获取多个部门下用户
     * @param zxClientType
     * @param zxAccountId
     * @return
     */
    @RequestMapping(value = "/user/get_user_by_deptids",method = RequestMethod.POST)
    Ret<List<GetAllRspDTO>> getUserByDeptIds(@RequestBody GetUserByDeptIdReqDTO getUserByDeptIdReqDTO,
                                                                                     @RequestParam("zxClientType") String zxClientType,
                                                                                     @RequestParam("zxAccountId") Long zxAccountId);


    /**
     * 通过名称模糊搜索人员列表
     */
    @RequestMapping(value = "/dept/get",method = RequestMethod.GET)
    Ret<DeptGetRspDTO> deptGetById(@RequestParam("corpId") Long corpId,
                                                  @RequestParam("id") String name,
                                                  @RequestParam("zxClientType") String zxClientType,
                                                  @RequestParam("zxAccountId") Long zxAccountId);

    @RequestMapping(value = "/user/getUsers",method = RequestMethod.POST)
    Ret<List<MTUserInfoRspDTO>> getUsers(@RequestBody GetUsersReqDTO req,
                                         @RequestParam("zxClientType") String zxClientType,
                                         @RequestParam("zxAccountId") Long zxAccountId
    );

}
