package lk.ijse.model;

public class Item {
    private static String ItemId;
    private static String Description;
    private static Integer QtyOnHand;
    private static Double Price;

    @Override
    public String toString() {
        return "Item{" +
                "ItemId='" + ItemId + '\'' +
                ", Description='" + Description + '\'' +
                ", QtyOnHand=" + QtyOnHand +
                ", Price=" + Price +
                '}';
    }

    public static String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public static String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static Integer getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(Integer qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public static Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Item(String itemId, String description, Integer qtyOnHand, Double price) {
        ItemId = itemId;
        Description = description;
        QtyOnHand = qtyOnHand;
        Price = price;
    }
}