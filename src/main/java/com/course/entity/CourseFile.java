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
 * 课程附件表 
 * </p>
 *
 * @author test
 * @since 2020-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_file")
public class CourseFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @TableField("details_id")
    private String detailsId;

    /**
     * 附件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 原文件名
     */
    @TableField("file_old_name")
    private String fileOldName;

    /**
     * 地址
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 状态
     */
    @TableField("show_flag")
    private String showFlag;

    /**
     * 任务内容
     */
    @TableField("file_content")
    private String fileContent;

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
