package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.common.CountResult;
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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

    @ApiOperation(value="统计总数", notes="")
    @GetMapping("/getPurchaseByCountAll")
    public Ret getPurchaseByCountAll(String countType) throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        Date stDate = new Date();
        Date enDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = sdf.format(enDate);
        Calendar cal = Calendar.getInstance();
        List<CountResult> getList = new ArrayList<>();
        CoursePurchase coursePurchase = new CoursePurchase();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE,-7);
            stDate = cal.getTime();
            String startStr = sdf.format(stDate);
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stDate = format.parse(date);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stDate = format.parse(date);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        queryWrapper.gt("created_time",stDate);
        queryWrapper.lt("created_time",enDate);
        getList = iCoursePurchaseService.list(queryWrapper);
        int counts = 0;
        if (getList != null && !getList.isEmpty()) {
            counts = getList.size();
        }
        return Ret.ok().setData(counts);
    }

    @ApiOperation(value="统计图", notes="")
    @GetMapping("/getPurchaseByCount")
    public Ret getPurchaseByCount(String countType) throws Exception{
        QueryWrapper queryWrapper = new QueryWrapper();
        Date stDate = new Date();
        Date enDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = sdf.format(enDate);
        Calendar cal = Calendar.getInstance();
        List<CountResult> getList = new ArrayList<>();
        CoursePurchase coursePurchase = new CoursePurchase();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE,-7);
            stDate = cal.getTime();
            String startStr = sdf.format(stDate);
            coursePurchase.setTime1(startStr);
            coursePurchase.setTime2(endStr);
            getList = iCoursePurchaseService.countPurchaseDataByDay(coursePurchase);
        }else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            coursePurchase.setTime1(date);
            coursePurchase.setTime2(endStr);
            getList = iCoursePurchaseService.countPurchaseDataByDay(coursePurchase);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            coursePurchase.setTime1(date);
            coursePurchase.setTime2(endStr);
            getList = iCoursePurchaseService.countPurchaseDataByMonth(coursePurchase);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        return Ret.ok().setData(getList);
    }

}
