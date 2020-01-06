package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

@Getter
@Setter
@ApiModel("所有用户返回值")
public class GetAllRspDTO {

    @ApiModelProperty(value = "用户名", position = 1)
    @Order(1)
    private String name;

    @ApiModelProperty(value = "im账号", position = 2)
    @Order(2)
    private String imAccount;

    @ApiModelProperty(value = "头像", position = 3)
    @Order(3)
    private String avatar;

    @ApiModelProperty(value = "账号id", position = 4)
    @Order(4)
    private String accountId;

    private String id;
}
