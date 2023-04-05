package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.UsrEditBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEditFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private ImageView closeEye;

    @FXML
    private Label lblusername;

    @FXML
    private Label lblPassword;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtPassword1;

    @FXML
    private ImageView openEye;

    @FXML
    private JFXPasswordField conformPassword;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private Label llconfirmpsw;

    @FXML
    private Label lblnic;
    private Matcher nicMatcher;
    private Matcher confirmPsMatcher;
    private Matcher userNameMatcher;
    private Matcher passWordMatcher;

    private void setPattern() {
        Pattern idPattern = Pattern.compile("^[0-9]{9}[vVxX]|[0-9]{12}[vVxX]|[0-9]{12}|[0-9]{9}$");
        nicMatcher = idPattern.matcher(txtNic.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        passWordMatcher = passwordPattern.matcher(txtPassword.getText());

        Pattern confirmPasswordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
        confirmPsMatcher = passwordPattern.matcher(conformPassword.getText());
    }

    public void initialize() {
        setPattern();
        txtPassword1.setVisible(false);
        openEye.setVisible(false);
    }

    @FXML
    void addSudentOnAction(ActionEvent event) {

    }

    String password;
    UsrEditBO usrEditBO = (UsrEditBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.USEREDIT);

    @FXML
    void changeOnAction(ActionEvent event) {
        password = txtPassword.getText();
        String nic = txtNic.getText();
        String username = txtUserName.getText();
        String conpassword = conformPassword.getText();
        UserDto dto = new UserDto(nic, username, password);
        try {
            boolean isUpdate = usrEditBO.updateUser(dto);
            if (isUpdate) {

                String url = "/lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                String title = "Update Successful";
                String text = "Hey, Update Successful";
                Notification.showNotification(url, title, text);

            }
            String url = "/lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
            String title = "Update UnSuccessful";
            String text = "Incorrect Nic";
            Notification.showNotification(url, title, text);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }

    @FXML
    void cloeEyeOnAction(MouseEvent event) {

    }

    @FXML
    void hidePasswordOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void homeOnAtion(ActionEvent event) {

    }

    @FXML
    void keyOnAction(ActionEvent event) {

    }

    @FXML
    void openEyeOnAction(MouseEvent event) {

    }

    @FXML
    void roomOnAction(ActionEvent event) {

    }

    @FXML
    void showPasswordOnKeyRelease(KeyEvent event) {

    }

    @FXML
    void txtNicOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtNicOnkeyPressed(KeyEvent event) {

    }

    @FXML
    void txtUserNameOnKeyPress(KeyEvent event) {

    }

    @FXML
    void txtUserNameOnKeyRelease(KeyEvent event) {

    }

}


