package com.course.utils.feignDto;

import java.util.List;

public class MTRoleUserInfoRspDTO {

    /**
     * accountId : 441
     * userId : 1042237505699553281
     * userName : 李大勤
     * deptName : 美腾智冠信息事业部
     * jobNumber :
     * controlRange : 智能工厂研发部,经营财务部
     * deptList : [{"id":"35","corpId":null,"outerId":null,"name":"智能工厂研发部","pid":null,"pids":null,"isCreateImgroup":null,"sort":null,"isBiz":null,"path":null,"creator":null,"createAt":null,"updator":null,"updateAt":null},{"id":"183","corpId":null,"outerId":null,"name":"经营财务部","pid":null,"pids":null,"isCreateImgroup":null,"sort":null,"isBiz":null,"path":null,"creator":null,"createAt":null,"updator":null,"updateAt":null}]
     * isAll : 0
     */

    private String accountId;

    private String userId;

    private String userName;

    private String deptName;

    private String jobNumber;

    private String controlRange;

    private String isAll;

    private List<DeptListBean> deptList;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getControlRange() {
        return controlRange;
    }

    public void setControlRange(String controlRange) {
        this.controlRange = controlRange;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public List<DeptListBean> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptListBean> deptList) {
        this.deptList = deptList;
    }

    public static class DeptListBean {
        /**
         * id : 35
         * corpId : null
         * outerId : null
         * name : 智能工厂研发部
         * pid : null
         * pids : null
         * isCreateImgroup : null
         * sort : null
         * isBiz : null
         * path : null
         * creator : null
         * createAt : null
         * updator : null
         * updateAt : null
         */

        private String id;

        private Object corpId;

        private Object outerId;

        private String name;

        private Object pid;

        private Object pids;

        private Object isCreateImgroup;

        private Object sort;

        private Object isBiz;

        private Object path;

        private Object creator;

        private Object createAt;

        private Object updator;

        private Object updateAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getCorpId() {
            return corpId;
        }

        public void setCorpId(Object corpId) {
            this.corpId = corpId;
        }

        public Object getOuterId() {
            return outerId;
        }

        public void setOuterId(Object outerId) {
            this.outerId = outerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPid() {
            return pid;
        }

        public void setPid(Object pid) {
            this.pid = pid;
        }

        public Object getPids() {
            return pids;
        }

        public void setPids(Object pids) {
            this.pids = pids;
        }

        public Object getIsCreateImgroup() {
            return isCreateImgroup;
        }

        public void setIsCreateImgroup(Object isCreateImgroup) {
            this.isCreateImgroup = isCreateImgroup;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getIsBiz() {
            return isBiz;
        }

        public void setIsBiz(Object isBiz) {
            this.isBiz = isBiz;
        }

        public Object getPath() {
            return path;
        }

        public void setPath(Object path) {
            this.path = path;
        }

        public Object getCreator() {
            return creator;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
        }

        public Object getCreateAt() {
            return createAt;
        }

        public void setCreateAt(Object createAt) {
            this.createAt = createAt;
        }

        public Object getUpdator() {
            return updator;
        }

        public void setUpdator(Object updator) {
            this.updator = updator;
        }

        public Object getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(Object updateAt) {
            this.updateAt = updateAt;
        }
    }
}
