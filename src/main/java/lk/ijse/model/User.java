package lk.ijse.model;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactDetails='" + ContactDetails + '\'' +
                '}';
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public User(String userId, String name, String address, String contactDetails) {
        this.UserId = userId;
        this.Name = name;
        this.Address = address;
        this.ContactDetails = contactDetails;
    }

    private String UserId;
    private String Name;
    private String Address;
    private String ContactDetails;
}
