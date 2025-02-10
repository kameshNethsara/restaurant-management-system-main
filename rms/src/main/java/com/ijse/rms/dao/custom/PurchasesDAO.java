package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.PurchasesDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchasesDAO {

    public String getNextPurchaseId() throws SQLException;
    public boolean savePurchase(PurchasesDto purchaseDto, InventoryItemDto inventoryDto) throws SQLException;
    public ArrayList<PurchasesDto> getAllPurchases() throws SQLException;
    public boolean updatePurchase(PurchasesDto purchaseDto) throws SQLException;
    public boolean deletePurchase(String purchaseId) throws SQLException;
    public PurchasesDto searchPurchase(String purchaseId) throws SQLException;

}
