/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.NhanVien;
import java.sql.Connection;
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
                
                
//              1  ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
//                while(rs.next()) {
//                    int id = rs.getInt("MaNhanVien");
//                    String hoNV = rs.getString("HoNV");
//                    String tenNV = rs.getString("TenNV");
//                    int idChiNhanh = rs.getInt("MaChiNhanh");
//                    String TaiKhoan = rs.getString("TaiKhoan");
//                    String MatKhau = rs.getString("MatKhau");
//                    boolean LoaiNV = rs.getBoolean("LoaiNV");
//                    employ.add(new NhanVien(id, hoNV, tenNV, idChiNhanh, TaiKhoan, MatKhau, LoaiNV));
//  
//                }
    }
        return employ;
 }
}
