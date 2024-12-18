-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hys_db
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
-- Table structure for table `hysa`
--

DROP TABLE IF EXISTS `hysa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hysa` (
  `hysAid` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `hysAname` varchar(50) DEFAULT NULL COMMENT '管理员姓名',
  `hysAphone` varchar(11) DEFAULT NULL COMMENT '管理员手机号',
  `hysAmail` varchar(50) DEFAULT NULL COMMENT '管理员邮箱',
  `hysApass` varchar(100) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`hysAid`)
) ENGINE=InnoDB AUTO_INCREMENT=90000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hysa`
--

LOCK TABLES `hysa` WRITE;
/*!40000 ALTER TABLE `hysa` DISABLE KEYS */;
/*!40000 ALTER TABLE `hysa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hysc`
--

DROP TABLE IF EXISTS `hysc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hysc` (
  `hysCid` int NOT NULL AUTO_INCREMENT COMMENT '评论表ID',
  `hysCidU` int DEFAULT NULL COMMENT '用户id',
  `hysCidMO` int DEFAULT NULL COMMENT '模组id',
  `hysCtxt` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `hysCtime` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`hysCid`),
  KEY `hysC_id_MO_fk` (`hysCidMO`),
  KEY `hysC_id_U_fk` (`hysCidU`),
  CONSTRAINT `hysC_id_MO_fk` FOREIGN KEY (`hysCidMO`) REFERENCES `hysmo` (`hysMOid`),
  CONSTRAINT `hysC_id_U_fk` FOREIGN KEY (`hysCidU`) REFERENCES `hysu` (`hysUid`)
) ENGINE=InnoDB AUTO_INCREMENT=30000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hysc`
--

LOCK TABLES `hysc` WRITE;
/*!40000 ALTER TABLE `hysc` DISABLE KEYS */;
/*!40000 ALTER TABLE `hysc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hysme`
--

DROP TABLE IF EXISTS `hysme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hysme` (
  `hysMEid` int NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `hysMEtxt` varchar(500) DEFAULT NULL COMMENT '内容',
  `hysMEtime` datetime DEFAULT NULL COMMENT '时间',
  `hysMEtoid` int DEFAULT NULL COMMENT '被留言ID',
  `hysMEidU` int DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`hysMEid`),
  KEY `hysME_id_U_fk` (`hysMEidU`),
  KEY `hysME_toid_U_fk` (`hysMEtoid`),
  CONSTRAINT `hysME_id_U_fk` FOREIGN KEY (`hysMEidU`) REFERENCES `hysu` (`hysUid`),
  CONSTRAINT `hysME_toid_U_fk` FOREIGN KEY (`hysMEtoid`) REFERENCES `hysu` (`hysUid`)
) ENGINE=InnoDB AUTO_INCREMENT=50000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hysme`
--

LOCK TABLES `hysme` WRITE;
/*!40000 ALTER TABLE `hysme` DISABLE KEYS */;
/*!40000 ALTER TABLE `hysme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hysmo`
--

DROP TABLE IF EXISTS `hysmo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hysmo` (
  `hysMOid` int NOT NULL AUTO_INCREMENT COMMENT '模组ID',
  `hysMOidT` int DEFAULT NULL COMMENT '类ID',
  `hysMOidU` int DEFAULT NULL COMMENT '上传者id',
  `hysMOtime` datetime DEFAULT NULL COMMENT '上传时间',
  `hysMOtxt` varchar(200) DEFAULT NULL COMMENT '模组内容',
  `hysMOsum` int DEFAULT NULL COMMENT '收藏数',
  PRIMARY KEY (`hysMOid`),
  KEY `hysMO_id_T_fk` (`hysMOidT`),
  KEY `hysmo_id_U_fk` (`hysMOidU`),
  CONSTRAINT `hysMO_id_T_fk` FOREIGN KEY (`hysMOidT`) REFERENCES `hyst` (`hysTid`),
  CONSTRAINT `hysmo_id_U_fk` FOREIGN KEY (`hysMOidU`) REFERENCES `hysu` (`hysUid`)
) ENGINE=InnoDB AUTO_INCREMENT=20002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hysmo`
--

LOCK TABLES `hysmo` WRITE;
/*!40000 ALTER TABLE `hysmo` DISABLE KEYS */;
INSERT INTO `hysmo` VALUES (20000,60000,10000,'2024-11-07 14:49:29','这里是情感本',20),(20001,60001,10001,'2024-11-07 14:49:55','这里是推理本',30);
/*!40000 ALTER TABLE `hysmo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hyss`
--

DROP TABLE IF EXISTS `hyss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hyss` (
  `hysSid` int NOT NULL AUTO_INCREMENT COMMENT '收藏表id',
  `hysSidU` int DEFAULT NULL COMMENT '用户ID',
  `hysSidMO` int DEFAULT NULL COMMENT '模组id',
  `hysStime` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`hysSid`),
  KEY `hysS_id_MO_fk` (`hysSidMO`),
  KEY `hysS_id_U_fk` (`hysSidU`),
  CONSTRAINT `hysS_id_MO_fk` FOREIGN KEY (`hysSidMO`) REFERENCES `hysmo` (`hysMOid`),
  CONSTRAINT `hysS_id_U_fk` FOREIGN KEY (`hysSidU`) REFERENCES `hysu` (`hysUid`)
) ENGINE=InnoDB AUTO_INCREMENT=40000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hyss`
--

LOCK TABLES `hyss` WRITE;
/*!40000 ALTER TABLE `hyss` DISABLE KEYS */;
/*!40000 ALTER TABLE `hyss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hyst`
--

DROP TABLE IF EXISTS `hyst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hyst` (
  `hysTid` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `hysTname` varchar(200) DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`hysTid`)
) ENGINE=InnoDB AUTO_INCREMENT=60002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模组分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hyst`
--

LOCK TABLES `hyst` WRITE;
/*!40000 ALTER TABLE `hyst` DISABLE KEYS */;
INSERT INTO `hyst` VALUES (60000,'情感'),(60001,'推理');
/*!40000 ALTER TABLE `hyst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hysu`
--

DROP TABLE IF EXISTS `hysu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hysu` (
  `hysUid` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `hysUname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `hysUphone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `hysUqq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `hysUmile` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `hysUpass` varchar(50) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`hysUid`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hysu`
--

LOCK TABLES `hysu` WRITE;
/*!40000 ALTER TABLE `hysu` DISABLE KEYS */;
INSERT INTO `hysu` VALUES (10000,'胡芸殊','12345678911','1234567','1234@123.123','123456'),(10001,'白羊','12345678911','1234567','1234@123.123','123456'),(10003,'摩羯','12345678911','1234567','1234@123.123','123456'),(10004,'摩羯','123123','123213','123123','123456'),(10005,'','','','','');
/*!40000 ALTER TABLE `hysu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-18 16:08:20
