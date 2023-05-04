-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: iniflexdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `tb_funcionario`
--

DROP TABLE IF EXISTS `tb_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_funcionario` (
  `id` binary(16) NOT NULL,
  `data_nascimento` date NOT NULL,
  `funcao` varchar(70) NOT NULL,
  `nome` varchar(10) NOT NULL,
  `salario` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j7qvded60vxad5tvs9ngbnu5l` (`data_nascimento`),
  UNIQUE KEY `UK_amf5gjurwvqle3lfvgngfy8kh` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_funcionario`
--

LOCK TABLES `tb_funcionario` WRITE;
/*!40000 ALTER TABLE `tb_funcionario` DISABLE KEYS */;
INSERT INTO `tb_funcionario` VALUES (_binary '2˘\\v\ÁJ§ã¨®hdjQ','1995-01-05','Recepcionista','Alice',2234.68),(_binary '0¿R$≠)BVñ>6˚;\‘˜','1996-02-09','Gerente','Helena',2799.93),(_binary 'A•8ΩÚF7Ø\‰ôn›≠','1994-07-08','Gerente','Laura',3017.45),(_binary 'zÚ\Ì{ı@–ç5N\‹NxW','1999-11-19','Operador','Heitor',1582.72),(_binary '{±¥˛NNß\»Û1R}','1961-05-02','Coordenador','Caio',9934.50),(_binary 'ÄB›ôLµ•\‚Üv∫¿','1988-10-14','Diretor','Miguel',19119.88),(_binary 'èã~π§Gû\≈6+\«}\‡´','1990-05-12','Operador','Jo√£o',2284.38),(_binary '\Ã\‹gæi˚KùüÈíöPñ','1993-03-31','Contador','Arthur',4071.84),(_binary '\Õ\ƒRÆöM£µ\œl\ÕÿºW','2003-05-24','Eletricista','Helo√≠sa',1606.85),(_binary '\Ëyˆî\"\‡G£à\Â™èUa','2000-10-18','Operador','Maria',2029.53);
/*!40000 ALTER TABLE `tb_funcionario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-03 19:40:37
