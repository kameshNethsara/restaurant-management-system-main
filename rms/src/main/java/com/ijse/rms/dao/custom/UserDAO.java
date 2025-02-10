package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.UserViewDto;
import com.ijse.rms.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {

//    public String getNextUserId() throws SQLException;
//    public UserViewDto searchUser(String userId) throws SQLException;
//    public ArrayList<UserViewDto> getAllUsers() throws SQLException;
//    public boolean saveUser(UserViewDto userDto) throws SQLException;
//    public boolean updateUser(UserViewDto userDto) throws SQLException;
//    public boolean deleteUser(String userId) throws SQLException;
//    //public boolean updateUserPassword(String userName,String password) throws SQLException;
      public boolean updateUserPassword(String username, String password) throws SQLException, ClassNotFoundException, SQLException;
}
