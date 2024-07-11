//package repositry;
//
//import db.DbConnection;
//import model.Orders;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class OrderRepo {
//    public static String getCurrentId() throws SQLException {
//        //String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";
//        String sql="select concat('O',max(cast(substring(orderId,2)as unsigned)))as max_order_id from Orders";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            String orderId = resultSet.getString(1);
//            return orderId;
//        }
//        return null;
//    }
//
//    public static boolean save(Orders order) throws SQLException {
//        String sql = "INSERT INTO Orders VALUES(?, ?, ?)";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        pstm.setString(1, Orders.getOrderId());
//
//        pstm.setDate(2, Orders.getDate());
//        pstm.setString(3, Orders.getCustomerId());
//
//        return pstm.executeUpdate() > 0;
//    }
//}