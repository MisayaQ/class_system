package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("通过用户名搜索(支持分页)参数")
public class SearchByNameReqDTO{

    @ApiModelProperty(value = "用户名", position = 1)
    @Order(1)
    private String name;

    @ApiModelProperty(value = "企业id", position = 2)
    @Order(2)
    private Long corpId;

    @Min(value = 1, message = "页数最小为{value}")
    @ApiModelProperty(value = "第几页", position = 3, required = true)
    @NotNull(message = "页数不能为空")
    private Integer pageNum;

    @Min(value = 1, message = "每页记录数最小为{value}")
    @ApiModelProperty(value = "每页记录数", position = 4, required = true)
    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
}
