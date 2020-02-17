package com.course.service.impl;

import com.course.entity.CourseFile;
import com.course.mapper.CourseFileMapper;
import com.course.service.ICourseFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程附件表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CourseFileServiceImpl extends ServiceImpl<CourseFileMapper, CourseFile> implements ICourseFileService {

    @Autowired
    private CourseFileMapper courseFileMapper;

    @Override
    public List<CourseFile> queryFileByPage(CourseFile courseFile) {
        return courseFileMapper.queryFileByPage(courseFile);
    }
}
