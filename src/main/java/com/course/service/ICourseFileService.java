package com.course.service;

import com.course.entity.CourseFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程附件表  服务类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
public interface ICourseFileService extends IService<CourseFile> {
    List<CourseFile> queryFileByPage(CourseFile courseFile);
}
