package lk.ijse.metleCrusher.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private JFXButton singUp;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton login;

    @FXML
    private PasswordField password;

    @FXML
    private JFXButton singup;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    private void checkLogin() throws IOException {
        if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.setTitle("Dashboard");
            stage.centerOnScreen();
        }

        else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please Enter Your Data");
        }
        else {
            wrongLogin.setText("Wrong Username Or Password");
        }
    }

    @FXML
    public void UserLoginOnAction(ActionEvent event) throws IOException {
        checkLogin();
    }

}
