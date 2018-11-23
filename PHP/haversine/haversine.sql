-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2018 at 10:35 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `haversine`
--

-- --------------------------------------------------------

--
-- Table structure for table `wisata`
--

CREATE TABLE `wisata` (
  `id` int(5) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `gambar` varchar(100) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wisata`
--

INSERT INTO `wisata` (`id`, `nama`, `gambar`, `lat`, `lng`) VALUES
(1, 'Masjid Agung Demak', 'masjid-agung-demak.jpg', -6.894649906672214, 110.63718136399984),
(2, 'Alun-alun Demak', '2432ff6461b2a5d67ebdc3550f9402bb.jpg', -6.894796, 110.638413),
(3, 'Makam Sunan Kalijaga', 'fb7efa06222e8033e1831f46dd9b12f5.jpg', -6.898446396766325, 110.64736470580101),
(4, 'Hutan Mangrove Demak', '0a0a59ecc0b14debc1ef0414d0f46fd0.jpg', -6.916692, 110.482984),
(5, 'Pantai Morosari', 'morosari.jpg', -6.924063087995197, 110.47853507101536),
(6, 'Taman Air Polaris', 'taman-air.jpg', -6.906109182781757, 110.65031144768),
(7, 'Brown Canyon Demak', '9653a8a328704518d8ecfdf588bb7b3f.jpg', -7.056777924901215, 110.48815783113241),
(8, 'Museum Glagah Wangi', '9287917af59810b5af71cfc23de2a50f.jpg', -6.89599, 110.637836);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `wisata`
--
ALTER TABLE `wisata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `wisata`
--
ALTER TABLE `wisata`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
