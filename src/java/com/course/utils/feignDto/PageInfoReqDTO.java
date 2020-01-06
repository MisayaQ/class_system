package com.course.utils.feignDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页参数接收实体
 */
@Data
public class PageInfoReqDTO {

    @ApiModelProperty(value = "当前页")
    private int page;

    @ApiModelProperty(value = "每页数量")
    private int pageSize;
}
