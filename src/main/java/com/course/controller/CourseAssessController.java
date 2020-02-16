package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.CourseAssess;
import com.course.entity.SysUser;
import com.course.service.ICourseAssessService;
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
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/course/course-assess")
public class CourseAssessController {

    @Autowired
    private ICourseAssessService iCourseAssessService;

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value="列表查询", notes="")
    @GetMapping("/getAssessByList")
    public Ret getAssessByList(CourseAssess courseAssess) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(courseAssess.getContent())){
            queryWrapper.like("content",courseAssess.getContent());
        }
        queryWrapper.orderByAsc("created_time");
        List<CourseAssess> getList = iCourseAssessService.list(queryWrapper);
        if (getList != null && !getList.isEmpty()) {
            for (CourseAssess cour : getList) {
                if (StringUtils.isNotEmpty(cour.getUserId())) {
                    QueryWrapper query = new QueryWrapper();
                    query.eq("ID",cour.getUserId());
                    SysUser getUser = iSysUserService.getById(cour.getUserId());
                    if (getUser != null) {
                        cour.setUserName(getUser.getUname());
                    }
                }
            }
        }
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCourseAssess")
    public Ret addCourseAssess(@RequestBody CourseAssess courseMenu) {
        if (StringUtils.isNotEmpty(courseMenu.getContent())) {
            courseMenu.setId(UUIDUtil.getUUID());
            courseMenu.setCreatedTime(new Date());
            courseMenu.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseAssessService.save(courseMenu);
            if (result) {
                return Ret.ok().setData(courseMenu);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        } else {
            return Ret.error().setMsg("评价内容不能为空");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCourseAssess")
    public Ret updateCourseAssess(@RequestBody CourseAssess courseMenu) {
        if (StringUtils.isNotEmpty(courseMenu.getContent())) {
            courseMenu.setUpdatedTime(LocalDateTime.now());
            boolean result = iCourseAssessService.updateById(courseMenu);
            if (result) {
                return Ret.ok().setData(courseMenu);
            } else {
                return Ret.error().setMsg("修改失败");
            }

        } else {
            return Ret.error().setMsg("评价内容不能为空");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iCourseAssessService.removeByIds(idList);
            if (result) {
                return Ret.ok().setMsg("删除成功");
            } else {
                return Ret.error().setMsg("删除失败");
            }
        } else {
            return Ret.error().setMsg("请选择要删除的数据");
        }
    }

}
