package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.SysNotice;
import com.course.entity.SysSchool;
import com.course.entity.SysUser;
import com.course.service.ISysSchoolService;
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
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-02-12
 */
@RestController
@RequestMapping("/course/sys-school")
public class SysSchoolController {

    @Autowired
    private ISysSchoolService iSysSchoolService;

    @ApiOperation(value = "分页查询", notes = "")
    @GetMapping("/getSchoolByPage")
    public Ret getSchoolByPage(Integer page, Integer pageSize, SysSchool sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(sysUser.getSchoolName())) {
            queryWrapper.like("school_name", sysUser.getSchoolName());
        }
        if (StringUtils.isNotEmpty(sysUser.getCity())) {
            queryWrapper.like("city", sysUser.getCity());
        }
        if (StringUtils.isNotEmpty(sysUser.getSchoolProperty())) {
            queryWrapper.like("school_property", sysUser.getSchoolProperty());
        }
        if (StringUtils.isNotEmpty(sysUser.getSchoolType())) {
            queryWrapper.eq("school_type", sysUser.getSchoolType());
        }
        queryWrapper.orderByDesc("id");
        Page pageInfo = new Page(page, pageSize);
        Page<SysSchool> getList = iSysSchoolService.page(pageInfo, queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value = "新增学校", notes = "新增")
    @PostMapping("/addSchool")
    public Ret addSchool(SysSchool sysSchool) {
        // 查询学校是否已存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("school_name", sysSchool.getSchoolName());
        List<SysSchool> list = iSysSchoolService.list(queryWrapper);
        if (list.size() != 0) {
            return Ret.error().setMsg("学校已存在");
        } else {
            sysSchool.setId(UUIDUtil.getUUID());
            sysSchool.setCreatedTime(LocalDateTime.now());
            sysSchool.setUpdatedTime(LocalDateTime.now());
            boolean result = iSysSchoolService.save(sysSchool);
            if (result) {
                return Ret.ok().setData(sysSchool);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        }
    }

    @ApiOperation(value = "批量删除学校", notes = "")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iSysSchoolService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value = "修改", notes = "")
    @PutMapping("/updateSchool")
    public Ret updateUser(@RequestBody SysSchool sysSchool) {
        return iSysSchoolService.updateSchool(sysSchool);
//        if (StringUtils.isNotEmpty(sysSchool.getSchoolName())) {
//            // 查询账号是否已存在
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("school_name", sysSchool.getSchoolName());
//            List<SysSchool> getList = iSysSchoolService.list(queryWrapper);
//            if (getList != null && !getList.isEmpty()) {
//                if (getList.size() > 1 || (getList.size() == 1 && !sysSchool.getId().equals(getList.get(0).getId()))) {
//                    return Ret.error().setMsg("学校已存在");
//                }
//            }
//            sysSchool.setUpdatedTime(LocalDateTime.now());
//            boolean result = iSysSchoolService.updateById(sysSchool);
//            if (result) {
//                return Ret.ok().setData(sysSchool);
//            } else {
//                return Ret.error().setMsg("修改失败");
//            }
//        } else {
//            return Ret.error().setMsg("学校名不能为空");
//        }
    }


}
