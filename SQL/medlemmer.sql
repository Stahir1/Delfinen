
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
  `senior` VARCHAR(255),
  `junior` VARCHAR(255),
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT = 0 DEFAULT CHARSET=UTF8;

INSERT INTO `medlemmer` (`ID`, `name`, `age`, `email`, `phoneNumber`, `City`, `zipCode`, `address`, `competitiveSwimmer`, `active`, `senior`, `junior`) VALUES
	(1, 'Rasmus Hansen', '16', 'rasmus@mail.dk', 57684957, 'Snekkersten', 1234, 'Hvorvej 15', true, true, false, true),
	(2, 'Henrik Nielsen', '17', 'henrik@mail.dk', 28374950, 'Jyllinge', 5362, 'Hejvej 76', true, true, false, true),
	(3, 'Daniel Andersen', '15', 'daniel@mail.dk', 27386950, 'Viborg', 7457, 'Vibvej 45', true, true, false, true),
	(4, 'Timo Leeh', '17', 'timo@mail.dk', 19704932, 'Kokkedal', 3074, 'Arnevej 76', true, true, false, true),
	(5, 'Andreas Wag', '14', 'andreas@mail.dk', 27384859, 'Armager', 8765, 'Armvej 38', true, true, false, true),
	(6, 'Trung Lei', '24', 'trung@mail.dk', 38695710, 'Humble', 2534, 'Sutdenvej 92', true, true, true, false),
	(7, 'Hamed Toun', '22', 'hamed@mail.dk', 39485032, 'Humble', 2534, 'Sutdenvej 34', true, true, true, false),
	(8, 'Sarah Baam', '28', 'sarah@mail.dk', 39685748, 'Snekkersten', 1234, 'Hvorvej 38', true, true, true, false),
	(9, 'Laura Frit', '26', 'laura@mail.dk', 27685940, 'Snekkersten', 1234, 'Hvorvej 63', true, true, true, false),
	(10, 'Cindy Xan', '28', 'cindy@mail.dk', 42314254, 'Armager', 8765, 'Armvej 10', true, true, true, false),
	(11, 'Bent Madsen', '12', 'bent@mail.dk', 94857675, 'Jyllinge', 5362, 'Hejvej 04', true, true, false, true),
	(12, 'Trine Madsen', '32', 'trine@mail.dk', 93847563, 'Jyllinge', 5362, 'Hejvej 04', false, false, true, false),
	(13, 'Jens Hansen', '14', 'jens@mail.dk', 92837465, 'Skagen', 3895, 'Minvej 79', true, true, false, true),
	(14, 'Line Mogenssen', '27', 'line@mail.dk', 39687958, 'Kokkedal', 3074, 'Arnevej 28', true, true, true, false),
	(15, 'Erik Henriksen', '43', 'erik@mail.dk', 12233445, 'Skagen', 3895, 'Minvej 12', true, true, true, false),
	(16, 'Ulla Rasmussen', '58', 'ulla@mail.dk', 25364758, 'Skagen', 3895, 'Minvej 16', false, false, true, false),
	(17, 'Mogens Benson', '65', 'mogens@mail.dk', 97867564, 'Kokkedal', 3074, 'Arnevej 102', false, true, true, false),
	(18, 'Nikolai Satnar', '34', 'nikolai@mail.dk', 38475867, 'Viby', 2459, 'Vabivej 107', false, true, true, false),
	(19, 'Mads Mikkelsen', '15', 'mads@mail.dk', 48596970, 'Skagen', 3895, 'Minvej 87', true, true, false, true),
	(20, 'Tina Mikkelsen', '71', 'tina@mail.dk', 98765434, 'Viborg', 7457, 'Vibvej 89', false, true, true, false);

