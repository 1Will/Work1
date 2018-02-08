/*
MySQL Data Transfer
Source Host: localhost
Source Database: eam2008_development
Target Host: localhost
Target Database: eam2008_development
Date: 2008-1-20 11:59:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_code_value
-- ----------------------------
CREATE TABLE `t_code_value` (
  `id` bigint(20) NOT NULL auto_increment,
  `VERSION` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `comment` varchar(100) default NULL,
  `read_only` bit(1) NOT NULL,
  `CREATED_TIME` datetime default NULL,
  `CREATOR` varchar(50) character set latin1 default NULL,
  `LAST_MODIFIED_TIME` datetime default NULL,
  `LAST_OPERATOR` varchar(50) character set latin1 default NULL,
  `master_code_id` bigint(20) default NULL,
  `DISCRIMINATOR` varchar(255) default NULL,
  `real_code` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FK9B04014AA3A8AFD0` (`master_code_id`),
  CONSTRAINT `FK9B04014AA3A8AFD0` FOREIGN KEY (`master_code_id`) REFERENCES `t_code_value` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_code_value` VALUES ('1', '0', '001', '设备属性', 'a', '', '2007-09-11 15:40:59', 'system', '2007-09-11 15:41:04', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('2', '0', '0011', '工装', null, '', '2007-09-11 15:43:35', 'system', '2007-09-11 15:43:40', 'system', '1', null, null);
INSERT INTO `t_code_value` VALUES ('3', '0', '0012', '设备', null, '', '2007-09-11 15:44:10', 'system', '2007-09-11 15:44:13', 'system', '1', null, null);
INSERT INTO `t_code_value` VALUES ('4', '0', '002', '设备特种属性', null, '', '2007-09-11 15:44:47', 'system', '2007-09-11 15:44:50', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('5', '0', '0021', '车辆与运输设备', null, '', '2007-09-11 15:45:26', 'system', '2007-09-11 15:45:30', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('6', '0', '0022', '电动机', null, '', '2007-09-11 15:46:15', 'system', '2007-09-11 15:46:19', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('7', '0', '0023', '电梯与起重设备', null, '', '2007-09-11 15:46:52', 'system', '2007-09-11 15:46:55', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('8', '0', '0024', '动力设备', null, '', '2007-09-11 15:47:29', 'system', '2007-09-11 15:47:33', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('9', '0', '0025', '构建筑物', null, '', '2007-09-11 15:48:13', 'system', '2007-09-11 15:48:17', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('10', '0', '0026', '锅炉设备', null, '', '2007-09-11 15:48:42', 'system', '2007-09-11 15:48:45', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('11', '0', '0027', '计量器具', null, '', '2007-09-11 15:49:07', 'system', '2007-09-11 15:49:12', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('12', '0', '0028', '压力容器', null, '', '2007-09-11 15:49:39', 'system', '2007-09-11 15:49:42', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('13', '0', '0029', '仪器仪表', null, '', '2007-09-11 15:50:06', 'system', '2007-09-11 15:50:09', 'system', '4', null, null);
INSERT INTO `t_code_value` VALUES ('14', '0', '003', '设备状态', null, '', '2007-09-11 15:50:48', 'system', '2007-09-11 15:50:52', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('15', '0', '0031', '完好', null, '', '2007-09-11 15:53:28', 'system', '2007-09-11 15:53:32', 'system', '14', null, null);
INSERT INTO `t_code_value` VALUES ('16', '0', '0032', '未使用', null, '', '2007-09-11 15:54:03', 'system', '2007-09-11 15:54:06', 'system', '14', null, null);
INSERT INTO `t_code_value` VALUES ('17', '0', '004', '点检标准分类', null, '', '2007-09-11 15:54:46', 'system', '2007-09-11 15:54:49', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('18', '0', '0041', '日常点检', null, '', '2007-09-11 15:55:13', 'system', '2007-09-11 15:55:16', 'system', '17', null, null);
INSERT INTO `t_code_value` VALUES ('19', '0', '0042', '定期点检', null, '', '2007-09-11 15:55:40', 'system', '2007-09-11 15:55:44', 'system', '17', null, null);
INSERT INTO `t_code_value` VALUES ('20', '0', '0043', '精密点检', null, '', '2007-09-11 15:56:06', 'system', '2007-09-11 15:56:09', 'system', '17', null, null);
INSERT INTO `t_code_value` VALUES ('21', '0', '0044', '技术诊断和倾向性诊断', null, '', '2007-09-11 15:56:41', 'system', '2007-09-11 15:56:44', 'system', '17', null, null);
INSERT INTO `t_code_value` VALUES ('22', '0', '0045', '精度测试检查', null, '', '2007-09-11 15:57:13', 'system', '2007-09-11 15:57:16', 'system', '17', null, null);
INSERT INTO `t_code_value` VALUES ('23', '0', '005', '生产用途', null, '', '2007-09-11 15:57:49', 'system', '2007-09-11 15:57:54', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('24', '0', '0051', '生产', null, '', '2007-09-11 15:58:13', 'system', '2007-09-11 15:58:17', 'system', '23', null, null);
INSERT INTO `t_code_value` VALUES ('25', '0', '0052', '非生产', null, '', '2007-09-11 15:58:40', 'system', '2007-09-11 15:58:43', 'system', '23', null, null);
INSERT INTO `t_code_value` VALUES ('26', '0', '006', '管理状态', null, '', '2007-09-11 15:59:10', 'system', '2007-09-11 15:59:13', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('27', '0', '0061', '在用', null, '', '2007-09-11 15:59:35', 'system', '2007-09-11 15:59:38', 'system', '26', null, null);
INSERT INTO `t_code_value` VALUES ('28', '0', '0062', '封存', null, '', '2007-09-11 16:00:01', 'system', '2007-09-11 16:00:05', 'system', '26', null, null);
INSERT INTO `t_code_value` VALUES ('29', '0', '0063', '报废', null, '', '2007-09-11 16:00:28', 'system', '2007-09-11 16:00:31', 'system', '26', null, null);
INSERT INTO `t_code_value` VALUES ('30', '0', '007', '供应商级别', null, '', '2007-09-11 16:00:58', 'system', '2007-09-11 16:01:02', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('31', '0', '0071', 'A', null, '', '2007-09-11 16:01:19', 'system', '2007-09-11 16:01:22', 'system', '30', null, null);
INSERT INTO `t_code_value` VALUES ('32', '0', '0072', 'B', null, '', '2007-09-11 16:01:37', 'system', '2007-09-11 16:01:40', 'system', '30', null, null);
INSERT INTO `t_code_value` VALUES ('33', '0', '008', '行业类型', null, '', '2007-10-24 12:08:14', 'system', '2007-10-24 12:08:18', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('34', '0', '0081', '冶金', null, '', '2007-10-24 13:25:06', 'system', '2007-10-24 13:25:10', 'system', '33', null, null);
INSERT INTO `t_code_value` VALUES ('35', '0', '0082', '勘测', null, '', '2007-10-24 13:27:28', 'system', '2007-10-24 13:27:31', 'system', '33', null, null);
INSERT INTO `t_code_value` VALUES ('36', '0', '0083', '电力', null, '', '2007-10-24 13:27:52', 'system', '2007-10-24 13:27:55', 'system', '33', null, null);
INSERT INTO `t_code_value` VALUES ('37', '0', '0084', '铸造', null, '', '2007-10-24 13:28:34', 'system', '2007-10-24 13:28:37', 'system', '33', null, null);
INSERT INTO `t_code_value` VALUES ('38', '0', '009', '供应商类型', null, '', '2007-10-24 13:31:02', 'system', '2007-10-24 13:31:05', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('39', '0', '0091', '设备', null, '', '2007-10-24 13:31:33', 'system', '2007-10-24 13:31:38', 'system', '38', null, null);
INSERT INTO `t_code_value` VALUES ('40', '0', '0092', '工装', null, '', '2007-10-24 13:31:59', 'system', '2007-10-24 13:32:02', 'system', '38', null, null);
INSERT INTO `t_code_value` VALUES ('41', '0', '0093', '其它', null, '', '2007-10-24 13:32:26', 'system', '2007-10-24 13:32:29', 'system', '38', null, null);
INSERT INTO `t_code_value` VALUES ('42', '0', '010', '公司性质', null, '', '2007-10-24 13:33:08', 'system', '2007-10-24 13:33:11', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('43', '0', '0101', '国营', null, '', '2007-10-24 13:33:39', 'system', '2007-10-24 13:33:43', 'system', '42', null, null);
INSERT INTO `t_code_value` VALUES ('44', '0', '0102', '集体', null, '', '2007-10-24 13:34:07', 'system', '2007-10-24 13:34:11', 'system', '42', null, null);
INSERT INTO `t_code_value` VALUES ('45', '0', '0103', '外资', null, '', '2007-10-24 13:34:34', 'system', '2007-10-24 13:34:37', 'system', '42', null, null);
INSERT INTO `t_code_value` VALUES ('46', '0', '0104', '私营', null, '', '2007-10-24 13:35:05', 'system', '2007-10-24 13:35:08', 'system', '42', null, null);
INSERT INTO `t_code_value` VALUES ('47', '0', '011', '计量单位', null, '', '2007-11-07 11:16:33', 'system', '2007-11-07 11:16:37', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('48', '0', '0111', '件', null, '', '2007-11-07 11:17:21', 'system', '2007-11-07 11:17:26', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('49', '0', '0112', '套', null, '', '2007-11-07 11:17:58', 'system', '2007-11-07 11:18:02', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('50', '0', '0113', '只', null, '', '2007-11-07 11:18:30', 'system', '2007-11-07 11:18:33', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('51', '0', '0114', '副', null, '', '2007-11-07 11:19:00', 'system', '2007-11-07 11:19:04', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('52', '0', '0115', '根', null, '', '2007-11-07 11:19:55', 'system', '2007-11-07 11:19:58', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('53', '0', '0116', '个', null, '', '2007-11-07 11:20:18', 'system', '2007-11-07 11:20:21', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('54', '0', '0117', '块', null, '', '2007-11-07 11:23:03', 'system', '2007-11-07 11:23:06', 'system', '47', null, null);
INSERT INTO `t_code_value` VALUES ('55', '0', '012', '文档种类', null, '', '2007-11-15 14:30:06', 'system', '2007-11-15 14:30:10', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('56', '0', '0121', '程序文件', null, '', '2007-11-15 14:30:58', 'system', '2007-11-15 14:31:02', 'system', '55', null, null);
INSERT INTO `t_code_value` VALUES ('57', '0', '0122', '制度文件', null, '', '2007-11-15 14:31:50', 'system', '2007-11-15 14:31:54', 'system', '55', null, null);
INSERT INTO `t_code_value` VALUES ('58', '0', '013', '备件种类', null, '', '2007-11-15 15:30:52', 'system', '2007-11-15 15:30:57', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('59', '0', '014', '车型', null, '', '2007-11-16 10:43:30', 'system', '2007-11-16 10:43:39', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('60', '0', '0131', '模具', null, '', '2007-11-19 09:44:44', 'system', '2007-11-19 09:44:51', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('63', '0', '015', '工装状态', null, '', '2007-11-20 09:27:06', 'sa', '2007-11-20 09:27:11', 'sa', null, null, null);
INSERT INTO `t_code_value` VALUES ('64', '0', '0151', '正常', null, '', '2007-11-20 09:28:03', 'sa', '2007-11-20 09:28:07', 'sa', '63', null, 'TOOLING_NORMAL');
INSERT INTO `t_code_value` VALUES ('65', '0', '0152', '使用中', null, '', '2007-11-20 09:28:31', 'sa', '2007-11-20 09:28:28', 'sa', '63', null, 'TOOLING_BORROW');
INSERT INTO `t_code_value` VALUES ('66', '0', '0153', '维修中', null, '', '2007-11-20 09:29:30', 'sa', '2007-11-20 09:29:33', 'sa', '63', null, 'TOOLING_REPAIR');
INSERT INTO `t_code_value` VALUES ('67', '0', '0154', '已报废', null, '', '2007-11-20 09:29:53', 'sa', '2007-11-20 09:29:50', 'sa', '63', null, 'TOOLING_DISCARD');
INSERT INTO `t_code_value` VALUES ('68', '0', '0033', '已报废', null, '', '2007-12-04 14:54:40', 'sa', '2007-12-04 14:54:44', 'sa', '14', null, 'DEVICE_DISCARD');
INSERT INTO `t_code_value` VALUES ('69', '0', '0155', '变更中', null, '', '2007-12-04 09:15:57', 'sa', '2007-12-04 09:16:03', 'sa', '63', null, 'TOOLING_CHANGE');
INSERT INTO `t_code_value` VALUES ('70', '0', '0034', '维修中', null, '', '2007-12-06 14:50:38', 'sa', '2007-12-06 14:50:47', 'sa', '14', null, 'DEVICE_REPAIR');
INSERT INTO `t_code_value` VALUES ('71', '0', '0156', '已封存', null, '', '2007-12-24 15:45:04', 'sa', '2007-12-31 15:45:12', 'sa', '63', null, 'TOOLING_DEMARCATE');
INSERT INTO `t_code_value` VALUES ('72', '0', '0132', '电磁阀', null, '', '2007-12-11 14:20:02', 'system', '2007-12-11 14:20:12', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('73', '0', '0133', '电器类', null, '', '2007-12-11 14:21:08', 'system', '2007-12-11 14:21:13', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('74', '0', '0134', '机械类', null, '', '2007-12-11 14:21:51', 'system', '2007-12-11 14:21:56', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('76', '0', '0136', '皮带类', null, '', '2007-12-11 14:23:05', 'system', '2007-12-11 14:23:11', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('77', '0', '0137', '焊钳备件A', null, '', '2007-12-11 14:55:23', 'system', '2007-12-11 14:55:29', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('78', '0', '0138', '阀与轴类', null, '', '2007-12-11 15:07:27', 'system', '2007-12-11 15:07:32', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('79', '0', '0139', '风动工具', null, '', '2007-12-11 15:12:47', 'system', '2007-12-11 15:12:51', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('80', '0', '0140', '机器人', null, '', '2007-12-11 15:17:04', 'system', '2007-12-11 15:17:07', 'system', '58', null, null);
INSERT INTO `t_code_value` VALUES ('81', '0', '0157', '失效', '', '', '2007-12-16 15:59:56', 'sa', '2007-12-16 16:00:16', 'system', '63', null, 'TOOLING_DISABLED');
INSERT INTO `t_code_value` VALUES ('82', '0', '016', '润滑油种类', '', '', '2007-12-21 13:48:04', 'system', '2007-12-21 13:48:12', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('83', '0', '017', '容量单位', null, '', '2007-12-24 15:32:26', 'system', '2007-12-24 15:32:33', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('84', '0', '0171', '毫升', null, '', '2007-12-24 15:33:21', 'system', '2007-12-24 15:33:30', 'system', '83', null, null);
INSERT INTO `t_code_value` VALUES ('85', '0', '0172', '升', null, '', '2007-12-24 15:34:11', 'system', '2007-12-24 15:34:06', 'system', '83', null, null);
INSERT INTO `t_code_value` VALUES ('86', '0', '0161', '轿车润滑油', null, '', '2007-12-24 15:35:38', 'system', '2007-12-24 15:35:46', 'system', '82', null, null);
INSERT INTO `t_code_value` VALUES ('87', '0', '018', '维修级别', null, '', '2008-01-08 14:00:11', 'system', '2008-01-08 14:00:22', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('88', '0', '0181', 'A', null, '', '2008-01-08 14:00:57', 'system', '2008-01-08 14:01:05', 'system', '87', null, null);
INSERT INTO `t_code_value` VALUES ('89', '0', '0182', 'B', null, '', '2008-01-08 14:02:10', 'system', '2008-01-08 14:02:18', 'system', '87', null, null);
INSERT INTO `t_code_value` VALUES ('90', '0', '0183', 'C', null, '', '2008-01-08 14:08:22', 'system', '2008-01-08 14:08:32', 'system', '87', null, null);
INSERT INTO `t_code_value` VALUES ('91', '0', '019', '大项修种类', null, '', '2008-01-14 17:28:07', 'system', '2008-01-14 17:28:15', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('92', '0', '0191', '大', null, '', '2008-01-14 17:28:46', 'system', '2008-01-14 17:28:54', 'system', '91', null, null);
INSERT INTO `t_code_value` VALUES ('93', '0', '0192', '项', null, '', '2008-01-14 17:29:53', 'system', '2008-01-14 17:30:00', 'system', '91', null, null);
INSERT INTO `t_code_value` VALUES ('94', '0', '020', '技术等级', null, '', '2008-01-14 17:31:01', 'system', '2008-01-14 17:31:07', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('95', '0', '0201', '机械', null, '', '2008-01-14 17:31:48', 'system', '2008-01-14 17:31:55', 'system', '94', null, null);
INSERT INTO `t_code_value` VALUES ('96', '0', '0202', '电气', null, '', '2008-01-14 17:33:20', 'system', '2008-01-14 17:33:25', 'system', '94', null, null);
INSERT INTO `t_code_value` VALUES ('97', '0', '021', '润滑方法', null, '', '2008-01-15 13:47:09', 'system', '2008-01-15 13:47:22', 'system', null, null, null);
INSERT INTO `t_code_value` VALUES ('98', '0', '0211', '加油', null, '', '2008-01-15 13:47:59', 'system', '2008-01-15 13:48:08', 'system', '97', null, null);
INSERT INTO `t_code_value` VALUES ('99', '0', '0212', '脂润滑', null, '', '2008-01-15 13:48:51', 'system', '2008-01-15 13:48:58', 'system', '97', null, null);
INSERT INTO `t_code_value` VALUES ('100', '0', '0213', '固体润滑', null, '', '2008-01-15 13:49:30', 'system', '2008-01-15 13:49:20', 'system', '97', null, null);
INSERT INTO `t_code_value` VALUES ('101', '0', '0214', '气体润滑', null, '', '2008-01-15 13:50:28', 'system', '2008-01-15 13:50:34', 'system', '97', null, null);
INSERT INTO `t_code_value` VALUES ('102', '0', '022', '报废类别', null, '', '2008-01-20 11:05:30', 'system', '2008-01-20 11:05:36', 'system', null, null, null);
