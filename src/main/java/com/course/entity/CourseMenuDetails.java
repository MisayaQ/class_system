package com.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程目录-详情关联表 
 * </p>
 *
 * @author test
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_menu_details")
public class CourseMenuDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 目录ID
     */
    @TableField("menu_id")
    private String menuId;

    /**
     * 课程ID
     */
    @TableField("details_id")
    private String detailsId;


}
