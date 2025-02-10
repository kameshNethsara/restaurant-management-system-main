package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.entity.MenuItems;

import java.sql.SQLException;

public interface MenuItemDAO extends CrudDAO<MenuItems> {

//    public String getNextMenuItemId() throws SQLException;
//    public boolean saveMenuItem(MenuItemDto menuItemDTO) throws SQLException;
//    public ArrayList<MenuItemDto> getAllMenuItems() throws SQLException;
//    public boolean updateMenuItem(MenuItemDto menuItemDTO) throws SQLException;
//    public boolean deleteMenuItem(String menuItemID) throws SQLException;
//    public MenuItemDto searchMenuItem(String menuItemID) throws SQLException;
    public MenuItems searchMenuItemName(String menuItemName) throws SQLException;
    //public boolean doesMenuItemExist(String menuItemID) throws SQLException;
}
