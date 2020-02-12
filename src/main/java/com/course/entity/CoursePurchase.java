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
 * 课程购买记录表 
 * </p>
 *
 * @author test
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_purchase")
public class CoursePurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 课程id
     */
    @TableField("details_id")
    private String detailsId;

    /**
     * 购买人id
     */
    @TableField("user_id")
    private String userId;

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

    @TableField("pur_address")
    private String purAddress;

    @TableField("state")
    private String state;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private String time1;

    @TableField(exist = false)
    private String time2;

    @TableField("pur_user")
    private String purUser;

    @TableField("pur_tel")
    private String purTel;

    @TableField(exist = false)
    private Integer page;

    @TableField(exist = false)
    private Integer pagesize;
}
