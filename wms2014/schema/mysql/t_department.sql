/*
MySQL Data Transfer
Source Host: localhost
Source Database: eam2008_development
Target Host: localhost
Target Database: eam2008_development
Date: 2008-1-20 12:00:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_department
-- ----------------------------
CREATE TABLE `t_department` (
  `id` bigint(20) NOT NULL auto_increment,
  `VERSION` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `leader` varchar(50) default NULL,
  `tel` varchar(50) default NULL,
  `code` varchar(50) default NULL,
  `CREATED_TIME` datetime default NULL,
  `CREATOR` varchar(50) default NULL,
  `LAST_MODIFIED_TIME` datetime default NULL,
  `LAST_OPERATOR` varchar(50) default NULL,
  `DISCRIMINATOR` varchar(255) default NULL,
  `sort_idx` tinyint(4) default NULL,
  `disabled` bit(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '2', '机动部', '施道清 鲁远  汪国海', '', 'JDB', null, null, '2007-12-30 11:00:14', 'sa', null, '1', '');
INSERT INTO `t_department` VALUES ('2', '3', '冲焊一厂', '董恒建', '0551-5648791', 'CH-1', null, null, '2007-12-30 10:55:37', 'sa', null, '6', '');
INSERT INTO `t_department` VALUES ('4', '1', '技术部', '--', '--', 'JSB', null, null, '2007-12-30 11:05:25', 'sa', null, null, '');
INSERT INTO `t_department` VALUES ('7', '1', '冲压', '--', '--', 'CY', null, null, '2007-12-30 11:04:40', 'sa', null, null, '');
INSERT INTO `t_department` VALUES ('8', '2', '总装一厂', ' 赵磊', '', 'ZZ-1', null, null, '2007-12-30 10:56:19', 'sa', null, '10', '');
INSERT INTO `t_department` VALUES ('10', '1', '涂装一厂', '刘旺平', '', 'TZ-1', null, null, '2007-12-30 10:56:00', 'sa', null, '8', '');
INSERT INTO `t_department` VALUES ('11', '2', '品管部', '颜定明 王斌', '', 'PGB', null, null, '2007-12-30 11:01:31', 'sa', null, '3', '');
INSERT INTO `t_department` VALUES ('12', '1', '生产部', '施勇 刘厚兵', '', 'SCB', null, null, '2007-12-30 10:59:50', 'sa', null, '2', '');
INSERT INTO `t_department` VALUES ('13', '1', '焊装', '--', '--', 'HZ', null, null, '2007-12-30 11:04:12', 'sa', null, null, '');
INSERT INTO `t_department` VALUES ('21', '1', '冲焊二厂', '林嗣杰', '', 'CH-2', '2007-12-30 10:56:42', 'sa', '2007-12-30 10:57:04', 'sa', 'com.yongjun.tdms.model.base.org.Department', '7', '');
INSERT INTO `t_department` VALUES ('22', '0', '涂装二厂', '周福东', '', 'TZ-2', '2007-12-30 10:58:59', 'sa', '2007-12-30 10:58:59', 'sa', 'com.yongjun.tdms.model.base.org.Department', '9', '');
INSERT INTO `t_department` VALUES ('23', '0', '总装二厂', '周仁发', '13856974113', 'ZZ-2', '2007-12-30 10:59:24', 'sa', '2007-12-30 10:59:24', 'sa', 'com.yongjun.tdms.model.base.org.Department', '11', '');
INSERT INTO `t_department` VALUES ('24', '0', '物料部', '刘琳', '', 'WLB', '2007-12-30 11:00:45', 'sa', '2007-12-30 11:00:45', 'sa', 'com.yongjun.tdms.model.base.org.Department', '4', '');
INSERT INTO `t_department` VALUES ('25', '0', '信息部', '吴睿东', '', 'XXB', '2007-12-30 11:02:02', 'sa', '2007-12-30 11:02:02', 'sa', 'com.yongjun.tdms.model.base.org.Department', '5', '');
