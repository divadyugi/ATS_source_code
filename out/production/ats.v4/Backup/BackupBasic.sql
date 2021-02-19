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
  `blankType` int DEFAULT NULL,
  PRIMARY KEY (`couponID`),
  UNIQUE KEY `couponID_UNIQUE` (`couponID`),
  KEY `blankIDCoupons_idx` (`blankIDCoupons`,`blankType`),
  CONSTRAINT `blankIDCoupons` FOREIGN KEY (`blankIDCoupons`, `blankType`) REFERENCES `blanks` (`blankID`, `Type`)
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
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
INSERT INTO `employees` VALUES (1,'changeMe','changeMe','test@gmail.com','ChangeMe','Office Manager');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixeddiscount`
--

LOCK TABLES `fixeddiscount` WRITE;
/*!40000 ALTER TABLE `fixeddiscount` DISABLE KEYS */;
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
  `blankType` int DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
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

-- Dump completed on 2020-04-11 12:49:16
