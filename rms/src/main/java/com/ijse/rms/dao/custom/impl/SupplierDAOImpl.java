package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.SupplierDAO;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    // Method to get the next supplier ID based on an incremental pattern
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT SupplierID FROM suppliers ORDER BY SupplierID DESC LIMIT 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // e.g., S001
            String substring = lastId.substring(1); // e.g., 001
            int lastIdIndex = Integer.parseInt(substring); // Convert to integer
            int nextIndex = lastIdIndex + 1; // Increment
            return String.format("S%03d", nextIndex); // Format as S002
        }
        return "S001"; // Default ID if no suppliers exist
    }
    @Override
    // Method to save a new supplier
    public boolean save(Supplier supplierEntity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO suppliers (SupplierID, Name, ContactInfo, Phone, Email, Address, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)",
                supplierEntity.getId(),
                supplierEntity.getName(),
                supplierEntity.getContactInfo(),
                supplierEntity.getPhone(),
                supplierEntity.getEmail(),
                supplierEntity.getAddress(),
                supplierEntity.getUserId()
        );
    }
    @Override
    // Method to get all suppliers
    public ArrayList<Supplier> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers");
        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (rst.next()) {
            Supplier supplierEntity = new Supplier(
                    rst.getString("SupplierID"),
                    rst.getString("Name"),
                    rst.getString("ContactInfo"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getString("Address"),
                    rst.getString("UserID")
            );
            supplierList.add(supplierEntity);
        }
        return supplierList;
    }
    @Override
    // Method to update an existing supplier
    public boolean update(Supplier supplierEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE suppliers SET Name=?, ContactInfo=?, Phone=?, Email=?, Address=?, UserID=? WHERE SupplierID=?",
                supplierEntity.getName(),
                supplierEntity.getContactInfo(),
                supplierEntity.getPhone(),
                supplierEntity.getEmail(),
                supplierEntity.getAddress(),
                supplierEntity.getUserId(),
                supplierEntity.getId()
        );
    }
    @Override
    // Method to delete a supplier by ID
    public boolean delete(String supplierID) throws SQLException {
        return SQLUtil.execute("DELETE FROM suppliers WHERE SupplierID=?", supplierID);
    }
    @Override
    // Method to search for a supplier by ID
    public Supplier search(String supplierID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers WHERE SupplierID=?", supplierID);
        if (rst.next()) {
            return new Supplier(
                    rst.getString("SupplierID"),
                    rst.getString("Name"),
                    rst.getString("ContactInfo"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getString("Address"),
                    rst.getString("UserID")
            );
        }
        return null; // Return null if no supplier is found
    }
    @Override
    public Supplier searchSupplierMobile(String supplierMobile) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE Phone LIKE ?";
        ResultSet rst = SQLUtil.execute(sql,"%" + supplierMobile +"%" );
        if (rst.next()) {
            return new Supplier(
                    rst.getString("SupplierID"),
                    rst.getString("Name"),
                    rst.getString("ContactInfo"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getString("Address"),
                    rst.getString("UserID")
            );
        }
        return null;
    }
}
