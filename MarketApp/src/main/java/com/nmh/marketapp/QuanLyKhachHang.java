/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.KhachHang;
import com.nmh.services.KhachHangService;
import com.nmh.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class QuanLyKhachHang {

    @FXML
    private Button btnThoat;
    @FXML
    private TextField txtMaKh;
    @FXML
    private TextField txtHoKH;
    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtSoDT;
    @FXML
    private TextField txtSearch;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private TableView tbKhachHang;

    KhachHangService kh = new KhachHangService();

    public void initialize() throws SQLException {
        this.txtMaKh.setDisable(true);
        this.loadIdKH();
        this.loadtbKH();
        this.loaddataKH();
        this.txtSearch.textProperty().addListener(e -> {

            this.tbKhachHang.getItems().clear();
            try {
                this.loaddataKH(this.txtSearch.getText());
            } catch (SQLException ex) {

                Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) { // Chỉ cho phép nhập số
                return change;
            } else {
                Alert a = MessageBox.getBox("Cảnh báo", "Vui lòng nhập số!!", Alert.AlertType.WARNING);
                a.show();
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);
        this.txtSearch.setTextFormatter(formatter);
    }

    public void loadIdKH() throws SQLException {
        List<KhachHang> k = kh.getKhachHang();
        int idLastKH = k.get(k.size() - 1).getIdKH() + 1;
        this.txtMaKh.setText(idLastKH + "");
    }

    public void addKH(ActionEvent evt) throws SQLException {

        if (!this.txtHoKH.getText().isEmpty() && !this.txtTenKH.getText().isEmpty() && !this.txtSoDT.getText().isEmpty() && this.dpNgaySinh.getValue() != null) {
            int idKH = Integer.parseInt(this.txtMaKh.getText());
            String hoKH = this.txtHoKH.getText();
            String tenKH = this.txtTenKH.getText();
            LocalDate ns = this.dpNgaySinh.getValue();
            Date NgaySinh = java.sql.Date.valueOf(ns);
            String soDT = this.txtSoDT.getText();
            KhachHang k = new KhachHang(idKH, hoKH, tenKH, (java.sql.Date) NgaySinh, soDT);

            try {
                kh.addKhachHang(k);
                this.loadIdKH();
                Alert a = MessageBox.getBox("Thêm Khách Hàng", "Thêm Khách Hàng Thành Công!!!", Alert.AlertType.CONFIRMATION);
                a.show();
                this.resetGiaTri();

            } catch (SQLException ex) {
                Alert b = MessageBox.getBox("Thêm Khách Hàng", "Thêm Khách Hàng Thất bại!!!", Alert.AlertType.ERROR);
                b.show();
            }
        } else {
            Alert a = MessageBox.getBox("Thêm Khách Hàng", "Vui lòng nhập đầy đủ thông tin khách hàng", Alert.AlertType.CONFIRMATION);
            a.show();
        }

    }

    public void loadtbKH() {
        TableColumn colId = new TableColumn("Mã Khách Hàng");
        TableColumn colHo = new TableColumn("Họ");
        TableColumn colTen = new TableColumn("Tên");
        TableColumn colNgaySinh = new TableColumn("Ngày Sinh");
        TableColumn colDT = new TableColumn("Số Điện Thoại");
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory(r -> {
            Button btn = new Button("Xóa Khách Hàng");
            
            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Khách Hàng", 
                        "Bạn muốn xóa khách này đúng không?", 
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button)evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        KhachHang q = (KhachHang) cell.getTableRow().getItem();
                        try {
                            if (kh.deleteKhachHang(q.getIdKH())) {
                                MessageBox.getBox("Khách Hàng", "Xóa Thành Công!!!", Alert.AlertType.INFORMATION).show();
                                this.loaddataKH(null);
                                this.loadIdKH();
                                this.resetGiaTri();
                            } else
                                MessageBox.getBox("Khách Hàng", "Xóa Thất Bại!!!", Alert.AlertType.WARNING).show();
                                
                         } catch (SQLException ex) {
                              MessageBox.getBox("Khách Hàng", ex.getMessage(), Alert.AlertType.WARNING).show();
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                });
            });
            
            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

        colId.setCellValueFactory(new PropertyValueFactory("idKH"));
        colHo.setCellValueFactory(new PropertyValueFactory("hoKH"));
        colTen.setCellValueFactory(new PropertyValueFactory("tenKH"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colDT.setCellValueFactory(new PropertyValueFactory("soDT"));

        this.tbKhachHang.getColumns().addAll(colId, colHo, colTen, colNgaySinh, colDT, colDel);

    }

    public void loaddataKH() throws SQLException {
        KhachHangService c = new KhachHangService();
        this.tbKhachHang.getItems().clear();
        this.tbKhachHang.setItems(FXCollections.observableList(c.getKhachHang()));
    }
    

    public void loaddataKH(String soDT) throws SQLException {
        KhachHangService c = new KhachHangService();
        this.tbKhachHang.getItems().clear();
        this.tbKhachHang.setItems(FXCollections.observableList(c.getKhachHang("", soDT)));
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

    public void suaKhachHang(ActionEvent evt) {
        Object selectedObject = tbKhachHang.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            KhachHang k = (KhachHang) selectedObject;
            this.txtMaKh.setText(k.getIdKH() + "");
            this.txtHoKH.setText(k.getHoKH());
            this.txtTenKH.setText(k.getTenKH());
            this.txtSoDT.setText(k.getSoDT());
            LocalDate NgaySinh = k.getNgaySinh().toLocalDate();
            this.dpNgaySinh.setValue(NgaySinh);

        } else {
            Alert c = MessageBox.getBox("Chọn Khách Hàng", "Vui lòng chọn khách hàng muốn sửa thông tin trong tableview!", Alert.AlertType.CONFIRMATION);
            c.show();
        }
    }

    public void capNhatKhachHang(ActionEvent evt) throws SQLException {
        int idKH = Integer.parseInt(this.txtMaKh.getText());
        String hoKh = this.txtHoKH.getText();
        String tenKH = this.txtTenKH.getText();
        LocalDate ns = this.dpNgaySinh.getValue();
        Date NgaySinh = java.sql.Date.valueOf(ns);
        String soDT = this.txtSoDT.getText();
        boolean kt = kh.updateKhachHang(new KhachHang(idKH, hoKh, tenKH, (java.sql.Date) NgaySinh, soDT));
        if (kt) {
            Alert a = MessageBox.getBox("Cập nhật thông tin khách hàng", "Cập nhật thành công!!!", Alert.AlertType.CONFIRMATION);
            a.show();
            this.loadIdKH();
            this.resetGiaTri();
            this.loaddataKH();

        } else {
            Alert b = MessageBox.getBox("Cập nhật thông tin khách hàng", "Cập nhật thất bại!!!", Alert.AlertType.CONFIRMATION);
            b.show();
        }

    }

    public void resetGiaTri() {
        this.txtMaKh.setText("");
        this.txtHoKH.setText("");
        this.txtTenKH.setText("");
        this.txtSoDT.setText("");
        this.dpNgaySinh.setValue(LocalDate.now());
    }
}
