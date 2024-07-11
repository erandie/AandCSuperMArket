package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PurchaseOrder_BO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderDetail_DAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrder_BOImpl implements PurchaseOrder_BO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetail_DAO orderDetailDao = (OrderDetail_DAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getcId(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    @Override
    public ItemDTO searchItems(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getId(), item.getDescription(), item.getUnitPrice(),item.getQty());
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertToDto =  new ArrayList<>();
        for (Customer customer : customerEntityData) {
            convertToDto.add(new CustomerDTO(customer.getcId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }
        return convertToDto;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> entityTypeData = itemDAO.getAll();
        ArrayList<ItemDTO> convertToDto = new ArrayList<>();
        for (Item item : entityTypeData) {
            convertToDto.add(new ItemDTO(item.getId(), item.getDescription(), item.getUnitPrice(), item.getQty()));
        }
        return convertToDto;
    }

    @Override
    public boolean purchaseOrder(OrderDTO dto) {
        Connection connection = null;

        try {
            connection = DbConnection.getDbConnection().getConnection();

            boolean b1 = orderDAO.exist(dto.getOid());
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);

            boolean b2 = orderDAO.add(new Orders(dto.getOid(), dto.getDate(), dto.getCustomerID()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO orderDetailDTO : dto.getOrderDetailDTOS()) {
                OrderDetail orderDetail = new OrderDetail(orderDetailDTO.getoId(), orderDetailDTO.getItemCode(), orderDetailDTO.getQty(), orderDetailDTO.getUnitPrice());
                boolean b3 = orderDetailDao.add(orderDetail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                ItemDTO itemDTO = findItem(orderDetailDTO.getItemCode());
                itemDTO.setQty(itemDTO.getQty() - orderDetailDTO.getQty());

                boolean b = itemDAO.update(new Item(itemDTO.getId(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQty()));
                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true; // Return true when the order is successfully placed

        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false; // Return false in case of exceptions
        }
    }


    @Override
    public ItemDTO findItem(String id) {
        try {
            Item item = itemDAO.search(id);
            return new ItemDTO(item.getId(), item.getDescription(), item.getUnitPrice(), item.getQty());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the item " + id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
