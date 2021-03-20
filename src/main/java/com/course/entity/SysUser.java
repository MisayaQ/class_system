package com.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 用户名
     */
    @TableField("uname")
    private String uname;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("mobile_phone")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 用户角色 1学生，2教师，3超级管理员
     */
    @TableField("user_role")
    private String userRole;

    /**
     * 删除标识
     */
    @TableField("valid_flag")
    private Integer validFlag;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @TableField("main_course")
    private String mainCourse;

    @TableField("resume")
    private String resume;

    @TableField("signature")
    private String signature;

}
