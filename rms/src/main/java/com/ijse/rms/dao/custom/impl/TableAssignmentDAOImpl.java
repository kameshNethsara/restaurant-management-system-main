package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.TableAssignmentDAO;
import com.ijse.rms.dao.custom.TableDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.TableAssignmentDto;
import com.ijse.rms.entity.TableAssignment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableAssignmentDAOImpl implements TableAssignmentDAO {

    //TableModel tableModel = new TableModel();
    TableDAO tableDAO = new TableDAOImply();
    @Override
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT TableAssignmentID FROM TableAssignments " +
                "ORDER BY TableAssignmentID DESC " +
                "LIMIT 1";

        ResultSet rst = SQLUtil.execute(sql);

        if (rst.next()) {
            String string = rst.getString(1); // TA001
            String substring = string.substring(2); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIdIndex = lastIdIndex + 1; // 2
            String newId = String.format("TA%03d", nextIdIndex); // TA002
            return newId;
        }
        return "TA001";
    }
    @Override
    public boolean save(TableAssignment TableAssignmentEntity) throws SQLException {

        boolean saveTableAssignment = SQLUtil.execute(
                "INSERT INTO TableAssignments VALUES (?, ?, ?, ?)",
                TableAssignmentEntity.getTableAssignmentID(),
                TableAssignmentEntity.getTableID(),
                TableAssignmentEntity.getReservationID(),
                TableAssignmentEntity.getAssignmentTime()
        );
        tableDAO.updateTableStatus(TableAssignmentEntity.getTableID(),"Reserved");
        return saveTableAssignment;
    }
    @Override
    public ArrayList<TableAssignment> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM TableAssignments");

        ArrayList<TableAssignment> tableAssignments = new ArrayList<>();

        while (rst.next()) {
            TableAssignment TableAssignmentEntity = new TableAssignment(
                    rst.getString("TableAssignmentID"),
                    rst.getString("TableID"),
                    rst.getString("ReservationID"),
                    rst.getTimestamp("AssignmentTime").toLocalDateTime()
            );
            tableAssignments.add(TableAssignmentEntity);
        }
        return tableAssignments;
    }
    @Override
    public boolean update(TableAssignment TableAssignmentEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE TableAssignments SET TableID=?, ReservationID=?, AssignmentTime=? WHERE TableAssignmentID=?",
                TableAssignmentEntity.getTableID(),
                TableAssignmentEntity.getReservationID(),
                TableAssignmentEntity.getAssignmentTime(),
                TableAssignmentEntity.getTableAssignmentID()
        );
    }
    @Override
    public boolean delete(String tableAssignmentID) throws SQLException {
        return SQLUtil.execute(
                "DELETE FROM TableAssignments WHERE TableAssignmentID=?",
                tableAssignmentID
        );
    }
    @Override
    public TableAssignment search(String tableAssignmentID) throws SQLException {
        ResultSet rst = SQLUtil.execute(
                "SELECT * FROM TableAssignments WHERE TableAssignmentID=?",
                tableAssignmentID
        );
        if (rst.next()) {
            return new TableAssignment(
                    rst.getString("TableAssignmentID"),
                    rst.getString("TableID"),
                    rst.getString("ReservationID"),
                    rst.getTimestamp("AssignmentTime").toLocalDateTime()
            );
        }
        return null;
    }

}
