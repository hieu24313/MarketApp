/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.KhachHang;
import com.nmh.pojo.NhanVien;
import com.nmh.pojo.SanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class SanPhamService {

    public List<SanPham> getSanPham() throws SQLException {
        List<SanPham> proc = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sanpham";

            PreparedStatement stm = conn.prepareCall(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int MaSP = rs.getInt("MaSanPham");
                String TenSP = rs.getString("TenSanPham");
                Double GiaSP = rs.getDouble("Gia");
                String DonVi = rs.getString("DonVi");
                String XuatXU = rs.getString("XuatXu");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                int idGiamGia = rs.getInt("MaGiamGia");
                proc.add(new SanPham(MaSP, TenSP, GiaSP, DonVi, XuatXU, MaChiNhanh, idGiamGia));
            }
            return proc;
        }

    }

//    public List<SanPham> getSanPham(String kw) throws SQLException {
//
//        List<SanPham> proc = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConn()) {
//
//            String sql = "SELECT * FROM sanpham";
//            if (kw != null && !kw.isEmpty()) {
//                sql += " WHERE TenSanPham like concat('%', ?, '%')";
//            }
//            PreparedStatement stm = conn.prepareCall(sql);
//            if (kw != null && !kw.isEmpty()) {
//                stm.setString(1, kw);
//            }
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                int MaSP = rs.getInt("MaSanPham");
//                String TenSP = rs.getString("TenSanPham");
//                int Gia = rs.getInt("Gia");
//                String DonVi = rs.getString("DonVi");
//                String XuatXu = rs.getString("XuatXu");
//                int MaGiamGia = rs.getInt("MaGiamGia");
//                proc.add(new SanPham(MaSP, TenSP, Gia, DonVi, XuatXu, MaGiamGia));
//
//            }
//
//        }
//        return proc;
//    }

    public boolean deleteSanPham(int idsp) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM sanpham WHERE MaSanPham=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idsp);

            return stm.executeUpdate() > 0;
        }
    }

    public List<SanPham> getSanPham(String ten) throws SQLException {
        List<SanPham> proc = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sanpham where TenSanPham LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int MaSP = rs.getInt("MaSanPham");
                String TenSP = rs.getString("TenSanPham");
                Double GiaSP = rs.getDouble("Gia");
                String DonVi = rs.getString("DonVi");
                String XuatXU = rs.getString("XuatXu");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                int idGiamGia = rs.getInt("MaGiamGia");
                proc.add(new SanPham(MaSP, TenSP, GiaSP, DonVi, XuatXU, MaChiNhanh, idGiamGia));
            }

        }
        return proc;
    }
    
    public boolean addSanPham(SanPham p) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO sanpham(MaSanPham, TenSanPham, Gia, DonVi, XuatXu, MaChiNhanh, MaGiamGia) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, p.getIdSanPham());
            stm.setString(2, p.getTenSP());
            stm.setDouble(3, p.getGiaSP());
            stm.setString(4, p.getDonVi());
            stm.setString(5, p.getXuatXu());
            stm.setInt(6, p.getIdChiNhanh());
            stm.setInt(7, p.getIdGiamGia());
            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
    
    public boolean updateSanPham(SanPham a) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            String sql = "UPDATE sanpham set TenSanPham=?, Gia=?, DonVi=?, XuatXu=?, MaChiNhanh=?, MaGiamGia=? WHERE MaSanPham=? ";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, a.getTenSP());
            stm.setDouble(2, a.getGiaSP());
            stm.setString(3, a.getDonVi());
            stm.setString(4, a.getXuatXu());
            stm.setInt(5, a.getIdChiNhanh());
            stm.setInt(6, a.getIdGiamGia());
            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
}
