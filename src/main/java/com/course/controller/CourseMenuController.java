package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.CourseMenu;
import com.course.entity.SysUser;
import com.course.service.ICourseMenuService;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    public Ret addCourseMenu(CourseMenu courseMenu) {
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
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(courseMenu.getCourseId())){
            queryWrapper.eq("course_id",courseMenu.getCourseId());
        }
        List<SysUser> getList = iCourseMenuService.list(queryWrapper);
        return Ret.ok().setData(getList);
    }

}
