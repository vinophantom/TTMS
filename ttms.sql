DROP database IF EXISTS ttms;
CREATE database ttms DEFAULT CHARACTER SET 'utf8';
use ttms;
/*
Navicat MySQL Data Transfer

Source Server         : ttms
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : ttms

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-08-30 17:35:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `type` int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `permission` varchar(500) DEFAULT NULL COMMENT '授权(如：user:create)',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('3', '产品管理', '请求路径', '1', '3', null, null, 'product:list', '2017-07-12 15:15:59', '2017-07-21 11:16:10', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('4', '销售管理', '请求路径', '1', '4', null, null, null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('7', '供销管理', '请求路径', '1', '7', null, null, 'dist:list', '2017-07-12 15:15:59', '2017-07-21 11:16:55', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('8', '系统管理', '请求路径', '1', '8', null, null, 'sys:list', '2017-07-12 15:15:59', '2017-07-21 11:16:00', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('11', '项目信息', 'project/listUI.do', '1', '11', null, '3', 'product:project:view', '2017-07-12 15:15:59', '2017-07-21 17:35:34', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('12', '团信息', 'team/listUI.do', '1', '12', null, '3', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('13', '产品信息', 'product/listUI.do', '1', '13', null, '3', '', '2017-07-12 15:15:59', '2017-07-24 14:34:08', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('24', '系统配置', '请求路径', '1', '24', null, '8', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('25', '日志管理', '请求路径', '1', '25', null, '8', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('34', '分销商管理', '请求路径', '1', '34', null, '7', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('35', '订单管理', '请求路径', '1', '35', null, '4', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('41', '供应商管理', '请求路径', '1', '41', null, '7', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('44', '渠道商管理', '请求路径', '1', '44', null, '7', null, '2017-07-12 15:15:59', '2017-07-12 15:15:59', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('45', '用户管理', 'user/listUI.do', '1', '45', null, '8', 'sys:user:view', '2017-07-12 15:15:59', '2017-07-21 17:36:01', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('46', '菜单管理', 'menu/listUI.do', '1', '46', null, '8', 'sys:menu:view', '2017-07-12 15:15:59', '2017-07-21 17:36:16', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('47', '角色管理', 'role/listUI.do', '1', '47', null, '8', 'sys:role:view', '2017-07-12 15:15:59', '2017-07-21 17:38:03', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('48', '组织管理', '请求路径', '1', '48', null, '8', 'sys:org:view', '2017-07-12 15:15:59', '2017-07-21 18:37:57', 'admin', 'admin');
INSERT INTO `sys_menus` VALUES ('115', '查看', '', '2', '1', null, '46', 'sys:menu:view', '2017-07-13 16:33:41', '2017-07-21 11:09:05', null, null);
INSERT INTO `sys_menus` VALUES ('116', '新增', '', '2', '2', null, '46', 'sys:menu:add', '2017-07-13 16:34:02', '2017-07-21 11:09:22', null, null);
INSERT INTO `sys_menus` VALUES ('117', '修改', '', '2', '3', null, '46', 'sys:menu:update', '2017-07-13 16:34:25', '2017-07-21 11:09:45', null, null);
INSERT INTO `sys_menus` VALUES ('118', '删除', '', '2', '4', null, '46', 'sys:menu:delete', '2017-07-13 16:34:46', '2017-07-21 11:10:12', null, null);
INSERT INTO `sys_menus` VALUES ('119', '查看', '', '2', '1', null, '45', 'sys:user:view', '2017-07-13 16:35:05', '2017-07-21 11:12:46', null, null);
INSERT INTO `sys_menus` VALUES ('120', '查看', '', '2', '1', null, '47', 'sys:role:view', '2017-07-13 16:35:26', '2017-07-21 11:13:43', null, null);
INSERT INTO `sys_menus` VALUES ('126', '新增', '', '2', '2', null, '45', 'sys:user:add', '2017-07-21 11:11:34', '2017-07-21 11:11:34', null, null);
INSERT INTO `sys_menus` VALUES ('127', '修改', '', '2', '3', null, '45', 'sys:user:update', '2017-07-21 11:11:56', '2017-07-21 11:11:56', null, null);
INSERT INTO `sys_menus` VALUES ('128', '新增', '', '2', '2', null, '47', 'sys:role:add', '2017-07-21 11:14:24', '2017-07-21 11:14:24', null, null);
INSERT INTO `sys_menus` VALUES ('129', '修改', '', '2', '3', null, '47', 'sys:role:update', '2017-07-21 11:14:48', '2017-07-21 11:14:48', null, null);
INSERT INTO `sys_menus` VALUES ('130', '删除', '', '2', '4', null, '47', 'sys:role:delete', '2017-07-21 11:15:09', '2017-07-21 11:15:09', null, null);
INSERT INTO `sys_menus` VALUES ('135', '查询', '', '2', '1', null, '11', 'product:project:view', '2017-07-21 17:21:40', '2017-07-21 17:21:40', null, null);
INSERT INTO `sys_menus` VALUES ('136', '新增', '', '2', '2', null, '11', 'product:project:add', '2017-07-21 17:22:02', '2017-07-21 17:22:02', null, null);
INSERT INTO `sys_menus` VALUES ('137', '启用', '', '2', '3', null, '11', 'product:project:valid', '2017-07-21 17:22:23', '2017-07-21 17:22:23', null, null);
INSERT INTO `sys_menus` VALUES ('138', '禁用', '', '2', '4', null, '11', 'product:project:invalid', '2017-07-21 17:22:44', '2017-07-21 17:22:44', null, null);
INSERT INTO `sys_menus` VALUES ('139', '修改', '', '2', '5', null, '11', 'product:project:update', '2017-07-21 17:25:20', '2017-07-21 17:25:20', null, null);
INSERT INTO `sys_menus` VALUES ('140', '产品分类', 'type/listUI.do', '1', '30', null, '3', 'product:type:view', '2017-08-29 14:19:34', '2017-08-29 14:19:34', null, null);

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', '系统管理员', '系统管理员', '2017-07-13 17:44:11', '2017-08-29 14:28:45', 'admin', 'admin');
INSERT INTO `sys_roles` VALUES ('2', '产品经理', '产品经理', '2017-07-13 17:44:47', '2017-08-29 14:21:32', 'admin', 'admin');
INSERT INTO `sys_roles` VALUES ('38', '团负责人', '只能查看项目', '2017-07-21 17:09:23', '2017-08-29 14:31:07', null, null);

-- ----------------------------
-- Table structure for sys_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menus`;
CREATE TABLE `sys_role_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT 'ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1171 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menus
-- ----------------------------
INSERT INTO `sys_role_menus` VALUES ('1122', '2', '3');
INSERT INTO `sys_role_menus` VALUES ('1123', '2', '11');
INSERT INTO `sys_role_menus` VALUES ('1124', '2', '135');
INSERT INTO `sys_role_menus` VALUES ('1125', '2', '136');
INSERT INTO `sys_role_menus` VALUES ('1126', '2', '137');
INSERT INTO `sys_role_menus` VALUES ('1127', '2', '138');
INSERT INTO `sys_role_menus` VALUES ('1128', '2', '139');
INSERT INTO `sys_role_menus` VALUES ('1129', '2', '12');
INSERT INTO `sys_role_menus` VALUES ('1130', '2', '13');
INSERT INTO `sys_role_menus` VALUES ('1131', '2', '140');
INSERT INTO `sys_role_menus` VALUES ('1132', '1', '3');
INSERT INTO `sys_role_menus` VALUES ('1133', '1', '11');
INSERT INTO `sys_role_menus` VALUES ('1134', '1', '135');
INSERT INTO `sys_role_menus` VALUES ('1135', '1', '136');
INSERT INTO `sys_role_menus` VALUES ('1136', '1', '137');
INSERT INTO `sys_role_menus` VALUES ('1137', '1', '138');
INSERT INTO `sys_role_menus` VALUES ('1138', '1', '139');
INSERT INTO `sys_role_menus` VALUES ('1139', '1', '12');
INSERT INTO `sys_role_menus` VALUES ('1140', '1', '13');
INSERT INTO `sys_role_menus` VALUES ('1141', '1', '140');
INSERT INTO `sys_role_menus` VALUES ('1142', '1', '4');
INSERT INTO `sys_role_menus` VALUES ('1143', '1', '35');
INSERT INTO `sys_role_menus` VALUES ('1144', '1', '7');
INSERT INTO `sys_role_menus` VALUES ('1145', '1', '34');
INSERT INTO `sys_role_menus` VALUES ('1146', '1', '41');
INSERT INTO `sys_role_menus` VALUES ('1147', '1', '44');
INSERT INTO `sys_role_menus` VALUES ('1148', '1', '8');
INSERT INTO `sys_role_menus` VALUES ('1149', '1', '24');
INSERT INTO `sys_role_menus` VALUES ('1150', '1', '25');
INSERT INTO `sys_role_menus` VALUES ('1151', '1', '45');
INSERT INTO `sys_role_menus` VALUES ('1152', '1', '119');
INSERT INTO `sys_role_menus` VALUES ('1153', '1', '126');
INSERT INTO `sys_role_menus` VALUES ('1154', '1', '127');
INSERT INTO `sys_role_menus` VALUES ('1155', '1', '46');
INSERT INTO `sys_role_menus` VALUES ('1156', '1', '115');
INSERT INTO `sys_role_menus` VALUES ('1157', '1', '116');
INSERT INTO `sys_role_menus` VALUES ('1158', '1', '117');
INSERT INTO `sys_role_menus` VALUES ('1159', '1', '118');
INSERT INTO `sys_role_menus` VALUES ('1160', '1', '47');
INSERT INTO `sys_role_menus` VALUES ('1161', '1', '120');
INSERT INTO `sys_role_menus` VALUES ('1162', '1', '128');
INSERT INTO `sys_role_menus` VALUES ('1163', '1', '129');
INSERT INTO `sys_role_menus` VALUES ('1164', '1', '130');
INSERT INTO `sys_role_menus` VALUES ('1165', '1', '48');
INSERT INTO `sys_role_menus` VALUES ('1166', '38', '3');
INSERT INTO `sys_role_menus` VALUES ('1167', '38', '11');
INSERT INTO `sys_role_menus` VALUES ('1168', '38', '135');
INSERT INTO `sys_role_menus` VALUES ('1169', '38', '12');
INSERT INTO `sys_role_menus` VALUES ('1170', '38', '13');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐  密码加密时前缀，使加密后的值不同',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `valid` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常  默认值 ：1',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', '174d6f4c544799e0bd1323759687aa9e', '9052e391-2efa-4643-9332-585a4e657473', 'admin@tedu.cn', '13624356789', '1', null, '2017-07-18 17:13:39', null, null);
INSERT INTO `sys_users` VALUES ('2', 'zhangli', 'a398038062b23fead34dd22399fbaf41', '5e7cbd36-e897-4951-b42b-19809caf3caa', 'zhangli@tedu.cn', '13678909876', '1', '2017-07-18 10:01:51', '2017-07-21 17:09:58', null, null);
INSERT INTO `sys_users` VALUES ('3', 'wangke', 'c5dc32ec66041aeddf432b3146bd2257', '5e3e1475-1ea9-4a6a-976e-b07545827139', 'wangke@tedu.cn', '18678900987', '1', '2017-07-18 11:40:53', '2017-07-21 17:40:28', null, null);
INSERT INTO `sys_users` VALUES ('4', 'zhangql', '+HBpqtPuj9KLBIpneR5X0A==', 'ed487fac-9952-45c9-acaa-21dab9c689cc', 'zhangql@tedu.cn', '13678909876', '1', '2017-07-18 12:17:30', '2017-07-18 17:40:09', null, null);
INSERT INTO `sys_users` VALUES ('5', 'fanwei', '1acab7425d6dfae670f26bd160518902', '34fbedb2-e135-4f8d-b595-24360edc348d', 'fanwei@tedu.cn', '13876545678', '1', '2017-07-20 17:03:22', '2017-07-20 17:03:22', null, null);
INSERT INTO `sys_users` VALUES ('6', 'wumei', '431ebdcccf3404787a144f9ba669a8e2', '8a14f46f-7a17-4dfe-85ab-08e63cb618ce', 'wumei@tedu.cn', '13567898765', '1', '2017-07-21 10:57:40', '2017-07-21 10:58:21', null, null);

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('13', '1', '1');
INSERT INTO `sys_user_roles` VALUES ('19', '4', '2');
INSERT INTO `sys_user_roles` VALUES ('20', '5', '1');
INSERT INTO `sys_user_roles` VALUES ('22', '6', '2');
INSERT INTO `sys_user_roles` VALUES ('23', '2', '38');
INSERT INTO `sys_user_roles` VALUES ('25', '3', '2');

-- ----------------------------
-- Table structure for tms_attachements
-- ----------------------------
DROP TABLE IF EXISTS `tms_attachements`;
CREATE TABLE `tms_attachements` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `fileName` varchar(200) DEFAULT NULL COMMENT '文件名称 ',
  `contentType` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `filePath` varchar(200) DEFAULT NULL COMMENT '备注',
  `fileDisgest` varchar(200) DEFAULT NULL COMMENT '文件摘要',
  `athType` int(3) DEFAULT NULL COMMENT '附件归属类型',
  `belongId` int(11) DEFAULT NULL COMMENT '归属对象id',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tms_attachements
-- ----------------------------
INSERT INTO `tms_attachements` VALUES ('1', 'A', 'net01.txt', 'text/plain', 'd:\\uploads\\2017\\08\\28\\38c694a6-ef99-4c1b-89a7-87c5df2d893f.txt', 'b02ff4b24d84fb95e93c52ef05e9a186', '1', '1', null, '2017-08-28 16:43:01', null, '2017-08-28 16:43:01');
INSERT INTO `tms_attachements` VALUES ('2', 'B', 'book.txt', 'text/plain', 'd:\\uploads\\2017\\08\\28\\6ed86e9b-4274-4336-b591-4db40146d71e.txt', '3edd95d67db776bbe474b70502f7b2f2', '1', '1', null, '2017-08-28 16:58:50', null, '2017-08-28 16:58:50');

-- ----------------------------
-- Table structure for tms_classes
-- ----------------------------
DROP TABLE IF EXISTS `tms_classes`;
CREATE TABLE `tms_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '分类序号',
  `parentId` int(11) DEFAULT NULL COMMENT '父类id ',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tms_classes
-- ----------------------------
INSERT INTO `tms_classes` VALUES ('1', '境外游', '2', null, '境外游', null, '2017-08-01 22:43:24', null, '2017-08-01 22:44:50');
INSERT INTO `tms_classes` VALUES ('2', '境内游', '2', null, '境内游', null, '2017-08-01 22:43:57', null, '2017-08-01 22:43:57');
INSERT INTO `tms_classes` VALUES ('3', '主题游', '3', '1', '主题游。。。。', null, '2017-08-01 22:45:19', null, '2017-08-01 22:45:19');
INSERT INTO `tms_classes` VALUES ('4', '河北', '5', '2', '河北', null, '2017-08-28 16:58:01', null, '2017-08-28 16:58:33');
INSERT INTO `tms_classes` VALUES ('5', '南戴河', null, '4', '南戴河', null, '2017-08-28 16:58:18', null, '2017-08-28 16:58:18');

-- ----------------------------
-- Table structure for tms_products
-- ----------------------------
DROP TABLE IF EXISTS `tms_products`;
CREATE TABLE `tms_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `code` varchar(50) NOT NULL COMMENT '产品编号',
  `name` varchar(200) DEFAULT '' COMMENT '产品名称',
  `teamId` int(11) DEFAULT NULL COMMENT '团号Id',
  `exText` varchar(500) DEFAULT NULL COMMENT '特殊提示',
  `onlineDate` date DEFAULT NULL COMMENT '上架时间',
  `offlineDate` date DEFAULT NULL COMMENT '下架时间',
  `quantity` int(11) DEFAULT '0' COMMENT '预售数量',
  `minQty` int(11) DEFAULT '0' COMMENT '最低数量',
  `soldQty` int(11) DEFAULT '0' COMMENT '已售数量',
  `price` decimal(10,0) DEFAULT '0' COMMENT '产品价格',
  `classId` int(11) DEFAULT '0' COMMENT '产品分类编号',
  `nights` int(11) DEFAULT '0' COMMENT '晚数',
  `state` int(11) DEFAULT '0' COMMENT '产品状态  0：待售  1：上架   2：下架',
  `note` varchar(2000) DEFAULT NULL COMMENT '备注',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人用户名',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '最后修改人用户名',
  `modifiedTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tms_products
-- ----------------------------

-- ----------------------------
-- Table structure for tms_projects
-- ----------------------------
DROP TABLE IF EXISTS `tms_projects`;
CREATE TABLE `tms_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '项目编码',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `beginDate` datetime DEFAULT NULL COMMENT '开始日期',
  `endDate` datetime DEFAULT NULL COMMENT '结束日期',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tms_projects
-- ----------------------------
INSERT INTO `tms_projects` VALUES ('1', 'tt-20170809-CN-BJ-001', '环球游', '2017-08-10 00:00:00', '2017-08-20 00:00:00', '1', '环球游....', '2017-08-09 08:54:26', '2017-08-09 08:54:26', null, null);
INSERT INTO `tms_projects` VALUES ('3', 'tt-20170828-CN-BJ-001', '月球游', '2017-08-29 00:00:00', '2017-09-30 00:00:00', '1', '月球游。。。', '2017-08-28 16:47:30', '2017-08-28 16:47:30', null, null);
INSERT INTO `tms_projects` VALUES ('4', 'tt-20170901-CN-BJ-001', '北欧游', '2017-09-02 00:00:00', '2017-09-20 00:00:00', '0', '北欧游。。。', '2017-08-28 16:48:31', '2017-08-29 15:52:57', null, null);
-- ----------------------------
-- Table structure for tms_teams
-- ----------------------------
DROP TABLE IF EXISTS `tms_teams`;
CREATE TABLE `tms_teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '团名称',
  `projectId` int(11) DEFAULT NULL COMMENT '项目id',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tms_teams
-- ----------------------------
INSERT INTO `tms_teams` VALUES ('1', '环球游30日团', '1', '0', '环球游30日团。。。', '2017-08-28 16:54:25', '2017-08-28 16:54:25', null, null);
INSERT INTO `tms_teams` VALUES ('2', '环球游40日团', '1', '1', '环球游40日团', '2017-08-28 16:54:40', '2017-08-28 16:54:40', null, null);
INSERT INTO `tms_teams` VALUES ('3', '月球游20日团', '3', '1', '月球游20日团。。。', '2017-08-28 16:55:03', '2017-08-28 16:55:03', null, null);
