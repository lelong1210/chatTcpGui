-- MariaDB dump 10.19  Distrib 10.4.25-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: projectJava
-- ------------------------------------------------------
-- Server version	10.4.25-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `messInfo`
--

DROP TABLE IF EXISTS `messInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userSource` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userDes` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `messContent` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `file` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=461 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messInfo`
--

LOCK TABLES `messInfo` WRITE;
/*!40000 ALTER TABLE `messInfo` DISABLE KEYS */;
INSERT INTO `messInfo` VALUES (401,'lql','lql10','/home/lql/Documents/123.jpeg','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221109195709123.jpeg','2022-11-09 19:57:04'),(402,'lql10','lql','chaoooo',NULL,'2022-11-10 22:50:04'),(403,'lql','lql10','chao ban',NULL,'2022-11-10 22:50:12'),(404,'lql10','lql','helllo',NULL,'2022-11-10 22:50:39'),(405,'lql10','lql','rewr',NULL,'2022-11-10 22:50:44'),(406,'lql10','lql','rwerew',NULL,'2022-11-10 22:50:45'),(407,'lql10','lql','rwerwe',NULL,'2022-11-10 22:50:46'),(408,'lql10','lql','/home/lql/Documents/nhi.zip','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221110225708nhi.zip','2022-11-10 22:57:08'),(409,'lql2','lql','xin chao',NULL,'2022-11-10 22:59:23'),(410,'lql','lql2','chao ban',NULL,'2022-11-10 22:59:32'),(411,'lql','lql10','weqe',NULL,'2022-11-10 23:08:36'),(412,'lql','lql10','xin chao',NULL,'2022-11-11 00:19:47'),(413,'lql','lql2','kaka',NULL,'2022-11-11 00:23:11'),(414,'lql2','lql','hehe',NULL,'2022-11-11 00:23:26'),(415,'lql','lql10','/home/lql/Downloads/Lab 7. Lap Trinh HTTP.txt','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221111002432Lab 7. Lap Trinh HTTP.txt','2022-11-11 00:24:32'),(416,'lql','lql10','rrrr',NULL,'2022-11-11 07:59:30'),(417,'lamanh','lql','hihi',NULL,'2022-11-11 08:05:53'),(418,'lamanh','lql','lalaa',NULL,'2022-11-11 08:05:57'),(419,'lamanh','lql','12345',NULL,'2022-11-11 08:06:01'),(420,'lql','lamanh','chao',NULL,'2022-11-11 08:06:07'),(421,'lamanh','lql','D:\\LearnInSchool\\Lập trình mạng\\LapTrinhMang.docx','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221111080625LapTrinhMang.docx','2022-11-11 08:06:23'),(422,'lql','lamanh','xin chao',NULL,'2022-11-15 21:08:06'),(423,'lql','lamanh','hehe',NULL,'2022-11-15 21:08:12'),(424,'lql','lamanh','1',NULL,'2022-11-15 21:08:13'),(425,'lql','lamanh','1',NULL,'2022-11-15 21:08:13'),(426,'lql','lamanh','1',NULL,'2022-11-15 21:08:14'),(427,'lql','lamanh','1',NULL,'2022-11-15 21:08:14'),(428,'lql','lamanh','1',NULL,'2022-11-15 21:08:14'),(429,'lql','lamanh','1',NULL,'2022-11-15 21:08:15'),(430,'lql','lamanh','1',NULL,'2022-11-15 21:08:15'),(431,'lql','lamanh','12',NULL,'2022-11-15 21:08:16'),(432,'lql','lamanh','2',NULL,'2022-11-15 21:08:16'),(433,'lql','lamanh','2',NULL,'2022-11-15 21:08:16'),(434,'lql','lamanh','2',NULL,'2022-11-15 21:08:17'),(435,'lql','lamanh','2',NULL,'2022-11-15 21:08:17'),(436,'lql','lamanh','2',NULL,'2022-11-15 21:08:17'),(437,'lql','lamanh','2',NULL,'2022-11-15 21:08:18'),(438,'lql','lamanh','2',NULL,'2022-11-15 21:08:18'),(439,'lql','lamanh','2',NULL,'2022-11-15 21:08:18'),(440,'lql','lamanh','2',NULL,'2022-11-15 21:08:19'),(441,'lql','lamanh','2',NULL,'2022-11-15 21:08:19'),(442,'lql','lamanh','2',NULL,'2022-11-15 21:08:19'),(443,'lql','lamanh','2',NULL,'2022-11-15 21:08:20'),(444,'lql','lamanh','2',NULL,'2022-11-15 21:08:20'),(445,'lql','lql2','21212',NULL,'2022-11-15 21:08:41'),(446,'lql','lql2','312312',NULL,'2022-11-15 21:08:42'),(447,'lql','lql2','312312',NULL,'2022-11-15 21:08:42'),(448,'lql','lql2','3123123',NULL,'2022-11-15 21:08:43'),(449,'lql','lql2','3232',NULL,'2022-11-15 21:08:44'),(450,'lql2','lql','42324',NULL,'2022-11-15 21:09:12'),(451,'lql2','lql','4234',NULL,'2022-11-15 21:09:13'),(452,'lql2','lql','4343',NULL,'2022-11-15 21:09:14'),(453,'lql','lql10','23424',NULL,'2022-11-15 21:09:21'),(454,'lql','lql2','323',NULL,'2022-11-15 21:09:56'),(455,'lql','lql2','323',NULL,'2022-11-15 21:09:58'),(456,'lql2','lql','11',NULL,'2022-11-15 21:10:01'),(457,'lql2','lql','2',NULL,'2022-11-15 21:10:02'),(458,'lql2','lql','hello',NULL,'2022-11-15 23:55:54'),(459,'lql','lql2','kaka',NULL,'2022-11-15 23:56:01'),(460,'lql','lql2','/home/lql/project.sql','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221115235616project.sql','2022-11-15 23:56:16');
/*!40000 ALTER TABLE `messInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('lamanh','123456',1),('lql','12345678',1),('lql1','12345678',1),('lql10','12345678',1),('lql2','12345678',1),('lql4','12345678',1),('lql5','12345678',1),('lql6','12345678',1);
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

-- Dump completed on 2022-11-15 23:56:55
