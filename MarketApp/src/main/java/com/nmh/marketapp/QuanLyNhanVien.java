/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.NhanVien;
import com.nmh.services.NhanVienService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class QuanLyNhanVien {

    @FXML
    private Button btnThoat;
    @FXML
    private TableView tbNhanVien;
    @FXML 
    private TextField txtSearch;

    
    public void initialize() throws SQLException{
        this.loadtbNhanVien();
        this.loaddataNhanVien();
    }
    public void thoatQuanLy(ActionEvent e) throws IOException {
        String nf = "QuanLy.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Trang Quản Lý");
        formStage.show();
        Stage oldStage = (Stage) btnThoat.getScene().getWindow();
        oldStage.close();
    }

    public void loadtbNhanVien() {
        
      TableColumn colId = new TableColumn("Mã Nhân Viên");
      TableColumn colHoNV = new TableColumn("Họ Nhân Viên");
      TableColumn colTenNV = new TableColumn("Tên Nhân Viên");
      TableColumn colIdChiNhanh = new TableColumn("Mã Chi Nhánh");
      TableColumn colTaiKhoan = new TableColumn("Tài Khoản");
      TableColumn colMatKhau = new TableColumn("Mật Khẩu");
      TableColumn colQuanLy = new TableColumn("Quản Lý");
      
      colId.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
      colHoNV.setCellValueFactory(new PropertyValueFactory("hoNV"));
      colTenNV.setCellValueFactory(new PropertyValueFactory("tenNV"));
      colIdChiNhanh.setCellValueFactory(new PropertyValueFactory("idChiNhanh"));
      colTaiKhoan.setCellValueFactory(new PropertyValueFactory("taiKhoan"));
      colMatKhau.setCellValueFactory(new PropertyValueFactory("matKhau"));
      colQuanLy.setCellValueFactory(new PropertyValueFactory("loaiNV"));
      
      this.tbNhanVien.getColumns().addAll(colId, colHoNV, colTenNV, colIdChiNhanh, colTaiKhoan, colMatKhau, colQuanLy);
    }
    
    public void loaddataNhanVien() throws SQLException{
        NhanVienService nvs = new NhanVienService();
        List<NhanVien> nv = nvs.getNhanVien();
        
        this.tbNhanVien.getItems().clear();
        this.tbNhanVien.setItems(FXCollections.observableList(nv));
        
    }
}
