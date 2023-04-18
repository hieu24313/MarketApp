/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author HP
 */
public class KhachHangServiceTest {

    private static Connection conn;
    private static KhachHangService khsv = new KhachHangService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetKhachHang() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        ds = khsv.getKhachHang();
        Assertions.assertEquals(9, ds.size());
    }

    @Test
    public void testGetKhachHangF() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        ds = khsv.getKhachHang();
        Assertions.assertNotEquals(10, ds.size());
    }

    @Test
    public void testGetKhachHang_ByTenandSDT() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        ds = khsv.getKhachHang("Hiếu", "0359505026");
        for (KhachHang kh : ds) {
            Assertions.assertEquals("Hiếu", kh.getTenKH());
            Assertions.assertEquals("0359505026", kh.getSoDT());
        }
    }

    @Test
    public void testaddKhachHang() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        KhachHang kh = new KhachHang(100, "Thanh", "Lam", Date.valueOf("2002-11-16"), "123");
        boolean b = khsv.addKhachHang(kh);
        Assertions.assertEquals(b, true);
        khsv.deleteKhachHang(100);
    }
//    @Test
//    public void testaddKhachHangF() throws Exception {
//        List<KhachHang> ds = new ArrayList<>();
//        KhachHang kh = new KhachHang(10,"Thanh","Lam", Date.valueOf("2002-11-16"),"123");
//        boolean b = khsv.addKhachHang(kh);
//        Assertions.assertEquals(b, false);
//        khsv.deleteKhachHang(10);
//    }

    @Test
    public void testdeleteKhachHang() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        KhachHang kh = new KhachHang(100, "Thanh", "Lam", Date.valueOf("2002-11-16"), "123");
        khsv.addKhachHang(kh);
        boolean b = khsv.deleteKhachHang(100);
        Assertions.assertEquals(b, true);

    }

    @Test
    public void testupdateKhachHang() throws Exception {
        List<KhachHang> ds = new ArrayList<>();
        KhachHang kh = new KhachHang(1, "Thanh", "Lam", Date.valueOf("2002-11-16"), "123");
        boolean b = khsv.updateKhachHang(kh);
        Assertions.assertTrue(b);
        ds = khsv.getKhachHang("Lam", "123");
        for (KhachHang x : ds) {
            Assertions.assertEquals(x.getIdKH(), 1);
            Assertions.assertEquals(x.getTenKH(), "Lam");
            Assertions.assertEquals(x.getHoKH(), "Thanh");
            Assertions.assertEquals(x.getSoDT(), "123");
            Assertions.assertEquals(x.getNgaySinh(), Date.valueOf("2002-11-16"));
        }
        if (b == true) {
            KhachHang khOLD = new KhachHang(1, "Nguyễn Minh", "Hiếu", Date.valueOf("2002-06-22"), "0359505026");
            boolean bNEW = khsv.updateKhachHang(khOLD);
            Assertions.assertTrue(bNEW);

        }
    }

}
