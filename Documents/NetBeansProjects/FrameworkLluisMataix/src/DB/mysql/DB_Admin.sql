-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bbdd_admin
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

DROP SCHEMA IF EXISTS bbdd_admin;

CREATE SCHEMA  bbdd_admin;

USE bbdd_admin;

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
  `Dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `Name` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Surname` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Mobile` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `Email` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `User` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Pass` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Avatar` varchar(900) COLLATE utf8_spanish_ci NOT NULL,
  `State` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Date_Birthday` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Age` int(11) NOT NULL,
  `Benefits` float NOT NULL,
  `Antiquity` int(11) NOT NULL,
  `Salary` float NOT NULL,
  `Activity` int(11) NOT NULL,
  `Hiring_Date` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`Dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('07261935F','Sergio','Pascual','654618954','Sergio@gmail.com','Sergioo','fdgsdfgsd','C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix\\src\\img\\avatar.png','Si','8/2/1991',25,100,0,1100,100,'18/6/2015'),('20457259R','Blanca','Suarez','564651358','Blanca@gmail.com','Blanca','fgsdfgsdfgdsf','C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix/src/modules/users/admin/model/utils/avatars/30179.png','No','16/7/1993',22,370,3,1370,90,'11/9/2012'),('21695921K','Lluis','Mataix','656197251','mataix.lluis@gmail.com','lluismat','lluismataix1234','C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix/src/modules/users/admin/model/utils/avatars/simpsons_apu.gif','No','15/4/1994',21,160,1,1160,60,'12/8/2014'),('48604856Z','Elena','Puerto','656197251','Elena@gmai.com','Elena95','123456789','C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix/src/modules/users/admin/model/utils/avatars/BayernMunchen.gif','Si','7/1/1995',21,100,0,1100,49,'10/6/2015'),('59699126G','John','Snow','646635547','johnsnow@gmail.com','johns','snow123','C:\\Users\\lluis\\Documents\\NetBeansProjects\\FrameworkLluisMataix/src/modules/users/admin/model/utils/avatars/avatarpic-l.png','Si','6/9/1979',36,100,0,1100,54,'23/5/2015');
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

-- Dump completed on 2016-05-03 12:18:55
