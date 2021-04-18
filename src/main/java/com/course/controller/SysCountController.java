package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.SysCount;
import com.course.entity.SysUser;
import com.course.service.ISysCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @ApiOperation(value="统计总数", notes="")
    @GetMapping("/getCountListAll")
    public Ret getCountListAll(String countType) throws Exception{
        return iSysCountService.getCountListAll(countType);
    }

    @ApiOperation(value="统计图表", notes="")
    @GetMapping("/getCountList")
    public Ret getCountList(String countType) throws Exception{
        return iSysCountService.getCountList(countType);

    }
}
