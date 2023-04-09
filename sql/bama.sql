-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2020 at 09:13 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bama`
--

-- --------------------------------------------------------

--
-- Table structure for table `info`
--

CREATE TABLE `info` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `lastname` varchar(40) NOT NULL,
  `post` varchar(30) NOT NULL,
  `children_num` int(2) NOT NULL,
  `gmail` varchar(250) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `info`
--

INSERT INTO `info` (`id`, `name`, `lastname`, `post`, `children_num`, `gmail`, `phone`, `password`) VALUES
(1, 'Peter', 'Parker', 'CopyWriter', 2, 'peter.parker@gmail.com', '09143432323', 'peterpass'),
(2, 'David', 'Smith', 'Art Director', 1, 'davidgmail@gmail.com', '09193458765', 'davidpass'),
(3, 'Maria', 'Garcia', 'Advertising Manager', 2, 'maria@gmail.com', '09145568743', 'mariapass'),
(4, 'Jake', 'Hernandez', 'Account Executive', 3, 'jake.herrr@gmail.com', '09231235456', 'jakepass'),
(5, 'Emily', 'Johnson', 'Account Coordinator', 1, 'emily+johnson@gmail.com', '09149912343', 'emilypass'),
(6, 'Harry', 'Brown', 'CopyWriter', 1, 'harryy@gmail.com', '09149916621', 'harrypass'),
(7, 'Jacob', 'Wilson', 'Creative Assistant', 2, 'jacob.wilson@gmail.com', '09144915566', 'jacobpass'),
(8, 'Richard', 'Evans', 'Creative Director', 1, 'richard@gmail.com', '09148732245', 'richardpass'),
(9, 'Mia', 'Roberts', 'Media Buyer', 0, 'mia.roberts@gmail.com', '09368894354', 'miapass'),
(10, 'Emily', 'Walsh', 'Media Assistant', 1, 'emily.walsh@gmail.com', '09215522123', 'emilypass'),
(11, 'Mohammad Amin', 'Sarbazi', 'CEO', 0, 'mmd.amin.ma@gmail.com', '09215537231', 'mmdaminpass'),
(12, 'Ida', 'Fallah', 'CEO', 0, 'ida.fallah@gmail.com', '09215458754', 'idapass'),
(13, 'Emma', 'Stone', 'CopyWriter', 2, 'emma.stone@gmail.com', '09145623231', 'emmapass'),
(14, 'Mohanna', 'Samadzade', 'CEO', 0, 'mohanna@gmail.com', '09144954321', 'mohannapass'),
(15, 'Babak', 'Mmdpour', 'CEO', 0, 'babakmmdpour@gmail.com', '09213434545', 'babakpass');

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `post` varchar(30) NOT NULL,
  `daily_wage` varchar(20) NOT NULL,
  `daily_work` varchar(20) NOT NULL,
  `overtime` varchar(20) NOT NULL,
  `month` varchar(20) NOT NULL,
  `housing` varchar(20) NOT NULL,
  `family` varchar(20) NOT NULL,
  `insurance` varchar(20) NOT NULL,
  `children` varchar(20) NOT NULL,
  `mission` varchar(20) NOT NULL,
  `tax` varchar(20) NOT NULL,
  `in_total` varchar(20) NOT NULL,
  `d_insurance` varchar(20) NOT NULL,
  `d_tax` varchar(20) NOT NULL,
  `d_loan` varchar(20) NOT NULL,
  `d_total` varchar(20) NOT NULL,
  `out_total` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`post`, `daily_wage`, `daily_work`, `overtime`, `month`, `housing`, `family`, `insurance`, `children`, `mission`, `tax`, `in_total`, `d_insurance`, `d_tax`, `d_loan`, `d_total`, `out_total`) VALUES
('CopyWriter', '100,000', '08:00', '00:30', '3,000,000', '200,000', '300,000', '100,000', '100,000', '400,000', '250,000', '4,350,000', '220,000', '120,000', '50,000', '390,000', '3,960,000'),
('Art Director', '200,000', '10:00', '00:00', '6,000,000', '350,000', '100,000', '200,000', '150,000', '300,000', '250,000', '7,350,000', '280,000', '120,000', '240,000', '640,000', '6,710,000'),
('Advertising Manager', '300,000', '07:00', '00:00', '9,000,000', '300,000', '450,000', '300,000', '200,000', '350,000', '200,000', '10,800,000', '450,000', '300,000', '150,000', '900,000', '9,900,000');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `chat` longtext NOT NULL,
  `leave` longtext NOT NULL,
  `attendance` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `chat`, `leave`, `attendance`) VALUES
(1, '<1:6 owner=1 tab=2>Hello there</1:6>', '', ''),
(2, '<2:4 owner=2 tab=1>Hello there</2:4><2:4 owner=2 tab=2>Woooo this is great !!!</2:4><2:4 owner=2 tab=3>Woooo this is great !!!</2:4><1:2 owner=2 tab=1>Hoolly moooly</1:2><2:6 owner=2 tab=2>Hello there</2:6>', '1!2020-06-09!2020-06-12!Accepted;', ''),
(3, '', '', ''),
(4, '<4:12 owner=4 tab=4>Sorry Ida. I\'ve got work-_- but you could ask david he is free</4:12>', '', ''),
(5, '', '1!2020-07-20!2020-07-22!Accepted;', ''),
(6, '<1:6 owner=6 tab=1>Hi peter</1:6><2:6 owner=6 tab=1>Hi David</2:6>', '1!2020-06-10!2020-06-12!Rejected;', ''),
(7, '', '', ''),
(8, '', '', ''),
(9, '', '1!2020-07-20!2020-07-22!Accepted;', ''),
(10, '', '', ''),
(11, '', '', ''),
(12, '<4:12 owner=12 tab=1>Hi jake</4:12><4:12 owner=12 tab=2>How are you</4:12><4:12 owner=12 tab=3>Could you do this project for me ? please i need to go</4:12>', '', ''),
(13, '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `info`
--
ALTER TABLE `info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
