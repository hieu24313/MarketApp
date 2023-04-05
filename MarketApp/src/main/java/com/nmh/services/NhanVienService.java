/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.NhanVien;
import java.sql.Connection;
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
public class NhanVienService {

    public List<NhanVien> getNhanVien() throws SQLException {
        List<NhanVien> employ = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
            while (rs.next()) {
                int id = rs.getInt("MaNhanVien");
                String hoNV = rs.getString("HoNV");
                String tenNV = rs.getString("TenNV");
                int idChiNhanh = rs.getInt("MaChiNhanh");
                String TaiKhoan = rs.getString("TaiKhoan");
                String MatKhau = rs.getString("MatKhau");
                boolean LoaiNV = rs.getBoolean("LoaiNV");
                employ.add(new NhanVien(id, hoNV, tenNV, idChiNhanh, TaiKhoan, MatKhau, LoaiNV));
            }
        }
        return employ;
    }

    public List<NhanVien> getNhanVien(String ten) throws SQLException {
        List<NhanVien> employ = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
//                Statement stm = conn.createStatement();
            String sql = "SELECT * FROM nhanvien where TenNV LIKE CONCAT('%', ?, '%')";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaNhanVien");
                String hoNV = rs.getString("HoNV");
                String tenNV = rs.getString("TenNV");
                int idChiNhanh = rs.getInt("MaChiNhanh");
                String TaiKhoan = rs.getString("TaiKhoan");
                String MatKhau = rs.getString("MatKhau");
                boolean LoaiNV = rs.getBoolean("LoaiNV");
                employ.add(new NhanVien(id, hoNV, tenNV, idChiNhanh, TaiKhoan, MatKhau, LoaiNV));
            }
        }
        return employ;
    }

    public boolean deleteNhanVien(int idnv) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM nhanvien WHERE MaNhanVien=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idnv);

            return stm.executeUpdate() > 0;
        }

    }

    public boolean addNhanVien(NhanVien v) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO nhanvien(MaNhanVien, HoNV, TenNV, MaChiNhanh, TaiKhoan, MatKhau, LoaiNV) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, v.getMaNhanVien());
            stm.setString(2, v.getHoNV());
            stm.setString(3, v.getTenNV());
            stm.setInt(4, v.getIdChiNhanh());
            stm.setString(5, v.getTaiKhoan());
            stm.setString(6, v.getMatKhau());
            stm.setBoolean(7, v.isLoaiNV());
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

    public boolean updateNhanVien(NhanVien v) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE nhanvien set HoNV=?, TenNV=?, MaChiNhanh=?, TaiKhoan=?, MatKhau=?, LoaiNV=? WHERE MaNhanVien = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(7, v.getMaNhanVien());
            stm.setString(1, v.getHoNV());
            stm.setString(2, v.getTenNV());
            stm.setInt(3, v.getIdChiNhanh());
            stm.setString(4, v.getTaiKhoan());
            stm.setString(5, v.getMatKhau());
            stm.setBoolean(6, v.isLoaiNV());
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
