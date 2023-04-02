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
import javafx.scene.control.TextField;
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
