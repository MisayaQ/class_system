package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class MTGetUserByDeptIdsReqDTO {

    private String corpId;

    private List<String> deptIds;

}
