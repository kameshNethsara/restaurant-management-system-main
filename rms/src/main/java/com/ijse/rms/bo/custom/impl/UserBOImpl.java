package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.UserDAO;
import com.ijse.rms.dao.custom.impl.UserDAOImpl;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.dto.UserViewDto;
import com.ijse.rms.entity.Customer;
import com.ijse.rms.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERS);

    // Method to get the next user ID based on an incremental pattern
    @Override
    public String getNextUserId() throws SQLException {
        return userDAO.getNextId();
    }
    @Override
    // Method to search for a user by ID
    public User searchUser(String userId) throws SQLException {
        return userDAO.search(userId);
    }
    @Override
    // Method to get all users
    public ArrayList<UserViewDto> getAllUsers() throws SQLException {
        ArrayList<User> UserList =userDAO.getAllData();
        ArrayList<UserViewDto> userViewDtoList = new ArrayList<>();

        for (User user : UserList) {
            userViewDtoList.add(new UserViewDto(
                 user.getId(),
                 user.getName(),
                 user.getPassword(),
                 user.getLoginTime(),
                 user.getEmployeeId()
            ));
        }
        return userViewDtoList;
    }
    @Override
    // Method to save a new user
    public boolean saveUser(UserViewDto userDto) throws SQLException {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getLoginTime(),
                userDto.getEmployeeId()
        );
        return userDAO.save(user);
    }
    @Override
    // Method to update an existing user
    public boolean updateUser(UserViewDto userDto) throws SQLException {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getLoginTime(),
                userDto.getEmployeeId()
        );
        return userDAO.update(user);
    }
    @Override
    public boolean deleteUser(String userId) throws SQLException {
        return userDAO.delete(userId);
    }
    @Override
    public boolean updateUserPassword(String userName,String password) throws SQLException, ClassNotFoundException {
        return userDAO.updateUserPassword(userName,password);
    }

}
