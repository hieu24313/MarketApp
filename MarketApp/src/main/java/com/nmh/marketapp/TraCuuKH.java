/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.KhachHang;
import com.nmh.services.KhachHangService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class TraCuuKH {

    @FXML
    private TextField txtTimTen;
    @FXML
    private TextField txtTimsdt;
    @FXML
    private TableView tbKhachHang;
    @FXML
    private Button btnThoat;

    KhachHangService c = new KhachHangService();

    public void initialize() throws SQLException {
        this.loadtbKH();
        this.txtTimTen.textProperty().addListener(e -> {
            try {
                this.tbKhachHang.getItems().clear();
                this.loaddataKH(this.txtTimTen.getText(),this.txtTimsdt.getText());

            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.txtTimsdt.textProperty().addListener(e -> {
            try {
                this.tbKhachHang.getItems().clear();
                this.loaddataKH(this.txtTimTen.getText(), this.txtTimsdt.getText());

            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loaddataKH();

    }

    public void thoatXemKH(ActionEvent evt) {
        Stage stg = (Stage) btnThoat.getScene().getWindow();
        stg.close();
    }

    public void loadtbKH() {
        TableColumn colId = new TableColumn("Mã Khách Hàng");
        TableColumn colHo = new TableColumn("Họ");
        TableColumn colTen = new TableColumn("Tên");
        TableColumn colNgaySinh = new TableColumn("Ngày Sinh");
        TableColumn colDT = new TableColumn("Số Điện Thoại");

        colId.setCellValueFactory(new PropertyValueFactory("idKH"));
        colHo.setCellValueFactory(new PropertyValueFactory("hoKH"));
        colTen.setCellValueFactory(new PropertyValueFactory("tenKH"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colDT.setCellValueFactory(new PropertyValueFactory("soDT"));

        this.tbKhachHang.getColumns().addAll(colId, colHo, colTen, colNgaySinh, colDT);

    }

    public void loaddataKH() throws SQLException {

        String ten = this.txtTimTen.getText();
        String soDT = this.txtTimsdt.getText();
        List<KhachHang> kh = c.getKhachHang(ten, soDT);
        this.tbKhachHang.getItems().clear();
        this.tbKhachHang.setItems(FXCollections.observableList(kh));
    }

    public void loaddataKH(String t, String dt) throws SQLException {

        String ten = t;
        String soDT = dt;
        List<KhachHang> kh = c.getKhachHang(ten, soDT);
        this.tbKhachHang.getItems().clear();
        this.tbKhachHang.setItems(FXCollections.observableList(kh));
    }
}
