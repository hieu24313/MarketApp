/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.SQLException;
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
public class ChiTietHoaDonServiceTest {

    private static Connection conn;
    private static ChiTietHoaDonService cthdsv;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        cthdsv = new ChiTietHoaDonService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetCTHD() throws Exception {
        List<ChiTietHoaDon> ds = cthdsv.getChiTietHoaDon();
        Assertions.assertEquals(32, ds.size());
    }

    @Test
    public void testGetCTHDF() throws Exception {
        List<ChiTietHoaDon> ds = cthdsv.getChiTietHoaDon();
        Assertions.assertNotEquals(1, ds.size());
    }

}
