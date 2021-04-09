package com.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.course.base.Ret;
import com.course.entity.SysSchool;
import com.course.mapper.SysSchoolMapper;
import com.course.service.ISysSchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-02-12
 */
@Service
public class SysSchoolServiceImpl extends ServiceImpl<SysSchoolMapper, SysSchool> implements ISysSchoolService {

    @Autowired
    SysSchoolMapper sysSchoolMapper;

    @Override
    public Ret updateSchool(SysSchool sysSchool) {
        if (StringUtils.isNotEmpty(sysSchool.getSchoolName())) {
            // 查询账号是否已存在
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("school_name", sysSchool.getSchoolName());
            List<SysSchool> getList = sysSchoolMapper.selectList(queryWrapper);
            if (getList != null && !getList.isEmpty()) {
                if (getList.size() > 1 || (getList.size() == 1 && !sysSchool.getId().equals(getList.get(0).getId()))) {
                    return Ret.error().setMsg("学校已存在");
                }
            }
            sysSchool.setUpdatedTime(LocalDateTime.now());
            List<String> properties = sysSchool.getSchoolProperties();
            String property = String.join(",", properties);
            sysSchool.setSchoolProperty(property);
            int result = sysSchoolMapper.updateById(sysSchool);
            if (result>0) {
                return Ret.ok().setData(sysSchool);
            } else {
                return Ret.error().setMsg("修改失败");
            }
        } else {
            return Ret.error().setMsg("学校名不能为空");
        }
    }
}
