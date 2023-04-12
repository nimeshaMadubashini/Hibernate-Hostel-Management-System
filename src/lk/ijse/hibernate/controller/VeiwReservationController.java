package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.ViewAllReservationBO;
import lk.ijse.hibernate.dto.KeyPaymentDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.util.List;

public class VeiwReservationController {
    @FXML
    private AnchorPane pane;
@FXML
private JFXComboBox comboStudent;
    @FXML
    private TableView<KeyPaymentDTO> tableView;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colStId;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private TableColumn<?, ?> s;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane paneStudent;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtGender;

    @FXML
    private JFXTextField txtbthdy;

    @FXML
    void closeOnAction(ActionEvent event) {
paneStudent.setVisible(false);
    }
    @FXML
    void onSelected(KeyEvent event) {
        paneStudent.setVisible(true);
String id= String.valueOf(comboStudent.getValue());
        try {
            StudentDTO studentDTO=vw.findStudent(id);
            if(studentDTO !=null){
                txtStudentId.setText(studentDTO.getStudent_id());
                txtName.setText(studentDTO.getName());
                txtAddress.setText(studentDTO.getAddress());
                txtbthdy.setText(String.valueOf(studentDTO.getDob()));
                txtContact.setText(studentDTO.getContact_no());
                txtGender.setText(studentDTO.getGender());
            }
        } catch (NullPointerException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void  initialize(){
        try {
            loadTable();
            loadStudentId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
ViewAllReservationBO vw= (ViewAllReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ViewAll);
public void loadTable() throws Exception {
    List<KeyPaymentDTO> list = vw.loadAllStudent();
    ObservableList<KeyPaymentDTO> observableList = FXCollections.observableArrayList();
    for (KeyPaymentDTO s : list) {
        observableList.add(new KeyPaymentDTO(s.getResId(),s.getStudent_id(),s.getName(),s.getRoom_Type_id(),s.
                getType(),s.getStatus()));
    }
    tableView.setItems(observableList);
    colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
    colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
    colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
    s.setCellValueFactory(new PropertyValueFactory<>("room_Type_id"));
    colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


}
    private void loadStudentId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> idList = vw.loadReserveRoomStId();

            for (String id : idList) {
                observableList.add(id);
            }
            comboStudent.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void BackOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.HOME,pane);
    }
}
