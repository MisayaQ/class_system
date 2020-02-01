package com.course.service.impl;

import com.course.common.CountResult;
import com.course.entity.SysCount;
import com.course.mapper.SysCountMapper;
import com.course.service.ISysCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
