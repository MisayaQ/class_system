package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 群组减人
 */
@Data
@Builder
public class GroupQuitPersonRDTO {

    private String groupId;

    private List<String> accountIds;

    private String corpId;

    //1:主动退群；2:踢出群；3:群加人撤销
    private int type;
}
