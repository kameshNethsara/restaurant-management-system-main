package com.ijse.rms.dao.custom;

import com.ijse.rms.dao.CrudDAO;
import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dto.OrderViewDto;
import com.ijse.rms.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemDAO extends CrudDAO<Order> {

//    public String getNextOrderId() throws SQLException;
//    public OrderViewDto orderSearch(String orderId) throws SQLException;
//    public ArrayList<OrderViewDto> getAllOrders() throws SQLException;
//    public boolean saveOrder(OrderViewDto orderDto) throws SQLException;
//    public boolean updateOrder(OrderViewDto orderDto) throws SQLException;
//    public boolean deleteOrder(String orderId) throws SQLException;
    public boolean updateOrderStatus(String orderId, String status) throws SQLException;

}
