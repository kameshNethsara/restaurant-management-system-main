package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.EmployeeViewDto;
import com.ijse.rms.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee> {

//    public String getNextEmployeeId() throws SQLException;
//    public boolean saveEmployee(EmployeeViewDto employeeDTO) throws SQLException;
//    public ArrayList<EmployeeViewDto> getAllEmployee() throws SQLException;
//    public boolean updateEmployee(EmployeeViewDto employeeDTO) throws SQLException;
//    public boolean deleteEmployee(String employeeID) throws SQLException;
//    public EmployeeViewDto searchEmployee(String employeeID) throws Exception;

}
