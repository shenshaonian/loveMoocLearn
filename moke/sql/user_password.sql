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

 Date: 16/08/2019 22:05:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
