package com.course.controller;


import com.course.service.ICourseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程问题表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-question")
public class CourseQuestionController {

    @Autowired
    private ICourseQuestionService iCourseQuestionService;



}
