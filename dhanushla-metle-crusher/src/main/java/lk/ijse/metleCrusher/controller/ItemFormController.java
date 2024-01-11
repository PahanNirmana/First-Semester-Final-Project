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
import lk.ijse.metleCrusher.bo.custom.ItemBO;
import lk.ijse.metleCrusher.bo.custom.impl.ItemBoImpl;
import lk.ijse.metleCrusher.dao.custom.ItemDAO;
import lk.ijse.metleCrusher.dao.custom.impl.ItemDAOImpl;
import lk.ijse.metleCrusher.dto.ItemDto;
import lk.ijse.metleCrusher.dto.tm.ItemTm;
import lk.ijse.metleCrusher.entity.ItemModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemFormController {
    @FXML
    private TableView<ItemTm> tblItem;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private Label lblItemDate;
    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    private ItemModel itemModel = new ItemModel();
    ItemBO itemBO = new ItemBoImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        tableListener();
        setDate();
    }
    private void setDate() {
        lblItemDate.setText(String.valueOf(LocalDate.now()));
    }
    private void tableListener() {
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(ItemTm row) {
        txtCode.setText(row.getCode());
        txtDescription.setText(row.getDescription());
        txtUnitPrice.setText(String.valueOf(row.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(row.getQtyOnHand()));
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isItemValid = isValidateItem();
        if (isItemValid) {
            String code = txtCode.getText();
            String description = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

            var dto = new ItemDto(code, description, unitPrice, qtyOnHand);

//        var model = new ItemModel();
            try {
                boolean isSaved = itemBO.saveItem(new ItemDto(code,description,unitPrice,qtyOnHand));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                    clearFields();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean isValidateItem() {
        String code = txtCode.getText();
        Pattern compile = Pattern.compile("[0-9]{3,}");
        Matcher matcher = compile.matcher(code);
        boolean matches = matcher.matches();

        if(!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Item ID").show();
            return false;
        }

        return true;
    }

    private void loadAllItems() {
//        var model = new ItemModel();
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try {

            ArrayList<ItemDto> getAllItems=itemBO.getAllItems();
            for (ItemDto dto:getAllItems) {
                tblItem.getItems().add(new ItemTm(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()));
            }


//            List<ItemDto> dtoList = itemModel.loadAllItems();
//
//            for (ItemDto dto : dtoList) {
//                obList.add(new ItemTm(
//                        dto.getCode(),
//                        dto.getDescription(),
//                        dto.getQtyOnHand(),
//                        dto.getUnitPrice(),
//                        new Button("Delete")
//                ));
//            }
//            tblItem.setItems(obList);



        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    @FXML
    void btnItemEkeIdanCustomerEkata(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();
    }

    @FXML
    void btnItemEkeIdanPlaceOrderEkata(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeorder_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Place Order");
        stage.centerOnScreen();
    }
    @FXML
    void btnItemiIdanEmployee(ActionEvent event)throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee");
        stage.centerOnScreen();
    }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
        String code = txtCode.getText();

            try {
                boolean isDeleted = itemBO.deleteItem(code);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item not deleted!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

//        var model = new ItemModel();
        try {

            ItemDto itemDTO = new ItemDto(code, description, unitPrice, qtyOnHand);
            boolean isUpdated = itemBO.updateItem(itemDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            ItemDto dto = itemModel.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    private void setFields(ItemDto dto) {
        txtCode.setText(dto.getCode());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
    }


}
