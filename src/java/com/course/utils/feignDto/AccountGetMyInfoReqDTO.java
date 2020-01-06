package com.course.utils.feignDto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

@Getter
@Setter
@ApiModel("当前账号信息查询参数值")
public class AccountGetMyInfoReqDTO{

    @ApiModelProperty(value = "网关传递的账号id", position = 0)
    @Order(1)
    private Long zxAccountId;
}
