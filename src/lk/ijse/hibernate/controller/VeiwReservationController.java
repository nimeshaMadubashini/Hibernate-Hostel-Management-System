package lk.ijse.hibernate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.ViewAllReservationBO;
import lk.ijse.hibernate.dto.KeyPaymentDTO;
import lk.ijse.hibernate.dto.RoomDTO;

import java.util.List;

public class VeiwReservationController {
    @FXML
    private AnchorPane pane;

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

    public void  initialize(){
        try {
            loadTable();
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
}
