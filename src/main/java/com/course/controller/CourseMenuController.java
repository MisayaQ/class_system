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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ICoursePurchaseService iCoursePurchaseService;

    @Autowired
    private ICourseDetailsService iCourseDetailsService;

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
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(courseMenu.getCourseId())){
            queryWrapper.eq("course_id",courseMenu.getCourseId());
        }
        queryWrapper.orderByDesc("updated_time");
        List<CourseMenu> getList = iCourseMenuService.list(queryWrapper);
        if (getList != null && !getList.isEmpty()) {
            for (CourseMenu cour : getList) {
                if (StringUtils.isNotEmpty(cour.getTeacherId())) {
                    QueryWrapper query = new QueryWrapper();
                    query.eq("ID",cour.getTeacherId());
                    SysUser getUser = iSysUserService.getById(cour.getTeacherId());
                    if (getUser != null) {
                        cour.setTeacherName(getUser.getUname());
                    }
                }
            }
        }
        return Ret.ok().setData(getList);
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
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);
        String stDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" +String.valueOf(day) + " 00:00:00";
        String edDate = String.valueOf(year) + "-" + String.valueOf(month) + "-" +String.valueOf(day) + " 23:59:59";
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("start_time",stDate);
        queryWrapper.lt("start_time",edDate);
        queryWrapper.orderByDesc("updated_time");
        List<CourseMenu> getAllList = iCourseMenuService.list(queryWrapper);
        QueryWrapper wapper1 = new QueryWrapper();
        wapper1.eq("user_id",userId);
        List<CoursePurchase> purList = iCoursePurchaseService.list(wapper1);
        List<CourseMenu> menuList = new ArrayList<>();
        if (getAllList != null && !getAllList.isEmpty() && purList != null && !purList.isEmpty()) {
            List<String> getPurId = new ArrayList<>();
            for(CoursePurchase coursePurchase : purList){
                getPurId.add(coursePurchase.getDetailsId());
            }
            for (CourseMenu cour : getAllList) {
                if(getPurId.contains(cour.getCourseId())){
                    if (StringUtils.isNotEmpty(cour.getTeacherId())) {
                        QueryWrapper query = new QueryWrapper();
                        query.eq("ID",cour.getTeacherId());
                        SysUser getUser = iSysUserService.getById(cour.getTeacherId());
                        if (getUser != null) {
                            cour.setTeacherName(getUser.getUname());
                        }
                    }
                    if (StringUtils.isNotEmpty(cour.getCourseId())) {
                        CourseDetails getInfo = iCourseDetailsService.getById(cour.getCourseId());
                        if(getInfo != null){
                            cour.setCourseName(getInfo.getCName());
                        }
                    }
                    menuList.add(cour);
                }
            }
        }
        return Ret.ok().setData(menuList);
    }


}
