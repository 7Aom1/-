/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : db_library

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2022-06-10 13:27:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bookinfo`
-- ----------------------------
DROP TABLE IF EXISTS `bookinfo`;
CREATE TABLE `bookinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(20) DEFAULT NULL,
  `bookname` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `typeID` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeID` (`typeID`),
  CONSTRAINT `bookinfo_ibfk_1` FOREIGN KEY (`typeID`) REFERENCES `booktype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookinfo
-- ----------------------------
INSERT INTO `bookinfo` VALUES ('1', '202201', 'web应用开发', '刘东', '123456', '20', '1');
INSERT INTO `bookinfo` VALUES ('2', '202202', 'c语言设计', '黄军', '123457', '15', '1');
INSERT INTO `bookinfo` VALUES ('3', '202203', '数据库', '郭明', '123458', '30', '1');
INSERT INTO `bookinfo` VALUES ('4', '202204', '软件工程', '刘艳', '123459', '20', '1');

-- ----------------------------
-- Table structure for `booktype`
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES ('1', '计算机', '5');
INSERT INTO `booktype` VALUES ('2', '文学', '20');

-- ----------------------------
-- Table structure for `borrow`
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `readerID` int(10) DEFAULT NULL,
  `bookID` int(10) DEFAULT NULL,
  `borrowTime` date DEFAULT NULL,
  `backTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookID` (`bookID`),
  KEY `readerID` (`readerID`),
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `bookinfo` (`id`),
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`readerID`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('1', '1', '1', '2022-06-08', '2022-06-10');
INSERT INTO `borrow` VALUES ('5', '1', '2', '2022-06-09', '2022-06-15');
INSERT INTO `borrow` VALUES ('9', '2', '1', '2022-06-10', '2022-06-10');
INSERT INTO `borrow` VALUES ('10', '2', '2', '2022-06-10', '2022-06-15');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'mr', '123');

-- ----------------------------
-- Table structure for `reader`
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `barCode` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `sage` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `typeID` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeID` (`typeID`),
  CONSTRAINT `reader_ibfk_1` FOREIGN KEY (`typeID`) REFERENCES `readertype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('1', '20220101', '王洋', '男', '21', '123456', '1');
INSERT INTO `reader` VALUES ('2', '20220102', '刘敏', '女', '20', '123456', '1');
INSERT INTO `reader` VALUES ('3', '20220103', '黄伟', '男', '21', '123456', '1');

-- ----------------------------
-- Table structure for `readertype`
-- ----------------------------
DROP TABLE IF EXISTS `readertype`;
CREATE TABLE `readertype` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readertype
-- ----------------------------
INSERT INTO `readertype` VALUES ('1', '学生', '5');
INSERT INTO `readertype` VALUES ('2', '教师', '8');
