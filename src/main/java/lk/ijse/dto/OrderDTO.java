package lk.ijse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String oid;
    private LocalDate date;
    private String customerID;
    private String customerName;
    private BigDecimal orderTotal;

    List<OrderDetailDTO> orderDetailDTOS;

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public List<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public OrderDTO(String orderId, LocalDate orderDate, String cId, List<OrderDetailDTO> orderDetailDTOS) {

    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "oid='" + oid + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderDTO(String oid, LocalDate date, String customerID, String customerName, BigDecimal orderTotal) {
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.orderTotal = orderTotal;
    }
}
