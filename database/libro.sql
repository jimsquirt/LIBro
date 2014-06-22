-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2014 at 10:51 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `libro`
--
CREATE DATABASE IF NOT EXISTS `libro` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `libro`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `show_all_book`()
begin
    select book_id, title, description, author, quantity 	
    from book 
    order by book_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_all_borrower`()
begin
    select borrower_id, name, address 	
    from borrower 
    order by borrower_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_all_transaction`()
begin
    select b.title, bor.name, t.quantity	
    from book b, borrower bor, transaction t
	where bor.borrower_id = t.borrower_id and b.book_id = t.book_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_book_by_author`(IN `book_author` VARCHAR(250))
begin
    select book_id, title, description, author, quantity 	
    from book 
	where author like CONCAT("%",book_author,"%")
    order by book_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_book_by_description`(IN `book_description` VARCHAR(250))
begin
    select book_id, title, description, author, quantity 	
    from book 
	where description like CONCAT("%",book_description,"%")
    order by book_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_book_by_title`(IN `book_title` VARCHAR(300))
begin
    select book_id, title, description, author, quantity 	
    from book 
	where title like CONCAT("%",book_title,"%")
    order by book_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_borrower_by_address`(IN `borrower_address` VARCHAR(350))
begin
    select borrower_id, name, address 	
    from borrower
	where address like CONCAT("%",borrower_address,"%")
    order by borrower_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_borrower_by_id`(IN `borrower_name` VARCHAR(250))
    NO SQL
begin
    select borrower_id, name, address 	
    from borrower 
	where name = CONCAT(borrower_name) 
    order by borrower_id;
  end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_borrower_by_name`(IN `borrower_name` VARCHAR(250))
begin
    select borrower_id, name, address 	
    from borrower 
	where name like CONCAT("%",borrower_name,"%") 
    order by borrower_id;
  end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) NOT NULL,
  `description` varchar(500) NOT NULL,
  `author` varchar(250) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `title`, `description`, `author`, `quantity`) VALUES
(8, 'Minna No Nihongo', 'Muzukashii desu.', 'Uchimura-sensei', 149);

--
-- Triggers `book`
--
DROP TRIGGER IF EXISTS `delete_empty_transaction`;
DELIMITER //
CREATE TRIGGER `delete_empty_transaction` AFTER UPDATE ON `book`
 FOR EACH ROW BEGIN
      
      DELETE FROM Transaction 
      WHERE Quantity = 0;
      
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `borrower`
--

CREATE TABLE IF NOT EXISTS `borrower` (
  `borrower_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `address` varchar(350) NOT NULL,
  PRIMARY KEY (`borrower_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `borrower`
--

INSERT INTO `borrower` (`borrower_id`, `name`, `address`) VALUES
(6, 'Lauritzkean Eric Cayobit', 'Leyte'),
(7, 'Ederlyn', 'Leyte');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `borrower_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `borrower_id` (`borrower_id`),
  KEY `book_id` (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=67 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`borrower_id`) REFERENCES `borrower` (`borrower_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
