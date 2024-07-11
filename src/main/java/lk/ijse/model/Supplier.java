//package lk.ijse.model;
//
////import lk.ijse.TM.SupplierTm;
//
//public class Supplier extends SupplierTm {
//    private static String SupplierId;
//    private static String Name;
//    private static String Location;
//    private static String ContactDetails;
//
//    @Override
//    public String toString() {
//        return "Supplier{" +
//                "SupplierId='" + SupplierId + '\'' +
//                ", Name='" + Name + '\'' +
//                ", Location='" + Location + '\'' +
//                ", ContactDetails='" + ContactDetails + '\'' +
//                ", UserId='" + UserId + '\'' +
//                '}';
//    }
//
//    public String getSupplierId() {
//        return SupplierId;
//    }
//
//    public void setSupplierId(String supplierId) {
//        SupplierId = supplierId;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        this.Name = name;
//    }
//
//    public static String getLocation() {
//        return Location;
//    }
//
//    public void setLocation(String location) {
//        Location = location;
//    }
//
//    public String getContactDetails() {
//        return ContactDetails;
//    }
//
//    public void setContactDetails(String contactDetails) {
//        ContactDetails = contactDetails;
//    }
//
//    public String getUserId() {
//        return UserId;
//    }
//
//    public void setUserId(String userId) {
//        UserId = userId;
//    }
//
//    public Supplier(String supplierId, String name, String location, String contactDetails) {
//        this.SupplierId = supplierId;
//        this.Name = name;
//        this.Location = location;
//        this.ContactDetails = contactDetails;
//       // this.UserId = userId;
//    }
//
//    private String UserId;
//
//}
//package controller;
//
//import com.jfoenix.controls.JFXButton;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import db.DbConnection;
//import model.*;
//import repositry.CustomerRepo;
//import repositry.ItemRepo;
//import repositry.OrderRepo;
//import repositry.placeOrderRepo;
//import TM.CartTm;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.*;
//
//public class PlaceOrderFormController {
//
//    public TextField txtOrderId;
//    @FXML
//    private Button btnAddToCart;
//
//    //  @FXML
//    // private Button btnBack;
//
//    @FXML
//    private Button btnPlaceOrder;
//
//    @FXML
//    private ComboBox<String> cmbCode;
//
//    @FXML
//    private ComboBox<String> cmbCustomerId;
//
//    @FXML
//    private TableColumn<?, ?> colAction;
//
//    @FXML
//    private TableColumn<?, ?> colDescription;
//
//    @FXML
//    private TableColumn<?, ?> colQty;
//
//    @FXML
//    private TableColumn<?, ?> colTotal;
//
//    @FXML
//    private TableColumn<?, ?> colUnitPrice;
//
//    @FXML
//    private TableColumn<?, ?> colCode;
//
//    @FXML
//    private TableView<CartTm> tblOrderCart;
//
////    @FXML
////    private Label lblCode;
////
////    @FXML
////    private Label lblCustomerId;
//
//    @FXML
//    private Label lblCustomerName;
//
//    @FXML
//    private Label lblDescription;
//
//    @FXML
//    private Label lblOrderDate;
//
//    @FXML
//    private Label lblOrderId;
//
//    @FXML
//    private Label lblQty;
//
//    @FXML
//    private Label lblQtyOnHand;
//
//    @FXML
//    private Label lblQtyOnHand1;
//
//    @FXML
//    private Label lblUnitPrice;
//
////    @FXML
////    private AnchorPane root;
//
//    @FXML
//    private AnchorPane rootNode;
//
//
//    @FXML
//    private TextField txtQty;
//
//    @FXML
//    private Label lblNetTotal;
//
//    @FXML
//    private Button btnPrintBill;
//
//    String tempId ;
//    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
//
//    public void initialize() {
//        setDate();
//        getCurrentOrderId();
//        getCustomerIds();
//        getItemCodes();
//        setCellValueFactory();
//    }
//
//    private void getCustomerIds() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        try {
//            List<String> idList = CustomerRepo.getIds();
//
//            for(String id : idList) {
//                obList.add(id);
//            }
//
//            cmbCustomerId.setItems(obList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private String generateNextOrderId(String currentId) {
//        if(currentId != null) {
//            String[] split = currentId.split("O");  //" ", "2"
//            int idNum = Integer.parseInt(split[1]);
//            return "O" + ++idNum;
//        }
//        return "O1";
//    }
//
//    private void setDate() {
//        LocalDate now = LocalDate.now();
//        lblOrderDate.setText(String.valueOf(now));
//    }
//
//    private void getCurrentOrderId() {
//        try {
//            String currentId = OrderRepo.getCurrentId();
//
//            String nextOrderId = generateNextOrderId(currentId);
//            lblOrderId.setText(nextOrderId);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    void btnAddToCartOnAction(ActionEvent event) {
//        String ItemId = cmbCode.getValue();
//        String Description = lblDescription.getText();
//        int QtyOnHand = Integer.parseInt(colQty.getText());
//        double Price = Double.parseDouble(lblUnitPrice.getText());
//        double Total = QtyOnHand * Price;
//        JFXButton btnRemove = new JFXButton("remove");
//
//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if(type.orElse(no) == yes) {
//                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex()+1;
//                System.out.println("Selected Index : "+selectedIndex);
//                obList.remove(selectedIndex);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//            }
//        });
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            if(ItemId.equals(colCode.getCellData(i))) {
//
//                CartTm tm = obList.get(i);
//                QtyOnHand += tm.getQtyOnHand();
//                Total = QtyOnHand * Price;
//
//                tm.setQtyOnHand(QtyOnHand);
//                tm.setTotal(Total);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//                return;
//            }
//        }
//
//        CartTm tm = new CartTm(ItemId, Description, QtyOnHand, Price, Total, btnRemove);
//        obList.add(tm);
//
//        tblOrderCart.setItems(obList);
//        calculateNetTotal();
//        txtQty.setText("");
//       /* String code =  cmbCode.getValue();
//        String description = lblDescription.getText();
//        int qty = Integer.parseInt(txtQty.getText());
//        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
//        double total = qty * unitPrice;
//        JFXButton btnRemove = new JFXButton("remove");
//        btnRemove.setCursor(Cursor.HAND);
//
//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if(type.orElse(no) == yes) {
//                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
//                obList.remove(selectedIndex);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//            }
//        });
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            if(code.equals(colCode.getCellData(i))) {
//
//                CartTm tm = obList.get(i);
//                qty += tm.getQty();
//                total = qty * unitPrice;
//
//                tm.setQty(qty);
//                tm.setTotal(total);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//                return;
//            }
//        }
//
//        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
//        obList.add(tm);
//
//        tblOrderCart.setItems(obList);
//        calculateNetTotal();
//        txtQty.setText("");*/
//    }
//    private void calculateNetTotal() {
//        int netTotal = 0;
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            netTotal += (double) colTotal.getCellData(i);
//        }
//        lblNetTotal.setText(String.valueOf(netTotal));
//    }
//
//    private void setCellValueFactory() {
//        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
//
//    }
//
//  /*  @FXML
//    void btnBackOnAction(ActionEvent event) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
//        Stage stage = (Stage) root.getScene().getWindow();
//
//        stage.setScene(new Scene(anchorPane));
//        stage.setTitle("Dashboard Form");
//        stage.centerOnScreen();
//
//    }*/
//
//    @FXML
//    void btnPlaceOrderOnAction(ActionEvent event) {
//        String OrderId = lblOrderId.getText();
//        String Description = lblDescription.getText();
//        String Date = String.valueOf(String.valueOf(LocalDate.now()));
//        String CustomerId = cmbCustomerId.getValue();
//
//        Orders order = new Orders(OrderId, Description, Date, CustomerId);
//
//        List<OrderDetails> odList = new ArrayList<>();
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            CartTm tm = obList.get(i);
//
//            OrderDetails od = new OrderDetails(OrderId, tm.getItemCode(), tm.getQtyOnHand(), tm.getPrice());
//
//            odList.add(od);
//        }
//
//        PlaceOrder po = new PlaceOrder(order, odList);
//
//        tempId = lblOrderId.getText();
//        try {
//            boolean isPlaced = placeOrderRepo.placeOrder(po);
//            if(isPlaced) {
//                initialize();
//                getCurrentOrderId();
//                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Order Placed Unsccessfully!").show();
//
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//    private void getItemCodes() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<String> ItemList = ItemRepo.getIds();
//
//            for (String code : ItemList) {
//                obList.add(String.valueOf(code));
//            }
//            cmbCode.setItems(obList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @FXML
//    void cmbCodeOnAction(ActionEvent event) {
//        String code = cmbCode.getValue();
//
//        try {
//            Item item = ItemRepo.searchById(code);
//            if(item != null) {
//                lblDescription.setText(item.getDescription());
//                lblUnitPrice.setText(String.valueOf(item.getPrice()));
//                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
//            }
//
//            txtQty.requestFocus();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    void cmbCustomerIdOnAction(ActionEvent event) {
//        String id = cmbCustomerId.getValue();
//        try {
//            Customer customer = CustomerRepo.searchById(id);
//
//            System.out.println(customer);
//
//            lblCustomerName.setText(customer.getName());
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
////    @FXML
////    void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
////        printBill();
////
////    }
//
//    public void printBill() throws JRException, SQLException {
//        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Blank_A4_5.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//        Map<String,Object> data = new HashMap<>();
//        data.put("tempId",lblOrderId.getText());
//        data.put("orderId",tempId);
//        data.put("total", lblNetTotal.getText());
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
//        JasperViewer.viewReport(jasperPrint,false);
//    }
//
//
//}


//
//    public TextField txtOrderId;
//    @FXML
//    private Button btnAddToCart;
//
//    //  @FXML
//    // private Button btnBack;
//
//    @FXML
//    private Button btnPlaceOrder;
//
//    @FXML
//    private ComboBox<String> cmbCode;
//
//    @FXML
//    private ComboBox<String> cmbCustomerId;
//
//    @FXML
//    private TableColumn<?, ?> colAction;
//
//    @FXML
//    private TableColumn<?, ?> colDescription;
//
//    @FXML
//    private TableColumn<?, ?> colQty;
//
//    @FXML
//    private TableColumn<?, ?> colTotal;
//
//    @FXML
//    private TableColumn<?, ?> colUnitPrice;
//
//    @FXML
//    private TableColumn<?, ?> colCode;
//
//    @FXML
//    private TableView<CartTm> tblOrderCart;
//
////    @FXML
////    private Label lblCode;
////
////    @FXML
////    private Label lblCustomerId;
//
//    @FXML
//    private Label lblCustomerName;
//
//    @FXML
//    private Label lblDescription;
//
//    @FXML
//    private Label lblOrderDate;
//
//    @FXML
//    private Label lblOrderId;
//
//    @FXML
//    private Label lblQty;
//
//    @FXML
//    private Label lblQtyOnHand;
//
//    @FXML
//    private Label lblQtyOnHand1;
//
//    @FXML
//    private Label lblUnitPrice;
//
////    @FXML
////    private AnchorPane root;
//
//    @FXML
//    private AnchorPane rootNode;
//
//
//    @FXML
//    private TextField txtQty;
//
//    @FXML
//    private Label lblNetTotal;
//
//    @FXML
//    private Button btnPrintBill;
//
//    String tempId ;
//    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
//
//    public void initialize() {
//        setDate();
//        getCurrentOrderId();
//        getCustomerIds();
//        getItemCodes();
//        setCellValueFactory();
//    }
//
//    private void getCustomerIds() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//
//        try {
//            List<String> idList = CustomerRepo.getIds();
//
//            for(String id : idList) {
//                obList.add(id);
//            }
//
//            cmbCustomerId.setItems(obList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private String generateNextOrderId(String currentId) {
//        if(currentId != null) {
//            String[] split = currentId.split("O");  //" ", "2"
//            int idNum = Integer.parseInt(split[1]);
//            return "O" + ++idNum;
//        }
//        return "O1";
//    }
//
//    private void setDate() {
//        LocalDate now = LocalDate.now();
//        lblOrderDate.setText(String.valueOf(now));
//    }
//
//    private void getCurrentOrderId() {
//        try {
//            String currentId = OrderRepo.getCurrentId();
//
//            String nextOrderId = generateNextOrderId(currentId);
//            lblOrderId.setText(nextOrderId);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    void btnAddToCartOnAction(ActionEvent event) {
//        String ItemId = cmbCode.getValue();
//        String Description = lblDescription.getText();
//        int QtyOnHand = Integer.parseInt(colQty.getText());
//        double Price = Double.parseDouble(lblUnitPrice.getText());
//        double Total = QtyOnHand * Price;
//        JFXButton btnRemove = new JFXButton("remove");
//
//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if(type.orElse(no) == yes) {
//                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex()+1;
//                System.out.println("Selected Index : "+selectedIndex);
//                obList.remove(selectedIndex);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//            }
//        });
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            if(ItemId.equals(colCode.getCellData(i))) {
//
//                CartTm tm = obList.get(i);
//                QtyOnHand += tm.getQtyOnHand();
//                Total = QtyOnHand * Price;
//
//                tm.setQtyOnHand(QtyOnHand);
//                tm.setTotal(Total);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//                return;
//            }
//        }
//
//        CartTm tm = new CartTm(ItemId, Description, QtyOnHand, Price, Total, btnRemove);
//        obList.add(tm);
//
//        tblOrderCart.setItems(obList);
//        calculateNetTotal();
//        txtQty.setText("");
//       /* String code =  cmbCode.getValue();
//        String description = lblDescription.getText();
//        int qty = Integer.parseInt(txtQty.getText());
//        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
//        double total = qty * unitPrice;
//        JFXButton btnRemove = new JFXButton("remove");
//        btnRemove.setCursor(Cursor.HAND);
//
//        btnRemove.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if(type.orElse(no) == yes) {
//                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
//                obList.remove(selectedIndex);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//            }
//        });
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            if(code.equals(colCode.getCellData(i))) {
//
//                CartTm tm = obList.get(i);
//                qty += tm.getQty();
//                total = qty * unitPrice;
//
//                tm.setQty(qty);
//                tm.setTotal(total);
//
//                tblOrderCart.refresh();
//                calculateNetTotal();
//                return;
//            }
//        }
//
//        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
//        obList.add(tm);
//
//        tblOrderCart.setItems(obList);
//        calculateNetTotal();
//        txtQty.setText("");*/
//    }
//    private void calculateNetTotal() {
//        int netTotal = 0;
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            netTotal += (double) colTotal.getCellData(i);
//        }
//        lblNetTotal.setText(String.valueOf(netTotal));
//    }
//
//    private void setCellValueFactory() {
//        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
//        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
//
//    }
//
//  /*  @FXML
//    void btnBackOnAction(ActionEvent event) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
//        Stage stage = (Stage) root.getScene().getWindow();
//
//        stage.setScene(new Scene(anchorPane));
//        stage.setTitle("Dashboard Form");
//        stage.centerOnScreen();
//
//    }*/
//
//    @FXML
//    void btnPlaceOrderOnAction(ActionEvent event) {
//        String OrderId = lblOrderId.getText();
//        String Description = lblDescription.getText();
//        String Date = String.valueOf(String.valueOf(LocalDate.now()));
//        String CustomerId = cmbCustomerId.getValue();
//
//        Orders order = new Orders(OrderId, Description, Date, CustomerId);
//
//        List<OrderDetails> odList = new ArrayList<>();
//
//        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
//            CartTm tm = obList.get(i);
//
//            OrderDetails od = new OrderDetails(OrderId, tm.getItemCode(), tm.getQtyOnHand(), tm.getPrice());
//
//            odList.add(od);
//        }
//
//        PlaceOrder po = new PlaceOrder(order, odList);
//
//        tempId = lblOrderId.getText();
//        try {
//            boolean isPlaced = placeOrderRepo.placeOrder(po);
//            if(isPlaced) {
//                initialize();
//                getCurrentOrderId();
//                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Order Placed Unsccessfully!").show();
//
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//    private void getItemCodes() {
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<String> ItemList = ItemRepo.getIds();
//
//            for (String code : ItemList) {
//                obList.add(String.valueOf(code));
//            }
//            cmbCode.setItems(obList);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @FXML
//    void cmbCodeOnAction(ActionEvent event) {
//        String code = cmbCode.getValue();
//
//        try {
//            Item item = ItemRepo.searchById(code);
//            if(item != null) {
//                lblDescription.setText(item.getDescription());
//                lblUnitPrice.setText(String.valueOf(item.getPrice()));
//                lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
//            }
//
//            txtQty.requestFocus();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @FXML
//    void cmbCustomerIdOnAction(ActionEvent event) {
//        String id = cmbCustomerId.getValue();
//        try {
//            Customer customer = CustomerRepo.searchById(id);
//
//            System.out.println(customer);
//
//            lblCustomerName.setText(customer.getName());
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
////    @FXML
////    void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
////        printBill();
////
////    }
//
//    public void printBill() throws JRException, SQLException {
//        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Blank_A4_5.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//        Map<String,Object> data = new HashMap<>();
//        data.put("tempId",lblOrderId.getText());
//        data.put("orderId",tempId);
//        data.put("total", lblNetTotal.getText());
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
//        JasperViewer.viewReport(jasperPrint,false);
//    }
//
//
//}

//package controller;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
////import model.Customer;
//import model.Employee;
////import repositry.CustomerRepo;
//import repositry.EmployeeRepo;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
////import static repositry.CustomerRepo.save;
//import static repositry.EmployeeRepo.save;
//
//public class EmployeeFormController extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginFormController.class.getResource("customer_form.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
//        stage.setTitle("Customer Form!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//
//
//    @FXML
//    private Button btnBack;
//
//    @FXML
//    private Button btnClear;
//
//    @FXML
//    private Button btnDelete;
//
//    @FXML
//    private Button btnSearch;
//
//    @FXML
//    private Button btnUpdate;
//
//    @FXML
//    private Button btnsave;
//
//    @FXML
//    private TableColumn<?, ?> colAction;
//
//    @FXML
//    private TableColumn<?, ?> colAddress;
//
//    @FXML
//    private TableColumn<?, ?> colId;
//
//    @FXML
//    private TableColumn<?, ?> colTel;
//
//    @FXML
//    private TableColumn<?, ?> colname;
//
//    @FXML
//    private TextField txtAddress;
//
//    @FXML
//    private TextField txtContact;
//
//    @FXML
//    private TextField txtEmpId;
//
//    @FXML
//    private TextField txtName;
//
//    @FXML
//    private AnchorPane rootNode;
//
//    @FXML
//    void btnBackOnAction(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
//        Stage stage = (Stage) rootNode.getScene().getWindow();
//
//        stage.setScene(new Scene(anchorPane));
//        stage.setTitle("Dashboard Form!!");
//        stage.centerOnScreen();
//    }
//
//    @FXML
//    void btnClearOnAction(ActionEvent event) {
//        clearFields();
//
//    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) {
//        String EmpId = txtEmpId.getText();
//
//        try {
//            boolean isDeleted = EmployeeRepo.delete(EmpId);
//            if(isDeleted) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//
//    }
//
//    @FXML
//    void btnSaveOnAction(ActionEvent event) {
//        String EmpId = txtEmpId.getText();
//        String Name = txtName.getText();
//        String Address = txtAddress.getText();
//        String ContactDetails = txtContact.getText();
//
//        Employee employee = new Employee(EmpId, Name, Address, ContactDetails);
//
//        try {
//            boolean isSaved = save(employee);
//            if (isSaved) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
//                //clearFields();
//            }
//        } catch (SQLException e) {
//            //throw new RuntimeException(e);
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
//    }
//
//
//    private void setCellValueFactory() {
//        colId.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
//        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        colTel.setCellValueFactory(new PropertyValueFactory<>("ContactDetails"));
//        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
//    }
//
//
//    private void clearFields() {
//        txtEmpId.setText("");
//        txtName.setText("");
//        txtAddress.setText("");
//        txtContact.setText("");
//
//    }
//
//    @FXML
//    void btnSearchOnAction(ActionEvent event) throws SQLException {
//        String EmpId = txtEmpId.getText();
//
//        Employee employee = EmployeeRepo.searchById(EmpId);
//        if (employee != null) {
//            txtEmpId.setText(employee.getEmpId());
//            txtName.setText(employee.getName());
//            txtAddress.setText(employee.getAddress());
//            txtContact.setText(employee.getContactDetails());
//        } else {
//            new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
//        }
//
//    }
//
//    @FXML
//    void btnUpdateOnAction(ActionEvent event) {
//        String EmpId = txtEmpId.getText();
//        String Name = txtName.getText();
//        String Address = txtAddress.getText();
//        String ContactDetails = txtContact.getText();
//
//
//        Employee employee = new Employee(EmpId, Name, Address, ContactDetails);
//
//        try {
//            boolean isUpdated = EmployeeRepo.update(employee);
//            if(isUpdated) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//
//    }
//
//    public void initialize() {
//
//        addRegex(txtEmpId);
//        addRegex(txtName);
//        addRegex(txtAddress);
//        addRegex(txtContact);
//        setCellValueFactory();
//
//    }
//
//    private void addRegex(TextField textField) {
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtEmpId && !newValue.matches("^E.*$")){
//                txtEmpId.setStyle(("-fx-focus-color :#f21e0f "));
//                txtEmpId.clear();
//            }else{
//                txtEmpId.setStyle(("-fx-focus-color :#c4c1c0 "));
//            }
//        });
//
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtName && !newValue.matches("^[A-Za-z]+(?:[\s-][A-Za-z]+)*$")){
//                txtName.setStyle(("-fx-focus-color :#f21e0f "));
//                txtName.clear();
//            }else{
//                txtName.setStyle(("-fx-focus-color :#c4c1c0 "));
//            }
//        });
//
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtAddress && !newValue.matches("^[\\w\\s\\.,#\\-\\/]+$")){
//                txtAddress.setStyle(("-fx-focus-color :#f21e0f "));
//                txtAddress.clear();
//            }else{
//                txtAddress.setStyle(("-fx-focus-color :#c4c1c0 "));
//            }
//        });
//
//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtContact && !newValue.matches("^\\+?[0-9\\s-]+$")){
//                txtContact.setStyle(("-fx-focus-color :#f21e0f "));
//                txtContact.clear();
//            }else{
//                txtContact.setStyle(("-fx-focus-color :#c4c1c0 "));
//            }
//        });
//
//    }}
//


//    @FXML
//    void btnLogOutOnAction(ActionEvent event) throws IOException {
////        Parent rootNode = FXMLLoader.load(getClass().getResource("login_form.fxml"));
////        Stage stage = new Stage();
////        stage.setTitle("Login Form!");
////        Scene scene = new Scene(rootNode);
////        stage.setScene(scene);
////        //Stage stage = (Stage) rootNode.getScene().getWindow();
////        stage.show();
//
//        try {
//            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
//            root.getChildren().clear();
//            root.getChildren().add(rootNode);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//


//    @FXML
//    void btnCusOnAction(ActionEvent event) throws IOException {
//        navigateToTheCustomerForm();
    //}


//  @FXML
//    void btnSalaryOnAction(ActionEvent event) throws IOException {
////        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/salaryFormInBlack.fxml"));
////        Stage stage = (Stage) rootNode.getScene().getWindow();
////
////        stage.setScene(new Scene(anchorPane));
////        stage.setTitle("Salary Form");
////        stage.centerOnScreen();
//
//      try {
//          AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/salaryFormInBlack.fxml"));
//          root.getChildren().clear();
//          root.getChildren().add(rootNode);
//      } catch (IOException e) {
//          throw new RuntimeException(e);
//      }
//
//    }

//    private void navigateToTheCustomerForm() throws IOException {
//        try {
//            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/newCustomer_form.fxml"));
//            rootNode.getChildren().clear();
//            rootNode.getChildren().add(rootNode);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

////    @FXML
////    void btnOrderOnAction(ActionEvent event) throws IOException {
////        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeOrder_form.fxml"));
////        Stage stage = (Stage) rootNode.getScene().getWindow();
////
////        stage.setScene(new Scene(anchorPane));
////        stage.setTitle("Place Order Form");
////        stage.centerOnScreen();
////
////    }
////    @FXML
////    void btnBackOnAction(ActionEvent event) throws IOException {
////        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/placeOrder_form.fxml"));
////        Stage stage = (Stage) rootNode.getScene().getWindow();
////
////        stage.setScene(new Scene(anchorPane));
////        stage.setTitle("Supplier Form");
////        stage.centerOnScreen();
////
////    }
////    @FXML
////    void btnLoginOnAction(ActionEvent event) {
////
////    }

//    @FXML
//    private void playMouseEnterAnimation(MouseEvent event) {
//        if (event.getSource() instanceof ImageView) {
//            ImageView icon = (ImageView) event.getSource();
//
//            switch (icon.getId()) {
//                case "imgCustomer":
//                    lblMenu.setText("Manage Customers");
//                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.pos.lk.ijse.pos.view customers");
//                    break;
//                case "imgItem":
//                    lblMenu.setText("Manage Items");
//                    lblDescription.setText("Click to add, edit, delete, search or lk.ijse.pos.lk.ijse.pos.view items");
//                    break;
//                case "imgOrder":
//                    lblMenu.setText("Place Orders");
//                    lblDescription.setText("Click here if you want to place a new order");
//                    break;
//                case "imgViewOrders":
//                    lblMenu.setText("Search Orders");
//                    lblDescription.setText("Click if you want to search orders");
//                    break;
//            }
//
//            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
//            scaleT.setToX(1.2);
//            scaleT.setToY(1.2);
//            scaleT.play();
//
//            DropShadow glow = new DropShadow();
//            glow.setColor(Color.CORNFLOWERBLUE);
//            glow.setWidth(20);
//            glow.setHeight(20);
//            glow.setRadius(20);
//            icon.setEffect(glow);
//        }
//    }

//            }

//
//    public void btnPrintBillOnAction(ActionEvent event) throws JRException, SQLException {
//        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/Employee_A4.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//
//            JasperPrint jasperPrint =
//                    JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
//            JasperViewer.viewReport(jasperPrint,false);
//
//        }
//    }
//
////    @FXML
////    void linkRegistrationOnAction(ActionEvent event) {
////
