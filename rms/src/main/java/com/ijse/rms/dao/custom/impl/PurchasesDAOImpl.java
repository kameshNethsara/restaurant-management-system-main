package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.PurchasesDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.PurchaseItemDto;
import com.ijse.rms.dto.PurchasesDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchasesDAOImpl implements PurchasesDAO {
    @Override
    public String getNextPurchaseId() throws SQLException {
        String sql = "SELECT PurchaseID FROM Purchases ORDER BY PurchaseID DESC LIMIT 1";
        ResultSet rst = SQLUtil.execute(sql);

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int lastIndex = Integer.parseInt(substring);
            int nextIndex = lastIndex + 1;
            return String.format("P%03d", nextIndex);
        }
        return "P001";
    }
    @Override
    public boolean savePurchase(PurchasesDto purchaseDto, InventoryItemDto inventoryDto) throws SQLException {

        boolean savePurchaseItem = false;
//            if (inventoryItem == null) {
//                inventoryItem = new InventoryItemModel();
//            }
        savePurchaseItem = SQLUtil.execute(
                "INSERT INTO Purchases (PurchaseID, SupplierID, TotalAmount, PurchaseDate) VALUES (?, ?, ?, ?)",
                purchaseDto.getId(),
                purchaseDto.getSupplierId(),
                purchaseDto.getTotalAmount(),
                purchaseDto.getDate()
        );
//            InventoryItemModel.saveInventoryItem(inventoryDto);
        return savePurchaseItem;
    }
    @Override
    public ArrayList<PurchasesDto> getAllPurchases() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Purchases");

        ArrayList<PurchasesDto> purchases = new ArrayList<>();
        while (rst.next()) {
            PurchasesDto purchase = new PurchasesDto(
                    rst.getString("PurchaseID"),
                    rst.getString("SupplierID"),
                    rst.getString("TotalAmount"),
                    rst.getDate("PurchaseDate").toLocalDate()
            );
            purchases.add(purchase);
        }
        return purchases;
    }
    @Override
    public boolean updatePurchase(PurchasesDto purchaseDto) throws SQLException {
        boolean update = SQLUtil.execute(
                "UPDATE Purchases SET SupplierID=?, TotalAmount=?, PurchaseDate=? WHERE PurchaseID=?",
                purchaseDto.getSupplierId(),
                purchaseDto.getTotalAmount(),
                purchaseDto.getDate(),
                purchaseDto.getId()
        );

        return update;
    }
    @Override
    public boolean deletePurchase(String purchaseId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Purchases WHERE PurchaseID=?", purchaseId);
    }
    @Override
    public PurchasesDto searchPurchase(String purchaseId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Purchases WHERE PurchaseID=?", purchaseId);
        if (rst.next()) {
            return new PurchasesDto(
                    rst.getString("PurchaseID"),
                    rst.getString("SupplierID"),
                    rst.getString("TotalAmount"),
                    rst.getDate("PurchaseDate").toLocalDate()
            );
        }
        return null;
    }

//    public boolean savePurchaseTransaction(String purchaseId, String supplierId, double totalAmount, java.sql.Date purchaseDate, ArrayList<PurchaseItemDto> purchaseItems) throws SQLException {
//        Connection connection = null;
//
//        try {
//            // Get database connection
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false); // Begin transaction
//
//            // Insert data into the Purchases table
//            String purchaseQuery = "INSERT INTO Purchases (PurchaseID, SupplierID, TotalAmount, PurchaseDate) VALUES (?, ?, ?, ?)";
//            SQLUtil.execute(
//                    purchaseQuery,
//                    purchaseId,
//                    supplierId,
//                    totalAmount,
//                    purchaseDate
//            );
//
//            // Insert data into the PurchaseItems table
//            String purchaseItemQuery = "INSERT INTO PurchaseItems (PurchaseItemID, InventoryItemID, PurchaseID, Price, Quantity, Status) VALUES (?, ?, ?, ?, ?, ?)";
//            for (PurchaseItemDto item : purchaseItems) {
//                SQLUtil.execute(
//                        purchaseItemQuery,
//                        item.getPurchaseItemId(),
//                        item.getInventoryItemId(),
//                        purchaseId,
//                        item.getPrice(),
//                        item.getQuantity(),
//                        item.getStatus()
//                );
//            }
//
//            connection.commit(); // Commit transaction if all operations are successful
//            return true;
//
//        } catch (SQLException e) {
//            if (connection != null) {
//                connection.rollback(); // Rollback on error
//            }
//            e.printStackTrace(); // Replace with a logging framework in production
//            throw e;
//
//        } finally {
//            if (connection != null) {
//                connection.setAutoCommit(true); // Reset auto-commit mode
//                connection.close(); // Close the connection
//            }
//        }
//    }
}
