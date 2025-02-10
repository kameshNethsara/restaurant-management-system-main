package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {

//    public String getNextId() throws SQLException;
//    public boolean save(CustomerViewDto customerDTO) throws SQLException;
//    public ArrayList<CustomerViewDto> getAll() throws SQLException;
//    public boolean update(CustomerViewDto customerDTO) throws SQLException;
//    public  boolean delete(String customerID) throws SQLException;
//    public CustomerViewDto search (String customerID) throws SQLException;
      public  Customer searchCustomerMobile(String customerMobile) throws SQLException;

}
