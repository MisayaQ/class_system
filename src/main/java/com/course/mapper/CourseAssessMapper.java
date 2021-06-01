package com.course.mapper;

import com.course.entity.CourseAssess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.entity.CoursePurchase;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2020-02-16
 */
public interface CourseAssessMapper extends BaseMapper<CourseAssess> {

    List<CourseAssess> queryPurByPage(CourseAssess courseAssessquery);

    Integer queryPurByCount(CourseAssess courseAssessquery);
}
