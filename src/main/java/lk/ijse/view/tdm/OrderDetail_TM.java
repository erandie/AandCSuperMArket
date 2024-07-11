package lk.ijse.view.tdm;

import java.math.BigDecimal;

public class OrderDetail_TM {
    private String oid;
    private String description;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public OrderDetail_TM() {

    }

    @Override
    public String toString() {
        return "OrderDetail_TM{" +
                "od_Id='" + oid + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }

    public String getOd_Id() {
        return oid;
    }

    public void setOd_Id(String od_Id) {
        this.oid = od_Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderDetail_TM(String od_Id, String description, int qty, BigDecimal unitPrice, BigDecimal total) {
        this.oid = od_Id;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }
}
