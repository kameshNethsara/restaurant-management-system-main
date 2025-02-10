package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.entity.InventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryItemBO extends SuperBO {

    public String getNextInventoryItemId() throws SQLException;
    public boolean saveInventoryItem(InventoryItemDto itemDto) throws SQLException;
    public ArrayList<InventoryItemDto> getAllInventoryItems() throws SQLException;
    public boolean updateInventoryItem(InventoryItemDto itemDto) throws SQLException;
    public boolean deleteInventoryItem(String itemId) throws SQLException;
    public InventoryItemDto searchInventoryItem(String itemId) throws SQLException;
    public InventoryItemDto searchInventoryItemName(String inventoryItemName) throws SQLException;

}
