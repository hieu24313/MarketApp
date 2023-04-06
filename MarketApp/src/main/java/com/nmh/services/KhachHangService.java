/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.KhachHang;
import com.nmh.pojo.SanPham;
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
public class KhachHangService {

    public List<KhachHang> getKhachHang() throws SQLException {
        List<KhachHang> cust = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM khachhang";

            PreparedStatement stm = conn.prepareCall(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int MaKH = rs.getInt("MaKH");
                String HoKH = rs.getString("HoKH");
                String TenKh = rs.getString("TenKH");
                Date ns = rs.getDate("NgaySinh");
                String SoDT = rs.getString("SoDT");
                cust.add(new KhachHang(MaKH, HoKH, TenKh, ns, SoDT));
            }
            return cust;
        }

    }

    public List<KhachHang> getKhachHang(String ten, String soDT) throws SQLException {
        List<KhachHang> cust = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {

            String sql = "SELECT * FROM khachhang WHERE 1=1";
            if (ten != null && !ten.isEmpty()) {
                sql += " AND TenKH LIKE CONCAT('%', ?, '%')";
            }
            if (soDT != null && !soDT.isEmpty()) {
                sql += " AND SoDT LIKE CONCAT('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            int i = 1;
            if (ten != null && !ten.isEmpty()) {
                stm.setString(i++, ten);
            }
            if (soDT != null && !soDT.isEmpty()) {
                stm.setString(i++, soDT);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int MaKH = rs.getInt("MaKH");
                String HoKH = rs.getString("HoKH");
                String TenKh = rs.getString("TenKH");
                Date ns = rs.getDate("NgaySinh");
                String SoDT = rs.getString("SoDT");
                cust.add(new KhachHang(MaKH, HoKH, TenKh, ns, SoDT));
            }
            return cust;
        }

    }

    public boolean addKhachHang(KhachHang k) throws SQLException {

        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO khachhang(MaKH, HoKH, TenKH, NgaySinh, SoDT) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, k.getIdKH());
            stm.setString(2, k.getHoKH());
            stm.setString(3, k.getTenKH());
            stm.setDate(4, k.getNgaySinh());
            stm.setString(5, k.getSoDT());
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

    public boolean updateKhachHang(KhachHang a) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
//             sql = "INSERT INTO khachhang(MaKH, HoKH, TenKH, NgaySinh, SoDT) VALUES (?, ?, ?, ?, ?)";
//            sql = "UPDATE khachhang set HoKH='" + a.getHoKH() + "', TenKH='" + a.getTenKH() + "', NgaySinh='"+a.getNgaySinh()+"',SoDT='"+a.getSoDT()+"' WHERE MaKH="+a.getIdKH();

            String sql = "UPDATE khachhang set HoKH=?, TenKH=?, NgaySinh=?, SoDT=? WHERE MaKH=? ";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, a.getHoKH());
            stm.setString(2, a.getTenKH());
            stm.setDate(3, a.getNgaySinh());
            stm.setString(4, a.getSoDT());
            stm.setInt(5, a.getIdKH());
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

    public boolean deleteKhachHang(int idKH) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM khachhang WHERE MaKH=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idKH);

            return stm.executeUpdate() > 0;
        }
    }

}
