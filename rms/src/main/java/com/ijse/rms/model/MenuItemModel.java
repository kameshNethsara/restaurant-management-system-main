//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.MenuItemDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class MenuItemModel {
//
//    public static String getNextMenuItemId() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT MenuItemID FROM MenuItems ORDER BY MenuItemID DESC LIMIT 1";
//        PreparedStatement pts = connection.prepareStatement(sql);
//        ResultSet rst = pts.executeQuery();
//
//        if (rst.next()) {
//            String string = rst.getString(1); // M001
//            String substring = string.substring(1); // 001
//            int lastIdIndex = Integer.parseInt(substring); // 1
//            int nextIndex = lastIdIndex + 1; // 2
//            return String.format("M%03d", nextIndex); // M002
//        }
//        return "M001";
//    }
//
//    public static boolean saveMenuItem(MenuItemDto menuItemDTO) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO MenuItems VALUES (?, ?, ?, ?, ?, ?)",
//                menuItemDTO.getId(),
//                menuItemDTO.getName(),
//                menuItemDTO.getDescription(),
//                menuItemDTO.getPrice(),
//                //menuItemDTO.getQty(),
//                menuItemDTO.getCategory(),
//                menuItemDTO.getKitchenSection()
//        );
//    }
//
//    public static ArrayList<MenuItemDto> getAllMenuItems() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItems");
//
//        ArrayList<MenuItemDto> menuItems = new ArrayList<>();
//        while (rst.next()) {
//            MenuItemDto menuItem = new MenuItemDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getDouble("Price"),
//                    //rst.getInt("Qty"),
//                    rst.getString("Category"),
//                    rst.getString("KitchenSection")
//            );
//            menuItems.add(menuItem);
//        }
//        return menuItems;
//    }
//
//    public static boolean updateMenuItem(MenuItemDto menuItemDTO) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE MenuItems SET Name=?, Description=?, Price=?, Category=?, KitchenSection=? WHERE MenuItemID=?",
//                menuItemDTO.getName(),
//                menuItemDTO.getDescription(),
//                menuItemDTO.getPrice(),
//               // menuItemDTO.getQty(),
//                menuItemDTO.getCategory(),
//                menuItemDTO.getKitchenSection(),
//                menuItemDTO.getId()
//        );
//    }
//
//    public static boolean deleteMenuItem(String menuItemID) throws SQLException {
//        return SQLUtil.execute("DELETE FROM MenuItems WHERE MenuItemID=?", menuItemID);
//    }
//
//    public static MenuItemDto searchMenuItem(String menuItemID) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItems WHERE MenuItemID=?", menuItemID);
//        if (rst.next()) {
//            return new MenuItemDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getDouble("Price"),
//                   // rst.getInt("Qty"),
//                    rst.getString("Category"),
//                    rst.getString("KitchenSection")
//            );
//        }
//        return null;
//    }
//    public static MenuItemDto searchMenuItemName(String menuItemName) throws SQLException {
//        String sql = "SELECT * FROM MenuItems WHERE Name LIKE ?";
//        ResultSet rst = SQLUtil.execute(sql, "%" + menuItemName.toLowerCase() + "%");
//        if (rst.next()) {
//            return new MenuItemDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("Name"),
//                    rst.getString("Description"),
//                    rst.getDouble("Price"),
//                    rst.getString("Category"),
//                    rst.getString("KitchenSection")
//            );
//        }
//        return null;
//    }
//
//
//}
