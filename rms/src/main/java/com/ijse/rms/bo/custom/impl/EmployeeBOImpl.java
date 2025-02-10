package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.EmployeeBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.EmployeeDAO;
import com.ijse.rms.dao.custom.impl.EmployeeDAOImpl;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.dto.EmployeeViewDto;
import com.ijse.rms.entity.Customer;
import com.ijse.rms.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    @Override
    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.getNextId();
    }
    @Override
    public boolean saveEmployee(EmployeeViewDto employeeDto) throws SQLException {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getPosition(),
                employeeDto.getEmail(),
                employeeDto.getPhone(),
                employeeDto.getHireDate()
        );
        return employeeDAO.save(employee);
    }
    @Override
    public ArrayList<EmployeeViewDto> getAllEmployee() throws SQLException {
        ArrayList<Employee> employeeList = employeeDAO.getAllData();
        ArrayList<EmployeeViewDto> employeeViewDtoList = new ArrayList<>();

        for (Employee employee : employeeList) {
            employeeViewDtoList.add(new EmployeeViewDto(
                    employee.getId(),
                    employee.getName(),
                    employee.getPosition(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getHireDate()
            ));
        }

        return employeeViewDtoList;

    }
    @Override
    public boolean updateEmployee(EmployeeViewDto employeeDto) throws SQLException {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getPosition(),
                employeeDto.getEmail(),
                employeeDto.getPhone(),
                employeeDto.getHireDate()
        );
        return employeeDAO.update(employee);
    }

    @Override
    public boolean deleteEmployee(String employeeID) throws SQLException {
        return employeeDAO.delete(employeeID);
    }

    @Override
    public EmployeeViewDto searchEmployee(String employeeID) throws Exception {
        Employee employee = employeeDAO.search(employeeID);
        if (employee == null) {
            return null; // Return null if the customer is not found
        }

        return new EmployeeViewDto(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate()
        );
    }


}
