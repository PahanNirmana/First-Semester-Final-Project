package lk.ijse.metleCrusher.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private AnchorPane root;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        System.out.println("btnCustomerOnAction class");

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Item Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Place Order");
        stage.centerOnScreen();
    }
    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException{
        System.out.println("Employee");
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee");
        stage.centerOnScreen();
    }
    @FXML
    void btnDashboardToCustomer(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnDashboardToEmployee(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee");
        stage.centerOnScreen();
    }

    @FXML
    void btnDashboardToItem(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Item Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnDashboardToPlaceOrder(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Place Order");
        stage.centerOnScreen();
    }
    @FXML
    void btnSingUpOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/singup_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("SingUp");
        stage.centerOnScreen();
    }

}
