package com.ijse.rms.dao;

import com.ijse.rms.dto.CustomerViewDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {

    public String getNextId() throws SQLException;
    public boolean save(T DTO) throws SQLException;
    public ArrayList<T> getAllData() throws SQLException;
    public boolean update(T DTO) throws SQLException;
    public  boolean delete(String customerID) throws SQLException;
    public T search(String customerID) throws SQLException;

}
