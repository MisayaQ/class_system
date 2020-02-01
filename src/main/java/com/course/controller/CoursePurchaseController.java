package com.course.controller;


import com.course.service.ICourseDetailsService;
import com.course.service.ICoursePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程购买记录表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-purchase")
public class CoursePurchaseController {
    @Autowired
    private ICoursePurchaseService iCoursePurchaseService;
}
