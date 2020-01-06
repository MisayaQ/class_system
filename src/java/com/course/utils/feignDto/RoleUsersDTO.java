package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class RoleUsersDTO {

    private String roleId;

    private String roleName;

    private List<MTUserInfoRspDTO> mtUserInfoRspDTOS;
}
