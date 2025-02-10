//package com.ijse.rms.model;
//
//import com.ijse.rms.dto.OrderItemDto;
//import com.ijse.rms.dao.SQLUtil;
//
//import java.sql.SQLException;
//
//public class OrderItemModel {
//
//    // Method to save
//    public boolean saveOrderItem(OrderItemDto orderItemDto) throws SQLException {
//        String sql1 = "INSERT INTO OrderItems (OrderID, MenuItemID, Quantity, Price) VALUES (?, ?, ?, ?)";
//        boolean saveOrderItem = SQLUtil.execute(sql1,
//                orderItemDto.getOrderId(),
//                orderItemDto.getMenuItemID(),
//                orderItemDto.getQuantity(),
//                orderItemDto.getPrice()
//        );
//        return saveOrderItem;
//    }
//}
