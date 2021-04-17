package com.course.service;

import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;

/**
 * <p>
 * 课程详情表  服务类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
public interface ICourseDetailsService extends IService<CourseDetails> {

    Ret queryCourseByPage(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException;

    Ret queryCourseByPageInStore(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException;

    Ret saveUser(CourseDetails courseDetails);

    Ret updateUser(CourseDetails courseDetails);

    Ret deleteUser(String ids);
}
