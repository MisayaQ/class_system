package com.course.utils.feignDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * <p>
 * 企业部门单个查询返回值
 * </p>
 *
 * @author yuechen
 * @since 2018-08-17
 */
@Getter
@Setter
@ApiModel("企业部门单个查询返回值")
public class DeptGetRspDTO{


    @ApiModelProperty(value = "企业部门id", position = 0)
    @Order(0)
    private String id;

    @ApiModelProperty(value = "企业id", position = 1)
    @Order(1)
    private String corpId;

    @ApiModelProperty(value = "部门企业内唯一标识, 外部系统用", position = 2)
    @Order(2)
    private String outerId;

    @ApiModelProperty(value = "名称", position = 3)
    @Order(3)
    private String name;

    @ApiModelProperty(value = "父部门id", position = 4)
    @Order(4)
    private String pid;

    @ApiModelProperty(value = "所有父部门id, 逗号分隔, id长度17,200最多11级", position = 5)
    @Order(5)
    private String pids;

    @ApiModelProperty(value = "是否创建im聊天组(0-否, 1-是)", position = 6)
    @Order(6)
    private Integer isCreateImgroup;

    @ApiModelProperty(value = "同级部门顺序", position = 7)
    @Order(7)
    private Integer sort;

    @ApiModelProperty(value = "是否是事业部(0-否, 1-是)", position = 8)
    @Order(8)
    private Integer isBiz;

    @ApiModelProperty(value = "全路径", position = 9)
    @Order(9)
    private List<DeptGetRspDTO> path;

    @ApiModelProperty(value = "部门人数", position = 11)
    @Order(11)
    private int userCnt;

    @ApiModelProperty(value = "创建人账号id", position = 30)
    @Order(30)
    private String creator;

    @ApiModelProperty(value = "创建时间", position = 31)
    @Order(32)
    private Long createAt;

    @ApiModelProperty(value = "更新人账号id", position = 34)
    @Order(34)
    private String updator;

    @ApiModelProperty(value = "更新时间", position = 35)
    @Order(35)
    private Long updateAt;

    @ApiModelProperty(value = "职位", position = 36)
    @Order(36)
    private String position;
    @ApiModelProperty(value = "是否有子部门", position = 36)
    @Order(37)
    private String flag;



}
