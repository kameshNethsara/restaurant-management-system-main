package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.EmployeeDAO;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.EmployeeViewDto;
import com.ijse.rms.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String getNextId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "select EmployeeID from employees " +
                "order by EmployeeID desc " +
                "limit  1";

        PreparedStatement pts = connection.prepareStatement(sql);
        ResultSet rst = pts.executeQuery();

        if(rst.next()) {
            String string = rst.getString(1); // C001
            String substring = string.substring(1); // 001
            int lastIdIndex = Integer.parseInt(substring); // 1
            int nextIIndex = lastIdIndex+1; // 2
            String newId = String.format("E%03d", nextIIndex); // C002
            return newId;
        }
        return "E001";
    }
    @Override
    public boolean save(Employee employeeEntity) throws SQLException {
        return SQLUtil.execute(
                "insert into employees values (?,?,?,?,?,?)",
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getPosition(),
                employeeEntity.getPhone(),
                employeeEntity.getEmail(),
                java.sql.Date.valueOf(String.valueOf(employeeEntity.getHireDate())) // Correctly convert LocalDate to java.sql.Date
        );
    }
    @Override
    public ArrayList<Employee> getAllData() throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from employees");

        ArrayList<Employee> employees = new ArrayList<>();

        while (rst.next()) {
            Employee employeeEntity = new Employee(
                    rst.getString(1),  // Emp ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Position
                    rst.getString(4),  // Phone
                    rst.getString(5),  // Email
                    rst.getDate(6)     // Directly use java.sql.Date without conversion
            );
            employees.add(employeeEntity);
        }
        return employees;
    }
    @Override
    public boolean update(Employee employeeEntity) throws SQLException {
        return SQLUtil.execute(
                "update employees set Name=?, Position=?, Phone=?, Email=?, HireDate=? where EmployeeID=?",
                employeeEntity.getName(),
                employeeEntity.getPosition(),
                employeeEntity.getPhone(),
                employeeEntity.getEmail(),
                java.sql.Date.valueOf(String.valueOf(employeeEntity.getHireDate())), // Convert LocalDate to java.sql.Date
                employeeEntity.getId()
        );
    }

    @Override
    public boolean delete(String employeeID) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, id);
//        return statement.executeUpdate() > 0 ? "Success" : "Fail";
        return SQLUtil.execute("delete from employees where EmployeeID=?",employeeID);
    }
    @Override
    public Employee search(String employeeID) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employees WHERE EmployeeID = ?", employeeID); // CrudUtil එකේ execute method එක use කරනවා

//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, employeeID);
//
//        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return new Employee(
                    rst.getString("EmployeeID"),
                    rst.getString("Name"),
                    rst.getString("Position"),
                    rst.getString("Phone"),
                    rst.getString("Email"),
                    rst.getDate("HireDate")
            );
        }
        return null;
    }

}
