//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.CustomerViewDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class CustomerViewModel {
//
//    public static String getNextCustomer() throws SQLException {
//    //    Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "select CustomerID from customers " +
//                     "order by CustomerID desc " +
//                     "limit  1";
//
//    //    PreparedStatement pts = connection.prepareStatement(sql);
//    //    ResultSet rst = pts.executeQuery();
//
//        ResultSet rst = SQLUtil.execute(sql);
//
//        if(rst.next()) {
//            String string = rst.getString(1); // C001
//            String substring = string.substring(1); // 001
//            int lastIdIndex = Integer.parseInt(substring); // 1
//            int nextIIndex = lastIdIndex+1; // 2
//            String newId = String.format("C%03d", nextIIndex); // C002
//            return newId;
//        }
//        return "C001";
//
//    }
//
//    public boolean saveCustomer(CustomerViewDto customerDTO) throws SQLException {
//        return SQLUtil.execute(
//                "insert into Customers values (?,?,?,?,?,?)",
//                customerDTO.getId(),
//                customerDTO.getName(),
//                customerDTO.getPhone(),
//                customerDTO.getEmail(),
//                customerDTO.getAddress(),
//                customerDTO.getUserId()
//        );
//    }
//    public static ArrayList<CustomerViewDto> getAllCustomer() throws SQLException {
//        ResultSet rst = SQLUtil.execute("select * from Customers");
//
//        ArrayList<CustomerViewDto> customerDTOS = new ArrayList<>();
//
//        while (rst.next()) {
//            CustomerViewDto customerDTO = new CustomerViewDto(
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getString(4),
//                    rst.getString(5),
//                    rst.getString(6)
//            );
//            customerDTOS.add(customerDTO);
//        }
//        return customerDTOS;
//    }
//
//    public boolean updateCustomer(CustomerViewDto customerDTO) throws SQLException {
//        return SQLUtil.execute(
//                "update Customers set Name=?,Phone=?, Email=?, Address=?, UserID=? where CustomerID=?",
//                customerDTO.getName(),
//                customerDTO.getPhone(),
//                customerDTO.getEmail(),
//                customerDTO.getAddress(),
//                customerDTO.getUserId(),
//                customerDTO.getId()
//
//        );
//    }
//
//    public static boolean deleteCustomer(String customerID) throws SQLException {
//        return SQLUtil.execute("delete from customers where CustomerID=?",customerID);
//    }
//
//    public CustomerViewDto searchCustomer(String customerID) throws SQLException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM customers WHERE CustomerID=?", customerID);
//        if (rst.next()) {
//            return new CustomerViewDto(
//                    rst.getString("CustomerID"),
//                    rst.getString("Name"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getString("Address"),
//                    rst.getString("UserID")
//            );
//        }
//        return null;
//    }
//
//    public static CustomerViewDto searchCustomerMobile(String customerMobile) throws SQLException {
//        String sql = "SELECT * FROM customers WHERE Phone LIKE ?";
//        ResultSet rst = SQLUtil.execute(sql,"%" + customerMobile +"%" );
//        if (rst.next()) {
//            return new CustomerViewDto(
//                    rst.getString("CustomerID"),
//                    rst.getString("Name"),
//                    rst.getString("Phone"),
//                    rst.getString("Email"),
//                    rst.getString("Address"),
//                    rst.getString("UserID")
//            );
//        }
//        return null;
//    }
//
//}
