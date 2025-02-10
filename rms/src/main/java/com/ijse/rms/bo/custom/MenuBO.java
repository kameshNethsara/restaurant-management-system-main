package com.ijse.rms.bo.custom;

import com.ijse.rms.dao.custom.MenuItemDAO;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.MenuItemDto;
import com.ijse.rms.entity.InventoryItem;
import com.ijse.rms.entity.MenuItems;
import com.ijse.rms.entity.MenuItemIngredent;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuBO {

    //public boolean saveMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException;
    public boolean saveMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException;
    public boolean deleteMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException;
    //public boolean updateMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException;
    boolean updateMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException;

    public ArrayList<MenuItemIngredent> getAllMenuItemIngredents() throws SQLException;

    public String getNextMenuItemId() throws SQLException;
    public boolean saveMenuItem(MenuItemDto menuItemDto) throws SQLException;
    public boolean deleteMenuItem(String menuItemID) throws SQLException;
    public boolean updateMenuItem(MenuItemDto menuItemDto) throws SQLException;
    public MenuItemDto searchMenuItem(String menuItemID) throws SQLException;
    public ArrayList<MenuItemDto> getAllMenuItems() throws SQLException;
    //public boolean doesMenuItemExist(String menuItemID) throws SQLException;

    public InventoryItemDto searchInventoryItemName(String inventoryItemName) throws SQLException;

}
