package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.StudentBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.time.LocalDate;

public class StudentManageFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXDatePicker txtdate;
    @FXML
    private Label lblid;

    @FXML
    private Label lblname;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact;

    @FXML
    private TableView<?> tblView;

    @FXML
    private TableColumn<?, ?> tblcolId;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> colAdd;

    @FXML
    private TableColumn<?, ?> colContctNum;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private JFXRadioButton rdbMale;

    @FXML
    private JFXRadioButton rdbFemale;
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);

    @FXML
    void addOnAction(ActionEvent event) {
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String add = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate dob = txtdate.getValue();
        String gender = null;
        if (rdbFemale.isSelected()) {
            gender = rdbFemale.getText();
        } else if (rdbMale.isSelected()) {
            gender = rdbMale.getText();
        }
        StudentDTO studentDTO=new StudentDTO(id,name,add,contact,dob,gender);
        try {
            boolean isAdd = studentBO.save(studentDTO);
            if(isAdd){
                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " User Account Create Successful";
                Notification.showNotification(url, title, text);
                txtStudentId.setText("");
                txtName.setText("");
                txtdate.setValue(LocalDate.now());
                txtAddress.setText("");
                txtContact.setText("");
                rdbMale.setText("");
                rdbFemale.setText("");
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "User Account Create UnSuccessful";
                Notification.showNotification(url, title, text);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void addOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void addressOnKeyPress(KeyEvent event) {

    }

    @FXML
    void contactOnKeyPress(KeyEvent event) {

    }

    @FXML
    void contactOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void homeOnAtion(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.HOME, pane);
    }

    @FXML
    void idOnKeyPress(KeyEvent event) {

    }

    @FXML
    void idOnkeyRelease(KeyEvent event) {

    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.KEY, pane);
    }

    @FXML
    void nameOnKeyPress(KeyEvent event) {

    }

    @FXML
    void nameOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM, pane);
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    @FXML
    void userOnaction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.USER, pane);
    }

}
