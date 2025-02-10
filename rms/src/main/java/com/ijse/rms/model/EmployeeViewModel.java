////package com.ijse.rms.model;
////
////import com.ijse.rms.db.DBConnection;
////import com.ijse.rms.dto.CustomerViewDto;
////import com.ijse.rms.util.CrudUtil;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.util.ArrayList;
////
////public class EmployeeViewModel {
////
////    public static String getNextEmployeeId() throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////
////        String sql = "select EmployeeID from employees " +
////                "order by EmployeeID desc " +
////                "limit  1";
////
////        PreparedStatement pts = connection.prepareStatement(sql);
////        ResultSet rst = pts.executeQuery();
////
////        if(rst.next()) {
////            String string = rst.getString(1); // C001
////            String substring = string.substring(1); // 001
////            int lastIdIndex = Integer.parseInt(substring); // 1
////            int nextIIndex = lastIdIndex+1; // 2
////            String newId = String.format("E%03d", nextIIndex); // C002
////            return newId;
////        }
////        return "E001";
////
////    }
////
////    public static boolean saveEmployee(CustomerViewDto.EmployeeViewDto employeeDTO) throws SQLException {
////        return CrudUtil.execute(
////                "insert into employees values (?,?,?,?,?,?)",
////                employeeDTO.getId(),
////                employeeDTO.getName(),
////                employeeDTO.getPosition(),
////                employeeDTO.getEmail(),
////                employeeDTO.getPhone(),
////                //EmployeeDTO.getHireDate()
////                java.sql.Date.valueOf(employeeDTO.getHireDate())  // converting LocalDate to java.sql.Date
////
////        );
////    }
////
////    public ArrayList<CustomerViewDto.EmployeeViewDto> getAllEmployee() throws SQLException {
////        ResultSet rst = CrudUtil.execute("select * from employees");
////
////        ArrayList<CustomerViewDto.EmployeeViewDto> employeeDTOS = new ArrayList<>();
////
////        while (rst.next()) {
////            CustomerViewDto.EmployeeViewDto employeeDTO = new CustomerViewDto.EmployeeViewDto(
////                    rst.getString(1),  // Emp ID
////                    rst.getString(2),  // Name
////                    rst.getString(3),  // Position
////                    rst.getString(4),  // Phone
////                    rst.getString(5),  // Email
////                    rst.getDate(6).toLocalDate()   //HireDate
////            );
////            employeeDTOS.add(employeeDTO);
////        }
////        return employeeDTOS;
////    }
////
////    public static boolean updateEmployee(CustomerViewDto.EmployeeViewDto employeeDTO) throws SQLException {
////        return CrudUtil.execute(
////                "update employees set Name=?, Position=?, Phone=?, Email=?, HireDate=? where EmployeeID=?",
////                employeeDTO.getName(),
////                employeeDTO.getPosition(),
////                employeeDTO.getPhone(),
////                employeeDTO.getEmail(),
////               // employeeDTO.getHireDate(),
////                java.sql.Date.valueOf(employeeDTO.getHireDate()),  // converting LocalDate to java.sql.Date
////                employeeDTO.getId()
////        );
////    }
////}
//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.EmployeeViewDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class EmployeeViewModel {
//
//    public static String getNextEmployeeId() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "select EmployeeID from employees " +
//                "order by EmployeeID desc " +
//                "limit  1";
//
//        PreparedStatement pts = connection.prepareStatement(sql);
//        ResultSet rst = pts.executeQuery();
//
//        if(rst.next()) {
//            String string = rst.getString(1); // C001
//            String substring = string.substring(1); // 001
//            int lastIdIndex = Integer.parseInt(substring); // 1
//            int nextIIndex = lastIdIndex+1; // 2
//            String newId = String.format("E%03d", nextIIndex); // C002
//            return newId;
//        }
//        return "E001";
//    }
//
//    public boolean saveEmployee(EmployeeViewDto employeeDTO) throws SQLException {
//        return SQLUtil.execute(
//                "insert into employees values (?,?,?,?,?,?)",
//                employeeDTO.getId(),
//                employeeDTO.getName(),
//                employeeDTO.getPosition(),
//                employeeDTO.getPhone(),
//                employeeDTO.getEmail(),
//                java.sql.Date.valueOf(String.valueOf(employeeDTO.getHireDate())) // Correctly convert LocalDate to java.sql.Date
//        );
//    }
//    public static ArrayList<EmployeeViewDto> getAllEmployee() throws SQLException {
//        ResultSet rst = SQLUtil.execute("select * from employees");
//
//        ArrayList<EmployeeViewDto> employeeDTOS = new ArrayList<>();
//
//        while (rst.next()) {
//            EmployeeViewDto employeeDTO = new EmployeeViewDto(
//                    rst.getString(1),  // Emp ID
//                    rst.getString(2),  // Name
//                    rst.getString(3),  // Position
//                    rst.getString(4),  // Phone
//                    rst.getString(5),  // Email
//                    rst.getDate(6)     // Directly use java.sql.Date without conversion
//            );
//            employeeDTOS.add(employeeDTO);
//        }
//        return employeeDTOS;
//    }
//
//    public boolean updateEmployee(EmployeeViewDto employeeDTO) throws SQLException {
//        return SQLUtil.execute(
//                "update employees set Name=?, Position=?, Phone=?, Email=?, HireDate=? where EmployeeID=?",
//                employeeDTO.getName(),
//                employeeDTO.getPosition(),
//                employeeDTO.getPhone(),
//                employeeDTO.getEmail(),
//                java.sql.Date.valueOf(String.valueOf(employeeDTO.getHireDate())), // Convert LocalDate to java.sql.Date
//                employeeDTO.getId()
//        );
//    }
//
//
//    public static boolean deleteEmployee(String employeeID) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
////        PreparedStatement statement = connection.prepareStatement(sql);
////        statement.setString(1, id);
////        return statement.executeUpdate() > 0 ? "Success" : "Fail";
//        return SQLUtil.execute("delete from employees where EmployeeID=?",employeeID);
//    }
//
//    public EmployeeViewDto searchEmployee(String employeeID) throws Exception{
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Employees WHERE EmployeeID = ?", employeeID); // CrudUtil එකේ execute method එක use කරනවා
//
////        PreparedStatement statement = connection.prepareStatement(sql);
////        statement.setString(1, employeeID);
////
////        ResultSet rst = statement.executeQuery();
//        if (rst.next()) {
//            return new EmployeeViewDto(
//                    rst.getString("EmployeeID"),
//                    rst.getString("Name"),
//                    rst.getString("Position"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getDate("HireDate")
//            );
//        }
//        return null;
//    }
//
//}
