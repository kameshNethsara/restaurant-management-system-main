package com.ijse.rms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.setTitle("Spice Up - Restaurant Management System");
            // load the icon image
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/cartoon_rms_logo_2-removebg-preview.png")));
            stage.setResizable(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Consider logging the error in a real application
        }
    }

    public static void main(String[] args) {

        launch(args);
       // testDBConnection();

    }
    public static void testDBConnection(){
        String URL = "jdbc:mysql://localhost:3306/RMS";
        String USER = "root";
        String PASSWORD = "@317Kns20020317";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}