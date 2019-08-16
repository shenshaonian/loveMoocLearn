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

 Date: 16/08/2019 22:05:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(255) NOT NULL COMMENT '1男，2-女',
  `age` int(11) NOT NULL DEFAULT '0',
  `telphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT 'byphone-bywechat-byalipay',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`) USING BTREE COMMENT '手机号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
