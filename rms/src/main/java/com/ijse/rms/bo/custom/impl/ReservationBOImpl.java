package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.ReservationBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.ReservationDAO;
import com.ijse.rms.dao.custom.impl.ReservationDAOImpl;
import com.ijse.rms.dto.ReservationDto;
import com.ijse.rms.dto.TableAssignmentDto;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.entity.TableAssignment;

import java.sql.SQLException;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO =(ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATIONS);

    @Override
    public String getNextReservationID() throws SQLException {
        return reservationDAO.getNextId();
    }

    @Override
    public boolean saveReservation(ReservationDto reservationDto, TableAssignmentDto tableAssignmentDto) throws SQLException {
        Reservation reservation = new Reservation(
                reservationDto.getReservationID(),
                reservationDto.getCustomerID(),
                reservationDto.getReservationDate(),
                reservationDto.getNumberOfGuests(),
                reservationDto.getSpecialRequests(),
                reservationDto.getStatus()
        );
        TableAssignment tableAssignment = new TableAssignment(
                tableAssignmentDto.getTableAssignmentID(),
                tableAssignmentDto.getTableID(),
                tableAssignmentDto.getReservationID(),
                tableAssignmentDto.getAssignmentTime()
        );
        return reservationDAO.saveReservation(reservation, tableAssignment);
    }

    @Override
    public boolean updateReservation(ReservationDto reservationDto, TableAssignmentDto tableAssignmentDto) throws SQLException {
        Reservation reservation = new Reservation(
                reservationDto.getReservationID(),
                reservationDto.getCustomerID(),
                reservationDto.getReservationDate(),
                reservationDto.getNumberOfGuests(),
                reservationDto.getSpecialRequests(),
                reservationDto.getStatus()
        );
        TableAssignment tableAssignment = new TableAssignment(
                tableAssignmentDto.getTableAssignmentID(),
                tableAssignmentDto.getTableID(),
                tableAssignmentDto.getReservationID(),
                tableAssignmentDto.getAssignmentTime()
        );
        return reservationDAO.updateReservation(reservation, tableAssignment);
    }

    @Override
    public boolean deleteReservation(String reservationID) throws SQLException {
        return reservationDAO.delete(reservationID);
    }

    @Override
    public ReservationDto searchReservation(String reservationID) throws SQLException {
        ReservationDto reservation = reservationDAO.search(reservationID);
        if (reservation == null) {
            return null; // Return null if the customer is not found
        }
        return new ReservationDto(
                reservation.getReservationID(),
                reservation.getCustomerID(),
                reservation.getReservationDate(),
                reservation.getNumberOfGuests(),
                reservation.getSpecialRequests(),
                reservation.getStatus()
        );
    }
}
