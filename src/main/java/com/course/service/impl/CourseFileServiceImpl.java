package com.course.service.impl;

import com.course.entity.CourseFile;
import com.course.mapper.CourseFileMapper;
import com.course.service.ICourseFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程附件表  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-01-06
 */
@Service
public class CourseFileServiceImpl extends ServiceImpl<CourseFileMapper, CourseFile> implements ICourseFileService {

}
