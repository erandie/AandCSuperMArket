package lk.ijse.dao;

import lk.ijse.dao.custom.OrderDetail_DAO;
import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER, ITEM, SUPPLIER, EMPLOYEE, ORDER, ORDER_DETAILS, SALLARY, QUERY_DAO
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetail_DAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

}
