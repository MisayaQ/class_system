package com.course.utils.feignDto;

import lombok.Data;

import java.util.List;

@Data
public class MTEquipmentInfoDTO{
    private String corpId;

    private long createTime;

    private String createUser;

    private String equipmentCode;

    private EquipmentSpatialBean equipmentSpatial;

    private String equipmentName;

    private String equipmentNote;

    private String equipmentUuid;

    private int id;

    private String manufacturerUuid;

    private String organizationCode;

    private String principalsUuid;

    private String specifications;

    private int status;

    private String tagUid;

    private long updateTime;

    private long useTime;

    private List<String> organizationCodeList;

    private List<String> principalsUuidList;

    @Data
    private class EquipmentSpatialBean {

        private int canDelete;

        private long createTime;

        private String createUserUuid;

        private int id;

        private int isEnable;

        private String spaCode;

        private String spaName;

        private String spaUuid;

        private long updateTime;

        private String updateUserUuid;

        private List<EquipmentPropertiesBean> equipmentProperties;

        @Data
        private class EquipmentPropertiesBean {

            private String dimCode;

            private boolean flag;

            private int id;

            private int isEnable;

            private String propName;

            private String propUuid;

            private String value;

        }
    }
}
