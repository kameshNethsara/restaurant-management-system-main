package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.TableDto;
import com.ijse.rms.entity.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TableBO extends SuperBO {

    public String getNextTableID() throws SQLException;
    public boolean saveTable(TableDto tableDto) throws SQLException;
    public boolean updateTableStatus(String tableID, String newStatus) throws SQLException;
    public ArrayList<TableDto> getAllTables() throws SQLException;
    public boolean updateTable(TableDto tableDto) throws SQLException;
    public boolean deleteTable(String tableID) throws SQLException;
    public Table searchTableById(String tableID) throws SQLException;
    public ArrayList<TableDto> searchTableByLocation(String location) throws SQLException;
    public ArrayList<TableDto> searchTableByStatus(String status) throws SQLException;

}
