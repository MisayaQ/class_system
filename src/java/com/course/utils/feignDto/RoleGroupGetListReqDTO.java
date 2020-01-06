package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("角色组&角色列表查询入参")
public class RoleGroupGetListReqDTO{


    @ApiModelProperty(value = "企业ID", position = 1)
    @Order(1)
    @NotNull(message = "corpId不能为空")
    private Long corpId;


}
