package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.CourseDetails;
import com.course.entity.CourseMenu;
import com.course.entity.CoursePurchase;
import com.course.entity.SysUser;
import com.course.mapper.CourseDetailsMapper;
import com.course.mapper.CourseMenuMapper;
import com.course.mapper.CoursePurchaseMapper;
import com.course.mapper.SysUserMapper;
import com.course.service.ICourseMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程目录  服务实现类
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Service
public class CourseMenuServiceImpl extends ServiceImpl<CourseMenuMapper, CourseMenu> implements ICourseMenuService {

    @Autowired
    CourseMenuMapper courseMenuMapper;

    @Autowired
    CoursePurchaseMapper coursePurchaseMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    CourseDetailsMapper courseDetailsMapper;

    @Override
    public Ret selectTodayCourse(String userId) {
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
        List<CourseMenu> getAllList = courseMenuMapper.selectList(queryWrapper);
        QueryWrapper wapper1 = new QueryWrapper();
        wapper1.eq("user_id",userId);
        List<CoursePurchase> purList = coursePurchaseMapper.selectList(wapper1);
        List<CourseMenu> menuList = new ArrayList<>();
        if (getAllList != null && !getAllList.isEmpty() && purList != null && !purList.isEmpty()) {
            List<String> getPurId = new ArrayList<>();
            for(CoursePurchase coursePurchase : purList){
                getPurId.add(coursePurchase.getDetailsId());
            }
            for (CourseMenu cour : getAllList) {
                if(getPurId.contains(cour.getCourseId())){
                    getTeacherName(cour);
                    if (StringUtils.isNotEmpty(cour.getCourseId())) {
                        CourseDetails getInfo = courseDetailsMapper.selectById(cour.getCourseId());
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

    @Override
    public Ret slelectMenuByList(CourseMenu courseMenu) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(courseMenu.getCourseId())){
            queryWrapper.eq("course_id",courseMenu.getCourseId());
        }
        queryWrapper.orderByAsc("start_time");
        List<CourseMenu> getList = courseMenuMapper.selectList(queryWrapper);
        for (CourseMenu menu : getList) {
            Date startTime = menu.getStartTime();
            Date endTime = menu.getEndTime();
            SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat endFormat = new SimpleDateFormat(" HH:mm");
            String start = startFormat.format(startTime);
            String end = endFormat.format(endTime);
            menu.setTimeUnite(start+" -"+end);
        }
        if (getList != null && !getList.isEmpty()) {
            for (CourseMenu cour : getList) {
                getTeacherName(cour);
            }
        }
        return Ret.ok().setData(getList);
    }

    public void getTeacherName(CourseMenu cour) {
        if (StringUtils.isNotEmpty(cour.getTeacherId())) {
            QueryWrapper query = new QueryWrapper();
            query.eq("ID",cour.getTeacherId());
            SysUser getUser = sysUserMapper.selectById(cour.getTeacherId());
            if (getUser != null) {
                cour.setTeacherName(getUser.getUname());
            }
        }
    }
}
