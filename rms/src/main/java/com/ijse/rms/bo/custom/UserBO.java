package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.UserViewDto;
import com.ijse.rms.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {

    public String getNextUserId() throws SQLException;
    public User searchUser(String userId) throws SQLException;
    public ArrayList<UserViewDto> getAllUsers() throws SQLException;
    public boolean saveUser(UserViewDto userDto) throws SQLException;
    public boolean updateUser(UserViewDto userDto) throws SQLException;
    public boolean deleteUser(String userId) throws SQLException;
    public boolean updateUserPassword(String username, String password) throws SQLException, ClassNotFoundException, SQLException;


}
