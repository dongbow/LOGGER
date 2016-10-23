CREATE DATABASE logger DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE logger;

CREATE TABLE user_info (
	user_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户id',
	user_name VARCHAR(20) NOT NULL COMMENT '用户名',
	password VARCHAR(40) NOT NULL COMMENT '用户密码',
	nickname VARCHAR(20) NOT NULL COMMENT '用户昵称，默认等于用户名',
	status INT NOT NULL DEFAULT '0' COMMENT '用户状态{0:正常，1:锁定}',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	is_admin INT NOT NULL DEFAULT '0' COMMENT '是否是管理员{0:否，1：是}',
	PRIMARY KEY(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `user_info` VALUES ('1', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'ADMIN', '0', '2016-10-23 20:32:04', '1');
INSERT INTO `user_info` VALUES ('2', 'one', 'c4ca4238a0b923820dcc509a6f75849b', 'One', '0', '2016-10-23 21:50:29', '0');
INSERT INTO `user_info` VALUES ('3', 'two', 'c4ca4238a0b923820dcc509a6f75849b', 'Two', '0', '2016-10-23 21:50:41', '0');
INSERT INTO `user_info` VALUES ('4', 'three', 'c4ca4238a0b923820dcc509a6f75849b', 'Three', '0', '2016-10-23 21:50:53', '0');
INSERT INTO `user_info` VALUES ('5', 'four', 'c4ca4238a0b923820dcc509a6f75849b', 'Four', '0', '2016-10-23 21:51:05', '0');
INSERT INTO `user_info` VALUES ('6', 'five', 'c4ca4238a0b923820dcc509a6f75849b', 'Five', '1', '2016-10-23 21:52:09', '0');

CREATE TABLE system_log (
	id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
	module VARCHAR(255) NOT NULL COMMENT '模块',
	method VARCHAR(255) NOT NULL COMMENT '方法',
	`desc` VARCHAR(255) NOT NULL COMMENT '描述',
	user_id BIGINT NOT NULL COMMENT '操作人ID',
	userNickname VARCHAR(20) NOT NULL COMMENT '操作人昵称',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作历史表';