-- Dumping data for table delfinen.medlemmer: ~0 rows (approximately)
/*!40000 ALTER TABLE `medlemmer` DISABLE KEYS */;
/*!40000 ALTER TABLE `medlemmer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- Dumping structure for tabel delfinen.medlemmer
DROP TABLE IF EXISTS `kontingentbetaling`;
CREATE TABLE IF NOT EXISTS `kontingentbetaling` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `hasPaid` bit(1) DEFAULT NULL,
  `date` VARCHAR(255),
  
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `kontingentbetaling` (`ID`, `name`, `age`, `active`, `amount`, `hasPaid`, `date`) VALUES
	(1, 'Rasmus Hansen', '16', true, 1000, true, '04-12-2020'),
	(2, 'Henrik Nielsen', '17', true, 1000, true, '02-12-2020'),
	(3, 'Daniel Andersen', '15', true, 1000, true, '03-12-2020'),
	(4, 'Timo Leeh', '17', true, 1000, true, '02-12-2020'),
	(5, 'Andreas Wag', '14', true, 1000, true, '02-12-2020'),
	(6, 'Trung Lei', '24', true, 1600, true, '01-12-2020'),
	(7, 'Hamed Toun', '22', true, 1600, true, '04-12-2020'),
	(8, 'Sarah Baam', '28', true, 1600, true, '03-12-2020'),
	(9, 'Laura Frit', '26', true, 1600, true, '02-12-2020'),
	(10, 'Cindy Xan', '28', true, 1600, true, '01-12-2020'),
	(11, 'Bent Madsen', '12', true, 1000, true, '02-12-2020'),
	(12, 'Trine Madsen', '32', false, 500, false, '02-12-2019'),
	(13, 'Jens Hansen', '14', true, 1000, true, '01-12-2020'),
	(14, 'Line Mogenssen', '27', true, 1600, true, '03-12-2020'),
	(15, 'Erik Henriksen', '43', true, 1600, true, '02-12-2020'),
	(16, 'Ulla Rasmussen', '58', false, 500, true, '01-12-2020'),
	(17, 'Mogens Benson', '65', true, 1200, false, '01-12-2019'),
	(18, 'Nikolai Satnar', '34', true, null, null, null),
	(19, 'Mads Mikkelsen', '15', true, 1000, true, '04-12-2020'),
	(20, 'Tina Mikkelsen', '71', true, 1200, true, '02-12-2020');


DROP TABLE IF EXISTS `svømmehold`;
CREATE TABLE IF NOT EXISTS `svømmehold` (
 `teamID` INT(11) DEFAULT NULL,
 `teamName` VARCHAR(255) DEFAULT NULL,
 `trainer` VARCHAR (255) DEFAULT NULL,
  `swimmerID` INT(11) NOT NULL,
  `swimmerAge` INT(11) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `svømmehold` (`teamID`, `teamName`, `trainer`, `swimmerID`, `swimmerAge`) VALUES
	(1, 'Ungdomshold', 'Erik Nielsen', 1, 16),
	(1, 'Ungdomshold', 'Erik Nielsen', 2, 17),
	(1, 'Ungdomshold', 'Erik Nielsen', 3, 15),
	(1, 'Ungdomshold', 'Erik Nielsen', 4, 17),
	(1, 'Ungdomshold', 'Erik Nielsen', 5, 14),
	(2, 'Seniorhold', 'Cecilie Karlsen', 6, 24),
	(2, 'Seniorhold', 'Cecilie Karlsen', 7, 22),
	(2, 'Seniorhold', 'Cecilie Karlsen', 8, 28),
	(2, 'Seniorhold', 'Cecilie Karlsen', 9, 26),
	(2, 'Seniorhold', 'Cecilie Karlsen', 10, 28),
	(1, 'Ungdomshold', 'Erik Nielsen', 11, 12),
	(1, 'Ungdomshold', 'Erik Nielsen', 13, 14),
	(2, 'Seniorhold', 'Cecilie Karlsen', 14, 27),
	(2, 'Seniorhold', 'Cecilie Karlsen', 15, 43),
	(1, 'Ungdomshold', 'Erik Nielsen', 19, 15);


DROP TABLE IF EXISTS `konkurrenceresultater`;
CREATE TABLE IF NOT EXISTS `konkurrenceresultater` (
 `swimmerID` INT(11) DEFAULT NULL,
 `eventname` VARCHAR (255) DEFAULT NULL,
 `eventCrawlTime` FLOAT(6, 3) DEFAULT NULL,
 `eventCrawlPlacement` VARCHAR (255) DEFAULT NULL,
 `eventButterflyTime` FLOAT(6, 3) DEFAULT NULL,
 `eventButtterflyPlacement` VARCHAR (255) DEFAULT NULL,
 `eventBackstrokeTime` FLOAT(6, 3) DEFAULT NULL,
 `eventBackstrokePlacement` VARCHAR (255) DEFAULT NULL,
 `eventBreaststrokeTime` FLOAT(6, 3) DEFAULT NULL,
 `eventBreaststrokePlacement` VARCHAR (255) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


INSERT INTO `konkurrenceresultater` (`swimmerID`, `eventname`, `eventCrawlTime`, `eventCrawlPlacement`, `eventButterflyTime`, 
	`eventButtterflyPlacement`, `eventBackstrokeTime`, `eventBackstrokePlacement`, `eventBreaststrokeTime`, `eventBreaststrokePlacement`) VALUES
	(1, 'Swim2Win', 43.069, 2, null, null, 54.178, 3, null, null),
	(2, 'Swim2Win', 41.009, 1, null, null, null, null, 67.401, 3),
	(3, 'Swim4TheWin', 54.101, 5, 45.760, 2, null, null, 45.983, 1),
	(4, 'Swim4TheWin', null, null, null, null, 45.243, 2, 56.243, 6),
	(5, 'Swim2Win', 54.785, 5, null, null, null, null, null, null),
	(6, 'Ready Set Swim', 45.002, 2, 34.950, 1, 41.981, 2, 39.981, 1),
	(7, 'Swimmer Time', 45.760, 2, 45.789, 3, 54.809, 1, 43.775, 3),
	(8, 'Swimmer Time', null, null, null, null, 63.530, 3, null, null),
	(10, 'Ready Set Swim', 43.910, 1, null, null, null, null, null, null),
	(15, 'iSwim', null, null, 43.561, 2, 54.678, 3, null, null),
	(19, 'iSwim', 65.543, 6, null, null, null, null, 45.534, 2);



DROP TABLE IF EXISTS `træningsresultater`;
CREATE TABLE IF NOT EXISTS `træningsresultater` (
 `swimmerID` INT(11) DEFAULT NULL,
 `crawlTime` FLOAT(6, 3) DEFAULT NULL,
 `crawlDate` VARCHAR (255) DEFAULT NULL,
 `butterflyTime` FLOAT(6, 3) DEFAULT NULL,
 `butterflyDate` VARCHAR (255) DEFAULT NULL,
 `backstrokeTime` FLOAT(6, 3) DEFAULT NULL,
 `backstrokeDate` VARCHAR (255) DEFAULT NULL,
 `breaststrokeTime` FLOAT(6, 3) DEFAULT NULL,
 `breaststrokeDate` VARCHAR (255) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `træningsresultater` (`swimmerID`, `crawlTime`, `crawlDate`, `butterflyTime`, 
	`butterflyDate`, `backstrokeTime`, `backstrokeDate`, `breaststrokeTime`, `breaststrokeDate`) VALUES
	(1, 45.239, '24-11-2019', 41.149, '24-11-2019', 52.013, '24-11-2019', 47.978, '24-11-2019'),
	(2, 42.578, '28-11-2019', 43.352, '28-11-2019', 65.239, '21-11-2019', 67.254, '23-11-2019'),
	(3, 44.453, '24-11-2019', 41.002, '22-11-2019', 47.239, '27-11-2019', 55.339, '23-11-2019'),
	(4, 45.278, '22-11-2019', 41.225, '26-11-2019', 48.229, '25-11-2019', 41.540, '29-11-2019'),
	(5, 34.139, '17-11-2019', 39.980, '19-11-2019', 43.939, '27-11-2019', 49.135, '22-11-2019'),
	(6, 42.339, '28-11-2019', 42.230, '21-11-2019', 45.247, '22-11-2019', 38.939, '03-12-2019'),
	(7, 39.829, '22-11-2019', 36.439, '21-11-2019', 37.001, '27-11-2019', 42.257, '17-11-2019'),
	(8, 43.874, '27-11-2019', 36.123, '28-11-2019', 41.641, '26-11-2019', 71.654, '25-11-2019'),
	(9, 42.339, '22-11-2019', 41.753, '21-11-2019', 67.232, '28-11-2019', 35.378, '22-11-2019'),
	(10, 36.237, '28-11-2019', 38.276, '26-11-2019', 45.239, '22-11-2019', 37.967, '01-12-2019'),
	(11, 56.239, '25-11-2019', 58.229, '22-11-2019', 65.233, '22-11-2019', 45.654, '21-11-2019'),
	(13, 43.279, '22-11-2019', 65.969, '22-11-2019', 44.463, '24-11-2019', 54.339, '21-11-2019'),
	(14, 42.451, '22-11-2019', 47.412, '22-11-2019', 57.643, '21-11-2019', 54.362, '26-11-2019'),
	(15, 47.437, '27-11-2019', 51.239, '22-11-2019', 67.239, '22-11-2019', 41.239, '22-11-2019'),
	(19, 52.718, '22-11-2019', 57.243, '22-11-2019', 64.532, '22-11-2019', 48.631, '22-11-2019');

