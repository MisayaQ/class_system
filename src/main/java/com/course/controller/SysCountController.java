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

import java.text.SimpleDateFormat;
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

    @ApiOperation(value="统计查询", notes="")
    @GetMapping("/getCountList")
    public Ret getCountList(String countType) throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        Date stDate = new Date();
        Date enDate = new Date();
        Calendar cal = Calendar.getInstance();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE,-7);
            stDate = cal.getTime();
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stDate = format.parse(date);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stDate = format.parse(date);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        queryWrapper.gt("count_date",stDate);
        queryWrapper.lt("count_date",enDate);
        List<SysUser> getList = iSysCountService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }
}
