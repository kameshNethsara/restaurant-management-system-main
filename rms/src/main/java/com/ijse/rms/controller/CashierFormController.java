package com.ijse.rms.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CashierFormController implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageCustomers;

    @FXML
    private Button btnManageOrders;

    @FXML
    private Button btnMenuItem;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnTable;

    @FXML
    private AnchorPane cashierAP;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private AnchorPane rightSubPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showDate();
        showTime();
    }

    // Method to show live date
    private void showDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Timeline dateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDate today = LocalDate.now(); // Get current date
            lblDate.setText(today.format(dateFormatter)); // Set it to the date label
        }));

        dateTimeline.setCycleCount(Animation.INDEFINITE); // Keep running indefinitely
        dateTimeline.play(); // Start the timeline for the date
    }

    // Method to show live time
    private void showTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline timeTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime now = LocalTime.now(); // Get current time
            lblTime.setText(now.format(timeFormatter)); // Set it to the time label
        }));

        timeTimeline.setCycleCount(Animation.INDEFINITE); // Keep running indefinitely
        timeTimeline.play(); // Start the timeline for the time
    }

    public void navigateTo(String fxmlPath) {
        try {
            rightPane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource(fxmlPath));
            rightPane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToLogin(ActionEvent event) {
        try {
            cashierAP.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
            cashierAP.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToDashboard(ActionEvent event) {

        System.out.println("Dashboard");
        navigateTo("/view/DashBoard.fxml");

//        try {
//            rightSubPane.getChildren().clear();
//            Parent UserView = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
//            rightSubPane.getChildren().add(UserView);
//        } catch (IOException e) {
//            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
//            e.printStackTrace();
//        }
    }

    @FXML
    void navigateToMenuItems(ActionEvent event) {
        System.out.println("MenuItem");
        navigateTo("/view/MenuItemView.fxml");
    }

    @FXML
    void navigateToReservation(ActionEvent event) {
        System.out.println("RESAVATION");
        navigateTo("/view/Reservation.fxml");
    }

    @FXML
    void navigateToTable(ActionEvent event) {
        System.out.println("Table");
        navigateTo("/view/Table.fxml");
    }

    @FXML
    void navigateToManageCustomers(ActionEvent event) {
         System.out.println("Employees");
        navigateTo("/view/CustomerView.fxml");
    }

    @FXML
    void navigateToManageOrders(ActionEvent event) {
        System.out.println("Orders");
        navigateTo("/view/OrderTransaction.fxml");
    }


    @FXML
    void btnCustomersColorChange(MouseEvent event) {
        btnManageCustomers.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnCustomersColorChangeBack(MouseEvent event) {
        btnManageCustomers.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnDashboardColorChange(MouseEvent event) {
        btnDashboard.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnDashboardColorChangeBack(MouseEvent event) {
        btnDashboard.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnLogoutColorChange(MouseEvent event) {
        btnLogout.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnLogoutColorChangeBack(MouseEvent event) {
        btnLogout.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnMenuItemsColorChange(MouseEvent event) {
        btnMenuItem.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnMenuItemsColorChangeBack(MouseEvent event) {
        btnMenuItem.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnOrdersColorChange(MouseEvent event) {
        btnManageOrders.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnOrdersColorChangeBack(MouseEvent event) {
        btnManageOrders.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnReservationColorChange(MouseEvent event) {
        btnReservation.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnReservationColorChangeBack(MouseEvent event) {
        btnReservation.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnTableColorChange(MouseEvent event) {
        btnTable.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnTableColorChangeBack(MouseEvent event) {
        btnTable.getStyleClass().remove("button-selecthover");
    }


}
