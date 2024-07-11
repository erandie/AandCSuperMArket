package lk.ijse.model;

import java.math.BigDecimal;
import java.util.Date;

public class Salary {
    private String SId;
    private String EmpId;
    private BigDecimal Amount;

    @Override
    public String toString() {
        return "Salary{" +
                "SId='" + SId + '\'' +
                ", EmpId='" + EmpId + '\'' +
                ", Amount=" + Amount +
                ", datesOfWorking=" + datesOfWorking +
                ", Address='" + Address + '\'' +
                '}';
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public Date getDatesOfWorking() {
        return datesOfWorking;
    }

    public void setDatesOfWorking(Date datesOfWorking) {
        this.datesOfWorking = datesOfWorking;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Salary(String SId, String empId, BigDecimal amount, Date datesOfWorking, String address) {
        this.SId = SId;
        this.EmpId = empId;
        this.Amount = amount;
        this.datesOfWorking = datesOfWorking;
        this.Address = address;
    }

    private Date datesOfWorking;
    private String Address;

}
