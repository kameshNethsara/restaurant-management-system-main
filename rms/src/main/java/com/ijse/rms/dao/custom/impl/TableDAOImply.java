package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.TableDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.TableDto;
import com.ijse.rms.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableDAOImply implements TableDAO {
    @Override
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT TableID FROM Tables ORDER BY TableID DESC LIMIT 1";

        PreparedStatement pts = connection.prepareStatement(sql);
        ResultSet rst = pts.executeQuery();

        if (rst.next()) {
            String lastId = rst.getString(1); // T001
            String substring = lastId.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIndex = lastIdIndex + 1; // 2
            return String.format("T%03d", nextIndex); // T002
        }
        return "T001";
    }
    @Override
    public boolean save(Table tableEntity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO Tables VALUES (?, ?, ?, ?, ?)",
                tableEntity.getTableId(),
                tableEntity.getTableNumber(),
                tableEntity.getTableCapacity(),
                tableEntity.getTableLocation(),
                tableEntity.getTableStatus()
        );
    }
    @Override
    public boolean updateTableStatus(String tableID, String newStatus) throws SQLException {
        return SQLUtil.execute(
                "UPDATE Tables SET Status=? WHERE TableID=?",
                newStatus,
                tableID
        );
    }
    @Override
    public ArrayList<Table> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Tables");

        ArrayList<Table> tableList = new ArrayList<>();

        while (rst.next()) {
            Table tableEntity = new Table(
                    rst.getString("TableID"),
                    rst.getInt("TableNumber"),
                    rst.getInt("Capacity"),
                    rst.getString("Location"),
                    rst.getString("Status")
            );
            tableList.add(tableEntity);
        }
        return tableList;
    }
    @Override
    public boolean update(Table tableEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE Tables SET TableNumber=?, Capacity=?, Location=?, Status=? WHERE TableID=?",
                tableEntity.getTableNumber(),
                tableEntity.getTableCapacity(),
                tableEntity.getTableLocation(),
                tableEntity.getTableStatus(),
                tableEntity.getTableId()
        );
    }
    @Override
    public boolean delete(String tableID) throws SQLException {
        return SQLUtil.execute("DELETE FROM Tables WHERE TableID=?", tableID);
    }
    @Override
    public Table search(String tableID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Tables WHERE TableID=?", tableID);
        if (rst.next()) {
            return new Table(
                    rst.getString("TableID"),
                    rst.getInt("TableNumber"),
                    rst.getInt("Capacity"),
                    rst.getString("Location"),
                    rst.getString("Status")
            );
        }
        return null;
    }
    @Override
    public ArrayList<Table> searchTableByLocation(String location) throws SQLException {
        String sql = "SELECT * FROM Tables WHERE Location LIKE ?";
        ResultSet rst = SQLUtil.execute(sql, "%" + location + "%");

        ArrayList<Table> tableList = new ArrayList<>();

        while (rst.next()) {
            Table tableEntity = new Table(
                    rst.getString("TableID"),
                    rst.getInt("TableNumber"),
                    rst.getInt("Capacity"),
                    rst.getString("Location"),
                    rst.getString("Status")
            );
            tableList.add(tableEntity);
        }
        return tableList;
    }
    @Override
    public ArrayList<Table> searchTableByStatus(String status) throws SQLException {
        String sql = "SELECT * FROM Tables WHERE Status = ?";
        ResultSet rst = SQLUtil.execute(sql, status);

        ArrayList<Table> availableTables = new ArrayList<>();

        while (rst.next()) {
            Table tableEntity = new Table(
                    rst.getString("TableID"),
                    rst.getInt("TableNumber"),
                    rst.getInt("Capacity"),
                    rst.getString("Location"),
                    rst.getString("Status")
            );
            availableTables.add(tableEntity);
        }
        return availableTables;
    }
}
