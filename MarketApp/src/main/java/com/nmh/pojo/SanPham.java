/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.pojo;

/**
 *
 * @author HP
 */
public class SanPham {
    private int idSanPham;
    private String tenSP;
    private double giaSP;
    private String donVi;
    private String xuatXu;
    private int idGiamGia;
    private int idChiNhanh;
    private double soluong = 1.0;



    public SanPham(int idSanPham, String tenSP, double giaSP, String donVi, String xuatXu, int idChiNhanh ,int idGiamGia) {
        this.idSanPham = idSanPham;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.donVi = donVi;
        this.xuatXu = xuatXu;
        this.idChiNhanh = idChiNhanh;
        this.idGiamGia = idGiamGia;
    }
    
    
    public SanPham() {
    }

    
    @Override
    public String toString() {
        return this.tenSP;
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
     * @return the tenSP
     */
    public String getTenSP() {
        return tenSP;
    }

    /**
     * @param tenSP the tenSP to set
     */
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    /**
     * @return the giaSP
     */
    public double getGiaSP() {
        return giaSP;
    }

    /**
     * @param giaSP the giaSP to set
     */
    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    /**
     * @return the donVi
     */
    public String getDonVi() {
        return donVi;
    }

    /**
     * @param donVi the donVi to set
     */
    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    /**
     * @return the idGiamGia
     */
    public int getIdGiamGia() {
        return idGiamGia;
    }

    /**
     * @param idGiamGia the idGiamGia to set
     */
    public void setIdGiamGia(int idGiamGia) {
        this.idGiamGia = idGiamGia;
    }

    /**
     * @return the xuatXu
     */
    public String getXuatXu() {
        return xuatXu;
    }

    /**
     * @param xuatXu the xuatXu to set
     */
    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    /**
     * @return the soluong
     */
    public double getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(double soluong) {
        this.soluong = soluong;
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

    
}
