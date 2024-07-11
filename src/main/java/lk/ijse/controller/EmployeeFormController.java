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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;
import lk.ijse.view.tdm.EmployeeTM;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeFormController {
    public JFXButton btnAddNew;
    public JFXButton btnDelete;
    public JFXButton btnNext;
    public JFXButton btnSave;
    public JFXButton btnSearch;
    public TableColumn<?, ?> colAddress;
    public TableColumn<?, ?> colContact;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colStatus;
    public ImageView imgHome;
    public AnchorPane root;
    public TableView<EmployeeTM> tblEmp;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtId;
    public TextField txtName;
    public TextField txtSearch;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOYTypes.EMPLOYEE);

    public void initialize() {
        tblEmp.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("empId"));
        tblEmp.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmp.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmp.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));

        txtName.setOnAction(actionEvent -> txtContact.requestFocus());
        txtContact.setOnAction(actionEvent -> txtAddress.requestFocus());

        initUI();

        tblEmp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtId.setText(newValue.getEmpId());
                txtName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                txtAddress.setText(newValue.getAddress());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtContact.setDisable(false);
                txtAddress.setDisable(false);
            }
        });

        txtAddress.setOnAction(actionEvent -> btnSave.fire());
        loadAllEmployees();

    }

    private void loadAllEmployees() {
        tblEmp.getItems().clear();
        try {
            ArrayList<EmployeeDTO> allEmps = employeeBO.getAllEmployee();

            for (EmployeeDTO e : allEmps) {
                tblEmp.getItems().add(new EmployeeTM(e.getEmpId(), e.getName(), e.getAddress(), e.getContact()));
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
        txtContact.clear();
        txtAddress.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtContact.setDisable(true);
        txtAddress.setDisable(true);
        txtId.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void navigatetoHome(MouseEvent mouseEvent) {

    }

    public void txtSearch_OnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText().trim(); // Trim whitespace

        System.out.println("Search Action Triggered");
        System.out.println("ID Entered: " + id);

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Valid Employee ID!").show();
            return;
        }

        try {
            Employee employee = employeeBO.search(id);
            if (employee != null) {
                txtId.setText(employee.getEmpId());
                txtName.setText(employee.getName());
                txtAddress.setText(employee.getAddress());
                txtContact.setText(employee.getContact());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class not found: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    public void btnNext_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/placeOrder_form2.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtId.clear();
        txtId.setText(genarateNewId());
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblEmp.getSelectionModel().clearSelection();
    }

    private String genarateNewId() {
        try {
            return employeeBO.generateNewEmpId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate new Employee Id!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblEmp.getItems().isEmpty()) {
            return "E00-001";
        } else {
            String id = getLastEmpId();
            int newEmpId = Integer.parseInt(id.replace("E", "")) +1;
            return String.format("E00-%03d", newEmpId);
        }
    }

    private String getLastEmpId() {
        List<EmployeeTM> tempEmpList  = new ArrayList<>(tblEmp.getItems());
        Collections.sort(tempEmpList);
        return tempEmpList.get(tempEmpList.size() - 1).getEmpId();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existEmp(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already exists!!").show();
                }

                employeeBO.addEmployee(new EmployeeDTO(id, name, contact, address));

                tblEmp.getItems().add(new EmployeeTM(id, name, contact, address));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the employee!!").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!existEmp(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such a employee from " + id).show();
                }

                employeeBO.updateEmployee(new EmployeeDTO(id, name, contact, address));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the employee!!").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            EmployeeTM selectedEmp = tblEmp.getSelectionModel().getSelectedItem();
            selectedEmp.setName(name);
            selectedEmp.setContact(contact);
            selectedEmp.setAddress(address);
            tblEmp.refresh();
        }

        btnAddNew.fire();

    }

    private boolean existEmp(String id) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(id);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblEmp.getSelectionModel().getSelectedItem().getEmpId();
        try {
            if (!existEmp(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such a employee from " + id).show();
            }

            employeeBO.deleteCustomer(id);

            tblEmp.getItems().remove(tblEmp.getSelectionModel().getSelectedItem());
            tblEmp.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete Employee!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void txtEmployeeContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, (JFXTextField) txtContact);
    }

    public void txtEmployeeAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, (JFXTextField) txtAddress);
    }
    public void txtEmployeeNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, (JFXTextField) txtName);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.CONTACT, (JFXTextField) txtContact)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ADDRESS, (JFXTextField) txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME, (JFXTextField) txtName)) return false;
        return true;
    }

}//jfxtextfield




























