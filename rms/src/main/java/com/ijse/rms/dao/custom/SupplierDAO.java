package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {

//    public String getNextSupplierId() throws SQLException;
//    public boolean saveSupplier(SupplierViewDto supplierDTO) throws SQLException;
//    public ArrayList<SupplierViewDto> getAllSuppliers() throws SQLException;
//    public boolean updateSupplier(SupplierViewDto supplierDTO) throws SQLException;
//    public boolean deleteSupplier(String supplierID) throws SQLException;
//    public SupplierViewDto searchSupplier(String supplierID) throws SQLException;
    public Supplier searchSupplierMobile(String supplierMobile) throws SQLException;

}
