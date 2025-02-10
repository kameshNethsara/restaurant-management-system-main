package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.SuperDAO;
import com.ijse.rms.entity.CustomReservation;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.tdm.PurchaseTransactionTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuaryDAO  extends SuperDAO {

    public String validPosition(String username) throws SQLException, ClassNotFoundException;
    public String getEmpEmail(String userName) throws SQLException;
    public ArrayList<CustomReservation> getAllReservations() throws SQLException;
    ArrayList<PurchaseTransactionTM> getAllPurchaseItems() throws SQLException;
}


