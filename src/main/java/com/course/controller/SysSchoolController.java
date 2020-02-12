package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.SysSchool;
import com.course.service.ISysSchoolService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getSchoolByPage")
    public Ret getSchoolByPage(Integer page, Integer pageSize, SysSchool sysUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(sysUser.getSchoolName())){
            queryWrapper.like("school_name",sysUser.getSchoolName());
        }
        if(StringUtils.isNotEmpty(sysUser.getCity())){
            queryWrapper.like("city",sysUser.getCity());
        }
        if(StringUtils.isNotEmpty(sysUser.getSchoolProperty())){
            queryWrapper.like("school_property",sysUser.getSchoolProperty());
        }
        if(StringUtils.isNotEmpty(sysUser.getSchoolType())){
            queryWrapper.eq("school_type",sysUser.getSchoolType());
        }
        queryWrapper.orderByDesc("updated_time");
        Page pageInfo = new Page(page,pageSize);
        Page<SysSchool> getList = iSysSchoolService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

}
