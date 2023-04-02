/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiTietHoaDon;
import com.nmh.pojo.HoaDon;
import com.nmh.utils.MessageBox;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author HP
 */
public class HoaDonService {

    public List<HoaDon> getHoaDon() throws SQLException {
        List<HoaDon> hd = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM hoadon";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int idHoaDon = rs.getInt("MaHoaDon");
                int idChiNhanh = rs.getInt("MaChiNhanh");
                int idNhanVien = rs.getInt("MaNhanVien");
                int idKH = rs.getInt("MaKH");
                double tong = rs.getDouble("Tong");
                double tienKHDua = rs.getDouble("TienKHDua");
                Date ngayCT = rs.getDate("NgayTao");
                hd.add(new HoaDon(idHoaDon, idChiNhanh, idNhanVien, idKH, tong, tienKHDua, ngayCT));
            }
        }
        return hd;
    }

    public boolean addHoaDon(HoaDon g, List<ChiTietHoaDon> ct) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO hoadon(MaHoaDon, MaChiNhanh, MaNhanVien, MaKH, Tong, TienKHDua, NgayTao) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, g.getIdHoaDon());
            stm.setInt(2, g.getIdChiNhanh());
            stm.setInt(3, g.getIdNhanVien());
            stm.setInt(4, g.getIdKH());
            stm.setDouble(5, g.getTong());
            stm.setDouble(6, g.getTienKHDua());
            java.sql.Date ngaytao = java.sql.Date.valueOf(g.getNgayTao().toString());
            stm.setDate(7, ngaytao);

            int r = stm.executeUpdate();

            if (r > 0) {
                sql = "INSERT INTO chitiethd(MaChiTietHD, MaHoaDon, MaSanPham, SoLuong, ThanhTien) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stm1 = conn.prepareCall(sql);

                for (ChiTietHoaDon c : ct) {
                    stm1.setInt(1, c.getIdChiTietHD());
                    stm1.setInt(2, c.getIdHoaDon());
                    stm1.setInt(3, c.getIdSanPham());
                    stm1.setDouble(4, c.getSoLuong());
                    stm1.setDouble(5, c.getThanhTien());
                    stm1.executeUpdate();
                }
            }
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
