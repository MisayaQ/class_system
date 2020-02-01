package com.course.mapper;

import com.course.common.CountResult;
import com.course.entity.SysCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2020-01-29
 */
@Repository
public interface SysCountMapper extends BaseMapper<SysCount> {
    int countAllDatas(SysCount sysCount);

    List<CountResult> countDataByDay(SysCount sysCount);

    List<CountResult> countDataByMonth(SysCount sysCount);
}
