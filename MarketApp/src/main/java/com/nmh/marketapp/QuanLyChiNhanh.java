/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.GiamGia;
import com.nmh.services.ChiNhanhService;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Mesh;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class QuanLyChiNhanh {

    @FXML
    private Button btnThoat;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tbChiNhanh;
    @FXML
    private TextField txtMaChiNhanh;
    @FXML
    private TextField txtDiaChi;

    public void initialize() throws SQLException {
        this.txtMaChiNhanh.setDisable(true);
        this.loadTableChiNhanh();
        this.setIDchoTextField();
        this.loadTableData();
        this.txtSearch.textProperty().addListener(e -> {
            
//                String diaChi = this.txtSearch.getText();
            try {
                this.loadTableData(this.txtSearch.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }

    public void addChiNhanh(ActionEvent evt) throws SQLException {
        if (!this.txtDiaChi.getText().isEmpty()) {
            ChiNhanhService cn = new ChiNhanhService();
            int id = Integer.parseInt(this.txtMaChiNhanh.getText());
            String diachi = this.txtDiaChi.getText();

            ChiNhanh c = new ChiNhanh(diachi, id);

            boolean kt = cn.addChiNhanh(c);
            if (kt) {
                Alert a = MessageBox.getBox("Thêm Chi Nhánh", "Thêm Chi Nhánh Thành Công!!!", Alert.AlertType.CONFIRMATION);
                a.show();
                this.loadTableData();
                this.resetGiaTri();
            } else {
                Alert b = MessageBox.getBox("Thêm Chi Nhánh", "Thêm Chi Nhánh Thất Bại!!!", Alert.AlertType.CONFIRMATION);
                b.show();
            }
        } else {
            Alert c = MessageBox.getBox("Chi Nhánh", "Vui Lòng Nhập Đầy Đủ Thông Tin!!!", Alert.AlertType.CONFIRMATION);
            c.show();
        }

    }

    public void loadTableChiNhanh() {

        TableColumn colId = new TableColumn("Mã Chi Nhánh");
        TableColumn colDiaChi = new TableColumn("Địa Chỉ");
        colDiaChi.setMinWidth(500);
        TableColumn colDel = new TableColumn();
        colDel.setMinWidth(200);
        colDel.setCellFactory(r -> {
            Button btn = new Button("Xóa Chi Nhánh");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Chi Nhánh",
                        "Bạn muốn xóa chi nhánh này đúng không?",
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        ChiNhanh q = (ChiNhanh) cell.getTableRow().getItem();
                        ChiNhanhService cn = new ChiNhanhService();
                        try {
                            if (cn.deleteChiNhanh(q.getMaChiNhanh())) {
                                MessageBox.getBox("Chi Nhánh", "Xóa Thành Công!!!", Alert.AlertType.INFORMATION).show();
                                this.loadTableData();
                                this.setIDchoTextField();
                                this.resetGiaTri();
                            } else {
                                MessageBox.getBox("Chi Nhánh", "Xóa Thất Bại!!!", Alert.AlertType.WARNING).show();
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Chi Nhanh", ex.getMessage(), Alert.AlertType.WARNING).show();
                            Logger.getLogger(QuanLyChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });
        colId.setCellValueFactory(new PropertyValueFactory("maChiNhanh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));

        this.tbChiNhanh.getColumns().addAll(colId, colDiaChi, colDel);
    }

//    public void loadTableData(String diaChi) throws SQLException {
//        ChiNhanhService c = new ChiNhanhService();
//        if (diaChi.isEmpty() || diaChi.equals("")) {
//            this.tbChiNhanh.getColumns().clear();
//            this.tbChiNhanh.setItems(FXCollections.observableList(c.getChiNhanh()));
//        } else {
//            this.tbChiNhanh.getColumns().clear();
//            this.tbChiNhanh.setItems(FXCollections.observableList(c.getChiNhanh(diaChi)));
//        }
//
//    }
    public void loadTableData() throws SQLException {
        ChiNhanhService c = new ChiNhanhService();
        
//        this.tbChiNhanh.getColumns().clear();
        this.tbChiNhanh.setItems(FXCollections.observableList(c.getChiNhanh()));
    }
    
    public void loadTableData(String diaChi) throws SQLException {
        ChiNhanhService c = new ChiNhanhService();
//        this.tbChiNhanh.getColumns().clear();
        this.tbChiNhanh.setItems(FXCollections.observableList(c.getChiNhanh(diaChi)));
    }

    public void setIDchoTextField() throws SQLException {
        ChiNhanhService cn = new ChiNhanhService();
        List<ChiNhanh> g = cn.getChiNhanh();
        int id = g.get(g.size() - 1).getMaChiNhanh() + 1;
        this.txtMaChiNhanh.setText(id + "");
    }

    
    public void resetGiaTri() throws SQLException {
        this.setIDchoTextField();
        this.txtDiaChi.setText("");

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

    public void suaChiNhanh(ActionEvent evt) {
        Object selectedObject = tbChiNhanh.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            ChiNhanh g = (ChiNhanh) selectedObject;
            this.txtDiaChi.setText(g.getDiaChi() + "");
            this.txtMaChiNhanh.setText(g.getMaChiNhanh() + "");
        } else {
            Alert c = MessageBox.getBox("Chọn Mã Giảm Giá", "Vui lòng chọn mã giảm giá muốn sửa thông tin trong tableview!", Alert.AlertType.CONFIRMATION);
            c.show();
        }
    }

    public void updateChiNhanh(ActionEvent evt) throws SQLException {
        ChiNhanhService cn = new ChiNhanhService();
        int idcn = Integer.parseInt(this.txtMaChiNhanh.getText());
        String diaChi = this.txtDiaChi.getText();
        ChiNhanh c = new ChiNhanh(diaChi, idcn);
        boolean kt = cn.updateChiNhanh(c);
        if (kt) {
            Alert a = MessageBox.getBox("Cập nhật mã giảm giá", "Cập nhật mã giảm giá thành công!!!", Alert.AlertType.CONFIRMATION);
            this.loadTableData();
            this.resetGiaTri();
            a.show();
        } else {
            Alert b = MessageBox.getBox("Cập nhật mã giảm giá", "Cập nhật mã giảm giá thất bại!!!", Alert.AlertType.CONFIRMATION);
            b.show();
        }
    }

}
