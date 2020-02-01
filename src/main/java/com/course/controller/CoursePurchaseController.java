package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.CourseMenu;
import com.course.entity.CoursePurchase;
import com.course.service.ICourseDetailsService;
import com.course.service.ICoursePurchaseService;
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
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 课程购买记录表  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/course/course-purchase")
public class CoursePurchaseController {

    @Autowired
    private ICoursePurchaseService iCoursePurchaseService;

    @Autowired
    private ICourseDetailsService iCourseDetailsService;

    @ApiOperation(value="分页查询", notes="")
    @GetMapping("/getPurchaseByPage")
    public Ret getPurchaseByPage(Integer page, Integer pageSize, CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Page pageInfo = new Page(page,pageSize);
        if(StringUtils.isNotEmpty(coursePurchase.getUserId())){
            queryWrapper.like("user_id",coursePurchase.getUserId());
        }
        queryWrapper.orderByDesc("updated_time");
        Page<CoursePurchase> getList = iCoursePurchaseService.page(pageInfo,queryWrapper);
        List<CoursePurchase> purList = getList.getRecords();
        if(purList != null && !purList.isEmpty()){
            for (CoursePurchase purchase : purList) {
                if (StringUtils.isNotEmpty(purchase.getDetailsId())) {
                    CourseDetails courseDetails = iCourseDetailsService.getById(purchase.getDetailsId());
                    if(courseDetails != null){
                        purchase.setCourseName(courseDetails.getCName());
                    }
                }
            }
        }
        return Ret.ok().setData(getList);
    }

    @ApiOperation(value="新增", notes="新增")
    @PostMapping("/addCoursePurchase")
    public Ret addCoursePurchase(CoursePurchase coursePurchase) {
        coursePurchase.setId(UUIDUtil.getUUID());
        coursePurchase.setValidFlag(0);
        coursePurchase.setCreatedTime(LocalDateTime.now());
        coursePurchase.setUpdatedTime(LocalDateTime.now());
        boolean result = iCoursePurchaseService.save(coursePurchase);
        if (result) {
            return Ret.ok().setData(coursePurchase);
        } else {
            return Ret.error().setMsg("新增失败");
        }
    }

    @ApiOperation(value="修改", notes="")
    @PutMapping("/updateCoursePurchase")
    public Ret updateCoursePurchase(@RequestBody CoursePurchase coursePurchase) {
        coursePurchase.setUpdatedTime(LocalDateTime.now());
        boolean result = iCoursePurchaseService.updateById(coursePurchase);
        if (result) {
            return Ret.ok().setData(coursePurchase);
        } else {
            return Ret.error().setMsg("修改失败");
        }
    }

    @ApiOperation(value = "批量删除", notes="")
    @PostMapping("/batchDelete")
    public Ret batchDelete(String ids) {
        if (org.apache.commons.lang.StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            List<String> idList = Arrays.asList(idArr);
            boolean result = iCoursePurchaseService.removeByIds(idList);
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
