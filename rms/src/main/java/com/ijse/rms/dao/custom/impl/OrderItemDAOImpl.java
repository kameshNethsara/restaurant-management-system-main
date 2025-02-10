package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.OrderItemDAO;
import com.ijse.rms.dto.OrderViewDto;
import com.ijse.rms.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemDAOImpl implements OrderItemDAO {

    //--OrderItemDAO = order Table on ERD--

    @Override
    // Method to get the next order ID based on an incremental pattern
    public String getNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");

        if (resultSet.next()) {
            String lastId = resultSet.getString("OrderID").substring(1);
            int nextId = Integer.parseInt(lastId) + 1;
            return String.format("O%03d", nextId);
        }
        return "O001";
    }
    @Override
    // Streamlined Method to search for an order by ID
    public Order search(String orderId) throws SQLException {
        // Define the SQL query for searching the order
        String sql = "SELECT * FROM orders WHERE OrderID = ?";

        // Execute the query using CrudUtil
        ResultSet resultSet = SQLUtil.execute(sql, orderId);

        // Check if the order is found and return it as an OrderViewDto object
        if (resultSet.next()) {
            return new Order(
                    resultSet.getString("OrderID"),
                    resultSet.getString("CustomerID"),
                    resultSet.getString("UserID"),
                    resultSet.getDate("OrderDate").toLocalDate(),
                    resultSet.getDouble("TotalAmount"),
                    resultSet.getString("Status"),
                    resultSet.getString("OrderType"),
                    resultSet.getString("ReservationID"),
                    resultSet.getString("PaymentID")
            );
        }
        return null; // Return null if no order is found
    }
    @Override
    // Method to get all orders
    public ArrayList<Order> getAllData() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Order> orderList = new ArrayList<>();

        while (resultSet.next()) {
            orderList.add(new Order(
                    resultSet.getString("OrderID"),
                    resultSet.getString("CustomerID"),
                    resultSet.getString("UserID"),
                    resultSet.getDate("OrderDate").toLocalDate(),
                    resultSet.getDouble("TotalAmount"),
                    resultSet.getString("Status"),
                    resultSet.getString("OrderType"),
                    resultSet.getString("ReservationID"),
                    resultSet.getString("PaymentID")
            ));
        }
        return orderList;
    }
    @Override
    // Method to save a new order
    public boolean save(Order orderEntity) throws SQLException {
        String sql = "INSERT INTO orders (OrderID, CustomerID, UserID, OrderDate, TotalAmount, Status, OrderType, ReservationID, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,
                orderEntity.getOrderId(),
                orderEntity.getCustomerId(),
                orderEntity.getUserId(),
                orderEntity.getOrderDate(),
                orderEntity.getTotalAmount(),
                orderEntity.getOrderStatus(),
                orderEntity.getOrderType(),
                // orderDto.getReservationId(),
                null, //meka resavation hadala hadanna
                orderEntity.getPaymentId()

        );
    }
    @Override
    // Method to update an existing order
    public boolean update(Order orderEntity) throws SQLException {
        String sql = "UPDATE orders SET CustomerID = ?, UserID = ?, OrderDate = ?, TotalAmount = ?, Status = ?, OrderType = ?, ReservationID = ?, PaymentID = ? WHERE OrderID = ?";
        return SQLUtil.execute(sql,
                orderEntity.getCustomerId(),
                orderEntity.getUserId(),
                orderEntity.getOrderDate(),
                orderEntity.getTotalAmount(),
                orderEntity.getOrderStatus(),
                orderEntity.getOrderType(),
                orderEntity.getReservationId(),
                orderEntity.getPaymentId(),
                orderEntity.getOrderId()
        );
    }
    @Override
    // Method to delete an order by ID
    public boolean delete(String orderId) throws SQLException {
        return SQLUtil.execute("DELETE FROM orders WHERE OrderID = ?", orderId);
    }
    @Override
    public boolean updateOrderStatus(String orderId, String status) throws SQLException {
        String sql = "UPDATE orders SET Status = ? WHERE OrderID = ?";
        return SQLUtil.execute(sql, status, orderId);

    }
}
