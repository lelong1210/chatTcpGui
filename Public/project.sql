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
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messInfo`
--

LOCK TABLES `messInfo` WRITE;
/*!40000 ALTER TABLE `messInfo` DISABLE KEYS */;
INSERT INTO `messInfo` VALUES (596,'admin','lql','xin chao',NULL,'2022-11-24 16:14:36'),(597,'lql','admin','/home/lql/AI.py','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221124161444AI.py','2022-11-24 16:14:44'),(598,'admin','lql','/home/lql/ngix','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/ngix','2022-11-24 16:14:54'),(599,'tan','lql','hihi',NULL,'2022-11-24 21:46:13'),(600,'tan','lql','D:\\Android\\1653981955-dacs3-nguyenvandung-levantan-baocao.docx','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/202211242149401653981955-dacs3-nguyenvandung-levantan-baocao.docx','2022-11-24 21:47:10'),(601,'admin','tan','/home/lql/crack.py','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/crack.py','2022-11-24 21:50:05'),(602,'lql','tan','/home/lql/Downloads/1.mp3','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/202211242215161.mp3','2022-11-24 22:15:16'),(603,'admin','tan3','eee',NULL,'2022-11-24 22:28:09'),(604,'admin','tan3','/home/lql/Downloads/1.mp3','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/1.mp3','2022-11-24 22:28:19'),(605,'lql','tan','/home/lql/crack.py','/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/20221125084222crack.py','2022-11-25 08:42:22'),(606,'lql2','lql','xin chao',NULL,'2022-11-25 08:53:20'),(607,'lql','lql2','chao ban',NULL,'2022-11-25 08:53:29'),(608,'lql2','lql','hiiii',NULL,'2022-11-25 08:53:33');
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
INSERT INTO `user` VALUES ('lamanh','123456',1),('lql','12345678',1),('lql1','12345678',1),('lql10','12345678',1),('lql12','12345678',1),('lql15','12345678',0),('lql2','12345678',1),('lql4','12345678',1),('lql5','12345678',0),('lql6','12345678',1),('tan','123',1),('tan1','123',1),('tan2','123',1),('tan3','123',1);
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

-- Dump completed on 2022-12-05 19:05:14
