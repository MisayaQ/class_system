package com.course.utils.feignDto;

import java.util.List;

public class MTMyInfoRspDTO {

    private String access_token;

    private String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    /**
     * id : 1139442477209821186
     * mobile : 15488888569
     * nickName : 任务测试人员1
     * avatar : https://zx-zgiot-002.oss-cn-qingdao.aliyuncs.com/file/778c8b6932ba446fb0126a9819b5eb24.jpg
     * imAccount : 1139442477209821186
     * email :
     * remark :
     * gender :
     * birthday : null
     * status : 1
     * isManager : 0
     * manageCorps : []
     * corpUsers : [{"id":"1139442477000105985","corpId":"6","outerId":null,"accountId":"1139442477209821186","name":"任务测试人员1","mobile":"15488888569","tel":"","email":"","status":0,"jobNumber":"","workPlace":"","hiredDate":1560441600000,"leaveDate":null,"isHideMobile":0,"remark":"","isManager":null,"corp":{"id":"6","name":"天津美腾科技有限公司","shortName":"美腾科技","code":"200","remark":"李","logo":"https://zx-zgiot-002.oss-cn-qingdao.aliyuncs.com/image/bb0e866ad0f64f73a71f2d41d3aa0377.jpg","userLimit":1000,"status":1,"depts":[{"id":"76","corpId":"6","outerId":null,"name":"应用二组","pid":"35","pids":"0,1042237414280511489,197,36,80,35,80,35","isCreateImgroup":0,"sort":0,"isBiz":1,"creator":"1","createAt":1559193749000,"updator":"451","updateAt":1541999417000}],"rootDeptId":"1042237414280511489","userCnt":0,"creator":1,"createAt":"1560309071000","updator":"134","updateAt":1560309071000},"creator":"123","createAt":1560499231000,"updator":"123","updateAt":1560499231000}]
     * creator : 123
     * createAt : 1560499231000
     * updator : 123
     * updateAt : 1560499378000
     * imToken : w5C+Lu2Og0jlD7+DBzLGYlUR853ik6bE9AS7tJnvLu0K1dyEMRsrPjKWdKJJSu+6CSTdVXVfxM9o5D+UIvRmo/Sp3RsHsFQDZBNcJ2hN2GW4Au6Ob4M6dQ==
     * appKey : x4vkb1qpxzlzk
     * appSecret : GJ1lUjPYwXT
     */

    private String id;

    private String mobile;

    private String nickName;

    private String avatar;

    private String imAccount;

    private String email;

    private String remark;

    private String gender;

    private Object birthday;

    private int status;

    private int isManager;

    private String creator;

    private long createAt;

    private String updator;

    private long updateAt;

    private String imToken;

    private String appKey;

    private String appSecret;

    private List<?> manageCorps;

    private List<CorpUsersBean> corpUsers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImAccount() {
        return imAccount;
    }

