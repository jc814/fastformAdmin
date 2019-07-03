/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : fastform

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-07-03 17:13:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for design
-- ----------------------------
DROP TABLE IF EXISTS `design`;
CREATE TABLE `design` (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `DESIGN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计名称',
  `IS_SINGLE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否单表 0：否 1：是',
  `ORDER_BY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序字段',
  `PRIMARY_KEY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主键字段',
  `BUTTONS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '按钮组',
  `COLUMN_NUM` char(1) DEFAULT NULL COMMENT '列数',
  `UNION_SQL` varchar(255) DEFAULT NULL COMMENT '多表查询语句',
  `TABLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '(单表)关联表名',
  `DESCRIBE` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of design
-- ----------------------------

-- ----------------------------
-- Table structure for design_field
-- ----------------------------
DROP TABLE IF EXISTS `design_field`;
CREATE TABLE `design_field` (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主键ID',
  `DESIGN_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计ID',
  `LABEL_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签名',
  `FIELD_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字段名',
  `FIELD_REF` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字段来源',
  `IS_DISPLAY` char(1) DEFAULT NULL COMMENT '是否展示字段 0：否 1：是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of design_field
-- ----------------------------

-- ----------------------------
-- Table structure for design_field_add
-- ----------------------------
DROP TABLE IF EXISTS `design_field_add`;
CREATE TABLE `design_field_add` (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主键ID',
  `DESIGN_FIELD_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计字段ID',
  `WIDTH` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宽度',
  `NUMBER` int(3) DEFAULT NULL COMMENT '排序序号',
  `IS_REQUIRE` char(1) DEFAULT NULL COMMENT '是否必填 0：否 1：是',
  `IS_SHOW` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否显示 0：否 1：是',
  `CHECK_TYPE` char(1) DEFAULT NULL COMMENT '校验类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of design_field_add
-- ----------------------------

-- ----------------------------
-- Table structure for design_field_list
-- ----------------------------
DROP TABLE IF EXISTS `design_field_list`;
CREATE TABLE `design_field_list` (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主键ID',
  `DESIGN_FIELD_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计字段ID',
  `WIDTH` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宽度',
  `NUMBER` int(3) DEFAULT NULL COMMENT '排序序号',
  `IS_SHOW` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否显示 0：否 1：是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of design_field_list
-- ----------------------------

-- ----------------------------
-- Table structure for design_field_search
-- ----------------------------
DROP TABLE IF EXISTS `design_field_search`;
CREATE TABLE `design_field_search` (
  `ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主键ID',
  `DESIGN_FIELD_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计字段ID',
  `WIDTH` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宽度',
  `ONELINE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否占用一行 0：否 1：是',
  `NUMBER` int(3) DEFAULT NULL COMMENT '排序序号',
  `IS_SHOW` char(1) DEFAULT NULL COMMENT '是否显示 0：否 1：是'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of design_field_search
-- ----------------------------

-- ----------------------------
-- Table structure for schema
-- ----------------------------
DROP TABLE IF EXISTS `schema`;
CREATE TABLE `schema` (
  `ID` varchar(32) DEFAULT NULL COMMENT '主键ID',
  `SCHEMA_NAME` varchar(50) DEFAULT NULL COMMENT '方案名称',
  `TABLE_ID` varchar(32) DEFAULT NULL COMMENT '关联设计ID',
  `DESCRIBE` varchar(255) DEFAULT NULL COMMENT '方案描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schema
-- ----------------------------
INSERT INTO `schema` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(32) NOT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `TEL` varchar(11) DEFAULT NULL,
  `IDENTITY` varchar(18) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `SEX` char(1) DEFAULT NULL,
  `AGE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
