package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * <p>
 * 企业用户单个查询返回值
 * </p>
 *
 * @author yuechen
 * @since 2018-08-18
 */
@Getter
@Setter
@ApiModel("企业用户单个查询返回值")
public class UserGetRspDTO{


    @ApiModelProperty(value = "企业用户id", position = 0)
    @Order(0)
    private Long id;

    @ApiModelProperty(value = "企业id", position = 1)
    @Order(1)
    private Long corpId;

    @ApiModelProperty(value = "用户企业内唯一标识, 外部系统用", position = 2)
    @Order(2)
    private String outerId;

    @ApiModelProperty(value = "账号id", position = 3)
    @Order(3)
    private String accountId;

    @ApiModelProperty(value = "姓名", position = 4)
    @Order(4)
    private String name;

    @ApiModelProperty(value = "手机号", position = 5)
    @Order(5)
    private String mobile;

    @ApiModelProperty(value = "电话", position = 6)
    @Order(6)
    private String tel;

    @ApiModelProperty(value = "企业邮箱", position = 7)
    @Order(7)
    private String email;

    @ApiModelProperty(value = "状态(0-未激活, 1-正常, 2-锁定, 3-离职)", position = 8)
    @Order(8)
    private Integer status;

    @ApiModelProperty(value = "工号", position = 9)
    @Order(9)
    private String jobNumber;

    @ApiModelProperty(value = "办公地点", position = 10)
    @Order(10)
    private String workPlace;

    @ApiModelProperty(value = "入职时间", position = 11)
    @Order(11)
    private String hiredDate;

    @ApiModelProperty(value = "离职时间", position = 12)
    @Order(12)
    private Long leaveDate;

    @ApiModelProperty(value = "是否隐藏手机号(0-否,1-是)", position = 14)
    @Order(14)
    private Integer isHideMobile;

    @ApiModelProperty(value = "备注", position = 15)
    @Order(15)
    private String remark;

    @ApiModelProperty(value = "所属部门", position = 16)
    @Order(16)
    private List<DeptGetRspDTO> depts;

    @ApiModelProperty(value = "im账号", position = 17)
    @Order(17)
    private String imAccount;

    @ApiModelProperty(value = "创建人账号id", position = 30)
    @Order(30)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 31)
    @Order(32)
    private Long createAt;

    @ApiModelProperty(value = "更新人账号id", position = 34)
    @Order(34)
    private Long updator;

    @ApiModelProperty(value = "更新时间", position = 35)
    @Order(35)
    private Long updateAt;

    @ApiModelProperty(value = "头像路径", position = 36)
    @Order(36)
    private String avatar;




}
