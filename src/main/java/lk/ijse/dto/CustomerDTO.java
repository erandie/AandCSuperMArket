package lk.ijse.dto;

public class CustomerDTO {
    private String cId;

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

    private String name;
    private String address;
    private String contact;

    public CustomerDTO() {

    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cId='" + cId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }


    public CustomerDTO(String cId, String name, String address, String contact) {
        this.cId = cId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
}
