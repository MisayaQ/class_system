package com.course.utils.feignDto;

import lombok.Data;

/**
 * 表单数据实体
 */
@Data
public class FormVariablesReqDTO {

    //字段id
    private String fieldId;
    //字段值
    private String fieldValue;
}
