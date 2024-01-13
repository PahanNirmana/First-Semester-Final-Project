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
import lk.ijse.metleCrusher.bo.custom.EmployeeBO;
import lk.ijse.metleCrusher.bo.custom.impl.CustomerBoImpl;
import lk.ijse.metleCrusher.bo.custom.impl.EmployeeBoImpl;
import lk.ijse.metleCrusher.dto.EmployeeDto;
import lk.ijse.metleCrusher.dto.tm.EmployeeTm;
import lk.ijse.metleCrusher.entity.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmployeeFormController {
    public AnchorPane root;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private Label lblEmpDate;

    @FXML
    private TextField txtEmpGande;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtEmpNic;

    @FXML
    private TextField txtEmpSalary;

    @FXML
    private TextField txtEmpTel;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colGander;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    EmployeeBO employeeBO = new EmployeeBoImpl();
    private EmployeeModel empModel = new EmployeeModel();

    public void initialize() {
        setCellValueFactory();
        LoadAllEmployee();
        setDate();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGander.setCellValueFactory(new PropertyValueFactory<>("gander"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void LoadAllEmployee() {
      //  var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = employeeBO.getAllEmployee();

            for (EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getId(),
                                dto.getNic(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getGander(),
                                dto.getTel(),
                                dto.getSalary()
                        )
                );
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void EmpSearchOnAction(ActionEvent event) {
        String id = txtEmpId.getText();

        try {
            EmployeeDto dto = employeeBO.searchEmployee(id);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnClearOnActionEmplloyee(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmpId.setText("");
        txtEmpNic.setText("");
        txtEmpName.setText("");
        txtEmpAddress.setText("");
        txtEmpGande.setText("");
        txtEmpTel.setText("");
        txtEmpSalary.setText("");
    }

    @FXML
    void btnDeleteOnAntionEmployee(ActionEvent event) {
        String id = txtEmpId.getText();

//        var model = new employeeModel();
        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Not Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnEmployeeIdanCustomer(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnEmployeeIdanPlaceOrder(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Place Order");
        stage.centerOnScreen();
    }

    @FXML
    void btnEmplyoyeeIdanItem(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnSaveOnActionEmployee(ActionEvent event) {
        String id = txtEmpId.getText();
        String nic = txtEmpNic.getText();
        String name = txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String gander = txtEmpGande.getText();
        String tel = txtEmpTel.getText();
        double salary = Double.parseDouble(txtEmpSalary.getText());

        var dto = new EmployeeDto(id, nic, name, address, gander, tel, salary);

        try {
            boolean isSaved = employeeBO.saveEmployee(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAntionEmployee(ActionEvent event) {
        String id = txtEmpId.getText();
        String nic = txtEmpNic.getText();
        String name = txtEmpName.getText();
        String address = txtEmpAddress.getText();
        String gander = txtEmpGande.getText();
        String tel = txtEmpTel.getText();
        double salary = Double.parseDouble(txtEmpSalary.getText());

        var dto = new EmployeeDto(id, nic, name, address, gander, tel, salary);

        //var model = new EmployeeModel();
        try {
            boolean isUpdated = employeeBO.updateEmployee(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated ! :)").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }

    private void setFields(EmployeeDto dto) {
        txtEmpId.setText(dto.getId());
        txtEmpNic.setText(dto.getNic());
        txtEmpName.setText(dto.getName());
        txtEmpAddress.setText(dto.getAddress());
        txtEmpGande.setText(dto.getGander());
        txtEmpTel.setText(dto.getTel());
        txtEmpSalary.setText(String.valueOf(dto.getSalary()));
    }

    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblEmpDate.setText(String.valueOf(LocalDate.now()));

    }
}
