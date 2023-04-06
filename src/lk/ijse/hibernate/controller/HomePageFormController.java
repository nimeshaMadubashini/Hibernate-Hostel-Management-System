package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;

public class HomePageFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox<?> cmbStudentId;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXComboBox<?> cmbRoomId;

    @FXML
    private JFXTextField txtRoomType;

    @FXML
    private JFXTextField txtAvailableQty;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField txtKeyMoney;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtDate;



    @FXML
    void clearFormOnAction(ActionEvent event) {

    }


    @FXML
    void reserveRoomOnAction(ActionEvent event) {

    }
    @FXML
    void addSudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.STUDENT,pane);
    }

    @FXML
    void homeOnAtion(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.HOME,pane);
    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.KEY,pane);
    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM,pane);
    }

    @FXML
    void userOnaction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.USER,pane);
    }

}
