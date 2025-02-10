//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.MenuItemIngredentsDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class MenuItemIngredentsModel {
//
//    // Save MenuItemIngredients
//    public static boolean saveMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO MenuItemIngredients VALUES (?, ?, ?)",
//                menuItemIngredentsDto.getMenuItemId(),
//                menuItemIngredentsDto.getInventoryId(),
//                menuItemIngredentsDto.getQtyNeeded()
//        );
//    }
//
//    // Update MenuItemIngredients
//    public static boolean updateMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE MenuItemIngredients SET QuantityNeeded=? WHERE MenuItemID=? AND InventoryItemID=?",
//                menuItemIngredentsDto.getQtyNeeded(),
//                menuItemIngredentsDto.getMenuItemId(),
//                menuItemIngredentsDto.getInventoryId()
//        );
//    }
//
//    // Delete MenuItemIngredients
//    public static boolean deleteMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException {
//        return SQLUtil.execute(
//                "DELETE FROM MenuItemIngredients WHERE MenuItemID=? AND InventoryItemID=?",
//                menuItemId,
//                inventoryId
//        );
//    }
//
//    // Get All MenuItemIngredients
//    public static ArrayList<MenuItemIngredentsDto> getAllMenuItemIngredents() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItemIngredients");
//        ArrayList<MenuItemIngredentsDto> menuItemIngredients = new ArrayList<>();
//
//        while (rst.next()) {
//            menuItemIngredients.add(new MenuItemIngredentsDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("InventoryItemID"),
//                    rst.getDouble("QuantityNeeded")
//            ));
//        }
//        return menuItemIngredients;
//    }
//
//    // Search by MenuItemID and InventoryItemID
//    public static MenuItemIngredentsDto searchMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException {
//        ResultSet rst = SQLUtil.execute(
//                "SELECT * FROM MenuItemIngredients WHERE MenuItemID=? AND InventoryItemID=?",
//                menuItemId,
//                inventoryId
//        );
//
//        if (rst.next()) {
//            return new MenuItemIngredentsDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("InventoryItemID"),
//                    rst.getInt("QuantityNeeded")
//            );
//        }
//        return null;
//    }
//
//    // Get Ingredients for a Specific MenuItemID
//    public static ArrayList<MenuItemIngredentsDto> getIngredientsForMenuItem(String menuItemId) throws SQLException {
//        ResultSet rst = SQLUtil.execute(
//                "SELECT * FROM MenuItemIngredients WHERE MenuItemID=?",
//                menuItemId
//        );
//        ArrayList<MenuItemIngredentsDto> menuItemIngredients = new ArrayList<>();
//
//        while (rst.next()) {
//            menuItemIngredients.add(new MenuItemIngredentsDto(
//                    rst.getString("MenuItemID"),
//                    rst.getString("InventoryItemID"),
//                    rst.getInt("QuantityNeeded")
//            ));
//        }
//        return menuItemIngredients;
//    }
//}
