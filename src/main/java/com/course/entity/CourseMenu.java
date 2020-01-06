package com.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程目录 
 * </p>
 *
 * @author test
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_menu")
public class CourseMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 目录名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 目录类型
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * 父节点
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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


}
