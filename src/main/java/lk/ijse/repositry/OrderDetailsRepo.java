//package repositry;
//
//import db.DbConnection;
//import model.OrderDetails;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//public class OrderDetailsRepo {
//    public static boolean save(List<OrderDetails> odList) throws SQLException {
//        for (OrderDetails od : odList) {
//            boolean isSaved = save(od);
//            if(!isSaved) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean save(OrderDetails od) throws SQLException {
//        String sql = "INSERT INTO OrderDetails VALUES(?, ?, ?, ?)";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        pstm.setString(1, od.getOrderId());
//        pstm.setDouble(2, Double.parseDouble(od.getSupplierId()));
//
//        return pstm.executeUpdate() > 0;    //false ->  |
//    }
//
//}