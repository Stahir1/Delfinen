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
DROP DATABASE IF EXISTS `delfinen`;
CREATE DATABASE IF NOT EXISTS `delfinen` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `delfinen`;

-- Dumping structure for tabel delfinen.kontingentbetaling
DROP TABLE IF EXISTS `kontingentbetaling`;
CREATE TABLE IF NOT EXISTS `kontingentbetaling` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `hasPaid` bit(1) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table delfinen.kontingentbetaling: ~0 rows (approximately)
/*!40000 ALTER TABLE `kontingentbetaling` DISABLE KEYS */;
INSERT INTO `kontingentbetaling` (`ID`, `name`, `age`, `active`, `amount`, `hasPaid`, `date`) VALUES
	(8, 'Test', 7, b'1', NULL, NULL, NULL),
	(8, 'Test', 7, b'1', NULL, NULL, NULL),
	(9, 'NewD', 55, b'1', NULL, NULL, NULL),
	(8, 'Test', 7, b'1', NULL, NULL, NULL),
	(9, 'NewD', 55, b'1', NULL, NULL, NULL),
	(10, 'Dnol', 5, b'1', NULL, NULL, NULL);
/*!40000 ALTER TABLE `kontingentbetaling` ENABLE KEYS */;

-- Dumping structure for tabel delfinen.medlemmer
DROP TABLE IF EXISTS `medlemmer`;
CREATE TABLE IF NOT EXISTS `medlemmer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `zipCode` int(11) NOT NULL DEFAULT '0',
  `address` varchar(255) DEFAULT NULL,
  `competitiveSwimmer` bit(1) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `senior` varchar(255) DEFAULT NULL,
  `junior` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table delfinen.medlemmer: ~0 rows (approximately)
/*!40000 ALTER TABLE `medlemmer` DISABLE KEYS */;
INSERT INTO `medlemmer` (`ID`, `name`, `age`, `email`, `phoneNumber`, `City`, `zipCode`, `address`, `competitiveSwimmer`, `active`, `senior`, `junior`) VALUES
	(8, 'Test', 7, 'test@gmail.com', 28282828, 'test', 1000, 'test', b'1', b'1', 'Nej', 'Ja'),
	(9, 'NewD', 55, 'newtesting@gmail.com', 29304135, 'Dalg', 1500, 'tvny', b'1', b'1', 'Ja', 'Nej'),
	(10, 'Dnol', 5, 'plvew@hotmail.com', 21212112, 'vrwao', 1400, '1t', b'1', b'1', 'Nej', 'Ja');
/*!40000 ALTER TABLE `medlemmer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
