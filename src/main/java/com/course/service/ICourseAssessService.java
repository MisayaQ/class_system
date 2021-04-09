package com.course.service;

import com.course.entity.CourseAssess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.course.entity.CoursePurchase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author test
 * @since 2020-02-16
 */
public interface ICourseAssessService extends IService<CourseAssess> {

    List<CourseAssess> queryPurByPage(CourseAssess courseAssessquery);
}
