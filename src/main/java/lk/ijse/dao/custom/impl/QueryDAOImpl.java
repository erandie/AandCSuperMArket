package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.CustomEntity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrder(String oId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Orders.oid, Orders.date, Orders.customerID, OrderDetail.oid, OrderDetail.itemCode, OrderDetail.qty, OrderDetail.unitPrice from Orders inner join OrderDetail on Orders.oid=OrderDetail.oid where Orders.oid = ?", oId);
        ArrayList<CustomEntity> allRecords = new ArrayList<>();
        while(resultSet.next()) {
            String oid1 = resultSet.getString("oid");
            String date = resultSet.getString("date");
            String customerId = resultSet.getString("customerID");
            String itemCode = resultSet.getString("qty");
            int qty = resultSet.getInt("qty");
            BigDecimal unitPrice = resultSet.getBigDecimal("unitPrice");

            LocalDate orderDate = LocalDate.parse(date);

            CustomEntity custom = new CustomEntity(oid1, LocalDate.parse(date), customerId, itemCode, qty, unitPrice);
            allRecords.add(custom);
        }
        return allRecords;
    }
}