    public void setImAccount(String imAccount) {
        this.imAccount = imAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsManager() {
        return isManager;
    }

    public void setIsManager(int isManager) {
        this.isManager = isManager;
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

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public List<?> getManageCorps() {
        return manageCorps;
    }

    public void setManageCorps(List<?> manageCorps) {
        this.manageCorps = manageCorps;
    }

    public List<CorpUsersBean> getCorpUsers() {
        return corpUsers;
    }

    public void setCorpUsers(List<CorpUsersBean> corpUsers) {
        this.corpUsers = corpUsers;
    }

    public static class CorpUsersBean {
        /**
         * id : 1139442477000105985
         * corpId : 6
         * outerId : null
         * accountId : 1139442477209821186
         * name : 任务测试人员1
         * mobile : 15488888569
         * tel :
         * email :
         * status : 0
         * jobNumber :
         * workPlace :
         * hiredDate : 1560441600000
         * leaveDate : null
         * isHideMobile : 0
         * remark :
         * isManager : null
         * corp : {"id":"6","name":"天津美腾科技有限公司","shortName":"美腾科技","code":"200","remark":"李","logo":"https://zx-zgiot-002.oss-cn-qingdao.aliyuncs.com/image/bb0e866ad0f64f73a71f2d41d3aa0377.jpg","userLimit":1000,"status":1,"depts":[{"id":"76","corpId":"6","outerId":null,"name":"应用二组","pid":"35","pids":"0,1042237414280511489,197,36,80,35,80,35","isCreateImgroup":0,"sort":0,"isBiz":1,"creator":"1","createAt":1559193749000,"updator":"451","updateAt":1541999417000}],"rootDeptId":"1042237414280511489","userCnt":0,"creator":1,"createAt":"1560309071000","updator":"134","updateAt":1560309071000}
         * creator : 123
         * createAt : 1560499231000
         * updator : 123
         * updateAt : 1560499231000
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

        private long hiredDate;

        private Object leaveDate;

        private int isHideMobile;

        private String remark;

        private Object isManager;

        private CorpBean corp;

        private String creator;

        private long createAt;

        private String updator;

        private long updateAt;

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

        public long getHiredDate() {
            return hiredDate;
        }

        public void setHiredDate(long hiredDate) {
            this.hiredDate = hiredDate;
        }

        public Object getLeaveDate() {
            return leaveDate;
        }

        public void setLeaveDate(Object leaveDate) {
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

        public Object getIsManager() {
            return isManager;
        }

        public void setIsManager(Object isManager) {
            this.isManager = isManager;
        }

        public CorpBean getCorp() {
            return corp;
        }

        public void setCorp(CorpBean corp) {
            this.corp = corp;
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

        public static class CorpBean {
            /**
             * id : 6
             * name : 天津美腾科技有限公司
             * shortName : 美腾科技
             * code : 200
             * remark : 李
             * logo : https://zx-zgiot-002.oss-cn-qingdao.aliyuncs.com/image/bb0e866ad0f64f73a71f2d41d3aa0377.jpg
             * userLimit : 1000
             * status : 1
             * depts : [{"id":"76","corpId":"6","outerId":null,"name":"应用二组","pid":"35","pids":"0,1042237414280511489,197,36,80,35,80,35","isCreateImgroup":0,"sort":0,"isBiz":1,"creator":"1","createAt":1559193749000,"updator":"451","updateAt":1541999417000}]
             * rootDeptId : 1042237414280511489
             * userCnt : 0
             * creator : 1
             * createAt : 1560309071000
             * updator : 134
             * updateAt : 1560309071000
             */

            private String id;

            private String name;

            private String shortName;

            private String code;

            private String remark;

            private String logo;

            private int userLimit;

            private int status;

            private String rootDeptId;

            private int userCnt;

            private long creator;

            private String createAt;

            private String updator;

            private long updateAt;

            private List<DeptsBean> depts;

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

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getUserLimit() {
                return userLimit;
            }

            public void setUserLimit(int userLimit) {
                this.userLimit = userLimit;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRootDeptId() {
                return rootDeptId;
            }

            public void setRootDeptId(String rootDeptId) {
                this.rootDeptId = rootDeptId;
            }

            public int getUserCnt() {
                return userCnt;
            }

            public void setUserCnt(int userCnt) {
                this.userCnt = userCnt;
            }

            public long getCreator() {
                return creator;
            }

            public void setCreator(long creator) {
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

            public long getUpdateAt() {
                return updateAt;
            }

            public void setUpdateAt(long updateAt) {
                this.updateAt = updateAt;
            }

            public List<DeptsBean> getDepts() {
                return depts;
            }

            public void setDepts(List<DeptsBean> depts) {
                this.depts = depts;
            }

            public static class DeptsBean {
                /**
                 * id : 76
                 * corpId : 6
                 * outerId : null
                 * name : 应用二组
                 * pid : 35
                 * pids : 0,1042237414280511489,197,36,80,35,80,35
                 * isCreateImgroup : 0
                 * sort : 0
                 * isBiz : 1
                 * creator : 1
                 * createAt : 1559193749000
                 * updator : 451
                 * updateAt : 1541999417000
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

                private String creator;

                private long createAt;

                private String updator;

                private long updateAt;

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
            }
        }
    }
}
