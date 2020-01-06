package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupCreateRDTO {

    private String name;

    private String corpId;

    private String deptId;

    private int type;

    private int onlyOwnerManage;

    private int onlyOwnerAtAll;

    private int onlyOwnerUpdate;

    private int freshViewHistory;

    private int userLimit;

    private String owner;

    private String creator;

    private List<String> groupAdmins;

    private List<String> groupMembers;

    private String comment;
}
