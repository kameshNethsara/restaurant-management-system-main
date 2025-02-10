package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.MenuItemIngredentsDto;
import com.ijse.rms.entity.MenuItemIngredent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuItemIngredentDAO extends CrudDAO<MenuItemIngredent> {

//    public boolean saveMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException;
//    public boolean updateMenuItemIngredents(MenuItemIngredentsDto menuItemIngredentsDto) throws SQLException;
    public boolean saveMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException;
    public boolean updateMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException;
//
//
    public boolean deleteMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException;
//    public ArrayList<MenuItemIngredentsDto> getAllMenuItemIngredents() throws SQLException;
    public MenuItemIngredent searchMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException;
    public ArrayList<MenuItemIngredent> getIngredientsForMenuItem(String menuItemId) throws SQLException;

}
