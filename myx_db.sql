-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: myx_db
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `myxc`
--

DROP TABLE IF EXISTS `myxc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxc` (
  `myxCid` int NOT NULL AUTO_INCREMENT COMMENT '收藏表ID',
  `myxCUid` int DEFAULT NULL COMMENT '收藏人ID',
  `myxCPid` int DEFAULT NULL COMMENT '发布表ID',
  `myxCtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  `myxCena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxCid`),
  KEY `myxc_P_id_fk` (`myxCPid`),
  KEY `myxc_U_id_fk` (`myxCUid`),
  CONSTRAINT `myxc_P_id_fk` FOREIGN KEY (`myxCPid`) REFERENCES `myxp` (`myxPid`),
  CONSTRAINT `myxc_U_id_fk` FOREIGN KEY (`myxCUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏表collection';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxc`
--

LOCK TABLES `myxc` WRITE;
/*!40000 ALTER TABLE `myxc` DISABLE KEYS */;
INSERT INTO `myxc` VALUES (10000,10000,10000,'2024-11-25 14:03:31',1),(10001,10001,10001,'2024-11-25 14:03:43',1),(10002,10001,10001,'2024-11-25 14:03:43',1);
/*!40000 ALTER TABLE `myxc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxh`
--

DROP TABLE IF EXISTS `myxh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxh` (
  `myxHid` int NOT NULL AUTO_INCREMENT COMMENT '浏览记录ID',
  `myxHUid` int DEFAULT NULL COMMENT '浏览人ID',
  `myxHPid` int DEFAULT NULL COMMENT '发布表ID',
  `myxHtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  `myxHena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxHid`),
  KEY `myxH_P_id_fk` (`myxHPid`),
  KEY `myxH_U_id_fk` (`myxHUid`),
  CONSTRAINT `myxH_P_id_fk` FOREIGN KEY (`myxHPid`) REFERENCES `myxp` (`myxPid`),
  CONSTRAINT `myxH_U_id_fk` FOREIGN KEY (`myxHUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='历史浏览记录history';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxh`
--

LOCK TABLES `myxh` WRITE;
/*!40000 ALTER TABLE `myxh` DISABLE KEYS */;
INSERT INTO `myxh` VALUES (10000,10000,10000,'2024-11-25 14:07:09',1);
/*!40000 ALTER TABLE `myxh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxm`
--

DROP TABLE IF EXISTS `myxm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxm` (
  `myxMid` int NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `myxMFUid` int DEFAULT NULL COMMENT '发送用户ID',
  `myxMTUid` int DEFAULT NULL COMMENT '接收用户ID',
  `myxMtxt` varchar(500) DEFAULT NULL COMMENT '留言内容',
  `myxMtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言时间',
  `myxMena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxMid`),
  KEY `myxM_FU_id_fk` (`myxMFUid`),
  KEY `myxM_TU_id_fk` (`myxMTUid`),
  CONSTRAINT `myxM_FU_id_fk` FOREIGN KEY (`myxMFUid`) REFERENCES `myxu` (`myxUid`),
  CONSTRAINT `myxM_TU_id_fk` FOREIGN KEY (`myxMTUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言表message';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxm`
--

LOCK TABLES `myxm` WRITE;
/*!40000 ALTER TABLE `myxm` DISABLE KEYS */;
INSERT INTO `myxm` VALUES (10000,10000,10001,'这里是留言','2024-11-25 14:07:27',1),(10001,10001,10000,'这里是留言','2024-11-25 14:07:49',1);
/*!40000 ALTER TABLE `myxm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxo`
--

DROP TABLE IF EXISTS `myxo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxo` (
  `myxOid` int NOT NULL AUTO_INCREMENT COMMENT '接取表ID',
  `myxOUid` int DEFAULT NULL COMMENT '接取人ID',
  `myxOPid` int DEFAULT NULL COMMENT '接取表ID',
  `myxOtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接取时间',
  `myxOena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxOid`),
  KEY `myxo_P_id_fk` (`myxOPid`),
  KEY `myxo_U_id_fk` (`myxOUid`),
  CONSTRAINT `myxo_P_id_fk` FOREIGN KEY (`myxOPid`) REFERENCES `myxp` (`myxPid`),
  CONSTRAINT `myxo_U_id_fk` FOREIGN KEY (`myxOUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='接取表order';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxo`
--

LOCK TABLES `myxo` WRITE;
/*!40000 ALTER TABLE `myxo` DISABLE KEYS */;
INSERT INTO `myxo` VALUES (10000,10000,10000,'2024-11-25 14:04:15',1),(10001,10001,10001,'2024-11-25 14:04:25',1);
/*!40000 ALTER TABLE `myxo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxp`
--

DROP TABLE IF EXISTS `myxp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxp` (
  `myxPid` int NOT NULL AUTO_INCREMENT COMMENT '发布表ID',
  `myxPTid` int DEFAULT NULL COMMENT '发布分类ID',
  `myxPUid` int DEFAULT NULL COMMENT '发布人ID',
  `myxPcon` varchar(500) DEFAULT NULL COMMENT '发布描述',
  `myxPtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `myxPena` tinyint(1) DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxPid`),
  KEY `myxP_T_id_fk` (`myxPTid`),
  KEY `myxP_U_id_fk` (`myxPUid`),
  CONSTRAINT `myxP_T_id_fk` FOREIGN KEY (`myxPTid`) REFERENCES `myxt` (`myxTid`),
  CONSTRAINT `myxP_U_id_fk` FOREIGN KEY (`myxPUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布表publish';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxp`
--

LOCK TABLES `myxp` WRITE;
/*!40000 ALTER TABLE `myxp` DISABLE KEYS */;
INSERT INTO `myxp` VALUES (10000,10000,10000,'这里是内容','2024-11-25 14:01:20',1),(10001,10001,10001,'这里是内容','2024-11-25 14:01:44',1);
/*!40000 ALTER TABLE `myxp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxr`
--

DROP TABLE IF EXISTS `myxr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxr` (
  `myxRid` int NOT NULL AUTO_INCREMENT COMMENT '评论表ID',
  `myxRUid` int DEFAULT NULL COMMENT '评论人ID',
  `myxRPid` int DEFAULT NULL COMMENT '发布表ID',
  `myxRtxt` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `myxRtim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `myxRena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxRid`),
  KEY `myxr_P_id_fk` (`myxRPid`),
  KEY `myxr_U_id_fk` (`myxRUid`),
  CONSTRAINT `myxr_P_id_fk` FOREIGN KEY (`myxRPid`) REFERENCES `myxp` (`myxPid`),
  CONSTRAINT `myxr_U_id_fk` FOREIGN KEY (`myxRUid`) REFERENCES `myxu` (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表review';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxr`
--

LOCK TABLES `myxr` WRITE;
/*!40000 ALTER TABLE `myxr` DISABLE KEYS */;
INSERT INTO `myxr` VALUES (10000,10000,10000,'这里是评论','2024-11-25 14:04:54',1),(10001,10001,10001,'这里是评论','2024-11-25 14:06:27',1);
/*!40000 ALTER TABLE `myxr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxt`
--

DROP TABLE IF EXISTS `myxt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxt` (
  `myxTid` int NOT NULL AUTO_INCREMENT COMMENT '发布分类ID',
  `myxTmod` varchar(50) DEFAULT NULL COMMENT '发布类型',
  `myxTena` tinyint(1) DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxTid`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='发布分类type';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxt`
--

LOCK TABLES `myxt` WRITE;
/*!40000 ALTER TABLE `myxt` DISABLE KEYS */;
INSERT INTO `myxt` VALUES (10000,'日常',1),(10001,'猫猫',1),(10002,'校园活动',1),(10003,'情感',1),(10004,'吐槽',1),(10005,'避坑',1);
/*!40000 ALTER TABLE `myxt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myxu`
--

DROP TABLE IF EXISTS `myxu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myxu` (
  `myxUid` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `myxUpho` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `myxUpas` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `myxUnam` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `myxUena` tinyint(1) NOT NULL DEFAULT '1' COMMENT '存在状态',
  PRIMARY KEY (`myxUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myxu`
--

LOCK TABLES `myxu` WRITE;
/*!40000 ALTER TABLE `myxu` DISABLE KEYS */;
INSERT INTO `myxu` VALUES (10000,'18245782879','123456','本人想养喵',1),(10001,'15845970528','123456','白堕',1);
/*!40000 ALTER TABLE `myxu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-25 14:08:40
