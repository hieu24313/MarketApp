/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmh.marketapp;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.GiamGia;
import com.nmh.pojo.SanPham;
import com.nmh.services.ChiNhanhService;
import com.nmh.services.GiamGiaService;
import com.nmh.services.SanPhamService;
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
public class QuanLySanPham {

    @FXML
    private Button btnThoat;
    @FXML
    private TableView tbSanPham;
    @FXML
    private TextField txtMaSanPham;
    @FXML
    private TextField txtTenSanPham;
    @FXML
    private TextField txtGiaSP;
    @FXML
    private TextField txtDonVi;
    @FXML
    private TextField txtXuatXu;
    @FXML
    private ComboBox cbMaGiamGia;
    @FXML
    private ComboBox cbMaChiNhanh;
    @FXML
    private TextField txtSearch;

    public void initialize() throws SQLException {
        this.txtMaSanPham.setDisable(true);
        this.cbMaGiamGia.setItems(FXCollections.observableList(this.loadMaGiamGia()));
        this.cbMaChiNhanh.setItems(FXCollections.observableList(this.loadMaChiNhanh()));
        this.loadTableColumn();
        this.loadDaTaSanPham();
        this.ganIdChoTextField();
        this.txtSearch.textProperty().addListener(e -> {

            this.tbSanPham.getItems().clear();
            try {
                this.loadDaTaSanPham(this.txtSearch.getText());
            } catch (SQLException ex) {

                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public List<GiamGia> loadMaGiamGia() throws SQLException {
        GiamGiaService gg = new GiamGiaService();
        return gg.getGiamGia();
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

    public void loadTableColumn() {
        TableColumn colId = new TableColumn("Mã Sản Phẩm");
        TableColumn colTen = new TableColumn("Tên Sản Phẩm");
        TableColumn colGia = new TableColumn("Giá Sản Phẩm");
        TableColumn colDonVi = new TableColumn("Đơn Vị");
        TableColumn colXuatXu = new TableColumn("Xuất Xứ");
        TableColumn colChiNhanh = new TableColumn("Mã Chi Nhánh");
        TableColumn colGiamGia = new TableColumn("Mã Giảm Giá");
        TableColumn colDel = new TableColumn();
        colDel.setCellFactory(r -> {
            Button btn = new Button("Xóa Sản Phẩm");

            btn.setOnAction(evt -> {
                Alert a = MessageBox.getBox("Sản Phẩm",
                        "Bạn muốn xóa Sản Phẩm này đúng không?",
                        Alert.AlertType.CONFIRMATION);
                a.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
                        Button b = (Button) evt.getSource();
                        TableCell cell = (TableCell) b.getParent();
                        SanPhamService sp = new SanPhamService();
                        SanPham q = (SanPham) cell.getTableRow().getItem();
                        try {
                            if (sp.deleteSanPham(q.getIdSanPham())) {
                                MessageBox.getBox("Sản Phẩm", "Xóa Thành Công!!!", Alert.AlertType.INFORMATION).show();
                                this.loadDaTaSanPham();
                                this.ganIdChoTextField();
                            } else {
                                MessageBox.getBox("Sản Phẩm", "Xóa Thất Bại!!!", Alert.AlertType.WARNING).show();
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Sản Phẩm", ex.getMessage(), Alert.AlertType.WARNING).show();
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            });

            TableCell c = new TableCell();
            c.setGraphic(btn);
            return c;
        });

        colId.setCellValueFactory(new PropertyValueFactory("idSanPham"));
        colTen.setCellValueFactory(new PropertyValueFactory("tenSP"));
        colGia.setCellValueFactory(new PropertyValueFactory("giaSP"));
        colDonVi.setCellValueFactory(new PropertyValueFactory("donVi"));
        colXuatXu.setCellValueFactory(new PropertyValueFactory("xuatXu"));
        colChiNhanh.setCellValueFactory(new PropertyValueFactory("idChiNhanh"));
        colGiamGia.setCellValueFactory(new PropertyValueFactory("idGiamGia"));

        this.tbSanPham.getColumns().addAll(colId, colTen, colGia, colDonVi, colXuatXu, colGiamGia, colChiNhanh, colDel);
    }

    public void loadDaTaSanPham() throws SQLException {
        SanPhamService sp = new SanPhamService();
        List<SanPham> s = sp.getSanPham();

        this.tbSanPham.setItems(FXCollections.observableList(s));
    }

    public void loadDaTaSanPham(String ten) throws SQLException {
        SanPhamService sp = new SanPhamService();
        List<SanPham> s = sp.getSanPham(ten);

        this.tbSanPham.setItems(FXCollections.observableList(s));
    }

    public void ganIdChoTextField() throws SQLException {
        SanPhamService sp = new SanPhamService();
        List<SanPham> s = sp.getSanPham();
        this.txtMaSanPham.setText(s.get(s.size() - 1).getIdSanPham() + 1 + "");
    }

    public void themSanPham(ActionEvent evt) throws SQLException {
        Double Gia = Double.valueOf(this.txtGiaSP.getText());
        if (Gia > 0 && !this.txtTenSanPham.getText().isEmpty() && !this.txtGiaSP.getText().isEmpty() && !this.txtDonVi.getText().isEmpty() && !this.txtXuatXu.getText().isEmpty() && this.cbMaGiamGia.getValue() != null) {

            
            try {
                int idSP = Integer.parseInt(this.txtMaSanPham.getText());
                String tenSP = this.txtTenSanPham.getText();
                
                String DonVi = this.txtDonVi.getText();
                String XuatXu = this.txtXuatXu.getText();
                GiamGia g = (GiamGia) this.cbMaGiamGia.getValue();
                int idGiamGia = g.getIdGiamGia();

                ChiNhanh cn = (ChiNhanh) this.cbMaChiNhanh.getValue();
                int idChiNhanh = cn.getMaChiNhanh();

                SanPhamService sp = new SanPhamService();
                SanPham s = new SanPham(idSP, tenSP, Gia, DonVi, XuatXu, idChiNhanh, idGiamGia);
                boolean kt = sp.addSanPham(s);
                if (kt) {
                    Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Thêm Sản Phẩm Thành Công!!!", Alert.AlertType.CONFIRMATION);
                    a.show();
                    this.loadDaTaSanPham();
                    this.resetGiaTri();
                    this.ganIdChoTextField();
                } else {
                    Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Thêm Sản Phẩm Thất Bại!!!", Alert.AlertType.CONFIRMATION);
                    a.show();
                }
            } catch (NumberFormatException ex) {
                Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Vui lòng chỉ nhập số vào ô giá trị!!!!", Alert.AlertType.CONFIRMATION);
                a.show();
            }

        } else {
            Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Vui lòng nhập đầy đủ và đúng thông tin!!!", Alert.AlertType.CONFIRMATION);
            a.show();
        }

    }

    public void resetGiaTri() {
        this.txtDonVi.setText("");
        this.txtTenSanPham.setText("");
        this.txtGiaSP.setText("");
        this.txtXuatXu.setText("");
        this.cbMaGiamGia.setValue(null);
        this.cbMaChiNhanh.setValue(null);
    }

    public List<ChiNhanh> loadMaChiNhanh() throws SQLException {
        ChiNhanhService s = new ChiNhanhService();
        return s.getChiNhanh();

    }

    public void suaSanPham(ActionEvent evt) throws SQLException {
        Object selectedObject = tbSanPham.getSelectionModel().getSelectedItem();

        if (selectedObject != null) {

            SanPham p = (SanPham) selectedObject;
            this.txtMaSanPham.setText(p.getIdSanPham() + "");
            this.txtTenSanPham.setText(p.getTenSP());
            this.txtGiaSP.setText(String.format("%.1f", p.getGiaSP()));
            this.txtDonVi.setText(p.getDonVi());
            this.txtXuatXu.setText(p.getXuatXu());

            int idChiNhanhTBview = p.getIdChiNhanh();
            List<Object> s = this.cbMaChiNhanh.getItems();
            for (Object b : s) {
                ChiNhanh ps = (ChiNhanh) b;
                if (ps.getMaChiNhanh() == idChiNhanhTBview) {
                    this.cbMaChiNhanh.setValue(b);
                }
            }

            List<Object> c = this.cbMaGiamGia.getItems();
            int idGiamGiaTBview = p.getIdGiamGia();
            for (Object g : c) {
                GiamGia ps = (GiamGia) g;
                if (ps.getIdGiamGia() == idGiamGiaTBview) {
                    this.cbMaGiamGia.setValue(g);
                }
            }

        }
        else{
            Alert c = MessageBox.getBox("Sửa Thông Tin Sản Phẩm", "Vui lòng chọn sản phẩm muốn sửa trong tableview!!!", Alert.AlertType.WARNING);
            c.show();
        }
    }

    public void updateSanPham(ActionEvent evt) throws SQLException {
        
        Double Gia = Double.valueOf(this.txtGiaSP.getText());
        
        if (Gia > 0 && !this.txtTenSanPham.getText().isEmpty() && !this.txtGiaSP.getText().isEmpty() && !this.txtDonVi.getText().isEmpty() && !this.txtXuatXu.getText().isEmpty() && this.cbMaGiamGia.getValue() != null) {
            try {
                int idSP = Integer.parseInt(this.txtMaSanPham.getText());
                String tenSP = this.txtTenSanPham.getText();
                
                String DonVi = this.txtDonVi.getText();
                String XuatXu = this.txtXuatXu.getText();
                GiamGia g = (GiamGia) this.cbMaGiamGia.getValue();
                int idGiamGia = g.getIdGiamGia();

                ChiNhanh cn = (ChiNhanh) this.cbMaChiNhanh.getValue();
                int idChiNhanh = cn.getMaChiNhanh();

                SanPhamService sp = new SanPhamService();
                SanPham s = new SanPham(idSP, tenSP, Gia, DonVi, XuatXu, idChiNhanh, idGiamGia);
                boolean kt = sp.updateSanPham(s);
                if (kt) {
                    Alert a = MessageBox.getBox("Cập Nhật Sản Phẩm", "Cập Nhật Sản Phẩm Thành Công!!!", Alert.AlertType.CONFIRMATION);
                    a.show();
                    this.loadDaTaSanPham();
                    this.resetGiaTri();
                    this.ganIdChoTextField();
                } else {
                    Alert a = MessageBox.getBox("Cập Nhật Sản Phẩm", "Cập Nhật Sản Phẩm Thất Bại!!!", Alert.AlertType.CONFIRMATION);
                    a.show();
                }
            } catch (NumberFormatException ex) {
                Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Vui lòng chỉ nhập số vào ô giá trị!!!!", Alert.AlertType.CONFIRMATION);
                a.show();
            }
        } else {
            Alert a = MessageBox.getBox("Cập Nhật Sản Phẩm", "Vui lòng nhập đầy đủ và đúng thông tin!!!", Alert.AlertType.CONFIRMATION);
            a.show();
        }
    }
}
