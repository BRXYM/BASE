/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : waimai

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 02/12/2024 22:51:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_ID` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_password` varchar(255) DEFAULT NULL,
  `admin_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, '123', '123', '18646731878');
COMMIT;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cart_ID` int NOT NULL AUTO_INCREMENT,
  `goods_ID` int DEFAULT NULL,
  `merchant_ID` int DEFAULT NULL,
  `user_ID` int DEFAULT NULL,
  PRIMARY KEY (`cart_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES (1, 0, 1, 0);
INSERT INTO `cart` VALUES (2, 0, 1, 0);
INSERT INTO `cart` VALUES (4, 0, 1, 0);
INSERT INTO `cart` VALUES (7, 1, 1, 1);
INSERT INTO `cart` VALUES (8, 1, 1, 1);
INSERT INTO `cart` VALUES (9, 1, 1, 1);
INSERT INTO `cart` VALUES (10, 1, 1, 1);
INSERT INTO `cart` VALUES (11, 1, 1, 1);
INSERT INTO `cart` VALUES (12, 1, 1, 1);
INSERT INTO `cart` VALUES (13, 1, 1, 1);
INSERT INTO `cart` VALUES (14, 1, 1, 1);
INSERT INTO `cart` VALUES (15, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `collection_ID` int NOT NULL AUTO_INCREMENT,
  `goods_ID` int DEFAULT NULL,
  `merchant_ID` int DEFAULT NULL,
  `user_ID` int DEFAULT NULL,
  PRIMARY KEY (`collection_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of collection
-- ----------------------------
BEGIN;
INSERT INTO `collection` VALUES (4, 1, 1, 1);
INSERT INTO `collection` VALUES (5, 2, 1, 1);
INSERT INTO `collection` VALUES (6, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for deliveryBox
-- ----------------------------
DROP TABLE IF EXISTS `deliveryBox`;
CREATE TABLE `deliveryBox` (
  `deliveryBox_ID` int NOT NULL AUTO_INCREMENT,
  `order_ID` int DEFAULT NULL,
  `deliveryPerson_ID` int DEFAULT NULL,
  `user_ID` int DEFAULT NULL,
  `deliveryBox_state` varchar(255) DEFAULT NULL,
  `deliveryBox_address` varchar(255) DEFAULT NULL,
  `deliveryBox_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deliveryBox_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of deliveryBox
-- ----------------------------
BEGIN;
INSERT INTO `deliveryBox` VALUES (1, 1, 1, 1, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for deliveryPerson
-- ----------------------------
DROP TABLE IF EXISTS `deliveryPerson`;
CREATE TABLE `deliveryPerson` (
  `deliveryPerson_ID` int NOT NULL AUTO_INCREMENT,
  `deliveryPerson_name` varchar(255) DEFAULT NULL,
  `deliveryPerson_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deliveryPerson_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_ID` int NOT NULL AUTO_INCREMENT,
  `merchant_ID` int DEFAULT NULL,
  `goods_num` int DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_text` varchar(255) DEFAULT NULL,
  `goods_photo1` varchar(255) DEFAULT NULL,
  `goods_photo2` varchar(255) DEFAULT NULL,
  `goods_photo3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`goods_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (1, 1, 100, 35.00, '烤肉拌饭经典', '美味的烤肉拌饭，搭配新鲜蔬菜。', 'https://pic.rmb.bdstatic.com/bjh/down/78dca1d9ed857ac8bc61d030eab506e4.jpeg', '', '');
INSERT INTO `goods` VALUES (2, 1, 200, 38.00, '烤肉拌饭豪华版', '精选优质肉类，豪华享受。', 'https://p3.ssl.qhimgs3.com/t013405871d15f955bc.jpg', '', '');
INSERT INTO `goods` VALUES (3, 1, 150, 30.00, '烤肉拌饭特辣', '热辣风味，挑战你的味蕾。', 'https://pic.rmb.bdstatic.com/bjh/down/78dca1d9ed857ac8bc61d030eab506e4.jpeg', '', '');
INSERT INTO `goods` VALUES (4, 1, 80, 32.00, '烤肉拌饭双拼', '双肉搭配，满足你的需求。', 'https://img0.baidu.com/it/u=1064585061,3050005528&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=800', '', '');
INSERT INTO `goods` VALUES (5, 2, 120, 15.00, '酸辣粉经典', '酸辣可口，十分开胃。', 'https://q2.itc.cn/q_70/images01/20240319/d8ebf0e9d08446a987eb3642175c17eb.jpeg', '', '');
INSERT INTO `goods` VALUES (6, 2, 80, 18.00, '酸辣粉大份', '加量不加价，满足食欲。', 'https://q0.itc.cn/images01/20240319/28563b71967842d396e42c4fe7b2aaaf.jpeg', '', '');
INSERT INTO `goods` VALUES (7, 2, 100, 20.00, '酸辣粉加蛋', '加入鸡蛋，营养更丰富。', 'https://imgservice.suning.cn/uimg1/b2c/image/kKDkoIy0yOmy3uIIRHTeJQ.jpg_800w_800h_4e', '', '');
INSERT INTO `goods` VALUES (8, 2, 90, 22.00, '酸辣粉拼豆腐', '豆腐搭配，更加健康。', 'https://copyright.bdstatic.com/vcg/creative/df8cd0b8b88f47dc9d23c62d3454b30a.jpg@wm_1,k_cGljX2JqaHdhdGVyLmpwZw==', '', '');
INSERT INTO `goods` VALUES (9, 3, 110, 25.00, '过桥米线原味', '原汁原味，口感一流。', 'https://q9.itc.cn/images01/20240304/754e2c17687245b2ad78e8c547bfde17.jpeg', '', '');
INSERT INTO `goods` VALUES (10, 3, 130, 28.00, '过桥米线双拼', '双肉选择，尽享美味。', 'https://pic.rmb.bdstatic.com/bjh/c82957acd038aedddbfea3c6592f2baf1376.jpeg', '', '');
INSERT INTO `goods` VALUES (11, 3, 90, 26.00, '过桥米线豪华版', '升级配料，吃得更满足。', 'https://su.bcebos.com/b2b-jiameng/dev/2020/07/29/f877a98cd3f183a09e02d39c25d54c90c322872a?w=600&h=400&s=118595&real_w=3132&real_h=2088&x-bce-process=image/resize,m_lfit,w_3132/format,f_jpg/quality,q_80', '', '');
INSERT INTO `goods` VALUES (12, 3, 70, 27.00, '过桥米线麻辣', '麻辣风味，喜欢刺激的你。', 'https://su.bcebos.com/b2b-jiameng/dev/2020/07/29/7aff20e7e6d5da650edfd03dc9aebace0d66c96f?w=600&h=400&s=83803&real_w=3132&real_h=2088&x-bce-process=image/resize,m_lfit,w_3132/quality,q_80', '', '');
INSERT INTO `goods` VALUES (13, 4, 200, 30.00, '排骨饭经典', '鲜美排骨，饭香四溢。', 'https://img2.baidu.com/it/u=1311707851,869730698&fm=253&fmt=auto&app=138&f=JPEG?w=749&h=500', '', '');
INSERT INTO `goods` VALUES (14, 4, 180, 32.00, '排骨饭大份', '大份量，尽情享受。', 'https://img2.baidu.com/it/u=3987554526,897933293&fm=253&fmt=auto&app=138&f=PNG?w=435&h=482', '', '');
INSERT INTO `goods` VALUES (15, 4, 150, 35.00, '排骨饭特辣', '辣味更佳，挑战你的味蕾。', 'https://img1.baidu.com/it/u=296885095,3812258590&fm=253&fmt=auto&app=138&f=JPEG?w=482&h=264', '', '');
INSERT INTO `goods` VALUES (16, 4, 160, 33.00, '排骨饭双拼', '双重享受，满满的幸福感。', 'https://img2.baidu.com/it/u=2511260919,2977405801&fm=253&fmt=auto&app=138&f=JPEG?w=746&h=500', '', '');
INSERT INTO `goods` VALUES (17, 5, 90, 20.00, '熏肉大饼经典', '外脆内软，熏肉香浓。', 'https://pic4.zhimg.com/v2-959b4cedbb0bd4b9f817766fd5315a73_r.jpg', '', '');
INSERT INTO `goods` VALUES (18, 5, 80, 22.00, '熏肉大饼双拼', '双重肉感，满足你的味蕾。', 'https://b0.bdstatic.com/025a8fe25c347a675bbe9d7fba8d6c4e.jpg', '', '');
INSERT INTO `goods` VALUES (19, 5, 70, 25.00, '熏肉大饼特辣', '加辣，劲爽十足。', 'http://img1.baidu.com/it/u=667214650,3458939237&fm=253&app=138&f=JPEG?w=800&h=1067', '', '');
INSERT INTO `goods` VALUES (20, 5, 60, 23.00, '熏肉大饼拼蔬菜', '搭配新鲜蔬菜，健康美味。', 'https://qcloud.dpfile.com/pc/UHjGsNZFBpD4RS6DPSLhG_rEpfTqDdph5d7E7BKHlXIAKbWlwfX4ZvxgogdetSZd.jpg', '', '');
COMMIT;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `merchant_ID` int NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(255) DEFAULT NULL,
  `merchant_phone` varchar(255) DEFAULT NULL,
  `merchant_photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`merchant_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of merchant
-- ----------------------------
BEGIN;
INSERT INTO `merchant` VALUES (1, '烤肉拌饭', '13800000001', 'https://img0.baidu.com/it/u=2577389729,3432802356&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=1067');
INSERT INTO `merchant` VALUES (2, '酸辣粉', '13800000002', 'https://img1.baidu.com/it/u=3826670260,146192392&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=704');
INSERT INTO `merchant` VALUES (3, '过桥米线', '13800000003', 'https://img.tukuppt.com/preview/00/46/05/29/46052962a983891456ashow.jpg');
INSERT INTO `merchant` VALUES (4, '排骨饭', '13800000004', 'https://img.redocn.com/sheji/20220311/paigufanhaibao_12243089.jpg.285.jpg');
INSERT INTO `merchant` VALUES (5, '熏肉大饼', '13800000005', 'https://img2.baidu.com/it/u=1666731339,3162420729&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=750');
COMMIT;

-- ----------------------------
-- Table structure for oorder
-- ----------------------------
DROP TABLE IF EXISTS `oorder`;
CREATE TABLE `oorder` (
  `order_ID` int NOT NULL AUTO_INCREMENT,
  `merchant_ID` int DEFAULT NULL,
  `goods_ID` int DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `order_state` varchar(255) DEFAULT NULL,
  `order_address` varchar(255) DEFAULT NULL,
  `user_ID` int DEFAULT NULL,
  PRIMARY KEY (`order_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oorder
-- ----------------------------
BEGIN;
INSERT INTO `oorder` VALUES (1, 0, 0, '2024-10-17 13:11:37', '已送达', NULL, 1);
INSERT INTO `oorder` VALUES (2, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oorder` VALUES (3, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oorder` VALUES (4, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oorder` VALUES (5, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oorder` VALUES (6, 1, 1, NULL, '配送中', NULL, NULL);
INSERT INTO `oorder` VALUES (7, 1, 1, NULL, '配送中', NULL, 1);
INSERT INTO `oorder` VALUES (8, 1, 1, '2024-12-02 15:51:34', '配送中', NULL, 1);
INSERT INTO `oorder` VALUES (9, 1, 1, '2024-12-02 15:52:02', '配送中', NULL, 1);
INSERT INTO `oorder` VALUES (10, 1, 1, '2024-12-02 15:52:21', '配送中', NULL, 1);
INSERT INTO `oorder` VALUES (11, 0, 0, '2024-12-02 15:52:21', '配送中', '忻州', 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_ID` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '123', '123', '18646731878123', '忻州繁峙');
INSERT INTO `user` VALUES (2, '1234', '1234', NULL, NULL);
INSERT INTO `user` VALUES (3, '12', '12', '12', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
