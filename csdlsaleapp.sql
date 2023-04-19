-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: salemarket
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
  `MaChiNhanh` int(11) NOT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaChiNhanh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chinhanh`
--

LOCK TABLES `chinhanh` WRITE;
/*!40000 ALTER TABLE `chinhanh` DISABLE KEYS */;
INSERT INTO `chinhanh` VALUES (1,'Hà Nội'),(2,'Hồ Chí Minh'),(3,'Đà Nẵng'),(4,'Đồng Nai'),(5,'Hải Phòng'),(6,'Bình Định'),(7,'Gia Lai'),(8,'Nha Trang');
/*!40000 ALTER TABLE `chinhanh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethd`
--

DROP TABLE IF EXISTS `chitiethd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethd` (
  `MaChiTietHD` int(11) NOT NULL,
  `MaHoaDon` int(11) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL,
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
INSERT INTO `chitiethd` VALUES (1,1,1,2,18000),(2,1,2,1,15000),(3,2,3,3,45000),(4,2,4,2,10000),(5,3,5,4,24000),(6,3,6,1,27000),(7,4,7,3,75000),(8,4,8,2,24000),(9,5,9,2,36000),(10,5,10,1,90000),(11,10,2,1,15000),(12,10,1,100,1000000),(13,11,2,1,15000),(14,11,1,100,1000000),(15,11,1,1,10000),(16,11,2,12,180000),(17,11,3,1,20000),(18,11,4,1,5000),(19,11,5,1,8000),(20,11,6,1,30000),(21,11,7,1,25000),(22,106,7,6,150000),(23,106,11,1,1200);
/*!40000 ALTER TABLE `chitiethd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giamgia`
--

DROP TABLE IF EXISTS `giamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giamgia` (
  `MaGiamGia` int(11) NOT NULL,
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
INSERT INTO `giamgia` VALUES (1,0.1,'2022-01-01','2022-01-31'),(2,0.2,'2022-02-01','2022-02-28'),(3,0.6,'2022-03-01','2022-03-31'),(4,0.1,'2023-03-27','2023-04-06'),(5,0.3,'2023-04-05','2023-04-05'),(6,0.3,'2023-04-05','2023-04-05'),(7,0.3,'2023-04-05','2023-04-05'),(8,0.9,'2023-04-05','2023-04-05'),(9,0.9,'2023-04-05','2023-04-05'),(10,1,'2023-04-06','2023-04-14'),(11,0.5,'2023-02-28','2023-03-02'),(12,0.1,'2023-04-02','2023-04-29');
/*!40000 ALTER TABLE `giamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `MaChiNhanh` int(11) DEFAULT NULL,
  `MaNhanVien` int(11) DEFAULT NULL,
  `MaKH` int(11) DEFAULT NULL,
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
INSERT INTO `hoadon` VALUES (1,1,1,1,100000,120000,'2022-01-01'),(2,2,2,2,50000,55000,'2022-01-02'),(3,3,3,3,80000,90000,'2022-01-03'),(4,1,2,2,120000,130000,'2022-01-04'),(5,2,3,1,60000,65000,'2022-01-05'),(6,2,2,1,1015000,2141240000,'2023-03-30'),(7,2,2,1,10000,142355,'2023-03-30'),(8,2,2,1,10500,2542540,'2023-03-30'),(9,2,2,1,25000,434362,'2023-03-30'),(10,2,2,1,1015000,1015000,'2023-04-05'),(11,2,2,1,1293000,12900000,'2023-04-05'),(100,1,1,1,200,50,'2002-11-16'),(101,1,1,1,200,50,'2002-11-16'),(102,1,1,1,200,50,'2002-11-16'),(103,1,1,1,200,50,'2002-11-16'),(105,1,1,1,200,50,'2002-11-16'),(106,2,2,1,151200,500000,'2023-04-19');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khachhang` (
  `MaKH` int(11) NOT NULL,
  `HoKH` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `TenKH` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `SoDT` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Nguyễn Minh','Hiếu','2002-06-22','0359505026'),(2,'Nguyễn Minh','Tuấn','2002-06-22','0359505026'),(3,'Nguyễn Minh','Hải','2002-06-22','0359505026'),(4,'Trần Văn','Liên','2023-03-27','0491041928'),(5,'Nguyễn Minh','Hiếu','2002-06-22','445134651'),(6,'Trương Hoàng','Vinh','2023-04-01','64326'),(7,'Truong Hoàng','Vinh','1980-01-01','64326'),(9,'Dương Hữu ','Thành','1990-01-01','012445465'),(10,'Hiếu','Nguyễn Minh','2023-03-27','0574573');
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `HoNV` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `TenNV` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `MaChiNhanh` int(11) DEFAULT NULL,
  `TaiKhoan` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
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
INSERT INTO `nhanvien` VALUES (1,'Nguyễn Văn','An',1,'a','1',0),(2,'Trần Thị','Ngọc',2,'a1','1',1),(3,'Lê Văn',' Công',2,'Conglv','123',0),(4,'Hiếu','Nguyễn Minh',3,'hieunm','123',0),(5,'Hồ Quang','Anh',3,'anhqh','123',1),(6,'Đỗ','Đạt',1,'datd','123',0),(7,'Hồ Chí','Quang',4,'quanghc','123',0),(8,'Nguyễn Minh ','Cường',4,'cuongnm','123',0),(9,'Lê Văn','Đạt',4,'datlv','123',0),(10,'Đỗ Quỳnh','Anh',4,'anhdq','123',0);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `MaSanPham` int(11) NOT NULL,
  `TenSanPham` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Gia` float DEFAULT NULL,
  `DonVi` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `XuatXu` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `MaChiNhanh` int(11) DEFAULT NULL,
  `MaGiamGia` int(11) DEFAULT NULL,
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
INSERT INTO `sanpham` VALUES (1,'Sữa Chua',10000,'Hộp','VN',1,1),(2,'Táo',15000,'Quả','CN',1,2),(3,'Mì Tôm kokomi',20000,'Gói','VN',1,2),(4,'Phở Gói VN',5000,'Gói','VN',1,2),(5,'Phở Trung Quốc',8000,'Gói','CN',2,3),(6,'Mì Tôm Hảo Hảo',4500,'Gói','VN',1,1),(7,'Đường',20000,'KG','VN',1,2),(8,'Mì Chính',12000,'Gói','VN',1,2),(9,'Ca Cao',18000,'Gói','VN',2,1),(10,'Kẹo Bạc Hà',25550,'Gói','VN',1,3),(11,'Cà Pháo',1200,'Củ','JP',2,1),(12,'Cà Rốt',12900,'Kg','CN',1,NULL),(13,'Cà Chua',32141,'Quả','VN',1,NULL);
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

-- Dump completed on 2023-04-19 16:11:40
