package com.ijse.rms.bo.custom;

import com.ijse.rms.bo.SuperBO;
import com.ijse.rms.dto.*;
import com.ijse.rms.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {

    public String getNextOrderId() throws SQLException;
    public String getNextPaymentID() throws SQLException;
    public boolean placeOrder(OrderViewDto orderDto, ArrayList<OrderItemDto> orderItemDtos, ArrayList<PaymentDto> paymentDtos) throws SQLException;
    public Order orderSearch(String orderId) throws SQLException;
    public MenuItemDto searchMenuItemName(String menuItemName) throws SQLException;
    public CustomerViewDto searchCustomerMobile(String customerMobile) throws SQLException;
    public boolean updateOrderStatus(String orderId, String status) throws SQLException;

}
