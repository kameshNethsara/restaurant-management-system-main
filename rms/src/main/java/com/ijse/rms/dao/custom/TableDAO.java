package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.TableDto;
import com.ijse.rms.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TableDAO extends CrudDAO<Table> {

//    public String getNextTableID() throws SQLException;
//    public boolean saveTable(TableDto tableDto) throws SQLException;
    public boolean updateTableStatus(String tableID, String newStatus) throws SQLException;
//    public ArrayList<TableDto> getAllTables() throws SQLException;
//    public boolean updateTable(TableDto tableDto) throws SQLException;
//    public boolean deleteTable(String tableID) throws SQLException;
//    public TableDto searchTableById(String tableID) throws SQLException;
    public ArrayList<Table> searchTableByLocation(String location) throws SQLException;
    public ArrayList<Table> searchTableByStatus(String status) throws SQLException;

}
