//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.PaymentDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class PaymentModel {
//
//    public static String getNextPaymentID() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT PaymentID FROM Payments ORDER BY PaymentID DESC LIMIT 1";
//
//        PreparedStatement pts = connection.prepareStatement(sql);
//        ResultSet rst = pts.executeQuery();
//
//        if (rst.next()) {
//            String lastID = rst.getString(1); // e.g., "P001"
//            int lastNumber = Integer.parseInt(lastID.substring(1)); // Extracts the number part
//            int nextNumber = lastNumber + 1; // Increment the number part
//            return String.format("P%03d", nextNumber); // Formats to "P002" etc.
//        }
//        return "P001";
//    }
//
//    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO Payments VALUES (?, ?, ?, ?)",
//                paymentDto.getId(),
//                paymentDto.getMethod(),
//                paymentDto.getAmount(),
//                paymentDto.getDate()
//        );
//    }
//
//    public static ArrayList<PaymentDto> getAllPayments() throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Payments");
//
//        ArrayList<PaymentDto> paymentList = new ArrayList<>();
//
//        while (rst.next()) {
//            PaymentDto payment = new PaymentDto(
//                    rst.getString("PaymentID"),
//                    rst.getString("PaymentMethod"),
//                    rst.getDouble("Amount"),
//                    rst.getDate("PaymentDate").toLocalDate()
//            );
//            paymentList.add(payment);
//        }
//        return paymentList;
//    }
//
//    public boolean updatePayment(PaymentDto paymentDto) throws SQLException {
//        return SQLUtil.execute(
//                "UPDATE Payments SET PaymentMethod=?, Amount=?, PaymentDate=? WHERE PaymentID=?",
//                paymentDto.getMethod(),
//                paymentDto.getAmount(),
//                paymentDto.getDate(),
//                paymentDto.getId()
//        );
//    }
//
//    public static boolean deletePayment(String paymentID) throws SQLException {
//        return SQLUtil.execute("DELETE FROM Payments WHERE PaymentID=?", paymentID);
//    }
//
//    public PaymentDto searchPayment(String paymentID) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Payments WHERE PaymentID=?", paymentID);
//        if (rst.next()) {
//            return new PaymentDto(
//                    rst.getString("PaymentID"),
//                    rst.getString("PaymentMethod"),
//                    rst.getDouble("Amount"),
//                    rst.getDate("PaymentDate").toLocalDate()
//            );
//        }
//        return null;
//    }
//}
