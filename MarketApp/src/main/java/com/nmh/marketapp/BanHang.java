package com.nmh.marketapp;

import com.nmh.pojo.ChiNhanh;
import com.nmh.pojo.ChiTietHoaDon;
import com.nmh.pojo.GiamGia;
import com.nmh.pojo.HoaDon;
import com.nmh.pojo.KhachHang;
import com.nmh.pojo.NhanVien;
import com.nmh.pojo.SanPham;
import com.nmh.services.ChiNhanhService;
import com.nmh.services.ChiTietHoaDonService;
import com.nmh.services.GiamGiaService;
import com.nmh.services.HoaDonService;
import com.nmh.services.KhachHangService;
import com.nmh.services.SanPhamService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.nmh.utils.MessageBox;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class BanHang {

    SanPhamService p = new SanPhamService();
    @FXML
    private TableView tbProc;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView tbHoaDon;
    @FXML
    private TextField txtTinhTienDu;
    @FXML
    private Button btnLuuHoaDon;
    @FXML
    private TextField txtTienKHDua;
    @FXML
    private TextField txtTong;
    @FXML
    private Label lbTenNV;
    @FXML
    private Label lbMaNV;
    @FXML
    private Label lbMaChiNhanh;
    @FXML
    private Label lbTenChiNhanh;
    @FXML
    private TextField txtMaKH;
    @FXML
    private Button btnDangXuat;

    public void initialize() throws SQLException {

        this.txtTinhTienDu.setDisable(true);
        this.txtTong.setDisable(true);
        this.loadTableColumnProc();
        this.loadTableColumnHD();
        this.tbHoaDon.setEditable(true);
        this.btnLuuHoaDon.setDisable(true);

        this.txtTienKHDua.textProperty().addListener(e -> {

            double thanhTien = Double.parseDouble(this.txtTong.getText());
            double soTienNhan = Double.parseDouble(txtTienKHDua.getText());
            txtTinhTienDu.setText(" " + (soTienNhan - thanhTien));

        });

        UnaryOperator<Change> filter = change -> {
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
        TextFormatter<String> formatter1 = new TextFormatter<>(filter);
        this.txtTienKHDua.setTextFormatter(formatter);
        this.txtMaKH.setTextFormatter(formatter1);
        this.txtSearch.setDisable(true);
        this.txtSearch.textProperty().addListener(e -> {
            try {
                int idchinhanh = Integer.parseInt(this.lbMaChiNhanh.getText());
                this.tbProc.getItems().clear();
                this.loadSP(this.txtSearch.getText(), idchinhanh);

            } catch (SQLException ex) {
                Logger.getLogger(DangNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void DiemDanh(ActionEvent event) throws SQLException {
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage currentStage = (Stage) currentScene.getWindow();
        NhanVien nv = (NhanVien) currentStage.getUserData();
        ChiNhanhService c = new ChiNhanhService();
        List<ChiNhanh> ch = c.getChiNhanh(nv.getIdChiNhanh());
        for (ChiNhanh ch1 : ch) {
            this.lbTenChiNhanh.setText(ch1.getDiaChi());
        }

        this.btnLuuHoaDon.setDisable(false);
        this.lbMaNV.setText(nv.getMaNhanVien() + "");
        this.lbMaChiNhanh.setText(nv.getIdChiNhanh() + "");
        this.lbTenNV.setText(nv.getHoNV() + " " + nv.getTenNV());
        int idchinhanh = Integer.parseInt(this.lbMaChiNhanh.getText());
        this.loadSP(idchinhanh);
        this.txtSearch.setDisable(false);

        Alert a = MessageBox.getBox("Điểm Danh Thành Công!!", "Chào, " + nv.getHoNV() + " " + nv.getTenNV(), Alert.AlertType.CONFIRMATION);
        a.show();
    }

    @FXML
    public void LuuHoaDon(ActionEvent event) throws SQLException {

        ObservableList<SanPham> allData = FXCollections.observableArrayList();
        allData.addAll(tbHoaDon.getItems()); //lấy all sản phẩm trong hóa đơn

        if (!this.txtTienKHDua.getText().isEmpty()) { //
            if (allData.isEmpty()) { //kiểm tra hóa đơn có null không
                Alert c = MessageBox.getBox("Cảnh Báo", "Vui lòng thêm sản phẩm vào hóa đơn!!", Alert.AlertType.WARNING);
                c.show();
            } else {
                int idKH = 1;
                KhachHangService kh = new KhachHangService();
                List<KhachHang> listkh = kh.getKhachHang();
                if (this.txtMaKH.getText().isEmpty()) {
                    idKH = 1;

                    boolean kt = ThemHoaDonVaoDB(idKH); //kiểm tra có add hóa đơn thành công không
                    if (kt) {
                        int idchinhanh1 = Integer.parseInt(this.lbMaChiNhanh.getText());
                        this.loadSP(idchinhanh1); // load lại sản phẩm
                        this.resetGiaTri();

                        Alert a = MessageBox.getBox("Thêm Hóa Đơn", "Thành Công!!!", Alert.AlertType.CONFIRMATION);
                        a.show();

                    } else {
                        Alert a1 = MessageBox.getBox("Thêm Hóa Đơn", "Thất Bại!!!", Alert.AlertType.CONFIRMATION);
                        a1.show();
                    }
                } else {
                    int kiemtraidKH = Integer.parseInt(this.txtMaKH.getText()); // lấy mã KH
                    for (KhachHang k : listkh) {
                        if (k.getIdKH() == kiemtraidKH) {
                            idKH = kiemtraidKH;

                            boolean kt = this.ThemHoaDonVaoDB(idKH); //kiểm tra có add hóa đơn thành công không
                            if (kt) {
                                int idchinhanh1 = Integer.parseInt(this.lbMaChiNhanh.getText());
                                this.loadSP(idchinhanh1); // load lại sản phẩm
                                this.loadTableColumnHD();
                                this.resetGiaTri();

                                Alert a = MessageBox.getBox("Thêm Hóa Đơn", "Thành Công!!!", Alert.AlertType.CONFIRMATION);
                                a.show();

                            } else {
                                Alert a1 = MessageBox.getBox("Thêm Hóa Đơn", "Thất Bại!!!", Alert.AlertType.CONFIRMATION);
                                a1.show();
                            }
                        }
                    }
                    if (idKH == 1) {
                        Alert a = MessageBox.getBox("Sai mã khách hàng", "Mã khách hàng sai hoặc không tồn tại!!", Alert.AlertType.WARNING);
                        a.show();
                    }

                }

            }
        } else {
            Alert a2 = MessageBox.getBox("Cảnh báo", "Vui lòng nhập số tiền khách đưa", Alert.AlertType.CONFIRMATION);
            a2.show();
        }

    }

    public boolean ThemHoaDonVaoDB(int MaKH) throws SQLException {
        List<ChiTietHoaDon> cthd = new ArrayList<>(); // Tạo ra list để lưu chi tiết sản phẩm trong hóa đơn
        ObservableList<SanPham> allData = FXCollections.observableArrayList();
        allData.addAll(tbHoaDon.getItems()); //lấy all sản phẩm trong hóa đơn
        HoaDonService hds = new HoaDonService(); //Tạo hóa đơn service
        ChiTietHoaDonService ct = new ChiTietHoaDonService(); //Tạo chi tiết hóa đơn service
        int idhd = hds.getHoaDon().get(hds.getHoaDon().size() - 1).getIdHoaDon() + 1; //lấy id hóa đơn cuối cùng +1
        int idcthd = ct.getChiTietHoaDon().get(ct.getChiTietHoaDon().size() - 1).getIdChiTietHD() + 1; //lấy id chi tiết hóa đơn cuối +1
        int idchinhanh = Integer.parseInt(this.lbMaChiNhanh.getText()); //lấy id chi nhánh
        int idNV = Integer.parseInt(this.lbMaNV.getText());//lấy id nhân viên
        int idKH = MaKH;

        double tong = Double.parseDouble(this.txtTong.getText()); //lấy tổng tiền
        double tienKHDua = Double.parseDouble(this.txtTienKHDua.getText()); //lấy tiền kh đưa được nhập vào
        java.time.LocalDate localDate = java.time.LocalDate.now();
        java.sql.Date NgayTao = java.sql.Date.valueOf(localDate);///tạo và ép kiểu ngày giờ tạo hóa đơn

        HoaDon s = new HoaDon(idhd, idchinhanh, idNV, idKH, tong, tienKHDua, NgayTao); //tạo hóa đơn để lưu

        for (SanPham c : allData) { // vòng for này để duyệt qua và lấy chi tiết của hóa đơn từ sản phẩm trong hóa đơn
            double thanhtien = c.getGiaSP() * c.getSoluong(); //thành tiền = số lượng * giá 1 sản phẩm
            cthd.add(new ChiTietHoaDon(idcthd, idhd, c.getIdSanPham(), c.getSoluong(), thanhtien));// thêm vào list chi tiết hóa đơn để lưu
            idcthd++;
        }

        return hds.addHoaDon(s, cthd);
    }

    public void resetGiaTri() {
        this.txtMaKH.setText("");
        this.txtTienKHDua.setText("");
        this.txtTinhTienDu.setText("");
    }

    public void loadTableColumnProc() throws SQLException {

        TableColumn colId = new TableColumn("Mã Sản Phẩm");
        TableColumn colTen = new TableColumn("Tên Sản Phẩm");
        TableColumn colGia = new TableColumn("Giá(VNĐ)");
        TableColumn colDon = new TableColumn("Đơn Vị");
        TableColumn colXuatXu = new TableColumn("Xuất Xứ");
        TableColumn colGiamGia = new TableColumn("Mã Giảm Giá");
        TableColumn colChiNhanh = new TableColumn("Mã Chi Nhánh");

        colId.setCellValueFactory(new PropertyValueFactory("idSanPham"));
        colTen.setCellValueFactory(new PropertyValueFactory("tenSP"));
        colGia.setCellValueFactory(new PropertyValueFactory("giaSP"));
        colXuatXu.setCellValueFactory(new PropertyValueFactory("xuatXu"));
        colDon.setCellValueFactory(new PropertyValueFactory("donVi"));
        colChiNhanh.setCellValueFactory(new PropertyValueFactory("idChiNhanh"));
        colGiamGia.setCellValueFactory(new PropertyValueFactory("idGiamGia"));

        this.tbProc.getColumns().addAll(colId, colTen, colDon, colGia, colXuatXu, colChiNhanh, colGiamGia);
    }

    public void loadTableColumnHD()  throws SQLException {

        TableColumn colId1 = new TableColumn("Mã Sản Phẩm");
        TableColumn colTen1 = new TableColumn("Tên Sản Phẩm");
        TableColumn colGia1 = new TableColumn("Giá(VNĐ)");
        TableColumn colDon1 = new TableColumn("Đơn Vị");
        TableColumn colChiNhanh1 = new TableColumn("Mã Chi Nhánh");
        TableColumn colXuatXu1 = new TableColumn("Xuất Xứ");
        TableColumn colGiamGia1 = new TableColumn("Mã Giảm Giá");

        colId1.setCellValueFactory(new PropertyValueFactory("idSanPham"));
        colTen1.setCellValueFactory(new PropertyValueFactory("tenSP"));
        colGia1.setCellValueFactory(new PropertyValueFactory("giaSP"));
        colXuatXu1.setCellValueFactory(new PropertyValueFactory("xuatXu"));
        colDon1.setCellValueFactory(new PropertyValueFactory("donVi"));
        colChiNhanh1.setCellValueFactory(new PropertyValueFactory("idChiNhanh"));
        colGiamGia1.setCellValueFactory(new PropertyValueFactory("idGiamGia"));

        TableColumn<SanPham, Double> colSoluong = new TableColumn<>("Số Lượng");
        colSoluong.setCellValueFactory(new PropertyValueFactory<>("soluong"));

        colSoluong.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colSoluong.setOnEditCommit((TableColumn.CellEditEvent<SanPham, Double> event) -> {
            try {
                SanPham sp = event.getTableView().getItems().get(event.getTablePosition().getRow());
                sp.setSoluong(event.getNewValue());
                this.txtTong.setText(tinhTong() + " ");
            } catch (SQLException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.tbHoaDon.getColumns().addAll(colSoluong, colId1, colTen1, colDon1, colGia1, colXuatXu1, colChiNhanh1, colGiamGia1);
    }

    public void loadSP() throws SQLException {
        List<SanPham> proc = p.getSanPham();

        this.tbProc.getItems().clear();
        this.tbProc.setItems(FXCollections.observableList(proc));

    }

    public void loadSP(int idchinhanh) throws SQLException {
        List<SanPham> proc = p.getSanPham(idchinhanh);

//        this.tbProc.getItems().clear();
        this.tbProc.setItems(FXCollections.observableList(proc));

    }

    public void loadSP(String kw) throws SQLException {
        List<SanPham> proc = p.getSanPham(kw);

        this.tbProc.getItems().clear();
        this.tbProc.setItems(FXCollections.observableList(proc));

    }

    public void loadSP(String kw, int idchinhanh) throws SQLException {
        List<SanPham> proc = p.getSanPham(kw, idchinhanh);

        this.tbProc.getItems().clear();
        this.tbProc.setItems(FXCollections.observableList(proc));

    }

    public void themSPVaoHD(ActionEvent evt) throws SQLException {
        SanPham selectedObject = (SanPham) tbProc.getSelectionModel().getSelectedItem();

        if (selectedObject != null) {
            GiamGiaService loadKM = new GiamGiaService();
            List<GiamGia> s = loadKM.getGiamGia();
            SanPham kt = (SanPham) selectedObject;
            if (kt.getIdGiamGia() != 0) {
                Date date = Date.valueOf(LocalDate.now());
                for (GiamGia g : s) {

                    if (date.compareTo(g.getTgBatDau()) > 0 && date.compareTo(g.getTgKetThuc()) < 0) {
                        if (g.getIdGiamGia() == kt.getIdGiamGia()) {
                            Double newGia = kt.getGiaSP() - kt.getGiaSP() * g.getGiaTri();
                            kt.setGiaSP(newGia);
                        }
                    }
                }
            }

            tbHoaDon.getItems().add(selectedObject);
            tbProc.getItems().remove(selectedObject);
            this.txtTong.setText(tinhTong() + "");
        } else {
            Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Vui lòng chọn sản phẩm", Alert.AlertType.WARNING);
            a.show();
        }
    }

    public void XoaSPKhoiHD(ActionEvent evt) throws SQLException {
        Object selectedObject = tbHoaDon.getSelectionModel().getSelectedItem();

        SanPhamService sp = new SanPhamService();

        if (selectedObject != null) {
            SanPham s = (SanPham) selectedObject;
            s.setSoluong(1);
            for (SanPham q : sp.getSanPham()) {
                if (q.getIdSanPham() == s.getIdSanPham()) {
                    s.setGiaSP(q.getGiaSP());
                }
            }
            this.tbProc.getItems().add(s);
            this.tbHoaDon.getItems().remove(s);
            this.txtTong.setText(tinhTong() + "");

        } else {
            Alert a = MessageBox.getBox("Thêm Sản Phẩm", "Vui lòng chọn sản phẩm", Alert.AlertType.WARNING);
            a.show();
        }
    }

    public long tinhTong() throws SQLException {
        ObservableList<SanPham> allData = FXCollections.observableArrayList();
        allData.addAll(tbHoaDon.getItems());
        long tong = 0;
        for (SanPham c : allData) {
            tong += c.getGiaSP() * c.getSoluong();
        }
        boolean ktkm = false;
        if (!this.txtMaKH.getText().isEmpty()) {
            int idkh = Integer.parseInt(this.txtMaKH.getText());
            KhachHangService kh = new KhachHangService();
            List<KhachHang> k;
            k = kh.getKhachHang();
            for (KhachHang c : k) {

                if (c.getIdKH() == idkh) {
                    LocalDate hientai = LocalDate.now();
                    if (hientai.compareTo(c.getNgaySinh().toLocalDate()) == 0) {
                        ktkm = true;
                        break;
                    }
                }
            }
        }
        if(ktkm && tong > 1000000){
            tong = (long) (tong - tong*0.1);
        }
        return tong;
    }

    public void TraCuuKH(ActionEvent evt) throws IOException {

        String nf = "TraCuuKH.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Tra Cứu Khách Hàng");
        formStage.show();
    }

    public void DangXuat(ActionEvent evt) throws IOException {
        String nf = "DangNhap.fxml";
        Parent form = FXMLLoader.load(getClass().getResource(nf));
        Scene formScene = new Scene(form);
        Stage formStage = new Stage();
        formStage.setScene(formScene);
        formStage.setTitle("Trang Đăng Nhập");
        formStage.setResizable(false);
        formStage.show();
        Stage oldStage = (Stage) btnDangXuat.getScene().getWindow();
        oldStage.close();
    }

}
