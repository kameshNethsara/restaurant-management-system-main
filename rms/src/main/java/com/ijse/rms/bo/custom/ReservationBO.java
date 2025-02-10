package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.ReservationDto;
import com.ijse.rms.dto.TableAssignmentDto;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.entity.TableAssignment;

import java.sql.SQLException;

public interface ReservationBO extends SuperBO {

    public String getNextReservationID() throws SQLException;
    public boolean saveReservation(ReservationDto reservationDto , TableAssignmentDto tableAssignmentDto) throws SQLException;
    public boolean updateReservation(ReservationDto reservationDto , TableAssignmentDto tableAssignmentDto) throws SQLException;
    public boolean deleteReservation(String reservationID) throws SQLException;
    public ReservationDto searchReservation(String reservationID) throws SQLException;

}
