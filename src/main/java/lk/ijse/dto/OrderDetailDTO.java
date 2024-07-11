package lk.ijse.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private String oid;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "oId='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public String getoId() {
        return oid;
    }

    public void setoId(String oId) {
        this.oid = oId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public OrderDetailDTO(String oId, String itemCode, int qty, BigDecimal unitPrice) {
        this.oid = oId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetailDTO() {
    }
}