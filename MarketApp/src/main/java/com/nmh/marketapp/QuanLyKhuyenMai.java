/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.GiamGia;
import com.nmh.services.GiamGiaService;
import com.nmh.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class QuanLyKhuyenMai {

    @FXML
    private Button btnThoat;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tbKhuyenMai;
    @FXML
    private TextField txtIdGiamGia;
    @FXML
    private TextField txtGiaTri;
    @FXML
    private DatePicker dpTGBatDau;
    @FXML
    private DatePicker dpTGKetThuc;

    public void initialize() throws SQLException {

        this.txtIdGiamGia.setDisable(true);
        this.loadTBKhuyenMai();
        this.loaddataTable();
        this.setIDchoTextField();
        this.txtSearch.textProperty().addListener(e -> {
            
            this.tbKhuyenMai.getItems().clear();
            try {
                this.loaddataTable(Integer.parseInt(this.txtSearch.getText()));
            } catch (SQLException ex) {

                Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void loadTBKhuyenMai() {
        TableColumn colid = new TableColumn("Mã Giảm Giá");
        TableColumn colGiaTri = new TableColumn("Giá Trị Của Mã Khuyến Mãi");
        TableColumn colBatDau = new TableColumn("Ngày Bắt Đầu");
        TableColumn colKetThuc = new TableColumn("Ngày Kết Thúc");
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory(r -> {
            Button btn = new Button("Xóa Mã Khuyến Mãi");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Mã Khuyến Mãi",
                        "Bạn muốn xóa mã khuyến mãi này đúng không?",
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        GiamGia q = (GiamGia) cell.getTableRow().getItem();
                        GiamGiaService ggsv = new GiamGiaService();
                        try {
                            if (ggsv.deleteGiamGia(q.getIdGiamGia())) {
                                MessageBox.getBox("Mã Khuyến Mãi", "Xóa Thành Công!!!", Alert.AlertType.INFORMATION).show();
                                this.loaddataTable();
                                this.setIDchoTextField();
                                this.resetGiaTri();
                            } else {
                                MessageBox.getBox("Mã Khuyến Mãi", "Xóa Thất Bại!!!", Alert.AlertType.WARNING).show();
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Mã Khuyến Mãi", ex.getMessage(), Alert.AlertType.WARNING).show();
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

        colid.setCellValueFactory(new PropertyValueFactory("idGiamGia"));
        colGiaTri.setCellValueFactory(new PropertyValueFactory("GiaTri"));
        colBatDau.setCellValueFactory(new PropertyValueFactory("tgBatDau"));
        colKetThuc.setCellValueFactory(new PropertyValueFactory("tgKetThuc"));

        this.tbKhuyenMai.getColumns().addAll(colid, colGiaTri, colBatDau, colKetThuc, colDel);
    }

    public void loaddataTable() throws SQLException {
        GiamGiaService ggsv = new GiamGiaService();
        this.tbKhuyenMai.getItems().clear();
        this.tbKhuyenMai.setItems(FXCollections.observableList(ggsv.getGiamGia()));
    }

    public void loaddataTable(int id) throws SQLException {
        GiamGiaService ggsv = new GiamGiaService();
        this.tbKhuyenMai.getItems().clear();
        this.tbKhuyenMai.setItems(FXCollections.observableList(ggsv.getGiamGia(id)));
    }

    public void setIDchoTextField() throws SQLException {
        GiamGiaService ggsv = new GiamGiaService();
        List<GiamGia> g = ggsv.getGiamGia();
        int id = g.get(g.size() - 1).getIdGiamGia() + 1;
        this.txtIdGiamGia.setText(id + "");
    }

    public void resetGiaTri() throws SQLException {
        this.txtGiaTri.setText("");
        this.dpTGBatDau.setValue(LocalDate.now());
        this.dpTGKetThuc.setValue(LocalDate.now());
        this.setIDchoTextField();
    }

    public void addGiamGia(ActionEvent evt) throws SQLException {
        if (!this.txtIdGiamGia.getText().isEmpty() && !this.txtGiaTri.getText().isEmpty() && this.dpTGBatDau.getValue() != null && this.dpTGKetThuc.getValue() != null) {
            GiamGiaService agg = new GiamGiaService();
            int id = Integer.parseInt(this.txtIdGiamGia.getText());
            Double giaTri = Double.valueOf(this.txtGiaTri.getText());
            LocalDate tgBD = this.dpTGBatDau.getValue();
            java.sql.Date tgBatDau = java.sql.Date.valueOf(tgBD);
            LocalDate tgkt = this.dpTGKetThuc.getValue();
            java.sql.Date tgKetThuc = java.sql.Date.valueOf(tgkt);

            GiamGia g = new GiamGia(id, giaTri, tgBatDau, tgKetThuc);
            boolean kt = agg.addGiamGia(g);
            if (kt) {
                this.resetGiaTri();
                this.loaddataTable();
                Alert a = MessageBox.getBox("Giảm Giá", "Thêm Thành Công", Alert.AlertType.CONFIRMATION);
                a.show();

            } else {
                Alert b = MessageBox.getBox("Giảm Giá", "Thêm Thất Bại", Alert.AlertType.CONFIRMATION);
                b.show();
            }
        } else {
            Alert c = MessageBox.getBox("Thêm Mã Giảm Giá", "Vui lòng nhập đầy đủ thông tin!!!", Alert.AlertType.CONFIRMATION);
            c.show();
        }

    }
    
    public void suaGiamGia(ActionEvent evt){
        Object selectedObject = tbKhuyenMai.getSelectionModel().getSelectedItem();
        if(selectedObject != null){
            GiamGia g = (GiamGia) selectedObject;
            this.txtIdGiamGia.setText(g.getIdGiamGia() + "");
            this.txtGiaTri.setText(g.getGiaTri()+ "");
            LocalDate tgBD = g.getTgBatDau().toLocalDate();
            LocalDate tgKT = g.getTgKetThuc().toLocalDate();
            this.dpTGBatDau.setValue(tgBD);
            this.dpTGKetThuc.setValue(tgKT);
        }
        else {
            Alert c = MessageBox.getBox("Chọn Mã Giảm Giá", "Vui lòng chọn mã giảm giá muốn sửa thông tin trong tableview!", Alert.AlertType.CONFIRMATION);
            c.show();
        }
    }
    
    public void updateGiamGia(ActionEvent evt) throws SQLException{
        GiamGiaService gg = new GiamGiaService();
        int idgg = Integer.parseInt(this.txtIdGiamGia.getText());
        Double GiaTri = Double.valueOf(this.txtGiaTri.getText());
        LocalDate tgBD = this.dpTGBatDau.getValue();
            java.sql.Date tgBatDau = java.sql.Date.valueOf(tgBD);
            LocalDate tgkt = this.dpTGKetThuc.getValue();
            java.sql.Date tgKetThuc = java.sql.Date.valueOf(tgkt);
            
            GiamGia g = new GiamGia(idgg, GiaTri, tgBatDau, tgKetThuc);
            boolean kt = gg.updateGiamGia(g);
            if(kt){
                Alert a = MessageBox.getBox("Cập nhật mã giảm giá", "Cập nhật mã giảm giá thành công!!!", Alert.AlertType.CONFIRMATION);
                this.loaddataTable();
                this.resetGiaTri();
                a.show();
            }
            else {
                Alert b = MessageBox.getBox("Cập nhật mã giảm giá", "Cập nhật mã giảm giá thất bại!!!", Alert.AlertType.CONFIRMATION);
                b.show();
            }
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
}
