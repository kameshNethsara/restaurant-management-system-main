//package com.ijse.rms.model;
////
////import com.ijse.rms.db.DBConnection;
////
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////
////public class LoginViewModel {
/////*
////    public boolean userChecker(String username, String password) throws SQLException, ClassNotFoundException {
////        boolean checker = false;
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? ";
////
////        PreparedStatement statement = connection.prepareStatement(sql);
////        statement.setString(1,username);
////        statement.setString(2, password);
////
////        try(ResultSet rst = statement.executeQuery()){
////            if (rst.next()) {
////                checker = true;
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return checker;
////    }
////*/
////}
//
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.util.CrudUtil;
//import com.ijse.rms.dto.UserViewDto;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginViewModel {
//
//    // Method to authenticate the user based on username and password
//    public static boolean authenticateUser(String username, String password) throws SQLException {
//        // SQL query to check if there is a matching username and password in the Users table
//        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username, password);
//
//        // If resultSet contains any data, the login credentials are valid
//        return resultSet.next();
//    }
//
//    // Method to check if the user has a valid position (Manager or Cashier)
//    public static boolean isValidPosition(String username) throws SQLException {
//        // SQL query to get the position of the user based on their username
//        String sql = "SELECT E.Position FROM Users U " +
//                "JOIN Employees E ON U.EmployeeID = E.EmployeeID " +
//                "WHERE U.Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username);
//
//        // If the resultSet contains data, check the position
//        if (resultSet.next()) {
//            String position = resultSet.getString("Position");
//            // Check if the position is either Manager or Cashier
//            return position.equalsIgnoreCase("Manager") || position.equalsIgnoreCase("Cashier");
//        }
//
//        // If no matching position found, return false
//        return false;
//    }
//
//    // Method to get user details (this could be useful for further processing like fetching EmployeeID)
//    public static UserViewDto getUserDetails(String username) throws SQLException {
//        // SQL query to get user details
//        String sql = "SELECT * FROM Users WHERE Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username);
//
//        // If the user exists, return the user details
//        if (resultSet.next()) {
//            UserViewDto user = new UserViewDto();
//            user.setId(resultSet.getString("UserID"));
//            user.setName(resultSet.getString("Username"));
//            user.setPassword(resultSet.getString("Password"));
//            user.setLoginTime(resultSet.getTimestamp("LoginTime").toLocalDateTime());
//            user.setEmployeeId(resultSet.getString("EmployeeID"));
//            return user;
//        }
//
//        // If user not found, return null
//        return null;
//    }
//
//    // Method to update the login time after a successful login
//    public static boolean updateLoginTime(String userID) throws SQLException {
//        // SQL query to update the login time of the user
//        String sql = "UPDATE Users SET LoginTime = NOW() WHERE UserID = ?";
//
//        // Executing the update query
//        return CrudUtil.execute(sql, userID);
//    }
//}

//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.util.CrudUtil;
//import com.ijse.rms.util.EncryptionUtil;
//import com.ijse.rms.dto.UserViewDto;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginViewModel {
//
//    // Method to authenticate the user based on username and password
//    public static boolean authenticateUser(String username, String password) throws SQLException {
//        // SQL query to check if there is a matching username in the Users table
//        String sql = "SELECT * FROM Users WHERE Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username);
//
//        // If user exists, fetch the salt and the hashed password
//        if (resultSet.next()) {
//            String storedSalt = resultSet.getString("Salt");
//            String storedHashedPassword = resultSet.getString("Password");
//
//            // Hash the input password with the stored salt
//            String inputHashedPassword = EncryptionUtil.hashPassword(password, storedSalt);
//
//            // Compare the hashed input password with the stored hashed password
//            return storedHashedPassword.equals(inputHashedPassword);
//        }
//
//        // If no matching username found, return false
//        return false;
//    }
//
//    // Method to check if the user has a valid position (Manager or Cashier)
//    public static boolean isValidPosition(String username) throws SQLException {
//        // SQL query to get the position of the user based on their username
//        String sql = "SELECT E.Position FROM Users U " +
//                "JOIN Employees E ON U.EmployeeID = E.EmployeeID " +
//                "WHERE U.Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username);
//
//        // If the resultSet contains data, check the position
//        if (resultSet.next()) {
//            String position = resultSet.getString("Position");
//            // Check if the position is either Manager or Cashier
//            return position.equalsIgnoreCase("Manager") || position.equalsIgnoreCase("Cashier");
//        }
//
//        // If no matching position found, return false
//        return false;
//    }
//
//    // Method to get user details (this could be useful for further processing like fetching EmployeeID)
//    public static UserViewDto getUserDetails(String username) throws SQLException {
//        // SQL query to get user details
//        String sql = "SELECT * FROM Users WHERE Username = ?";
//
//        // Executing the query and getting the result set
//        ResultSet resultSet = CrudUtil.execute(sql, username);
//
//        // If the user exists, return the user details
//        if (resultSet.next()) {
//            UserViewDto user = new UserViewDto();
//            user.setId(resultSet.getString("UserID"));
//            user.setName(resultSet.getString("Username"));
//            user.setPassword(resultSet.getString("Password"));
//            user.setLoginTime(resultSet.getTimestamp("LoginTime").toLocalDateTime());
//            user.setEmployeeId(resultSet.getString("EmployeeID"));
//            return user;
//        }
//
//        // If user not found, return null
//        return null;
//    }
//
//    // Method to update the login time after a successful login
//    public static boolean updateLoginTime(String userID) throws SQLException {
//        // SQL query to update the login time of the user
//        String sql = "UPDATE Users SET LoginTime = NOW() WHERE UserID = ?";
//
//        // Executing the update query
//        return CrudUtil.execute(sql, userID);
//    }
//
//    // Method to sign in the user: Authenticate and update login time if valid
//    public static boolean signInUser(String username, String password) throws SQLException {
//        // First, authenticate the user
//        if (authenticateUser(username, password)) {
//            // If authentication is successful, fetch user details to update login time
//            UserViewDto user = getUserDetails(username);
//
//            // Update the login time
//            return updateLoginTime(user.getId());
//        }
//
//        // If authentication fails, return false
//        return false;
//    }
//
//
//    // Method to register a new user with encryption
//    public static boolean registerUser(String username, String password) throws SQLException {
//        // Generate a salt for the password
//        String salt = EncryptionUtil.generateSalt();
//
//        // Hash the password with the salt
//        String hashedPassword = EncryptionUtil.hashPassword(password, salt);
//
//        // SQL query to insert a new user into the Users table
//        String sql = "INSERT INTO Users (Username, Password, Salt) VALUES (?, ?, ?)";
//
//        // Executing the insert query
//        return CrudUtil.execute(sql, username, hashedPassword, salt);
//    }
//}
//
