package com.ijse.rms.controller;

import com.ijse.rms.bo.custom.TableBO;
import com.ijse.rms.bo.custom.impl.TableBOImpl;
import com.ijse.rms.dto.TableDto;
import com.ijse.rms.entity.Table;
import com.ijse.rms.tdm.TableTM;
//import com.ijse.rms.model.CustomerViewModel;
//import com.ijse.rms.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    TableBO tableBO = new TableBOImpl();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnResavation;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSearchByStatus;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> choiseBoxTableStatus;

    @FXML
    private void initialize() {
        choiseBoxTableStatus.getItems().addAll("Available", "Occupied", "Reserved");
    }

    @FXML
    private TableColumn<TableTM, Integer> colTableCapacity;

    @FXML
    private TableColumn<TableTM, String> colTableId;

    @FXML
    private TableColumn<TableTM, String> colTableLocation;

    @FXML
    private TableColumn<TableTM, Integer> colTableNumber;

    @FXML
    private TableColumn<TableTM, String> colTableStatus;

    @FXML
    private Label lblTableCapacity;

    @FXML
    private Label lblTableId;

    @FXML
    private Label lblTableLocation;

    @FXML
    private Label lblTableNumber;

    @FXML
    private Label lblTableStatus;

    @FXML
    private AnchorPane tableAnchorePane;

    @FXML
    private TableView<TableTM> tblTable;

    @FXML
    private TextField txtTableCapacity;

    @FXML
    private TextField txtTableId;

    @FXML
    private TextField txtTableLocation;

    @FXML
    private TextField txtTableNumber;

    private String getNextTableId() {
        try {
            return tableBO.getNextTableID();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set table column properties
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colTableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        colTableCapacity.setCellValueFactory(new PropertyValueFactory<>("tableCapacity"));
        colTableLocation.setCellValueFactory(new PropertyValueFactory<>("tableLocation"));
        colTableStatus.setCellValueFactory(new PropertyValueFactory<>("tableStatus"));

        try {
            txtTableId.setText(getNextTableId());
            refreshTable();
        } catch (Exception e) { //-----------------------//
            throw new RuntimeException(e);
        }

        // Initialize default button states
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = tableBO.deleteTable(txtTableId.getText());
            if (isDeleted) {
                refreshPage();
            } else {
                showAlert("Error", "Failed to delete the table.");
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        refreshPage();
        txtTableId.setText(getNextTableId());

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String tableId = txtTableId.getText();
            int tableNumber = Integer.parseInt(txtTableNumber.getText());
            int tableCapacity = Integer.parseInt(txtTableCapacity.getText());
            String tableLocation = txtTableLocation.getText();
            String tableStatus = choiseBoxTableStatus.getValue();

            TableDto tableDto = new TableDto(tableId, tableNumber, tableCapacity, tableLocation, tableStatus);
            boolean isSaved = tableBO.saveTable(tableDto);

            if (isSaved) {
                refreshPage();
            } else {
                showAlert("Error", "Failed to save the table.");
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numbers for table number and capacity.");
        }
    }

    @FXML
    void btnSearchByStatusOnAction(ActionEvent event) {
            String selectedStatus = choiseBoxTableStatus.getValue();

            if (selectedStatus == null || selectedStatus.isEmpty()) {
                showAlert("Warning", "Please select a table status.");
                return;
            }

            try {
                // Fetch tables by status
                ArrayList<TableDto> filteredTables = tableBO.searchTableByStatus(selectedStatus);

                // Convert to TableTM for displaying in the TableView
                ObservableList<TableTM> tableTMs = FXCollections.observableArrayList();

                for (TableDto tableDto : filteredTables) {
                    tableTMs.add(new TableTM(
                            tableDto.getTableId(),
                            tableDto.getTableNumber(),
                            tableDto.getTableCapacity(),
                            tableDto.getTableLocation(),
                            tableDto.getTableStatus()
                    ));
                }

                // Update the TableView with filtered data
                tblTable.setItems(tableTMs);

                if (filteredTables.isEmpty()) {
                    showAlert("Info", "No tables found with the selected status.");
                }
            } catch (SQLException e) {
                showAlert("Error", "Database error: " + e.getMessage());
            }


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String tableId = txtTableId.getText();
        try {
            Table tableEntity = tableBO.searchTableById(tableId);
            if (tableEntity != null) {
                txtTableNumber.setText(String.valueOf(tableEntity.getTableNumber()));
                txtTableCapacity.setText(String.valueOf(tableEntity.getTableCapacity()));
                txtTableLocation.setText(tableEntity.getTableLocation());
                choiseBoxTableStatus.setValue(tableEntity.getTableStatus());
            } else {
                showAlert("Warning", "No table found with ID: " + tableId);
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
//            String tableId = txtTableId.getText();
//            int tableNumber = Integer.parseInt(txtTableNumber.getText());
//            int tableCapacity = Integer.parseInt(txtTableCapacity.getText());
//            String tableLocation = txtTableLocation.getText();
//            String tableStatus = choiseBoxTableStatus.getValue();

            TableDto tableDto = new TableDto(
                    txtTableId.getText(),
                    Integer.parseInt(txtTableNumber.getText()),
                    Integer.parseInt(txtTableCapacity.getText()),
                    txtTableLocation.getText(),
                    choiseBoxTableStatus.getValue()
            );
            boolean isUpdated = tableBO.updateTable(tableDto);

            if (isUpdated) {
                refreshPage();
            } else {
                showAlert("Error", "Failed to update the table.");
            }
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numbers for table number and capacity.");
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TableTM selectedTable = tblTable.getSelectionModel().getSelectedItem();
        if (selectedTable != null) {
            txtTableId.setText(selectedTable.getTableId());
            txtTableNumber.setText(String.valueOf(selectedTable.getTableNumber()));
            txtTableCapacity.setText(String.valueOf(selectedTable.getTableCapacity()));
            txtTableLocation.setText(selectedTable.getTableLocation());
            choiseBoxTableStatus.setValue(selectedTable.getTableStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }


    private void refreshPage() {
        txtTableId.clear();
        txtTableNumber.clear();
        txtTableCapacity.clear();
        txtTableLocation.clear();
        choiseBoxTableStatus.getSelectionModel().clearSelection();

        refreshTable();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void refreshTable() {
        try {
            ArrayList<TableDto> tableList = tableBO.getAllTables();
            ObservableList<TableTM> tableTMs = FXCollections.observableArrayList();

            for (TableDto tabledto : tableList) {
                tableTMs.add(new TableTM(
                        tabledto.getTableId(),
                        tabledto.getTableNumber(),
                        tabledto.getTableCapacity(),
                        tabledto.getTableLocation(),
                        tabledto.getTableStatus()
                ));
            }
            tblTable.setItems(tableTMs);
        } catch (SQLException e) {
            showAlert("Error", "Database error: " + e.getMessage());
        }
    }

        @FXML
        void navigateToResavation (ActionEvent event){
            try {
                tableAnchorePane.getChildren().clear();
                Parent UserView = FXMLLoader.load(getClass().getResource("/view/Reservation.fxml"));
                tableAnchorePane.getChildren().add(UserView);
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
                e.printStackTrace();
            }
        }

        @FXML
        void btnResavationColorChange (MouseEvent event){
            btnResavation.getStyleClass().add("button-selecthover");
        }

        @FXML
        void btnResavationColorChangeBack (MouseEvent event){
            btnResavation.getStyleClass().remove("button-selecthover");
        }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
