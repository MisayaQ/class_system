package com.course.utils.feignDto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 企业部门分页列表查询参数
 * </p>
 *
 * @author yuechen
 * @since 2018-08-17
 */
@Getter
@Setter
@ApiModel("企业部门分页列表查询参数")
public class DeptGetPageListReqDTO{


    @ApiModelProperty(value = "企业id", position = 1, required = true)
    @Order(1)
    @NotNull(message = "企业id不能为空")
    private Long corpId;

    @ApiModelProperty(value = "父部门id", position = 4, required = true)
    @Order(4)
    @NotNull(message = "父部门id不能为空")
    private Long pid;

    @Min(value = 1, message = "页数最小为{value}")
    @ApiModelProperty(value = "第几页", required = true)
    @NotNull(message = "页数不能为空")
    private Integer pageNum;

    @Min(value = 1, message = "每页记录数最小为{value}")
    @ApiModelProperty(value = "每页记录数", position = 4, required = true)
    @NotNull(message = "每页记录数不能为空")
    private Integer pageSize;
}
