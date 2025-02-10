package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.ReservationDAO;
import com.ijse.rms.dao.custom.TableAssignmentDAO;
import com.ijse.rms.dto.ReservationDto;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.entity.TableAssignment;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {


    //TableAssignmentModel tableAssignmentModel;
    TableAssignmentDAO tableAssignmentDAO;

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT ReservationID FROM Reservations ORDER BY ReservationID DESC LIMIT 1";
        ResultSet rst = SQLUtil.execute(sql);

        if (rst.next()) {
            String currentID = rst.getString(1); // R001
            String numericPart = currentID.substring(1); // 001
            int id = Integer.parseInt(numericPart) + 1; // 2
            return String.format("R%03d", id); // R002
        }
        return "R001";
    }

    @Override
    public boolean save(ReservationDto DTO) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<ReservationDto> getAllData() throws SQLException {
        return null;
    }

    @Override
    public boolean update(ReservationDto DTO) throws SQLException {
        return false;
    }

    @Override
    public boolean saveReservation(Reservation reservationDto , TableAssignment TableAssignmentEntity) throws SQLException {
        boolean saveResavation = false;
        if (tableAssignmentDAO == null) {
            tableAssignmentDAO = new TableAssignmentDAOImpl();
        }

        String sql = "INSERT INTO Reservations (ReservationID, CustomerID, ReservationDate, NumberOfGuests, SpecialRequests, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        saveResavation = SQLUtil.execute(
                sql,
                reservationDto.getReservationID(),
                reservationDto.getCustomerID(),
                reservationDto.getReservationDate(),
                reservationDto.getNumberOfGuests(),
                reservationDto.getSpecialRequests(),
                reservationDto.getStatus()
        );
        tableAssignmentDAO.save(TableAssignmentEntity);


        return saveResavation;
    }

//    public static ArrayList<ReservationDto> getAllReservations() throws SQLException {
//        String sql = "SELECT * FROM Reservations";
//        ResultSet rst = CrudUtil.execute(sql);
//
//        ArrayList<ReservationDto> reservations = new ArrayList<>();
//        while (rst.next()) {
//            ReservationDto reservation = new ReservationDto(
//                    rst.getString("ReservationID"),
//                    rst.getString("CustomerID"),
//                    rst.getDate("ReservationDate").toLocalDate(),
//                    rst.getInt("NumberOfGuests"),
//                    rst.getString("SpecialRequests"),
//                    rst.getString("Status")
//            );
//            reservations.add(reservation);
//        }
//        return reservations;
//    }
    @Override
    public boolean updateReservation(Reservation reservationDto , TableAssignment TableAssignmentEntity) throws SQLException {
        boolean update = false;
        if (tableAssignmentDAO == null) {
            tableAssignmentDAO = new TableAssignmentDAOImpl();
        }
        String sql = "UPDATE Reservations SET CustomerID = ?, ReservationDate = ?, NumberOfGuests = ?, SpecialRequests = ?, Status = ? " +
                "WHERE ReservationID = ?";
        update=  SQLUtil.execute(
                sql,
                reservationDto.getCustomerID(),
                reservationDto.getReservationDate(),
                reservationDto.getNumberOfGuests(),
                reservationDto.getSpecialRequests(),
                reservationDto.getStatus(),
                reservationDto.getReservationID()
        );
        tableAssignmentDAO.update(TableAssignmentEntity);
        return update;
    }
    @Override
    public boolean delete(String reservationID) throws SQLException {
        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";
        return SQLUtil.execute(sql, reservationID);
    }
    @Override
    public ReservationDto search(String reservationID) throws SQLException {
        String sql = "SELECT * FROM Reservations WHERE ReservationID = ?";
        ResultSet rst = SQLUtil.execute(sql, reservationID);

        if (rst.next()) {
            return new ReservationDto(
                    rst.getString("ReservationID"),
                    rst.getString("CustomerID"),
                    rst.getDate("ReservationDate").toLocalDate(),
                    rst.getInt("NumberOfGuests"),
                    rst.getString("SpecialRequests"),
                    rst.getString("Status")
            );
        }
        return null;
    }

}
