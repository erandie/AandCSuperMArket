package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
//import lk.ijse.model.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.view.tdm.CustomerTM;
//import lk.ijse.model.CustomerDTO;
import lk.ijse.dto.CustomerDTO;
//import repositry.CustomerRepo;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import static repositry.CustomerRepo.save;

public class CustomerFormController {
    public JFXButton btnAddNew;
    public JFXButton btnDelete;
    public JFXButton btnNext;
    public JFXButton btnSave;
    public JFXButton btnSearch;
    public ImageView imgHome;
    public AnchorPane root;
    public TableView<CustomerTM> tblCustomers;
//    public TextField txtAddress;
//    public TextField txtContact;
//    public TextField txtId;
//    public TextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public TextField txtSearch;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOYTypes.CUSTOMER);

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cId"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));

        txtName.setOnAction(actionEvent -> txtAddress.requestFocus());
        txtAddress.setOnAction(actionEvent -> txtContact.requestFocus());

        initUI();

        addRegex(txtId, "^C\\d{2}-\\d{3}$", "ID must follow the pattern CXX-XXX");
        addRegex(txtName, "^[A-Za-z]+(?:[\\s-][A-Za-z]+)*$", "Name should start with a capital letter and contain only letters");
        addRegex(txtAddress, "^[A-Za-z]+(?:[\\s-][A-Za-z]+)*$", "Address should start with a capital letter and contain only letters");
        addRegex(txtContact, "^(\\+?\\d{10,12})$", "Contact number should be 10-12 digits, optionally starting with +");


        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "save");
            btnDelete.setDisable(newValue == null);
            btnAddNew.setText(newValue != null ? "+AddCustomer" : "Clear");

            if (newValue != null) {
                txtId.setText(newValue.getCId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContact.setDisable(false);

            }
        });

        txtContact.setOnAction(actionEvent -> btnSave.fire());
        loadAllCustomer();

    }

    private void loadAllCustomer()  {
        tblCustomers.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();

            for (CustomerDTO c : allCustomers) {
                tblCustomers.getItems().add(new CustomerTM(c.getcId(), c.getName(), c.getAddress(), c.getContact()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } /*catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
  */ catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("save");
        tblCustomers.getSelectionModel().clearSelection();
    }

    private String generateNewId() {
        try {
            return customerBO.generateNew_CustomerId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to generate a new id!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblCustomers.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String cId = getLastCustomerId();
            int newCustomerId = Integer.parseInt(cId.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }
    }


    private String getLastCustomerId() {
        List<CustomerTM> tempCustomerList = new ArrayList<>(tblCustomers.getItems());
        Collections.sort(tempCustomerList);
        return tempCustomerList.get(tempCustomerList.size() - 1).getCId();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cId = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existCustomer(cId)) {
                    new Alert(Alert.AlertType.ERROR, cId + " already exists!").show();
                    return;
                }

                customerBO.addCustomer(new CustomerDTO(cId, name, address, contact));
                tblCustomers.getItems().add(new CustomerTM(cId, name, address, contact));

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer! " + e.getMessage()).show();
            }

//            if (isValid()) {
//               boolean isSaved =  customerBO.addCustomer(new CustomerDTO(cId, name, address, contact));
//                if (isSaved) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
//                    initUI();
//                }
//            }
//               loadAllCustomer();

        } else {
            try {
                if (!existCustomer(cId)) {
                    new Alert(Alert.AlertType.ERROR, "Can't find the ID " + cId + "! Enter another one!").show();
                    return;
                }

                customerBO.updateCustomer(new CustomerDTO(cId, name, address, contact));
                CustomerTM selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
                selectedCustomer.setName(name);
                selectedCustomer.setAddress(address);
                selectedCustomer.setContact(contact);
                tblCustomers.refresh();

            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer! " + e.getMessage()).show();
            }
        }
        btnAddNew.fire();
    }



    private boolean existCustomer(String cId) throws SQLException, ClassNotFoundException {
        return customerBO.existCustomer(cId);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String cId = tblCustomers.getSelectionModel().getSelectedItem().getCId();
        try {
            if (!existCustomer(cId)) {
                new Alert(Alert.AlertType.ERROR, "There is a no such a customer from " + cId).show();
            }

            customerBO.deleteCustomer(cId);

            tblCustomers.getItems().remove(tblCustomers.getSelectionModel().getSelectedItem());
            tblCustomers.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, "failed to delete the customer!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtSearch_OnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText().trim(); // Trim whitespace

        System.out.println("Search Action Triggered");
        System.out.println("ID Entered: " + id);

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Valid Customer ID!").show();
            return;
        }

        try {
            Customer customer = customerBO.search(id);
            if (customer != null) {
                txtId.setText(customer.getcId());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtContact.setText(customer.getContact());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    private void addRegex(JFXTextField textField, String pattern, String message) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(pattern)) {
                if (!textField.getStyleClass().contains("error")) {
                    textField.getStyleClass().add("error");
                }
            } else {
                textField.getStyleClass().remove("error");
            }
        });
    }

    public void btnNext_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/item_form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void txtCustomerContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, txtContact);
    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, txtAddress);
    }
    public void txtCustomerNAmeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtName);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtName)) return false;
        return true;
    }

}


































