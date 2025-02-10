//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.InventoryItemDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class InventoryItemModel {
//
//    public static String getNextInventoryItemId() throws SQLException {
//        String sql = "SELECT InventoryItemID FROM InventoryItems ORDER BY InventoryItemID DESC LIMIT 1";
//        ResultSet rst = SQLUtil.execute(sql);
//
//        if (rst.next()) {
//            String lastId = rst.getString(1);
//            String substring = lastId.substring(1);
//            int lastIndex = Integer.parseInt(substring);
//            int nextIndex = lastIndex + 1;
//            return String.format("I%03d", nextIndex);
//        }
//        return "I001";
//    }
//
//    public static boolean saveInventoryItem(InventoryItemDto itemDto) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO InventoryItems (InventoryItemID, Name, Description, Quantity, Unit) VALUES (?, ?, ?, ?, ?)",
//                itemDto.getId(),
//                itemDto.getName(),
//                itemDto.getDescription(),
//                itemDto.getQty(),
//                itemDto.getUnit()
//        );
//    }
//
//    public static ArrayList<InventoryItemDto> getAllInventoryItems() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM InventoryItems");
//
//        ArrayList<InventoryItemDto> items = new ArrayList<>();
//        while (rst.next()) {
//            InventoryItemDto item = new InventoryItemDto(
//                    rst.getString("InventoryItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getInt("Quantity"),
//                    rst.getString("Unit")
//            );
//            items.add(item);
//        }
//        return items;
//    }
//
//    public static boolean updateInventoryItem(InventoryItemDto itemDto) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE InventoryItems SET Name=?, Description=?, Quantity=?, Unit=? WHERE InventoryItemID=?",
//                itemDto.getName(),
//                itemDto.getDescription(),
//                itemDto.getQty(),
//                itemDto.getUnit(),
//                itemDto.getId()
//        );
//    }
//
//    public static boolean deleteInventoryItem(String itemId) throws SQLException {
//        return SQLUtil.execute("DELETE FROM InventoryItems WHERE InventoryItemID=?", itemId);
//    }
//
//    public static InventoryItemDto searchInventoryItem(String itemId) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM InventoryItems WHERE InventoryItemID=?", itemId);
//        if (rst.next()) {
//            return new InventoryItemDto(
//                    rst.getString("InventoryItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getInt("Quantity"),
//                    rst.getString("Unit")
//            );
//        }
//        return null;
//    }
//
//    public static InventoryItemDto searchInventoryItemName(String inventoryItemName) throws SQLException {
//        String sql = "SELECT * FROM InventoryItems WHERE Name LIKE ?";
//        ResultSet rst = SQLUtil.execute(sql, "%" + inventoryItemName.toLowerCase() + "%");
//        if (rst.next()) {
//            return new InventoryItemDto(
//                    rst.getString("InventoryItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getInt("Quantity"),
//                    rst.getString("Unit")
//            );
//        }
//        return null;
//    }
//
//
//}
