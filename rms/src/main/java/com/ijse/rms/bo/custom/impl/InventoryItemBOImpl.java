package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.InventoryItemBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.impl.InventoryItemDAOImpl;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.entity.InventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryItemBOImpl implements InventoryItemBO {

//    InventoryItemDAO inventoryItemDAO = new InventoryItemDAOImpl();
    InventoryItemDAO inventoryItemDAO = (InventoryItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);


    @Override
    public String getNextInventoryItemId() throws SQLException {
        return inventoryItemDAO.getNextId();
    }

    @Override
    public boolean saveInventoryItem(InventoryItemDto inventoryItemDto) throws SQLException {
        InventoryItem inventoryItem = new InventoryItem(
                inventoryItemDto.getId(),
                inventoryItemDto.getName(),
                inventoryItemDto.getDescription(),
                inventoryItemDto.getQty(),
                inventoryItemDto.getUnit()
        );
        return inventoryItemDAO.save(inventoryItem);
    }

    @Override
    public ArrayList<InventoryItemDto> getAllInventoryItems() throws SQLException {
        ArrayList<InventoryItem> inventoryItemArrayList = inventoryItemDAO.getAllData();
        ArrayList<InventoryItemDto> inventoryItemDtoArrayList = new ArrayList<>();
        for (InventoryItem inventoryItem : inventoryItemArrayList){
            inventoryItemDtoArrayList.add(new InventoryItemDto(
                    inventoryItem.getId(),
                    inventoryItem.getName(),
                    inventoryItem.getDescription(),
                    inventoryItem.getQty(),
                    inventoryItem.getUnit()
            ));
        }
        return inventoryItemDtoArrayList;
    }

    @Override
    public boolean updateInventoryItem(InventoryItemDto inventoryItemDto) throws SQLException {
        InventoryItem inventoryItem = new InventoryItem(
                inventoryItemDto.getId(),
                inventoryItemDto.getName(),
                inventoryItemDto.getDescription(),
                inventoryItemDto.getQty(),
                inventoryItemDto.getUnit()
        );
        return inventoryItemDAO.update(inventoryItem);
    }

    @Override
    public boolean deleteInventoryItem(String itemId) throws SQLException {
        return inventoryItemDAO.delete(itemId);
    }

    @Override
    public InventoryItemDto searchInventoryItem(String itemId) throws SQLException {
       InventoryItem inventoryItem = inventoryItemDAO.search(itemId);
        if (inventoryItem == null) {
            return null; // Return null if the customer is not found
        }
       return new InventoryItemDto(
               inventoryItem.getId(),
               inventoryItem.getName(),
               inventoryItem.getDescription(),
               inventoryItem.getQty(),
               inventoryItem.getUnit()
       );
    }

    @Override
    public InventoryItemDto searchInventoryItemName(String inventoryItemName) throws SQLException {
        InventoryItem inventoryItem = inventoryItemDAO.search(inventoryItemName);
        if (inventoryItem == null) {
            return null; // Return null if the customer is not found
        }
        return new InventoryItemDto(
                inventoryItem.getId(),
                inventoryItem.getName(),
                inventoryItem.getDescription(),
                inventoryItem.getQty(),
                inventoryItem.getUnit()
        );
    }
}
