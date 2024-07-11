package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public JFXButton btnCustomer;
    public JFXButton btnEmployee;
    public JFXButton btnItem;
    public JFXButton btnPlaceOrder;
    public JFXButton btnSupplier;
    public JFXButton btnUser;
    public ImageView imagePO;
    public ImageView imgCustomer;
    public ImageView imgEmp;
    public ImageView imgItem;
    public ImageView imgSupp;
    public ImageView imgUser;
    public AnchorPane root;
    public AnchorPane rootNode;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        navigateToTheCustomerForm();
    }


    private void navigateToTheCustomerForm() throws IOException {
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/newCustomer_form.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


  @FXML
  void btnEmployeeOnAction(ActionEvent event) throws IOException {
        navigateToTheEmployeeForm();
    }

    private void navigateToTheEmployeeForm() {
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        navigateToTheSupplierForm();
    }

    private void navigateToTheSupplierForm(){
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        navigateToTheItemForm();
    }
    private void navigateToTheItemForm(){
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void linkPlaceOrderOnAction(ActionEvent event) throws IOException {
        navigateToThePlaceOrderForm();
    }
    private void navigateToThePlaceOrderForm(){
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/placeOrder_form2.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegistrationOnAction(ActionEvent actionEvent) {
        navigateToTheRegisterrForm();
    }

    private void navigateToTheRegisterrForm(){
        try {
            AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/newRegister_form.fxml"));
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

