/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.pojo;

/**
 *
 * @author HP
 */
public class ChiNhanh {
    private String diaChi;
    private int maChiNhanh;

    public ChiNhanh(String diaChi, int maChiNhanh) {
        this.diaChi = diaChi;
        this.maChiNhanh = maChiNhanh;
    }
    public ChiNhanh(){
        
    }

    @Override
    public String toString() {
        return this.diaChi;
    }
    
    
    
    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the maChiNhanh
     */
    public int getMaChiNhanh() {
        return maChiNhanh;
    }

    /**
     * @param maChiNhanh the maChiNhanh to set
     */
    public void setMaChiNhanh(int maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
