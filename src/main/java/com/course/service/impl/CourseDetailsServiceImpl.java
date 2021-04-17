package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.SysUser;
import com.course.mapper.CourseDetailsMapper;
import com.course.mapper.SysUserMapper;
import com.course.service.ICourseDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程详情表  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CourseDetailsServiceImpl extends ServiceImpl<CourseDetailsMapper, CourseDetails> implements ICourseDetailsService {

    @Autowired
    private CourseDetailsMapper courseDetailsMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Ret queryCourseByPageInStore(Integer page, Integer pageSize,CourseDetails courseDetails) throws ParseException {
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
        queryWrapper.orderByDesc("updated_time");
        Page<CourseDetails> getList = courseDetailsMapper.selectPage(pageInfo,queryWrapper);

        List<CourseDetails> detailsList = getList.getRecords();

        //计算结束时间剩余天数
        Date nowTime = new Date();
        Iterator<CourseDetails> iterator = detailsList.iterator();
        //删除过期课程
        while (iterator.hasNext()) {
            CourseDetails details = iterator.next();
            Date endTime = details.getEndSellTime();
            int i = differentDays(nowTime, endTime);
            if (i <= 0) {
                iterator.remove();
            }
            details.setDeadLine(String.valueOf(i));
        }
        if (detailsList != null && !detailsList.isEmpty()) {
            for (CourseDetails cour : detailsList) {
                if (StringUtils.isNotEmpty(cour.getTeacherIds())) {
                    String teacherNames = "";
                    String[] ids = cour.getTeacherIds().split(",");
                    for(String id : ids){
                        QueryWrapper query = new QueryWrapper();
                        query.eq("ID",id);
                        SysUser getUser = sysUserMapper.selectById(id);
                        if (getUser != null) {
                            teacherNames += getUser.getUname() + ",";
                            teacherNames = teacherNames.substring(0, teacherNames.length() - 1);
                        }
                    }
                    cour.setTeacherNames(teacherNames);
                }
            }
        }
        getList.setRecords(detailsList);
        return Ret.ok().setData(getList);
    }

    @Override
    public Ret queryCourseByPage(Integer page, Integer pageSize, CourseDetails courseDetails) throws ParseException {
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
        queryWrapper.orderByDesc("updated_time");
        Page<CourseDetails> getList = courseDetailsMapper.selectPage(pageInfo,queryWrapper);

        List<CourseDetails> detailsList = getList.getRecords();

        if (detailsList != null && !detailsList.isEmpty()) {
            for (CourseDetails cour : detailsList) {
                if (StringUtils.isNotEmpty(cour.getTeacherIds())) {
                    String teacherNames = "";
                    String[] ids = cour.getTeacherIds().split(",");
                    for(String id : ids){
                        QueryWrapper query = new QueryWrapper();
                        query.eq("ID",id);
                        SysUser getUser = sysUserMapper.selectById(id);
                        if (getUser != null) {
                            teacherNames += getUser.getUname() + ",";
                            teacherNames = teacherNames.substring(0, teacherNames.length() - 1);
                        }
                    }
                    cour.setTeacherNames(teacherNames);
                }
            }
        }
        getList.setRecords(detailsList);
        return Ret.ok().setData(getList);
    }


    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
