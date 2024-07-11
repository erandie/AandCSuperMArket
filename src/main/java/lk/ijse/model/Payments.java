package lk.ijse.model;

import java.util.Date;

public class Payments {
    private String PaymentId;
    private String Name;
    private String Address;
    private String ContactDetails;
    private String UserId;
    private Date DOB;

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactDetails() {
        return ContactDetails;
    }

    public void setContactDetails(String contactDetails) {
        ContactDetails = contactDetails;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "PaymentId='" + PaymentId + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactDetails='" + ContactDetails + '\'' +
                ", UserId='" + UserId + '\'' +
                ", DOB=" + DOB +
                '}';
    }

    public Payments(String paymentId, String name, String address, String contactDetails, String userId, Date DOB) {
        this.PaymentId = paymentId;
        this.Name = name;
        this.Address = address;
        this.ContactDetails = contactDetails;
        this.UserId = userId;
        this.DOB = DOB;
    }
}
