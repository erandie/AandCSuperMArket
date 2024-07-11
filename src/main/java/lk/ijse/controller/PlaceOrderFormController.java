package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PurchaseOrder_BO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.view.tdm.OrderDetail_TM;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaceOrderFormController {
    @FXML
    public JFXButton btbAdd;
    public JFXButton btnPlacepOrder;
    public JFXComboBox<String> cmbCustomerId;
    public JFXComboBox<String> cmnItrmId;
    public Label lblTotal;
    public Label lblDate;
    public Label lblId;
    public AnchorPane root;
    public TableView<OrderDetail_TM> tblOrderDetails;
    public TextField txtCustomerName;
    public TextField txtItemDesc;
    public TextField txtQty;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TableColumn<OrderDetail_TM, String> colCode;
    public TableColumn<OrderDetail_TM, String> colDescription;
    public TableColumn<OrderDetail_TM, Integer> colQty;
    public TableColumn<OrderDetail_TM, BigDecimal> colUnitPrice;
    public TableColumn<OrderDetail_TM, BigDecimal> colTotal;
    public TableColumn<OrderDetail_TM, Button> colDelete;
    public String orderId;

    PurchaseOrder_BO purchaseOrderBo = (PurchaseOrder_BO) BOFactory.getBoFactory().getBO(BOFactory.BOYTypes.PURCHASE_ORDER);

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("od_Id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        colDelete.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(actionEvent -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblId.setText("Order ID: " + orderId);
        lblDate.setText(LocalDate.now().toString());
        btnPlacepOrder.setDisable(true);
        setUpTextFieldProperties();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButton();
            if (newValue != null) {
                loadCustomerDetails(newValue);
            } else {
                txtCustomerName.clear();
            }
        });

        cmnItrmId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            txtQty.setEditable(newItemCode != null);
            btbAdd.setDisable(newItemCode == null);

            if (newItemCode != null) {
                loadItemDetails(newItemCode);
            } else {
                clearItemDetails();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if (selectedOrderDetail != null) {
                cmnItrmId.setDisable(true);
                cmnItrmId.setValue(selectedOrderDetail.getOd_Id());
                btbAdd.setText("Update");
                txtQty.setText(Integer.toString(selectedOrderDetail.getQty()));
            } else {
                resetItemSelection();
            }
        });

        loadAllCustomerIds();
        loadAllItemIds();
    }

    private void setUpTextFieldProperties() {
        txtCustomerName.setFocusTraversable(false);
        txtCustomerName.setEditable(false);
        txtItemDesc.setFocusTraversable(false);
        txtItemDesc.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQty.setFocusTraversable(false);
        txtQty.setOnAction(actionEvent -> btbAdd.fire());
        txtQty.setEditable(false);
        btbAdd.setDisable(true);

        //txtQty.clear();
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
    }

    private void loadCustomerDetails(String customerId) {
        try {
            if (!existCustomer(customerId)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + customerId).show();
                return;
            }

            CustomerDTO customerDTO = purchaseOrderBo.searchCustomer(customerId);
            txtCustomerName.setText(customerDTO.getName());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + customerId + ": " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemDetails(String itemCode) {
        try {
            if (!existItem(itemCode)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + itemCode).show();
                return;
            }

            ItemDTO itemDTO = purchaseOrderBo.searchItems(itemCode);
            txtItemDesc.setText(itemDTO.getDescription());
            txtUnitPrice.setText(itemDTO.getUnitPrice().setScale(2).toString());

            Optional<OrderDetail_TM> optionalOrderDetailTm = tblOrderDetails.getItems().stream().filter(detail -> detail.getOd_Id().equals(itemCode)).findFirst();
            int qtyOnHand = itemDTO.getQty() - (optionalOrderDetailTm.map(OrderDetail_TM::getQty).orElse(0));
            txtQtyOnHand.setText(Integer.toString(qtyOnHand));
            txtQty.clear();
            txtQty.requestFocus();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearItemDetails() {
        cmbCustomerId.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        txtItemDesc.clear();
        txtQty.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }

    private void resetItemSelection() {
        btbAdd.setText("Add");
        cmnItrmId.setDisable(false);
        cmnItrmId.getSelectionModel().clearSelection();
        txtQty.clear();
        //txtQty.setText("qty");
        txtQtyOnHand.clear();
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return purchaseOrderBo.existItem(code);
    }

    private String generateNewOrderId() {
        try {
            return purchaseOrderBo.generateOrderId();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order Id: " + e.getMessage()).show();
        }
        return "OID-001";
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlacepOrder.setDisable((cmbCustomerId.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }


    private void calculateTotal() {
        BigDecimal total = tblOrderDetails.getItems().stream().map(OrderDetail_TM::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        lblTotal.setText("Total: " + total);
    }

    private void loadAllItemIds() {
        try {
            ArrayList<ItemDTO> allItems = purchaseOrderBo.getAllItems();
            for (ItemDTO i : allItems) {
                cmnItrmId.getItems().add(i.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load item ids: " + e.getMessage()).show();
        }
    }

    private void loadAllCustomerIds() {
        try {
            ArrayList<CustomerDTO> allCustomers = purchaseOrderBo.getAllCustomer();
            for (CustomerDTO c : allCustomers) {
                cmbCustomerId.getItems().add(c.getcId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids: " + e.getMessage()).show();
        }
    }

    private boolean existCustomer(String cId) throws SQLException, ClassNotFoundException {
        return purchaseOrderBo.existCustomer(cId);
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        String itemCode = cmnItrmId.getSelectionModel().getSelectedItem();
        String desc = txtItemDesc.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal total = unitPrice.multiply(new BigDecimal(qty)).setScale(2);

        Optional<OrderDetail_TM> optionalOrderDetailTm = tblOrderDetails.getItems().stream().filter(detail -> detail.getOd_Id().equals(itemCode)).findFirst();

        if (optionalOrderDetailTm.isPresent()) {
            OrderDetail_TM orderDetailTm = optionalOrderDetailTm.get();

            if (btbAdd.getText().equalsIgnoreCase("Update")) {
                orderDetailTm.setQty(qty);
                orderDetailTm.setTotal(total);
                tblOrderDetails.getSelectionModel().clearSelection();
            } else {
                orderDetailTm.setQty(orderDetailTm.getQty() + qty);
                total = new BigDecimal(orderDetailTm.getQty()).multiply(unitPrice).setScale(2);
                orderDetailTm.setTotal(total);
            }
            tblOrderDetails.refresh();
        } else {
            tblOrderDetails.getItems().add(new OrderDetail_TM(itemCode, desc, qty, unitPrice, total));
        }

        cmnItrmId.getSelectionModel().clearSelection();
        cmnItrmId.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
        try {
            boolean b = saveOrder(orderId, LocalDate.now(), cmbCustomerId.getValue(),
                    tblOrderDetails.getItems().stream().map(tm -> new OrderDetailDTO(orderId, tm.getOd_Id(), tm.getQty(), tm.getUnitPrice())).collect(Collectors.toList()));

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbCustomerId.getSelectionModel().clearSelection();
        cmnItrmId.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        txtQty.clear();
        calculateTotal();
    }

    private boolean saveOrder(String orderId, LocalDate orderDate, String cId, List<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        OrderDTO orderDTO = new OrderDTO(orderId, orderDate, cId, orderDetailDTOS);
        return purchaseOrderBo.purchaseOrder(orderDTO);
    }

    private void resetOrderForm() {
        orderId = generateNewOrderId();
        lblId.setText("Order Id: " + orderId);
        cmbCustomerId.getSelectionModel().clearSelection();
        cmnItrmId.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        txtQty.clear();
        txtQtyOnHand.clear();
        calculateTotal();
    }
}
