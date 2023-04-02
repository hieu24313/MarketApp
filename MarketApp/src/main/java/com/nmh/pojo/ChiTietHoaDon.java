/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.pojo;

/**
 *
 * @author HP
 */
public class ChiTietHoaDon {
    private int idChiTietHD;
    private int idHoaDon;
    private int idSanPham;
    private double soLuong;
    private double thanhTien;

    public ChiTietHoaDon(int idChiTietHD, int idHoaDon, int idSanPham, double soLuong, double thanhTien) {
        this.idChiTietHD = idChiTietHD;
        this.idHoaDon = idHoaDon;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }
    
    
    /**
     * @return the idChiTietHD
     */
    public int getIdChiTietHD() {
        return idChiTietHD;
    }

    /**
     * @param idChiTietHD the idChiTietHD to set
     */
    public void setIdChiTietHD(int idChiTietHD) {
        this.idChiTietHD = idChiTietHD;
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
     * @return the idSanPham
     */
    public int getIdSanPham() {
        return idSanPham;
    }

    /**
     * @param idSanPham the idSanPham to set
     */
    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    /**
     * @return the soLuong
     */
    public double getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the thanhTien
     */
    public double getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
