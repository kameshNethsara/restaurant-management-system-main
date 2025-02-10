package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.MenuItemDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.entity.MenuItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemDAOImpl implements MenuItemDAO {
    @Override
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT MenuItemID FROM MenuItems ORDER BY MenuItemID DESC LIMIT 1";
        PreparedStatement pts = connection.prepareStatement(sql);
        ResultSet rst = pts.executeQuery();

        if (rst.next()) {
            String string = rst.getString(1); // M001
            String substring = string.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIndex = lastIdIndex + 1; // 2
            return String.format("M%03d", nextIndex); // M002
        }
        return "M001";
    }
    @Override
    public boolean save(MenuItems menuItemEntity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO MenuItems VALUES (?, ?, ?, ?, ?, ?)",
                menuItemEntity.getId(),
                menuItemEntity.getName(),
                menuItemEntity.getDescription(),
                menuItemEntity.getPrice(),
                //menuItemDTO.getQty(),
                menuItemEntity.getCategory(),
                menuItemEntity.getKitchenSection()
        );
    }
    @Override
    public ArrayList<MenuItems> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItems");

        ArrayList<MenuItems> menuItems = new ArrayList<>();
        while (rst.next()) {
            MenuItems menuItem = new MenuItems(
                    rst.getString("MenuItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getDouble("Price"),
                    //rst.getInt("Qty"),
                    rst.getString("Category"),
                    rst.getString("KitchenSection")
            );
            menuItems.add(menuItem);
        }
        return menuItems;
    }
    @Override
    public boolean update(MenuItems menuItemEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE MenuItems SET Name=?, Description=?, Price=?, Category=?, KitchenSection=? WHERE MenuItemID=?",
                menuItemEntity.getName(),
                menuItemEntity.getDescription(),
                menuItemEntity.getPrice(),
                // menuItemDTO.getQty(),
                menuItemEntity.getCategory(),
                menuItemEntity.getKitchenSection(),
                menuItemEntity.getId()
        );
    }
    @Override
    public boolean delete(String menuItemID) throws SQLException {
        return SQLUtil.execute("DELETE FROM MenuItems WHERE MenuItemID=?", menuItemID);
    }
    @Override
    public MenuItems search(String menuItemID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItems WHERE MenuItemID=?", menuItemID);
        if (rst.next()) {
            return new MenuItems(
                    rst.getString("MenuItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getDouble("Price"),
                    // rst.getInt("Qty"),
                    rst.getString("Category"),
                    rst.getString("KitchenSection")
            );
        }
        return null;
    }
    @Override
    public MenuItems searchMenuItemName(String menuItemName) throws SQLException {
        String sql = "SELECT * FROM MenuItems WHERE Name LIKE ?";
        ResultSet rst = SQLUtil.execute(sql, "%" + menuItemName.toLowerCase() + "%");
        if (rst.next()) {
            return new MenuItems(
                    rst.getString("MenuItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getDouble("Price"),
                    rst.getString("Category"),
                    rst.getString("KitchenSection")
            );
        }
        return null;
    }
//    @Override
//    public boolean doesMenuItemExist(String menuItemID) throws SQLException {
//        //try (Connection connection = DBConnection.getConnection()) {
//            String sql = "SELECT COUNT(*) FROM MenuItems WHERE MenuItemID = ?";
////            PreparedStatement stmt = connection.prepareStatement(query);
////            stmt.setString(1, menuItemID);
//
//            ResultSet rs = SQLUtil.execute(sql);
//            if (rs.next()) {
//                // If count > 0, it means the MenuItemID exists
//                return rs.getInt(1) > 0;
//            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////            // Log or handle the exception as needed
////        }
//        return false; // If there's an error or no record found, return false
//    }
}
