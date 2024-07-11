//package repositry;
//
//import db.DbConnection;
//import model.PlaceOrder;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class placeOrderRepo {
//    public static boolean placeOrder(PlaceOrder po) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        connection.setAutoCommit(false);
//
//        try {
//            boolean isOrderSaved = OrderRepo.save(po.getOrder());
//            if (isOrderSaved) {
//
//                //  boolean isQtyUpdated = ItemRepo.updateQty(po.getOderList().get());
//                // if (isQtyUpdated) {
//                boolean isOrderDetailSaved = OrderDetailsRepo.save(po.getOrderDetailsList());
//                if (isOrderDetailSaved) {
//                    connection.commit();
//                    return true;
//                }
//                //}
//            }
//            connection.rollback();
//            return false;
//        } catch (Exception e) {
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//}