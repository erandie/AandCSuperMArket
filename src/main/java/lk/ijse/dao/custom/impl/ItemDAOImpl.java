package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem =  new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item");
        while (resultSet.next()) {
            allItem.add(new Item(resultSet.getString("id"), resultSet.getString("description"), resultSet.getBigDecimal("unitPrice"), resultSet.getInt("qty")));
        }
        return allItem;

    }

    @Override
    public boolean add(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (id, description, unitPrice, qty) VALUES (?,?,?,?)", entity.getId(), entity.getDescription(), entity.getUnitPrice(), entity.getQty());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qty=? WHERE id=?", entity.getDescription(), entity.getUnitPrice(), entity.getQty(), entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Item WHERE id=?", id);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Item ORDER BY id DESC LIMIT 1;");
        if (resultSet.next()) {
            String id = resultSet.getString("id");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE id=?", id);
    }

    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Item WHERE id=?", id+"");
        resultSet.next();
        return new Item(id + "", resultSet.getString("description"), resultSet.getBigDecimal("unitPrice"), resultSet.getInt("qty"));
    }
}





























