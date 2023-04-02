/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.pojo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class KhachHang {
    private int idKH;
    private String hoKH;
    private String tenKH;
    private Date ngaySinh;
    private String soDT;

    public KhachHang(int idKH, String hoKH, String tenKH, Date ngaySinh, String soDT) {
        this.idKH = idKH;
        this.hoKH = hoKH;
        this.tenKH = tenKH;
        Date ns = ngaySinh;
        this.ngaySinh = ns;
        this.soDT = soDT;
    }
    public KhachHang(){
        
    }

    @Override
    public String toString() {
        return this.hoKH + this.tenKH;
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
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the soDT
     */
    public String getSoDT() {
        return soDT;
    }

    /**
     * @param soDT the soDT to set
     */
    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    /**
     * @return the hoKH
     */
    public String getHoKH() {
        return hoKH;
    }

    /**
     * @param hoKH the hoKH to set
     */
    public void setHoKH(String hoKH) {
        this.hoKH = hoKH;
    }
    
}
