package com.course.utils.feignDto;

import java.util.List;

public class MTUserInfoRspDTO {

    /**
     * id : 1042237494349766657
     * corpId : 6
     * outerId : null
     * accountId : 290
     * name : 任少龙
     * mobile : 17694804822
     * tel :
     * email :
     * status : 1
     * jobNumber :
     * workPlace :
     * hiredDate : 1537286400000
     * leaveDate : 1537286400000
     * isHideMobile : 0
     * remark :
     * depts : [{"id":"197","corpId":"6","outerId":"张淑强","name":"美腾智冠信息事业部","pid":"1042237414280511489","pids":"0,1042237414280511489","isCreateImgroup":1,"sort":95,"isBiz":0,"path":[{"id":"1042237414280511489","corpId":"6","outerId":null,"name":"天津美腾科技有限公司","pid":"0","pids":"0","isCreateImgroup":0,"sort":0,"isBiz":0,"path":null,"managers":null,"userCnt":0,"creator":"1","createAt":1559009944000,"updator":"123","updateAt":1559009928000,"position":null,"flag":null,"group":null,"owner":null,"groupMembers":null},{"id":"197","corpId":"6","outerId":"张淑强","name":"美腾智冠信息事业部","pid":"1042237414280511489","pids":"0,1042237414280511489","isCreateImgroup":1,"sort":95,"isBiz":0,"path":null,"managers":null,"userCnt":0,"creator":"1","createAt":1559714861000,"updator":"123","updateAt":1559714413000,"position":null,"flag":null,"group":null,"owner":null,"groupMembers":null}],"managers":null,"userCnt":0,"creator":"1","createAt":1559714861000,"updator":"123","updateAt":1559714413000,"position":null,"flag":null,"group":null,"owner":null,"groupMembers":null}]
     * imAccount : renshaolong_test
     * creator : 1
     * createAt : 1537323691000
     * updator : 123
     * updateAt : 1558325453000
     * avatar : http://zx-zgiot-002.oss-cn-qingdao.aliyuncs.com/image/72d601089a1e41cba5cb87ad0c53eeb0.png
     * roles : [{"id":"1128488322302513154","name":"事业部总经理","corpId":null,"creator":null,"createAt":null,"updator":null,"updateAt":null}]
     * userSuperiorsInfo : {"id":"1042237477954232321","name":"李太友","mobile":"18802239698","tel":"","email":"","accountId":"133","status":1}
     */

    private String id;

    private String corpId;

    private Object outerId;

    private String accountId;

    private String name;

    private String mobile;

    private String tel;

    private String email;

    private int status;

    private String jobNumber;

    private String workPlace;

    private String hiredDate;

    private long leaveDate;

    private int isHideMobile;

    private String remark;

    private String imAccount;

    private long creator;

    private long createAt;

    private long updator;

    private long updateAt;

    private String avatar;

    private UserSuperiorsInfoBean userSuperiorsInfo;

    private List<DeptsBean> depts;

    private List<RolesBean> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Object getOuterId() {
        return outerId;
    }

