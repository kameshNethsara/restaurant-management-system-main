//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.UserViewDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class UserViewModel {
//
//    // Method to get the next user ID based on an incremental pattern
//    public static String getNextUserId() throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "SELECT UserID FROM users ORDER BY UserID DESC LIMIT 1";
////        PreparedStatement pts = connection.prepareStatement(sql);
////        ResultSet rst = pts.executeQuery();
//
//        ResultSet resultSet = SQLUtil.execute("SELECT UserID FROM users ORDER BY UserID DESC LIMIT 1");
//
//        if (resultSet.next()) {
//            String lastId = resultSet.getString("UserID").substring(1);
//            int nextId = Integer.parseInt(lastId) + 1;
//            return String.format("U%03d", nextId);
//        }
//        return "U001";
//    }
//
//    // Method to search for a user by ID
//    public UserViewDto searchUser(String userId) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "SELECT * FROM users WHERE UserID = ?";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setString(1, userId);
////        ResultSet resultSet = preparedStatement.executeQuery();
//
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users WHERE UserID = ?", userId);
//
//        if (resultSet.next()) {
//            return new UserViewDto(
//                    resultSet.getString("UserID"),
//                    resultSet.getString("Username"),
//                    resultSet.getString("Password"),
//                    resultSet.getTimestamp("LoginTime").toLocalDateTime(),
//                    resultSet.getString("EmployeeID")
//            );
//        }
//        return null;
//    }
//
//    // Method to get all users
//    public ArrayList<UserViewDto> getAllUsers() throws SQLException {
////        ArrayList<UserViewDto> userList = new ArrayList<>();
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "SELECT * FROM users";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        ResultSet resultSet = preparedStatement.executeQuery();
//
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users");
//        ArrayList<UserViewDto> userList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            userList.add(new UserViewDto(
//                    resultSet.getString("UserID"),
//                    resultSet.getString("Username"),
//                    resultSet.getString("Password"),
//                    resultSet.getTimestamp("LoginTime").toLocalDateTime(),
//                    resultSet.getString("EmployeeID")
//            ));
//        }
//        return userList;
//    }
//
//    // Method to save a new user
//    public boolean saveUser(UserViewDto userDto) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "INSERT INTO users (UserID, Name, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setString(1, user.getId());
////        preparedStatement.setString(2, user.getName());
////        preparedStatement.setString(3, user.getPassword());
////        preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getLoginTime()));
////        preparedStatement.setString(5, user.getEmployeeId());
////
////        return preparedStatement.executeUpdate() > 0;
//
//        //String encriptPassword = EncryptionUtil.EncryptPassword(userDto.getPassword());
//
//        String sql = "INSERT INTO users (UserID, Username, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
//        return SQLUtil.execute(sql,
//                userDto.getId(),
//                userDto.getName(),
//                // encriptPassword
//                userDto.getPassword(),
//                userDto.getLoginTime(),
//                userDto.getEmployeeId()
//        );
//    }
//
//    // Method to update an existing user
//    public boolean updateUser(UserViewDto userDto) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "INSERT INTO users (UserID, Name, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setString(1, user.getId());
////        preparedStatement.setString(2, user.getName());
////        preparedStatement.setString(3, user.getPassword());
////        preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getLoginTime()));
////        preparedStatement.setString(5, user.getEmployeeId());
////
////        return preparedStatement.executeUpdate() > 0;
//        String sql = "UPDATE users SET Username = ?, Password = ?, LoginTime = ?, EmployeeID = ? WHERE UserID = ?";
//        return SQLUtil.execute(sql,
//                userDto.getName(),
//                userDto.getPassword(),
//                userDto.getLoginTime(),
//                userDto.getEmployeeId(),
//                userDto.getId()
//        );
//    }
//
//   public boolean deleteUser(String userId) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "DELETE FROM users WHERE UserID = ?";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setString(1, userId);
////
////        return preparedStatement.executeUpdate() > 0;
//
//       return SQLUtil.execute( "DELETE FROM users WHERE UserID = ?", userId);
//    }
//
//    public boolean updateUserPassword(String userName,String password) throws SQLException {
//        String sql = "UPDATE users SET Password = ? WHERE Username = ?";
//        return SQLUtil.execute(sql,
//                password,
//                userName
//        );
//    }
//}
