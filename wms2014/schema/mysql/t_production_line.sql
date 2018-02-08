/*
MySQL Data Transfer
Source Host: localhost
Source Database: eam2008_development
Target Host: localhost
Target Database: eam2008_development
Date: 2007-12-18 11:20:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_production_line
-- ----------------------------
CREATE TABLE `t_production_line` (
  `id` bigint(20) NOT NULL auto_increment,
  `VERSION` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `CREATED_TIME` datetime default NULL,
  `CREATOR` varchar(50) character set latin1 default NULL,
  `LAST_MODIFIED_TIME` datetime default NULL,
  `LAST_OPERATOR` varchar(50) character set latin1 default NULL,
  `DISCRIMINATOR` varchar(255) default NULL,
  `org_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FK9350F1EF394D9528` (`department_id`),
  KEY `FK9350F1EFC3E0A856` (`org_id`),
  CONSTRAINT `FK9350F1EF394D9528` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`id`),
  CONSTRAINT `FK9350F1EFC3E0A856` FOREIGN KEY (`org_id`) REFERENCES `t_organizations` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_production_line` VALUES ('1', '0', '001', 'B1线', '7', '2007-09-11 09:46:17', 'system', '2007-09-11 09:46:22', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('2', '0', '002', 'B2线', '7', '2007-09-11 09:54:57', 'system', '2007-09-18 09:55:01', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('3', '0', '003', 'C1线', '7', '2007-09-11 09:55:56', 'system', '2007-09-11 09:56:01', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('4', '0', '004', 'C2线', '7', '2007-12-14 09:37:14', 'system', '2007-12-14 09:37:21', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('5', '0', '000', 'A线', '7', '2007-12-14 09:38:40', 'system', '2007-12-14 09:38:43', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('6', '0', '005', '内饰工段', '8', '2007-12-18 11:10:55', 'system', '2007-12-18 11:11:02', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('7', '0', '006', '底盘工段', '8', '2007-12-18 11:12:32', 'system', '2007-12-18 11:12:37', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('8', '0', '007', '合装工段', '8', '2007-12-18 11:13:04', 'system', '2007-12-18 11:13:09', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('9', '0', '008', '调试工段', '8', '2007-12-18 11:13:30', 'system', '2007-12-18 11:13:35', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('10', '0', '009', '面漆工段', '10', '2007-12-18 11:14:45', 'system', '2007-12-18 11:14:50', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('11', '0', '010', '涂胶工段', '10', '2007-12-18 11:15:13', 'system', '2007-12-18 11:15:19', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('12', '0', '011', '前处理工段', '10', '2007-12-18 11:15:44', 'system', '2007-12-18 11:15:49', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('13', '0', '012', '保险杠工段', '10', '2007-12-18 11:16:13', 'system', '2007-12-18 11:16:18', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('14', '0', '013', '车身工段', '13', '2007-12-18 11:17:14', 'system', '2007-12-18 11:17:20', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('15', '0', '014', '车门调整工段', '13', '2007-12-18 11:17:45', 'system', '2007-12-18 11:17:50', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('16', '0', '015', '侧围工段', '13', '2007-12-18 11:18:13', 'system', '2007-12-18 11:18:18', 'system', null, '1');
INSERT INTO `t_production_line` VALUES ('17', '0', '016', '配件工段', '13', '2007-12-18 11:18:41', 'system', '2007-12-18 11:18:46', 'system', null, '1');
