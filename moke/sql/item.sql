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

 Date: 16/08/2019 22:04:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `decription` varchar(500) NOT NULL DEFAULT '' COMMENT '描述',
  `sales` int(11) NOT NULL DEFAULT '0' COMMENT '销量',
  `img_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
