-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: salemarket
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chinhanh`
--

DROP TABLE IF EXISTS `chinhanh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chinhanh` (
  `MaChiNhanh` int NOT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaChiNhanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chinhanh`
--

LOCK TABLES `chinhanh` WRITE;
/*!40000 ALTER TABLE `chinhanh` DISABLE KEYS */;
INSERT INTO `chinhanh` VALUES (1,'Hà Nội'),(2,'Hồ Chí Minh'),(3,'Đà Nẵng'),(4,'Đồng Nai');
/*!40000 ALTER TABLE `chinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethd`
--

DROP TABLE IF EXISTS `chitiethd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethd` (
  `MaChiTietHD` int DEFAULT NULL,
  `MaHoaDon` int NOT NULL,
  `MaSanPham` int NOT NULL,
  `SoLuong` float DEFAULT NULL,
  `ThanhTien` float DEFAULT NULL,
  PRIMARY KEY (`MaHoaDon`,`MaSanPham`),
  KEY `chitiethd_ibfk_2` (`MaSanPham`),
  CONSTRAINT `chitiethd_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`) ON DELETE CASCADE,
  CONSTRAINT `chitiethd_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethd`
--

LOCK TABLES `chitiethd` WRITE;
/*!40000 ALTER TABLE `chitiethd` DISABLE KEYS */;
INSERT INTO `chitiethd` VALUES (1,1,1,2,18000),(2,1,2,1,15000),(3,2,3,3,45000),(4,2,4,2,10000),(5,3,5,4,24000),(6,3,6,1,27000),(7,4,7,3,75000),(8,4,8,2,24000),(9,5,9,2,36000),(10,5,10,1,90000),(11,6,1,100,1000000),(11,6,2,1,15000),(13,7,1,1,10000),(14,8,1,1.05,10500),(15,9,1,1,10000),(15,9,2,1,15000),(17,10,1,1,10000),(17,10,2,1,15000),(19,11,1,0,0),(19,11,2,0,0),(21,12,1,1,10000),(21,12,2,1,15000),(23,13,6,1000,30000000),(23,13,10,1,100000),(25,14,1,1,10000),(25,14,2,1000,15000000),(27,15,1,10,100000),(27,15,2,1,15000),(29,16,1,1,10000),(29,16,2,10,150000),(31,17,1,1,10000),(31,17,2,10,150000),(33,18,1,1,10000),(33,18,2,1,15000),(33,18,3,1,20000),(36,19,1,1,10000),(36,19,2,1,15000),(38,20,1,1,10000),(38,20,2,1,15000),(40,21,1,1,10000),(40,21,2,1,15000),(42,22,1,1,10000),(42,22,2,1,15000),(44,23,1,10,100000),(44,23,2,1,15000),(44,23,3,1,20000),(47,24,1,1,10000),(47,24,2,1,15000),(47,24,3,1,20000),(50,25,1,1,10000),(50,25,2,1,15000),(50,25,3,1,20000),(53,26,1,1,10000),(53,26,2,1,15000),(55,27,1,1,10000),(55,27,2,1,15000),(57,28,1,1,10000),(57,28,2,1,15000),(57,28,3,1,20000),(57,28,4,1,5000),(57,28,5,1,8000),(57,28,6,1,30000),(57,28,7,1,25000),(57,28,8,1,12000),(57,28,9,1,18000),(57,28,10,1,100000),(67,29,1,1,10000),(67,29,2,1,15000),(69,30,1,1,10000),(69,30,2,1,15000),(71,31,1,1,10000),(71,31,2,1,15000),(73,32,1,1,10000),(73,32,2,1,15000),(75,33,1,1,10000),(75,33,2,1,15000),(75,33,3,1,20000),(78,34,1,1,10000),(78,34,2,1,15000),(78,34,3,1,20000),(81,35,1,1,10000),(81,35,2,1,15000),(81,35,3,1,20000),(84,36,1,1,10000),(84,36,2,1,15000),(84,36,3,1,20000),(87,37,1,1,10000),(87,37,2,1,15000),(87,37,3,1,20000),(90,38,1,1,10000),(90,38,2,100000000,1500000000000),(90,38,3,1,20000),(93,39,1,1,10000),(93,39,2,100000000,1500000000000),(93,39,3,1,20000),(96,40,1,1,10000),(96,40,2,100000000,1500000000000),(96,40,3,1,20000),(99,41,1,1,10000),(99,41,2,100000000,1500000000000),(99,41,3,1,20000);
/*!40000 ALTER TABLE `chitiethd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giamgia`
--

DROP TABLE IF EXISTS `giamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giamgia` (
  `MaGiamGia` int NOT NULL,
  `GiaTri` float DEFAULT NULL,
  `ThoiGianBatDau` date DEFAULT NULL,
  `ThoiGianKetThuc` date DEFAULT NULL,
  PRIMARY KEY (`MaGiamGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giamgia`
--

LOCK TABLES `giamgia` WRITE;
/*!40000 ALTER TABLE `giamgia` DISABLE KEYS */;
INSERT INTO `giamgia` VALUES (0,0.1,'2022-03-01','2022-03-31'),(1,0.1,'2022-01-01','2022-01-31'),(2,0.2,'2022-02-01','2022-02-28'),(3,0.6,'2022-03-01','2022-03-31');
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHoaDon` int NOT NULL,
  `MaChiNhanh` int DEFAULT NULL,
  `MaNhanVien` int DEFAULT NULL,
  `MaKH` int DEFAULT NULL,
  `Tong` float DEFAULT NULL,
  `TienKHDua` float DEFAULT NULL,
  `NgayTao` date DEFAULT NULL,
  PRIMARY KEY (`MaHoaDon`),
  KEY `hoadon_ibfk_1` (`MaKH`),
  KEY `hoadon_ibfk_2` (`MaChiNhanh`),
  KEY `hoadon_ibfk_3` (`MaNhanVien`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE CASCADE,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaChiNhanh`) REFERENCES `chinhanh` (`MaChiNhanh`) ON DELETE CASCADE,
  CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1,1,100000,120000,'2022-01-01'),(2,2,2,2,50000,55000,'2022-01-02'),(3,3,3,3,80000,90000,'2022-01-03'),(4,1,2,2,120000,130000,'2022-01-04'),(5,2,3,1,60000,65000,'2022-01-05'),(6,2,2,1,1015000,2141240000,'2023-03-30'),(7,2,2,1,10000,142355,'2023-03-30'),(8,2,2,1,10500,2542540,'2023-03-30'),(9,2,2,1,25000,434362,'2023-03-30'),(10,2,2,1,25000,141414,'2023-03-30'),(11,2,2,1,0,413451,'2023-03-30'),(12,2,2,1,25000,4325240,'2023-03-30'),(13,2,2,1,30100000,55555600000,'2023-03-30'),(14,2,2,1,15010000,15100000,'2023-03-30'),(15,2,2,1,115000,115000,'2023-03-31'),(16,2,2,4,160000,1600000,'2023-03-31'),(17,2,2,1,160000,1600000,'2023-03-31'),(18,2,2,1,45000,45000,'2023-03-31'),(19,2,2,2,25000,2,'2023-03-31'),(20,2,2,1,25000,25000,'2023-03-31'),(21,2,2,3,25000,25000,'2023-03-31'),(22,2,2,3,25000,25000,'2023-03-31'),(23,2,2,1,135000,135000,'2023-04-01'),(24,2,2,4,45000,45000,'2023-04-01'),(25,2,2,1,45000,100000,'2023-04-01'),(26,2,2,1,25000,25000,'2023-04-01'),(27,2,2,1,25000,321,'2023-04-01'),(28,2,2,1,243000,321,'2023-04-01'),(29,2,2,1,25000,23,'2023-04-01'),(30,2,2,1,25000,23,'2023-04-01'),(31,2,2,1,25000,321,'2023-04-01'),(32,2,2,1,25000,321,'2023-04-01'),(33,2,2,1,45000,45000,'2023-04-01'),(34,2,2,1,45000,45000,'2023-04-01'),(35,2,2,1,45000,45000,'2023-04-01'),(36,2,2,1,45000,412,'2023-04-01'),(37,2,2,1,45000,412,'2023-04-01'),(38,2,2,1,1500000000000,8.65866e18,'2023-04-01'),(39,2,2,1,1500000000000,86586600000000,'2023-04-01'),(40,2,2,1,1500000000000,865866000000000,'2023-04-01'),(41,2,2,1,1500000000000,8.65866e15,'2023-04-01');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` int NOT NULL,
  `HoKH` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `TenKH` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `SoDT` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Nguyễn Minh','Hiếu','2002-06-22','0359505026'),(2,'Nguyễn Minh','Tuấn','2002-06-22','0359505026'),(3,'Nguyễn Minh','Hải','2002-06-22','0359505026'),(4,'Trần Văn','Liên','2023-03-27','0491041928'),(5,'Nguyễn Minh','Hiếu','2002-06-22','445134651'),(6,'Trương Hoàng','Vinh','2023-04-01','64326'),(7,'Truong H','Vinh','1980-01-01','64326'),(9,'Dương Hữu ','Thành','1990-01-01','012445465'),(10,'hiếu','nguyễn minh','2023-03-27','0574573');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` int NOT NULL,
  `HoNV` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `TenNV` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `MaChiNhanh` int DEFAULT NULL,
  `TaiKhoan` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `LoaiNV` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `TaiKhoan_UNIQUE` (`TaiKhoan`),
  KEY `MaChiNhanh` (`MaChiNhanh`),
  CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaChiNhanh`) REFERENCES `chinhanh` (`MaChiNhanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Nguyễn Văn','A',1,'a','1',1),(2,'Trần Thị','B',2,'a1','1',0),(3,'Lê Văn',' C',2,'admin2','123',0),(4,'Phạm Thị','D',3,'admin3','123',1),(5,'Đặng Văn','E',3,'admin4','123',0);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSanPham` int NOT NULL,
  `TenSanPham` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Gia` float DEFAULT NULL,
  `DonVi` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `XuatXu` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `MaGiamGia` int DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`),
  KEY `sanpham_ibfk_1` (`MaGiamGia`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaGiamGia`) REFERENCES `giamgia` (`MaGiamGia`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Sản phẩm A',10000,'Chiếc','VN',1),(2,'Sản phẩm B',15000,'Chiếc','CN',2),(3,'Sản phẩm C',20000,'Chiếc','JP',2),(4,'Sản phẩm D',5000,'Cái','USA',1),(5,'Sản phẩm E',8000,'Cái','UK',3),(6,'Sản phẩm F',30000,'Bộ','UK',1),(7,'Sản phẩm G',25000,'Bộ','VN',3),(8,'Sản phẩm H',12000,'Chiếc','VN',2),(9,'Sản phẩm I',18000,'Chiếc','VN',1),(10,'Sản phẩm J',100000,'Bộ','VN',3);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-04 17:08:12
