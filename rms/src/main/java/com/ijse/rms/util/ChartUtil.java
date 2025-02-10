package com.ijse.rms.util;

import com.ijse.rms.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartUtil {

    // Execute a query with parameters (select or non-select)
    public static ResultSet execute(String query, Object[] params) {
        try {
            Connection connection = DBConnection.getInstance().getConnection(); // Get the connection from DBConnection
            try (PreparedStatement pstm = connection.prepareStatement(query)) {
                // Set parameters in the PreparedStatement
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        pstm.setObject(i + 1, params[i]);
                    }
                }

                // If it's a SELECT query, execute and return the result
                if (query.trim().toUpperCase().startsWith("SELECT")) {
                    return pstm.executeQuery(); // Use executeQuery for SELECT queries
                } else {
                    pstm.executeUpdate(); // Use executeUpdate for INSERT, UPDATE, DELETE
                    return null; // Return null for non-SELECT queries
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Return null if there's an error
    }
}
