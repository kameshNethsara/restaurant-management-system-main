package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.PaymentDto;
import com.ijse.rms.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {

//    public String getNextPaymentID() throws SQLException;
//    public boolean savePayment(PaymentDto paymentDto) throws SQLException;
//    public ArrayList<PaymentDto> getAllPayments() throws SQLException;
//    public boolean updatePayment(PaymentDto paymentDto) throws SQLException;
//    public boolean deletePayment(String paymentID) throws SQLException;
//    public PaymentDto searchPayment(String paymentID) throws SQLException;

}
