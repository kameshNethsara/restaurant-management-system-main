package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.CustomerDAO;
import com.ijse.rms.dao.custom.impl.CustomerDAOImpl;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String getNextCustomer() throws SQLException {
        return customerDAO.getNextId();
    }
    @Override
    public boolean saveCustomer(CustomerViewDto customerDto) throws SQLException {
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getPhone(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getUserId()
        );
        return customerDAO.save(customer);
    }
    @Override
    public ArrayList<CustomerViewDto> getAllCustomer() throws SQLException {
        ArrayList<Customer> customerList = customerDAO.getAllData();
        ArrayList<CustomerViewDto> customerViewDtoList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerViewDtoList.add(new CustomerViewDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getUserId()
            ));
        }

        return customerViewDtoList;
    }

    @Override
    public boolean updateCustomer(CustomerViewDto customerDto) throws SQLException {
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getPhone(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getUserId()
        );
        return customerDAO.update(customer);
    }
    @Override
    public boolean deleteCustomer(String customerID) throws SQLException {
        return customerDAO.delete(customerID);
    }
    @Override
    public CustomerViewDto searchCustomer(String customerID) throws SQLException {
        Customer customer = customerDAO.search(customerID);

        if (customer == null) {
            return null; // Return null if the customer is not found
        }

        return new CustomerViewDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getUserId()
        );
    }
    @Override
    public CustomerViewDto searchCustomerMobile(String customerMobile) throws SQLException {
        Customer customer = customerDAO.search(customerMobile);

        if (customer == null) {
            return null; // Return null if the customer is not found
        }

        return new CustomerViewDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getUserId()
        );
    }


}
