/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1@root
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : excelweb

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2017-07-08 18:34:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for import_data
-- ----------------------------
DROP TABLE IF EXISTS `import_data`;
CREATE TABLE `import_data` (
  `id` bigint(20) NOT NULL,
  `deal_date` varchar(255) DEFAULT NULL,
  `deal_status` varchar(255) DEFAULT NULL,
  `import_data_type` varchar(255) DEFAULT NULL,
  `import_date` varchar(255) DEFAULT NULL,
  `import_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of import_data
-- ----------------------------
INSERT INTO `import_data` VALUES ('1', '2017-07-08 16:09:08', '1', 'student', '2017-07-08 16:09:08', '1');

-- ----------------------------
-- Table structure for import_data_detail
-- ----------------------------
DROP TABLE IF EXISTS `import_data_detail`;
CREATE TABLE `import_data_detail` (
  `id` bigint(20) NOT NULL,
  `col0` varchar(255) DEFAULT NULL,
  `col1` varchar(255) DEFAULT NULL,
  `col2` varchar(255) DEFAULT NULL,
  `col3` varchar(255) DEFAULT NULL,
  `col4` varchar(255) DEFAULT NULL,
  `col5` varchar(255) DEFAULT NULL,
  `col6` varchar(255) DEFAULT NULL,
  `col7` varchar(255) DEFAULT NULL,
  `col8` varchar(255) DEFAULT NULL,
  `col9` varchar(255) DEFAULT NULL,
  `deal_fail_code` varchar(255) DEFAULT NULL,
  `deal_fail_msg` varchar(255) DEFAULT NULL,
  `deal_status` varchar(255) DEFAULT NULL,
  `import_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of import_data_detail
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `stuage` varchar(255) DEFAULT NULL,
  `stubirthday` varchar(255) DEFAULT NULL,
  `stuhobby` varchar(255) DEFAULT NULL,
  `stuname` varchar(255) DEFAULT NULL,
  `stunum` varchar(255) DEFAULT NULL,
  `stusex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '20', '2000-01-01', '看电影', '张三', '001', '男');
INSERT INTO `student` VALUES ('2', '18', '2001-04-12', '骑车', '林志玲', '002', '女');
INSERT INTO `student` VALUES ('3', '20', '2005-01-01', '玩游戏', '李四', '003', '男');
INSERT INTO `student` VALUES ('4', '18', '2001-04-12', '骑车', '林志玲', '004', '女');
INSERT INTO `student` VALUES ('5', '20', '2007-09-18', '看电影', '杨幂', '005', '女');
INSERT INTO `student` VALUES ('6', '18', '2002-07-12', '骑车', '刘亦菲', '006', '女');
