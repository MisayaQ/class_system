package com.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_menu")
public class CourseMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

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

    @TableField("course_id")
    private String courseId;

    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @TableField("teacher_id")
    private String teacherId;

    @TableField("menu_descrip")
    private String menuDescrip;

    @TableField(exist = false)
    private String teacherName;

    @TableField("menu_state")
    private String menuState;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private String timeUnite;

}
