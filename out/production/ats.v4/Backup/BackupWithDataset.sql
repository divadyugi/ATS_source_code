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
INSERT INTO `blanks` VALUES (1,101,'Assigned','2019-07-09','2019-07-11',211),(1,201,'Sold','2019-06-03','2019-06-03',250),(1,420,'Assigned','2019-05-08','2019-05-08',250),(1,444,'Sold','2019-04-01','2019-04-01',250),(2,101,'Assigned','2019-07-09','2019-07-11',211),(2,201,'Sold','2019-06-03','2019-06-03',250),(2,420,'Assigned','2019-05-08','2019-05-08',250),(2,444,'Refunded','2019-04-01','2019-04-01',250),(3,101,'Assigned','2019-07-09','2019-07-11',211),(3,201,'Assigned','2019-06-03','2019-06-03',250),(3,420,'Assigned','2019-05-08','2019-05-08',250),(3,444,'Sold','2019-04-01','2019-04-01',250),(4,101,'Assigned','2019-07-09','2019-07-11',211),(4,201,'Assigned','2019-06-03','2019-06-03',250),(4,420,'Assigned','2019-05-08','2019-05-08',250),(4,444,'Sold','2019-04-01','2019-04-01',250),(5,101,'Assigned','2019-07-09','2019-07-11',211),(5,201,'Assigned','2019-06-03','2019-06-03',250),(5,420,'Assigned','2019-05-08','2019-05-08',250),(5,444,'Assigned','2019-04-01','2019-04-01',250),(6,101,'Assigned','2019-07-09','2019-07-11',211),(6,201,'Assigned','2019-06-03','2019-06-03',250),(6,420,'Assigned','2019-05-08','2019-05-08',250),(6,444,'Assigned','2019-04-01','2019-04-01',250),(7,101,'Assigned','2019-07-09','2019-07-11',211),(7,201,'Assigned','2019-06-03','2019-06-03',250),(7,420,'Assigned','2019-05-08','2019-05-08',250),(7,444,'Assigned','2019-04-01','2019-04-01',250),(8,101,'Assigned','2019-07-09','2019-07-11',211),(8,201,'Assigned','2019-06-03','2019-06-03',250),(8,420,'Assigned','2019-05-08','2019-05-08',250),(8,444,'Assigned','2019-04-01','2019-04-01',250),(9,101,'Assigned','2019-07-09','2019-07-11',211),(9,201,'Assigned','2019-06-03','2019-06-03',250),(9,420,'Assigned','2019-05-08','2019-05-08',250),(9,444,'Assigned','2019-04-01','2019-04-01',250),(10,101,'Assigned','2019-07-09','2019-07-11',211),(10,201,'Assigned','2019-06-03','2019-06-03',250),(10,420,'Assigned','2019-05-08','2019-05-08',250),(10,444,'Assigned','2019-04-01','2019-04-01',250),(11,101,'Assigned','2019-07-09','2019-07-11',211),(11,201,'Refunded','2019-06-03','2019-06-15',211),(11,420,'Assigned','2019-05-08','2019-05-08',250),(11,444,'Assigned','2019-04-01','2019-04-01',250),(12,101,'Assigned','2019-07-09','2019-07-11',211),(12,201,'Assigned','2019-06-03','2019-06-15',211),(12,420,'Assigned','2019-05-08','2019-05-08',250),(12,444,'Assigned','2019-04-01','2019-04-01',250),(13,101,'Assigned','2019-07-09','2019-07-11',211),(13,201,'Assigned','2019-06-03','2019-06-15',211),(13,420,'Assigned','2019-05-08','2019-05-08',250),(13,444,'Assigned','2019-04-01','2019-04-01',250),(14,101,'Assigned','2019-07-09','2019-07-11',211),(14,201,'Assigned','2019-06-03','2019-06-15',211),(14,420,'Assigned','2019-05-08','2019-05-08',250),(14,444,'Assigned','2019-04-01','2019-04-01',250),(15,101,'Assigned','2019-07-09','2019-07-11',211),(15,201,'Assigned','2019-06-03','2019-06-15',211),(15,420,'Assigned','2019-05-08','2019-05-08',250),(15,444,'Assigned','2019-04-01','2019-04-01',250),(16,101,'Assigned','2019-07-09','2019-07-11',211),(16,201,'Assigned','2019-06-03','2019-06-15',211),(16,420,'Assigned','2019-05-08','2019-05-08',250),(16,444,'Assigned','2019-04-01','2019-04-01',250),(17,101,'Assigned','2019-07-09','2019-07-11',211),(17,201,'Assigned','2019-06-03','2019-06-15',211),(17,420,'Assigned','2019-05-08','2019-05-08',250),(17,444,'Assigned','2019-04-01','2019-04-01',250),(18,101,'Assigned','2019-07-09','2019-07-11',211),(18,201,'Assigned','2019-06-03','2019-06-15',211),(18,420,'Assigned','2019-05-08','2019-05-08',250),(18,444,'Assigned','2019-04-01','2019-04-01',250),(19,101,'Assigned','2019-07-09','2019-07-11',211),(19,201,'Assigned','2019-06-03','2019-06-15',211),(19,420,'Assigned','2019-05-08','2019-05-08',250),(19,444,'Assigned','2019-04-01','2019-04-01',250),(20,101,'Assigned','2019-07-09','2019-07-11',211),(20,201,'Assigned','2019-06-03','2019-06-15',211),(20,420,'Assigned','2019-05-08','2019-05-08',250),(20,444,'Assigned','2019-04-01','2019-04-01',250),(21,101,'Assigned','2019-07-09','2019-07-11',211),(21,201,'Assigned','2019-06-03','2019-06-15',211),(21,420,'Assigned','2019-05-08','2019-05-08',250),(21,444,'Sold','2019-04-01','2019-04-05',211),(22,101,'Assigned','2019-07-09','2019-07-11',211),(22,201,'Assigned','2019-06-03','2019-06-15',211),(22,420,'Assigned','2019-05-08','2019-05-08',250),(22,444,'Sold','2019-04-01','2019-04-05',211),(23,101,'Assigned','2019-07-09','2019-07-11',211),(23,201,'Assigned','2019-06-03','2019-06-15',211),(23,420,'Assigned','2019-05-08','2019-05-08',250),(23,444,'Assigned','2019-04-01','2019-04-05',211),(24,101,'Assigned','2019-07-09','2019-07-11',211),(24,201,'Assigned','2019-06-03','2019-06-15',211),(24,420,'Assigned','2019-05-08','2019-05-08',250),(24,444,'Assigned','2019-04-01','2019-04-05',211),(25,101,'Assigned','2019-07-09','2019-07-11',211),(25,201,'Assigned','2019-06-03','2019-06-15',211),(25,420,'Assigned','2019-05-08','2019-05-08',250),(25,444,'Assigned','2019-04-01','2019-04-05',211),(26,101,'Assigned','2019-07-09','2019-07-11',211),(26,201,'Received','2019-06-03','2019-06-15',NULL),(26,420,'Assigned','2019-05-08','2019-05-08',250),(26,444,'Assigned','2019-04-01','2019-04-05',211),(27,101,'Assigned','2019-07-09','2019-07-11',211),(27,201,'Received','2019-06-03','2019-06-15',NULL),(27,420,'Assigned','2019-05-08','2019-05-08',250),(27,444,'Assigned','2019-04-01','2019-04-05',211),(28,101,'Assigned','2019-07-09','2019-07-11',211),(28,201,'Received','2019-06-03','2019-06-15',NULL),(28,420,'Assigned','2019-05-08','2019-05-08',250),(28,444,'Assigned','2019-04-01','2019-04-05',211),(29,101,'Assigned','2019-07-09','2019-07-11',211),(29,201,'Received','2019-06-03','2019-06-15',NULL),(29,420,'Assigned','2019-05-08','2019-05-08',250),(29,444,'Assigned','2019-04-01','2019-04-05',211),(30,101,'Assigned','2019-07-09','2019-07-11',211),(30,201,'Received','2019-06-03','2019-06-15',NULL),(30,420,'Assigned','2019-05-08','2019-05-08',250),(30,444,'Assigned','2019-04-01','2019-04-05',211),(31,101,'Assigned','2019-07-09','2019-07-11',211),(31,201,'Received','2019-06-03','2019-06-15',NULL),(31,420,'Assigned','2019-05-08','2019-05-10',211),(31,444,'Assigned','2019-04-01','2019-04-05',211),(32,101,'Assigned','2019-07-09','2019-07-11',211),(32,201,'Received','2019-06-03','2019-06-15',NULL),(32,420,'Assigned','2019-05-08','2019-05-10',211),(32,444,'Assigned','2019-04-01','2019-04-05',211),(33,101,'Assigned','2019-07-09','2019-07-11',211),(33,201,'Received','2019-06-03','2019-06-15',NULL),(33,420,'Assigned','2019-05-08','2019-05-10',211),(33,444,'Assigned','2019-04-01','2019-04-05',211),(34,101,'Assigned','2019-07-09','2019-07-11',211),(34,201,'Received','2019-06-03','2019-06-15',NULL),(34,420,'Assigned','2019-05-08','2019-05-10',211),(34,444,'Assigned','2019-04-01','2019-04-05',211),(35,101,'Assigned','2019-07-09','2019-07-11',211),(35,201,'Received','2019-06-03','2019-06-15',NULL),(35,420,'Assigned','2019-05-08','2019-05-10',211),(35,444,'Assigned','2019-04-01','2019-04-05',211),(36,101,'Assigned','2019-07-09','2019-07-11',211),(36,201,'Received','2019-06-03',NULL,NULL),(36,420,'Assigned','2019-05-08','2019-05-10',211),(36,444,'Assigned','2019-04-01','2019-04-05',211),(37,101,'Assigned','2019-07-09','2019-07-11',211),(37,201,'Received','2019-06-03',NULL,NULL),(37,420,'Assigned','2019-05-08','2019-05-10',211),(37,444,'Assigned','2019-04-01','2019-04-05',211),(38,101,'Assigned','2019-07-09','2019-07-11',211),(38,201,'Received','2019-06-03',NULL,NULL),(38,420,'Assigned','2019-05-08','2019-05-10',211),(38,444,'Assigned','2019-04-01','2019-04-05',211),(39,101,'Assigned','2019-07-09','2019-07-11',211),(39,201,'Received','2019-06-03',NULL,NULL),(39,420,'Assigned','2019-05-08','2019-05-10',211),(39,444,'Assigned','2019-04-01','2019-04-05',211),(40,101,'Assigned','2019-07-09','2019-07-11',211),(40,201,'Received','2019-06-03',NULL,NULL),(40,420,'Assigned','2019-05-08','2019-05-10',211),(40,444,'Assigned','2019-04-01','2019-04-05',211),(41,101,'Assigned','2019-07-09','2019-07-11',211),(41,201,'Received','2019-06-03',NULL,NULL),(41,420,'Assigned','2019-05-08','2019-05-10',211),(41,444,'Received','2019-04-01',NULL,NULL),(42,101,'Assigned','2019-07-09','2019-07-11',211),(42,201,'Received','2019-06-03',NULL,NULL),(42,420,'Assigned','2019-05-08','2019-05-10',211),(42,444,'Received','2019-04-01',NULL,NULL),(43,101,'Assigned','2019-07-09','2019-07-11',211),(43,201,'Received','2019-06-03',NULL,NULL),(43,420,'Assigned','2019-05-08','2019-05-10',211),(43,444,'Received','2019-04-01',NULL,NULL),(44,101,'Assigned','2019-07-09','2019-07-11',211),(44,201,'Received','2019-06-03',NULL,NULL),(44,420,'Assigned','2019-05-08','2019-05-10',211),(44,444,'Received','2019-04-01',NULL,NULL),(45,101,'Assigned','2019-07-09','2019-07-11',211),(45,201,'Received','2019-06-03',NULL,NULL),(45,420,'Assigned','2019-05-08','2019-05-10',211),(45,444,'Received','2019-04-01',NULL,NULL),(46,101,'Assigned','2019-07-09','2019-07-11',211),(46,201,'Received','2019-06-03',NULL,NULL),(46,420,'Assigned','2019-05-08','2019-05-10',211),(46,444,'Received','2019-04-01',NULL,NULL),(47,101,'Assigned','2019-07-09','2019-07-11',211),(47,201,'Received','2019-06-03',NULL,NULL),(47,420,'Assigned','2019-05-08','2019-05-10',211),(47,444,'Received','2019-04-01',NULL,NULL),(48,101,'Assigned','2019-07-09','2019-07-11',211),(48,201,'Received','2019-06-03',NULL,NULL),(48,420,'Assigned','2019-05-08','2019-05-10',211),(48,444,'Received','2019-04-01',NULL,NULL),(49,101,'Assigned','2019-07-09','2019-07-11',211),(49,201,'Received','2019-06-03',NULL,NULL),(49,420,'Assigned','2019-05-08','2019-05-10',211),(49,444,'Received','2019-04-01',NULL,NULL),(50,101,'Assigned','2019-07-09','2019-07-11',211),(50,201,'Received','2019-06-03',NULL,NULL),(50,420,'Assigned','2019-05-08','2019-05-10',211),(50,444,'Received','2019-04-01',NULL,NULL),(51,201,'Received','2019-06-03',NULL,NULL),(51,420,'Received','2019-05-08',NULL,NULL),(51,444,'Received','2019-04-01',NULL,NULL),(52,201,'Received','2019-06-03',NULL,NULL),(52,420,'Received','2019-05-08',NULL,NULL),(52,444,'Received','2019-04-01',NULL,NULL),(53,201,'Received','2019-06-03',NULL,NULL),(53,420,'Received','2019-05-08',NULL,NULL),(53,444,'Received','2019-04-01',NULL,NULL),(54,201,'Received','2019-06-03',NULL,NULL),(54,420,'Received','2019-05-08',NULL,NULL),(54,444,'Received','2019-04-01',NULL,NULL),(55,201,'Received','2019-06-03',NULL,NULL),(55,420,'Received','2019-05-08',NULL,NULL),(55,444,'Received','2019-04-01',NULL,NULL),(56,201,'Received','2019-06-03',NULL,NULL),(56,420,'Received','2019-05-08',NULL,NULL),(56,444,'Received','2019-04-01',NULL,NULL),(57,201,'Received','2019-06-03',NULL,NULL),(57,420,'Received','2019-05-08',NULL,NULL),(57,444,'Received','2019-04-01',NULL,NULL),(58,201,'Received','2019-06-03',NULL,NULL),(58,420,'Received','2019-05-08',NULL,NULL),(58,444,'Received','2019-04-01',NULL,NULL),(59,201,'Received','2019-06-03',NULL,NULL),(59,420,'Received','2019-05-08',NULL,NULL),(59,444,'Received','2019-04-01',NULL,NULL),(60,201,'Received','2019-06-03',NULL,NULL),(60,420,'Received','2019-05-08',NULL,NULL),(60,444,'Received','2019-04-01',NULL,NULL),(61,201,'Received','2019-06-03',NULL,NULL),(61,420,'Received','2019-05-08',NULL,NULL),(61,444,'Received','2019-04-01',NULL,NULL),(62,201,'Received','2019-06-03',NULL,NULL),(62,420,'Received','2019-05-08',NULL,NULL),(62,444,'Received','2019-04-01',NULL,NULL),(63,201,'Received','2019-06-03',NULL,NULL),(63,420,'Received','2019-05-08',NULL,NULL),(63,444,'Received','2019-04-01',NULL,NULL),(64,201,'Received','2019-06-03',NULL,NULL),(64,420,'Received','2019-05-08',NULL,NULL),(64,444,'Received','2019-04-01',NULL,NULL),(65,201,'Received','2019-06-03',NULL,NULL),(65,420,'Received','2019-05-08',NULL,NULL),(65,444,'Received','2019-04-01',NULL,NULL),(66,201,'Received','2019-06-03',NULL,NULL),(66,420,'Received','2019-05-08',NULL,NULL),(66,444,'Received','2019-04-01',NULL,NULL),(67,201,'Received','2019-06-03',NULL,NULL),(67,420,'Received','2019-05-08',NULL,NULL),(67,444,'Received','2019-04-01',NULL,NULL),(68,201,'Received','2019-06-03',NULL,NULL),(68,420,'Received','2019-05-08',NULL,NULL),(68,444,'Received','2019-04-01',NULL,NULL),(69,201,'Received','2019-06-03',NULL,NULL),(69,420,'Received','2019-05-08',NULL,NULL),(69,444,'Received','2019-04-01',NULL,NULL),(70,201,'Received','2019-06-03',NULL,NULL),(70,420,'Received','2019-05-08',NULL,NULL),(70,444,'Received','2019-04-01',NULL,NULL),(71,201,'Received','2019-06-03',NULL,NULL),(71,420,'Received','2019-05-08',NULL,NULL),(71,444,'Received','2019-04-01',NULL,NULL),(72,201,'Received','2019-06-03',NULL,NULL),(72,420,'Received','2019-05-08',NULL,NULL),(72,444,'Received','2019-04-01',NULL,NULL),(73,201,'Received','2019-06-03',NULL,NULL),(73,420,'Received','2019-05-08',NULL,NULL),(73,444,'Received','2019-04-01',NULL,NULL),(74,201,'Received','2019-06-03',NULL,NULL),(74,420,'Received','2019-05-08',NULL,NULL),(74,444,'Received','2019-04-01',NULL,NULL),(75,201,'Received','2019-06-03',NULL,NULL),(75,420,'Received','2019-05-08',NULL,NULL),(75,444,'Received','2019-04-01',NULL,NULL),(76,201,'Received','2019-06-03',NULL,NULL),(76,420,'Received','2019-05-08',NULL,NULL),(76,444,'Received','2019-04-01',NULL,NULL),(77,201,'Received','2019-06-03',NULL,NULL),(77,420,'Received','2019-05-08',NULL,NULL),(77,444,'Received','2019-04-01',NULL,NULL),(78,201,'Received','2019-06-03',NULL,NULL),(78,420,'Received','2019-05-08',NULL,NULL),(78,444,'Received','2019-04-01',NULL,NULL),(79,201,'Received','2019-06-03',NULL,NULL),(79,420,'Received','2019-05-08',NULL,NULL),(79,444,'Received','2019-04-01',NULL,NULL),(80,201,'Received','2019-06-03',NULL,NULL),(80,420,'Received','2019-05-08',NULL,NULL),(80,444,'Received','2019-04-01',NULL,NULL),(81,201,'Received','2019-06-03',NULL,NULL),(81,420,'Received','2019-05-08',NULL,NULL),(81,444,'Received','2019-04-01',NULL,NULL),(82,201,'Received','2019-06-03',NULL,NULL),(82,420,'Received','2019-05-08',NULL,NULL),(82,444,'Received','2019-04-01',NULL,NULL),(83,201,'Received','2019-06-03',NULL,NULL),(83,420,'Received','2019-05-08',NULL,NULL),(83,444,'Received','2019-04-01',NULL,NULL),(84,201,'Received','2019-06-03',NULL,NULL),(84,420,'Received','2019-05-08',NULL,NULL),(84,444,'Received','2019-04-01',NULL,NULL),(85,201,'Received','2019-06-03',NULL,NULL),(85,420,'Received','2019-05-08',NULL,NULL),(85,444,'Received','2019-04-01',NULL,NULL),(86,201,'Received','2019-06-03',NULL,NULL),(86,420,'Received','2019-05-08',NULL,NULL),(86,444,'Received','2019-04-01',NULL,NULL),(87,201,'Received','2019-06-03',NULL,NULL),(87,420,'Received','2019-05-08',NULL,NULL),(87,444,'Received','2019-04-01',NULL,NULL),(88,201,'Received','2019-06-03',NULL,NULL),(88,420,'Received','2019-05-08',NULL,NULL),(88,444,'Received','2019-04-01',NULL,NULL),(89,201,'Received','2019-06-03',NULL,NULL),(89,420,'Received','2019-05-08',NULL,NULL),(89,444,'Received','2019-04-01',NULL,NULL),(90,201,'Received','2019-06-03',NULL,NULL),(90,420,'Received','2019-05-08',NULL,NULL),(90,444,'Received','2019-04-01',NULL,NULL),(91,201,'Received','2019-06-03',NULL,NULL),(91,420,'Received','2019-05-08',NULL,NULL),(91,444,'Received','2019-04-01',NULL,NULL),(92,201,'Received','2019-06-03',NULL,NULL),(92,420,'Received','2019-05-08',NULL,NULL),(92,444,'Received','2019-04-01',NULL,NULL),(93,201,'Received','2019-06-03',NULL,NULL),(93,420,'Received','2019-05-08',NULL,NULL),(93,444,'Received','2019-04-01',NULL,NULL),(94,201,'Received','2019-06-03',NULL,NULL),(94,420,'Received','2019-05-08',NULL,NULL),(94,444,'Received','2019-04-01',NULL,NULL),(95,201,'Received','2019-06-03',NULL,NULL),(95,420,'Received','2019-05-08',NULL,NULL),(95,444,'Received','2019-04-01',NULL,NULL),(96,201,'Received','2019-06-03',NULL,NULL),(96,420,'Received','2019-05-08',NULL,NULL),(96,444,'Received','2019-04-01',NULL,NULL),(97,201,'Received','2019-06-03',NULL,NULL),(97,420,'Received','2019-05-08',NULL,NULL),(97,444,'Received','2019-04-01',NULL,NULL),(98,201,'Received','2019-06-03',NULL,NULL),(98,420,'Received','2019-05-08',NULL,NULL),(98,444,'Received','2019-04-01',NULL,NULL),(99,201,'Received','2019-06-03',NULL,NULL),(99,420,'Received','2019-05-08',NULL,NULL),(99,444,'Received','2019-04-01',NULL,NULL),(100,201,'Received','2019-06-03',NULL,NULL),(100,420,'Received','2019-05-08',NULL,NULL),(100,444,'Received','2019-04-01',NULL,NULL);
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
INSERT INTO `carddetails` VALUES ('4901000223453456','2020-04-24',123,'Sarah','B',25),('6454986387338876','2020-04-16',123,'casual','casual',28),('7449155545893456','2020-04-16',123,'casual3','casual3',29);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
INSERT INTO `commission` VALUES (7,9,'2019-12-31',NULL,444),(8,5,'2019-12-31',NULL,201);
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
  CONSTRAINT `couponDestinationCouponID` FOREIGN KEY (`couponIDDestination`) REFERENCES `coupons` (`couponID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupondestination`
--

LOCK TABLES `coupondestination` WRITE;
/*!40000 ALTER TABLE `coupondestination` DISABLE KEYS */;
INSERT INTO `coupondestination` VALUES (3,'2','1',5);
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
  `blankType` int NOT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponID_UNIQUE` (`couponID`),
  KEY `blankIDCoupons_idx` (`blankIDCoupons`,`blankType`),
  CONSTRAINT `blankIDCoupons` FOREIGN KEY (`blankIDCoupons`, `blankType`) REFERENCES `blanks` (`blankID`, `Type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (4,'Auditor',1,444),(5,'Flight',1,444);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (23,'Chris','Smart','Valued','Chris.Smart@gmail.com',7),(24,'David','Dodson','Valued','DaveD@gmail.com',8),(25,'Sarah','Broklehurst','Valued','SarahB@gmail.com',9),(26,'Dominic','Beatty','Regular','Dom@gmail.com',NULL),(27,'Casual1','Casual1','Customer','Casual1@gmail.com',NULL),(28,'casual2','casual2','Customer','Casual2@gmail.com',NULL),(29,'casual3','casual3','Customer','casual3',NULL),(30,'casual4','casual4','Customer','casual4',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (7,'Fixed'),(8,'Flexible'),(9,'Fixed');
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
INSERT INTO `employees` VALUES (211,'Dennis','Menace','DennisMenace@gmail.com','Gnasher','Travel advisor'),(220,'Minnie','Minx','MinnieMinx@gmail.com','NotiGirl','Office Manager'),(250,'Penelope','Pitstop','penelope@gmail.com','PinkMobile','Travel advisor'),(320,'Arthur','Daley','ArthurDaley@gmail.com','LiesaLot','System Administrator');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exchangerate`
--

LOCK TABLES `exchangerate` WRITE;
/*!40000 ALTER TABLE `exchangerate` DISABLE KEYS */;
INSERT INTO `exchangerate` VALUES (9,'2019-12-31','2020-02-01',0.54),(10,'2020-02-01',NULL,0.43);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixeddiscount`
--

LOCK TABLES `fixeddiscount` WRITE;
/*!40000 ALTER TABLE `fixeddiscount` DISABLE KEYS */;
INSERT INTO `fixeddiscount` VALUES (4,7,1.00),(5,9,2.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblediscount`
--

LOCK TABLES `flexiblediscount` WRITE;
/*!40000 ALTER TABLE `flexiblediscount` DISABLE KEYS */;
INSERT INTO `flexiblediscount` VALUES (4,8,0),(5,8,1),(6,8,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblediscountranges`
--

LOCK TABLES `flexiblediscountranges` WRITE;
/*!40000 ALTER TABLE `flexiblediscountranges` DISABLE KEYS */;
INSERT INTO `flexiblediscountranges` VALUES (4,4,4),(5,5,5),(6,6,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flexiblerange`
--

LOCK TABLES `flexiblerange` WRITE;
/*!40000 ALTER TABLE `flexiblerange` DISABLE KEYS */;
INSERT INTO `flexiblerange` VALUES (4,0,1000),(5,1000,2000),(6,2000,NULL);
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
  `blankType` int NOT NULL,
  PRIMARY KEY (`salesID`),
  UNIQUE KEY `salesID_UNIQUE` (`salesID`),
  KEY `SalesCommissionID_idx` (`commissionIDSale`),
  KEY `SalesCustomerID_idx` (`customerIDSale`),
  KEY `SalesExchangeRateID_idx` (`exchangeRateID`),
  KEY `SalesEmployeeID_idx` (`employeeIDSale`),
  KEY `SalesCardDetails_idx` (`cardDetailsID`),
  KEY `SalesBlankID_idx` (`blankIDSale`,`blankType`),
  CONSTRAINT `SalesBlankID` FOREIGN KEY (`blankIDSale`, `blankType`) REFERENCES `blanks` (`blankID`, `Type`),
  CONSTRAINT `SalesCardDetails` FOREIGN KEY (`cardDetailsID`) REFERENCES `carddetails` (`cardNumber`),
  CONSTRAINT `SalesCommissionID` FOREIGN KEY (`commissionIDSale`) REFERENCES `commission` (`commissionID`),
  CONSTRAINT `SalesCustomerID` FOREIGN KEY (`customerIDSale`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `SalesEmployeeID` FOREIGN KEY (`employeeIDSale`) REFERENCES `employees` (`ID`),
  CONSTRAINT `SalesExchangeRateID` FOREIGN KEY (`exchangeRateID`) REFERENCES `exchangerate` (`exchangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (11,'Cash',22.54,35,1,25,250,7,9,'2020-01-01',NULL,'null',NULL,NULL,444),(12,'Card',225.4,98,2,25,250,7,9,'2020-01-01','4901000223453456','null',NULL,NULL,444),(13,'Cash',86,15.6,1,27,250,8,NULL,'2020-01-01',NULL,'null',NULL,NULL,201),(15,'Late payment',220,138,3,24,250,7,10,'2020-02-02',NULL,'Failed To Pay',NULL,NULL,444),(16,'Late payment',227.7,58,4,23,250,7,10,'2020-02-02',NULL,'Failed To Pay',NULL,NULL,444),(17,'Card',75,13.8,2,28,250,8,NULL,'2020-02-02','6454986387338876','null',NULL,NULL,201),(18,'Late payment',245,60,21,25,211,7,10,'2020-02-02',NULL,'Paid','2020-02-13',NULL,444),(19,'Card',300,65,22,29,211,7,10,'2020-02-02','7449155545893456','null',NULL,NULL,444),(20,'Cash',75,13.8,11,30,211,8,NULL,'2020-02-02',NULL,'null',NULL,NULL,201);
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travelagent`
--

LOCK TABLES `travelagent` WRITE;
/*!40000 ALTER TABLE `travelagent` DISABLE KEYS */;
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

-- Dump completed on 2020-04-11 12:47:47
