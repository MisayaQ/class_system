package com.course.service.impl;

import com.course.entity.CourseQuestion;
import com.course.mapper.CourseQuestionMapper;
import com.course.service.ICourseQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程问题表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CourseQuestionServiceImpl extends ServiceImpl<CourseQuestionMapper, CourseQuestion> implements ICourseQuestionService {

}
