-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bbdd_admin
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `Dni` varchar(9) CHARACTER SET latin1 NOT NULL,
  `Name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Surname` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Mobile` varchar(9) CHARACTER SET latin1 NOT NULL,
  `Email` varchar(45) CHARACTER SET latin1 NOT NULL,
  `User` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Pass` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Avatar` varchar(100) CHARACTER SET latin1 NOT NULL,
  `State` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Date_Birthday` varchar(50) CHARACTER SET latin1 NOT NULL,
  `Age` int(11) NOT NULL,
  `Benefits` float NOT NULL,
  `Antiquity` int(11) NOT NULL,
  `Salary` float NOT NULL,
  `Activity` int(11) NOT NULL,
  `Hiring_Date` varchar(50) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`Dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('21695921k','Lluis','Mataix','656197251','mataix.lluis@gmail.com','lluismat','1234','sdfasdf','yes','15/04/1994',21,2000,5,1500,50,'30/11/2015');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-07 18:01:48
