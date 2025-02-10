//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.OrderViewDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class OrderViewModel {
//
//    // Method to get the next order ID based on an incremental pattern
//    public static String getNextOrderId() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
//
//        if (resultSet.next()) {
//            String lastId = resultSet.getString("OrderID").substring(1);
//            int nextId = Integer.parseInt(lastId) + 1;
//            return String.format("O%03d", nextId);
//        }
//        return "O001";
//    }
//
//    // Streamlined Method to search for an order by ID
//    public OrderViewDto orderSearch(String orderId) throws SQLException {
//        // Define the SQL query for searching the order
//        String sql = "SELECT * FROM orders WHERE OrderID = ?";
//
//        // Execute the query using CrudUtil
//        ResultSet resultSet = SQLUtil.execute(sql, orderId);
//
//        // Check if the order is found and return it as an OrderViewDto object
//        if (resultSet.next()) {
//            return new OrderViewDto(
//                    resultSet.getString("OrderID"),
//                    resultSet.getString("CustomerID"),
//                    resultSet.getString("UserID"),
//                    resultSet.getDate("OrderDate").toLocalDate(),
//                    resultSet.getDouble("TotalAmount"),
//                    resultSet.getString("Status"),
//                    resultSet.getString("OrderType"),
//                    resultSet.getString("ReservationID"),
//                    resultSet.getString("PaymentID")
//            );
//        }
//        return null; // Return null if no order is found
//    }
//
//    // Method to get all orders
//    public ArrayList<OrderViewDto> getAllOrders() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");
//        ArrayList<OrderViewDto> orderList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            orderList.add(new OrderViewDto(
//                    resultSet.getString("OrderID"),
//                    resultSet.getString("CustomerID"),
//                    resultSet.getString("UserID"),
//                    resultSet.getDate("OrderDate").toLocalDate(),
//                    resultSet.getDouble("TotalAmount"),
//                    resultSet.getString("Status"),
//                    resultSet.getString("OrderType"),
//                    resultSet.getString("ReservationID"),
//                    resultSet.getString("PaymentID")
//            ));
//        }
//        return orderList;
//    }
//
//    // Method to save a new order
//    public boolean saveOrder(OrderViewDto orderDto) throws SQLException {
//        String sql = "INSERT INTO orders (OrderID, CustomerID, UserID, OrderDate, TotalAmount, Status, OrderType, ReservationID, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        return SQLUtil.execute(sql,
//                orderDto.getOrderId(),
//                orderDto.getCustomerId(),
//                orderDto.getUserId(),
//                orderDto.getOrderDate(),
//                orderDto.getTotalAmount(),
//                orderDto.getOrderStatus(),
//                orderDto.getOrderType(),
//               // orderDto.getReservationId(),
//                null, //meka resavation hadala hadanna
//                orderDto.getPaymentId()
//
//        );
//    }
//
//    // Method to update an existing order
//    public boolean updateOrder(OrderViewDto orderDto) throws SQLException {
//        String sql = "UPDATE orders SET CustomerID = ?, UserID = ?, OrderDate = ?, TotalAmount = ?, Status = ?, OrderType = ?, ReservationID = ?, PaymentID = ? WHERE OrderID = ?";
//        return SQLUtil.execute(sql,
//                orderDto.getCustomerId(),
//                orderDto.getUserId(),
//                orderDto.getOrderDate(),
//                orderDto.getTotalAmount(),
//                orderDto.getOrderStatus(),
//                orderDto.getOrderType(),
//                orderDto.getReservationId(),
//                orderDto.getPaymentId(),
//                orderDto.getOrderId()
//        );
//    }
//
//    // Method to delete an order by ID
//    public boolean deleteOrder(String orderId) throws SQLException {
//        return SQLUtil.execute("DELETE FROM orders WHERE OrderID = ?", orderId);
//    }
//}
