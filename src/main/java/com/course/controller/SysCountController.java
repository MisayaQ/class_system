package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.SysUser;
import com.course.service.ISysCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2020-01-29
 */
@RestController
@RequestMapping("/course/sys-count")
public class SysCountController {
    @Autowired
    private ISysCountService iSysCountService;

    @ApiOperation(value="统计查询", notes="")
    @GetMapping("/getCountList")
    public Ret getCountList(String countType) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if ("day".equals(countType)) {

        }else if ("month".equals(countType)) {

        } else if ("year".equals(countType)) {

        }
        List<SysUser> getList = iSysCountService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }
}
