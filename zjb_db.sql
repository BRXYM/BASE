-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zjb_db
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
-- Table structure for table `cla`
--

DROP TABLE IF EXISTS `cla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cla` (
  `cla_id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `cla_name` varchar(255) DEFAULT NULL COMMENT '课程名',
  `teach_id` int DEFAULT NULL COMMENT '教官id',
  `cla_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '授课时间',
  PRIMARY KEY (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1033 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cla`
--

LOCK TABLES `cla` WRITE;
/*!40000 ALTER TABLE `cla` DISABLE KEYS */;
INSERT INTO `cla` VALUES (1000,'最后一课',1000,'2025-01-20 01:51:21'),(1001,'最后二课',1000,'2025-01-20 01:51:21'),(1002,'最后三课',1000,'2025-01-20 01:51:21'),(1003,'最后四课',1000,'2025-01-20 01:51:21'),(1004,'最后五课',1000,'2025-01-20 01:51:21'),(1005,'最后六课',1000,'2025-01-20 01:51:21'),(1006,'最后七课',1000,'2025-01-20 01:51:21'),(1007,'最后七课',1001,'2025-01-20 01:51:21'),(1008,'最后七课',1001,'2025-01-20 01:51:21'),(1009,'最后七课',1001,'2025-01-20 01:51:21'),(1010,'最后七课',1001,'2025-01-20 01:51:21'),(1011,'最后七课',1001,'2025-01-20 01:51:21'),(1012,'最后七课',1001,'2025-01-20 01:51:21'),(1013,'最后七课',1001,'2025-01-20 01:51:21'),(1014,'最后七课',1001,'2025-01-20 01:51:21'),(1015,'最后七课',1001,'2025-01-20 01:51:21'),(1016,'最后七课',1001,'2025-01-20 01:51:21'),(1017,'最后五课',1002,'2025-01-20 01:51:21'),(1018,'最后五课',1002,'2025-01-20 01:51:21'),(1019,'最后五课',1002,'2025-01-20 01:51:21'),(1020,'最后五课',1002,'2025-01-20 01:51:21'),(1021,'最后五课',1002,'2025-01-20 01:51:21'),(1022,'最后五课',1002,'2025-01-20 01:51:21'),(1023,'最后五课',1002,'2025-01-20 01:51:21'),(1024,'最后五课',1002,'2025-01-20 01:51:21'),(1025,'最后五课',1002,'2025-01-20 01:51:21'),(1026,'最后五课',1002,'2025-01-20 01:51:21'),(1027,'最后二课',1003,'2025-01-20 01:51:21'),(1028,'最后二课',1003,'2025-01-20 01:51:21'),(1029,'最后二课',1003,'2025-01-20 01:51:21'),(1030,'最后二课',1003,'2025-01-20 01:51:21'),(1031,'最后二课',1003,'2025-01-20 01:51:21'),(1032,'最后二课',1003,'2025-01-20 01:51:21');
/*!40000 ALTER TABLE `cla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `good` (
  `good_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `good_txt` varchar(500) DEFAULT NULL COMMENT '商品备注',
  `good_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `good_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `good_num` int DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`good_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (1000,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,35),(1001,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40),(1002,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,28),(1003,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40),(1004,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40),(1005,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40),(1006,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,33),(1007,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,35),(1008,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40),(1009,'农夫三拳','农夫三拳有点甜','农夫山泉.png',2.00,40);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ord`
--

DROP TABLE IF EXISTS `ord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ord` (
  `ord_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `good_id` int DEFAULT NULL COMMENT '商品id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `ord_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
  `ord_num` int DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`ord_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ord`
--

LOCK TABLES `ord` WRITE;
/*!40000 ALTER TABLE `ord` DISABLE KEYS */;
INSERT INTO `ord` VALUES (1,1006,1000,'2025-01-20 01:36:59',1),(2,1006,1000,'2025-01-20 01:38:09',1),(3,1006,1000,'2025-01-20 01:38:09',1),(4,1006,1000,'2025-01-20 01:38:09',1),(5,1006,1000,'2025-01-20 01:38:10',1),(6,1006,1000,'2025-01-20 01:38:10',1),(7,1006,1000,'2025-01-20 01:43:02',1),(8,1002,1000,'2025-01-20 01:45:55',4),(9,1002,1000,'2025-01-20 01:46:13',8),(10,1000,1000,'2025-01-20 01:46:20',5),(11,1000,1001,'2025-01-20 01:46:20',5),(12,1000,1001,'2025-01-20 01:46:20',5),(13,1007,1001,'2025-01-20 02:01:19',5);
/*!40000 ALTER TABLE `ord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sela`
--

DROP TABLE IF EXISTS `sela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sela` (
  `sela_id` int NOT NULL AUTO_INCREMENT COMMENT '选课id',
  `cla_id` int DEFAULT NULL COMMENT '课程id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `sela_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
  PRIMARY KEY (`sela_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sela`
--

LOCK TABLES `sela` WRITE;
/*!40000 ALTER TABLE `sela` DISABLE KEYS */;
INSERT INTO `sela` VALUES (1000,1002,1000,'2025-01-20 02:25:24'),(1001,1002,1000,'2025-01-20 02:25:24'),(1002,1003,1000,'2025-01-20 02:27:53');
/*!40000 ALTER TABLE `sela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach`
--

DROP TABLE IF EXISTS `teach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teach` (
  `teach_id` int NOT NULL AUTO_INCREMENT COMMENT '教练id',
  `teach_name` varchar(255) DEFAULT NULL COMMENT '教练姓名',
  `teach_img` varchar(500) DEFAULT NULL COMMENT '教练头像',
  `teach_phone` varchar(20) DEFAULT NULL COMMENT '教练手机号',
  `teach_pass` varchar(255) DEFAULT NULL COMMENT '教练密码',
  `teach_txt` varchar(500) DEFAULT NULL COMMENT '教师简介',
  PRIMARY KEY (`teach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教练表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach`
--

LOCK TABLES `teach` WRITE;
/*!40000 ALTER TABLE `teach` DISABLE KEYS */;
INSERT INTO `teach` VALUES (1000,'这是教师名字1','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1001,'这是教师名字2','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1002,'这是教师名字3','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1003,'这是教师名字4','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1004,'这是教师名字5','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1005,'这是教师名字6','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1006,'这是教师名字7','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1007,'这是教师名字8','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师'),(1008,'这是教师名字9','125445262_p0.jpg','12312312345','123','好教师啊好教师，这是一个好教师');
/*!40000 ALTER TABLE `teach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '学员id',
  `user_img` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(255) DEFAULT NULL COMMENT '学员姓名',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '学员手机号',
  `user_pass` varchar(255) DEFAULT NULL COMMENT '学员密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学院表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1000,'88353494_p0.jpg','123','123','123'),(1001,'88353494_p0.jpg','111','111','111'),(1002,'hutao.jpg','222','222','222');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-20  3:53:05
