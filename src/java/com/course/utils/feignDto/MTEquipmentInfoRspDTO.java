package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

/**
 * 模糊查询设备返回实体类
 */
@Data
public class MTEquipmentInfoRspDTO {

    private String code;

    private Long ctime;

    private List<MTEquipmentInfoDTO> data;

    private String message;
}
