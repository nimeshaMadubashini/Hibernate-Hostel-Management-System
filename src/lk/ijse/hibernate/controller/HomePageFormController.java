package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.ReservationBO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HomePageFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox cmbStudentID;
@FXML
private JFXTextField txtname;
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
        try {
            txtID.setText(reservationBO.getReservationId());
            txtDate.setText(String.valueOf(LocalDate.now()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> idList = reservationBO.loadStudentId();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbStudentID.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void comboRoomEnterOnKeyPress(KeyEvent event) {
String roomId= String.valueOf(cmbRoomId.getValue());

        try {
       RoomDTO roomDTO = reservationBO.findDetail(roomId);
            txtRoomType.setText(roomDTO.getType());
            txtKeyMoney.setText(roomDTO.getKey_money());
            txtAvailableQty.setText(String.valueOf(roomDTO.getQty()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void comboStudentEnterOnKeyPrsse(KeyEvent event) {
String studentId= String.valueOf(cmbStudentID.getValue());
        try {
            StudentDTO studentDTO=reservationBO.findStudent(studentId);
            txtname.setText(studentDTO.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
