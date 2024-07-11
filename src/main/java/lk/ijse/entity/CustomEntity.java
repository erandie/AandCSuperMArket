package lk.ijse.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomEntity {
    //customer
    private String cId;
    private String name;
    private String address;
    private String contact;

    //item
    private String Id;
    private String description;
    private BigDecimal unitPrice;
    private int qty;

    public CustomEntity(String oid1, LocalDate parse, String customerId, String itemCode, int qty, BigDecimal unitPrice) {

    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "cId='" + cId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", Id='" + Id + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
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
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public CustomEntity(String cId, String name, String address, String contact, String id, String description, BigDecimal unitPrice, int qty) {
        this.cId = cId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        Id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
