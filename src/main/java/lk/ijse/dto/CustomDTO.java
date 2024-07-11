package lk.ijse.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomDTO {
    //customer
    private String cId;
    private String name;
    private String address;
    private String contact;

    //item
    private String id;
    private String description;
    private BigDecimal unitPrice;
    private int qty;

    //employee
    private String empId;

    //orders
    private String oid;
    private LocalDate date;
    private String customerID;

    //orderDetails
    private String oId;
    private String itemCode;

    public CustomDTO() {

    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "cId='" + cId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", empId='" + empId + '\'' +
                ", oid='" + oid + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                ", oId='" + oId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                '}';
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public CustomDTO(String cId, String name, String address, String contact, String id, String description, BigDecimal unitPrice, int qty, String empId, String oid, LocalDate date, String customerID, String oId, String itemCode) {
        this.cId = cId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.empId = empId;
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
        this.oId = oId;
        this.itemCode = itemCode;
    }
}
