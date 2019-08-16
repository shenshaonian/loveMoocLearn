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

 Date: 16/08/2019 22:04:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '库存表主键自增',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '商品库存件数',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
