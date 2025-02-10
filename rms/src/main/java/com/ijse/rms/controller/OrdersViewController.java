//package com.ijse.rms.controller;
//
//import com.ijse.rms.dao.custom.OrderItemDAO;
//import com.ijse.rms.dao.custom.impl.OrderItemDAOImpl;
//import com.ijse.rms.dto.OrderViewDto;
//import com.ijse.rms.tdm.OrderTM;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.control.Label;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class OrdersViewController {
//
//    //private final OrderViewModel orderModel = new OrderViewModel();
//    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
//
//    @FXML
//    private Button btnDelete, btnReset, btnSave, btnUpdate,btnSearch;
//
//    @FXML
//    private TableColumn<OrderTM, String> colCustomerId, colOrderId, colOrderType, colPaymentId, colReservationId, colStatus, colUserId;
//    @FXML
//    private TableColumn<OrderTM, Double> colTotalAmount;
//    @FXML
//    private TableColumn<OrderTM, LocalDate> colOrderDate;
//
//    @FXML
//    private TableView<OrderTM> tblOrders;
//
//    @FXML
//    private TextField txtCustomerId, txtOrderAmount, txtOrderStatus, txtOrderType, txtOrdersId, txtPaymentId, txtReservationId, txtUserId;
//
//    @FXML
//    private Label lblDate, lblOrderDate;
//
//    @FXML
//    void initialize() {
//        initializeTable();
//        refreshTable();
//        txtOrdersId.setText(getNextOrderId());
//
//        // Format the current date to "dd-MM-yyyy" format
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        lblOrderDate.setText(LocalDate.now().format(formatter)); // Set formatted date in lblOrderDate
//
//        btnUpdate.setDisable(true);
//        btnDelete.setDisable(true);
//    }
//
//    private void initializeTable() {
//        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
//        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
//        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
//        colOrderType.setCellValueFactory(new PropertyValueFactory<>("orderType"));
//        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
//        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
//    }
//
//    @FXML
//    void btnSaveOnAction(ActionEvent event) {
//        try {
//            // Validate required fields before proceeding
//            if (txtOrdersId.getText().isEmpty() || txtCustomerId.getText().isEmpty() || txtUserId.getText().isEmpty() ||
//                    txtOrderAmount.getText().isEmpty() || txtOrderStatus.getText().isEmpty() ||
//                    txtOrderType.getText().isEmpty() || txtReservationId.getText().isEmpty() || txtPaymentId.getText().isEmpty()) {
//                System.out.println("All fields are required.");
//                return; // Exit if any field is empty
//            }
//
//            // Attempt to parse the order amount to a double
//            double orderAmount;
//            try {
//                orderAmount = Double.parseDouble(txtOrderAmount.getText());
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid order amount. Please enter a valid number.");
//                return;
//            }
//
//            // Create OrderViewDto object with parsed data
//            OrderViewDto order = new OrderViewDto(
//                    txtOrdersId.getText(),
//                    txtCustomerId.getText(),
//                    txtUserId.getText(),
//                    LocalDate.now(), // Assume current date for simplicity
//                    orderAmount,
//                    txtOrderStatus.getText(),
//                    txtOrderType.getText(),
//                    txtReservationId.getText(),
//                    txtPaymentId.getText()
//            );
//
//            // Save the order and refresh the page if successful
//            if (orderItemDAO.save(order)) {
//                System.out.println("Order saved successfully.");
//                refreshPage();
//            } else {
//                System.out.println("Order could not be saved.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @FXML
//    void btnUpdateOnAction(ActionEvent event) {
//        OrderViewDto order = new OrderViewDto(
//                txtOrdersId.getText(),
//                txtCustomerId.getText(),
//                txtUserId.getText(),
//                LocalDate.now(), // Assume current date for simplicity
//                Double.parseDouble(txtOrderAmount.getText()),
//                txtOrderStatus.getText(),
//                txtOrderType.getText(),
//                txtReservationId.getText(),
//                txtPaymentId.getText()
//        );
//
//        try {
//            if (orderItemDAO.update(order)) {
//                refreshPage();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void btnDeleteOnAction(ActionEvent event) {
//        try {
//            if (orderItemDAO.delete(txtOrdersId.getText())) {
//                refreshPage();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void btnSearchOnAction(ActionEvent event) {
//        String orderId = txtOrdersId.getText(); // Assuming orderId is entered in txtOrdersId
//
//        try {
//            OrderViewDto order = orderItemDAO.search(orderId);
//
//            if (order != null) {
//                // Populate the text fields with order details
//                txtCustomerId.setText(order.getCustomerId());
//                txtOrderType.setText(order.getOrderType());
//                txtPaymentId.setText(order.getPaymentId());
//                txtReservationId.setText(order.getReservationId());
//                txtOrderStatus.setText(order.getOrderStatus());
//                txtUserId.setText(order.getUserId());
//                txtOrderAmount.setText(String.valueOf(order.getTotalAmount()));
//                lblOrderDate.setText(order.getOrderDate().toString());
//
//                btnUpdate.setDisable(false);
//                btnDelete.setDisable(false);
//                btnSave.setDisable(true);
//            } else {
//                System.out.println("Order not found!");
//                // Optional: show an alert to the user
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void btnResetOnAction(ActionEvent event) {
//        refreshPage();
//    }
//
//    @FXML
//    void onClickTable(MouseEvent event) {
//        OrderTM selectedOrder = tblOrders.getSelectionModel().getSelectedItem();
//        if (selectedOrder != null) {
//            txtOrdersId.setText(selectedOrder.getOrderId());
//            txtCustomerId.setText(selectedOrder.getCustomerId());
//            txtOrderType.setText(selectedOrder.getOrderType());
//            txtPaymentId.setText(selectedOrder.getPaymentId());
//            txtReservationId.setText(selectedOrder.getReservationId());
//            txtOrderStatus.setText(selectedOrder.getOrderStatus());
//            txtUserId.setText(selectedOrder.getUserId());
//            txtOrderAmount.setText(String.valueOf(selectedOrder.getTotalAmount()));
//
//            btnSave.setDisable(true);
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
//        }
//    }
//
//    private void refreshPage() {
//        refreshTable();
//        txtOrdersId.setText(getNextOrderId());
//        txtCustomerId.clear();
//        txtOrderType.clear();
//        txtPaymentId.clear();
//        txtReservationId.clear();
//        txtOrderStatus.clear();
//        txtUserId.clear();
//        txtOrderAmount.clear();
//
//        btnSave.setDisable(false);
//        btnUpdate.setDisable(true);
//        btnDelete.setDisable(true);
//    }
//
//    private String getNextOrderId() {
//        try {
//            return orderItemDAO.getNextId();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    private void refreshTable() {
//        try {
//            ArrayList<OrderViewDto> orderDTOS = orderItemDAO.getAllData();
//            ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();
//
//            for (OrderViewDto orderDTO : orderDTOS) {
//                orderTMS.add(new OrderTM(
//                        orderDTO.getOrderId(),
//                        orderDTO.getCustomerId(),
//                        orderDTO.getUserId(),
//                        orderDTO.getOrderDate(),
//                        orderDTO.getTotalAmount(),
//                        orderDTO.getOrderType(),
//                        orderDTO.getOrderStatus(),
//                        orderDTO.getReservationId(),
//                        orderDTO.getPaymentId()
//                ));
//            }
//            tblOrders.setItems(orderTMS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
