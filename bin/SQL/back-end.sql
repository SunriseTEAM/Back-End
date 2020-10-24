-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sunriseshop
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `add_to_cart`
--

DROP TABLE IF EXISTS `add_to_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_to_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `added_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_pk_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_to_cart`
--

LOCK TABLES `add_to_cart` WRITE;
/*!40000 ALTER TABLE `add_to_cart` DISABLE KEYS */;
INSERT INTO `add_to_cart` VALUES (39,1,1,150,'2020-10-15 23:16:06',1);
/*!40000 ALTER TABLE `add_to_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'shirt'),(2,'T-Shirts'),(3,'Blazers'),(4,'Musical Instruments'),(5,'Mobile');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout_cart`
--

DROP TABLE IF EXISTS `checkout_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `payment_type` enum('COD','ONLINE') DEFAULT NULL,
  `delivery_address` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout_cart`
--

LOCK TABLES `checkout_cart` WRITE;
/*!40000 ALTER TABLE `checkout_cart` DISABLE KEYS */;
INSERT INTO `checkout_cart` VALUES (10,2,6,9150,'2020-01-28 21:52:18',1,21144,'COD','test adding.'),(11,1,1,9150,'2020-01-28 21:52:18',1,21144,'COD','test adding.'),(12,2,1,18000,'2020-04-04 23:07:52',1,27393,'COD','delivery address'),(13,4,3,18000,'2020-04-04 23:07:52',1,27393,'COD','delivery address'),(14,1,1,150,'2020-04-04 23:08:55',1,20865,'COD','tsting.'),(15,1,1,1650,'2020-04-04 23:34:04',1,16909,'COD','testing address '),(16,2,1,1650,'2020-04-04 23:34:04',1,16909,'COD','testing address '),(17,1,3,900,'2020-09-18 00:23:56',1,21933,'COD','a'),(18,1,2,3300,'2020-09-21 10:57:05',1,24338,'COD','HCM'),(19,2,2,3300,'2020-09-21 10:57:05',1,24338,'COD','HCM'),(20,1,1,8650,'2020-09-21 20:31:03',1,23594,'COD','TMS '),(21,2,1,8650,'2020-09-21 20:31:03',1,23594,'COD','TMS '),(22,3,1,8650,'2020-09-21 20:31:03',1,23594,'COD','TMS '),(23,4,1,8650,'2020-09-21 20:31:03',1,23594,'COD','TMS '),(24,1,1,7300,'2020-09-30 08:04:05',1,22284,'COD','a'),(25,5,1,7300,'2020-09-30 08:04:05',1,22284,'COD','a'),(26,1,1,150,'2020-09-30 08:05:09',1,26683,'COD','a'),(27,1,1,150,'2020-09-30 08:05:38',1,20937,'COD','s'),(28,1,1,1650,'2020-09-30 08:34:14',1,27732,'COD','y'),(29,2,1,1650,'2020-09-30 08:34:14',1,27732,'COD','y'),(30,1,1,1650,'2020-09-30 09:07:19',1,25548,'COD','HCM'),(31,2,1,1650,'2020-09-30 09:07:19',1,25548,'COD','HCM'),(32,1,1,1650,'2020-09-30 09:08:48',1,25074,'COD','fds'),(33,2,1,1650,'2020-09-30 09:08:48',1,25074,'COD','fds'),(34,1,2,300,'2020-10-10 17:29:52',1,23553,'COD','ntt'),(35,2,3,9000,'2020-10-15 23:06:35',1,10447,'COD','ádasd');
/*!40000 ALTER TABLE `checkout_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` varchar(40) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `order_on` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_pk_id` (`product_id`),
  CONSTRAINT `product_pk_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `price` double DEFAULT NULL,
  `added_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `category_id` bigint DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `quatityavi` int DEFAULT NULL,
  `ratings` int DEFAULT NULL,
  `favourite` tinyint(1) DEFAULT NULL,
  `seller` varchar(45) DEFAULT NULL,
  `ImageUrl` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_fk` (`category_id`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'VÁY MINI A BASIC',150,'2019-05-10 00:00:00',1,'Hi',5,5,NULL,'Puti Fashion',''),(2,'VÁY MIDI WRAP SATIN',1500,'2019-06-11 00:00:00',1,NULL,NULL,NULL,NULL,'Puti Fashion',NULL),(4,'men blazers',5500,'2019-06-11 00:00:00',3,NULL,NULL,NULL,NULL,NULL,NULL),(5,'test4',6700,'2020-09-11 00:00:00',4,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_token` text,
  `type` int DEFAULT NULL,
  `address` text,
  `is_email_verified` int DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (17,'admin','admin@admin.com','12345678','2020-05-09 17:00:00',NULL,NULL,'DN',1,'0334118321'),(21,'user1','user1@gmail.com','12345678','2020-06-09 17:00:00',NULL,NULL,'DN',NULL,'0267832222'),(22,'user2','user2@gmail.com','12345678','2020-07-09 17:00:00',NULL,NULL,'DN',NULL,'0131113213');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-24 22:55:56
