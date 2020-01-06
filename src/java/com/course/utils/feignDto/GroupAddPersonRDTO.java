package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 群组加人
 */
@Data
@Builder
public class GroupAddPersonRDTO {

    private String groupId;

    private String name;

    private String corpId;

    private List<String> accountIds;

    private String operationId;
}
