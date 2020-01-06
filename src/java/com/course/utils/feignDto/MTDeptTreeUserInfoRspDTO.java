package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;
@Data
public class MTDeptTreeUserInfoRspDTO {

    private String deptName;

    private List<Long> childIds;

    private List<MTDeptTreeUserInfoRspDTO> childDeptTreeResDTOS;

    private List<UserInfoDTO> userInfoList;

    private String deptId;

    private String pid;
}
