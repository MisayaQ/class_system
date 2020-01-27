package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.SysUser;
import com.course.service.ISysUserService;
import com.course.utils.StringUtil;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户管理  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getUserByPage")
    public Ret getUserByPage(Integer page,Integer pageSize,SysUser sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(sysUser.getUname())){
            queryWrapper.like("uname",sysUser.getUname());
        }
        if(StringUtils.isNotEmpty(sysUser.getAccount())){
            queryWrapper.like("account",sysUser.getAccount());
        }
        if(StringUtils.isNotEmpty(sysUser.getUserRole())){
            queryWrapper.eq("user_role",sysUser.getUserRole());
        }
        if(sysUser.getValidFlag() != null){
            queryWrapper.eq("valid_flag",sysUser.getValidFlag());
        }
        Page pageInfo = new Page(page,pageSize);
        Page<SysUser> getList = iSysUserService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="注册或新增用户")
    @PostMapping("/addUser")
    public Ret addUser(SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",sysUser.getAccount());
            List<SysUser> getList = iSysUserService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                return Ret.error().setMsg("账号已存在");
            } else {
                sysUser.setId(UUIDUtil.getUUID());
                sysUser.setValidFlag(0);
                sysUser.setCreatedTime(LocalDateTime.now());
                sysUser.setUpdatedTime(LocalDateTime.now());
                boolean result = iSysUserService.save(sysUser);
                if (result) {
                    return Ret.ok().setData(sysUser);
                } else {
                    return Ret.error().setMsg("新增失败");
                }
            }
        } else {
            return Ret.error().setMsg("账号不能为空");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateUser")
    public Ret updateUser(SysUser sysUser) {
        if (StringUtils.isNotEmpty(sysUser.getAccount())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",sysUser.getAccount());
            List<SysUser> getList = iSysUserService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !sysUser.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("账号已存在");
                }
            }
            sysUser.setUpdatedTime(LocalDateTime.now());
            boolean result = iSysUserService.updateById(sysUser);
            if (result) {
                return Ret.ok().setData(sysUser);
            } else {
                return Ret.error().setMsg("修改失败");
            }
        } else {
            return Ret.error().setMsg("账号不能为空");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iSysUserService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="根据id查询个人信息", notes="")
    @GetMapping("/getUserById")
    public Ret getUserById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        SysUser getInfo = iSysUserService.getById(id);
        return Ret.ok().setData(getInfo);
    }

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getUserByList")
    public Ret getUserByList(SysUser sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<SysUser> getList = iSysUserService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }

}
