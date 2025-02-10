package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public String getNextCustomer() throws SQLException;
    public boolean saveCustomer(CustomerViewDto customerDto) throws SQLException;
    public ArrayList<CustomerViewDto> getAllCustomer() throws SQLException;
    public boolean updateCustomer(CustomerViewDto customerDto) throws SQLException;
    public boolean deleteCustomer(String customerID) throws SQLException;
    public CustomerViewDto searchCustomer(String customerID) throws SQLException;
    public CustomerViewDto searchCustomerMobile(String customerMobile) throws SQLException;
}
