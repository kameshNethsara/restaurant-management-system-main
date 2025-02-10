//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginModel {
//
//    public boolean userChecker(String username, String password) throws SQLException, ClassNotFoundException, SQLException {
//        boolean checker = false;
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? ";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,username);
//        statement.setString(2, password);
//
//        try(ResultSet rst = statement.executeQuery()){
//            if (rst.next()) {
//                checker = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return checker;
//    }
//        // Method to check if the user has a valid position (Manager or Cashier)
//    public static String validPosition(String username) throws SQLException {
//        // SQL query to get the position of the user based on their username
//        String position = null;
//        String sql = "SELECT E.Position FROM Users U " +
//                "JOIN Employees E ON U.EmployeeID = E.EmployeeID " +
//                "WHERE U.Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = SQLUtil.execute(sql, username);
//
//        // If the resultSet contains data, check the position
//        if (resultSet.next()) {
//             position = resultSet.getString("Position");
//            // Check if the position is either Manager or Cashier
//
//        }
//
//        // If no matching position found, return false
//        return position;
//    }
//}
