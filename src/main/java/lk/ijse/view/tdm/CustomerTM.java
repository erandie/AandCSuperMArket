package lk.ijse.view.tdm;

public class CustomerTM implements Comparable<CustomerTM>{
    private String cId;
    private String name;
    private String address;
    private String contact;

    // Constructor
    public CustomerTM(String cId, String name, String address, String contact) {
        this.cId = cId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    // Getter and setter for cId
    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    // Getters and setters for other properties
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

    @Override
    public int compareTo(CustomerTM o) {
        return cId.compareTo(o.getCId());
    }
}
