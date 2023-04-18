/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.HoaDon;
import java.sql.Connection;
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
public class HoaDonServiceTest {

    private static Connection conn;
    private static ChiNhanhService cnsv = new ChiNhanhService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChiNhanhServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetChiNhanh_byDiaChi() throws Exception {
        List<ChiNhanh> ds = new ArrayList<>();
        ds = cnsv.getChiNhanh("TP HCM");
        for (ChiNhanh cn : ds) {
            Assertions.assertEquals("Hồ Chí Minh", cn.getDiaChi());
        }
    }

    @Test
    public void testGetChiNhanh_byMaDiaChi_1() throws Exception {
        List<ChiNhanh> ds = new ArrayList<>();
        ds = cnsv.getChiNhanh(1);
        for (ChiNhanh cn : ds) {
            Assertions.assertEquals("Hà Nội", cn.getDiaChi());
        }
    }

    @Test
    public void testGetChiNhanh_byMaDiaChi_am1() throws Exception {
        List<ChiNhanh> ds = new ArrayList<>();
        ds = cnsv.getChiNhanh(-1);
        Assertions.assertEquals(0, ds.size());
    }

    @Test
    public void testGetChiNhanh() throws Exception {
        List<ChiNhanh> ds = new ArrayList<>();
        ds = cnsv.getChiNhanh();
        Assertions.assertEquals(8, ds.size());
    }

    @Test
    public void testaddChiNhanh() throws Exception {
//        cnsv.deleteChiNhanh(10);
        List<ChiNhanh> ds = new ArrayList<>();
        ChiNhanh cn = new ChiNhanh("test", 10);
        boolean b = cnsv.addChiNhanh(cn);
        Assertions.assertEquals(b, true);
        cnsv.deleteChiNhanh(10);
    }

    @Test
    public void testdeleteChiNhanh() throws Exception {
//        cnsv.deleteChiNhanh(10);
        List<ChiNhanh> ds = new ArrayList<>();
        ChiNhanh cn = new ChiNhanh("test", 10);
        cnsv.addChiNhanh(cn);
        boolean b = cnsv.deleteChiNhanh(10);
        Assertions.assertEquals(b, true);
    }

    @Test
    public void testupdateGiamGia() throws Exception {
        List<ChiNhanh> ds = new ArrayList<>();
        ChiNhanh cn = new ChiNhanh("test", 1);
        boolean b = cnsv.updateChiNhanh(cn);
        Assertions.assertTrue(b);
        ds = cnsv.getChiNhanh(1);
        for (ChiNhanh x : ds) {
            Assertions.assertEquals(x.getMaChiNhanh(), 1);
            Assertions.assertEquals(x.getDiaChi(), "test");
        }
        if (b == true) {
            ChiNhanh cnOLD = new ChiNhanh("Hà Nội", 1);
            boolean bNEW = cnsv.updateChiNhanh(cnOLD);
            Assertions.assertTrue(bNEW);

        }
    }

}
