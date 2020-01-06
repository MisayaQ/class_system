package com.course.service.impl;

import com.course.entity.SysUser;
import com.course.mapper.SysUserMapper;
import com.course.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理  服务实现类
 * </p>
 *
 * @author test
 * @since 2020-01-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
