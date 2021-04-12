package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.entity.CourseAssess;
import com.course.entity.CoursePurchase;
import com.course.mapper.CourseAssessMapper;
import com.course.mapper.CoursePurchaseMapper;
import com.course.service.ICourseAssessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-02-16
 */
@Service
public class CourseAssessServiceImpl extends ServiceImpl<CourseAssessMapper, CourseAssess> implements ICourseAssessService {

    @Autowired
    private CourseAssessMapper courseAssessMapper;
    @Override
    public List<CourseAssess> queryPurByPage(CourseAssess courseAssessquery) {
        return courseAssessMapper.queryPurByPage(courseAssessquery);
    }

}
