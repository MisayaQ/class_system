package com.course.mapper;

import com.course.entity.CourseFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程附件表  Mapper 接口
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Repository
public interface CourseFileMapper extends BaseMapper<CourseFile> {
    List<CourseFile> queryFileByPage(CourseFile courseFile);
}
