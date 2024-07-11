//package repositry;
//
//import db.DbConnection;
//import model.Customer;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerRepo {
//    //public static boolean save(Customer customer) throws SQLException {
//    public static boolean save(Customer customer) throws SQLException {
//        String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, customer.getCustomerId());
//        pstm.setObject(2, customer.getName());
//        pstm.setObject(3, customer.getAddress());
//        pstm.setObject(4, customer.getContactDetails());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static Customer searchById(String CustomerId) throws SQLException {
//        String sql = "SELECT * FROM Customer WHERE CustomerId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, CustomerId);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//           // String CustomerId = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String Address = resultSet.getString(3);
//            String ContactDetails = resultSet.getString(4);
//            // String age = resultSet.getString(5);
//
//
//            Customer customer = new Customer(CustomerId, name, Address, ContactDetails);
//
//            return customer;
//        }
//
//        return null;
//    }
//
//    public static boolean update(Customer customer) throws SQLException {
//        String sql = "UPDATE Customer SET name = ?, Address = ?, ContactDetails = ?  WHERE CustomerId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setObject( 1, customer.getName());
//        pstm.setObject( 2, customer.getAddress());
//        pstm.setObject( 3, customer.getContactDetails());
//        // pstm.setObject( 4, customer.getAge());
//        pstm.setObject(4, customer.getCustomerId());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String CustomerId) throws SQLException {
//        String sql = "DELETE FROM Customer WHERE CustomerId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, CustomerId);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT CustomerId FROM Customer";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String CustomerId = resultSet.getString(1);
//            idList.add(CustomerId);
//        }
//        return idList;
//    }
//    public static List<Customer> getAll() throws SQLException {
//        String sql = "SELECT * FROM Customer";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Customer> cusList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String CustomerId = resultSet.getString(1);
//            String name = resultSet.getString(2);
//            String Address = resultSet.getString(3);
//            String ContactDetails = resultSet.getString(4); // Corrected index
//
//            Customer customer = new Customer(CustomerId, name, Address, ContactDetails); // Corrected order
//            cusList.add(customer);
//        }
//        return cusList;
//    }
//}