//package repositry;
//
//import db.DbConnection;
//import model.Customer;
//import model.Item;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemRepo {
//    public static boolean save(Item item) throws SQLException {
//        String sql = "INSERT INTO Item VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, Item.getItemId());
//        pstm.setObject(2, Item.getDescription());
//        pstm.setObject(3, Item.getQtyOnHand());
//        pstm.setObject(4, Item.getPrice());
//
//        return pstm.executeUpdate() > 0;
//
//
//    }
//    public static Item searchById(String ItemId) throws SQLException {
//        String sql = "SELECT * FROM Item WHERE ItemId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, ItemId);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            ItemId = resultSet.getString(1);
//            String Description = resultSet.getString(2);
//            Integer QtyOnHand = Integer.valueOf(resultSet.getString(3));
//            Double Price = Double.valueOf(resultSet.getString(4));
//
//
//            Item item = new Item(ItemId, Description, QtyOnHand, Price);
//
//            return item;
//        }
//
//        return null;
//    }
//    public static boolean update(Item item) throws SQLException {
//        String sql = "UPDATE Item SET Description = ?, QtyOnHand= ?, Price=?  WHERE ItemId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setObject(1, item.getItemId());
//        pstm.setObject(2, item.getDescription());
//        pstm.setObject(3, item.getQtyOnHand());
//        pstm.setObject(4, item.getPrice());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String ItemId) throws SQLException {
//        String sql = "DELETE FROM Item WHERE ItemId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, ItemId);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT ItemId FROM Item";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String ItemId = resultSet.getString(1);
//            idList.add(ItemId);
//        }
//        return idList;
//    }
//    public static List<Item> getAll() throws SQLException {
//        String sql = "SELECT * FROM Item";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Item> itemList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String ItemId = resultSet.getString(1);
//            String Description = resultSet.getString(2);
//            Integer QtyOnHand = Integer.valueOf(resultSet.getString(3));
//            Double Price = Double.valueOf(resultSet.getString(4));
//
//
//
//            Item item = new Item(ItemId, Description, QtyOnHand, Price); // Corrected order
//            itemList.add(item);
//        }
//        return itemList;
//    }
//
//
//}