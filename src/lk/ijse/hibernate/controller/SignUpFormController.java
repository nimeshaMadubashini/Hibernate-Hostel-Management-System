package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.UserBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtpassword;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblName;

    @FXML
    private Label lbluserName;

    @FXML
    private Label lblPassword;
    private Matcher nicMatcher;
    private Matcher nameMatcher;
    private Matcher userNameMatcher;
    private Matcher passWordMatcher;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.UserSignUp);

    private void setPattern() {
        Pattern idPattern = Pattern.compile("^[0-9]{9}[vVxX]|[0-9]{12}[vVxX]|[0-9]{12}|[0-9]{9}$");
        nicMatcher = idPattern.matcher(txtNic.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        passWordMatcher = passwordPattern.matcher(txtpassword.getText());
    }
    public void initialize() {
        setPattern();
    }

    @FXML
    void SignUPOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String password = txtpassword.getText();
        try {
            if (nicMatcher.matches()) {
                if (nameMatcher.matches()) {
                    if (userNameMatcher.matches()) {
                        if (passWordMatcher.matches()) {
            boolean isSave = userBO.saveUser(new UserDto(nic, name, userName, password));
            if(isSave){
                String url = "lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Successful!";
                String text = " User Account Create Successful";
                Notification.showNotification(url, title, text);
                txtName.setText("");
                txtNic.setText("");
                txtpassword.setText("");
                txtUserName.setText("");
                Navigation.navigation(Routes.SIGNING, pane);
            } else {
                String url = "lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                String title = "UnSuccessful";
                String text = "User Account Create UnSuccessful";
                Notification.showNotification(url, title, text);
            }
                        } else {
                            txtpassword.requestFocus();
                            txtpassword.setFocusColor(Paint.valueOf("Red"));
                            lblPassword.setText("invalid password");
                        }
                    } else {
                        txtUserName.requestFocus();
                        txtUserName.setFocusColor(Paint.valueOf("Red"));
                        lbluserName.setText("invalid user name");
                    }
                } else {
                    txtName.requestFocus();
                    txtName.setFocusColor(Paint.valueOf("Red"));
                    lblName.setText("invalid name");

                }
            } else {
                txtNic.requestFocus();
                txtNic.setFocusColor(Paint.valueOf("Red"));
                lblNic.setText("invalid staff id");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtNameOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtUserName.requestFocus();
        }
    }

    @FXML
    void txtNicOnkeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtName.requestFocus();
        }
    }

    @FXML
    void txtPasswordOnKeyPress(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String nic = txtNic.getText();
            String name = txtName.getText();
            String userName = txtUserName.getText();
            String password = txtpassword.getText();
            try {
                boolean isSave = userBO.saveUser(new UserDto(nic, name, userName, password));
                Navigation.navigation(Routes.SIGNING, pane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void txtUserNameOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtpassword.requestFocus();
        }
    }
    @FXML
    void txtNameOnKeyRelease(KeyEvent event) {
        lblName.setText("");
        txtName.setFocusColor(Paint.valueOf("Blue"));
        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
        nameMatcher = namePattern.matcher(txtName.getText());

        if (!nameMatcher.matches()) {
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("Red"));
            lblName.setText("invalid name");
        }
    }

    @FXML
    void txtNicOnKeyReleased(KeyEvent event) {
        lblNic.setText("");
        txtNic.setFocusColor(Paint.valueOf("Blue"));
        Pattern idPattern = Pattern.compile("^[0-9]{9}[vVxX]|[0-9]{12}[vVxX]|[0-9]{12}|[0-9]{9}$");
        nicMatcher = idPattern.matcher(txtNic.getText());


        if (!nicMatcher.matches()) {
            txtNic.requestFocus();
            txtNic.setFocusColor(Paint.valueOf("Red"));
            lblNic.setText("invalid nic");
        }
    }
    @FXML
    void txtPasswordOnKeyRelease(KeyEvent event) {
        lblPassword.setText("");
        txtpassword.setFocusColor(Paint.valueOf("Blue"));
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        passWordMatcher = passwordPattern.matcher(txtpassword.getText());

        if (!passWordMatcher.matches()) {
            txtpassword.requestFocus();
            txtpassword.setFocusColor(Paint.valueOf("Red"));
            lblPassword.setText("invalid password");
        }
    }
    @FXML
    void txtUserNameOnKeyRelease(KeyEvent event) {
        lbluserName.setText("");
        txtUserName.setFocusColor(Paint.valueOf("Blue"));
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());


        if (!userNameMatcher.matches()) {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lbluserName.setText("invalid user name");
        }
    }
}
