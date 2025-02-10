//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.ReservationDto;
//import com.ijse.rms.dto.TableAssignmentDto;
//import com.ijse.rms.tdm.ReservationTM;
//import com.ijse.rms.dao.SQLUtil;
//
//import javax.swing.table.TableModel;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ReservationModel {
//
//    TableAssignmentModel tableAssignmentModel;
//    //TableModel tableModel;
//
//    public static String getNextReservationID() throws SQLException {
//        String sql = "SELECT ReservationID FROM Reservations ORDER BY ReservationID DESC LIMIT 1";
//        ResultSet rst = SQLUtil.execute(sql);
//
//        if (rst.next()) {
//            String currentID = rst.getString(1); // R001
//            String numericPart = currentID.substring(1); // 001
//            int id = Integer.parseInt(numericPart) + 1; // 2
//            return String.format("R%03d", id); // R002
//        }
//        return "R001";
//    }
//
//    public boolean saveReservation(ReservationDto reservationDto , TableAssignmentDto tableAssignmentDto) throws SQLException {
//        boolean saveResavation = false;
//        if (tableAssignmentModel == null) {
//            tableAssignmentModel = new TableAssignmentModel();
//        }
//
//        String sql = "INSERT INTO Reservations (ReservationID, CustomerID, ReservationDate, NumberOfGuests, SpecialRequests, Status) " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        saveResavation = SQLUtil.execute(
//                sql,
//                reservationDto.getReservationID(),
//                reservationDto.getCustomerID(),
//                reservationDto.getReservationDate(),
//                reservationDto.getNumberOfGuests(),
//                reservationDto.getSpecialRequests(),
//                reservationDto.getStatus()
//        );
//        tableAssignmentModel.saveTableAssignment(tableAssignmentDto);
//
//
//        return saveResavation;
//    }
//
////    public static ArrayList<ReservationDto> getAllReservations() throws SQLException {
////        String sql = "SELECT * FROM Reservations";
////        ResultSet rst = CrudUtil.execute(sql);
////
////        ArrayList<ReservationDto> reservations = new ArrayList<>();
////        while (rst.next()) {
////            ReservationDto reservation = new ReservationDto(
////                    rst.getString("ReservationID"),
////                    rst.getString("CustomerID"),
////                    rst.getDate("ReservationDate").toLocalDate(),
////                    rst.getInt("NumberOfGuests"),
////                    rst.getString("SpecialRequests"),
////                    rst.getString("Status")
////            );
////            reservations.add(reservation);
////        }
////        return reservations;
////    }
//
//    public static ArrayList<ReservationTM> getAllReservations() throws SQLException {
//        String sql = """
//            SELECT
//                r.ReservationID,
//                r.CustomerID,
//                r.ReservationDate,
//                r.NumberOfGuests,
//                r.SpecialRequests,
//                r.Status AS ReservationStatus,
//                t.TableID,
//                t.TableNumber,
//                t.Capacity,  -- Column name in the database
//                t.Location AS TableLocation,
//                t.Status AS TableStatus,
//                ta.TableAssignmentID,
//                ta.AssignmentTime
//            FROM
//                Reservations r
//            JOIN
//                TableAssignments ta ON r.ReservationID = ta.ReservationID
//            JOIN
//                Tables t ON ta.TableID = t.TableID;
//            """;
//
//        ResultSet rst = SQLUtil.execute(sql);
//
//        ArrayList<ReservationTM> reservations = new ArrayList<>();
//        while (rst.next()) {
//            reservations.add(new ReservationTM(
//                    rst.getString("ReservationID"),
//                    rst.getDate("ReservationDate").toLocalDate(),
//                    rst.getInt("NumberOfGuests"),
//                    rst.getString("SpecialRequests"),
//                    rst.getString("TableID"),
//                    rst.getString("TableStatus"),
//                    rst.getInt("Capacity"),  // Corrected column name
//                    rst.getString("TableLocation")
//            ));
//        }
//        return reservations;
//    }
//
//
//
//    public boolean updateReservation(ReservationDto reservationDto , TableAssignmentDto tableAssignmentDto) throws SQLException {
//        boolean update = false;
//        if (tableAssignmentModel == null) {
//            tableAssignmentModel = new TableAssignmentModel();
//        }
//        String sql = "UPDATE Reservations SET CustomerID = ?, ReservationDate = ?, NumberOfGuests = ?, SpecialRequests = ?, Status = ? " +
//                "WHERE ReservationID = ?";
//        update=  SQLUtil.execute(
//                sql,
//                reservationDto.getCustomerID(),
//                reservationDto.getReservationDate(),
//                reservationDto.getNumberOfGuests(),
//                reservationDto.getSpecialRequests(),
//                reservationDto.getStatus(),
//                reservationDto.getReservationID()
//        );
//        tableAssignmentModel.updateTableAssignment(tableAssignmentDto);
//        return update;
//    }
//
//    public static boolean deleteReservation(String reservationID) throws SQLException {
//        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";
//        return SQLUtil.execute(sql, reservationID);
//    }
//
//    public static ReservationDto searchReservation(String reservationID) throws SQLException {
//        String sql = "SELECT * FROM Reservations WHERE ReservationID = ?";
//        ResultSet rst = SQLUtil.execute(sql, reservationID);
//
//        if (rst.next()) {
//            return new ReservationDto(
//                    rst.getString("ReservationID"),
//                    rst.getString("CustomerID"),
//                    rst.getDate("ReservationDate").toLocalDate(),
//                    rst.getInt("NumberOfGuests"),
//                    rst.getString("SpecialRequests"),
//                    rst.getString("Status")
//            );
//        }
//        return null;
//    }
//}
