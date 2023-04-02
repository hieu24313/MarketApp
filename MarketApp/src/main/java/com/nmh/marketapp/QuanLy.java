/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class QuanLy {

    @FXML
    private Button btnSanPham;
    @FXML
    private Button btnNhanVien;
    @FXML
    private Button btnKhachHang;
    @FXML
    private Button btnChiNhanh;
    @FXML
    private Button btnKhuyenMai;


    public void chuyenSangSanPham(ActionEvent evt) throws IOException {

        String nf = "QuanLySanPham.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Quản Lý Sản Phẩm");
        formStage.show();
        Stage oldStage = (Stage) btnSanPham.getScene().getWindow();
        oldStage.close();
    }

    public void chuyenSangNhanVien(ActionEvent evt) throws IOException {

        String nf = "QuanLyNhanVien.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Quản Lý Nhân Viên");
        formStage.show();
        Stage oldStage = (Stage) btnNhanVien.getScene().getWindow();
        oldStage.close();
    }

    public void chuyenSangKhachHang(ActionEvent evt) throws IOException {

        String nf = "QuanLyKhachHang.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Quản Lý Khách Hàng");
        formStage.show();
        Stage oldStage = (Stage) btnKhachHang.getScene().getWindow();
        oldStage.close();
    }

    public void chuyenSangKhuyenMai(ActionEvent evt) throws IOException{
        String nf = "QuanLyKhuyenMai.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Quản Lý Khuyễn Mãi");
        formStage.show();
        Stage oldStage = (Stage) btnKhuyenMai.getScene().getWindow();
        oldStage.close();
    }
    
    public void chuyenSangChiNhanh(ActionEvent evt) throws IOException{
        String nf = "QuanLyChiNhanh.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Quản Lý Chi Nhánh");
        formStage.show();
        Stage oldStage = (Stage) btnChiNhanh.getScene().getWindow();
        oldStage.close();
    }

}
