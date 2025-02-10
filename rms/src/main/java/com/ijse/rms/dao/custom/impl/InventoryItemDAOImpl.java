package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.entity.InventoryItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryItemDAOImpl implements InventoryItemDAO {
    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT InventoryItemID FROM InventoryItems ORDER BY InventoryItemID DESC LIMIT 1";
        ResultSet rst = SQLUtil.execute(sql);

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int lastIndex = Integer.parseInt(substring);
            int nextIndex = lastIndex + 1;
            return String.format("I%03d", nextIndex);
        }
        return "I001";
    }
    @Override
    public boolean save(InventoryItem inventoryItemEntity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO InventoryItems (InventoryItemID, Name, Description, Quantity, Unit) VALUES (?, ?, ?, ?, ?)",
                inventoryItemEntity.getId(),
                inventoryItemEntity.getName(),
                inventoryItemEntity.getDescription(),
                inventoryItemEntity.getQty(),
                inventoryItemEntity.getUnit()
        );
    }
    @Override
    public ArrayList<InventoryItem> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM InventoryItems");

        ArrayList<InventoryItem> items = new ArrayList<>();
        while (rst.next()) {
            InventoryItem item = new InventoryItem(
                    rst.getString("InventoryItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getInt("Quantity"),
                    rst.getString("Unit")
            );
            items.add(item);
        }
        return items;
    }
    @Override
    public boolean update(InventoryItem inventoryItemEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE InventoryItems SET Name=?, Description=?, Quantity=?, Unit=? WHERE InventoryItemID=?",
                inventoryItemEntity.getName(),
                inventoryItemEntity.getDescription(),
                inventoryItemEntity.getQty(),
                inventoryItemEntity.getUnit(),
                inventoryItemEntity.getId()
        );
    }
    @Override
    public boolean delete(String itemId) throws SQLException {
        return SQLUtil.execute("DELETE FROM InventoryItems WHERE InventoryItemID=?", itemId);
    }
    @Override
    public InventoryItem search(String itemId) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM InventoryItems WHERE InventoryItemID=?", itemId);
        if (rst.next()) {
            return new InventoryItem(
                    rst.getString("InventoryItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getInt("Quantity"),
                    rst.getString("Unit")
            );
        }
        return null;
    }
    @Override
    public InventoryItem searchInventoryItemName(String inventoryItemName) throws SQLException {
        String sql = "SELECT * FROM InventoryItems WHERE Name LIKE ?";
        ResultSet rst = SQLUtil.execute(sql, "%" + inventoryItemName.toLowerCase() + "%");
        if (rst.next()) {
            return new InventoryItem(
                    rst.getString("InventoryItemID"),
                    rst.getString("Name"),
                    rst.getString("Description"),
                    rst.getInt("Quantity"),
                    rst.getString("Unit")
            );
        }
        return null;
    }

    @Override
    public boolean inventoryItemQTYUpdate(String menuItemId, double orderQty) throws SQLException {

            String sql = "SELECT InventoryItemID, QuantityNeeded FROM MenuItemIngredients WHERE MenuItemID = ?";
            ResultSet rst = SQLUtil.execute(sql, menuItemId);
            boolean allUpdatesSuccessful = true;

            while (rst.next()) {
                String inventoryItemId = rst.getString("InventoryItemID");
                double qtyNeeded = rst.getDouble("QuantityNeeded"); // Use getDouble for accuracy
                double qtyUse = orderQty * qtyNeeded;

                String sql2 = "UPDATE InventoryItems SET Quantity = Quantity - ? WHERE InventoryItemID = ?";
                boolean updateSuccess = SQLUtil.execute(sql2, qtyUse, inventoryItemId);

                if (!updateSuccess) {
                    allUpdatesSuccessful = false; // Track if any update fails
                }
            }

            return allUpdatesSuccessful;

    }


}
