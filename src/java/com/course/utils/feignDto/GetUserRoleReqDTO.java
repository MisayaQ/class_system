package com.course.utils.feignDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Z on 2019/3/6.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRoleReqDTO {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "企业id")
    private String corpId;

}