    public void setOuterId(Object outerId) {
        this.outerId = outerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    public long getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(long leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getIsHideMobile() {
        return isHideMobile;
    }

    public void setIsHideMobile(int isHideMobile) {
        this.isHideMobile = isHideMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImAccount() {
        return imAccount;
    }

    public void setImAccount(String imAccount) {
        this.imAccount = imAccount;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdator() {
        return updator;
    }

    public void setUpdator(long updator) {
        this.updator = updator;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserSuperiorsInfoBean getUserSuperiorsInfo() {
        return userSuperiorsInfo;
    }

    public void setUserSuperiorsInfo(UserSuperiorsInfoBean userSuperiorsInfo) {
        this.userSuperiorsInfo = userSuperiorsInfo;
    }

    public List<DeptsBean> getDepts() {
        return depts;
    }

    public void setDepts(List<DeptsBean> depts) {
        this.depts = depts;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public static class UserSuperiorsInfoBean {
        /**
         * id : 1042237477954232321
         * name : 李太友
         * mobile : 18802239698
         * tel :
         * email :
         * accountId : 133
         * status : 1
         */

        private String id;

        private String name;

        private String mobile;

        private String tel;

        private String email;

        private String accountId;

        private int status;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class DeptsBean {
        /**
         * id : 197
         * corpId : 6
         * outerId : 张淑强
         * name : 美腾智冠信息事业部
         * pid : 1042237414280511489
         * pids : 0,1042237414280511489
         * isCreateImgroup : 1
         * sort : 95
         * isBiz : 0
         * path : [{"id":"1042237414280511489","corpId":"6","outerId":null,"name":"天津美腾科技有限公司","pid":"0","pids":"0","isCreateImgroup":0,"sort":0,"isBiz":0,"path":null,"managers":null,"userCnt":0,"creator":"1","createAt":1559009944000,"updator":"123","updateAt":1559009928000,"position":null,"flag":null,"group":null,"owner":null,"groupMembers":null},{"id":"197","corpId":"6","outerId":"张淑强","name":"美腾智冠信息事业部","pid":"1042237414280511489","pids":"0,1042237414280511489","isCreateImgroup":1,"sort":95,"isBiz":0,"path":null,"managers":null,"userCnt":0,"creator":"1","createAt":1559714861000,"updator":"123","updateAt":1559714413000,"position":null,"flag":null,"group":null,"owner":null,"groupMembers":null}]
         * managers : null
         * userCnt : 0
         * creator : 1
         * createAt : 1559714861000
         * updator : 123
         * updateAt : 1559714413000
         * position : null
         * flag : null
         * group : null
         * owner : null
         * groupMembers : null
         */

        private String id;

        private String corpId;

        private String outerId;

        private String name;

        private String pid;

        private String pids;

        private int isCreateImgroup;

        private int sort;

        private int isBiz;

        private Object managers;

        private long userCnt;

        private String creator;

        private long createAt;

        private String updator;

        private long updateAt;

        private Object position;

        private Object flag;

        private Object group;

        private Object owner;

        private Object groupMembers;

        private List<PathBean> path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        public String getOuterId() {
            return outerId;
        }

        public void setOuterId(String outerId) {
            this.outerId = outerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPids() {
            return pids;
        }

        public void setPids(String pids) {
            this.pids = pids;
        }

        public int getIsCreateImgroup() {
            return isCreateImgroup;
        }

        public void setIsCreateImgroup(int isCreateImgroup) {
            this.isCreateImgroup = isCreateImgroup;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIsBiz() {
            return isBiz;
        }

        public void setIsBiz(int isBiz) {
            this.isBiz = isBiz;
        }

        public Object getManagers() {
            return managers;
        }

        public void setManagers(Object managers) {
            this.managers = managers;
        }

        public long getUserCnt() {
            return userCnt;
        }

        public void setUserCnt(long userCnt) {
            this.userCnt = userCnt;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public long getCreateAt() {
            return createAt;
        }

        public void setCreateAt(long createAt) {
            this.createAt = createAt;
        }

        public String getUpdator() {
            return updator;
        }

        public void setUpdator(String updator) {
            this.updator = updator;
        }

        public long getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(long updateAt) {
            this.updateAt = updateAt;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getFlag() {
            return flag;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public Object getGroup() {
            return group;
        }

        public void setGroup(Object group) {
            this.group = group;
        }

        public Object getOwner() {
            return owner;
        }

        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public Object getGroupMembers() {
            return groupMembers;
        }

        public void setGroupMembers(Object groupMembers) {
            this.groupMembers = groupMembers;
        }

        public List<PathBean> getPath() {
            return path;
        }

        public void setPath(List<PathBean> path) {
            this.path = path;
        }

        public static class PathBean {
            /**
             * id : 1042237414280511489
             * corpId : 6
             * outerId : null
             * name : 天津美腾科技有限公司
             * pid : 0
             * pids : 0
             * isCreateImgroup : 0
             * sort : 0
             * isBiz : 0
             * path : null
             * managers : null
             * userCnt : 0
             * creator : 1
             * createAt : 1559009944000
             * updator : 123
             * updateAt : 1559009928000
             * position : null
             * flag : null
             * group : null
             * owner : null
             * groupMembers : null
             */

            private String id;

            private String corpId;

            private Object outerId;

            private String name;

            private String pid;

            private String pids;

            private int isCreateImgroup;

            private int sort;

            private int isBiz;

            private Object path;

            private Object managers;

            private long userCnt;

            private String creator;

            private long createAt;

            private String updator;

            private long updateAt;

            private Object position;

            private Object flag;

            private Object group;

            private Object owner;

            private Object groupMembers;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCorpId() {
                return corpId;
            }

            public void setCorpId(String corpId) {
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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPids() {
                return pids;
            }

            public void setPids(String pids) {
                this.pids = pids;
            }

            public int getIsCreateImgroup() {
                return isCreateImgroup;
            }

            public void setIsCreateImgroup(int isCreateImgroup) {
                this.isCreateImgroup = isCreateImgroup;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIsBiz() {
                return isBiz;
            }

            public void setIsBiz(int isBiz) {
                this.isBiz = isBiz;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public Object getManagers() {
                return managers;
            }

            public void setManagers(Object managers) {
                this.managers = managers;
            }

            public long getUserCnt() {
                return userCnt;
            }

            public void setUserCnt(long userCnt) {
                this.userCnt = userCnt;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public long getCreateAt() {
                return createAt;
            }

            public void setCreateAt(long createAt) {
                this.createAt = createAt;
            }

            public String getUpdator() {
                return updator;
            }

            public void setUpdator(String updator) {
                this.updator = updator;
            }

            public long getUpdateAt() {
                return updateAt;
            }

            public void setUpdateAt(long updateAt) {
                this.updateAt = updateAt;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public Object getFlag() {
                return flag;
            }

            public void setFlag(Object flag) {
                this.flag = flag;
            }

            public Object getGroup() {
                return group;
            }

            public void setGroup(Object group) {
                this.group = group;
            }

            public Object getOwner() {
                return owner;
            }

            public void setOwner(Object owner) {
                this.owner = owner;
            }

            public Object getGroupMembers() {
                return groupMembers;
            }

            public void setGroupMembers(Object groupMembers) {
                this.groupMembers = groupMembers;
            }
        }
    }

    public static class RolesBean {
        /**
         * id : 1128488322302513154
         * name : 事业部总经理
         * corpId : null
         * creator : null
         * createAt : null
         * updator : null
         * updateAt : null
         */

        private String id;

        private String name;

        private Object corpId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCorpId() {
            return corpId;
        }

        public void setCorpId(Object corpId) {
            this.corpId = corpId;
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
