package com.course.utils.feignDto;

import lombok.Data;

@Data
public class RoleInfoRspDTO {

    private String id;

    private String name;

    private String cropId;

    private long creator;

    private String createAt;

    private long updator;

    private String updateAt;
}
