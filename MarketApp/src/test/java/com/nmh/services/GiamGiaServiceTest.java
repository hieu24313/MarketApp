/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.GiamGia;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class GiamGiaServiceTest {

    private static Connection conn;
    private static GiamGiaService ggsv = new GiamGiaService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(GiamGiaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GiamGiaServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetGiamGia() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        ds = ggsv.getGiamGia();
        Assertions.assertEquals(12, ds.size());
    }

    @Test
    public void testGetGiamGiaF() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        ds = ggsv.getGiamGia();
        Assertions.assertNotEquals(100, ds.size());
    }

    @Test
    public void testGetGiamGia_byMaKhuyenMai() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        ds = ggsv.getGiamGia(3);
        for (GiamGia s : ds) {
            Assertions.assertEquals(s.getIdGiamGia(), 3);
            Assertions.assertEquals(s.getGiaTri(), 0.6);
            Assertions.assertEquals(s.getTgBatDau(), Date.valueOf("2022-03-01"));
            Assertions.assertEquals(s.getTgKetThuc(), Date.valueOf("2022-03-31"));
        }
    }

    @Test
    public void testGetGiamGia_byMaKhuyenMai_am() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        ds = ggsv.getGiamGia(-1);
        Assertions.assertNotEquals(1, ds.size());
    }

    @Test
    public void testaddGiamGia() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        GiamGia gg = new GiamGia(100, 0.1, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31"));
        boolean b = ggsv.addGiamGia(gg);
        Assertions.assertEquals(b, true);
        ds = ggsv.getGiamGia(100);
        for (GiamGia s : ds) {
            Assertions.assertEquals(s.getIdGiamGia(), 100);
            Assertions.assertEquals(s.getGiaTri(), 0.1);
            Assertions.assertEquals(s.getTgBatDau(), Date.valueOf("2022-03-01"));
            Assertions.assertEquals(s.getTgKetThuc(), Date.valueOf("2022-03-31"));
        }
        ggsv.deleteGiamGia(100);
    }

    @Test
    public void testdeleteGiamGia() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        GiamGia gg = new GiamGia(100, 0.1, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31"));
        ggsv.addGiamGia(gg);
        boolean b = ggsv.deleteGiamGia(100);
        Assertions.assertEquals(b, true);
    }

    @Test
    public void testupdateGiamGia() throws Exception {
        List<GiamGia> ds = new ArrayList<>();
        GiamGia gg = new GiamGia(10, 100, Date.valueOf("2022-03-01"), Date.valueOf("2022-03-31"));
        boolean b = ggsv.updateGiamGia(gg);
        Assertions.assertTrue(b);
        ds = ggsv.getGiamGia(1);
//        for(GiamGia s : ds){
//            Assertions.assertEquals(s.getIdGiamGia(),10);
//            Assertions.assertEquals(s.getGiaTri(),100);
//            Assertions.assertEquals(s.getTgBatDau(),Date.valueOf("2022-03-01"));
//            Assertions.assertEquals(s.getTgKetThuc(),Date.valueOf("2022-03-31"));
//        }
        if (b == true) {
            GiamGia ggOLD = new GiamGia(10, 1, Date.valueOf("2023-04-06"), Date.valueOf("2023-04-14"));
            boolean bNew = ggsv.updateGiamGia(ggOLD);
            Assertions.assertTrue(bNew);
        }
    }

}
