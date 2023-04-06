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
    public List<ChiNhanh> getChiNhanh(String dc) throws SQLException{
        List<ChiNhanh> c = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
          String sql = "SELECT * FROM chinhanh where DiaChi LIKE CONCAT('%', ?, '%')";
          PreparedStatement stm = conn.prepareCall(sql);
          stm.setString(1, dc);
          ResultSet rs = stm.executeQuery();
          while(rs.next()){
              int id = rs.getInt("MaChiNhanh");
              String diachi = rs.getString("DiaChi");
              c.add(new ChiNhanh(diachi, id));
              
          }
          
        }
        return c;
    }
    
    public List<ChiNhanh> getChiNhanh(int idcn) throws SQLException{
        List<ChiNhanh> c = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
          String sql = "SELECT * FROM chinhanh where MaChiNhanh = " + idcn;
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
    
    public List<ChiNhanh> getChiNhanh() throws SQLException{
        List<ChiNhanh> c = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
          String sql = "SELECT * FROM chinhanh";
          PreparedStatement stm = conn.prepareCall(sql);
          ResultSet rs = stm.executeQuery();
          while(rs.next()){
              int id = rs.getInt("MaChiNhanh");
              String diachi = rs.getString("DiaChi");
              c.add(new ChiNhanh(diachi, id));
//              Alert a = MessageBox.getBox("bug", "Bug" + diachi, Alert.AlertType.CONFIRMATION);
//              a.show();
          }
          
        }
        return c;
    }
    
    public boolean addChiNhanh(ChiNhanh c) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chinhanh(MaChiNhanh, DiaChi) VALUES (?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, c.getMaChiNhanh());
            stm.setString(2, c.getDiaChi());
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
    
    public boolean updateChiNhanh(ChiNhanh c) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE chinhanh set DiaChi=? WHERE MaChiNhanh = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setString(1, c.getDiaChi());
            stm.setInt(2, c.getMaChiNhanh());
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
    
    public boolean deleteChiNhanh(int idChiNhanh) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM chinhanh WHERE MaChiNhanh = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idChiNhanh);
            
            return stm.executeUpdate() > 0;
    }
    }
    
}
