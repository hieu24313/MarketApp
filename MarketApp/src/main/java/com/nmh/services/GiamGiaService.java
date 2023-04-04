/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.GiamGia;
import com.nmh.pojo.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class GiamGiaService {
    public List<GiamGia> getGiamGia() throws SQLException{

        List<GiamGia> giamGia = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM giamgia";

            PreparedStatement stm = conn.prepareCall(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaGiamGia");
                Double GiaTri = rs.getDouble("GiaTri");
                Date tgBatDau = rs.getDate("ThoiGianBatDau");
                Date tgKetThuc = rs.getDate("ThoiGianKetThuc");
                giamGia.add(new GiamGia(id,GiaTri, tgBatDau, tgKetThuc));
            }
            return giamGia;
        }
}
    public List<GiamGia> getGiamGia(int idkm) throws SQLException{

        List<GiamGia> giamGia = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM giamgia where MaGiamGia LIKE ?";

            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idkm);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MaGiamGia");
                Double GiaTri = rs.getDouble("GiaTri");
                Date tgBatDau = rs.getDate("ThoiGianBatDau");
                Date tgKetThuc = rs.getDate("ThoiGianKetThuc");
                giamGia.add(new GiamGia(id,GiaTri, tgBatDau, tgKetThuc));
            }
            return giamGia;
        }
}
    
    public boolean deleteGiamGia(int idKhuyenMai) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM giamgia WHERE MaGiamGia = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idKhuyenMai);
            
            return stm.executeUpdate() > 0;
    }
    }
    
    public boolean addGiamGia(GiamGia k) throws SQLException {

        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO giamgia(MaGiamGia, GiaTri, ThoiGianBatDau, ThoiGianKetThuc) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, k.getIdGiamGia());
            stm.setDouble(2, k.getGiaTri());
            stm.setDate(3, k.getTgBatDau());
            stm.setDate(4, k.getTgKetThuc());
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
    
    public boolean updateGiamGia(GiamGia g) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE giamgia set GiaTri=?, ThoiGianBatDau=?, ThoiGianKetThuc=? WHERE MaGiamGia=? ";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setDouble(1, g.getGiaTri());
            stm.setDate(2, g.getTgBatDau());
            stm.setDate(3, g.getTgKetThuc());
            stm.setInt(4, g.getIdGiamGia());
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
