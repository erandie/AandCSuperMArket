package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetail_DAO;
import lk.ijse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetail_DAOImpl implements OrderDetail_DAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implement yet!!");
    }

    @Override
    public boolean add(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderDetails (od_Id, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                entity.getoId(), entity.getItemCode(), entity.getUnitPrice(), entity.getQty());
    }


    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature us not implemented yet!!");
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }

    @Override
    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }

    @Override
    public OrderDetail search(String oid) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature is not implemented yet!!");
    }
}
