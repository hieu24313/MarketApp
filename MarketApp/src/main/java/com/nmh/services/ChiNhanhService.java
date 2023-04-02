/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.services;

import com.nmh.pojo.ChiNhanh;
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
public class ChiNhanhService {
    public List<ChiNhanh> getChiNhanh(int idChiNhanh) throws SQLException{
        List<ChiNhanh> c = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
          String sql = "SELECT * FROM chinhanh where MaChiNhanh = " + idChiNhanh;
          PreparedStatement stm = conn.prepareCall(sql);
          ResultSet rs = stm.executeQuery();
          while(rs.next()){
              int id = rs.getInt("MaChiNhanh");
              String diachi = rs.getString("DiaChi");
              c.add(new ChiNhanh(diachi, id));
          }
          
        }
        return c;
    }
}
