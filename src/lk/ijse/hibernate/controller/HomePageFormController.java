package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.ReservationBO;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.util.List;

public class HomePageFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox cmbStudentId;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXComboBox cmbRoomId;

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
ReservationBO reservationBO= (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.RESERVATION);

    public void initialize() {
    loadRoomId();
    loadStudentId();
    }


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

    public void loadRoomId(){
        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();

            List<String> idList=reservationBO.loadRoomId();
            for (String id:idList) {
                observableList.add(id);

            }
            cmbRoomId.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void loadStudentId() {
        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();

            List<String> idList=reservationBO.loadStudentId();
            for (String id:idList) {
                observableList.add(id);

            }
            cmbStudentId.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
