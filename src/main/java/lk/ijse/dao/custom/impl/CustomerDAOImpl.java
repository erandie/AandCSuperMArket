package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomer = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers");
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("cId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("contact"));
            allCustomer.add(customer);
        }
        return allCustomer;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customers (cId, name, address, contact) VALUES (?,?,?,?)",
                entity.getcId(),
                entity.getName(),
                entity.getAddress(),
                entity.getContact());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customers SET name=?, address=?, contact=? WHERE cId=?",
                entity.getName(),
                entity.getAddress(),
                entity.getContact(),
                entity.getcId());
    }

    @Override
    public boolean exist(String cId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cId FROM customers WHERE cId=?", cId);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cId FROM customers ORDER BY cId DESC LIMIT 1;");
        if (resultSet.next()) {
            String cId = resultSet.getString("cId");
            int newCustomerId = Integer.parseInt(cId.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean delete(String cId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customers WHERE cId=?", cId);
    }

    @Override
    public Customer search(String cId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers WHERE cId=?", cId);
        if (resultSet.next()) {
            return new Customer(resultSet.getString("cId"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("contact"));
        } else {
            return null;
        }
    }
}
