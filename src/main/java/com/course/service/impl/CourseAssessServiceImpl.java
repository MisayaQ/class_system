package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.base.Ret;
import com.course.entity.CourseAssess;
import com.course.entity.CoursePurchase;
import com.course.entity.SysUser;
import com.course.mapper.CourseAssessMapper;
import com.course.mapper.CoursePurchaseMapper;
import com.course.mapper.SysUserMapper;
import com.course.service.ICourseAssessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-02-16
 */
@Service
public class CourseAssessServiceImpl extends ServiceImpl<CourseAssessMapper, CourseAssess> implements ICourseAssessService {

    @Autowired
    private CourseAssessMapper courseAssessMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<CourseAssess> queryPurByPage(CourseAssess courseAssessquery) {
        return courseAssessMapper.queryPurByPage(courseAssessquery);
    }

    @Override
    public Ret selectAssesssByPage(Integer page, Integer pageSize, CourseAssess courseAssess) {
        QueryWrapper queryWrapper = new QueryWrapper();
        CourseAssess courseAssessquery = new CourseAssess();
        page = (page - 1) * pageSize;
        courseAssessquery.setPage(page);
        courseAssessquery.setPagesize(pageSize);
        Page pageInfo = new Page();
        if (StringUtils.isNotEmpty(courseAssess.getCourseId())) {
            courseAssessquery.setCourseId("%" + courseAssess.getCourseId() + "%");
        }
        if (StringUtils.isNotEmpty(courseAssess.getCourseName())) {
            courseAssessquery.setCourseName("%" + courseAssess.getCourseName() + "%");
        }
        if (StringUtils.isNotEmpty(courseAssess.getUserName())) {
            courseAssessquery.setUserName("%" + courseAssess.getUserName() + "%");
        }
        queryWrapper.orderByAsc("created_time");
        List<CourseAssess> getList = courseAssessMapper.queryPurByPage(courseAssessquery);
        Page<CourseAssess> list = courseAssessMapper.selectPage(pageInfo,queryWrapper);
        for (CourseAssess assess : getList) {
            System.out.println(assess.getAssessLevel());
        }
        if (getList != null && !getList.isEmpty()) {
            for (CourseAssess cour : getList) {
                if (StringUtils.isNotEmpty(cour.getUserId())) {
                    QueryWrapper query = new QueryWrapper();
                    query.eq("ID",cour.getUserId());
                    SysUser getUser = sysUserMapper.selectById(cour.getUserId());
                    if (getUser != null) {
                        cour.setUserName(getUser.getUname());
                    }
                }
            }
        }
        list.setRecords(getList);
        list.setTotal(getList.size());
        return Ret.ok().setData(list);
    }

}
