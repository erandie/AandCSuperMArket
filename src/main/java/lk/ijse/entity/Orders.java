package lk.ijse.entity;

import java.time.LocalDate;

public class Orders {
    private String oid;
    private LocalDate date;
    private String customerID;

    public Orders() {

    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
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

    public Orders(String oid, LocalDate date, String customerID) {
        this.oid = oid;
        this.date = date;
        this.customerID = customerID;
    }
}
