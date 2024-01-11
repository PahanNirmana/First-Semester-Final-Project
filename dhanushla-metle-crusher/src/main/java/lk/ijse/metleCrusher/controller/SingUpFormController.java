package lk.ijse.metleCrusher.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SingUpFormController {

    @FXML
    private JFXButton register;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnAllReadySingUpOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_Form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login");
        stage.centerOnScreen();

    }

}
