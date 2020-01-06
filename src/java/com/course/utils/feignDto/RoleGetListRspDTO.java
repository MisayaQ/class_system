package com.course.utils.feignDto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

@Getter
@Setter
@ApiModel("角色分页列表查询返回值")
public class RoleGetListRspDTO{

    @ApiModelProperty(value = "id", position = 1)
    @Order(0)
    private Long id;

    @ApiModelProperty(value = "", position = 2)
    @Order(1)
    private String name;

    @ApiModelProperty(value = "", position = 3)
    @Order(2)
    private Long corpId;

    @ApiModelProperty(value = "创建人账号id", position = 30)
    @Order(30)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 31)
    @Order(32)
    private String createAt;

    @ApiModelProperty(value = "更新人账号id", position = 34)
    @Order(34)
    private Long updator;

    @ApiModelProperty(value = "更新时间", position = 35)
    @Order(35)
    private String updateAt;

}
