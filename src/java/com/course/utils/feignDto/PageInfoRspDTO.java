package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

/**
 * 分页参数返回实体
 */
@Data
public class PageInfoRspDTO<T> {
    //当前页数据
    private List<T> rows;
    //总数
    private int total;

    private PageInfoRspDTO(List<T> rows, int total) {

        this.rows = rows;
        this.total = total;
    }

    public static <T> PageInfoRspDTO<T> create(List<T> rows, int total) {
        return new PageInfoRspDTO<>(rows, total);
    }
}
