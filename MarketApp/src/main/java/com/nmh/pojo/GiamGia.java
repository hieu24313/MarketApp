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
public class GiamGia {
    private int idGiamGia;
    private double GiaTri;
    private Date tgBatDau;
    private Date tgKetThuc;

    public GiamGia(int idGiamGia, double GiaTri, Date tgBatDau, Date tgKetThuc) {
        this.idGiamGia = idGiamGia;
        this.GiaTri = GiaTri;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
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
     * @return the GiaTri
     */
    public double getGiaTri() {
        return GiaTri;
    }

    /**
     * @param GiaTri the GiaTri to set
     */
    public void setGiaTri(double GiaTri) {
        this.GiaTri = GiaTri;
    }

    /**
     * @return the tgBatDau
     */
    public Date getTgBatDau() {
        return tgBatDau;
    }

    /**
     * @param tgBatDau the tgBatDau to set
     */
    public void setTgBatDau(Date tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    /**
     * @return the tgKetThuc
     */
    public Date getTgKetThuc() {
        return tgKetThuc;
    }

    /**
     * @param tgKetThuc the tgKetThuc to set
     */
    public void setTgKetThuc(Date tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }
    
}
