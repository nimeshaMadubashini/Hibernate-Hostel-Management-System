package lk.ijse.hibernate.controller;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.UserLoginBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLoginformController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtPassword1;
    @FXML
    private Label lblusername;
    @FXML
    private ImageView openEye;
    @FXML
    private ImageView closeEye;
    @FXML
    private Label lblPassword;
    private Matcher userNameMatcher;
    private Matcher passWordMatcher;
    UserLoginBO userLoginBO = (UserLoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.UserLogin);

    private void setPattern() {

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        passWordMatcher = passwordPattern.matcher(txtPassword.getText());
    }

    public void initialize() {
        setPattern();
        txtPassword1.setVisible(false);
        openEye.setVisible(false);
    }
String password;
    @FXML
    void SignUpOnAction(ActionEvent event) throws IOException, InvocationTargetException {

        Navigation.navigation(Routes.SIGNUP, pane);
    }

    @FXML
    void singInOnAction(ActionEvent event) throws Exception {
        String username = txtUserName.getText();
         password = txtPassword.getText();
        if (userNameMatcher.matches()) {
            if (passWordMatcher.matches()) {
                boolean isverify = userLoginBO.verifyUser(username, password);
                if (isverify) {
                    String url = "/lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                    String title = "Login Successful";
                    String text = "Hey, Login Successful";
                    Notification.showNotification(url, title, text);
                    Navigation.navigation(Routes.HOME,pane);
                } else {
                    String url = "/lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                    String title = "Login UnSuccessful";
                    String text = "Incorrect Username or Password";
                    Notification.showNotification(url, title, text);
                }
            } else {
                txtPassword.requestFocus();
                txtPassword.setFocusColor(Paint.valueOf("Red"));
                lblPassword.setText("invalid password");
            }
        } else {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblusername.setText("invalid user name");
        }
    }


    @FXML
    void hidePasswordOnKeyRelease(KeyEvent event) {
        lblPassword.setText("");
        txtPassword.setFocusColor(Paint.valueOf("Blue"));
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        passWordMatcher = passwordPattern.matcher(txtPassword.getText());

        if (!passWordMatcher.matches()) {
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            lblPassword.setText("invalid password");
        }
        password=txtPassword.getText();
        txtPassword1.setText(password);
    }
    @FXML
    void cloeEyeOnAction(MouseEvent event) {
txtPassword1.setVisible(true);
openEye.setVisible(true);
closeEye.setVisible(false);
txtPassword.setVisible(false);
    }

    @FXML
    void openEyeOnAction(MouseEvent event) {
        txtPassword1.setVisible(false);
        openEye.setVisible(false);
        closeEye.setVisible(true);
        txtPassword.setVisible(true);
    }

    @FXML
    void showPasswordOnKeyRelease(KeyEvent event) {
password=txtPassword1.getText();
txtPassword.setText(password);
    }
    @FXML
    void txtUserNameOnKeyPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtPassword.requestFocus();
        }
    }

    @FXML
    void txtUserNameOnKeyRelease(KeyEvent event) {
        lblusername.setText("");
        txtUserName.setFocusColor(Paint.valueOf("Blue"));
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());


        if (!userNameMatcher.matches()) {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblusername.setText("invalid user name");
        }
    }
}




