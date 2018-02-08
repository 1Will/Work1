/*
MySQL Data Transfer
Source Host: localhost
Source Database: eam2008_development
Target Host: localhost
Target Database: eam2008_development
Date: 2007-12-6 17:38:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_tooling_type
-- ----------------------------
CREATE TABLE `t_tooling_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `DISCRIMINATOR` varchar(255) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `CREATED_TIME` datetime default NULL,
  `CREATOR` varchar(50) default NULL,
  `LAST_MODIFIED_TIME` datetime default NULL,
  `LAST_OPERATOR` varchar(50) default NULL,
  `real_code` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FK6AFB64FAC3E0A856` (`org_id`),
  CONSTRAINT `FK6AFB64FAC3E0A856` FOREIGN KEY (`org_id`) REFERENCES `t_organizations` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_tooling_type` VALUES ('1', 'rrrr', '0', '001', '模具', '1', '2007-11-20 17:08:08', 'sa', '2007-11-20 17:08:12', 'sa', 'MOULD_TYPE');
INSERT INTO `t_tooling_type` VALUES ('2', 'rrrr', '0', '002', '夹具', '1', '2007-11-20 17:08:46', 'sa', '2007-11-20 17:08:49', 'sa', 'TONGS_TYPE');
INSERT INTO `t_tooling_type` VALUES ('3', 'rrrrr', '0', '003', '工位器具', '1', '2007-11-20 17:09:00', 'sa', '2007-11-20 17:08:57', 'sa', 'STATION_TYPE');
INSERT INTO `t_tooling_type` VALUES ('4', 'rrrr', '0', '004', '检具', '1', '2007-11-20 17:10:45', 'sa', '2007-11-20 17:10:49', 'sa', 'CHECKTOOL_TYPE');
INSERT INTO `t_tooling_type` VALUES ('5', 'rrr', '0', '005', '辅具', '1', '2007-11-20 17:10:59', 'sa', '2007-11-20 17:10:56', 'sa', 'ACCESSORYTOOL_TYPE');
