package lk.ijse.view.tdm;

public class SupplierTM implements Comparable<SupplierTM>{
    private String id;
    private String name;
    private String address;
    private String contact;
    private String description;

    @Override
    public String toString() {
        return "SupplierTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SupplierTM(String id, String name, String address, String contact, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.description = description;
    }

    public SupplierTM() {
    }

    @Override
    public int compareTo(SupplierTM o) {
        return id.compareTo(o.getId());
    }
}
