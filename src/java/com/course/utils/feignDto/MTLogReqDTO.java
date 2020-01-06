package com.course.utils.feignDto;

import lombok.Data;

import java.util.Map;

@Data
public class MTLogReqDTO {

    private String content;

    private String corpId;

    private String eventCode;

    private String eventName;

    private String moduleCode;

    private String moduleName;

    private Integer optDuration;

    private String platform;

    private String properties;

    private Map<String, Object> propertyMap;

    private String userUuid;

    private String userZxId;

}
