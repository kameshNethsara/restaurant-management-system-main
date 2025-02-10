//
//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.InventoryItemDto;
//import com.ijse.rms.dto.PurchasesDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class PurchasesModel {
//
//        public static String getNextPurchaseId() throws SQLException {
//            String sql = "SELECT PurchaseID FROM Purchases ORDER BY PurchaseID DESC LIMIT 1";
//            ResultSet rst = SQLUtil.execute(sql);
//
//            if (rst.next()) {
//                String lastId = rst.getString(1);
//                String substring = lastId.substring(1);
//                int lastIndex = Integer.parseInt(substring);
//                int nextIndex = lastIndex + 1;
//                return String.format("P%03d", nextIndex);
//            }
//            return "P001";
//        }
//
//        public boolean savePurchase(PurchasesDto purchaseDto, InventoryItemDto inventoryDto) throws SQLException {
//
//            boolean savePurchaseItem = false;
////            if (inventoryItem == null) {
////                inventoryItem = new InventoryItemModel();
////            }
//            savePurchaseItem = SQLUtil.execute(
//                    "INSERT INTO Purchases (PurchaseID, SupplierID, TotalAmount, PurchaseDate) VALUES (?, ?, ?, ?)",
//                    purchaseDto.getId(),
//                    purchaseDto.getSupplierId(),
//                    purchaseDto.getTotalAmount(),
//                    purchaseDto.getDate()
//            );
////            InventoryItemModel.saveInventoryItem(inventoryDto);
//            return savePurchaseItem;
//        }
//
//        public static ArrayList<PurchasesDto> getAllPurchases() throws SQLException {
//            ResultSet rst = SQLUtil.execute("SELECT * FROM Purchases");
//
//            ArrayList<PurchasesDto> purchases = new ArrayList<>();
//            while (rst.next()) {
//                PurchasesDto purchase = new PurchasesDto(
//                        rst.getString("PurchaseID"),
//                        rst.getString("SupplierID"),
//                        rst.getString("TotalAmount"),
//                        rst.getDate("PurchaseDate").toLocalDate()
//                );
//                purchases.add(purchase);
//            }
//            return purchases;
//        }
//
//        public boolean updatePurchase(PurchasesDto purchaseDto) throws SQLException {
//            boolean update = SQLUtil.execute(
//                    "UPDATE Purchases SET SupplierID=?, TotalAmount=?, PurchaseDate=? WHERE PurchaseID=?",
//                    purchaseDto.getSupplierId(),
//                    purchaseDto.getTotalAmount(),
//                    purchaseDto.getDate(),
//                    purchaseDto.getId()
//            );
//
//            return update;
//        }
//
//        public static boolean deletePurchase(String purchaseId) throws SQLException {
//            return SQLUtil.execute("DELETE FROM Purchases WHERE PurchaseID=?", purchaseId);
//        }
//
//        public PurchasesDto searchPurchase(String purchaseId) throws SQLException {
//            ResultSet rst = SQLUtil.execute("SELECT * FROM Purchases WHERE PurchaseID=?", purchaseId);
//            if (rst.next()) {
//                return new PurchasesDto(
//                        rst.getString("PurchaseID"),
//                        rst.getString("SupplierID"),
//                        rst.getString("TotalAmount"),
//                        rst.getDate("PurchaseDate").toLocalDate()
//                );
//            }
//            return null;
//        }
//
//}
//
//
