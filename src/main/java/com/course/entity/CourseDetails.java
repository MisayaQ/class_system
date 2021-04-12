package com.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 课程详情表
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_details")
public class CourseDetails implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 课程名称
     */
    @TableField("c_name")
    private String cName;

    /**
     * 课程类别
     */
    @TableField("c_type")
    private String cType;

    /**
     * 开始时间
     */
    @TableField("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 讲课教师
     */
    @TableField("teacher_ids")
    private String teacherIds;

    /**
     * 课时数
     */
    @TableField("count_class")
    private Integer countClass;

    /**
     * 课程简介
     */
    @TableField("c_descript")
    private String cDescript;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    @TableField("course_code")
    private String courseCode;

    @TableField("course_prise")
    private Integer coursePrise;

    @TableField("is_top")
    private Integer isTop;

    @TableField("sell_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sellTime;

    @TableField("end_sell_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endSellTime;

    @TableField(exist = false)
    private String teacherNames;

    @TableField("tips")
    private String tips;

    @TableField(exist = false)
    private String deadLine;

}
