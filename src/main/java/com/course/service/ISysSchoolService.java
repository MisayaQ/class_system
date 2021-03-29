package com.course.service;

import com.course.base.Ret;
import com.course.entity.SysSchool;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author test
 * @since 2020-02-12
 */
public interface ISysSchoolService extends IService<SysSchool> {

    Ret updateSchool(SysSchool sysSchool);
}
