package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer =  new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomer.add(new CustomerDTO(c.getcId(), c.getName(), c.getAddress(), c.getContact()));
        }
        return allCustomer;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getcId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getcId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean existCustomer(String cId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(cId);
    }

    @Override
    public boolean deleteCustomer(String cId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(cId);
    }

    @Override
    public String generateNew_CustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

}
