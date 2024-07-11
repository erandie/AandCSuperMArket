package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean existCustomer(String cId) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String cId) throws SQLException, ClassNotFoundException;

    public String generateNew_CustomerId() throws SQLException, ClassNotFoundException;

    public Customer search(String id) throws SQLException, ClassNotFoundException;

}
