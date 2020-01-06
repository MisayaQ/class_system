package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupDismissRDTO {

    private String corpId;

    private String operationId;

    private String groupId;
}
