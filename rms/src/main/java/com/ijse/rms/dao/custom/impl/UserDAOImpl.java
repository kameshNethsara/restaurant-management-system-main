package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.UserDAO;
import com.ijse.rms.dto.UserViewDto;
import com.ijse.rms.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    // Method to get the next user ID based on an incremental pattern
    @Override
    public String getNextId() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT UserID FROM users ORDER BY UserID DESC LIMIT 1";
//        PreparedStatement pts = connection.prepareStatement(sql);
//        ResultSet rst = pts.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT UserID FROM users ORDER BY UserID DESC LIMIT 1");

        if (resultSet.next()) {
            String lastId = resultSet.getString("UserID").substring(1);
            int nextId = Integer.parseInt(lastId) + 1;
            return String.format("U%03d", nextId);
        }
        return "U001";
    }
    @Override
    // Method to search for a user by ID
    public User search(String userId) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM users WHERE UserID = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, userId);
//        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users WHERE UserID = ?", userId);

        if (resultSet.next()) {
            return new User(
                    resultSet.getString("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getTimestamp("LoginTime").toLocalDateTime(),
                    resultSet.getString("EmployeeID")
            );
        }
        return null;
    }
    @Override
    // Method to get all users
    public ArrayList<User> getAllData() throws SQLException {
//        ArrayList<UserViewDto> userList = new ArrayList<>();
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM users";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM users");
        ArrayList<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            userList.add(new User(
                    resultSet.getString("UserID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getTimestamp("LoginTime").toLocalDateTime(),
                    resultSet.getString("EmployeeID")
            ));
        }
        return userList;
    }
    @Override
    // Method to save a new user
    public boolean save(User userEntity) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "INSERT INTO users (UserID, Name, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, user.getId());
//        preparedStatement.setString(2, user.getName());
//        preparedStatement.setString(3, user.getPassword());
//        preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getLoginTime()));
//        preparedStatement.setString(5, user.getEmployeeId());
//
//        return preparedStatement.executeUpdate() > 0;

        //String encriptPassword = EncryptionUtil.EncryptPassword(userDto.getPassword());

        String sql = "INSERT INTO users (UserID, Username, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                userEntity.getId(),
                userEntity.getName(),
                // encriptPassword
                userEntity.getPassword(),
                userEntity.getLoginTime(),
                userEntity.getEmployeeId()
        );
    }
    @Override
    // Method to update an existing user
    public boolean update(User userEntity) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "INSERT INTO users (UserID, Name, Password, LoginTime, EmployeeID) VALUES (?, ?, ?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, user.getId());
//        preparedStatement.setString(2, user.getName());
//        preparedStatement.setString(3, user.getPassword());
//        preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getLoginTime()));
//        preparedStatement.setString(5, user.getEmployeeId());
//
//        return preparedStatement.executeUpdate() > 0;
        String sql = "UPDATE users SET Username = ?, Password = ?, LoginTime = ?, EmployeeID = ? WHERE UserID = ?";
        return SQLUtil.execute(sql,
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getLoginTime(),
                userEntity.getEmployeeId(),
                userEntity.getId()
        );
    }
    @Override
    public boolean delete(String userId) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "DELETE FROM users WHERE UserID = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, userId);
//
//        return preparedStatement.executeUpdate() > 0;

        return SQLUtil.execute( "DELETE FROM users WHERE UserID = ?", userId);
    }
//    @Override
//    public boolean updateUserPassword(String userName,String password) throws SQLException {
//        String sql = "UPDATE users SET Password = ? WHERE Username = ?";
//        return SQLUtil.execute(sql,
//                password,
//                userName
//        );
//    }
    @Override
    public boolean updateUserPassword(String username, String password) throws SQLException, ClassNotFoundException, SQLException {

        boolean checker = false;
        //Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ? ";

//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,username);
//        statement.setString(2, password);

        try(ResultSet rst = SQLUtil.execute(sql, username, password)) {
            if (rst.next()) {
                checker = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checker;
    }
    // Method to check if the user has a valid position (Manager or Cashier)

}
