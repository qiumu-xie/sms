/*
Navicat MySQL Data Transfer

Source Server         : mysql8.0
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : ssm_sms

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2023-07-17 09:41:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('156', '潘安', '女', '123456789', '328472116@qq.com', '18037151521', '河南', '/lkdstudentproject/upload/admin_portrait/96d1ffd2-a319-464f-b3a5-2940a64890da__1120.jpg');
INSERT INTO `tb_admin` VALUES ('231', '张三', '男', '123456', '14@qq.com', '18037151521', '湖南', '/lkdstudentproject/upload/admin_portrait/44d24016-4a0c-4a08-82ff-8d84c312f282__8655.jpg_wh1200.jpg');

-- ----------------------------
-- Table structure for `tb_clazz`
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `number` int NOT NULL,
  `introducation` varchar(200) NOT NULL,
  `coordinator` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `grade_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_clazz_tb_grade__fk_idx` (`grade_name`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES ('70', '软件三班', '40', '主要都是高薪就业', '李哥1', '328472116@qq.cm', '15939407503', '信息工程大一');

-- ----------------------------
-- Table structure for `tb_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `manager` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `introducation` varchar(200) NOT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES ('93', '信息工程大一', '李主任', '328472116@qq.com', '18037151521', '主管信息工程大一学生事务');

-- ----------------------------
-- Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `introducation` varchar(200) DEFAULT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_student_sno_uindex` (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('8', '201809123', '李胜利', '男', '12345678', '328472116@qq.com', '18037151521', '北京', '优秀', '', '软件一班');

-- ----------------------------
-- Table structure for `tb_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  `portrait_path` varchar(200) DEFAULT NULL,
  `clazz_name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_teacher_tno_uindex` (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('12', '201809208', '王嘉盛', '男', '122600', '328472116@qq.com', '13037151511', '河南', '/sms/upload/teacher_portrait/2e3c5845-16a7-442c-a1a8-42309dbd94c7__u=1443567650,1630108118&fm=26&gp=0.jpg', '软件一班');
INSERT INTO `tb_teacher` VALUES ('13', '201809111', '赵宏宇', '女', '12345678', '328472116@qq.com', '13725031269', '北京', '', '软件二班');
