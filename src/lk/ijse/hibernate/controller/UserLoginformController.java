package lk.ijse.hibernate.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.UserLoginBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class UserLoginformController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private Label lblusername;

    @FXML
    private Label lblPassword;
    UserLoginBO userLoginBO = (UserLoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.UserLogin);

    @FXML
    void SignUpOnAction(ActionEvent event) throws IOException, InvocationTargetException {

        Navigation.navigation(Routes.SIGNUP, pane);
    }

    @FXML
    void singInOnAction(ActionEvent event) throws Exception {
        String username = txtUserName.getText();
        String password = txtPassword.getText();
        UserDto userDto=new UserDto(username,password);
        boolean isverify = userLoginBO.verifyUser(userDto);
        if (isverify) {
            String url = "/lk/ijse/hibernate/assest/icons8-check-mark-48.png";
            String title = "Login Successful";
            String text = "Hey, Login Successful";
            Notification.showNotification(url, title, text);
        } else {
            String url = "/lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
            String title = "Login UnSuccessful";
            String text = "Incorrect Username or Password";
            Notification.showNotification(url, title, text);
        }
    }

}


