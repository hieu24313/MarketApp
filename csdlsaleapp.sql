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
INSERT INTO `chinhanh` VALUES (1,'HÃ  Ná»™i'),(2,'Há»“ ChÃ­ Minh'),(3,'ÄÃ  Náºµng'),(4,'Äá»“ng Nai'),(5,'Háº£i PhÃ²ng'),(6,'BÃ¬nh Äá»‹nh'),(7,'Gia Lai'),(8,'Nha Trang');
/*!40000 ALTER TABLE `chinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethd`
--

DROP TABLE IF EXISTS `chitiethd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethd` (
  `MaChiTietHD` int NOT NULL,
  `MaHoaDon` int DEFAULT NULL,
  `MaSanPham` int DEFAULT NULL,
  `SoLuong` float DEFAULT NULL,
  `ThanhTien` float DEFAULT NULL,
  PRIMARY KEY (`MaChiTietHD`),
  KEY `chitiethd_ibfk_2` (`MaSanPham`),
  KEY `chitiethd_ibfk_1` (`MaHoaDon`),
  CONSTRAINT `chitiethd_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`) ON DELETE SET NULL,
  CONSTRAINT `chitiethd_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethd`
--

LOCK TABLES `chitiethd` WRITE;
/*!40000 ALTER TABLE `chitiethd` DISABLE KEYS */;
INSERT INTO `chitiethd` VALUES (1,1,1,2,18000),(2,1,2,1,15000),(3,2,3,3,45000),(4,2,4,2,10000),(5,3,5,4,24000),(6,3,6,1,27000),(7,4,7,3,75000),(8,4,8,2,24000),(9,5,9,2,36000),(10,5,10,1,90000),(11,10,2,1,15000),(12,10,1,100,1000000),(13,11,2,1,15000),(14,11,1,100,1000000),(15,11,1,1,10000),(16,11,2,12,180000),(17,11,3,1,20000),(18,11,4,1,5000),(19,11,5,1,8000),(20,11,6,1,30000),(21,11,7,1,25000);
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
INSERT INTO `giamgia` VALUES (0,0,'2020-03-01','2030-03-31'),(1,0.1,'2022-01-01','2022-01-31'),(2,0.2,'2022-02-01','2022-02-28'),(3,0.6,'2022-03-01','2022-03-31'),(4,0.1,'2023-03-27','2023-04-06'),(5,0.3,'2023-04-05','2023-04-05'),(6,0.3,'2023-04-05','2023-04-05'),(7,0.3,'2023-04-05','2023-04-05'),(8,0.9,'2023-04-05','2023-04-05'),(9,0.9,'2023-04-05','2023-04-05'),(10,1,'2023-04-06','2023-04-14');
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
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON DELETE SET NULL,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaChiNhanh`) REFERENCES `chinhanh` (`MaChiNhanh`) ON DELETE SET NULL,
  CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (1,1,1,1,100000,120000,'2022-01-01'),(2,2,2,2,50000,55000,'2022-01-02'),(3,3,3,3,80000,90000,'2022-01-03'),(4,1,2,2,120000,130000,'2022-01-04'),(5,2,3,1,60000,65000,'2022-01-05'),(6,2,2,1,1015000,2141240000,'2023-03-30'),(7,2,2,1,10000,142355,'2023-03-30'),(8,2,2,1,10500,2542540,'2023-03-30'),(9,2,2,1,25000,434362,'2023-03-30'),(10,2,2,1,1015000,1015000,'2023-04-05'),(11,2,2,1,1293000,12900000,'2023-04-05');
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
INSERT INTO `khachhang` VALUES (1,'Nguyá»…n Minh','Hiáº¿u','2002-06-22','0359505026'),(2,'Nguyá»…n Minh','Tuáº¥n','2002-06-22','0359505026'),(3,'Nguyá»…n Minh','Háº£i','2002-06-22','0359505026'),(4,'Tráº§n VÄƒn','LiÃªn','2023-03-27','0491041928'),(5,'Nguyá»…n Minh','Hiáº¿u','2002-06-22','445134651'),(6,'TrÆ°Æ¡ng HoÃ ng','Vinh','2023-04-01','64326'),(7,'Truong H','Vinh','1980-01-01','64326'),(9,'DÆ°Æ¡ng Há»¯u ','ThÃ nh','1990-01-01','012445465'),(10,'hiáº¿u','nguyá»…n minh','2023-03-27','0574573');
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
  `TaiKhoan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `LoaiNV` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`MaNhanVien`),
  UNIQUE KEY `TaiKhoan_UNIQUE` (`TaiKhoan`),
  KEY `MaChiNhanh` (`MaChiNhanh`),
  CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaChiNhanh`) REFERENCES `chinhanh` (`MaChiNhanh`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Nguyá»…n VÄƒn','A',1,'a','1',1),(2,'Tráº§n Thá»‹','B',2,'a1','1',0),(3,'LÃª VÄƒn',' C',2,'admin2','123',0),(4,'Hiáº¿u','nguyen minh',3,'ad','123',0);
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
  `MaChiNhanh` int DEFAULT NULL,
  `MaGiamGia` int DEFAULT NULL,
  PRIMARY KEY (`MaSanPham`),
  KEY `sanpham_ibfk_1` (`MaGiamGia`),
  KEY `sanpham_ibfk_2` (`MaChiNhanh`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaGiamGia`) REFERENCES `giamgia` (`MaGiamGia`) ON DELETE SET NULL,
  CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`MaChiNhanh`) REFERENCES `chinhanh` (`MaChiNhanh`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Sáº£n pháº©m A',10000,'Chiáº¿c','VN',1,1),(2,'Sáº£n pháº©m B',15000,'Chiáº¿c','CN',2,2),(3,'Sáº£n pháº©m C',20000,'Chiáº¿c','JP',2,2),(4,'Sáº£n pháº©m D',5000,'CÃ¡i','USA',4,1),(5,'Sáº£n pháº©m E',8000,'CÃ¡i','UK',3,3),(6,'Sáº£n pháº©m F',30000,'Bá»™','UK',1,1),(7,'Sáº£n pháº©m G',25000,'Bá»™','VN',2,3),(8,'Sáº£n pháº©m H',12000,'Chiáº¿c','VN',1,2),(9,'Sáº£n pháº©m I',18000,'Chiáº¿c','VN',2,1),(10,'Sáº£n pháº©m J',100000,'Bá»™','VN',2,3),(11,'CÃ  PhÃ¡o',1200,'Cá»§','JP',2,1),(12,'CÃ  Rá»‘t',12900,'Kg','CN',1,0),(13,'CÃ  Chua',32141,'Quáº£','VN',1,0),(14,'Hiáº¿u',100000000000,'NgÆ°á»i','Viá»‡t Nam',2,10);
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

-- Dump completed on 2023-04-07 16:19:09
