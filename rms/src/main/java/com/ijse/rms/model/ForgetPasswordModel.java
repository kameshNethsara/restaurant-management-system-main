//package com.ijse.rms.model;
//
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ForgetPasswordModel {
//    public static String getEmpEmail(String userName) throws SQLException {
//        String sql = "SELECT e.Email " +
//                "FROM Employees e " +
//                "JOIN Users u ON e.EmployeeID = u.EmployeeID " +
//                "WHERE u.Username = ?";
//
//        ResultSet rst = SQLUtil.execute(sql, userName);
//
//        if (rst.next()) {
//            return rst.getString("Email");
//        }
//        return null; // Return null if no email found
//    }
//}
