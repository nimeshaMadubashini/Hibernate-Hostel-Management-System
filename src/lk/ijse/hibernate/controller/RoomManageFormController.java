package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.RoomBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomManageFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtroomid;

    @FXML
    private JFXTextField txtRoomType;

    @FXML
    private JFXTextField txtLKR;

    @FXML
    private JFXTextField txtqty;
    @FXML
    private Label lblid;

    @FXML
    private Label lblType;

    @FXML
    private Label lblMoney;

    @FXML
    private Label lblQty;

    @FXML
    private TableView<RoomDTO> tblView;

    @FXML
    private TableColumn tblcolId;

    @FXML
    private TableColumn colRoomType;

    @FXML
    private TableColumn colKeyMoney;

    @FXML
    private TableColumn colQty;

    @FXML
    private JFXComboBox comboid;
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ROOM);
    int myIndex;
    private Matcher idMatcher;
    private Matcher  typeMatcher;
    private Matcher lkrMatcher;
    private Matcher qtyMatcher;



    private void setPattern() {
        Pattern idPattern = Pattern.compile("^(Rm)(-)([0-9]{1})([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtroomid.getText());

        Pattern typePattern = Pattern.compile("^(Non-AC|AC|AC\\\\Food|Non-AC\\\\Food)$");
        typeMatcher = typePattern.matcher(txtRoomType.getText());

        Pattern lkrPattern = Pattern.compile("^([0-9]{4,})$");
        lkrMatcher = lkrPattern.matcher(txtLKR.getText());


        Pattern qtyPattern = Pattern.compile("^([0-9]{1,})$");
        qtyMatcher = qtyPattern.matcher(txtqty.getText());


    }
    public void initialize() {
        loadRoomId();
        setPattern();
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
        String id = txtroomid.getText();
        String type = txtRoomType.getText();
        String lkr = txtLKR.getText();
        int qty = Integer.parseInt(txtqty.getText());

        RoomDTO roomDTO = new RoomDTO(id, type, lkr, qty);

        try {
            boolean isAdd = roomBO.save(roomDTO);
            if (isAdd) {

                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " Room Add  Successful";
                Notification.showNotification(url, title, text);
                txtroomid.setText("");
                txtRoomType.setText("");
                txtLKR.setText("");
                txtqty.setText("");
                table();
                loadRoomId();
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "Room Add  UnSuccessful";
                Notification.showNotification(url, title, text);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadRoomId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<String> idList = roomBO.loadRoomId();

            for (String id : idList) {
                observableList.add(id);
            }
            comboid.setItems(observableList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void table() throws Exception {
        List<RoomDTO> list = roomBO.loadAllRoomTOTable();
        ObservableList<RoomDTO> observableList = FXCollections.observableArrayList();
        for (RoomDTO s : list) {
            observableList.add(new RoomDTO(s.getRoom_type_id(), s.getType(), s.getKey_money(), s.getQty()));
        }
        tblView.setItems(observableList);
        tblcolId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


        tblView.setRowFactory(tv -> {
            TableRow<RoomDTO> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {

                    myIndex = tblView.getSelectionModel().getSelectedIndex();
                    txtroomid.setText(tblView.getItems().get(myIndex).getRoom_type_id());
                    txtRoomType.setText(tblView.getItems().get(myIndex).getType());
                    txtLKR.setText(tblView.getItems().get(myIndex).getKey_money());
                    txtqty.setText(String.valueOf(tblView.getItems().get(myIndex).getQty()));


                }
            });
            return myRow;
        });
    }

    @FXML
    void comboKeyOnPress(KeyEvent event) throws Exception {
        String id = String.valueOf(comboid.getValue());
        List<RoomDTO> list = roomBO.findRoomCM(id);
        ObservableList<RoomDTO> observableList = FXCollections.observableArrayList();
        for (RoomDTO s : list) {
            observableList.add(new RoomDTO(s.getRoom_type_id(), s.getType(), s.getKey_money(), s.getQty()));
        }
        tblView.setItems(observableList);
        tblcolId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }



    @FXML
    void deleteOnAction(ActionEvent event) {
        String id = txtroomid.getText();
        try {
            if (idMatcher.matches()) {
                if (typeMatcher.matches()) {
                    if (lkrMatcher.matches()) {
                        if (qtyMatcher.matches()) {
            boolean isDelete = roomBO.delete(id);
            if (isDelete) {

                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " Room Delete  Successful";
                Notification.showNotification(url, title, text);
                txtroomid.setText("");
                txtRoomType.setText("");
                txtLKR.setText("");
                txtqty.setText("");
                table();
                loadRoomId();
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "Room Delete  UnSuccessful";
                Notification.showNotification(url, title, text);
            }
                        } else {
                            txtqty.requestFocus();
                            txtqty.setFocusColor(Paint.valueOf("Red"));
                            lblQty.setText("invalid Qty");
                        }
                    } else {
                        txtLKR.requestFocus();
                        txtLKR.setFocusColor(Paint.valueOf("Red"));
                        lblMoney.setText("invalid Currency");
                    }

                } else {
                    txtRoomType.requestFocus();
                    txtRoomType.setFocusColor(Paint.valueOf("Red"));
                    lblType.setText("invalid Room Type");
                }
            } else {
                txtroomid.requestFocus();
                txtroomid.setFocusColor(Paint.valueOf("Red"));
                lblid.setText("invalid ID Type");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void homeOnAtion(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.HOME, pane);
    }

    @FXML
    void idOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtRoomType.requestFocus();
        }
    }

    @FXML
    void idOnkeyRelease(KeyEvent event) {
        lblid.setText("");
        txtroomid.setFocusColor(Paint.valueOf("Blue"));
        Pattern idPattern = Pattern.compile("^(Rm)(-)([0-9]{1})([0-9]{1})([0-9]{1})$");
        idMatcher = idPattern.matcher(txtroomid.getText());

        if (!idMatcher.matches()) {
            txtroomid.requestFocus();
            txtroomid.setFocusColor(Paint.valueOf("Red"));
            lblid.setText("Invalid ID Type");
        }
    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.KEY, pane);
    }

    @FXML
    void loadAllOnAction(ActionEvent event) {
        try {
            table();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void moneyOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtqty.requestFocus();
        }
    }

    @FXML
    void moneyOnKeyRelease(KeyEvent event) {
        lblMoney.setText("");
        txtLKR.setFocusColor(Paint.valueOf("Blue"));
        Pattern lkrPattern = Pattern.compile("^([0-9]{4,})$");
        lkrMatcher = lkrPattern.matcher(txtLKR.getText());

        if (!lkrMatcher.matches()) {
            txtLKR.requestFocus();
            txtLKR.setFocusColor(Paint.valueOf("Red"));
            lblMoney.setText("Invalid Currency");
        }
    }

    @FXML
    void qtyOnKeyPress(KeyEvent event) {

    }

    @FXML
    void qtyOnKeyReleased(KeyEvent event) {
        lblQty.setText("");
        txtqty.setFocusColor(Paint.valueOf("Blue"));
        Pattern qtyPattern = Pattern.compile("^([0-9]{1,})$");
        qtyMatcher = qtyPattern.matcher(txtqty.getText());

        if (!qtyMatcher.matches()) {
            txtqty.requestFocus();
            txtqty.setFocusColor(Paint.valueOf("Red"));
            lblQty.setText("Invalid Qty");
        }
    }

    @FXML
    void studentOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.STUDENT, pane);
    }

    @FXML
    void typeOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtLKR.requestFocus();
        }
    }

    @FXML
    void typeOnKeyRelease(KeyEvent event) {
        lblType.setText("");
        txtRoomType.setFocusColor(Paint.valueOf("Blue"));
        Pattern typePattern = Pattern.compile("^(Non-AC|AC|AC\\\\Food|Non-AC\\\\Food)$");
        idMatcher = typePattern.matcher(txtRoomType.getText());

        if (!idMatcher.matches()) {
            txtRoomType.requestFocus();
            txtRoomType.setFocusColor(Paint.valueOf("Red"));
            lblType.setText("Invalid  Room Type");
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String id = txtroomid.getText();
        String type = txtRoomType.getText();
        String lkr = txtLKR.getText();
        int qty = Integer.parseInt(txtqty.getText());

        RoomDTO roomDTO = new RoomDTO(id, type, lkr, qty);

        try {
            boolean isUpdate = roomBO.update(roomDTO);
            if (isUpdate) {

                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " Room update  Successful";
                Notification.showNotification(url, title, text);
                txtroomid.setText("");
                txtRoomType.setText("");
                txtLKR.setText("");
                txtqty.setText("");
                table();
                loadRoomId();
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "Room Update  UnSuccessful";
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
}
