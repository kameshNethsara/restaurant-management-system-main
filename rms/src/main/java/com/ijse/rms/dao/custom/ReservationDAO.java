package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dto.ReservationDto;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.entity.TableAssignment;

import java.sql.SQLException;

public interface ReservationDAO extends CrudDAO<ReservationDto> {

//    public String getNextReservationID() throws SQLException;
    public boolean saveReservation(Reservation reservationEntity , TableAssignment TableAssignmentEntity) throws SQLException;
    public boolean updateReservation(Reservation reservationEntity , TableAssignment TableAssignmentEntity) throws SQLException;
//    public boolean deleteReservation(String reservationID) throws SQLException;
//    public ReservationDto searchReservation(String reservationID) throws SQLException;

}
