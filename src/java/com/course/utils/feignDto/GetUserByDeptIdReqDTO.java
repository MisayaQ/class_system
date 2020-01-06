package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.util.List;

@Getter
@Setter
@ApiModel("企业部门所有用户列表查询参数")
public class GetUserByDeptIdReqDTO {

    @ApiModelProperty(value = "企业id", position = 1)
    @Order(1)
    private Long corpId;

    @ApiModelProperty(value = "部门id集合", position = 2)
    @Order(2)
    private List<Long> deptIds;

}
