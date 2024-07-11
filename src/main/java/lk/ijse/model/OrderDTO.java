package lk.ijse.model;

import java.time.LocalDate;

public class OrderDTO {
    private String oid;
    private LocalDate orderDate;
    private String customerId;
    private String customerName;
    private String orderTotal;

    public OrderDTO() {

    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oId='" + oid + '\'' +
                ", orderDate=" + orderDate +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderTotal='" + orderTotal + '\'' +
                '}';
    }

    public String getoId() {
        return oid;
    }

    public void setoId(String oId) {
        this.oid = oId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderDTO(String oId, LocalDate orderDate, String customerId, String customerName, String orderTotal) {
        this.oid = oId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderTotal = orderTotal;
    }
}
