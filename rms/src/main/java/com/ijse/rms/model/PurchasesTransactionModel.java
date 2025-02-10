//package com.ijse.rms.model;
//
//import com.ijse.rms.tdm.PurchaseTransactionTM;
//import com.ijse.rms.dao.SQLUtil;
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.PurchaseItemDto;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class PurchasesTransactionModel {
//
////    public static ArrayList<PurchaseTransactionTM> getAllPurchaseItems() throws SQLException {
////        String sql = """
////        SELECT
////            pi.PurchaseItemID,
////            pi.InventoryItemID,
////            ii.Name AS InventoryItemName,
////            pi.PurchaseID,
////            pi.Status AS PurchaseItemStatus,
////            p.PurchaseDate,
////            pi.Quantity AS PurchaseItemQty,
////            pi.Price AS PurchaseItemPrice,
////            p.TotalAmount AS PurchaseTotalPrice
////        FROM
////            PurchaseItems pi
////        JOIN
////            InventoryItems ii ON pi.InventoryItemID = ii.InventoryItemID
////        JOIN
////            Purchases p ON pi.PurchaseID = p.PurchaseID;
////    """;
////
////        ResultSet rst = SQLUtil.execute(sql);
////
////        ArrayList<PurchaseTransactionTM> purchaseItems = new ArrayList<>();
////        while (rst.next()) {
////            purchaseItems.add(new PurchaseTransactionTM(
////                    rst.getString("PurchaseID"),          // supplierId mapped to PurchaseID
////                    rst.getString("InventoryItemID"),
////                    rst.getString("InventoryItemName"),
////                    rst.getString("PurchaseID"),
////                    rst.getString("PurchaseItemStatus"),
////                    rst.getDate("PurchaseDate").toLocalDate(),
////                    rst.getInt("PurchaseItemQty"),
////                    rst.getDouble("PurchaseItemPrice"),
////                    rst.getDouble("PurchaseTotalPrice")
////            ));
////        }
////        return purchaseItems;
////    }
////
//
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
//}
