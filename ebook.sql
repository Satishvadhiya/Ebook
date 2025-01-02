-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2024 at 11:38 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `book_dtls`
--

CREATE TABLE `book_dtls` (
  `bookid` int(11) NOT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` varchar(20) DEFAULT NULL,
  `bookcategory` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `photo_name` varchar(500) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_dtls`
--

INSERT INTO `book_dtls` (`bookid`, `bookname`, `author`, `price`, `bookcategory`, `status`, `photo_name`, `email`) VALUES
(4, 'c', 'balaguruswamy', '500', 'New', 'Active', 'c.jpeg', 'admin'),
(5, 'html', 'balaguruswamy', '500', 'New', 'Active', 'html.png', 'admin'),
(3, 'java', 'balaguruswamy', '784', 'Old', 'Active', 'java.jpeg', 'admin'),
(7, 'c#', 'balaguruswamy', '699', 'Old', 'Active', 'images.png', 'admin'),
(8, 'mysql', 'balaguruswamy', '500', 'New', 'Active', 'html.png', 'admin'),
(19, 'c#', 'balaguruswamy', '200', 'New', 'Active', 'images.png', 'admin'),
(11, 'scc constable 2', 'yyy', '400', 'Old', 'Active', 'sscconstable.jpeg', 'yash@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `book_order`
--

CREATE TABLE `book_order` (
  `id` int(20) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `price` varchar(50) NOT NULL,
  `payment` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_order`
--

INSERT INTO `book_order` (`id`, `order_id`, `user_name`, `email`, `address`, `phone`, `book_name`, `author`, `price`, `payment`) VALUES
(19, 'BOOK-ORD-004', 'yash', 'yash@gmail.com', 'pbr,pbr,pbr,pbr,360575', '0987654321', 'mysql', 'balaguruswamy', '500.0', 'COD'),
(18, 'BOOK-ORD-003', 'satish', 'satish@gmail.com', 'jj,jj,jj,jj,2', '8154954005', 'html', 'balaguruswamy', '500.0', 'COD'),
(17, 'BOOK-ORD-002', 'satish', 'satish@gmail.com', 'jj,jj,jj,jj,2', '8154954005', 'mysql', 'balaguruswamy', '500.0', 'COD'),
(16, 'BOOK-ORD-001', 'satish', 'satish@gmail.com', 'jj,jj,jj,jj,2', '8154954005', 'c', 'balaguruswamy', '500.0', 'COD');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cid` int(20) NOT NULL,
  `bid` int(20) DEFAULT NULL,
  `uid` int(20) DEFAULT NULL,
  `bookname` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total_price` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cid`, `bid`, `uid`, `bookname`, `author`, `price`, `total_price`) VALUES
(42, 19, 2, 'c#', 'balaguruswamy', 200, 200),
(43, 19, 0, 'c#', 'balaguruswamy', 200, 200),
(25, 8, 1, 'mysql', 'balaguruswamy', 500, 500);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phno` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `phno`, `password`) VALUES
(1, 'yash', 'yash@gmail.com', '0987654321', '1234'),
(2, 'satish', 'satish@gmail.com', '8154954005', '1234'),
(10, 'satish', 'satish123@gmail.com', '8154954005', '1234'),
(9, 'satish', 'admin@gmail.com', '1234567890', 'admin'),
(11, 'vishal vadhiya', 'vishal@gmail.com', '7984613996', 'vishal');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_dtls`
--
ALTER TABLE `book_dtls`
  ADD PRIMARY KEY (`bookid`);

--
-- Indexes for table `book_order`
--
ALTER TABLE `book_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book_dtls`
--
ALTER TABLE `book_dtls`
  MODIFY `bookid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `book_order`
--
ALTER TABLE `book_order`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cid` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
