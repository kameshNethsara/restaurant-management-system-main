package com.ijse.rms.db;

import lombok.Getter; //    public Connection getConnection(){return connection;}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    private static DBConnection dBConnection;
    @Getter
    private Connection connection;

    private final String URL ="jdbc:mysql://localhost:3306/RMS";
    private final String USER ="root";
    private final String PASSWORD ="@317Kns20020317";


    private DBConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Log the error with appropriate logging framework
            System.err.println("Database connection failed: " + e.getMessage());
            throw new SQLException("Failed to connect to the database", e);
        }
    }
    public static DBConnection getInstance() throws SQLException {
        if (dBConnection==null){
            dBConnection=new DBConnection();
        }
        return dBConnection;
    }
    /*
    private DBConnection()throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsm","root","@317Kns20020317");
    }
    public static DBConnection getInstance() throws  ClassNotFoundException, SQLException{
        if(dBConnection == null){
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }

    public Connection getConnection(){
        return connection;
    }

 */
}
