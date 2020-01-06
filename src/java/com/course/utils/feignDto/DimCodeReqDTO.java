package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DimCodeReqDTO {

    private String name;

    private Integer pageNum;

    private Integer pageSize;
}
