/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.NhanVien;
import com.nmh.services.ChiNhanhService;
import com.nmh.services.NhanVienService;
import com.nmh.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
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
public class QuanLyNhanVien {

    @FXML
    private Button btnThoat;
    @FXML
    private TableView tbNhanVien;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtMaNhanVien;
    @FXML
    private TextField txtHoNhanVien;
    @FXML
    private TextField txtTenNhanVien;
    @FXML
    private ComboBox cbChiNhanh;
    @FXML
    private TextField txtTaiKhoanNhanVien;
    @FXML
    private TextField txtMatKhauNhanVien;
    @FXML
    private ComboBox cbLoaiNhanVien;

    public void initialize() throws SQLException {
        this.loadtbNhanVien();
        this.loaddataNhanVien();
        cbLoaiNhanVien.getItems().addAll("Nhân Viên", "Quản Lý");
        cbChiNhanh.setItems(FXCollections.observableList(this.layDSChiNhanh()));
        this.txtMaNhanVien.setDisable(true);
        this.ganIdNhanVienChoTextField();
        this.txtSearch.textProperty().addListener(e -> {

            this.tbNhanVien.getItems().clear();
            try {
                this.loaddataNhanVien(this.txtSearch.getText());
            } catch (SQLException ex) {

                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void loaddataNhanVien(String ten) throws SQLException {
        NhanVienService nvs = new NhanVienService();
        List<NhanVien> nv = nvs.getNhanVien(ten);

        this.tbNhanVien.getItems().clear();
        this.tbNhanVien.setItems(FXCollections.observableList(nv));

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
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory(r -> {
            Button btn = new Button("Xóa Nhân Viên");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Nhân Viên",
                        "Bạn muốn xóa nhân viên này đúng không?",
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        NhanVienService nv = new NhanVienService();
                        NhanVien q = (NhanVien) cell.getTableRow().getItem();
                        try {
                            if (nv.deleteNhanVien(q.getMaNhanVien())) {
                                MessageBox.getBox("Nhân Viên", "Xóa Thành Công!!!", Alert.AlertType.INFORMATION).show();
                                this.loaddataNhanVien();
                                this.ganIdNhanVienChoTextField();
                            } else {
                                MessageBox.getBox("Nhân Viên", "Xóa Thất Bại!!!", Alert.AlertType.WARNING).show();
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Nhân Viên", ex.getMessage(), Alert.AlertType.WARNING).show();
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

        colId.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
        colHoNV.setCellValueFactory(new PropertyValueFactory("hoNV"));
        colTenNV.setCellValueFactory(new PropertyValueFactory("tenNV"));
        colIdChiNhanh.setCellValueFactory(new PropertyValueFactory("idChiNhanh"));
        colTaiKhoan.setCellValueFactory(new PropertyValueFactory("taiKhoan"));
        colMatKhau.setCellValueFactory(new PropertyValueFactory("matKhau"));
        colQuanLy.setCellValueFactory(new PropertyValueFactory("loaiNV"));

        this.tbNhanVien.getColumns().addAll(colId, colHoNV, colTenNV, colIdChiNhanh, colTaiKhoan, colMatKhau, colQuanLy, colDel);
    }

    public void loaddataNhanVien() throws SQLException {
        NhanVienService nvs = new NhanVienService();
        List<NhanVien> nv = nvs.getNhanVien();

        this.tbNhanVien.getItems().clear();
        this.tbNhanVien.setItems(FXCollections.observableList(nv));

    }

    public List<ChiNhanh> layDSChiNhanh() throws SQLException {
        ChiNhanhService c = new ChiNhanhService();
        return c.getChiNhanh();
    }

    public void addNhanVien(ActionEvent evt) throws SQLException {
        if (!this.txtHoNhanVien.getText().isEmpty() && !this.txtTenNhanVien.getText().isEmpty() && cbChiNhanh.getValue() != null && !this.txtTaiKhoanNhanVien.getText().isEmpty() && !this.txtMatKhauNhanVien.getText().isEmpty() && this.cbLoaiNhanVien.getValue() != null) {
            int idnv = Integer.parseInt(this.txtMaNhanVien.getText());
            String hoNV = this.txtHoNhanVien.getText();
            String tenNV = this.txtTenNhanVien.getText();
            ChiNhanh cn = (ChiNhanh) this.cbChiNhanh.getValue();
            int maChiNhanh = cn.getMaChiNhanh();
            String taiKhoan = this.txtTaiKhoanNhanVien.getText();
            String matKhau = this.txtMatKhauNhanVien.getText();
            String nhanVien = (String) this.cbLoaiNhanVien.getValue();
            boolean loaiNhanVien = true;
            if (nhanVien.equals("Nhân Viên")) {
                loaiNhanVien = false;
            }
            boolean chuaCoTaiKhoan = true;
            for (NhanVien n : this.layDSNhanVien()) {
                if (n.getTaiKhoan().equals(taiKhoan)) {
                    chuaCoTaiKhoan = false;
                }
            }
            if (chuaCoTaiKhoan) {
                NhanVienService nv = new NhanVienService();
                NhanVien n = new NhanVien(idnv, hoNV, tenNV, maChiNhanh,taiKhoan, matKhau, loaiNhanVien);
                boolean kt = nv.addNhanVien(n);
                if (kt) {
                    Alert c = MessageBox.getBox("Thêm nhân viên", "Thêm Nhân Viên Thành Công!!!", Alert.AlertType.CONFIRMATION);
                    c.show();
                    this.resetGiaTri();
                    this.loaddataNhanVien();
                    this.ganIdNhanVienChoTextField();
                }
            } else {
                Alert a = MessageBox.getBox("Thêm nhân viên", "Tên tài khoản đã tồn tại", Alert.AlertType.WARNING);
                a.show();
            }
        } else {
            Alert b = MessageBox.getBox("Thêm nhân viên", "vui lòng nhập đầy đủ thông tin!!!", Alert.AlertType.WARNING);
            b.show();
        }

    }

    public void ganIdNhanVienChoTextField() throws SQLException {
        NhanVienService nv = new NhanVienService();
        List<NhanVien> listnhanvien = nv.getNhanVien();
        int id = listnhanvien.get(listnhanvien.size() - 1).getMaNhanVien() + 1;
        this.txtMaNhanVien.setText(id + "");
    }

    public List<NhanVien> layDSNhanVien() throws SQLException {
        NhanVienService n = new NhanVienService();
        return n.getNhanVien();
    }

    public void resetGiaTri() {
        this.txtMaNhanVien.setText("");
        this.txtHoNhanVien.setText("");
        this.txtTenNhanVien.setText("");
        this.txtTaiKhoanNhanVien.setText("");
        this.txtMatKhauNhanVien.setText("");
    }

    public void suaNhanVien(ActionEvent evt) {
        Object selectedObject = tbNhanVien.getSelectionModel().getSelectedItem();
        if (selectedObject != null) {
            NhanVien k = (NhanVien) selectedObject;
            this.txtMaNhanVien.setText(k.getMaNhanVien() + "");
            this.txtHoNhanVien.setText(k.getHoNV());
            this.txtTenNhanVien.setText(k.getTenNV());
            List<Object> chiNhanh = cbChiNhanh.getItems();

            for (Object c : chiNhanh) {
                ChiNhanh cn = (ChiNhanh) c;
                if (cn.getMaChiNhanh() == k.getIdChiNhanh()) {
                    this.cbChiNhanh.setValue(c);
                }
            }
            this.txtTaiKhoanNhanVien.setText(k.getTaiKhoan());
            this.txtMatKhauNhanVien.setText(k.getMatKhau());
            if (k.isLoaiNV()) {
                this.cbLoaiNhanVien.setValue("Nhân Viên");
            } else {
                this.cbLoaiNhanVien.setValue("Quản Lý");
            }

        } else {
            Alert c = MessageBox.getBox("Chọn Nhân Viên", "Vui lòng chọn khách hàng muốn sửa thông tin trong tableview!", Alert.AlertType.CONFIRMATION);
            c.show();
        }
    }

    public void capNhatNhanVien() throws SQLException {
        if (!this.txtHoNhanVien.getText().isEmpty() && !this.txtTenNhanVien.getText().isEmpty() && cbChiNhanh.getValue() != null && !this.txtTaiKhoanNhanVien.getText().isEmpty() && !this.txtMatKhauNhanVien.getText().isEmpty() && this.cbLoaiNhanVien.getValue() != null) {
            int idnv = Integer.parseInt(this.txtMaNhanVien.getText());
            String hoNV = this.txtHoNhanVien.getText();
            String tenNV = this.txtTenNhanVien.getText();
            ChiNhanh cn = (ChiNhanh) this.cbChiNhanh.getValue();
            int maChiNhanh = cn.getMaChiNhanh();
            String taiKhoan = this.txtTaiKhoanNhanVien.getText();
            String matKhau = this.txtMatKhauNhanVien.getText();
            String nhanVien = (String) this.cbLoaiNhanVien.getValue();
            boolean loaiNhanVien = true;
            if (nhanVien.equals("Nhân Viên")) {
                loaiNhanVien = false;
            }
            boolean chuaCoTaiKhoan = true;
            for (NhanVien n : this.layDSNhanVien()) {
                if (n.equals(taiKhoan)) {
                    chuaCoTaiKhoan = false;
                }
            }
            if (chuaCoTaiKhoan) {
                NhanVienService nv = new NhanVienService();
                NhanVien n = new NhanVien(idnv, hoNV, tenNV, maChiNhanh,taiKhoan, matKhau, loaiNhanVien);
                boolean kt = nv.updateNhanVien(n);
                if (kt) {
                    Alert c = MessageBox.getBox("Cập nhật nhân viên", "Cập nhật thành công!!!", Alert.AlertType.CONFIRMATION);
                    c.show();
                    this.resetGiaTri();
                    this.loaddataNhanVien();
                    this.ganIdNhanVienChoTextField();
                }
            } else {
                Alert a = MessageBox.getBox("Cập nhật nhân viên", "Tên tài khoản đã tồn tại", Alert.AlertType.WARNING);
                a.show();
            }
        } else {
            Alert b = MessageBox.getBox("Cập nhật nhân viên", "vui lòng nhập đầy đủ thông tin!!!", Alert.AlertType.WARNING);
            b.show();
        }

    }
}
