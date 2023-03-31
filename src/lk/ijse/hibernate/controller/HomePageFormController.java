package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.utill.nave.Navigation;
import lk.ijse.hibernate.utill.nave.Routes;

import java.io.IOException;

public class HomePageFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    void addSudentOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.STUDENT,pane);
    }

    @FXML
    void homeOnAtion(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.HOME,pane);
    }

    @FXML
    void keyOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.KEY,pane);
    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.ROOM,pane);
    }

    @FXML
    void userOnaction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.USER,pane);
    }

}
