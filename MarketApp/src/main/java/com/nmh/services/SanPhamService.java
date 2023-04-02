/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.SanPham;
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
public class SanPhamService {
    public List<SanPham> getSanPham(String kw) throws SQLException {
        
        List<SanPham> proc = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
          
            String sql = "SELECT * FROM sanpham";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE TenSanPham like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                    int MaSP = rs.getInt("MaSanPham");
                    String TenSP = rs.getString("TenSanPham");
                    int Gia = rs.getInt("Gia");
                    String DonVi = rs.getString("DonVi");
                    String XuatXu = rs.getString("XuatXu");
                    int MaGiamGia = rs.getInt("MaGiamGia");
                    proc.add(new SanPham(MaSP, TenSP, Gia, DonVi, XuatXu, MaGiamGia));
                    
            } 
            
        }
        return proc;
 }
}
