package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.OrderDAO;
import com.ijse.rms.dto.OrderItemDto;
import com.ijse.rms.dto.OrderViewDto;
import com.ijse.rms.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    //--OrderDAO = orderItem Table on ERD--

    @Override
    public String getNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(OrderItem orderItemEntity) throws SQLException {
        String sql1 = "INSERT INTO OrderItems (OrderID, MenuItemID, Quantity, Price) VALUES (?, ?, ?, ?)";
        boolean saveOrderItem = SQLUtil.execute(sql1,
                orderItemEntity.getOrderId(),
                orderItemEntity.getMenuItemID(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getPrice()
        );
        return saveOrderItem;
    }

    @Override
    public ArrayList<OrderItem> getAllData() throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderItem orderItemEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String customerID) throws SQLException {
        return false;
    }

    @Override
    public OrderItem search(String customerID) throws SQLException {
        return null;
    }
}
