package com.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author test
 * @since 2020-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_school")
public class SysSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private String id;
    /**
     * 学校名称
     */
    @TableField("school_name")
    private String schoolName;

    /**
     * 院校属性
     */
    @TableField("school_property")
    private String schoolProperty;

    /**
     * 院校类型
     */
    @TableField("school_type")
    private String schoolType;

    /**
     * 所在城市
     */
    @TableField("city")
    private String city;

    /**
     * 多余字段
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;


    @TableField("valid_flag")
    private int validFlag;

    @TableField(exist = false)
    private List<String> schoolProperties;

}
