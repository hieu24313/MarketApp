/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ChiTietHoaDonService {

    
    public List<ChiTietHoaDon> getChiTietHoaDon() throws SQLException {
        List<ChiTietHoaDon> cthd = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM chitiethd";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int idCTHoaDon = rs.getInt("MaChiTietHD");
                int idHoaDon = rs.getInt("MaHoaDon");
                int idSanPham = rs.getInt("MaSanPham");
                double sl = rs.getDouble("SoLuong");
                double tong = rs.getDouble("ThanhTien");
                
                cthd.add(new ChiTietHoaDon(idCTHoaDon, idHoaDon, idSanPham, sl, tong));
            }
        }
        return cthd;
    }
}
