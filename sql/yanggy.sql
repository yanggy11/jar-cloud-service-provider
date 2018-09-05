CREATE TABLE `dynamic_route` (
  `dynamicId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id` varchar(1024) NOT NULL,
  `path` varchar(1024) NOT NULL,
  `serviceId` varchar(1024) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `stripPrefix` tinyint(2) NOT NULL DEFAULT '1',
  `retryable` tinyint(2) NOT NULL DEFAULT '0',
  `enabled` tinyint(2) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`dynamicId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `dynamic_route` VALUES ('1', 'consumer', '/consumer/**', 'cloud-service-consumer', null, '1', '0', '1', '2017-10-20 22:43:30', '2017-10-20 22:43:30', '0'), ('2', 'users', '/users/**', 'cloud-service-provider', null, '1', '0', '1', '2017-10-20 22:43:30', '2017-10-20 22:43:30', '0');

CREATE TABLE `dynamic_tree` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `remark` varchar(1024) NOT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

INSERT INTO `dynamic_tree` VALUES ('1', '-1', 'root', 'root'), ('2', '1', 'level1', 'level1'), ('3', '1', 'level2', 'level2'), ('4', '1', 'level3', 'level3'), ('5', '2', 'level1_1', 'level1_1'), ('6', '2', 'level1_2', 'level1_2'), ('7', '2', 'level1_3', 'level1_3'), ('8', '3', 'level2_1', 'level2_1'), ('9', '3', 'level2_2', 'level2_2'), ('10', '3', 'level2_3', 'level2_3'), ('11', '4', 'level3_1', 'level3_1'), ('12', '4', 'level3_2', 'level3_2'), ('13', '4', 'level3_3', 'level3_3'), ('14', '4', 'level3_4', 'level3_4');

CREATE TABLE `menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `menu` varchar(100) NOT NULL DEFAULT ' ' COMMENT '路径',
  `menu_name` varchar(100) NOT NULL DEFAULT ' ' COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单',
  `icon` varchar(100) NOT NULL DEFAULT ' ' COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('1', 'readme', '自述', '-1', 'el-icon-setting'), ('2', '2', '数据管理', '-1', 'el-icon-menu'), ('6', 'userList', '用户管理', '2', ''), ('7', 'resources', '权限管理', '2', ''), ('8', 'menus', '菜单管理', '2', ''), ('9', 'routes', '路由管理', '2', '');
COMMIT;

-- ----------------------------
--  Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `resources` varchar(100) NOT NULL DEFAULT '',
  `roleName` varchar(100) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `resources`
-- ----------------------------
BEGIN;
INSERT INTO `resources` VALUES ('1', 'ROLE_USER', '普通用户', '2017-10-21 11:15:48', '2017-10-21 14:12:39', '0'), ('2', 'ROLE_ADMIN', '管理员', '2017-10-21 11:15:48', '2017-10-21 15:54:37', '0'), ('3', 'test', '测试', '2017-10-21 11:47:59', '2017-10-21 15:55:18', '0'), ('4', 'test1', '测试1', '2017-10-21 11:48:04', '2017-10-21 15:55:11', '0'), ('5', 'ROLE_CHECK', '检查员', '2017-10-21 14:02:50', '2017-10-21 14:02:50', '0'), ('6', 'ROLE_EDIT', '编辑权限', '2017-10-21 14:04:24', '2017-10-21 14:04:37', '0'), ('7', 'ROLE_USER_ADD', '用户新增权限', '2017-10-21 14:10:07', '2017-10-21 14:10:07', '0'), ('8', 'ROLE_USER_EDIT', '用户编辑权限', '2017-10-21 14:10:45', '2017-10-21 14:10:45', '0'), ('9', 'ROLE_USER_DELETE', '用户删除权限', '2017-10-21 14:11:36', '2017-10-21 14:11:36', '0'), ('10', 'ROLE_USER_QUERY', '用户查询权限', '2017-10-21 14:14:02', '2017-10-21 14:14:02', '0');
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('5', '1', '1'), ('6', '1', '2'), ('7', '1', '5'), ('8', '1', '6'), ('9', '1', '7'), ('10', '1', '8'), ('11', '1', '9'), ('12', '1', '10');
COMMIT;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `password` varchar(255) NOT NULL DEFAULT ' ' COMMENT '密码',
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL,
  `age` int(11) unsigned NOT NULL DEFAULT '1',
  `sex` int(2) NOT NULL DEFAULT '0' COMMENT '性别 1 女 0 男',
  `phone` varchar(12) NOT NULL,
  `email` varchar(128) NOT NULL,
  `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


INSERT INTO `users` VALUES ('e10adc3949ba59abbe56e057f20f883e', '1', 'derrick', '29', '0', '17706390524', 'yangguiyun@mobanker.com', '0', '2017-10-20 21:36:10', '2017-10-21 16:27:30'), ('e10adc3949ba59abbe56e057f20f883e', '2', 'jll', '29', '1', '12345678900', 'jj@mail.com', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('e10adc3949ba59abbe56e057f20f883e', '5', '11', '27', '0', '', '', '1', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('e10adc3949ba59abbe56e057f20f883e', '6', 'djjalk', '28', '1', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('e10adc3949ba59abbe56e057f20f883e', '7', 'dsafg', '34', '1', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('e10adc3949ba59abbe56e057f20f883e', '23', 'jjjj', '0', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('e10adc3949ba59abbe56e057f20f883e', '26', 'jjjyj', '0', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('10793daa0329cd3b0cd80da270b1e591', '30', '5234', '18', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('f31f768385aab90f9156a8ab6ea2688d', '31', '523d4', '18', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('75e2617e184641aaa0b3d72003a9ee29', '35', 'string', '0', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('dc868e11b3328d9728cf04831e1edf19', '36', 'str1ing', '0', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('8be761c79870ba75f6d5cb749bf4b161', '37', 'str111ing', '0', '0', '', '', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('1', '38', 'hikajhhk', '12', '1', '11', '1', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('11', '39', 'oiakljljka', '34', '0', '111', '11', '0', '2017-10-20 21:36:10', '2017-10-20 21:36:10'), ('rwtwtw', '42', 'rtestyws', '12', '0', '1222222345', 'rtwrrt', '0', '2017-10-21 15:22:29', '2017-10-21 15:22:29'), ('wrt ', '43', 'tww gv', '54', '1', '245262567373', '5ywye', '0', '2017-10-21 15:22:48', '2017-10-21 15:22:48');
