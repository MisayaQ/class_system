package com.course.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.CourseDetails;
import com.course.entity.CourseMenu;
import com.course.entity.CoursePurchase;
import com.course.entity.SysUser;
import com.course.service.ICourseDetailsService;
import com.course.service.ICoursePurchaseService;
import com.course.service.ISysUserService;
import com.course.utils.StringUtil;
import com.course.utils.UUIDUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
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

    @Autowired
    private ISysUserService iSysUserService;

    @ApiOperation(value = "分页查询", notes = "")
    @GetMapping("/getPurchaseByPage")
    public Ret getPurchaseByPage(Integer page, Integer pageSize, CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        CoursePurchase purchasequery = new CoursePurchase();
        purchasequery.setPage((page - 1) * pageSize);
        purchasequery.setPagesize(pageSize);
        if (StringUtils.isNotEmpty(coursePurchase.getUserId())) {
            purchasequery.setUserId(coursePurchase.getUserId());
        }
        if (StringUtils.isNotEmpty(coursePurchase.getCourseName())) {
            purchasequery.setCourseName("%" + coursePurchase.getCourseName() + "%");
        }
        if (StringUtils.isNotEmpty(coursePurchase.getUname())) {
            purchasequery.setUname("%" + coursePurchase.getUname() + "%");
        }
        queryWrapper.orderByDesc("updated_time");
        List<CoursePurchase> getList = iCoursePurchaseService.queryPurByPage(purchasequery);
//        List<CoursePurchase> purList = getList.getRecords();
        if (getList != null && !getList.isEmpty()) {
            for (CoursePurchase purchase : getList) {
                if (StringUtils.isNotEmpty(purchase.getDetailsId())) {
                    CourseDetails courseDetails = iCourseDetailsService.getById(purchase.getDetailsId());
                    SysUser user = iSysUserService.getById(purchase.getUserId());
                    if (courseDetails != null) {
                        purchase.setUserName(user.getUname());
                        purchase.setCourseName(courseDetails.getCName());
                        purchase.setStartTime(courseDetails.getStartTime());
                        purchase.setEndTime(courseDetails.getEndTime());
                    }
                }
            }
        }
        return Ret.ok().setData(getList);
    }



    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/addCoursePurchase")
    public Ret addCoursePurchase(CoursePurchase coursePurchase) {
        return iCoursePurchaseService.savePurches(coursePurchase);
    }

    @ApiOperation(value = "修改", notes = "")
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

    @ApiOperation(value = "批量删除", notes = "")
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

    @ApiOperation(value = "统计总数", notes = "")
    @GetMapping("/getPurchaseByCountAll")
    public Ret getPurchaseByCountAll(String countType) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        Date stDate = new Date();
        Date enDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = sdf.format(enDate);
        Calendar cal = Calendar.getInstance();
        List<CountResult> getList = new ArrayList<>();
        CoursePurchase coursePurchase = new CoursePurchase();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE, -7);
            stDate = cal.getTime();
            String startStr = sdf.format(stDate);
        } else if ("month".equals(countType)) {
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
        queryWrapper.gt("created_time", stDate);
        queryWrapper.lt("created_time", enDate);
        getList = iCoursePurchaseService.list(queryWrapper);
        int counts = 0;
        if (getList != null && !getList.isEmpty()) {
            counts = getList.size();
        }
        return Ret.ok().setData(counts);
    }

    @ApiOperation(value = "统计图", notes = "")
    @GetMapping("/getPurchaseByCount")
    public Ret getPurchaseByCount(String countType) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        Date stDate = new Date();
        Date enDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endStr = sdf.format(enDate);
        Calendar cal = Calendar.getInstance();
        List<CountResult> getList = new ArrayList<>();
        CoursePurchase coursePurchase = new CoursePurchase();
        if ("day".equals(countType)) {
            cal.add(Calendar.DATE, -7);
            stDate = cal.getTime();
            String startStr = sdf.format(stDate);
            coursePurchase.setTime1(startStr);
            coursePurchase.setTime2(endStr);
            getList = iCoursePurchaseService.countPurchaseDataByDay(coursePurchase);
        } else if ("month".equals(countType)) {
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

    @ApiOperation(value = "课程购买人数", notes = "")
    @GetMapping("/getPurchaseCountByCourseId")
    public Ret getPurchaseCountByCourseId(CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(coursePurchase.getDetailsId())) {
            queryWrapper.eq("details_id", coursePurchase.getDetailsId());
        }
        List<CoursePurchase> getList = iCoursePurchaseService.list(queryWrapper);
        if (getList != null && !getList.isEmpty()) {
            return Ret.ok().setData(getList.size());
        } else {
            return Ret.ok().setData(0);
        }

    }

}
