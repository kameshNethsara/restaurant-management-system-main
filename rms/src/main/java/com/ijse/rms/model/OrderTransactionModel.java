//package com.ijse.rms.model;
//
//import com.ijse.rms.dao.custom.MenuItemIngredentDAO;
//import com.ijse.rms.dao.custom.OrderDAO;
//import com.ijse.rms.dao.custom.OrderItemDAO;
//import com.ijse.rms.dao.custom.PaymentDAO;
//import com.ijse.rms.dao.custom.impl.MenuItemIngredentDAOImpl;
//import com.ijse.rms.dao.custom.impl.OrderDAOImpl;
//import com.ijse.rms.dao.custom.impl.OrderItemDAOImpl;
//import com.ijse.rms.dao.custom.impl.PaymentDAOImpl;
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.*;
//import com.ijse.rms.dao.SQLUtil;
//import javafx.scene.control.Alert;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class OrderTransactionModel {
//
//    PaymentDAO paymentDAO = new PaymentDAOImpl();
//    OrderDAO orderDAO = new OrderDAOImpl();
//    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
//    MenuItemIngredentDAO menuItemIngredentDAO = new MenuItemIngredentDAOImpl();
//
//    //orderview model eke save kara
//
//    public boolean placeOrder(OrderViewDto orderDto, ArrayList<OrderItemDto> orderItemDtos, ArrayList<PaymentDto> paymentDtos) throws SQLException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        try {
//            connection.setAutoCommit(false);
//
//            // Validate input data
//            if (orderDto == null || orderItemDtos == null || paymentDtos == null) {
//                throw new IllegalArgumentException("Order, Order Items, or Payments data is missing.");
//            }
//            if (orderItemDtos.isEmpty()) {
//                throw new IllegalArgumentException("No order items provided.");
//            }
//            if (paymentDtos.isEmpty()) {
//                throw new IllegalArgumentException("No payment details provided.");
//            }
//
//            // Step 1: Save Payments
//            for (PaymentDto paymentDto : paymentDtos) {
//                if (paymentDto.getAmount() <= 0) {
//                    throw new IllegalArgumentException("Invalid payment amount: " + paymentDto.getAmount());
//                }
//
////                boolean savePayment = SQLUtil.execute(
////                        "INSERT INTO Payments VALUES (?, ?, ?, ?)",
////                        paymentDto.getId(),
////                        paymentDto.getMethod(),
////                        paymentDto.getAmount(),
////                        paymentDto.getDate()
////                );
//                //PaymentModel paymentModel = new PaymentModel();
//
//                boolean savePayment = paymentDAO.savePayment(paymentDto);
//
//
//                if (!savePayment) {
//                    connection.rollback();
//                    alertUser("Failed to save payment for ID: " + paymentDto.getId());
//                    return false;
//                }
//            }
//
//            // Step 2: Save Order
////            String sql = "INSERT INTO orders (OrderID, CustomerID, UserID, OrderDate, TotalAmount, Status, OrderType, ReservationID, PaymentID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
////            boolean saveOrder = SQLUtil.execute(sql,
////                    orderDto.getOrderId(),
////                    orderDto.getCustomerId(),
////                    orderDto.getUserId(),
////                    orderDto.getOrderDate(),
////                    orderDto.getTotalAmount(),
////                    orderDto.getOrderStatus(),
////                    orderDto.getOrderType(),
////                    orderDto.getReservationId(),
////                    orderDto.getPaymentId()
////            );
//            boolean saveOrder = orderItemDAO.saveOrder(orderDto);
//            if (!saveOrder) {
//                connection.rollback();
//                alertUser("Failed to save order with ID: " + orderDto.getOrderId());
//                return false;
//            }
//
//            // Step 3: Save Order Items and Update Inventory
//            for (OrderItemDto orderItemDto : orderItemDtos) {
//                if (orderItemDto.getQuantity() <= 0) {
//                    throw new IllegalArgumentException("Invalid quantity for MenuItemID: " + orderItemDto.getMenuItemID());
//                }
//                if (orderItemDto.getPrice() <= 0) {
//                    throw new IllegalArgumentException("Invalid price for MenuItemID: " + orderItemDto.getMenuItemID());
//                }
//
////                String sql1 = "INSERT INTO OrderItems (OrderID, MenuItemID, Quantity, Price) VALUES (?, ?, ?, ?)";
////                boolean saveOrderItem = SQLUtil.execute(sql1,
////                        orderItemDto.getOrderId(),
////                        orderItemDto.getMenuItemID(),
////                        orderItemDto.getQuantity(),
////                        orderItemDto.getPrice()
////                );
//                OrderDAO orderItem = new OrderDAOImpl();
//                boolean saveOrderItem = orderItem.saveOrderItem(orderItemDto);
//
//                if (!saveOrderItem) {
//                    connection.rollback();
//                    alertUser("Failed to save order item with MenuItemID: " + orderItemDto.getMenuItemID());
//                    return false;
//                }
//
//                // 5 Update inventory
//                boolean updateInventory = inventoryItemUpdate(orderItemDto.getMenuItemID(), orderItemDto.getQuantity());
//                if (!updateInventory) {
//                    connection.rollback();
//                    alertUser("Failed to update inventory for MenuItemID: " + orderItemDto.getMenuItemID());
//                    return false;
//                }
//            }
//
//            connection.commit();
//            alertUser("Order placed successfully!");
//            return true;
//
//        } catch (IllegalArgumentException e) {
//            alertUser("Validation error: " + e.getMessage());
//            throw e;
//        } catch (Exception e) {
//            if (connection != null) {
//                connection.rollback();
//            }
//            alertUser("Transaction failed: " + e.getMessage());
//            throw new SQLException("Transaction failed: " + e.getMessage(), e);
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
//
//
//    public boolean inventoryItemUpdate(String menuItemId, double orderQty) {
//        //5 menuItemIngredient
//        try {
//            String sql = "SELECT InventoryItemID, QuantityNeeded FROM MenuItemIngredients WHERE MenuItemID = ?";
//            ResultSet rst = SQLUtil.execute(sql, menuItemId);
//            boolean allUpdatesSuccessful = true;
//
//            while (rst.next()) {
//                String inventoryItemId = rst.getString("InventoryItemID");
//                double qtyNeeded = rst.getDouble("QuantityNeeded"); // Use getDouble for accuracy
//                double qtyUse = orderQty * qtyNeeded;
//
//                String sql2 = "UPDATE InventoryItems SET Quantity = Quantity - ? WHERE InventoryItemID = ?";
//                boolean updateSuccess = SQLUtil.execute(sql2, qtyUse, inventoryItemId);
//
//                if (!updateSuccess) {
//                    allUpdatesSuccessful = false; // Track if any update fails
//                }
//            }
//
//            return allUpdatesSuccessful;
//        } catch (Exception e) {
//            throw new RuntimeException("Error updating inventory items", e);
//        }
//    }
//
//    // Method to display alert to the user
//    private void alertUser(String message) {
//        // Create an alert of type ERROR or INFORMATION based on message type
//        Alert alert = new Alert(Alert.AlertType.INFORMATION); // You can change AlertType based on message importance
//        alert.setTitle("Order Status");
//        alert.setHeaderText(null);  // No header text
//        alert.setContentText(message);  // The message to display in the alert
//
//        alert.showAndWait();  // Display the alert and wait for user to close it
//    }
//
//    public boolean updateOrderStatus(String orderId, String status) {
//        try {
//            String sql = "UPDATE orders SET Status = ? WHERE OrderID = ?";
//            return SQLUtil.execute(sql, status, orderId);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to update the order status: " + orderId, e);
//        }
//    }
//
//
////    public boolean inventoryItemUpdate(String menuItemId , double orderQty){
////       //5 menuItemIngredient
////        try{
////            String sql = "SELECT InventoryItemID , QuantityNeeded FROM MenuItemIngredients WHERE MenuItemID = ?";
////            ResultSet rst = CrudUtil.execute(sql,menuItemId);
////           while(rst.next()){
////               String inventoryItemId = rst.getString("InventoryItemID");
////               double qtyNeeded = rst.getInt("QuantityNeeded");
////               double qtyUse = orderQty * qtyNeeded;
////
////               String sql2 = "UPDATE InventoryItems SET Quantity = Quantity - ? WHERE InventoryItemID = ?";
////               return CrudUtil.execute(sql2, qtyUse, inventoryItemId);
////           }
////        } catch (Exception e) {
////            throw new RuntimeException(e);
////        }
////        return false;
////    }
//
//
//    ///////////////////////////////////////////////////////////
//
////    public boolean placeOrder(OrderViewDto orderDto, List<OrderItemDto> orderItems, PaymentDto paymentDto, List<MenuItemDto> menuItems, List<MenuItemIngredentsDto> menuItemIngredents) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////
////        try {
////            connection.setAutoCommit(false); // Disable auto-commit
////
////            // Step 1: Save payment
////            boolean isPaymentSaved = PAYMENT_MODEL.savePayment(paymentDto);
////            if (!isPaymentSaved) {
////                connection.rollback();
////                return false;
////            }
////            // Step 2: Save the order
////            boolean isOrderSaved = saveInToOrder(orderDto);
////            if (!isOrderSaved) {
////                connection.rollback();
////                return false;
////            }
////
//////            // Step 2: Save payment
//////            boolean isPaymentSaved = PAYMENT_MODEL.savePayment(paymentDto);
//////            if (!isPaymentSaved) {
//////                connection.rollback();
//////                return false;
//////            }
////
////            // Step 3: Save each order item
////            OrderItemModel orderItemModel = new OrderItemModel();
////            for (OrderItemDto orderItemDto : orderItems) {
////                boolean isOrderItemSaved = orderItemModel.saveOrderItem(orderItemDto);
////                if (!isOrderItemSaved) {
////                    connection.rollback();
////                    return false;
////                }
////            }
////
////            // Step 4: Save each menu item
////            for (MenuItemDto menuItemDto : menuItems) {
////                boolean isMenuItemSaved = MENU_ITEM_MODEL.saveMenuItem(menuItemDto);
////                if (!isMenuItemSaved) {
////                    connection.rollback();
////                    return false;
////                }
////            }
////
////            // Step 5: Save each menu item ingredient
////            for (MenuItemIngredentsDto ingredentsDto : menuItemIngredents) {
////                boolean isIngredentsSaved = MENU_ITEM_INGREDENT_MODEL.saveMenuItemIngredents(ingredentsDto);
////                if (!isIngredentsSaved) {
////                    connection.rollback();
////                    return false;
////                }
////            }
////
////            // Step 6: Update Inventory items and quantities
////            boolean isInventoryUpdated = updateInventoryQuantities(menuItemIngredents);
////            if (!isInventoryUpdated) {
////                connection.rollback();
////                return false;
////            }
////
////            connection.commit(); // Commit if all steps are successful
////            return true;
////        } catch (Exception e) {
////            connection.rollback(); // Rollback in case of an exception
////            e.printStackTrace(); // Log the exception
////            return false;
////        } finally {
////            connection.setAutoCommit(true); // Restore auto-commit
////        }
////    }
////
////    private boolean updateInventoryQuantities(List<MenuItemIngredentsDto> menuItemIngredents) {
////        return false;
////    }
///*
//    // Method to calculate and update inventory quantities based on ingredients
//    private boolean updateInventoryQuantities(List<MenuItemIngredentsDto> menuItemIngredents) throws SQLException {
//        InventoryItemModel inventoryItemModel = new InventoryItemModel();
//
//        for (MenuItemIngredentsDto ingredientDto : menuItemIngredents) {
//            int ingredientRequiredQty = ingredientDto.getQtyNeeded(); // Quantity needed per menu item
//            int orderQty = getOrderQuantityForMenuItem(ingredientDto.getMenuItemId()); // Order quantity for the menu item
//
//            // Calculate the total quantity needed for this ingredient
//            int totalRequiredQty = orderQty * ingredientRequiredQty;
//
//            // Update the inventory quantity
//            boolean isInventoryUpdated = updateInventoryQuantity(ingredientDto.getInventoryId(), totalRequiredQty);
//            if (!isInventoryUpdated) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // Method to get the order quantity for a menu item
//    public int getOrderQuantityForMenuItem(String menuItemId) throws SQLException {
//        String query = "SELECT SUM(Quantity) FROM OrderItems WHERE MenuItemID = ?";
//        ResultSet resultSet = CrudUtil.execute(query, menuItemId);
//
//        if (resultSet.next()) {
//            return resultSet.getInt(1); // Retrieves the total quantity ordered for the menu item
//        }
//        return 0; // Return 0 if no orders exist for the menu item
//    }
//
//
//    public boolean updateInventoryQuantity(String inventoryItemId, int totalRequiredQty) throws SQLException {
//        String query = "UPDATE InventoryItems SET Quantity = Quantity - ? WHERE InventoryItemID = ?";
//        int affectedRows = (int) CrudUtil.execute(query, totalRequiredQty, inventoryItemId);
//
//        return affectedRows > 0; // If at least one row is affected, return true
//    }
//
//
//    // Method to calculate total required quantity of an ingredient
//    public int getRequiredQuantity(String menuItemId, int orderQty) throws SQLException {
//        List<MenuItemIngredentsDto> ingredients = getIngredientsForMenuItem(menuItemId); // Retrieve ingredients for the menu item
//
//        int totalRequiredQty = 0;
//        for (MenuItemIngredentsDto ingredientDto : ingredients) {
//            int ingredientQty = ingredientDto.getQtyNeeded(); // Quantity required per menu item
//            totalRequiredQty += ingredientQty * orderQty; // Multiply by the number of items ordered
//        }
//
//        return totalRequiredQty;
//    }
//
//    // Retrieve the ingredients for a menu item
//    private List<MenuItemIngredentsDto> getIngredientsForMenuItem(String menuItemId) throws SQLException {
//        String query = "SELECT * FROM MenuItemIngredents WHERE MenuItemId = ?";
//        ResultSet resultSet = CrudUtil.execute(query, menuItemId);
//
//        List<MenuItemIngredentsDto> ingredients = new ArrayList<>();
//        while (resultSet.next()) {
//            String inventoryItemId = resultSet.getString("InventoryItemID");
//            int quantityNeeded = resultSet.getInt("QuantityNeeded");
//            MenuItemIngredentsDto ingredient = new MenuItemIngredentsDto(menuItemId, inventoryItemId, quantityNeeded);
//            ingredients.add(ingredient);
//        }
//
//        return ingredients;
//    }
//
// */
//}
