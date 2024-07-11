//package repositry;
//
//import db.DbConnection;
//import model.Customer;
//import model.Employee;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmployeeRepo {
//    //public static boolean save(Customer customer) throws SQLException {
//    public static boolean save(Employee employee) throws SQLException {
//        String sql = "INSERT INTO Employee VALUES(?, ?, ?, ?)";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, Employee.getEmpId());
//        pstm.setObject(2, Employee.getName());
//        pstm.setObject(3, Employee.getAddress());
//        pstm.setObject(4, Employee.getContactDetails());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static Employee searchById(String EmpId) throws SQLException {
//        String sql = "SELECT * FROM Employee WHERE EmpId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, EmpId);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            // String CustomerId = resultSet.getString(1);
//            String Name = resultSet.getString(2);
//            String Address = resultSet.getString(3);
//            String ContactDetails = resultSet.getString(4);
//            // String age = resultSet.getString(5);
//
//
//            Employee employee = new Employee(EmpId, Name, Address, ContactDetails);
//
//            return employee;
//        }
//
//        return null;
//    }
//
//    public static boolean update(Employee employee) throws SQLException {
//        String sql = "UPDATE Employee SET Name = ?, Address = ?, ContactDetails = ?  WHERE EmpId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setObject( 1, Employee.getName());
//        pstm.setObject( 2, Employee.getAddress());
//        pstm.setObject( 3, Employee.getContactDetails());
//        // pstm.setObject( 4, customer.getAge());
//        pstm.setObject(4, Employee.getEmpId());
//
//        return pstm.executeUpdate() > 0;
//    }
//
//    public static boolean delete(String EmpId) throws SQLException {
//        String sql = "DELETE FROM Employee WHERE EmpId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, EmpId);
//
//        return pstm.executeUpdate() > 0;
//    }
//
//
//
//    public static List<String> getIds() throws SQLException {
//        String sql = "SELECT EmpId FROM Employee";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String EmpId = resultSet.getString(1);
//            idList.add(EmpId);
//        }
//        return idList;
//    }
//    public static List<Employee> getAll() throws SQLException {
//        String sql = "SELECT * FROM Employee";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection()
//                .prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        List<Employee> empList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String EmpId = resultSet.getString(1);
//            String Name = resultSet.getString(2);
//            String Address = resultSet.getString(3);
//            String ContactDetails = resultSet.getString(4); // Corrected index
//
//            Employee employee = new Employee(EmpId, Name, Address, ContactDetails); // Corrected order
//            empList.add(employee);
//        }
//        return empList;
//    }
//}