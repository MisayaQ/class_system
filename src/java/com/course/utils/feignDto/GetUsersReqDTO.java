package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class GetUsersReqDTO {
    private List<Long> deptIds;

    private List<Long> roleIds;

    private Long corpId;
}
