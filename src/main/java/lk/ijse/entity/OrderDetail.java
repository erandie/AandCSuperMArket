package lk.ijse.entity;

import java.math.BigDecimal;

public class OrderDetail {
    private String oid;
    private String itemCode;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "oId='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public OrderDetail() {
    }

    private int qty;
    private BigDecimal unitPrice;

    public OrderDetail(String oId, String itemCode, int qty, BigDecimal unitPrice) {
        this.oid = oId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getoId() {
        return oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQty() {
        return qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
