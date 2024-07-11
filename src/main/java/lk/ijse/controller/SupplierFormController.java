package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Supplier;
import lk.ijse.view.tdm.SupplierTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SupplierFormController{
    public JFXButton btn_AddNew;
    public JFXButton btn_Delete;
    public JFXButton btn_Next;
    public JFXButton btn_Save;
    public TableView<SupplierTM> tableSupplier;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtDescription;
    public TextField txtId;
    public TextField txtName;
    public TextField txtSearch;
    public AnchorPane root;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOYTypes.SUPPLIER);


    public void initialize() {
        tableSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("description"));

        txtName.setOnAction(actionEvent -> txtAddress.requestFocus());
        txtAddress.setOnAction(actionEvent -> txtContact.requestFocus());
        txtContact.setOnAction(actionEvent -> txtDescription.requestFocus());

        initUI();

        tableSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btn_Delete.setDisable(newValue == null);
            btn_Save.setText((newValue != null ? "Update" : "Save"));
            btn_Save.setDisable(newValue == null);
            btn_AddNew.setText((newValue != null ? "Clear" : "+AddNewItem"));

            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtDescription.setText(newValue.getDescription());
            }
        });

        txtDescription.setOnAction(actionEvent -> btn_Save.fire());
        loadAllSupplier();

    }

    private void loadAllSupplier() {
        tableSupplier.getItems().clear();

        try {
            ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();

            for (SupplierDTO supplierDTO : allSupplier){
                tableSupplier.getItems().add(new SupplierTM(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getContact(), supplierDTO.getDescription()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDescription.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDescription.setDisable(true);
        txtId.setEditable(false);
        btn_Save.setDisable(true);
        btn_Delete.setDisable(true);
    }

    private String getLastSupplierId() {
        List<SupplierTM> tempSupplierList = new ArrayList<>(tableSupplier.getItems());
        Collections.sort(tempSupplierList);
        return  tempSupplierList.get(tempSupplierList.size() - 1).getId();
    }

    private String generateNewId() {
        try {
            return supplierBO.generateNewSupplierId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to generate a new id!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tableSupplier.getItems().isEmpty()) {
            return "S00-001";
        } else {
            String id = getLastSupplierId();
            int newSupplierId = Integer.parseInt(id.replace("S", "")) + 1;
            return String.format("S00-%03d", newSupplierId);
        }
    }

    @FXML
    void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tableSupplier.getSelectionModel().getSelectedItem().getId();

        try {
            if (!existSupplier(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such a supplier from " + id).show();
            }

            supplierBO.deleteSupplier(id);

            tableSupplier.getItems().remove(tableSupplier.getSelectionModel().getSelectedItem());
            tableSupplier.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete Supplier!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNext_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/employee_form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    void btnAddNew_OnAction() {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDescription.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDescription.clear();
        txtName.requestFocus();
        btn_Save.setDisable(false);
        btn_Save.setText("save");
        tableSupplier.getSelectionModel().clearSelection();
    }

    @FXML
    void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String description = txtDescription.getText();

        if (btn_Save.getText().equalsIgnoreCase("save")) {
            try{
                if (existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists!!").show();
                }

                supplierBO.addSupplier(new SupplierDTO(id, name, address, contact, description));

                tableSupplier.getItems().add(new SupplierTM(id, name, address, contact, description));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such a Supplier " + id).show();
                }

                supplierBO.updateSupplier(new SupplierDTO(id, name, address, contact, description));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            SupplierTM selectedSupplier = tableSupplier.getSelectionModel().getSelectedItem();
            selectedSupplier.setName(name);
            selectedSupplier.setAddress(address);
            selectedSupplier.setContact(contact);
            selectedSupplier.setDescription(description);
            tableSupplier.refresh();
        }

        btn_AddNew.fire();

    }

    private boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.existSupplier(id);
    }

    public void txtSearch_OnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText().trim(); // Trim whitespace

        System.out.println("Search Action Triggered");
        System.out.println("ID Entered: " + id);

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Valid Supplier ID!").show();
            return;
        }

        try {
            Supplier supplier = supplierBO.search(id);
            if (supplier != null) {
                txtId.setText(supplier.getId());
                txtName.setText(supplier.getName());
                txtAddress.setText(supplier.getAddress());
                txtContact.setText(supplier.getContact());
                txtDescription.setText(supplier.getDescription());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    public void txtSupplierContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, (JFXTextField) txtContact);
    }

    public void txtSupplierAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, (JFXTextField) txtAddress);
    }
    public void txtSupplierNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, (JFXTextField) txtName);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, (JFXTextField) txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, (JFXTextField) txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, (JFXTextField) txtName)) return false;
        return true;
    }

}







































