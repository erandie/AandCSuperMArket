package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier");
        while (resultSet.next()) {
            Supplier supplier = new Supplier(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("contact"), resultSet.getString("description"));
            allSupplier.add(supplier);
        }
        return allSupplier;

    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Supplier (id, name, address, contact, description) VALUES (?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDescription());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET name=?, address=?, contact=?, description=?, id=?", entity.getName(), entity.getAddress(), entity.getContact(), entity.getDescription(), entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Supplier WHERE id=?", id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Supplier ORDER BY id DESC LIMIT 1;");
        if (resultSet.next()) {
            String id = resultSet.getString("id");
            int newSupplierId = Integer.parseInt(id.replace("S00-", "")) +1;
            return String.format("S00-%03d", newSupplierId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE id=?", id);
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier WHERE id=?", id);
        resultSet.next();
        return new Supplier(id, resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("contact"), resultSet.getString("description"));
    }
}


























