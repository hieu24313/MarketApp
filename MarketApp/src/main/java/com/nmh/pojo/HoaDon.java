/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.pojo;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class HoaDon {
    private int idHoaDon;
    private int idChiNhanh;
    private int idNhanVien;
    private int idKH;
    private double tong;
    private double tienKHDua;
    private Date ngayTao;

    public HoaDon(int idHoaDon, int idChiNhanh, int idNhanVien, int idKH, double tong, double tienKHDua, Date ngayTao) {
        this.idHoaDon = idHoaDon;
        this.idChiNhanh = idChiNhanh;
        this.idNhanVien = idNhanVien;
        this.idKH = idKH;
        this.tong = tong;
        this.tienKHDua = tienKHDua;
        this.ngayTao = ngayTao;
    }
    
    public HoaDon(){
        
    }
    
    /**
     * @return the idHoaDon
     */
    public int getIdHoaDon() {
        return idHoaDon;
    }

    /**
     * @param idHoaDon the idHoaDon to set
     */
    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    /**
     * @return the idChiNhanh
     */
    public int getIdChiNhanh() {
        return idChiNhanh;
    }

    /**
     * @param idChiNhanh the idChiNhanh to set
     */
    public void setIdChiNhanh(int idChiNhanh) {
        this.idChiNhanh = idChiNhanh;
    }

    /**
     * @return the idNhanVien
     */
    public int getIdNhanVien() {
        return idNhanVien;
    }

    /**
     * @param idNhanVien the idNhanVien to set
     */
    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    /**
     * @return the idKH
     */
    public int getIdKH() {
        return idKH;
    }

    /**
     * @param idKH the idKH to set
     */
    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    /**
     * @return the tong
     */
    public double getTong() {
        return tong;
    }

    /**
     * @param tong the tong to set
     */
    public void setTong(double tong) {
        this.tong = tong;
    }

    /**
     * @return the tienKHDua
     */
    public double getTienKHDua() {
        return tienKHDua;
    }

    /**
     * @param tienKHDua the tienKHDua to set
     */
    public void setTienKHDua(double tienKHDua) {
        this.tienKHDua = tienKHDua;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
}
