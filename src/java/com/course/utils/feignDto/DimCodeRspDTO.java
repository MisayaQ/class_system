package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;
@Data
public class DimCodeRspDTO {

    private String code;

    private Long ctime;

    private data data;

    private String message;

    @Data
    private class data{

        private  List<DimCode> rows;

        private Integer total;

        @Data
        private class DimCode{
            private String code;

            private String id;

            private String name;
        }
    }

}
