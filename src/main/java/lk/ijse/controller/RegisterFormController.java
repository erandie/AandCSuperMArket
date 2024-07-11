package lk.ijse.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
//import db.DbConnection;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController{
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAddress;

    @FXML
    private AnchorPane root;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String UserId = txtUserId.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String ContactDetails = txtTel.getText();
        String Password = txtPw.getText();



        try {
            boolean isSaved = saveUser(UserId, Name, Address, ContactDetails, Password);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User added!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean saveUser(String UserId, String Name, String Address, String ContactDetails, String Password) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO User VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getDbConnection().getConnection();

        PreparedStatement pstm = connection.prepareCall(sql);
        pstm.setObject(1, UserId);
        pstm.setObject(2, Name);
        pstm.setObject(3, Address);
        pstm.setObject(4, ContactDetails);
        pstm.setObject(5, Password);

        return pstm.executeUpdate() > 0;

    }

    public void linkLoginOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form2.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form!!");
        stage.centerOnScreen();

    }

    public void initialize() {

        addRegex(txtUserId);
        addRegex(txtName);
        addRegex(txtTel);
        addRegex(txtAddress);
        addRegex(txtPw);

    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtUserId && !newValue.matches("^U.*$")){
                new Alert(Alert.AlertType.INFORMATION,"Start with U").show();
                txtUserId.clear();
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtAddress && !newValue.matches("^[A-Za-z]+(?:[\s-][A-Za-z]+)*$")){
                new Alert(Alert.AlertType.INFORMATION,"First letter should be capital").show();
                txtAddress.clear();
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtName && !newValue.matches("^[A-Za-z]+(?:[\s-][A-Za-z]+)*$")){
                new Alert(Alert.AlertType.INFORMATION,"First letter should be capital").show();
                txtName.clear();
            }
        });

//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtPw && !newValue.matches("^[A-z|\\s]{3,}$")){
//                new Alert(Alert.AlertType.INFORMATION,"Only letters").show();
//                txtPw.clear();
//            }
//        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtTel && !newValue.matches("\\d+(\\.\\d{2})?")){
                new Alert(Alert.AlertType.INFORMATION,"example - +765823106 or 0765823106").show();
                txtTel.clear();
            }
        });
    }
}
