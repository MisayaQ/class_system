package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.CourseMenu;
import com.course.entity.SysUser;
import com.course.entity.SysUserAddress;
import com.course.service.ISysUserAddressService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2020-02-02
 */
@RestController
@RequestMapping("/course/sys-user-address")
public class SysUserAddressController {

    @Autowired
    private ISysUserAddressService iSysUserAddressService;

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getAddressByList")
    public Ret getAddressByList(SysUserAddress sysUserAddress) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("updated_time");
        List<SysUser> getList = iSysUserAddressService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addSysUserAddress")
    public Ret addSysUserAddress(@RequestBody SysUserAddress sysUserAddress) {
        sysUserAddress.setId(UUIDUtil.getUUID());
        sysUserAddress.setCreatedTime(LocalDateTime.now());
        sysUserAddress.setUpdatedTime(LocalDateTime.now());
        boolean result = iSysUserAddressService.save(sysUserAddress);
        if (result) {
            return Ret.ok().setData(sysUserAddress);
        } else {
            return Ret.error().setMsg("新增失败");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateSysUserAddress")
    public Ret updateSysUserAddress(@RequestBody SysUserAddress sysUserAddress) {
        sysUserAddress.setUpdatedTime(LocalDateTime.now());
        boolean result = iSysUserAddressService.updateById(sysUserAddress);
        if (result) {
            return Ret.ok().setData(sysUserAddress);
        } else {
            return Ret.error().setMsg("修改失败");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iSysUserAddressService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

}
