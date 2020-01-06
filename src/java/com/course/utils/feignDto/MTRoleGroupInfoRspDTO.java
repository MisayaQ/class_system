package com.course.utils.feignDto;

import java.util.List;

public class MTRoleGroupInfoRspDTO {

    /**
     * id : 1128488217616879618
     * name : 拜拜
     * corpId : 6
     * roles : [{"id":"1128488267411656706","name":"部长","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1128488267466182657"},{"id":"1128488322302513154","name":"事业部总经理","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1128488322373816322"},{"id":"1130685864499691521","name":"角色1","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1130685864529051650"},{"id":"1130685903745794049","name":"角色2","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1130685903749988354"},{"id":"1130685935211462657","name":"角色3","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1130685935219851265"},{"id":"1130685961195175937","name":"角色4","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1130685961203564546"},{"id":"1133639571700965377","name":"角色5","corpId":6,"creator":null,"createAt":null,"updator":null,"updateAt":null,"rgid":"1133639571705159681"}]
     */

    private String id;

    private String name;

    private String corpId;

    private List<RolesBean> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public static class RolesBean {
        /**
         * id : 1128488267411656706
         * name : 部长
         * corpId : 6
         * creator : null
         * createAt : null
         * updator : null
         * updateAt : null
         * rgid : 1128488267466182657
         */

        private String id;

        private String name;

        private String corpId;

        private String creator;

        private String createAt;

        private String updator;

        private String updateAt;

        private String rgid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        public Object getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdator() {
            return updator;
        }

        public void setUpdator(String updator) {
            this.updator = updator;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public String getRgid() {
            return rgid;
        }

        public void setRgid(String rgid) {
            this.rgid = rgid;
        }
    }
}
