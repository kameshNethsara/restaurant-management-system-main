package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.PaymentDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.PaymentDto;
import com.ijse.rms.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT PaymentID FROM Payments ORDER BY PaymentID DESC LIMIT 1";

        PreparedStatement pts = connection.prepareStatement(sql);
        ResultSet rst = pts.executeQuery();

        if (rst.next()) {
            String lastID = rst.getString(1); // e.g., "P001"
            int lastNumber = Integer.parseInt(lastID.substring(1)); // Extracts the number part
            int nextNumber = lastNumber + 1; // Increment the number part
            return String.format("P%03d", nextNumber); // Formats to "P002" etc.
        }
        return "P001";
    }
    @Override
    public boolean save(Payment paymentEntity) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO Payments VALUES (?, ?, ?, ?)",
                paymentEntity.getId(),
                paymentEntity.getMethod(),
                paymentEntity.getAmount(),
                paymentEntity.getDate()
        );
    }
    @Override
    public ArrayList<Payment> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payments");

        ArrayList<Payment> paymentList = new ArrayList<>();

        while (rst.next()) {
            Payment paymentEntity = new Payment(
                    rst.getString("PaymentID"),
                    rst.getString("PaymentMethod"),
                    rst.getDouble("Amount"),
                    rst.getDate("PaymentDate").toLocalDate()
            );
            paymentList.add(paymentEntity);
        }
        return paymentList;
    }
    @Override
    public boolean update(Payment paymentEntity) throws SQLException {
        return SQLUtil.execute(
                "UPDATE Payments SET PaymentMethod=?, Amount=?, PaymentDate=? WHERE PaymentID=?",
                paymentEntity.getMethod(),
                paymentEntity.getAmount(),
                paymentEntity.getDate(),
                paymentEntity.getId()
        );
    }
    @Override
    public boolean delete(String paymentID) throws SQLException {
        return SQLUtil.execute("DELETE FROM Payments WHERE PaymentID=?", paymentID);
    }
    @Override
    public Payment search(String paymentID) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Payments WHERE PaymentID=?", paymentID);
        if (rst.next()) {
            return new Payment(
                    rst.getString("PaymentID"),
                    rst.getString("PaymentMethod"),
                    rst.getDouble("Amount"),
                    rst.getDate("PaymentDate").toLocalDate()
            );
        }
        return null;
    }
}
