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
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Item;
import lk.ijse.view.tdm.ItemTM;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormController {
    public AnchorPane root;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TableView<ItemTM> tblItems;
    public TextField txtUnitPrice;
    public TextField txtSearch;
    public JFXButton btnAddNewItem;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOYTypes.ITEM);

    public void initialize(){
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));

        initUI();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "update" : "save");
            btnSave.setDisable(newValue == null);
            btnAddNewItem.setText(newValue != null ? "+AddItem" : "Clear");

            if (newValue != null) {
                txtCode.setText(newValue.getId());
                txtDescription.setText(newValue.getDescription());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQty() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(actionEvent -> btnSave.fire());
        txtDescription.requestFocus();
        txtDescription.setOnAction(actionEvent -> txtUnitPrice.requestFocus());
        txtUnitPrice.setOnAction(actionEvent -> txtQtyOnHand.requestFocus());
        loadAllItem();

    }

    private void loadAllItem() {
        tblItems.getItems().clear();
        try {
            ArrayList<ItemDTO> allItem = itemBO.getAllItem();

            for (ItemDTO itemDTO : allItem) {
                tblItems.getItems().add(new ItemTM(itemDTO.getId(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQty()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtCode.setEditable(false);
        txtDescription.setEditable(true);
        txtUnitPrice.setEditable(true);
        txtQtyOnHand.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("jdjd");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("save");
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblItems.getSelectionModel().getSelectedItem().getId();
        try{
            if (!existItem(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such a item with " + id).show();
            }

            itemBO.deleteItem(id);

            tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
            tblItems.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "failed to delete item " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtCode.getText();
        String description = txtDescription.getText();

        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
        int qty = Integer.parseInt(txtQtyOnHand.getText());

        if (btnSave.getText().equalsIgnoreCase("save")){
            try {
                if (existItem(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already exist!!").show();
                }

                itemBO.saveItem(new ItemDTO(id, description, unitPrice, qty));

                tblItems.getItems().add(new ItemTM(id, description, unitPrice, qty));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existItem(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such a item in this id!!").show();
                }

                itemBO.updateItem(new ItemDTO(id, description, unitPrice, qty));

                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setUnitPrice(unitPrice);
                selectedItem.setQty(qty);
                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();

    }

    private boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemBO.existItem(id);
    }

    private String generateNewId() {
        try {
            return itemBO.generateNewId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }

    public void btnNext_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/supplier_form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void txtSearch_OnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText().trim(); // Trim whitespace

        System.out.println("Search Action Triggered");
        System.out.println("ID Entered: " + id);

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Valid Item ID!").show();
            return;
        }

        try {
            Item item = itemBO.search(id);
            if (item != null) {
                txtCode.setText(item.getId());
                txtDescription.setText(item.getDescription());
                txtQtyOnHand.setText(String.valueOf(item.getQty()));
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    public void txtItemUnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.UNIT_PRICE, (JFXTextField) txtUnitPrice);
    }

    public void txtItemQTYOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QTY, (JFXTextField) txtQtyOnHand);
    }
    public void txtItemDescOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.DESC, (JFXTextField) txtDescription);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.UNIT_PRICE, (JFXTextField) txtUnitPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.QTY, (JFXTextField) txtQtyOnHand)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.DESC, (JFXTextField) txtDescription)) return false;
        return true;
    }


}







































