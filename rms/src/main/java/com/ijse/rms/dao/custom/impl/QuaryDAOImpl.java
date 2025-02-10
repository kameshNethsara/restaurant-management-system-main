package com.ijse.rms.dao.custom.impl;

import com.ijse.rms.dao.SQLUtil;
import com.ijse.rms.dao.custom.QuaryDAO;
import com.ijse.rms.entity.CustomReservation;
import com.ijse.rms.entity.Reservation;
import com.ijse.rms.tdm.PurchaseTransactionTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuaryDAOImpl implements QuaryDAO {
    @Override
    public String validPosition(String username) throws SQLException, ClassNotFoundException {
        // SQL query to get the position of the user based on their username
        String position = null;
        String sql = "SELECT E.Position FROM Users U " +
                "JOIN Employees E ON U.EmployeeID = E.EmployeeID " +
                "WHERE U.Username = ?";

        // Executing the query and getting the result set
        ResultSet resultSet = SQLUtil.execute(sql, username);

        // If the resultSet contains data, check the position
        if (resultSet.next()) {
            position = resultSet.getString("Position");
            // Check if the position is either Manager or Cashier

        }

        // If no matching position found, return false
        return position;
    }
    @Override
    public String getEmpEmail(String userName) throws SQLException {
            String sql = "SELECT e.Email " +
                    "FROM Employees e " +
                    "JOIN Users u ON e.EmployeeID = u.EmployeeID " +
                    "WHERE u.Username = ?";

            ResultSet rst = SQLUtil.execute(sql, userName);

            if (rst.next()) {
                return rst.getString("Email");
            }
            return null; // Return null if no email found
    }
    @Override
    public ArrayList<CustomReservation> getAllReservations() throws SQLException {
        String sql = """
            SELECT
                r.ReservationID,
                r.CustomerID,
                r.ReservationDate,
                r.NumberOfGuests,
                r.SpecialRequests,
                r.Status AS ReservationStatus,
                t.TableID,
                t.TableNumber,
                t.Capacity,  -- Column name in the database
                t.Location AS TableLocation,
                t.Status AS TableStatus,
                ta.TableAssignmentID,
                ta.AssignmentTime
            FROM
                Reservations r
            JOIN
                TableAssignments ta ON r.ReservationID = ta.ReservationID
            JOIN
                Tables t ON ta.TableID = t.TableID;
            """;

        ResultSet rst = SQLUtil.execute(sql);

        ArrayList<CustomReservation> reservations = new ArrayList<>();
        while (rst.next()) {
            reservations.add(new CustomReservation(
                    rst.getString("ReservationID"),
                    rst.getDate("ReservationDate").toLocalDate(),
                    rst.getInt("NumberOfGuests"),
                    rst.getString("SpecialRequests"),
                    rst.getString("TableID"),
                    rst.getString("TableStatus"),
                    rst.getInt("Capacity"),  // Corrected column name
                    rst.getString("TableLocation")
            ));
        }
        return reservations;
    }

    public ArrayList<PurchaseTransactionTM> getAllPurchaseItems() throws SQLException {
        String sql = """
        SELECT 
            pi.PurchaseItemID,
            pi.InventoryItemID,
            ii.Name AS InventoryItemName,
            pi.PurchaseID,
            pi.Status AS PurchaseItemStatus,
            p.PurchaseDate,
            pi.Quantity AS PurchaseItemQty,
            pi.Price AS PurchaseItemPrice,
            p.TotalAmount AS PurchaseTotalPrice
        FROM 
            PurchaseItems pi
        JOIN 
            InventoryItems ii ON pi.InventoryItemID = ii.InventoryItemID
        JOIN 
            Purchases p ON pi.PurchaseID = p.PurchaseID;
    """;

        ResultSet rst = SQLUtil.execute(sql);

        ArrayList<PurchaseTransactionTM> purchaseItems = new ArrayList<>();
        while (rst.next()) {
            purchaseItems.add(new PurchaseTransactionTM(
                    rst.getString("PurchaseID"),          // supplierId mapped to PurchaseID
                    rst.getString("InventoryItemID"),
                    rst.getString("InventoryItemName"),
                    rst.getString("PurchaseID"),
                    rst.getString("PurchaseItemStatus"),
                    rst.getDate("PurchaseDate").toLocalDate(),
                    rst.getInt("PurchaseItemQty"),
                    rst.getDouble("PurchaseItemPrice"),
                    rst.getDouble("PurchaseTotalPrice")
            ));
        }
        return purchaseItems;
    }



}
