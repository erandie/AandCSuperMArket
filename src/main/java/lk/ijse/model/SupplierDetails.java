package lk.ijse.model;

public class SupplierDetails {
    private String ProductId;

    @Override
    public String toString() {
        return "SupplierDetails{" +
                "ProductId='" + ProductId + '\'' +
                ", SupplierId='" + SupplierId + '\'' +
                '}';
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public SupplierDetails(String productId, String supplierId) {
        ProductId = productId;
        SupplierId = supplierId;
    }

    private String SupplierId;
}
