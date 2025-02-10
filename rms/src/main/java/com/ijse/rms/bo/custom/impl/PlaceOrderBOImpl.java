package com.ijse.rms.bo.custom.impl;

import com.ijse.rms.bo.custom.PlaceOrderBO;
import com.ijse.rms.dao.DAOFactory;
import com.ijse.rms.dao.custom.*;
import com.ijse.rms.dao.custom.impl.*;
import com.ijse.rms.db.DBConnection;
import com.ijse.rms.dto.*;
import com.ijse.rms.entity.*;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

//    MenuItemDAO menuItemDAO = new MenuItemDAOImpl();
//    OrderDAO orderDAO = new OrderDAOImpl();
//    PaymentDAO paymentDAO = new PaymentDAOImpl();
//    InventoryItemDAO inventoryItemDAO = new InventoryItemDAOImpl();
//    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
//    CustomerDAO customerDAO = new CustomerDAOImpl();

    MenuItemDAO menuItemDAO = (MenuItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MENU_ITEM);;
    InventoryItemDAO inventoryItemDAO = (InventoryItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.INVENTORY);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);
    PaymentDAO paymentDAO =(PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENTS);
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_ITEMS);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);


    @Override
    public String getNextOrderId() throws SQLException {
        return orderItemDAO.getNextId();
    }

    @Override
    public String getNextPaymentID() throws SQLException {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean placeOrder(OrderViewDto orderDto, ArrayList<OrderItemDto> orderItems, ArrayList<PaymentDto> paymentDtos) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            // Validate input data
            if (orderDto == null || orderItems == null || paymentDtos == null) {
                throw new IllegalArgumentException("Order, Order Items, or Payments data is missing.");
            }
            if (orderItems.isEmpty()) {
                throw new IllegalArgumentException("No order items provided.");
            }
            if (paymentDtos.isEmpty()) {
                throw new IllegalArgumentException("No payment details provided.");
            }

            // Step 1: Save Payments
            for (PaymentDto paymentDto : paymentDtos) {
                if (paymentDto.getAmount() <= 0) {
                    throw new IllegalArgumentException("Invalid payment amount: " + paymentDto.getAmount());
                }

                boolean savePayment = paymentDAO.save(new Payment(
                        paymentDto.getId(),
                        paymentDto.getMethod(),
                        paymentDto.getAmount(),
                        paymentDto.getDate()
                ));

                if (!savePayment) {
                    connection.rollback();
                    alertUser("Failed to save payment for ID: " + paymentDto.getId());
                    return false;
                }
            }

            boolean saveOrder = orderItemDAO.save(new Order(
                    orderDto.getOrderId(),
                    orderDto.getCustomerId(),
                    orderDto.getUserId(),
                    orderDto.getOrderDate(),
                    orderDto.getTotalAmount(),
                    orderDto.getOrderStatus(),
                    orderDto.getOrderType(),
                    orderDto.getReservationId(),
                    orderDto.getPaymentId()
            ));
            if (!saveOrder) {
                connection.rollback();
                alertUser("Failed to save order with ID: " + orderDto.getOrderId());
                return false;
            }

            // Step 3: Save Order Items and Update Inventory
            for (OrderItemDto orderItemDto : orderItems) {
                if (orderItemDto.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Invalid quantity for MenuItemID: " + orderItemDto.getMenuItemID());
                }
                if (orderItemDto.getPrice() <= 0) {
                    throw new IllegalArgumentException("Invalid price for MenuItemID: " + orderItemDto.getMenuItemID());
                }

                boolean saveOrderItem = orderDAO.save(new OrderItem(
                        orderItemDto.getOrderItemID(),
                        orderItemDto.getOrderId(),
                        orderItemDto.getMenuItemID(),
                        orderItemDto.getQuantity(),
                        orderItemDto.getPrice()
                ));

                if (!saveOrderItem) {
                    connection.rollback();
                    alertUser("Failed to save order item with MenuItemID: " + orderItemDto.getMenuItemID());
                    return false;
                }

                // 5 Update inventory
                boolean updateInventory = inventoryItemDAO.inventoryItemQTYUpdate(orderItemDto.getMenuItemID(), orderItemDto.getQuantity());
                if (!updateInventory) {
                    connection.rollback();
                    alertUser("Failed to update inventory for MenuItemID: " + orderItemDto.getMenuItemID());
                    return false;
                }
            }

            connection.commit();
            alertUser("Order placed successfully!");
            return true;

        } catch (IllegalArgumentException e) {
            alertUser("Validation error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            alertUser("Transaction failed: " + e.getMessage());
            throw new SQLException("Transaction failed: " + e.getMessage(), e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Order orderSearch(String orderId) throws SQLException {
        return orderItemDAO.search(orderId);
    }

    @Override
    public MenuItemDto searchMenuItemName(String menuItemName) throws SQLException {
        MenuItems menuItems = menuItemDAO.searchMenuItemName(menuItemName);

        if (menuItems == null) {
            return null; // Return null if the menu item is not found
        }

        return new MenuItemDto(
                menuItems.getId(),
                menuItems.getName(),
                menuItems.getDescription(),
                menuItems.getPrice(),
                menuItems.getCategory(),
                menuItems.getKitchenSection()
                );
    }

    @Override
    public CustomerViewDto searchCustomerMobile(String customerMobile) throws SQLException {
        Customer customer = customerDAO.searchCustomerMobile(customerMobile);

        if (customer == null) {
            return null; // Return null if the customer is not found
        }

        return new CustomerViewDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getUserId()
        );
    }

    @Override
    public boolean updateOrderStatus(String orderId, String status) throws SQLException {
        return orderItemDAO.updateOrderStatus(orderId, status);
    }


    private void alertUser(String message) {
        // Create an alert of type ERROR or INFORMATION based on message type
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // You can change AlertType based on message importance
        alert.setTitle("Order Status");
        alert.setHeaderText(null);  // No header text
        alert.setContentText(message);  // The message to display in the alert

        alert.showAndWait();  // Display the alert and wait for user to close it
    }
}
