package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class MTUserGetPageListRspDTO {

    private int pageNum;

    private int pageSize;

    private int total;

    private List<UserGetPageListByDeptRspDTO> list;
}
