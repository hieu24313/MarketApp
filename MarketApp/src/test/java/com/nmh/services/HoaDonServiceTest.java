/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.ChiTietHoaDon;
import com.nmh.pojo.HoaDon;
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
public class HoaDonServiceTest {

    private static Connection conn ;
    private static  HoaDonService hdsv = new HoaDonService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Test
    public void testGetHoaDon() throws Exception {
        List<HoaDon> ds = new ArrayList<>();
        ds = hdsv.getHoaDon();
        Assertions.assertEquals(17, ds.size());
    }
    @Test
    public void testGetHoaDonF() throws Exception {
        List<HoaDon> ds = new ArrayList<>();
        ds = hdsv.getHoaDon();
        Assertions.assertNotEquals(100, ds.size());
    }
//    @Test
//    public void testaddHoaDon() throws Exception {
//        List<HoaDon> ds = new ArrayList<>();
//        List<ChiTietHoaDon> dsct = new ArrayList<>();
//        HoaDon hd = new HoaDon(106,1,1,1,200,50, Date.valueOf("2002-11-16"));
//        boolean b = hdsv.addHoaDon(hd,dsct);
//        Assertions.assertEquals(b, true);
//    }

}
