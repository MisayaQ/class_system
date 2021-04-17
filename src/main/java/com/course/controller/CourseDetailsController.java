package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.SysUser;
import com.course.service.ICourseDetailsService;
import com.course.service.ISysUserService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程详情表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-details")
public class CourseDetailsController {

    @Autowired
    private ICourseDetailsService iCourseDetailsService;

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getCourseDetailsByPage")
    public Ret getCourseDetailsByPage(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException {
        return iCourseDetailsService.queryCourseByPage(page,pageSize,courseDetails);
    }

    @ApiOperation(value="分页查询 课程商城", notes="")
    @GetMapping("/getCourseDetailsByPages")
    public Ret getCourseDetailsByPage1(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException {
        return iCourseDetailsService.queryCourseByPageInStore(page,pageSize,courseDetails);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseDetails")
    public Ret addCourseDetails(@RequestBody CourseDetails courseDetails) {
        return iCourseDetailsService.saveUser(courseDetails);
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseDetails")
    public Ret updateCourseDetails(@RequestBody CourseDetails courseDetails) {
        return iCourseDetailsService.updateUser(courseDetails);
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        return iCourseDetailsService.deleteUser(ids);
    }

    @ApiOperation(value="根据id查询", notes="")
    @GetMapping("/getCourseDetailsById")
    public Ret getCourseDetailsById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        CourseDetails getInfo = iCourseDetailsService.getById(id);
        if (StringUtils.isNotEmpty(getInfo.getTeacherIds())) {
            String teacherNames = "";
            String[] ids = getInfo.getTeacherIds().split(",");
            for(String tid : ids){
                QueryWrapper query = new QueryWrapper();
                query.eq("ID",tid);
                SysUser getUser = iSysUserService.getById(tid);
                if (getUser != null) {
                    teacherNames += getUser.getUname() + ",";
                }
            }
            getInfo.setTeacherNames(teacherNames);
        }
        return Ret.ok().setData(getInfo);
    }

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getCourseDetailsByList")
    public Ret getCourseDetailsByList(CourseDetails courseDetails) {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }


}
