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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
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
    public Ret getCourseDetailsByPage(Integer page, Integer pageSize,CourseDetails courseDetails) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        if(StringUtils.isNotEmpty(courseDetails.getTeacherIds())){
            queryWrapper.like("teacher_ids",courseDetails.getTeacherIds());
        }
        if(StringUtils.isNotEmpty(courseDetails.getCName())){
            queryWrapper.like("c_name",courseDetails.getCName());
        }
        if(StringUtils.isNotEmpty(courseDetails.getCourseCode())){
            queryWrapper.like("course_code",courseDetails.getCourseCode());
        }
        if(courseDetails.getIsTop() != null){
            queryWrapper.eq("is_top",courseDetails.getIsTop());
        }
        Page<CourseDetails> getList = iCourseDetailsService.page(pageInfo,queryWrapper);
        List<CourseDetails> detailsList = getList.getRecords();
        if (detailsList != null && !detailsList.isEmpty()) {
            for (CourseDetails cour : detailsList) {
                if (StringUtils.isNotEmpty(cour.getTeacherIds())) {
                    String teacherNames = "";
                    String[] ids = cour.getTeacherIds().split(",");
                    for(String id : ids){
                        QueryWrapper query = new QueryWrapper();
                        query.eq("ID",id);
                        SysUser getUser = iSysUserService.getById(id);
                        if (getUser != null) {
                            teacherNames += getUser.getUname() + ",";
                        }
                    }
                    cour.setTeacherNames(teacherNames);
                }
            }
        }
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseDetails")
    public Ret addCourseDetails(@RequestBody CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("c_name",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                return Ret.error().setMsg("课程名称已存在");
            } else {
                courseDetails.setId(UUIDUtil.getUUID());
                courseDetails.setValidFlag(0);
                courseDetails.setCreatedTime(new Date());
                courseDetails.setUpdatedTime(new Date());
                boolean result = iCourseDetailsService.save(courseDetails);
                if (result) {
                    return Ret.ok().setData(courseDetails);
                } else {
                    return Ret.error().setMsg("新增失败");
                }
            }
        } else {
            return Ret.error().setMsg("课程名称不能为空");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseDetails")
    public Ret updateCourseDetails(@RequestBody CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("c_name",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !courseDetails.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("课程名称已存在");
                }
            }
            courseDetails.setUpdatedTime(new Date());
            boolean result = iCourseDetailsService.updateById(courseDetails);
            if (result) {
                return Ret.ok().setData(courseDetails);
            } else {
                return Ret.error().setMsg("修改失败");
            }
        } else {
            return Ret.error().setMsg("课程名称不能为空");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iCourseDetailsService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="根据id查询个人信息", notes="")
    @GetMapping("/getCourseDetailsById")
    public Ret getCourseDetailsById(String id) {
        //QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ID",id);
        CourseDetails getInfo = iCourseDetailsService.getById(id);
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
