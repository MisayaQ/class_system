package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.common.CountResult;
import com.course.entity.CourseDetails;
import com.course.entity.CoursePurchase;
import com.course.entity.SysUser;
import com.course.mapper.CourseDetailsMapper;
import com.course.mapper.CoursePurchaseMapper;
import com.course.mapper.SysUserMapper;
import com.course.service.ICoursePurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程购买记录表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CoursePurchaseServiceImpl extends ServiceImpl<CoursePurchaseMapper, CoursePurchase> implements ICoursePurchaseService {

    @Autowired
    private CoursePurchaseMapper coursePurchaseMapper;

    @Autowired
    private CourseDetailsMapper courseDetailsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<CountResult>
    countPurchaseDataByDay(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.countPurchaseDataByDay(coursePurchase);
    }

    @Override
    public List<CountResult> countPurchaseDataByMonth(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.countPurchaseDataByMonth(coursePurchase);
    }

    @Override
    public List<CoursePurchase> queryPurByPage(CoursePurchase coursePurchase) {
        return coursePurchaseMapper.queryPurByPage(coursePurchase);
    }

    @Override
    public Ret savePurches(CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        coursePurchase.setId(UUIDUtil.getUUID());
        coursePurchase.setValidFlag(0);
        coursePurchase.setCreatedTime(LocalDateTime.now());
        coursePurchase.setUpdatedTime(LocalDateTime.now());
        coursePurchase.setState("0");
        queryWrapper.eq("user_id", coursePurchase.getUserId());
        queryWrapper.eq("details_id", coursePurchase.getDetailsId());
        List<CoursePurchase> list = coursePurchaseMapper.selectList(queryWrapper);
        System.out.println(list);
        if (list != null && list.size() != 0) {
            return Ret.error().setMsg("您已购买过此课程，请勿重复购买");
        } else {
            int result = coursePurchaseMapper.insert(coursePurchase);
            if (result > 0) {
                return Ret.ok().setData(coursePurchase);
            } else {
                return Ret.error().setMsg("新增失败");
            }
        }
    }

    @Override
    public Ret getPurchaseCountAll(String countType) throws ParseException {
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
        getList = coursePurchaseMapper.selectList(queryWrapper);
        int counts = 0;
        if (getList != null && !getList.isEmpty()) {
            counts = getList.size();
        }
        return Ret.ok().setData(counts);
    }

    @Override
    public Ret getPurchaseCount(String countType) {
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
            getList = coursePurchaseMapper.countPurchaseDataByDay(coursePurchase);
        } else if ("month".equals(countType)) {
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01 00:00:00";
            coursePurchase.setTime1(date);
            coursePurchase.setTime2(endStr);
            getList = coursePurchaseMapper.countPurchaseDataByDay(coursePurchase);
        } else if ("year".equals(countType)) {
            int year = cal.get(Calendar.YEAR);
            String date = String.valueOf(year) + "-01-01 00:00:00";
            coursePurchase.setTime1(date);
            coursePurchase.setTime2(endStr);
            getList = coursePurchaseMapper.countPurchaseDataByMonth(coursePurchase);
        } else {
            return Ret.error().setMsg("只能为day,month,year类型");
        }
        return Ret.ok().setData(getList);
    }

    @Override
    public Ret selectPurchaseByPage(Integer page, Integer pageSize, CoursePurchase coursePurchase) {
        QueryWrapper queryWrapper = new QueryWrapper();
        CoursePurchase purchasequery = new CoursePurchase();
        page = (page - 1) * pageSize;
        purchasequery.setPage(page);
        purchasequery.setPagesize(pageSize);
        Page pageInfo = new Page();
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
        List<CoursePurchase> getList = coursePurchaseMapper.queryPurByPage(purchasequery);
        Integer count = coursePurchaseMapper.queryPurByCount(purchasequery);
        Page<CoursePurchase> list = coursePurchaseMapper.selectPage(pageInfo, queryWrapper);
        if (getList != null && !getList.isEmpty()) {
            for (CoursePurchase purchase : getList) {
                if (StringUtils.isNotEmpty(purchase.getDetailsId())) {
                    CourseDetails courseDetails = courseDetailsMapper.selectById(purchase.getDetailsId());
                    SysUser user = sysUserMapper.selectById(purchase.getUserId());
                    if (courseDetails != null) {
                        purchase.setUserName(user.getUname());
                        purchase.setCourseName(courseDetails.getCName());
                        purchase.setStartTime(courseDetails.getStartTime());
                        purchase.setEndTime(courseDetails.getEndTime());
                    }
                }
            }
        }
        list.setRecords(getList);
        list.setTotal(count);
        return Ret.ok().setData(list);
    }
}
