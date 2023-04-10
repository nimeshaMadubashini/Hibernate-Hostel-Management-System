package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.KeyPaymentBO;
import lk.ijse.hibernate.dto.KeyPaymentDTO;
import lk.ijse.hibernate.dto.Notification;

import java.util.List;

public class KeyPaymentFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<KeyPaymentDTO> tableView;

    @FXML
    private TableColumn colResId;

    @FXML
    private TableColumn colStId;

    @FXML
    private TableColumn colStdName;

    @FXML
    private TableColumn s;

    @FXML
    private TableColumn colRoomType;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colOption;
KeyPaymentBO kBO= (KeyPaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.Key);
public void initialize(){
    loadTable();
}
    @FXML
    void HomeOnAction(ActionEvent event) {

    }

    @FXML
    void addSudentOnAction(ActionEvent event) {

    }

    @FXML
    void roomOnAction(ActionEvent event) {

    }

    @FXML
    void userOnaction(ActionEvent event) {

    }
public void loadTable(){
    try {
        List<Object[]> list=kBO.getPendingKeyPayments();
        ObservableList<KeyPaymentDTO> obList= FXCollections.observableArrayList();
        for (Object[] r:list) {
            System.out.println(r[0].toString());
            System.out.println(r[1].toString());
            System.out.println(r[2].toString());
            System.out.println(r[3].toString());
            System.out.println(r[4].toString());
            System.out.println(r[5].toString());
            obList.add(new KeyPaymentDTO(r[0].toString(),r[1].toString(),r[2].toString(),r[3].toString(),r[4].toString(),
                    r[5].toString(),getButton(r[0].toString())));

        }
        tableView.setItems(obList);
        colResId.setCellValueFactory(new PropertyValueFactory<>("resId"));
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        s.setCellValueFactory(new PropertyValueFactory<>("room_Type_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
    private JFXButton getButton(String resId) {
        JFXButton btn = new JFXButton(" Pay ");
        btn.setStyle("-fx-border-color: Black");
        btn.setOnAction(event -> {
            try {
              boolean isUpdate= kBO.updatePayment(resId);
                if(isUpdate){
                    String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                    String title = "Successful!";
                    String text = " Payment Update  Successful";
                    Notification.showNotification(url, title, text);
                } else {
                    String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                    String title = "UnSuccessful";
                    String text = "payment Update UnSuccessful";
                    Notification.showNotification(url, title, text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return btn;
    }
}
