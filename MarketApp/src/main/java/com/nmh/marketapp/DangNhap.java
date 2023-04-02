package com.nmh.marketapp;


import com.nmh.pojo.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.nmh.services.NhanVienService;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class DangNhap {
    

    NhanVienService n = new NhanVienService();
    @FXML private TextField txtTaiKhoan;
    @FXML private TextField txtMatKhau;
    @FXML private Button btnDangNhap;
    
public void initialize(URL url, ResourceBundle rb) throws SQLException {

}

    public void kiemTraDangNhap(ActionEvent evt) throws SQLException, IOException{

        String tk = this.txtTaiKhoan.getText();
        String pw = this.txtMatKhau.getText();
        List<NhanVien> c = n.getNhanVien();
        
        for (NhanVien nv : c) {
            if(nv.getTaiKhoan().equals(tk) && nv.getMatKhau().equals(pw))
            {
                //gọi form, kiểm tra role nếu là quản lý
                if(nv.isLoaiNV()==true) {
                   String nf = "QuanLy.fxml";
                   Parent form = FXMLLoader.load(getClass().getResource(nf));
                   Scene formScene = new Scene(form);
                   Stage formStage = new Stage();
                   formStage.setScene(formScene);
                   formStage.setTitle("Trang Quản Lý");
                   formScene.setUserData(nv);
                   formStage.setUserData(nv);
                   formStage.show();
                   Stage oldStage = (Stage) btnDangNhap.getScene().getWindow();
                   oldStage.close();
                   break;

                    //gọi form cho quản lý
                }
                else {
//                   String tenNV = nv.getHoNV() + nv.getTenNV();
                   String nf = "BanHang.fxml";
                   Parent form = FXMLLoader.load(getClass().getResource(nf));
                   Scene formScene = new Scene(form);
                   Stage formStage = new Stage();
                   formStage.setScene(formScene);
                   formStage.setTitle("Trang Bán Hàng");
                   formStage.setUserData(nv);
                   formScene.setUserData(nv);
                   formStage.show();
                   Stage oldStage = (Stage) btnDangNhap.getScene().getWindow();
                   oldStage.close(); 
                   break;
                     

                    
                }
                
            }
        }
    }
}




