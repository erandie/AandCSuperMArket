package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSupplier = new ArrayList<>();
        ArrayList<Supplier> allEntityData = supplierDAO.getAll();

        for (Supplier supplier : allEntityData) {
            allSupplier.add(new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getContact(), supplier.getDescription()));
        }
        return allSupplier;
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getId(),dto.getName(), dto.getAddress(), dto.getContact(), dto.getDescription()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getDescription()));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }
}
