-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ats
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Current Database: `ats`
--

/*!40000 DROP DATABASE IF EXISTS `ats`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ats`;

--
-- Table structure for table `blanks`
--

DROP TABLE IF EXISTS `blanks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blanks` (
  `blankID` int NOT NULL,
  `Type` int NOT NULL,
  `BlankStatus` varchar(45) NOT NULL,
  `receiveDate` date NOT NULL,
  `assignedDate` date DEFAULT NULL,
  `employeesIDBlanks` int DEFAULT NULL,
  PRIMARY KEY (`blankID`,`Type`),
  KEY `blankEmployeesID_idx` (`employeesIDBlanks`),
  CONSTRAINT `blankEmployeesID` FOREIGN KEY (`employeesIDBlanks`) REFERENCES `employees` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blanks`
--

LOCK TABLES `blanks` WRITE;
/*!40000 ALTER TABLE `blanks` DISABLE KEYS */;
INSERT INTO `blanks` VALUES (1,440,'Received','2020-03-24',NULL,NULL),(1,444,'Sold','2019-04-01','2019-04-01',250),(2,444,'Refunded','2019-04-01','2019-04-01',250),(3,444,'Assigned','2019-04-01','2019-04-01',250),(4,444,'Assigned','2019-04-01','2019-04-01',250),(5,444,'Assigned','2019-04-01','2019-04-01',250),(6,444,'Assigned','2019-04-01','2019-04-01',250),(7,444,'Assigned','2019-04-01','2019-04-01',250),(8,444,'Assigned','2019-04-01','2019-04-01',250),(9,444,'Assigned','2019-04-01','2019-04-01',250),(10,444,'Assigned','2019-04-01','2019-04-01',250),(11,444,'Assigned','2019-04-01','2019-04-01',250),(12,444,'Assigned','2019-04-01','2019-04-01',250),(13,444,'Assigned','2019-04-01','2019-04-01',250),(14,444,'Assigned','2019-04-01','2019-04-01',250),(15,444,'Assigned','2019-04-01','2019-04-01',250),(16,444,'Assigned','2019-04-01','2019-04-01',250),(17,444,'Assigned','2019-04-01','2019-04-01',250),(18,444,'Assigned','2019-04-01','2019-04-01',250),(19,444,'Assigned','2019-04-01','2019-04-01',250),(20,444,'Assigned','2019-04-01','2019-04-01',250),(21,444,'Sold','2019-04-01','2019-04-05',211),(22,444,'Sold','2019-04-01','2019-04-05',211),(23,444,'Assigned','2019-04-01','2019-04-05',211),(24,444,'Assigned','2019-04-01','2019-04-05',211),(25,444,'Assigned','2019-04-01','2019-04-05',211),(26,444,'Assigned','2019-04-01','2019-04-05',211),(27,444,'Assigned','2019-04-01','2019-04-05',211),(28,444,'Assigned','2019-04-01','2019-04-05',211),(29,444,'Assigned','2019-04-01','2019-04-05',211),(30,444,'Assigned','2019-04-01','2019-04-05',211),(31,444,'Assigned','2019-04-01','2019-04-05',211),(32,444,'Assigned','2019-04-01','2019-04-05',211),(33,444,'Assigned','2019-04-01','2019-04-05',211),(34,444,'Assigned','2019-04-01','2019-04-05',211),(35,444,'Assigned','2019-04-01','2019-04-05',211),(36,444,'Assigned','2019-04-01','2019-04-05',211),(37,444,'Assigned','2019-04-01','2019-04-05',211),(38,444,'Assigned','2019-04-01','2019-04-05',211),(39,444,'Assigned','2019-04-01','2019-04-05',211),(40,444,'Assigned','2019-04-01','2019-04-05',211),(41,444,'Assigned','2019-04-01','2020-03-24',211),(42,444,'Assigned','2019-04-01','2020-03-24',211),(43,444,'Received','2019-04-01',NULL,NULL),(44,444,'Received','2019-04-01',NULL,NULL),(45,444,'Received','2019-04-01',NULL,NULL),(46,444,'Received','2019-04-01',NULL,NULL),(47,444,'Received','2019-04-01',NULL,NULL),(48,444,'Assigned','2019-04-01','2020-03-24',211),(49,444,'Assigned','2019-04-01','2020-03-24',211),(50,444,'Received','2019-04-01',NULL,NULL),(51,444,'Received','2019-04-01',NULL,NULL),(52,444,'Received','2019-04-01',NULL,NULL),(53,444,'Received','2019-04-01',NULL,NULL),(54,444,'Received','2019-04-01',NULL,NULL),(55,444,'Received','2019-04-01',NULL,NULL),(58,444,'Received','2019-04-01',NULL,NULL),(59,444,'Received','2019-04-01',NULL,NULL),(60,444,'Received','2019-04-01',NULL,NULL),(64,444,'Received','2019-04-01',NULL,NULL),(68,444,'Received','2019-04-01',NULL,NULL),(70,444,'Received','2019-04-01',NULL,NULL),(71,444,'Received','2019-04-01',NULL,NULL),(72,444,'Received','2019-04-01',NULL,NULL),(73,444,'Received','2019-04-01',NULL,NULL),(75,444,'Received','2019-04-01',NULL,NULL),(83,444,'Received','2019-04-01',NULL,NULL),(85,444,'Received','2019-04-01',NULL,NULL),(86,444,'Received','2019-04-01',NULL,NULL),(87,444,'Received','2019-04-01',NULL,NULL),(88,444,'Received','2019-04-01',NULL,NULL),(89,444,'Received','2019-04-01',NULL,NULL),(90,444,'Received','2019-04-01',NULL,NULL),(91,444,'Received','2019-04-01',NULL,NULL),(92,444,'Received','2019-04-01',NULL,NULL),(93,444,'Received','2019-04-01',NULL,NULL),(94,444,'Received','2019-04-01',NULL,NULL),(95,444,'Received','2019-04-01',NULL,NULL),(96,444,'Received','2019-04-01',NULL,NULL),(97,444,'Received','2019-04-01',NULL,NULL),(98,444,'Received','2019-04-01',NULL,NULL),(99,444,'Received','2019-04-01',NULL,NULL),(101,420,'Sold','2019-05-08','2019-05-08',250),(102,420,'Assigned','2019-05-08','2019-05-08',250),(103,420,'Assigned','2019-05-08','2019-05-08',250),(104,420,'Assigned','2019-05-08','2019-05-08',250),(105,420,'Assigned','2019-05-08','2019-05-08',250),(106,420,'Assigned','2019-05-08','2019-05-08',250),(107,420,'Assigned','2019-05-08','2019-05-08',250),(108,420,'Assigned','2019-05-08','2019-05-08',250),(109,420,'Assigned','2019-05-08','2019-05-08',250),(110,420,'Assigned','2019-05-08','2019-05-08',250),(111,420,'Assigned','2019-05-08','2019-05-08',250),(112,420,'Assigned','2019-05-08','2019-05-08',250),(113,420,'Assigned','2019-05-08','2019-05-08',250),(114,420,'Assigned','2019-05-08','2019-05-08',250),(115,420,'Assigned','2019-05-08','2019-05-08',250),(116,420,'Assigned','2019-05-08','2019-05-08',250),(117,420,'Assigned','2019-05-08','2019-05-08',250),(118,420,'Assigned','2019-05-08','2019-05-08',250),(119,420,'Assigned','2019-05-08','2019-05-08',250),(120,420,'Assigned','2019-05-08','2019-05-08',250),(121,420,'Assigned','2019-05-08','2019-05-08',250),(122,420,'Assigned','2019-05-08','2019-05-08',250),(123,420,'Assigned','2019-05-08','2019-05-08',250),(124,420,'Assigned','2019-05-08','2019-05-08',250),(125,420,'Assigned','2019-05-08','2019-05-08',250),(126,420,'Assigned','2019-05-08','2019-05-08',250),(127,420,'Assigned','2019-05-08','2019-05-08',250),(128,420,'Assigned','2019-05-08','2019-05-08',250),(129,420,'Assigned','2019-05-08','2019-05-08',250),(130,420,'Assigned','2019-05-08','2019-05-08',250),(131,420,'Assigned','2019-05-08','2019-05-10',211),(132,420,'Assigned','2019-05-08','2019-05-10',211),(133,420,'Assigned','2019-05-08','2019-05-10',211),(134,420,'Assigned','2019-05-08','2019-05-10',211),(135,420,'Assigned','2019-05-08','2019-05-10',211),(136,420,'Assigned','2019-05-08','2019-05-10',211),(137,420,'Assigned','2019-05-08','2019-05-10',211),(138,420,'Assigned','2019-05-08','2019-05-10',211),(139,420,'Assigned','2019-05-08','2019-05-10',211),(140,420,'Assigned','2019-05-08','2019-05-10',211),(141,420,'Assigned','2019-05-08','2019-05-10',250),(142,420,'Assigned','2019-05-08','2019-05-10',211),(143,420,'Assigned','2019-05-08','2019-05-10',211),(144,420,'Assigned','2019-05-08','2019-05-10',211),(145,420,'Assigned','2019-05-08','2019-05-10',211),(146,420,'Assigned','2019-05-08','2019-05-10',211),(147,420,'Assigned','2019-05-08','2019-05-10',211),(148,420,'Assigned','2019-05-08','2019-05-10',211),(149,420,'Assigned','2019-05-08','2019-05-10',211),(150,420,'Assigned','2019-05-08','2019-05-10',211),(151,420,'Received','2019-05-08',NULL,NULL),(152,420,'Received','2019-05-08',NULL,NULL),(153,420,'Received','2019-05-08',NULL,NULL),(154,420,'Received','2019-05-08',NULL,NULL),(155,420,'Received','2019-05-08',NULL,NULL),(156,420,'Received','2019-05-08',NULL,NULL),(157,420,'Received','2019-05-08',NULL,NULL),(158,420,'Received','2019-05-08',NULL,NULL),(159,420,'Received','2019-05-08',NULL,NULL),(160,420,'Received','2019-05-08',NULL,NULL),(161,420,'Received','2019-05-08',NULL,NULL),(162,420,'Received','2019-05-08',NULL,NULL),(163,420,'Received','2019-05-08',NULL,NULL),(164,420,'Received','2019-05-08',NULL,NULL),(165,420,'Received','2019-05-08',NULL,NULL),(166,420,'Received','2019-05-08',NULL,NULL),(167,420,'Received','2019-05-08',NULL,NULL),(168,420,'Received','2019-05-08',NULL,NULL),(169,420,'Received','2019-05-08',NULL,NULL),(170,420,'Received','2019-05-08',NULL,NULL),(171,420,'Received','2019-05-08',NULL,NULL),(172,420,'Received','2019-05-08',NULL,NULL),(173,420,'Received','2019-05-08',NULL,NULL),(174,420,'Received','2019-05-08',NULL,NULL),(175,420,'Received','2019-05-08',NULL,NULL),(176,420,'Received','2019-05-08',NULL,NULL),(177,420,'Received','2019-05-08',NULL,NULL),(178,420,'Received','2019-05-08',NULL,NULL),(179,420,'Received','2019-05-08',NULL,NULL),(180,420,'Received','2019-05-08',NULL,NULL),(181,420,'Received','2019-05-08',NULL,NULL),(182,420,'Received','2019-05-08',NULL,NULL),(183,420,'Received','2019-05-08',NULL,NULL),(184,420,'Received','2019-05-08',NULL,NULL),(185,420,'Received','2019-05-08',NULL,NULL),(186,420,'Received','2019-05-08',NULL,NULL),(187,420,'Received','2019-05-08',NULL,NULL),(188,420,'Received','2019-05-08',NULL,NULL),(189,420,'Received','2019-05-08',NULL,NULL),(190,420,'Received','2019-05-08',NULL,NULL),(191,420,'Received','2019-05-08',NULL,NULL),(192,420,'Received','2019-05-08',NULL,NULL),(193,420,'Received','2019-05-08',NULL,NULL),(194,420,'Received','2019-05-08',NULL,NULL),(195,420,'Received','2019-05-08',NULL,NULL),(196,420,'Received','2019-05-08',NULL,NULL),(197,420,'Received','2019-05-08',NULL,NULL),(198,420,'Received','2019-05-08',NULL,NULL),(199,420,'Received','2019-05-08',NULL,NULL),(200,420,'Received','2019-05-08',NULL,NULL),(201,201,'Sold','2019-06-03','2019-06-03',250),(202,201,'Assigned','2019-06-03','2019-06-03',250),(203,201,'Assigned','2019-06-03','2019-06-03',250),(204,201,'Assigned','2019-06-03','2019-06-03',250),(205,201,'Assigned','2019-06-03','2019-06-03',250),(206,201,'Assigned','2019-06-03','2019-06-03',250),(207,201,'Assigned','2019-06-03','2019-06-03',250),(208,201,'Assigned','2019-06-03','2019-06-03',250),(209,201,'Assigned','2019-06-03','2019-06-03',250),(210,201,'Assigned','2019-06-03','2019-06-03',250),(211,201,'Assigned','2019-06-03','2019-06-15',211),(212,201,'Assigned','2019-06-03','2019-06-15',211),(213,201,'Assigned','2019-06-03','2019-06-15',211),(214,201,'Assigned','2019-06-03','2019-06-15',211),(215,201,'Assigned','2019-06-03','2019-06-15',211),(216,201,'Assigned','2019-06-03','2019-06-15',211),(217,201,'Assigned','2019-06-03','2019-06-15',211),(218,201,'Assigned','2019-06-03','2019-06-15',211),(219,201,'Assigned','2019-06-03','2019-06-15',211),(220,201,'Assigned','2019-06-03','2019-06-15',211),(221,201,'Assigned','2019-06-03','2019-06-15',211),(222,201,'Assigned','2019-06-03','2019-06-15',211),(223,201,'Assigned','2019-06-03','2019-06-15',211),(224,201,'Assigned','2019-06-03','2019-06-15',211),(225,201,'Assigned','2019-06-03','2019-06-15',211),(226,201,'Received','2019-06-03',NULL,NULL),(227,201,'Received','2019-06-03',NULL,NULL),(228,201,'Received','2019-06-03',NULL,NULL),(229,201,'Received','2019-06-03',NULL,NULL),(230,201,'Received','2019-06-03',NULL,NULL),(231,201,'Received','2019-06-03',NULL,NULL),(232,201,'Received','2019-06-03',NULL,NULL),(233,201,'Received','2019-06-03',NULL,NULL),(234,201,'Received','2019-06-03',NULL,NULL),(235,201,'Received','2019-06-03',NULL,NULL),(236,201,'Received','2019-06-03',NULL,NULL),(237,201,'Received','2019-06-03',NULL,NULL),(238,201,'Received','2019-06-03',NULL,NULL),(239,201,'Received','2019-06-03',NULL,NULL),(240,201,'Received','2019-06-03',NULL,NULL),(241,201,'Received','2019-06-03',NULL,NULL),(242,201,'Received','2019-06-03',NULL,NULL),(243,201,'Received','2019-06-03',NULL,NULL),(244,201,'Received','2019-06-03',NULL,NULL),(245,201,'Received','2019-06-03',NULL,NULL),(246,201,'Received','2019-06-03',NULL,NULL),(247,201,'Received','2019-06-03',NULL,NULL),(248,201,'Received','2019-06-03',NULL,NULL),(249,201,'Received','2019-06-03',NULL,NULL),(250,201,'Received','2019-06-03',NULL,NULL),(251,201,'Received','2019-06-03',NULL,NULL),(252,201,'Received','2019-06-03',NULL,NULL),(253,201,'Received','2019-06-03',NULL,NULL),(254,201,'Received','2019-06-03',NULL,NULL),(255,201,'Received','2019-06-03',NULL,NULL),(256,201,'Received','2019-06-03',NULL,NULL),(257,201,'Received','2019-06-03',NULL,NULL),(258,201,'Received','2019-06-03',NULL,NULL),(259,201,'Received','2019-06-03',NULL,NULL),(260,201,'Received','2019-06-03',NULL,NULL),(261,201,'Received','2019-06-03',NULL,NULL),(262,201,'Received','2019-06-03',NULL,NULL),(263,201,'Received','2019-06-03',NULL,NULL),(264,201,'Received','2019-06-03',NULL,NULL),(265,201,'Received','2019-06-03',NULL,NULL),(266,201,'Received','2019-06-03',NULL,NULL),(267,201,'Received','2019-06-03',NULL,NULL),(268,201,'Received','2019-06-03',NULL,NULL),(269,201,'Received','2019-06-03',NULL,NULL),(270,201,'Received','2019-06-03',NULL,NULL),(271,201,'Received','2019-06-03',NULL,NULL),(272,201,'Received','2019-06-03',NULL,NULL),(273,201,'Received','2019-06-03',NULL,NULL),(274,201,'Received','2019-06-03',NULL,NULL),(275,201,'Received','2019-06-03',NULL,NULL),(276,201,'Received','2019-06-03',NULL,NULL),(277,201,'Received','2019-06-03',NULL,NULL),(278,201,'Received','2019-06-03',NULL,NULL),(279,201,'Received','2019-06-03',NULL,NULL),(280,201,'Received','2019-06-03',NULL,NULL),(281,201,'Received','2019-06-03',NULL,NULL),(282,201,'Received','2019-06-03',NULL,NULL),(283,201,'Received','2019-06-03',NULL,NULL),(284,201,'Received','2019-06-03',NULL,NULL),(285,201,'Received','2019-06-03',NULL,NULL),(286,201,'Received','2019-06-03',NULL,NULL),(287,201,'Received','2019-06-03',NULL,NULL),(288,201,'Received','2019-06-03',NULL,NULL),(289,201,'Received','2019-06-03',NULL,NULL),(290,201,'Received','2019-06-03',NULL,NULL),(291,201,'Received','2019-06-03',NULL,NULL),(292,201,'Received','2019-06-03',NULL,NULL),(293,201,'Received','2019-06-03',NULL,NULL),(294,201,'Received','2019-06-03',NULL,NULL),(295,201,'Received','2019-06-03',NULL,NULL),(296,201,'Received','2019-06-03',NULL,NULL),(297,201,'Received','2019-06-03',NULL,NULL),(298,201,'Received','2019-06-03',NULL,NULL),(299,201,'Received','2019-06-03',NULL,NULL),(300,201,'Received','2019-06-03',NULL,NULL),(301,101,'Assigned','2019-07-09','2019-07-11',211),(302,101,'Assigned','2019-07-09','2019-07-11',211),(303,101,'Assigned','2019-07-09','2019-07-11',211),(304,101,'Assigned','2019-07-09','2019-07-11',211),(305,101,'Assigned','2019-07-09','2019-07-11',211),(306,101,'Assigned','2019-07-09','2019-07-11',211),(307,101,'Assigned','2019-07-09','2019-07-11',211),(308,101,'Assigned','2019-07-09','2019-07-11',211),(309,101,'Assigned','2019-07-09','2019-07-11',211),(310,101,'Assigned','2019-07-09','2019-07-11',211),(311,101,'Assigned','2019-07-09','2019-07-11',211),(312,101,'Assigned','2019-07-09','2019-07-11',211),(313,101,'Assigned','2019-07-09','2019-07-11',211),(314,101,'Assigned','2019-07-09','2019-07-11',211),(315,101,'Assigned','2019-07-09','2019-07-11',211),(316,101,'Assigned','2019-07-09','2019-07-11',211),(317,101,'Assigned','2019-07-09','2019-07-11',211),(318,101,'Assigned','2019-07-09','2019-07-11',211),(319,101,'Assigned','2019-07-09','2019-07-11',211),(320,101,'Assigned','2019-07-09','2019-07-11',211),(321,101,'Assigned','2019-07-09','2019-07-11',211),(322,101,'Assigned','2019-07-09','2019-07-11',211),(323,101,'Assigned','2019-07-09','2019-07-11',211),(324,101,'Assigned','2019-07-09','2019-07-11',211),(325,101,'Assigned','2019-07-09','2019-07-11',211),(326,101,'Assigned','2019-07-09','2019-07-11',211),(327,101,'Assigned','2019-07-09','2019-07-11',211),(328,101,'Assigned','2019-07-09','2019-07-11',211),(329,101,'Assigned','2019-07-09','2019-07-11',211),(330,101,'Assigned','2019-07-09','2019-07-11',211),(331,101,'Assigned','2019-07-09','2019-07-11',211),(332,101,'Assigned','2019-07-09','2019-07-11',211),(333,101,'Assigned','2019-07-09','2019-07-11',211),(334,101,'Assigned','2019-07-09','2019-07-11',211),(335,101,'Assigned','2019-07-09','2019-07-11',211),(336,101,'Assigned','2019-07-09','2019-07-11',211),(337,101,'Assigned','2019-07-09','2019-07-11',211),(338,101,'Assigned','2019-07-09','2019-07-11',211),(339,101,'Assigned','2019-07-09','2019-07-11',211),(340,101,'Assigned','2019-07-09','2019-07-11',211),(341,101,'Assigned','2019-07-09','2019-07-11',211),(342,101,'Assigned','2019-07-09','2019-07-11',211),(343,101,'Assigned','2019-07-09','2019-07-11',211),(344,101,'Assigned','2019-07-09','2019-07-11',211),(345,101,'Assigned','2019-07-09','2019-07-11',211),(346,101,'Assigned','2019-07-09','2019-07-11',211),(347,101,'Assigned','2019-07-09','2019-07-11',211),(348,101,'Assigned','2019-07-09','2019-07-11',211),(349,101,'Assigned','2019-07-09','2019-07-11',211),(350,101,'Assigned','2019-07-09','2019-07-11',211),(351,444,'Received','2020-03-02',NULL,NULL),(352,444,'Received','2020-03-02',NULL,NULL),(353,444,'Received','2020-03-02',NULL,NULL),(354,444,'Received','2020-03-02',NULL,NULL),(355,444,'Received','2020-03-02',NULL,NULL),(356,444,'Received','2020-03-02',NULL,NULL),(357,444,'Received','2020-03-02',NULL,NULL),(358,444,'Received','2020-03-02',NULL,NULL),(359,444,'Received','2020-03-02',NULL,NULL),(360,444,'Received','2020-03-02',NULL,NULL),(361,444,'Received','2020-03-02',NULL,NULL),(362,444,'Received','2020-03-02',NULL,NULL),(363,444,'Received','2020-03-03',NULL,NULL),(364,444,'Received','2020-03-03',NULL,NULL),(365,444,'Received','2020-03-03',NULL,NULL),(366,444,'Received','2020-03-03',NULL,NULL),(367,444,'Received','2020-03-03',NULL,NULL),(368,444,'Received','2020-03-03',NULL,NULL),(369,444,'Received','2020-03-03',NULL,NULL),(370,444,'Received','2020-03-03',NULL,NULL),(371,444,'Received','2020-03-03',NULL,NULL),(372,444,'Received','2020-03-03',NULL,NULL),(373,444,'Received','2020-03-03',NULL,NULL),(374,444,'Received','2020-03-03',NULL,NULL),(375,444,'Received','2020-03-03',NULL,NULL),(376,444,'Received','2020-03-03',NULL,NULL),(377,444,'Received','2020-03-03',NULL,NULL),(378,444,'Received','2020-03-03',NULL,NULL),(379,444,'Received','2020-03-03',NULL,NULL),(380,444,'Received','2020-03-03',NULL,NULL),(381,444,'Received','2020-03-03',NULL,NULL),(382,444,'Received','2020-03-03',NULL,NULL),(383,444,'Received','2020-03-03',NULL,NULL),(384,444,'Received','2020-03-03',NULL,NULL),(385,444,'Received','2020-03-03',NULL,NULL),(386,444,'Received','2020-03-03',NULL,NULL),(450,444,'Received','2020-03-23',NULL,NULL),(451,444,'Received','2020-03-23',NULL,NULL),(452,444,'Received','2020-03-23',NULL,NULL),(453,444,'Received','2020-03-23',NULL,NULL),(454,444,'Received','2020-03-23',NULL,NULL),(455,444,'Received','2020-03-23',NULL,NULL),(456,444,'Received','2020-03-23',NULL,NULL),(457,444,'Received','2020-03-23',NULL,NULL),(458,444,'Received','2020-03-23',NULL,NULL),(459,444,'Received','2020-03-23',NULL,NULL),(460,444,'Received','2020-03-23',NULL,NULL),(461,444,'Received','2020-03-23',NULL,NULL);
/*!40000 ALTER TABLE `blanks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carddetails`
--

DROP TABLE IF EXISTS `carddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carddetails` (
  `cardNumber` varchar(16) NOT NULL,
  `expiryDate` date NOT NULL,
  `cvv` int NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `customerIDCard` int NOT NULL,
  PRIMARY KEY (`cardNumber`),
  KEY `cardCustomerID_idx` (`customerIDCard`),
  CONSTRAINT `cardCustomerID` FOREIGN KEY (`customerIDCard`) REFERENCES `customer` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carddetails`
--

LOCK TABLES `carddetails` WRITE;
/*!40000 ALTER TABLE `carddetails` DISABLE KEYS */;
INSERT INTO `carddetails` VALUES ('4901000223453456','2020-02-14',123,'Test','Test',8);
/*!40000 ALTER TABLE `carddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission` (
  `commissionID` int NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `commissionDate` date NOT NULL,
  `commissionEndDate` date DEFAULT NULL,
  `BlankTypeCommission` int NOT NULL,
  PRIMARY KEY (`commissionID`),
  UNIQUE KEY `commissionID_UNIQUE` (`commissionID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
INSERT INTO `commission` VALUES (3,9,'2020-01-01','2020-02-01',444),(4,5,'2020-01-01',NULL,201),(5,5,'2020-02-01',NULL,444),(6,12,'2020-01-01','2020-01-03',420);
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupondestination`
--

DROP TABLE IF EXISTS `coupondestination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupondestination` (
  `destinationID` int NOT NULL AUTO_INCREMENT,
  `destinationTo` varchar(45) NOT NULL,
  `destinationFrom` varchar(45) NOT NULL,
  `couponIDDestination` int NOT NULL,
  PRIMARY KEY (`destinationID`),
  KEY `couponDestinationCouponID_idx` (`couponIDDestination`),
  CONSTRAINT `couponDestinationCouponID` FOREIGN KEY (`couponIDDestination`) REFERENCES `coupons` (`couponID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupondestination`
--

LOCK TABLES `coupondestination` WRITE;
/*!40000 ALTER TABLE `coupondestination` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupondestination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `couponID` int NOT NULL AUTO_INCREMENT,
  `couponType` varchar(45) NOT NULL,
  `blankIDCoupons` int NOT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponID_UNIQUE` (`couponID`),
  KEY `blankIDCoupons_idx` (`blankIDCoupons`),
  CONSTRAINT `blankIDCoupons` FOREIGN KEY (`blankIDCoupons`) REFERENCES `blanks` (`blankID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `customerType` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `discountID` int DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  KEY `discountID_idx` (`discountID`),
  CONSTRAINT `discountID` FOREIGN KEY (`discountID`) REFERENCES `discount` (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Chris','Smart','Valued','ChrisSmart@gmail.com',1),(2,'David','Dodson','Valued','DavidDodson@gmail.com',2),(3,'Sarah','Broklehurst','Valued','SarahBroklehurst@gmail.com',3),(4,'Dominic','Beatty','Regular','DominicBeatty@gmail.com',NULL),(8,'test','test','Customer','test',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discountID` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'Fixed'),(2,'Flexible'),(3,'Fixed');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'test','test','test@gmail.com','test','Office Manager'),(211,'Dennis','Mennace','DennisMenace@gmail.com','Gnasher','Travel advisor'),(220,'Minnie','Minx','MinniMinx@gmail.com','NotiGirl','Office Manager'),(250,'Penelope','Pitstop','penelopepitstop@gmail.com','PinkMobile','Travel advisor'),(320,'Arthur','Daley','ArthurDaley@gmail.com','LiesaLot','System Administrator');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exchangerate`
--

DROP TABLE IF EXISTS `exchangerate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exchangerate` (
  `exchangeID` int NOT NULL AUTO_INCREMENT,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `exchangeAmount` float NOT NULL,
  PRIMARY KEY (`exchangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerate`
--

LOCK TABLES `exchangerate` WRITE;
/*!40000 ALTER TABLE `exchangerate` DISABLE KEYS */;
INSERT INTO `exchangerate` VALUES (3,'2020-01-01','2020-01-01',0.54),(4,'2020-03-17','2020-03-17',0.7),(8,'2020-02-01','2020-02-03',0.43);
/*!40000 ALTER TABLE `exchangerate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixeddiscount`
--

DROP TABLE IF EXISTS `fixeddiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixeddiscount` (
  `fixedID` int NOT NULL AUTO_INCREMENT,
  `fixedDiscountID` int DEFAULT NULL,
  `amount` decimal(4,2) NOT NULL,
  PRIMARY KEY (`fixedID`),
  UNIQUE KEY `fixedDiscountID_UNIQUE` (`fixedDiscountID`),
  KEY `fixedDiscountID_idx` (`fixedDiscountID`),
  CONSTRAINT `fixedDiscountID` FOREIGN KEY (`fixedDiscountID`) REFERENCES `discount` (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixeddiscount`
--

LOCK TABLES `fixeddiscount` WRITE;
/*!40000 ALTER TABLE `fixeddiscount` DISABLE KEYS */;
INSERT INTO `fixeddiscount` VALUES (1,1,1.00),(2,3,2.00);
/*!40000 ALTER TABLE `fixeddiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexiblediscount`
--

DROP TABLE IF EXISTS `flexiblediscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexiblediscount` (
  `flexibleID` int NOT NULL AUTO_INCREMENT,
  `flexibleDiscountID` int NOT NULL,
  `discountAmount` float NOT NULL,
  PRIMARY KEY (`flexibleID`),
  KEY `flexibleDiscountID_idx` (`flexibleDiscountID`),
  CONSTRAINT `flexibleDiscountID` FOREIGN KEY (`flexibleDiscountID`) REFERENCES `discount` (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblediscount`
--

LOCK TABLES `flexiblediscount` WRITE;
/*!40000 ALTER TABLE `flexiblediscount` DISABLE KEYS */;
INSERT INTO `flexiblediscount` VALUES (1,2,0),(2,2,1),(3,2,2);
/*!40000 ALTER TABLE `flexiblediscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexiblediscountranges`
--

DROP TABLE IF EXISTS `flexiblediscountranges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexiblediscountranges` (
  `discountRangeID` int NOT NULL AUTO_INCREMENT,
  `rangeID` int NOT NULL,
  `flexibleDiscountID` int NOT NULL,
  PRIMARY KEY (`discountRangeID`),
  KEY `RangeID_idx` (`rangeID`),
  KEY `flexibleID_idx` (`flexibleDiscountID`),
  CONSTRAINT `flexibleID` FOREIGN KEY (`flexibleDiscountID`) REFERENCES `flexiblediscount` (`flexibleID`),
  CONSTRAINT `RangeID` FOREIGN KEY (`rangeID`) REFERENCES `flexiblerange` (`rangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblediscountranges`
--

LOCK TABLES `flexiblediscountranges` WRITE;
/*!40000 ALTER TABLE `flexiblediscountranges` DISABLE KEYS */;
INSERT INTO `flexiblediscountranges` VALUES (1,1,1),(2,2,2),(3,3,3);
/*!40000 ALTER TABLE `flexiblediscountranges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flexiblerange`
--

DROP TABLE IF EXISTS `flexiblerange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flexiblerange` (
  `rangeID` int NOT NULL AUTO_INCREMENT,
  `amountFrom` float NOT NULL,
  `amountTo` float DEFAULT NULL,
  PRIMARY KEY (`rangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblerange`
--

LOCK TABLES `flexiblerange` WRITE;
/*!40000 ALTER TABLE `flexiblerange` DISABLE KEYS */;
INSERT INTO `flexiblerange` VALUES (1,0,1000),(2,1000,2000),(3,2000,NULL);
/*!40000 ALTER TABLE `flexiblerange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `salesID` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `taxes` float NOT NULL,
  `blankIDSale` int NOT NULL,
  `customerIDSale` int NOT NULL,
  `employeeIDSale` int NOT NULL,
  `commissionIDSale` int NOT NULL,
  `exchangeRateID` int DEFAULT NULL,
  `salesDate` date NOT NULL,
  `cardDetailsID` varchar(16) DEFAULT NULL,
  `latePaymentStatus` varchar(45) DEFAULT NULL,
  `latePaymentDate` date DEFAULT NULL,
  `MCOstatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`salesID`),
  UNIQUE KEY `salesID_UNIQUE` (`salesID`),
  KEY `SalesCommissionID_idx` (`commissionIDSale`),
  KEY `SalesCustomerID_idx` (`customerIDSale`),
  KEY `SalesBlankID_idx` (`blankIDSale`),
  KEY `SalesExchangeRateID_idx` (`exchangeRateID`),
  KEY `SalesEmployeeID_idx` (`employeeIDSale`),
  KEY `SalesCardDetails_idx` (`cardDetailsID`),
  CONSTRAINT `SalesBlankID` FOREIGN KEY (`blankIDSale`) REFERENCES `blanks` (`blankID`) ON DELETE CASCADE,
  CONSTRAINT `SalesCardDetails` FOREIGN KEY (`cardDetailsID`) REFERENCES `carddetails` (`cardNumber`) ON DELETE CASCADE,
  CONSTRAINT `SalesCommissionID` FOREIGN KEY (`commissionIDSale`) REFERENCES `commission` (`commissionID`) ON DELETE CASCADE,
  CONSTRAINT `SalesCustomerID` FOREIGN KEY (`customerIDSale`) REFERENCES `customer` (`customerID`) ON DELETE CASCADE,
  CONSTRAINT `SalesEmployeeID` FOREIGN KEY (`employeeIDSale`) REFERENCES `employees` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `SalesExchangeRateID` FOREIGN KEY (`exchangeRateID`) REFERENCES `exchangerate` (`exchangeID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,'Cash',215.6,58,1,3,250,3,3,'2020-01-01',NULL,NULL,NULL,NULL),(2,'Card',230,98,2,8,250,3,3,'2020-01-01','4901000223453456',NULL,NULL,NULL),(4,'Cash',50,15,21,4,211,3,3,'2020-01-01',NULL,NULL,NULL,NULL),(5,'Cash',50,15,22,2,211,3,3,'2020-01-01',NULL,NULL,NULL,NULL),(6,'Cash',50,50,101,2,250,6,3,'2020-01-01',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `startdate`
--

DROP TABLE IF EXISTS `startdate`;
/*!50001 DROP VIEW IF EXISTS `startdate`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `startdate` AS SELECT 
 1 AS `DATE_ADD(NOW(),INTERVAL -30 DAY)`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `todaydate`
--

DROP TABLE IF EXISTS `todaydate`;
/*!50001 DROP VIEW IF EXISTS `todaydate`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `todaydate` AS SELECT 
 1 AS `NOW()`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `travelagent`
--

DROP TABLE IF EXISTS `travelagent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travelagent` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelagent`
--

LOCK TABLES `travelagent` WRITE;
/*!40000 ALTER TABLE `travelagent` DISABLE KEYS */;
INSERT INTO `travelagent` VALUES (1,'AirVIA','test2');
/*!40000 ALTER TABLE `travelagent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ats`
--

USE `ats`;

--
-- Final view structure for view `startdate`
--

/*!50001 DROP VIEW IF EXISTS `startdate`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `startdate` AS select (now() + interval -(30) day) AS `DATE_ADD(NOW(),INTERVAL -30 DAY)` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `todaydate`
--

/*!50001 DROP VIEW IF EXISTS `todaydate`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `todaydate` AS select now() AS `NOW()` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-26 19:22:57
