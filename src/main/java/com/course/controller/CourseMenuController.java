package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.CourseMenu;
import com.course.entity.CoursePurchase;
import com.course.entity.SysUser;
import com.course.service.ICourseDetailsService;
import com.course.service.ICourseMenuService;
import com.course.service.ICoursePurchaseService;
import com.course.service.ISysUserService;
import com.course.utils.StringUtil;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程目录  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-menu")
public class CourseMenuController {

    @Autowired
    private ICourseMenuService iCourseMenuService;

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseMenu")
    public Ret addCourseMenu(@RequestBody CourseMenu courseMenu) {
        if (StringUtils.isNotEmpty(courseMenu.getMenuName())) {
            courseMenu.setId(UUIDUtil.getUUID());
            courseMenu.setValidFlag(0);
            courseMenu.setCreatedTime(LocalDateTime.now());
            courseMenu.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseMenuService.save(courseMenu);
            if (result) {
                return Ret.ok().setData(courseMenu);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        } else {
            return Ret.error().setMsg("课程名称不能为空");
        }
    }

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getMenuByList")
    public Ret getMenuByList(CourseMenu courseMenu) {

        return iCourseMenuService.slelectMenuByList(courseMenu);
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseMenu")
    public Ret updateCourseMenu(@RequestBody CourseMenu courseMenu) {
        if (StringUtils.isNotEmpty(courseMenu.getMenuName())) {
            courseMenu.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseMenuService.updateById(courseMenu);
            if (result) {
                return Ret.ok().setData(courseMenu);
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
            boolean result = iCourseMenuService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

    @ApiOperation(value="今日课程", notes="")
    @GetMapping("/getMenuByToday")
    public Ret getMenuByToday(String userId) {
        return iCourseMenuService.selectTodayCourse(userId);
    }


}
