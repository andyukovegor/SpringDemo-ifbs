--
-- Table structure for table `car_brand`
--

DROP TABLE IF EXISTS `car_brand`;
CREATE TABLE `car_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT 'None',
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `car_brand`
--

LOCK TABLES `car_brand` WRITE;
INSERT INTO `car_brand` VALUES (1,'Lada','www.lada.com'),(2,'BMW','www.bmw.com'),(3,'Kia','www.kia.com'),(4,'Tesla','www.tesla.com');
UNLOCK TABLES;

--
-- Table structure for table `car_model`
--

DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_brand_id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `productionDate` year(4) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `car_model`
--

LOCK TABLES `car_model` WRITE;
INSERT INTO `car_model` VALUES (1,1,'Kalina',2010,400.5),(2,1,'Largus',2005,900),(3,3,'Optima',2014,1200.8);
UNLOCK TABLES;

