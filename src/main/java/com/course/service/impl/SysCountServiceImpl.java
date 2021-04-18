package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.SysCount;
import com.course.mapper.SysCountMapper;
import com.course.service.ISysCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-01-29
 */
@Service
public class SysCountServiceImpl extends ServiceImpl<SysCountMapper, SysCount> implements ISysCountService {
    @Autowired
    private SysCountMapper sysCountMapper;

    @Override
    public int countAllDatas(SysCount sysCount) {
        return sysCountMapper.countAllDatas(sysCount);
    }

    @Override
    public List<CountResult> countDataByDay(SysCount sysCount) {
        return sysCountMapper.countDataByDay(sysCount);
    }

    @Override
    public List<CountResult> countDataByMonth(SysCount sysCount) {
        return sysCountMapper.countDataByMonth(sysCount);
    }

    @Override
    public Ret getCountListAll(String countType) {
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
        int counts = sysCountMapper.countAllDatas(sysCount);
        return Ret.ok().setData(counts);
    }

    @Override
    public Ret getCountList(String countType) {
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
            getList = sysCountMapper.countDataByDay(sysCount);
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            sysCount.setTime1(date);
            sysCount.setTime2(enDate);
            getList = sysCountMapper.countDataByDay(sysCount);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            sysCount.setTime1(date);
            sysCount.setTime2(enDate);
            getList = sysCountMapper.countDataByMonth(sysCount);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        return Ret.ok().setData(getList);
    }
}
