//package com.ijse.rms.model;
//
//import com.ijse.rms.dao.custom.TableDAO;
//import com.ijse.rms.dao.custom.impl.TableDAOImply;
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.TableAssignmentDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class TableAssignmentModel {
//
//    //TableModel tableModel = new TableModel();
//    TableDAO tableDAO = new TableDAOImply();
//
//    public static String getNextTableAssignmentID() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT TableAssignmentID FROM TableAssignments " +
//                "ORDER BY TableAssignmentID DESC " +
//                "LIMIT 1";
//
//        ResultSet rst = SQLUtil.execute(sql);
//
//        if (rst.next()) {
//            String string = rst.getString(1); // TA001
//            String substring = string.substring(2); // 001
//            int lastIdIndex = Integer.parseInt(substring); // 1
//            int nextIdIndex = lastIdIndex + 1; // 2
//            String newId = String.format("TA%03d", nextIdIndex); // TA002
//            return newId;
//        }
//        return "TA001";
//    }
//
//    public boolean saveTableAssignment(TableAssignmentDto tableAssignmentDto) throws SQLException {
//
//        boolean saveTableAssignment = SQLUtil.execute(
//                "INSERT INTO TableAssignments VALUES (?, ?, ?, ?)",
//                tableAssignmentDto.getTableAssignmentID(),
//                tableAssignmentDto.getTableID(),
//                tableAssignmentDto.getReservationID(),
//                tableAssignmentDto.getAssignmentTime()
//        );
//        tableDAO.updateTableStatus(tableAssignmentDto.getTableID(),"Reserved");
//        return saveTableAssignment;
//    }
//
//    public static ArrayList<TableAssignmentDto> getAllTableAssignments() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM TableAssignments");
//
//        ArrayList<TableAssignmentDto> tableAssignments = new ArrayList<>();
//
//        while (rst.next()) {
//            TableAssignmentDto tableAssignmentDto = new TableAssignmentDto(
//                    rst.getString("TableAssignmentID"),
//                    rst.getString("TableID"),
//                    rst.getString("ReservationID"),
//                    rst.getTimestamp("AssignmentTime").toLocalDateTime()
//            );
//            tableAssignments.add(tableAssignmentDto);
//        }
//        return tableAssignments;
//    }
//
//    public boolean updateTableAssignment(TableAssignmentDto tableAssignmentDto) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE TableAssignments SET TableID=?, ReservationID=?, AssignmentTime=? WHERE TableAssignmentID=?",
//                tableAssignmentDto.getTableID(),
//                tableAssignmentDto.getReservationID(),
//                tableAssignmentDto.getAssignmentTime(),
//                tableAssignmentDto.getTableAssignmentID()
//        );
//    }
//
//    public static boolean deleteTableAssignment(String tableAssignmentID) throws SQLException {
//        return SQLUtil.execute(
//                "DELETE FROM TableAssignments WHERE TableAssignmentID=?",
//                tableAssignmentID
//        );
//    }
//
//    public TableAssignmentDto searchTableAssignment(String tableAssignmentID) throws SQLException {
//        ResultSet rst = SQLUtil.execute(
//                "SELECT * FROM TableAssignments WHERE TableAssignmentID=?",
//                tableAssignmentID
//        );
//        if (rst.next()) {
//            return new TableAssignmentDto(
//                    rst.getString("TableAssignmentID"),
//                    rst.getString("TableID"),
//                    rst.getString("ReservationID"),
//                    rst.getTimestamp("AssignmentTime").toLocalDateTime()
//            );
//        }
//        return null;
//    }
//
//}
