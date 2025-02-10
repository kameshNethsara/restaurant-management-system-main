package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.MenuItemIngredentDAO;
import com.ijse.rms.dto.MenuItemIngredentsDto;
import com.ijse.rms.entity.MenuItemIngredent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemIngredentDAOImpl implements MenuItemIngredentDAO {
    @Override
    public boolean saveMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException {
        boolean result = false;
        try {
            result = SQLUtil.execute(
                    "INSERT INTO MenuItemIngredients (MenuItemID, InventoryItemID, QuantityNeeded) VALUES (?, ?, ?)",
//                    menuItemIngredentsDto.getMenuItemId(),
//                    menuItemIngredentsDto.getInventoryId(),
//                    menuItemIngredentsDto.getQtyNeeded()

                    menuItemID,inventoryItemID,quantityNeeded

            );
            System.out.println("Query Success: " + result);
        } catch (SQLException e) {
            e.printStackTrace(); // To log the error
            System.err.println("Error while saving menu item ingredients: " + e.getMessage());
        }
        return result;
    }

    @Override
    // Update MenuItemIngredients
    public boolean updateMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException {
        String sql = "UPDATE MenuItemIngredients SET QuantityNeeded=? WHERE MenuItemID=? AND InventoryItemID=?";
        return SQLUtil.execute(
                sql,
//                menuItemIngredentsDto.getQtyNeeded(),
//                menuItemIngredentsDto.getMenuItemId(),
//                menuItemIngredentsDto.getInventoryId()
                quantityNeeded,menuItemID,inventoryItemID
        );
    }
    @Override
    // Delete MenuItemIngredients
    public boolean deleteMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM MenuItemIngredients WHERE MenuItemID=? AND InventoryItemID=?",
                menuItemId,
                inventoryId
        );
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(MenuItemIngredent DTO) throws SQLException {
        return false;
    }

    @Override
    // Get All MenuItemIngredients
    public ArrayList<MenuItemIngredent> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItemIngredients");
        ArrayList<MenuItemIngredent> menuItemIngredients = new ArrayList<>();

        while (rst.next()) {
            menuItemIngredients.add(new MenuItemIngredent(
                    rst.getString("MenuItemID"),
                    rst.getString("InventoryItemID"),
                    rst.getDouble("QuantityNeeded")
            ));
        }
        return menuItemIngredients;
    }

    @Override
    public boolean update(MenuItemIngredent DTO) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException {
        return false;
    }

    @Override
    public MenuItemIngredent search(String customerID) throws SQLException {
        return null;
    }

    @Override
    // Search by MenuItemID and InventoryItemID
    public MenuItemIngredent searchMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException {
        ResultSet rst = SQLUtil.execute(
                "SELECT * FROM MenuItemIngredients WHERE MenuItemID=? AND InventoryItemID=?",
                menuItemId,
                inventoryId
        );

        if (rst.next()) {
            return new MenuItemIngredent(
                    rst.getString("MenuItemID"),
                    rst.getString("InventoryItemID"),
                    rst.getInt("QuantityNeeded")
            );
        }
        return null;
    }
    @Override
    // Get Ingredients for a Specific MenuItemID
    public ArrayList<MenuItemIngredent> getIngredientsForMenuItem(String menuItemId) throws SQLException {
        ResultSet rst = SQLUtil.execute(
                "SELECT * FROM MenuItemIngredients WHERE MenuItemID=?",
                menuItemId
        );
        ArrayList<MenuItemIngredent> menuItemIngredients = new ArrayList<>();

        while (rst.next()) {
            menuItemIngredients.add(new MenuItemIngredent(
                    rst.getString("MenuItemID"),
                    rst.getString("InventoryItemID"),
                    rst.getInt("QuantityNeeded")
            ));
        }
        return menuItemIngredients;
    }
}
