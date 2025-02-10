package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.CustomerDAO;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public String getNextId() throws SQLException {
        //    Connection connection = DBConnection.getInstance().getConnection();

        String sql = "select CustomerID from customers " +
                "order by CustomerID desc " +
                "limit  1";

        //    PreparedStatement pts = connection.prepareStatement(sql);
        //    ResultSet rst = pts.executeQuery();

        ResultSet rst = SQLUtil.execute(sql);

        if(rst.next()) {
            String string = rst.getString(1); // C001
            String substring = string.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIIndex = lastIdIndex+1; // 2
            String newId = String.format("C%03d", nextIIndex); // C002
            return newId;
        }
        return "C001";

    }
    @Override
    public boolean save(Customer customerEntity) throws SQLException {
        return SQLUtil.execute(
                "insert into Customers values (?,?,?,?,?,?)",
                customerEntity.getId(),
                customerEntity.getName(),
                customerEntity.getPhone(),
                customerEntity.getEmail(),
                customerEntity.getAddress(),
                customerEntity.getUserId()
        );
    }
    @Override
    public  ArrayList<Customer> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from Customers");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer customerEntity = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            customers.add(customerEntity);
        }
        return customers;
    }
    @Override
    public boolean update(Customer customerEntity) throws SQLException {
        return SQLUtil.execute(
                "update Customers set Name=?,Phone=?, Email=?, Address=?, UserID=? where CustomerID=?",
                customerEntity.getName(),
                customerEntity.getPhone(),
                customerEntity.getEmail(),
                customerEntity.getAddress(),
                customerEntity.getUserId(),
                customerEntity.getId()

        );
    }
    @Override
    public boolean delete(String customerID) throws SQLException {
        return SQLUtil.execute("delete from customers where CustomerID=?",customerID);
    }
    @Override
    public Customer search(String customerID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customers WHERE CustomerID=?", customerID);
        if (rst.next()) {
            return new Customer(
                    rst.getString("CustomerID"),
                    rst.getString("Name"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getString("Address"),
                    rst.getString("UserID")
            );
        }
        return null;
    }
    //----------------------------------------------------------------------------------//
   @Override
    public Customer searchCustomerMobile(String customerMobile) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Phone LIKE ?";
        ResultSet rst = SQLUtil.execute(sql,"%" + customerMobile +"%" );
        if (rst.next()) {
            return new Customer(
                    rst.getString("CustomerID"),
                    rst.getString("Name"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getString("Address"),
                    rst.getString("UserID")
            );
        }
        return null;
    }

}
