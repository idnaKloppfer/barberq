/*
 Navicat Premium Data Transfer

 Source Server         : conec
 Source Server Type    : MySQL
 Source Server Version : 100132
 Source Host           : localhost:3306
 Source Schema         : barberq

 Target Server Type    : MySQL
 Target Server Version : 100132
 File Encoding         : 65001

 Date: 21/09/2018 15:57:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PHONE` bigint(10) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000,
  `EMAIL` varchar(150) CHARACTER SET hebrew COLLATE hebrew_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `phoneperson`(`PHONE`) USING BTREE,
  CONSTRAINT `phoneperson` FOREIGN KEY (`PHONE`) REFERENCES `person` (`PHONE`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERSON_ID` bigint(20) NOT NULL,
  `JOB_TITTLE_ID` bigint(20) NULL DEFAULT NULL,
  `ENTRYTIME` time(0) NOT NULL DEFAULT '00:00:00',
  `DEPARTURETIME` time(0) NOT NULL DEFAULT '00:00:00',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `person`(`PERSON_ID`) USING BTREE,
  INDEX `JobTitle`(`JOB_TITTLE_ID`) USING BTREE,
  CONSTRAINT `JobTitle` FOREIGN KEY (`JOB_TITTLE_ID`) REFERENCES `job_tittle` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 200834709 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for empwork
-- ----------------------------
DROP TABLE IF EXISTS `empwork`;
CREATE TABLE `empwork`  (
  `idemployee` bigint(20) NOT NULL,
  `idwork` int(11) NOT NULL,
  INDEX `workdays`(`idwork`) USING BTREE,
  INDEX `emplo`(`idemployee`) USING BTREE,
  CONSTRAINT `emplo` FOREIGN KEY (`idemployee`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workdays` FOREIGN KEY (`idwork`) REFERENCES `workdays` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for haircut_type
-- ----------------------------
DROP TABLE IF EXISTS `haircut_type`;
CREATE TABLE `haircut_type`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STYLE` varchar(100) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `PRICE` float NOT NULL,
  `GENDER` char(1) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `DURATION` bigint(15) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `haircut`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for job_tittle
-- ----------------------------
DROP TABLE IF EXISTS `job_tittle`;
CREATE TABLE `job_tittle`  (
  `NAME` varchar(50) CHARACTER SET hebrew COLLATE hebrew_general_ci NULL DEFAULT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `WORK_POSITION_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `position_id`(`WORK_POSITION_ID`) USING BTREE,
  CONSTRAINT `position_id` FOREIGN KEY (`WORK_POSITION_ID`) REFERENCES `work_position` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting`  (
  `EMPLOYEE_SUPPORT` bigint(20) NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `CLIENT_ID` bigint(20) NOT NULL,
  `HAIRCUT` bigint(20) NULL DEFAULT NULL,
  `USER_ID` int(20) NOT NULL,
  `COMPLETEDWORK` tinyint(1) NOT NULL DEFAULT 0,
  `TOTALPRICE` double(100, 2) NULL DEFAULT NULL,
  `DISCOUNT` int(3) NULL DEFAULT NULL,
  `DATEEXIT` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `userfo`(`USER_ID`) USING BTREE,
  INDEX `clientt`(`CLIENT_ID`) USING BTREE,
  INDEX `haircut`(`HAIRCUT`) USING BTREE,
  INDEX `employe`(`EMPLOYEE_SUPPORT`) USING BTREE,
  CONSTRAINT `client` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employe` FOREIGN KEY (`EMPLOYEE_SUPPORT`) REFERENCES `employee` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `haircut` FOREIGN KEY (`HAIRCUT`) REFERENCES `haircut_type` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userfo` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for meetserv
-- ----------------------------
DROP TABLE IF EXISTS `meetserv`;
CREATE TABLE `meetserv`  (
  `ids` bigint(15) NOT NULL,
  `idm` bigint(15) NOT NULL,
  INDEX `id_s`(`ids`) USING BTREE,
  INDEX `id_m`(`idm`) USING BTREE,
  CONSTRAINT `id_m` FOREIGN KEY (`idm`) REFERENCES `meeting` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_s` FOREIGN KEY (`ids`) REFERENCES `service` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `ID` bigint(15) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `LAST_NAME` varchar(30) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `PHONE` bigint(10) UNSIGNED NOT NULL,
  `GENDER` char(1) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `TYPEPERSON` varchar(30) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  PRIMARY KEY (`PHONE`, `ID`) USING BTREE,
  INDEX `PHONE`(`PHONE`) USING BTREE,
  INDEX `idPerson`(`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 223 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service`  (
  `ID` bigint(15) NOT NULL AUTO_INCREMENT,
  `PRICE` float(15, 0) NOT NULL,
  `NAME` varchar(255) CHARACTER SET hebrew COLLATE hebrew_bin NOT NULL,
  `DURATION` bigint(15) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `PASSWORD` varchar(100) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `EMPLOYEE_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `employee`(`EMPLOYEE_ID`) USING BTREE,
  CONSTRAINT `employee` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for work_position
-- ----------------------------
DROP TABLE IF EXISTS `work_position`;
CREATE TABLE `work_position`  (
  `NAME` varchar(50) CHARACTER SET hebrew COLLATE hebrew_general_ci NULL DEFAULT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for workdays
-- ----------------------------
DROP TABLE IF EXISTS `workdays`;
CREATE TABLE `workdays`  (
  `days` varchar(20) CHARACTER SET hebrew COLLATE hebrew_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = hebrew COLLATE = hebrew_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
