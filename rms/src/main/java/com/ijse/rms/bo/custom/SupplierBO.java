package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public String getNextSupplierId() throws SQLException;
    public boolean saveSupplier(SupplierViewDto supplierDTO) throws SQLException;
    public ArrayList<SupplierViewDto> getAllSuppliers() throws SQLException;
    public boolean updateSupplier(SupplierViewDto supplierDTO) throws SQLException;
    public boolean deleteSupplier(String supplierID) throws SQLException;
    public SupplierViewDto searchSupplier(String supplierID) throws SQLException;
    public Supplier searchSupplierMobile(String supplierMobile) throws SQLException;

}
