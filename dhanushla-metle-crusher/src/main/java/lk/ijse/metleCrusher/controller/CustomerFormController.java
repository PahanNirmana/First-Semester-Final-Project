package lk.ijse.metleCrusher.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.metleCrusher.bo.custom.CustomerBO;
import lk.ijse.metleCrusher.bo.custom.impl.CustomerBoImpl;
import lk.ijse.metleCrusher.dto.CustomerDto;
import lk.ijse.metleCrusher.dto.tm.CustomerTm;
import lk.ijse.metleCrusher.entity.CustomerModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController {
    public AnchorPane root;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblCustomerDate;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;
    CustomerBO customerBO = new CustomerBoImpl();

    private CustomerModel cusModel = new CustomerModel();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
        setDate();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllCustomer() {
        var model = new CustomerModel();

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDto> allCustomer= customerBO.getAllCustomer();
            for (CustomerDto c:allCustomer) {
                tblCustomer.getItems().
                        add(new CustomerTm(c.getId(),c.getName(),c.getAddress(),c.getTel()));
            }



//            List<CustomerDto> dtoList = model.getAllCustomer();
//
//            for (CustomerDto dto : dtoList) {
//                obList.add(
//                        new CustomerTm(
//                                dto.getId(),
//                                dto.getName(),
//                                dto.getAddress(),
//                                dto.getTel()
//                        )
//                );
//            }
//
//            tblCustomer.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblCustomerDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCustomerValid = validateCustomer();

        if (isCustomerValid){
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();

            var dto = new CustomerDto(id, name, address, tel);

            try {
                boolean isSaved = customerBO.saveCustomer(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
                    tblCustomer.getItems().add(new CustomerTm(id, name, address,tel));
                    clearFields();
                }
             //   CustomerDto customerDTO=new CustomerDto(id,name,address,tel);

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean validateCustomer() {
        String id_value = txtId.getText();
        Pattern compile = Pattern.compile("[C][0-9]{3,}");
        Matcher matcher = compile.matcher(id_value);
        boolean matches = matcher.matches();

        if(!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer ID").show();
            return false;
        }

        //validate customer Tel
        String tel_value = txtTel.getText();
        Pattern compile1 = Pattern.compile("[0-9]{10,}");
        Matcher matcher1 = compile1.matcher(tel_value);
        boolean isTelValid = matcher1.matches();
        if (!isTelValid){
            new Alert(Alert.AlertType.ERROR,"Invalid Tel").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
       String id = txtId.getText();
      //  String id = tblCustomer.getSelectionModel().getSelectedItem().getId();
//        var model = new CustomerModel();
        try {

            boolean isDeleted = customerBO.deleteCustomer(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();

                customerBO.deleteCustomer(id);
                tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
                tblCustomer.getSelectionModel().clearSelection();

            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "customer not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        var customerDto = new CustomerDto(id, name, address, tel);

//        var model = new CustomerModel();
        try {
            boolean isUpdated = customerBO.updateCustomer(customerDto);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                clearFields();
            }
            CustomerDto customerDTO=new CustomerDto(id,name,address,tel);
            boolean isUpdate = customerBO.updateCustomer(customerDTO);
            if (isUpdate){
                tblCustomer.getItems().add(new CustomerTm(id, name, address,tel));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

//        var model = new CustomerModel();
        try {
            CustomerDto customerDto = cusModel.searchCustomer(id);
//            System.out.println(customerDto);
            if (customerDto != null) {
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtAddress.setText(customerDto.getAddress());
                txtTel.setText(customerDto.getTel());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void btnCustomerPageEkeIdanItemPageEkata(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnCustomerIdanPlaceOrderEkata(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Place Order");
        stage.centerOnScreen();
    }
    @FXML
    void btnCustomerIdanEmployee(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee");
        stage.centerOnScreen();
    }
}
