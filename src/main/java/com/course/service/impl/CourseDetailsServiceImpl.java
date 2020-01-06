package com.course.service.impl;

import com.course.entity.CourseDetails;
import com.course.mapper.CourseDetailsMapper;
import com.course.service.ICourseDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程详情表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CourseDetailsServiceImpl extends ServiceImpl<CourseDetailsMapper, CourseDetails> implements ICourseDetailsService {

}
