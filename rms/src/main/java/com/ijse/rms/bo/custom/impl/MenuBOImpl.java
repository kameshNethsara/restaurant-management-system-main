package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.MenuBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.MenuItemDAO;
import com.ijse.rms.dao.custom.MenuItemIngredentDAO;
import com.ijse.rms.dao.custom.impl.InventoryItemDAOImpl;
import com.ijse.rms.dao.custom.impl.MenuItemDAOImpl;
import com.ijse.rms.dao.custom.impl.MenuItemIngredentDAOImpl;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.MenuItemDto;
import com.ijse.rms.entity.InventoryItem;
import com.ijse.rms.entity.MenuItems;
import com.ijse.rms.entity.MenuItemIngredent;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuBOImpl implements MenuBO {

//    MenuItemIngredentDAO menuItemIngredentDAO = new MenuItemIngredentDAOImpl();
//    MenuItemDAO menuItemDAO = new MenuItemDAOImpl();
//    InventoryItemDAOImpl inventoryItemDAO = new InventoryItemDAOImpl();

    MenuItemIngredentDAO menuItemIngredentDAO = (MenuItemIngredentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MENU_ITEM_INGREDIENTS);;
    MenuItemDAO menuItemDAO = (MenuItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MENU_ITEM);;
    InventoryItemDAO inventoryItemDAO = (InventoryItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);



    @Override
    public boolean saveMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException {
       // return menuItemIngredentDAO.saveMenuItemIngredents(menuItemIngredentsDto);
        return menuItemIngredentDAO.saveMenuItemIngredents(quantityNeeded, menuItemID, inventoryItemID);
    }
/////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public boolean doesMenuItemExist(String menuItemID) throws SQLException {
//        return menuItemDAO.doesMenuItemExist(menuItemID);
//    }

    @Override
    public boolean deleteMenuItemIngredents(String menuItemId, String inventoryId) throws SQLException {
        return menuItemIngredentDAO.deleteMenuItemIngredents(menuItemId, inventoryId);
    }

    @Override
    public boolean updateMenuItemIngredents(double quantityNeeded, String menuItemID, String inventoryItemID) throws SQLException {
        //return menuItemIngredentDAO.updateMenuItemIngredents(menuItemIngredentsDto);
        return menuItemIngredentDAO.updateMenuItemIngredents( quantityNeeded,  menuItemID,  inventoryItemID);
    }

    @Override
    public ArrayList<MenuItemIngredent> getAllMenuItemIngredents() throws SQLException {
        return menuItemIngredentDAO.getAllData();
    }

    @Override
    public String getNextMenuItemId() throws SQLException {
        return menuItemDAO.getNextId();
    }

    @Override
    public boolean saveMenuItem(MenuItemDto menuItemDto) throws SQLException {
        MenuItems menuItems = new MenuItems(
                menuItemDto.getId(),
                menuItemDto.getName(),
                menuItemDto.getDescription(),
                menuItemDto.getPrice(),
                menuItemDto.getCategory(),
                menuItemDto.getKitchenSection()
        );
        return menuItemDAO.save(menuItems);
    }

    @Override
    public boolean deleteMenuItem(String menuItemID) throws SQLException {
        return menuItemDAO.delete(menuItemID);
    }

    @Override
    public boolean updateMenuItem(MenuItemDto menuItemDto) throws SQLException {
        MenuItems menuItems = new MenuItems(
                menuItemDto.getId(),
                menuItemDto.getName(),
                menuItemDto.getDescription(),
                menuItemDto.getPrice(),
                menuItemDto.getCategory(),
                menuItemDto.getKitchenSection()
        );
        return menuItemDAO.update(menuItems);
    }

    @Override
    public MenuItemDto searchMenuItem(String menuItemID) throws SQLException {
       MenuItems menuItems = menuItemDAO.search(menuItemID);

       if(menuItems == null){
           return null;
       }

       return new MenuItemDto(
               menuItems.getId(),
               menuItems.getName(),
               menuItems.getDescription(),
               menuItems.getPrice(),
               menuItems.getCategory(),
               menuItems.getKitchenSection()
       );
    }

    @Override
    public ArrayList<MenuItemDto> getAllMenuItems() throws SQLException {
        ArrayList<MenuItems> menuItems = menuItemDAO.getAllData();
        ArrayList<MenuItemDto> menuItemDtos = new ArrayList<>();

        for(MenuItems menuItem : menuItems){
            menuItemDtos.add(new MenuItemDto(
                    menuItem.getId(),
                    menuItem.getName(),
                    menuItem.getDescription(),
                    menuItem.getPrice(),
                    menuItem.getCategory(),
                    menuItem.getKitchenSection()
            ));
        }
        return menuItemDtos;
    }

    @Override
    public InventoryItemDto searchInventoryItemName(String inventoryItemName) throws SQLException {
        InventoryItem inventoryItem = inventoryItemDAO.searchInventoryItemName(inventoryItemName);
//        if (inventoryItem == null) {
//            System.out.println("No inventory item found with name: " + inventoryItemName);
//            return null;
//        }
        InventoryItemDto dto = new InventoryItemDto();
        dto.setId(inventoryItem.getId());
        dto.setName(inventoryItem.getName());
        dto.setDescription(inventoryItem.getDescription());
        dto.setQty(inventoryItem.getQty());
        dto.setUnit(inventoryItem.getUnit());
        return dto;

    }
}
