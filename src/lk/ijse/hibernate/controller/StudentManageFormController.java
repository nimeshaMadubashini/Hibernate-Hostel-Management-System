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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Label lblGender;

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
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher addressMatcher;
    private Matcher teleNumMatcher;


    private void setPattern() {
        Pattern idPattern = Pattern.compile("^(S)([0-9]{1})|([0-9]{1,})$");
        idMatcher = idPattern.matcher(txtStudentId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern addressPattern = Pattern.compile("^([a-zA-Z0-9\\/,\\s])+([a-zA-z\\s,])+([a-zA-z])$");
        addressMatcher = addressPattern.matcher(txtAddress.getText());

        Pattern teleNumPattern = Pattern.compile("^(074|075|072|077|071|078|047|011)([0-9]{7}$)");
        teleNumMatcher = teleNumPattern.matcher(txtContact.getText());
    }

    public void initialize() {
        setPattern();
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
            if (idMatcher.matches()) {
                if (nameMatcher.matches()) {
                        if (addressMatcher.matches()) {
                            if (teleNumMatcher.matches()) {
                                if (rdbFemale.isSelected() || rdbMale.isSelected()) {
                                    boolean isAdd = studentBO.save(studentDTO);
                                    if (isAdd) {
                                        String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                                        String title = "Successful!";
                                        String text = " Student Add  Successful";
                                        Notification.showNotification(url, title, text);
                                        txtStudentId.setText("");
                                        txtName.setText("");
                                        txtdate.setValue(LocalDate.now());
                                        txtAddress.setText("");
                                        txtContact.setText("");
                                        rdbMale.setSelected(false);
                                        rdbFemale.setSelected(false);
                                        table();
                                        loadStudentId();
                                    } else {
                                        String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                                        String title = "UnSuccessful";
                                        String text = "Student Add  UnSuccessful";
                                        Notification.showNotification(url, title, text);
                                    }
                                }else {
                                    lblGender.setText("Select one");
                                }
                            } else {
                                txtContact.requestFocus();
                                txtContact.setFocusColor(Paint.valueOf("Red"));
                                lblContact.setText("invalid TelePhone Number");
                            }
                        } else {
                            txtAddress.requestFocus();
                            txtAddress.setFocusColor(Paint.valueOf("Red"));
                            lblAddress.setText("invalid address");
                        }
            } else {
                txtName.requestFocus();
                txtName.setFocusColor(Paint.valueOf("Red"));
                lblname.setText("invalid name Type");

            }
        } else {
            txtStudentId.requestFocus();
            txtStudentId.setFocusColor(Paint.valueOf("Red"));
            lblid.setText("invalid Student id");

        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void addOnKeyRelease(KeyEvent event) {
        lblAddress.setText("");
        txtAddress.setFocusColor(Paint.valueOf("Blue"));
        Pattern namePattern = Pattern.compile("^([a-zA-Z0-9\\/,\\s])+([a-zA-z\\s,])+([a-zA-z])$");
        addressMatcher = namePattern.matcher(txtAddress.getText());

        if (!addressMatcher.matches()) {
            txtAddress.requestFocus();
            txtAddress.setFocusColor(Paint.valueOf("Red"));
            lblAddress.setText("Invalid   Address");
        }
    }

    @FXML
    void addressOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtContact.requestFocus();
        }
    }

    @FXML
    void contactOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtdate.requestFocus();
        }
    }

    @FXML
    void contactOnKeyReleased(KeyEvent event) {
        lblContact.setText("");
        txtContact.setFocusColor(Paint.valueOf("Blue"));
        Pattern namePattern = Pattern.compile("^(074|075|072|077|071|078|047|011)([0-9]{7}$)");
        teleNumMatcher = namePattern.matcher(txtContact.getText());

        if (!teleNumMatcher.matches()) {
            txtContact.requestFocus();
            txtContact.setFocusColor(Paint.valueOf("Red"));
            lblContact.setText("Invalid  TelePhone Number");
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtStudentId.getText();
        try {
            boolean isDelete = studentBO.delete(id);
            if (isDelete) {
                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " Student Account delete Successful";
                Notification.showNotification(url, title, text);
                txtStudentId.setText("");
                txtName.setText("");
                txtdate.setValue(LocalDate.now());
                txtAddress.setText("");
                txtContact.setText("");
                rdbMale.setSelected(false);
                rdbFemale.setSelected(false);
                table();
                loadStudentId();
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "Student Account update UnSuccessful";
                Notification.showNotification(url, title, text);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtName.requestFocus();
        }
    }

    @FXML
    void idOnkeyRelease(KeyEvent event) {
        lblid.setText("");
        txtStudentId.setFocusColor(Paint.valueOf("Blue"));
        Pattern idPattern = Pattern.compile("^(S)([0-9]{1})|([0-9]{1,})$");
        idMatcher = idPattern.matcher(txtStudentId.getText());

        if (!idMatcher.matches()) {
            txtStudentId.requestFocus();
            txtStudentId.setFocusColor(Paint.valueOf("Red"));
            lblid.setText("Invalid  Member_Id");
        }
    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.KEY, pane);
    }

    @FXML
    void nameOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtAddress.requestFocus();
        }
    }

    @FXML
    void nameOnKeyRelease(KeyEvent event) {
        lblname.setText("");
        txtName.setFocusColor(Paint.valueOf("Blue"));
        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        if (!nameMatcher.matches()) {
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("Red"));
            lblname.setText("Invalid  Name Type");
        }
    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM, pane);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
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
            boolean isUpdated = studentBO.update(studentDTO);
            if (isUpdated) {
                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " Student Account Update Successful";
                Notification.showNotification(url, title, text);
                txtStudentId.setText("");
                txtName.setText("");
                txtdate.setValue(LocalDate.now());
                txtAddress.setText("");
                txtContact.setText("");
                rdbMale.setSelected(false);
                rdbFemale.setSelected(false);
                table();
                loadStudentId();
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "Student Account update UnSuccessful";
                Notification.showNotification(url, title, text);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    void comboKeyOnPress(KeyEvent event) throws Exception {
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

                    if ("Male".equals(tblView.getItems().get(myIndex).getGender())) {
                        rdbMale.setSelected(true);
                    } else if ("Female".equals(tblView.getItems().get(myIndex).getGender())) {
                        rdbFemale.setSelected(true);
                    }

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
