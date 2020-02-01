package com.course.service;

import com.course.common.CountResult;
import com.course.entity.SysCount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author test
 * @since 2020-01-29
 */
public interface ISysCountService extends IService<SysCount> {
    int countAllDatas(SysCount sysCount);

    List<CountResult> countDataByDay(SysCount sysCount);

    List<CountResult> countDataByMonth(SysCount sysCount);
}
