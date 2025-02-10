//package com.ijse.rms.model;
//
//import com.ijse.rms.db.DBConnection;
//import com.ijse.rms.dto.OrderViewDto;
//import com.ijse.rms.dto.ReservationDto;
//import com.ijse.rms.dto.TableAssignmentDto;
//import com.ijse.rms.dto.TableDto;
//import com.ijse.rms.util.CrudUtil;
//import javafx.scene.control.Alert;
//
//import java.awt.image.PixelGrabber;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class ReservationTransactionModel {
//
//// Method to handle reservation, order, and table assignment as a single transaction
////    public static boolean placeBooking(ReservationDto reservationDto,
////                                                     OrderViewDto orderDto,
////                                                     TableAssignmentDto tableAssignmentDto) throws SQLException {
////        // Get the connection
////        Connection connection = DBConnection.getInstance().getConnection();
////
////        // Disable auto-commit for manual transaction control
////        connection.setAutoCommit(false);
////
////        try {
////            // Step 1: Save reservation details
////            boolean isReservationSaved = CrudUtil.execute(
////                    "INSERT INTO Reservations (ReservationID, CustomerID, ReservationDate, NumberOfGuests, SpecialRequests, Status) " +
////                            "VALUES (?, ?, ?, ?, ?, ?)",
////                    reservationDto.getReservationID(),
////                    reservationDto.getCustomerID(),
////                    reservationDto.getReservationDate(),
////                    reservationDto.getNumberOfGuests(),
////                    reservationDto.getSpecialRequests(),
////                    reservationDto.getStatus()
////            );
////
////            if (!isReservationSaved) {
////                connection.rollback(); // Rollback if reservation fails
////                return false;
////            }
////
////            // Step 2: Save order details
////            boolean isOrderSaved = CrudUtil.execute(
////                    "INSERT INTO Orders (OrderID, CustomerID, UserID, OrderDate, TotalAmount, Status, OrderType, ReservationID, PaymentID) " +
////                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
////                    orderDto.getOrderId(),
////                    orderDto.getCustomerId(),
////                    orderDto.getUserId(),
////                    orderDto.getOrderDate(),
////                    orderDto.getTotalAmount(),
////                    orderDto.getOrderStatus(),
////                    orderDto.getOrderType(),
////                    reservationDto.getReservationID(), // Link the order to the reservation
////                    orderDto.getPaymentId()
////            );
////
////            if (!isOrderSaved) {
////                connection.rollback(); // Rollback if order fails
////                return false;
////            }
////
////            // Step 3: Save table assignment details
////            boolean isTableAssigned = CrudUtil.execute(
////                    "INSERT INTO TableAssignments (TableAssignmentID, TableID, ReservationID, AssignmentTime) " +
////                            "VALUES (?, ?, ?, ?)",
////                    tableAssignmentDto.getTableAssignmentID(),
////                    tableAssignmentDto.getTableID(),
////                    reservationDto.getReservationID(), // Link table assignment to the reservation
////                    tableAssignmentDto.getAssignmentTime()
////            );
////
////            if (!isTableAssigned) {
////                connection.rollback(); // Rollback if table assignment fails
////                return false;
////            }
////
////            // If all operations are successful, commit the transaction
////            connection.commit();
////            return true;
////
////        } catch (SQLException e) {
////            // Rollback transaction if an exception occurs
////            connection.rollback();
////            throw e;
////        } finally {
////            // Reset auto-commit to true after the transaction is done
////            connection.setAutoCommit(true);
////        }
////    }
//
//    public static boolean placeBooking(ReservationDto reservationDto) throws SQLException {
//        boolean isReservationSaved = CrudUtil.execute(
//                    "INSERT INTO Reservations (ReservationID, CustomerID, ReservationDate, NumberOfGuests, SpecialRequests, Status) " +
//                            "VALUES (?, ?, ?, ?, ?, ?)",
//                    reservationDto.getReservationID(),
//                    reservationDto.getCustomerID(),
//                    reservationDto.getReservationDate(),
//                    reservationDto.getNumberOfGuests(),
//                    reservationDto.getSpecialRequests(),
//                    reservationDto.getStatus()
//        );
//        return isReservationSaved;
//    }
//
//    public static boolean updateBooking(ReservationDto reservationDto) throws SQLException {
//        String sql = "UPDATE Reservations SET CustomerID = ?, ReservationDate = ?, NumberOfGuests = ?, SpecialRequests = ?, Status = ? " +
//                "WHERE ReservationID = ?";
//        return CrudUtil.execute(
//                sql,
//                reservationDto.getCustomerID(),
//                reservationDto.getReservationDate(),
//                reservationDto.getNumberOfGuests(),
//                reservationDto.getSpecialRequests(),
//                reservationDto.getStatus(),
//                reservationDto.getReservationID()
//        );
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
//}
