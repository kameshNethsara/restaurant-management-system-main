//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.TableDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class TableModel {
//
//    public static String getNextTableID() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT TableID FROM Tables ORDER BY TableID DESC LIMIT 1";
//
//        PreparedStatement pts = connection.prepareStatement(sql);
//        ResultSet rst = pts.executeQuery();
//
//        if (rst.next()) {
//            String lastId = rst.getString(1); // T001
//            String substring = lastId.substring(1); // 001
//            int lastIdIndex = Integer.parseInt(substring); // 1
//            int nextIndex = lastIdIndex + 1; // 2
//            return String.format("T%03d", nextIndex); // T002
//        }
//        return "T001";
//    }
//
//    public boolean saveTable(TableDto tableDto) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO Tables VALUES (?, ?, ?, ?, ?)",
//                tableDto.getTableId(),
//                tableDto.getTableNumber(),
//                tableDto.getTableCapacity(),
//                tableDto.getTableLocation(),
//                tableDto.getTableStatus()
//        );
//    }
//    public boolean updateTableStatus(String tableID, String newStatus) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE Tables SET Status=? WHERE TableID=?",
//                newStatus,
//                tableID
//        );
//    }
//
//
//    public static ArrayList<TableDto> getAllTables() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Tables");
//
//        ArrayList<TableDto> tableList = new ArrayList<>();
//
//        while (rst.next()) {
//            TableDto tableDto = new TableDto(
//                    rst.getString("TableID"),
//                    rst.getInt("TableNumber"),
//                    rst.getInt("Capacity"),
//                    rst.getString("Location"),
//                    rst.getString("Status")
//            );
//            tableList.add(tableDto);
//        }
//        return tableList;
//    }
//
//    public boolean updateTable(TableDto tableDto) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE Tables SET TableNumber=?, Capacity=?, Location=?, Status=? WHERE TableID=?",
//                tableDto.getTableNumber(),
//                tableDto.getTableCapacity(),
//                tableDto.getTableLocation(),
//                tableDto.getTableStatus(),
//                tableDto.getTableId()
//        );
//    }
//
//    public static boolean deleteTable(String tableID) throws SQLException {
//        return SQLUtil.execute("DELETE FROM Tables WHERE TableID=?", tableID);
//    }
//
//    public static TableDto searchTableById(String tableID) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Tables WHERE TableID=?", tableID);
//        if (rst.next()) {
//            return new TableDto(
//                    rst.getString("TableID"),
//                    rst.getInt("TableNumber"),
//                    rst.getInt("Capacity"),
//                    rst.getString("Location"),
//                    rst.getString("Status")
//            );
//        }
//        return null;
//    }
//
//    public static ArrayList<TableDto> searchTableByLocation(String location) throws SQLException {
//        String sql = "SELECT * FROM Tables WHERE Location LIKE ?";
//        ResultSet rst = SQLUtil.execute(sql, "%" + location + "%");
//
//        ArrayList<TableDto> tableList = new ArrayList<>();
//
//        while (rst.next()) {
//            TableDto tableDto = new TableDto(
//                    rst.getString("TableID"),
//                    rst.getInt("TableNumber"),
//                    rst.getInt("Capacity"),
//                    rst.getString("Location"),
//                    rst.getString("Status")
//            );
//            tableList.add(tableDto);
//        }
//        return tableList;
//    }
//    public static ArrayList<TableDto> searchTableByStatus(String status) throws SQLException {
//        String sql = "SELECT * FROM Tables WHERE Status = ?";
//        ResultSet rst = SQLUtil.execute(sql, status);
//
//        ArrayList<TableDto> availableTables = new ArrayList<>();
//
//        while (rst.next()) {
//            TableDto tableDto = new TableDto(
//                    rst.getString("TableID"),
//                    rst.getInt("TableNumber"),
//                    rst.getInt("Capacity"),
//                    rst.getString("Location"),
//                    rst.getString("Status")
//            );
//            availableTables.add(tableDto);
//        }
//        return availableTables;
//    }
//}
