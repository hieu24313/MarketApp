/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.SanPham;
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
public class SanPhamServiceTest {

    private static Connection conn;
    private static SanPhamService spsv = new SanPhamService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetSanPham_ByTenSP() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        ds = spsv.getSanPham("Sản phẩm A");
        for (SanPham x : ds) {
            Assertions.assertEquals("Sản phẩm A", x.getTenSP());
        }

    }

    @Test
    public void testGetSanPham_ByTenSP_SanPham() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        ds = spsv.getSanPham("Sản phẩm");
        for (SanPham x : ds) {
            Assertions.assertTrue(x.getTenSP().contains("Sản phẩm"));
        }
    }

    @Test
    public void testGetSanPham_ByTenSP_Mot() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        ds = spsv.getSanPham("1");
        Assertions.assertEquals(0, ds.size());
    }

    @Test
    public void testGetSanPham_ByTenSP_KTDB() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        ds = spsv.getSanPham("@!");
        Assertions.assertEquals(0, ds.size());
    }

    @Test
    public void testGetSanPham() throws Exception {
        List<SanPham> ds = new ArrayList<>();

        ds = spsv.getSanPham();
        Assertions.assertEquals(ds.size(), 13);
    }
    @Test
    public void testGetSanPhamf() throws Exception {
        List<SanPham> ds = new ArrayList<>();

        ds = spsv.getSanPham();
        Assertions.assertNotEquals(ds.size(), 100);
    }
    @Test
    public void testGetSanPham_ByIDCHINHANH() throws Exception {
        List<SanPham> ds = new ArrayList<>();

        ds = spsv.getSanPham("Cà Pháo", 2);
        for (SanPham s : ds) {
            Assertions.assertEquals(s.getIdSanPham(), 11);
            Assertions.assertEquals(s.getTenSP(), "Cà Pháo");
            Assertions.assertEquals(s.getGiaSP(), 1200);
            Assertions.assertEquals(s.getDonVi(), "Củ");
            Assertions.assertEquals(s.getXuatXu(), "JP");
            Assertions.assertEquals(s.getIdChiNhanh(), 2);
            Assertions.assertEquals(s.getIdGiamGia(), 1);
        }
    }
    @Test
    public void testGetSanPhamF_ByIDCHINHANH() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        ds = spsv.getSanPham("Thái", 2);
        Assertions.assertEquals(0,ds.size());
    }

    @Test
    public void testaddSanPham() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        SanPham sp = new SanPham(100, "Duy", 1000000, "người", "An Giang", 1, 1);
        boolean b = spsv.addSanPham(sp);
        Assertions.assertEquals(b, true);
        ds = spsv.getSanPham(100);
        for (SanPham s : ds) {
            Assertions.assertEquals(s.getIdSanPham(), 100);
            Assertions.assertEquals(s.getTenSP(), "Duy");
            Assertions.assertEquals(s.getGiaSP(), 1000000);
            Assertions.assertEquals(s.getDonVi(), "người");
            Assertions.assertEquals(s.getXuatXu(), "An Giang");
            Assertions.assertEquals(s.getIdChiNhanh(), 1);
            Assertions.assertEquals(s.getIdGiamGia(), 0);
        }
        spsv.deleteSanPham(100);
    }
    @Test
    public void testaddSanPhamF() throws Exception {
        SanPham sp = new SanPham(1, "Duy", 1000000, "người", "An Giang", 1, 1);
        boolean b;
        try{
            b = spsv.addSanPham(sp);
        }catch (SQLException ex){
            b= false;
        }
        Assertions.assertEquals(b, false);

    }

    @Test
    public void testdeleteSanPham() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        SanPham sp = new SanPham(101, "Duy", 1000000, "người", "An Giang", 1, 1);
        spsv.addSanPham(sp);
        boolean b = spsv.deleteSanPham(101);
        Assertions.assertEquals(b, true);
    }
    @Test
    public void testdeleteSanPhamF() throws Exception {
        boolean b;
        try{
            b = spsv.deleteSanPham(200);
        }catch (SQLException ex){
            b= false;
        }
        Assertions.assertEquals(b, false);
    }

    @Test
    public void testupdateSanPham() throws Exception {
        List<SanPham> ds = new ArrayList<>();
        SanPham sp = new SanPham(14, "Duy", 1000000, "người", "An Giang", 1, 1);
        boolean b = spsv.updateSanPham(sp);
        Assertions.assertTrue(b);
        ds = spsv.getSanPham(14);
        for (SanPham s : ds) {
            Assertions.assertEquals(s.getIdSanPham(), 14);
            Assertions.assertEquals(s.getTenSP(), "Duy");
            Assertions.assertEquals(s.getGiaSP(), 1000000);
            Assertions.assertEquals(s.getDonVi(), "người");
            Assertions.assertEquals(s.getXuatXu(), "An Giang");
            Assertions.assertEquals(s.getIdChiNhanh(), 1);
            Assertions.assertEquals(s.getIdGiamGia(), 0);
        }
        if (b == true) {
            SanPham spOLD = new SanPham(14, "Hiếu", 100000000, "Người", "Việt Nam", 2, 10);
            boolean bNEW = spsv.updateSanPham(spOLD);
            Assertions.assertTrue(bNEW);

        }
    }

}
