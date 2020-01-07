package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.service.ICourseDetailsService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    
    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getCourseDetailsByPage")
    public Ret getCourseDetailsByPage(Integer page, Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        Page<CourseDetails> getList = iCourseDetailsService.page(pageInfo,queryWrapper);
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseDetails")
    public Ret addCourseDetails(CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                return Ret.error().setMsg("课程名称已存在");
            } else {
                courseDetails.setId(UUIDUtil.getUUID());
                courseDetails.setValidFlag(0);
                courseDetails.setCreatedTime(LocalDateTime.now());
                courseDetails.setUpdatedTime(LocalDateTime.now());
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
    public Ret updateCourseDetails(CourseDetails courseDetails) {
        if (StringUtils.isNotEmpty(courseDetails.getCName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("account",courseDetails.getCName());
            List<CourseDetails> getList = iCourseDetailsService.list(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !courseDetails.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("课程名称已存在");
                }
            }
            courseDetails.setUpdatedTime(LocalDateTime.now());
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
