package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.custome.UsrEditBO;
import lk.ijse.hibernate.dto.Notification;
import lk.ijse.hibernate.dto.UserDto;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;
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
        confirmPsMatcher = confirmPasswordPattern.matcher(conformPassword.getText());
    }

    public void initialize() {
        setPattern();
        txtPassword1.setVisible(false);
        openEye.setVisible(false);
    }

    @FXML
    void addSudentOnAction(ActionEvent event) throws IOException {
Navigation.navigation(Routes.STUDENT,pane);
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
            if (nicMatcher.matches()) {
                    if (userNameMatcher.matches()) {
                        if (passWordMatcher.matches()) {
                            if (conpassword.equals(password) && confirmPsMatcher.matches()) {
                                boolean isUpdate = usrEditBO.updateUser(dto);
                                if (isUpdate) {

                                    String url = "/lk/ijse/hibernate/assest/icons8-check-mark-48.png";
                                    String title = "Update Successful";
                                    String text = "Hey, Update Successful";
                                    Notification.showNotification(url, title, text);

                                }else{
                                String url = "/lk/ijse/hibernate/assest/icons8-select-no-64 (1).png";
                                String title = "Update UnSuccessful";
                                String text = "Incorrect Nic";
                                Notification.showNotification(url, title, text);
                            }
                            } else {
                                conformPassword.requestFocus();
                                conformPassword.setFocusColor(Paint.valueOf("red"));
                                llconfirmpsw.setText("Not Match password");
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
        } else {
            txtNic.requestFocus();
            txtNic.setFocusColor(Paint.valueOf("Red"));
            lblnic.setText("invalid staff id");
        }

    } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }

    @FXML
    void cloeEyeOnAction(MouseEvent event) {
        txtPassword1.setVisible(true);
        openEye.setVisible(true);
        closeEye.setVisible(false);
        txtPassword.setVisible(false);
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
void confirmpswONKeyRelease(KeyEvent keyEvent){
    llconfirmpsw.setText("");
    conformPassword.setFocusColor(Paint.valueOf("Blue"));
    Pattern confirmPasswordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8}$");
    confirmPsMatcher = confirmPasswordPattern.matcher(conformPassword.getText());

    if (!confirmPsMatcher.matches()) {
        conformPassword.requestFocus();
        conformPassword.setFocusColor(Paint.valueOf("Red"));
        llconfirmpsw.setText("not match password");
    }

}
    @FXML
    void homeOnAtion(ActionEvent event) throws IOException {
Navigation.navigation(Routes.HOME,pane);
    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
Navigation.navigation(Routes.ROOM,pane);
    }

    @FXML
    void openEyeOnAction(MouseEvent event) {
        txtPassword1.setVisible(false);
        openEye.setVisible(false);
        closeEye.setVisible(true);
        txtPassword.setVisible(true);
    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
Navigation.navigation(Routes.ROOM,pane);
    }

    @FXML
    void showPasswordOnKeyRelease(KeyEvent event) {
        password=txtPassword1.getText();
        txtPassword.setText(password);
    }

    @FXML
    void txtNicOnKeyReleased(KeyEvent event) {
        lblnic.setText("");
        txtNic.setFocusColor(Paint.valueOf("Blue"));
        Pattern idPattern = Pattern.compile("^[0-9]{9}[vVxX]|[0-9]{12}[vVxX]|[0-9]{12}|[0-9]{9}$");
        nicMatcher = idPattern.matcher(txtNic.getText());


        if (!nicMatcher.matches()) {
            txtNic.requestFocus();
            txtNic.setFocusColor(Paint.valueOf("Red"));
            lblnic.setText("invalid nic");
        }
    }

    @FXML
    void txtNicOnkeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            txtUserName.requestFocus();
        }
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


