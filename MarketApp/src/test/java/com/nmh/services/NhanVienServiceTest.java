/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.NhanVien;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author HP
 */
public class NhanVienServiceTest {

    private static Connection conn;
    private static NhanVienService nvsv = new NhanVienService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetNhanVien() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        ds = nvsv.getNhanVien();
        Assertions.assertEquals(4, ds.size());
    }

    @Test
    public void testGetNhanVienF() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        ds = nvsv.getNhanVien();
        Assertions.assertNotEquals(100, ds.size());
    }

    @Test
    public void testGetNhanVien_ByTen() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        ds = nvsv.getNhanVien("A");
        for (NhanVien nv : ds) {
            Assertions.assertEquals("A", nv.getTenNV());
        }
    }

    @Test
    public void testaddNhanVien() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        NhanVien nv = new NhanVien(100, "Trinh Bao", "Duy", 1, "admin100", "123", false);
        boolean b = nvsv.addNhanVien(nv);
        Assertions.assertEquals(b, true);
        nvsv.deleteNhanVien(100);
    }

    @Test
    public void testdeleteNhanVien() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        NhanVien nv = new NhanVien(100, "Trinh Bao", "Duy", 1, "admin100", "123", false);
        nvsv.addNhanVien(nv);
        boolean b = nvsv.deleteNhanVien(100);
        Assertions.assertEquals(b, true);

    }

    @Test
    public void testupdateNhanVien() throws Exception {
        List<NhanVien> ds = new ArrayList<>();
        NhanVien nv = new NhanVien(1, "Trinh", "Duy", 1, "admin100", "123", false);
        boolean b = nvsv.updateNhanVien(nv);
        Assertions.assertTrue(b);
        ds = nvsv.getNhanVien("Duy");
        for (NhanVien x : ds) {
            Assertions.assertEquals(x.getMaNhanVien(), 1);
            Assertions.assertEquals(x.getHoNV(), "Trinh");
            Assertions.assertEquals(x.getTenNV(), "Duy");
            Assertions.assertEquals(x.getIdChiNhanh(), 1);
            Assertions.assertEquals(x.getTaiKhoan(), "admin100");
            Assertions.assertEquals(x.getMatKhau(), "123");
            Assertions.assertFalse(x.isLoaiNV());
        }
        if (b == true) {
            NhanVien nvOLD = new NhanVien(1, "Nguyễn Văn", "A", 1, "a", "1", true);
            boolean bNEW = nvsv.updateNhanVien(nvOLD);
            Assertions.assertTrue(bNEW);

        }
    }

}
