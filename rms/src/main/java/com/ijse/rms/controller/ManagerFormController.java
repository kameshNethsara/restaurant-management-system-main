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
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManagerFormController implements Initializable {

   @Override
    public void initialize(URL location, ResourceBundle resources) {

       showDate();
       showTime();

//        URL imageUrl = getClass().getResource("/asserts/mr-bean-waiting.gif");
//        if (imageUrl != null) {
//            Image image = new Image(imageUrl.toString());
//            imageview.setImage(image);
//        } else {
//            System.out.println("Image not found");
//        }
   }

    @FXML
    private Label lblDate; // Label for date

    @FXML
    private Label lblTime; // Label for time

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


    @FXML
    private Button btnAccounts;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnManageEmployees;

    @FXML
    private Button btnManageCustomers;

    @FXML
    private Button btnManageOrders;

    @FXML
    private Button btnPurchaseItems;

    @FXML
    private Button btnManageSuppliers;

    @FXML
    private Button btnMenuItem;

//    @FXML
//    private Button btnResavation;

    @FXML
    private Button btnTable;

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane managerPane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private AnchorPane rightSubPane;

    public void navigateTo(String fxmlPath) {
        try {
            rightSubPane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource(fxmlPath));
            rightSubPane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

    @FXML
    void navigateToAccounts(ActionEvent event) {
        //System.out.println("Accounts");
        navigateTo("/view/UserView.fxml");
    }

    @FXML
    void navigateToDashboard(ActionEvent event) {

        System.out.println("Dashboard");
        navigateTo("/view/DashBoard.fxml");
    }

    @FXML
    void navigateToManageEmployees(ActionEvent event) {
        System.out.println("Employees");
        navigateTo("/view/EmployeeView.fxml");
    }

    @FXML
    void navigateToManageCustomers(ActionEvent event) {
        // System.out.println("Employees");
        navigateTo("/view/CustomerView.fxml");
    }

    @FXML
    void navigateToManageOrders(ActionEvent event) {
        //System.out.println("Orders");
        navigateTo("/view/OrderTransaction.fxml");
    }

    @FXML
    void navigateToPurchaseItems(ActionEvent event) {

        System.out.println("PurchaseItems");//my purchase eka (transaction)
        navigateTo("/view/PurchaseView.fxml");
        //navigateTo("/view/PurchaseItemsView.fxml");
    }

    @FXML
    void navigateToManageSuppliers(ActionEvent event) {

        //System.out.println("Suppliers");
        navigateTo("/view/SupplierView.fxml");
    }


    @FXML
    void navigateToMenuItems(ActionEvent event) {
        navigateTo("/view/MenuItemView.fxml");
    }

//    @FXML
//    void navigateToReservation(ActionEvent event) {
//        System.out.println("RESAVATION");
//        navigateTo("/view/Reservation.fxml");
//    }

    @FXML
    void navigateToTable(ActionEvent event) {
        System.out.println("Table");
        navigateTo("/view/Table.fxml");
    }

    @FXML
    void navigateToLogin(ActionEvent event) {
        try {
            managerPane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
            managerPane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogoutColorChange(MouseEvent event) {
        //-fx-background-color: #E53935; -fx-text-fill: #FFFFFF;
        btnLogout.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnLogoutColorChangeBack(MouseEvent event) {
        btnLogout.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnMenuItemsColorChange(MouseEvent event) { btnMenuItem.getStyleClass().add("button-selecthover");}

    @FXML
    void btnMenuItemsColorChangeBack(MouseEvent event) { btnMenuItem.getStyleClass().remove("button-selecthover");}

    @FXML
    void btnAccountColorChange(MouseEvent event) {
        btnAccounts.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnAccountColorChangeBack(MouseEvent event) {
        btnAccounts.getStyleClass().remove("button-selecthover");
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
    void btnEmployeeColorChange(MouseEvent event) {
        btnManageEmployees.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnEmployeeColorChangeBack(MouseEvent event) {
        btnManageEmployees.getStyleClass().remove("button-selecthover");
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
    void btnOrdersColorChange(MouseEvent event) {
        btnManageOrders.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnOrdersColorChangeBack(MouseEvent event) {
        btnManageOrders.getStyleClass().remove("button-selecthover");
    }

    @FXML
    void btnPurchaseItemsColorChange(MouseEvent event) { btnPurchaseItems.getStyleClass().add("button-selecthover");}

    @FXML
    void btnPurchaseItemsColorChangeBack(MouseEvent event) {btnPurchaseItems.getStyleClass().remove("button-selecthover");}

    @FXML
    void btnSuppliersColorChange(MouseEvent event) {
        btnManageSuppliers.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnSuppliersColorChangeBack(MouseEvent event) {
        btnManageSuppliers.getStyleClass().remove("button-selecthover");
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
