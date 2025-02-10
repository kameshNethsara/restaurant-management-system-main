package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.SupplierBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.SupplierDAO;
import com.ijse.rms.dao.custom.impl.SupplierDAOImpl;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.Customer;
import com.ijse.rms.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public String getNextSupplierId() throws SQLException {
        return supplierDAO.getNextId();
    }

    @Override
    public boolean saveSupplier(SupplierViewDto supplierDto) throws SQLException {
        Supplier supplier = new Supplier(
                supplierDto.getId(),
                supplierDto.getName(),
                supplierDto.getContactInfo(),
                supplierDto.getPhone(),
                supplierDto.getEmail(),
                supplierDto.getAddress(),
                supplierDto.getUserId()
        );
        return supplierDAO.save(supplier);
    }

    @Override
    public ArrayList<SupplierViewDto> getAllSuppliers() throws SQLException {
        ArrayList<Supplier> supplierList = supplierDAO.getAllData();
        ArrayList<SupplierViewDto> suppliertoList = new ArrayList<>();

        for (Supplier supplier : supplierList) {
            suppliertoList.add(new SupplierViewDto(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContactInfo(),
                    supplier.getPhone(),
                    supplier.getEmail(),
                    supplier.getAddress(),
                    supplier.getUserId()
            ));
        }

        return suppliertoList;
    }

    @Override
    public boolean updateSupplier(SupplierViewDto supplierDto) throws SQLException {
        Supplier supplier = new Supplier(
                supplierDto.getId(),
                supplierDto.getName(),
                supplierDto.getContactInfo(),
                supplierDto.getPhone(),
                supplierDto.getEmail(),
                supplierDto.getAddress(),
                supplierDto.getUserId()
        );
        return supplierDAO.update(supplier);
    }

    @Override
    public boolean deleteSupplier(String supplierID) throws SQLException {
        return supplierDAO.delete(supplierID);
    }

    @Override
    public SupplierViewDto searchSupplier(String supplierID) throws SQLException {
        Supplier supplier = supplierDAO.search(supplierID);
        if (supplier == null) {
            return null; // Return null if the customer is not found
        }
        return new SupplierViewDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getUserId()
        );
    }

    @Override
    public Supplier searchSupplierMobile(String supplierMobile) throws SQLException {
        return supplierDAO.searchSupplierMobile(supplierMobile);
    }
}
