package lk.ijse.view.tdm;

public class EmployeeTM implements Comparable<EmployeeTM> {
    private String empId;
    private String name;
    private String address;
    private String contact;

    public EmployeeTM() {

    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public EmployeeTM(String empId, String name, String address, String contact) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public int compareTo(EmployeeTM o) {
        return empId.compareTo(o.getEmpId());
    }
}
