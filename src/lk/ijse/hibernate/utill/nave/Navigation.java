package lk.ijse.hibernate.utill.nave;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigation(Routes routes,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window= (Stage) Navigation.pane.getScene().getWindow();
        switch (routes){
            case SIGNUP:
                window.setTitle("SignUP Page");
                initUI("SignUpForm.fxml");
                break;
            case SIGNING:
                window.setTitle("SignIn Page");
                initUI("UserLoginform.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR,"Not Suitable UI Found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hibernate/view/"+location)));
    }


}
