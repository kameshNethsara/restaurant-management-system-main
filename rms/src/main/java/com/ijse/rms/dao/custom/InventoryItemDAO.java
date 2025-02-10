package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.entity.InventoryItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryItemDAO extends CrudDAO<InventoryItem> {

//    public String getNextInventoryItemId() throws SQLException;
//    public boolean saveInventoryItem(InventoryItemDto itemDto) throws SQLException;
//    public ArrayList<InventoryItemDto> getAllInventoryItems() throws SQLException;
//    public boolean updateInventoryItem(InventoryItemDto itemDto) throws SQLException;
//    public boolean deleteInventoryItem(String itemId) throws SQLException;
//    public InventoryItemDto searchInventoryItem(String itemId) throws SQLException;
    public InventoryItem searchInventoryItemName(String inventoryItemName) throws SQLException;
    public boolean inventoryItemQTYUpdate(String menuItemId, double orderQty) throws SQLException;
}
