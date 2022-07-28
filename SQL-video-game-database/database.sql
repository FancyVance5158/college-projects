-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 30, 2022 at 05:20 PM
-- Server version: 10.3.34-MariaDB-0ubuntu0.20.04.1
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joravanc_3_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `composer`
--

CREATE TABLE `composer` (
  `composerID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `composer`
--

INSERT INTO `composer` (`composerID`, `name`) VALUES
(1, 'Manaka Kataoka'),
(2, 'Yasuaki Iwata'),
(3, 'Hajime Wakai'),
(4, 'Kazuki Muraoka'),
(5, 'Hiroyuki Togo'),
(6, 'Takanari Ishiyama'),
(7, 'Lee Jeon Myung'),
(8, 'Maki Kirioka'),
(9, 'Minako Hamano'),
(10, 'Akira Fujiwara'),
(11, 'Tsukasa Saitoh'),
(12, 'Shoi Miyazawa'),
(13, 'Tai Tomisawa'),
(14, 'Yuka Kitamura'),
(15, 'Yoshimi Kudo'),
(16, 'Mick Gordon'),
(17, 'Kelly Bailey'),
(18, 'Mike Morasky'),
(19, 'Henry Jackman'),
(20, 'Mahito Yokota'),
(21, 'Koji Kondo'),
(22, 'Hideki Naganuma'),
(23, 'Richard Jacques');

-- --------------------------------------------------------

--
-- Table structure for table `composerGame`
--

CREATE TABLE `composerGame` (
  `composerID` int(11) NOT NULL,
  `gameID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `composerGame`
--

INSERT INTO `composerGame` (`composerID`, `gameID`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 3),
(10, 3),
(11, 4),
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(16, 5),
(17, 6),
(18, 6),
(19, 7),
(20, 8),
(21, 8);

-- --------------------------------------------------------

--
-- Table structure for table `developer`
--

CREATE TABLE `developer` (
  `developerID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `developer`
--

INSERT INTO `developer` (`developerID`, `name`) VALUES
(1, 'Nintendo EPD'),
(2, 'Konami'),
(3, 'Nintendo R&D 1'),
(4, 'FromSoftware'),
(5, 'id Software'),
(6, 'Valve'),
(7, 'Naughty Dog'),
(8, 'Nintendo EAD'),
(9, 'Smilebit');

-- --------------------------------------------------------

--
-- Table structure for table `director`
--

CREATE TABLE `director` (
  `directorID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `director`
--

INSERT INTO `director` (`directorID`, `name`) VALUES
(1, 'Hidemaro Fujibayashi'),
(2, 'Hideo Kojima'),
(3, 'Yoshio Sakamoto'),
(4, 'Takehiko Hosokawa'),
(5, 'Hidetaka Miyazaki'),
(6, 'Yui Tanimura'),
(7, 'Hugo Martin'),
(8, 'Bruce Straley'),
(9, 'Neil Druckmann'),
(10, 'Yoshiaki Koizumi'),
(11, 'Masayoshi Kikuchi');

-- --------------------------------------------------------

--
-- Table structure for table `directorGame`
--

CREATE TABLE `directorGame` (
  `directorID` int(11) NOT NULL,
  `gameID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `directorGame`
--

INSERT INTO `directorGame` (`directorID`, `gameID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 3),
(5, 4),
(6, 4),
(7, 5),
(8, 7),
(9, 7),
(10, 8);

-- --------------------------------------------------------

--
-- Stand-in structure for view `fullGameList`
-- (See below for the actual view)
--
CREATE TABLE `fullGameList` (
`Title` varchar(100)
,`Platform` varchar(50)
,`Developer` varchar(50)
,`Publisher` varchar(50)
,`physical` tinyint(1)
,`ESRB Rating` varchar(50)
,`Release Date` date
);

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `gameID` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `platformID` int(11) NOT NULL,
  `developerID` int(11) NOT NULL,
  `publisherID` int(11) NOT NULL,
  `physical` tinyint(1) NOT NULL,
  `ratingID` int(11) NOT NULL,
  `releaseDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`gameID`, `title`, `platformID`, `developerID`, `publisherID`, `physical`, `ratingID`, `releaseDate`) VALUES
(1, 'The Legend of Zelda: Breath of the Wild', 1, 1, 1, 1, 2, '2017-03-03'),
(2, 'Metal Gear Solid', 2, 2, 2, 1, 4, '1998-10-21'),
(3, 'Metroid Fusion', 3, 3, 1, 1, 1, '2002-11-17'),
(4, 'Elden Ring', 4, 4, 3, 0, 4, '2022-02-25'),
(5, 'DOOM Eternal', 4, 5, 4, 0, 4, '2020-03-20'),
(6, 'Portal', 4, 6, 5, 0, 2, '2007-10-10'),
(7, 'Uncharted 4', 5, 7, 6, 1, 4, '2016-05-10'),
(8, 'Super Mario Galaxy', 6, 8, 1, 1, 1, '2007-11-12');

--
-- Triggers `game`
--
DELIMITER $$
CREATE TRIGGER `gamesBeforeDelete` BEFORE DELETE ON `game` FOR EACH ROW BEGIN
		INSERT INTO soldGames
		SET
		gameID = OLD.gameID,
		title = OLD.title,
		platformID = OLD.platformID,
		developerID = OLD.developerID,
		publisherID = OLD.publisherID,
		physical = OLD.physical,
		ratingID = OLD.ratingID,
        releaseDate = OLD.releaseDate,
		sellDate = CURRENT_DATE();
	END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `gamesForCousin`
-- (See below for the actual view)
--
CREATE TABLE `gamesForCousin` (
`Title` varchar(100)
,`System` varchar(50)
,`ESRB Rating` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genreID` int(11) NOT NULL,
  `genre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreID`, `genre`) VALUES
(1, 'Action-Adventure'),
(2, 'Stealth'),
(3, 'Platformer'),
(4, 'Metroidvania'),
(5, 'Open-World'),
(6, 'ARPG'),
(7, 'FPS'),
(8, 'Puzzle'),
(9, 'Sports');

-- --------------------------------------------------------

--
-- Table structure for table `genreGame`
--

CREATE TABLE `genreGame` (
  `genreID` int(11) NOT NULL,
  `gameID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genreGame`
--

INSERT INTO `genreGame` (`genreID`, `gameID`) VALUES
(1, 1),
(1, 2),
(1, 7),
(2, 2),
(3, 3),
(3, 6),
(3, 8),
(4, 3),
(5, 4),
(6, 4),
(7, 5),
(7, 6),
(8, 6);

-- --------------------------------------------------------

--
-- Table structure for table `manufacturer`
--

CREATE TABLE `manufacturer` (
  `manufacturerID` int(11) NOT NULL,
  `manufacturer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manufacturer`
--

INSERT INTO `manufacturer` (`manufacturerID`, `manufacturer`) VALUES
(1, 'Nintendo'),
(2, 'Sony'),
(3, 'Valve'),
(4, 'Microsoft');

-- --------------------------------------------------------

--
-- Table structure for table `platform`
--

CREATE TABLE `platform` (
  `platformID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `releaseYear` year(4) NOT NULL,
  `display` varchar(4) NOT NULL,
  `handheld` tinyint(1) NOT NULL,
  `manufacturerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `platform`
--

INSERT INTO `platform` (`platformID`, `name`, `releaseYear`, `display`, `handheld`, `manufacturerID`) VALUES
(1, 'Nintendo Switch', 2017, 'HDTV', 1, 1),
(2, 'Playstation 1', 1995, 'CRT', 0, 2),
(3, 'Game Boy Advance', 2001, 'LCD', 1, 1),
(4, 'Steam', 2003, 'HDTV', 0, 3),
(5, 'Playstation 4', 2013, 'HDTV', 0, 2),
(6, 'Nintendo Wii', 2006, 'CRT', 0, 1),
(7, 'Xbox', 2001, 'CRT', 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `publisherID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`publisherID`, `name`) VALUES
(1, 'Nintendo'),
(2, 'Konami'),
(3, 'Bandai Namco'),
(4, 'Bethesda'),
(5, 'Valve'),
(6, 'Sony'),
(7, 'Sega');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `ratingID` int(11) NOT NULL,
  `rating` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`ratingID`, `rating`) VALUES
(1, 'Everyone'),
(2, 'Everyone 10+'),
(3, 'Teen'),
(4, 'Mature');

-- --------------------------------------------------------

--
-- Table structure for table `soldGames`
--

CREATE TABLE `soldGames` (
  `gameID` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `platformID` int(11) NOT NULL,
  `developerID` int(11) NOT NULL,
  `publisherID` int(11) NOT NULL,
  `physical` tinyint(1) NOT NULL,
  `ratingID` int(11) NOT NULL,
  `releaseDate` date NOT NULL,
  `sellDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `soldGames`
--

INSERT INTO `soldGames` (`gameID`, `title`, `platformID`, `developerID`, `publisherID`, `physical`, `ratingID`, `releaseDate`, `sellDate`) VALUES
(9, 'Jet Set Radio Future', 7, 9, 7, 1, 3, '2002-02-25', '2022-04-30');

-- --------------------------------------------------------

--
-- Structure for view `fullGameList`
--
DROP TABLE IF EXISTS `fullGameList`;

CREATE ALGORITHM=UNDEFINED DEFINER=`joravanc`@`localhost` SQL SECURITY DEFINER VIEW `fullGameList`  AS  select `game`.`title` AS `Title`,`platform`.`name` AS `Platform`,`developer`.`name` AS `Developer`,`publisher`.`name` AS `Publisher`,`game`.`physical` AS `physical`,`rating`.`rating` AS `ESRB Rating`,`game`.`releaseDate` AS `Release Date` from ((((`game` join `platform` on(`game`.`platformID` = `platform`.`platformID`)) join `developer` on(`game`.`developerID` = `developer`.`developerID`)) join `publisher` on(`game`.`publisherID` = `publisher`.`publisherID`)) join `rating` on(`game`.`ratingID` = `rating`.`ratingID`)) order by `game`.`title` ;

-- --------------------------------------------------------

--
-- Structure for view `gamesForCousin`
--
DROP TABLE IF EXISTS `gamesForCousin`;

CREATE ALGORITHM=UNDEFINED DEFINER=`joravanc`@`localhost` SQL SECURITY DEFINER VIEW `gamesForCousin`  AS  select `game`.`title` AS `Title`,`platform`.`name` AS `System`,`rating`.`rating` AS `ESRB Rating` from ((`game` join `platform` on(`game`.`platformID` = `platform`.`platformID`)) join `rating` on(`game`.`ratingID` = `rating`.`ratingID`)) where `game`.`physical` = 1 and `game`.`ratingID` in (1,2,3) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `composer`
--
ALTER TABLE `composer`
  ADD PRIMARY KEY (`composerID`);

--
-- Indexes for table `composerGame`
--
ALTER TABLE `composerGame`
  ADD PRIMARY KEY (`composerID`,`gameID`),
  ADD KEY `composerID` (`composerID`,`gameID`),
  ADD KEY `composerGame-gameID` (`gameID`);

--
-- Indexes for table `developer`
--
ALTER TABLE `developer`
  ADD PRIMARY KEY (`developerID`);

--
-- Indexes for table `director`
--
ALTER TABLE `director`
  ADD PRIMARY KEY (`directorID`);

--
-- Indexes for table `directorGame`
--
ALTER TABLE `directorGame`
  ADD PRIMARY KEY (`directorID`,`gameID`),
  ADD KEY `directorID` (`directorID`,`gameID`),
  ADD KEY `directorGame-gameID` (`gameID`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`gameID`),
  ADD KEY `platformID` (`platformID`),
  ADD KEY `developerID` (`developerID`,`publisherID`,`ratingID`),
  ADD KEY `game-publisherID` (`publisherID`),
  ADD KEY `game-ratingID` (`ratingID`);

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genreID`);

--
-- Indexes for table `genreGame`
--
ALTER TABLE `genreGame`
  ADD PRIMARY KEY (`genreID`,`gameID`),
  ADD KEY `genreID` (`genreID`),
  ADD KEY `genreGame-gameID` (`gameID`);

--
-- Indexes for table `manufacturer`
--
ALTER TABLE `manufacturer`
  ADD PRIMARY KEY (`manufacturerID`);

--
-- Indexes for table `platform`
--
ALTER TABLE `platform`
  ADD PRIMARY KEY (`platformID`),
  ADD KEY `manufacturerID` (`manufacturerID`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`publisherID`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`ratingID`);

--
-- Indexes for table `soldGames`
--
ALTER TABLE `soldGames`
  ADD PRIMARY KEY (`gameID`),
  ADD KEY `platformID` (`platformID`),
  ADD KEY `developerID` (`developerID`,`publisherID`,`ratingID`),
  ADD KEY `game-publisherID` (`publisherID`),
  ADD KEY `game-ratingID` (`ratingID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `composer`
--
ALTER TABLE `composer`
  MODIFY `composerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `developer`
--
ALTER TABLE `developer`
  MODIFY `developerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `director`
--
ALTER TABLE `director`
  MODIFY `directorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genreID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `manufacturer`
--
ALTER TABLE `manufacturer`
  MODIFY `manufacturerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `platform`
--
ALTER TABLE `platform`
  MODIFY `platformID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `publisherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `ratingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `soldGames`
--
ALTER TABLE `soldGames`
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `composerGame`
--
ALTER TABLE `composerGame`
  ADD CONSTRAINT `composerGame-composerID` FOREIGN KEY (`composerID`) REFERENCES `composer` (`composerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `composerGame-gameID` FOREIGN KEY (`gameID`) REFERENCES `game` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `directorGame`
--
ALTER TABLE `directorGame`
  ADD CONSTRAINT `directorGame-directorID` FOREIGN KEY (`directorID`) REFERENCES `director` (`directorID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `directorGame-gameID` FOREIGN KEY (`gameID`) REFERENCES `game` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game-developerID` FOREIGN KEY (`developerID`) REFERENCES `developer` (`developerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `game-platformID` FOREIGN KEY (`platformID`) REFERENCES `platform` (`platformID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `game-publisherID` FOREIGN KEY (`publisherID`) REFERENCES `publisher` (`publisherID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `game-ratingID` FOREIGN KEY (`ratingID`) REFERENCES `rating` (`ratingID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `genreGame`
--
ALTER TABLE `genreGame`
  ADD CONSTRAINT `genreGame-gameID` FOREIGN KEY (`gameID`) REFERENCES `game` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `genreGame-genreID` FOREIGN KEY (`genreID`) REFERENCES `genre` (`genreID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `platform`
--
ALTER TABLE `platform`
  ADD CONSTRAINT `platform-manufacturerID` FOREIGN KEY (`manufacturerID`) REFERENCES `manufacturer` (`manufacturerID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
