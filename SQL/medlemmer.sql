-- --------------------------------------------------------
-- Vært:                         127.0.0.1
-- Server-version:               8.0.18 - MySQL Community Server - GPL
-- ServerOS:                     Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for delfinen
CREATE DATABASE IF NOT EXISTS `delfinen` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `delfinen`;

-- Dumping structure for tabel delfinen.medlemmer
DROP TABLE IF EXISTS `medlemmer`;
CREATE TABLE IF NOT EXISTS `medlemmer` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `zipCode` int(11) NOT NULL DEFAULT '0',
  `address` varchar(255) DEFAULT NULL,
  `competitiveSwimmer` bit(1) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT = 0 DEFAULT CHARSET=UTF8;

-- Dumping data for table delfinen.medlemmer: ~0 rows (approximately)
/*!40000 ALTER TABLE `medlemmer` DISABLE KEYS */;
/*!40000 ALTER TABLE `medlemmer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
