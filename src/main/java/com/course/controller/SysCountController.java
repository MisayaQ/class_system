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
        QueryWrapper queryWrapper = new QueryWrapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stDate = "";
        String enDate = sdf.format(new Date());
        Calendar cal = Calendar.getInstance();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE,-7);
            stDate = sdf.format(cal.getTime());
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            stDate = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            stDate = String.valueOf(year) + "-01-01 00:00:00";
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        SysCount sysCount = new SysCount();
        sysCount.setTime1(stDate);
        sysCount.setTime2(enDate);
        int counts = iSysCountService.countAllDatas(sysCount);
        return Ret.ok().setData(counts);
    }

    @ApiOperation(value="统计图表", notes="")
    @GetMapping("/getCountList")
    public Ret getCountList(String countType) throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stDate = "";
        String enDate = sdf.format(new Date());
        Calendar cal = Calendar.getInstance();
        List<CountResult> getList = new ArrayList<>();
        SysCount sysCount = new SysCount();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE,-7);
            stDate = sdf.format(cal.getTime());
            sysCount.setTime1(stDate);
            sysCount.setTime2(enDate);
            getList = iSysCountService.countDataByDay(sysCount);
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            sysCount.setTime1(date);
            sysCount.setTime2(enDate);
            getList = iSysCountService.countDataByDay(sysCount);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            sysCount.setTime1(date);
            sysCount.setTime2(enDate);
            getList = iSysCountService.countDataByMonth(sysCount);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }

        return Ret.ok().setData(getList);
    }
}
