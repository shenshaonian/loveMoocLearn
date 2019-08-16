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

 Date: 16/08/2019 22:04:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) NOT NULL COMMENT '订单编号订单表',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `item_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '下单当时的商品单价',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `order_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `promo_id` int(11) NOT NULL DEFAULT '0' COMMENT '若不是0说明这个订单是秒杀订单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
