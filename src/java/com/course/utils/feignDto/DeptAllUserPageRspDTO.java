package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class DeptAllUserPageRspDTO<T> {

    private int pageNum;

    private int pageSize;

    private int total;

    private List<T> list;
}
