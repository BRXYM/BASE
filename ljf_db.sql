-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ljf_db
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ljf_admin`
--

DROP TABLE IF EXISTS `ljf_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_admin` (
  `ljf_adm_id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `ljf_adm_name` varchar(255) DEFAULT NULL COMMENT '管理员姓名',
  `ljf_adm_phone` varchar(255) DEFAULT NULL COMMENT '管理员手机号',
  `ljf_adm_psword` varchar(255) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`ljf_adm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_admin`
--

LOCK TABLES `ljf_admin` WRITE;
/*!40000 ALTER TABLE `ljf_admin` DISABLE KEYS */;
INSERT INTO `ljf_admin` VALUES (1000,'管理员','123456789','123456');
/*!40000 ALTER TABLE `ljf_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_ads`
--

DROP TABLE IF EXISTS `ljf_ads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_ads` (
  `ljf_ads_id` int NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `ljf_ads_txt` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`ljf_ads_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_ads`
--

LOCK TABLES `ljf_ads` WRITE;
/*!40000 ALTER TABLE `ljf_ads` DISABLE KEYS */;
INSERT INTO `ljf_ads` VALUES (1000,'大连交通大学');
/*!40000 ALTER TABLE `ljf_ads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_good`
--

DROP TABLE IF EXISTS `ljf_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_good` (
  `ljf_good_id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `ljf_good_sell_id` int DEFAULT NULL COMMENT '商家ID',
  `ljf_good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `ljf_good_num` int DEFAULT NULL COMMENT '商品数量',
  `ljf_good_txt` varchar(500) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`ljf_good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_good`
--

LOCK TABLES `ljf_good` WRITE;
/*!40000 ALTER TABLE `ljf_good` DISABLE KEYS */;
INSERT INTO `ljf_good` VALUES (1000,1000,'农夫山泉',20,'大自然的搬运工');
/*!40000 ALTER TABLE `ljf_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_mess`
--

DROP TABLE IF EXISTS `ljf_mess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_mess` (
  `ljf_mess_id` int NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `ljf_mess_txt` varchar(255) DEFAULT NULL COMMENT '留言内容',
  `ljf_mess_order_id` int DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`ljf_mess_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_mess`
--

LOCK TABLES `ljf_mess` WRITE;
/*!40000 ALTER TABLE `ljf_mess` DISABLE KEYS */;
INSERT INTO `ljf_mess` VALUES (1000,'农夫山泉有点甜',1000);
/*!40000 ALTER TABLE `ljf_mess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_order`
--

DROP TABLE IF EXISTS `ljf_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_order` (
  `ljf_order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `ljf_order_good_id` int DEFAULT NULL COMMENT '商品ID',
  `ljf_order_user_id` int DEFAULT NULL COMMENT '用户ID',
  `ljf_order_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
  PRIMARY KEY (`ljf_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_order`
--

LOCK TABLES `ljf_order` WRITE;
/*!40000 ALTER TABLE `ljf_order` DISABLE KEYS */;
INSERT INTO `ljf_order` VALUES (1000,1000,1000,'2024-11-30 20:47:09');
/*!40000 ALTER TABLE `ljf_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_sell`
--

DROP TABLE IF EXISTS `ljf_sell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_sell` (
  `ljf_sell_id` int NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `ljf_sell_phone` varchar(50) DEFAULT NULL COMMENT '商家手机号',
  `ljf_sell_psword` varchar(255) DEFAULT NULL COMMENT '商家密码',
  `ljf_sell_name` varchar(255) DEFAULT NULL COMMENT '商家姓名',
  PRIMARY KEY (`ljf_sell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_sell`
--

LOCK TABLES `ljf_sell` WRITE;
/*!40000 ALTER TABLE `ljf_sell` DISABLE KEYS */;
INSERT INTO `ljf_sell` VALUES (1000,'123456789','123456','商家');
/*!40000 ALTER TABLE `ljf_sell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ljf_user`
--

DROP TABLE IF EXISTS `ljf_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ljf_user` (
  `ljf_user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `ljf_user_phone` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `ljf_user_psword` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `ljf_user_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `ljf_user_ads_id` int DEFAULT NULL,
  PRIMARY KEY (`ljf_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ljf_user`
--

LOCK TABLES `ljf_user` WRITE;
/*!40000 ALTER TABLE `ljf_user` DISABLE KEYS */;
INSERT INTO `ljf_user` VALUES (1000,'123456789','123456','ljf',1000),(1001,'','','',0);
/*!40000 ALTER TABLE `ljf_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-30 23:23:00
