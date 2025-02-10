package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.EmployeeViewDto;
import com.ijse.rms.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    public String getNextEmployeeId() throws SQLException;
    public boolean saveEmployee(EmployeeViewDto employeeDto) throws SQLException;
    public ArrayList<EmployeeViewDto> getAllEmployee() throws SQLException;
    public boolean updateEmployee(EmployeeViewDto employeeDto) throws SQLException;
    public boolean deleteEmployee(String employeeID) throws SQLException;
    public EmployeeViewDto searchEmployee(String employeeID) throws Exception;

}
