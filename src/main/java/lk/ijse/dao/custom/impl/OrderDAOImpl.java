package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("this feature is not implemented yet!!");
    }

    @Override
    public boolean add(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders (id, date, customerId) VALUES (?,?,?)", entity.getOid(), entity.getDate(), entity.getCustomerID());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id from Orders WHERE id=?", oid);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Orders ORDER BY id DESC LIMIT 1;");
        return resultSet.next() ? String.format("OID-%03d", (Integer.parseInt(resultSet.getString("id").replace("OID-", "")) + 1)) : "OID-001";
    }


    @Override
    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }

    @Override
    public Orders search(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }
}