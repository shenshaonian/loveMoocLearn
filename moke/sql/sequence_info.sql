/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/08/2019 22:05:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) NOT NULL COMMENT '名字--相当于一个x轴的表',
  `current_value` int(11) NOT NULL DEFAULT '0' COMMENT '当前编码值',
  `step` int(11) NOT NULL DEFAULT '0' COMMENT '每次增加值',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
