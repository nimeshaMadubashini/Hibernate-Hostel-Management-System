package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.StudentBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private TableView<StudentDTO> tblView;
    @FXML
    private JFXComboBox comboid;
    @FXML
    private TableColumn tblcolId;

    @FXML
    private TableColumn colname;

    @FXML
    private TableColumn colAdd;

    @FXML
    private TableColumn colContctNum;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colGender;
    @FXML
    private JFXRadioButton rdbMale;

    @FXML
    private JFXRadioButton rdbFemale;
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);
    int myIndex;

    public void initialize() {
        loadStudentId();
        try {
            table();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
        StudentDTO studentDTO = new StudentDTO(id, name, add, contact, dob, gender);
        try {
            boolean isAdd = studentBO.save(studentDTO);
            if (isAdd) {
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
                table();
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
    void loqadOnAction(ActionEvent event) throws Exception {
        comboid.setValue("");
        table();
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

    @FXML
    void searchIdOnMouseClicked(MouseEvent event) throws Exception {
        String id = String.valueOf(comboid.getValue());
        List<StudentDTO> list = studentBO.findStudent(id);
        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();
        for (StudentDTO s : list) {
            observableList.add(new StudentDTO(s.getStudent_id(), s.getName(), s.getAddress(), s.getContact_no(), s.getDob(), s.getGender()));
        }
        tblView.setItems(observableList);
        tblcolId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContctNum.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

    public void table() throws Exception {
        List<StudentDTO> list = studentBO.loadAllStudent();
        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();
        for (StudentDTO s : list) {
            observableList.add(new StudentDTO(s.getStudent_id(), s.getName(), s.getAddress(), s.getContact_no(), s.getDob(), s.getGender()));
        }
        tblView.setItems(observableList);
        tblcolId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContctNum.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblView.setRowFactory(tv -> {
            TableRow<StudentDTO> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {

                    myIndex = tblView.getSelectionModel().getSelectedIndex();
                    txtStudentId.setText(tblView.getItems().get(myIndex).getStudent_id());
                    txtName.setText(tblView.getItems().get(myIndex).getName());
                    txtAddress.setText(tblView.getItems().get(myIndex).getAddress());
                    txtContact.setText(tblView.getItems().get(myIndex).getContact_no());
                    txtdate.setValue(tblView.getItems().get(myIndex).getDob());
                    rdbFemale.setText(tblView.getItems().get(myIndex).getGender());
                    rdbMale.setText(tblView.getItems().get(myIndex).getGender());


                }
            });
            return myRow;
        });

    }

    private void loadStudentId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> idList = studentBO.loadStudentId();

            for (String id : idList) {
                observableList.add(id);
            }
            comboid.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
