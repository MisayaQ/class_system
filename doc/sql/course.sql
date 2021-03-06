
CREATE TABLE `course_details`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `c_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `c_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程类别',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `teacher_ids` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲课教师',
  `count_class` int(5) NULL DEFAULT NULL COMMENT '课时数',
  `c_descript` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `remarks` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `valid_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程详情表 ' ROW_FORMAT = Dynamic;

CREATE TABLE `course_file`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `details_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `file_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件类型',
  `file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `file_old_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文件名',
  `file_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `show_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `file_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务内容',
  `valid_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程附件表 ' ROW_FORMAT = Dynamic;

CREATE TABLE `course_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `menu_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录名称',
  `menu_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目录类型',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `valid_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程目录 ' ROW_FORMAT = Dynamic;

CREATE TABLE `course_purchase`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `details_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '购买人id',
  `valid_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程购买记录表 ' ROW_FORMAT = Dynamic;

CREATE TABLE `sys_notice`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `valid_flag` int(1) NULL DEFAULT 0 COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告 ' ROW_FORMAT = Dynamic;

CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `account` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `uname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile_phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `user_role` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '用户角色 1学生，2教师，3超级管理员',
  `valid_flag` int(11) NULL DEFAULT NULL COMMENT '删除标识',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理 ' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `sys_count`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count_date` datetime NULL DEFAULT NULL,
  `counts` int(10) NULL DEFAULT NULL,
  `valid_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `sys_user_address`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_top` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `sys_school`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校名称',
  `school_property` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院校属性',
  `school_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院校类型',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多余字段',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `course_assess`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `course_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `assess_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价等级',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任课老师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE course_details ADD course_code varchar(255) COMMENT '课程代码';
ALTER TABLE course_details ADD course_prise int(5) COMMENT '价格';
ALTER TABLE course_details ADD is_top int(1) COMMENT '是否置顶';
ALTER TABLE course_details ADD sell_time datetime COMMENT '开售时间';
ALTER TABLE course_details ADD end_sell_time datetime COMMENT '结束售卖时间';
ALTER TABLE course_menu ADD course_id varchar(128) COMMENT '课程id';
ALTER TABLE course_details ADD tips varchar(255) COMMENT '听课须知';
ALTER TABLE course_menu ADD start_time datetime COMMENT '开始时间';
ALTER TABLE course_menu ADD end_time datetime COMMENT '结束时间';
ALTER TABLE course_menu ADD teacher_id varchar(50) COMMENT '任课老师';
ALTER TABLE course_menu ADD menu_descrip varchar(255) COMMENT '课程介绍';
ALTER TABLE sys_user ADD main_course varchar(255) COMMENT '主讲课程';
ALTER TABLE sys_user ADD resume varchar(255) COMMENT '讲课简历';
ALTER TABLE course_purchase ADD pur_address varchar(255) COMMENT '订单地址';
ALTER TABLE course_purchase ADD state varchar(255) COMMENT '购买结果状态1待付款2已付款3已发货4交易失败';
ALTER TABLE sys_user_address ADD receiver varchar(255) COMMENT '收货人';
ALTER TABLE sys_user_address ADD tele_number varchar(255) COMMENT '联系电话';
ALTER TABLE course_purchase ADD pur_user varchar(255) COMMENT '收货姓名';
ALTER TABLE course_purchase ADD pur_tel varchar(255) COMMENT '收货电话号';
ALTER TABLE course_menu ADD menu_state varchar(10) COMMENT '课程状态';
ALTER TABLE course_file ADD menu_id varchar(50) COMMENT '目录ID';
