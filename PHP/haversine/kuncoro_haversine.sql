/*
SQLyog Ultimate v10.42 
MySQL - 5.5.5-10.1.9-MariaDB : Database - kuncoro_haversine
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kuncoro_haversine` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `kuncoro_haversine`;

/*Table structure for table `wisata` */

DROP TABLE IF EXISTS `wisata`;

CREATE TABLE `wisata` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) DEFAULT NULL,
  `gambar` varchar(100) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `wisata` */

insert  into `wisata`(`id`,`nama`,`gambar`,`lat`,`lng`) values (1,'Masjid Agung Demak','masjid-agung-demak.jpg',-6.894649906672214,110.63718136399984),(2,'Alun-alun Demak','2432ff6461b2a5d67ebdc3550f9402bb.jpg',-6.894796,110.638413),(3,'Makam Sunan Kalijaga','fb7efa06222e8033e1831f46dd9b12f5.jpg',-6.898446396766325,110.64736470580101),(4,'Hutan Mangrove Demak','0a0a59ecc0b14debc1ef0414d0f46fd0.jpg',-6.916692,110.482984),(5,'Pantai Morosari','morosari.jpg',-6.924063087995197,110.47853507101536),(6,'Taman Air Polaris','taman-air.jpg',-6.906109182781757,110.65031144768),(7,'Brown Canyon Demak','9653a8a328704518d8ecfdf588bb7b3f.jpg',-7.056777924901215,110.48815783113241),(8,'Museum Glagah Wangi','9287917af59810b5af71cfc23de2a50f.jpg',-6.89599,110.637836);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
