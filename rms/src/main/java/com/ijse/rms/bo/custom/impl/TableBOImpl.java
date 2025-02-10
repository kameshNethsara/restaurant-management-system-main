package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.TableBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.TableDAO;
import com.ijse.rms.dao.custom.impl.TableDAOImply;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.dto.TableDto;
import com.ijse.rms.entity.Customer;
import com.ijse.rms.entity.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public class TableBOImpl implements TableBO {

    TableDAO tableDAO = (TableDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TABLE);

    @Override
    public String getNextTableID() throws SQLException {
        return tableDAO.getNextId();
    }

    @Override
    public boolean saveTable(TableDto tableDto) throws SQLException {
        Table newTable = new Table( // Initialize newTable correctly
                tableDto.getTableId(),
                tableDto.getTableNumber(),
                tableDto.getTableCapacity(),
                tableDto.getTableLocation(),
                tableDto.getTableStatus()
        );
        return tableDAO.save(newTable);
    }

    @Override
    public boolean updateTableStatus(String tableID, String newStatus) throws SQLException {
        return tableDAO.updateTableStatus(tableID, newStatus);
    }

    @Override
    public ArrayList<TableDto> getAllTables() throws SQLException {
        ArrayList<Table> TableList = tableDAO.getAllData();
        ArrayList<TableDto> TableDtoList = new ArrayList<>();
        for(Table table : TableList) {
            TableDtoList.add( new TableDto(
                    table.getTableId(),
                    table.getTableNumber(),
                    table.getTableCapacity(),
                    table.getTableLocation(),
                    table.getTableStatus()
            ));
        }
        return TableDtoList;
    }

    @Override
    public boolean updateTable(TableDto tableDto) throws SQLException {
        Table newTable = new Table(
                tableDto.getTableId(),
                tableDto.getTableNumber(),
                tableDto.getTableCapacity(),
                tableDto.getTableLocation(),
                tableDto.getTableStatus()
        );
        return tableDAO.save(newTable);

    }

    @Override
    public boolean deleteTable(String tableID) throws SQLException {
        return tableDAO.delete(tableID);
    }

    @Override
    public Table searchTableById(String tableID) throws SQLException {
        return tableDAO.search(tableID);
    }

    @Override
    public ArrayList<TableDto> searchTableByLocation(String location) throws SQLException {
        ArrayList<Table> TableList = tableDAO.searchTableByLocation(location);
        ArrayList<TableDto> TableDtoList = new ArrayList<>();
        for(Table table : TableList) {
            TableDtoList.add( new TableDto(
                    table.getTableId(),
                    table.getTableNumber(),
                    table.getTableCapacity(),
                    table.getTableLocation(),
                    table.getTableStatus()
            ));
        }
        return TableDtoList;

    }

    @Override
    public ArrayList<TableDto> searchTableByStatus(String status) throws SQLException {
        ArrayList<Table> TableList = tableDAO.searchTableByStatus(status);
        ArrayList<TableDto> TableDtoList = new ArrayList<>();
        for(Table table : TableList) {
            TableDtoList.add( new TableDto(
                    table.getTableId(),
                    table.getTableNumber(),
                    table.getTableCapacity(),
                    table.getTableLocation(),
                    table.getTableStatus()
            ));
        }
        return TableDtoList;

    }
}
