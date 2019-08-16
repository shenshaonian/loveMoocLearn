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

 Date: 16/08/2019 22:04:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '秒杀活动自增id',
  `promo_name` varchar(255) NOT NULL DEFAULT '' COMMENT '秒杀商品名',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '活动开始时间',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品编号-对用商品表',
  `promo_item_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品秒杀价格',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '活动结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
