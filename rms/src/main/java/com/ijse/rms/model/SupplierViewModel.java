//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.SupplierViewDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class SupplierViewModel {
//
//    // Method to get the next supplier ID based on an incremental pattern
//    public static String getNextSupplierId() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT SupplierID FROM suppliers ORDER BY SupplierID DESC LIMIT 1");
//
//        if (rst.next()) {
//            String lastId = rst.getString(1); // e.g., S001
//            String substring = lastId.substring(1); // e.g., 001
//            int lastIdIndex = Integer.parseInt(substring); // Convert to integer
//            int nextIndex = lastIdIndex + 1; // Increment
//            return String.format("S%03d", nextIndex); // Format as S002
//        }
//        return "S001"; // Default ID if no suppliers exist
//    }
//
//    // Method to save a new supplier
//    public boolean saveSupplier(SupplierViewDto supplierDTO) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO suppliers (SupplierID, Name, ContactInfo, Phone, Email, Address, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)",
//                supplierDTO.getId(),
//                supplierDTO.getName(),
//                supplierDTO.getContactInfo(),
//                supplierDTO.getPhone(),
//                supplierDTO.getEmail(),
//                supplierDTO.getAddress(),
//                supplierDTO.getUserId()
//        );
//    }
//
//    // Method to get all suppliers
//    public static ArrayList<SupplierViewDto> getAllSuppliers() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers");
//        ArrayList<SupplierViewDto> supplierList = new ArrayList<>();
//
//        while (rst.next()) {
//            SupplierViewDto supplierDTO = new SupplierViewDto(
//                    rst.getString("SupplierID"),
//                    rst.getString("Name"),
//                    rst.getString("ContactInfo"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getString("Address"),
//                    rst.getString("UserID")
//            );
//            supplierList.add(supplierDTO);
//        }
//        return supplierList;
//    }
//
//    // Method to update an existing supplier
//    public boolean updateSupplier(SupplierViewDto supplierDTO) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE suppliers SET Name=?, ContactInfo=?, Phone=?, Email=?, Address=?, UserID=? WHERE SupplierID=?",
//                supplierDTO.getName(),
//                supplierDTO.getContactInfo(),
//                supplierDTO.getPhone(),
//                supplierDTO.getEmail(),
//                supplierDTO.getAddress(),
//                supplierDTO.getUserId(),
//                supplierDTO.getId()
//        );
//    }
//
//    // Method to delete a supplier by ID
//    public static boolean deleteSupplier(String supplierID) throws SQLException {
//        return SQLUtil.execute("DELETE FROM suppliers WHERE SupplierID=?", supplierID);
//    }
//
//    // Method to search for a supplier by ID
//    public SupplierViewDto searchSupplier(String supplierID) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM suppliers WHERE SupplierID=?", supplierID);
//        if (rst.next()) {
//            return new SupplierViewDto(
//                    rst.getString("SupplierID"),
//                    rst.getString("Name"),
//                    rst.getString("ContactInfo"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getString("Address"),
//                    rst.getString("UserID")
//            );
//        }
//        return null; // Return null if no supplier is found
//    }
//
//    public static SupplierViewDto searchSupplierMobile(String supplierMobile) throws SQLException {
//        String sql = "SELECT * FROM suppliers WHERE Phone LIKE ?";
//        ResultSet rst = SQLUtil.execute(sql,"%" + supplierMobile +"%" );
//        if (rst.next()) {
//            return new SupplierViewDto(
//                    rst.getString("SupplierID"),
//                    rst.getString("Name"),
//                    rst.getString("ContactInfo"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getString("Address"),
//                    rst.getString("UserID")
//            );
//        }
//        return null;
//    }
//}
