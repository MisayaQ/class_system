package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class MTUserGetsReqDTO {

    private String corpId;

    private List<String> ids;

}
