package com.ijse.rms.controller;

import com.ijse.rms.dao.SQLUtil;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import java.sql.ResultSet;

public class DashBoardController {

    @FXML
    private AnchorPane dashboardAP;

    @FXML
    private LineChart<String, Number> trendingMenuItem;

    @FXML
    public void initialize() {
        loadTrendingMenuItems(); // Load data when the dashboard initializes
    }

    private void loadTrendingMenuItems() {
        // Set up the axes
        CategoryAxis xAxis = (CategoryAxis) trendingMenuItem.getXAxis();
        xAxis.setLabel("Menu Item");
        NumberAxis yAxis = (NumberAxis) trendingMenuItem.getYAxis();
        yAxis.setLabel("Total Orders");

        trendingMenuItem.setTitle("Trending Menu Items");

        try {
            // SQL query to fetch menu items and their total quantities ordered
            String query = "SELECT mi.Name AS MenuItemName, SUM(oi.Quantity) AS TotalOrders " +
                    "FROM OrderItems oi " +
                    "JOIN MenuItems mi ON oi.MenuItemID = mi.MenuItemID " +
                    "GROUP BY mi.Name " +
                    "ORDER BY TotalOrders DESC";
            ResultSet resultSet = SQLUtil.execute(query);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Trending Menu Items");

            while (resultSet.next()) {
                String menuItemName = resultSet.getString("MenuItemName");
                int totalOrders = resultSet.getInt("TotalOrders");
                series.getData().add(new XYChart.Data<>(menuItemName, totalOrders));
            }

            // Clear any previous data and set the new series
            trendingMenuItem.getData().clear();
            trendingMenuItem.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
