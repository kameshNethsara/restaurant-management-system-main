package com.ijse.rms.controller;

import com.ijse.rms.bo.custom.SupplierBO;
import com.ijse.rms.bo.custom.impl.SupplierBOImpl;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.PurchasesDAO;
import com.ijse.rms.dao.custom.QuaryDAO;
import com.ijse.rms.dao.custom.impl.InventoryItemDAOImpl;
import com.ijse.rms.dao.custom.impl.PurchasesDAOImpl;
import com.ijse.rms.dao.custom.impl.QuaryDAOImpl;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.InventoryItem;
import com.ijse.rms.entity.Supplier;
import com.ijse.rms.tdm.PurchaseTransactionTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {

    SupplierBO supplierBO = new SupplierBOImpl();
    //bo nemi enne transaction eken danata kamak na... aye balanna
    InventoryItemDAO inventoryItemDAO = new InventoryItemDAOImpl();
    PurchasesDAO purchasesDAO = new PurchasesDAOImpl();
    QuaryDAO quaryDAO = new QuaryDAOImpl();

    @FXML
    private Button btnAddPurchaseItem;

    @FXML
    private Button btnRemovePurchase;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInventorySearch;

    @FXML
    private Button btnPurchase;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSupplierSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colInventoryItemId;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colInventoryItemName;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colPurchaseId;

    @FXML
    private TableColumn<PurchaseTransactionTM, Double> colPurchaseItemPrice;

    @FXML
    private TableColumn<PurchaseTransactionTM, Integer> colPurchaseItemQty;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colPurchaseItemStatus;

    @FXML
    private TableColumn<PurchaseTransactionTM, Double> colPurchaseTotalPrice;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colSupplierId;

    @FXML
    private TableColumn<PurchaseTransactionTM, String> colPurchaseDate;

    @FXML
    private TableView<PurchaseTransactionTM> tblPurchaseItem;

    @FXML
    private ChoiceBox<?> choiseBoxPurchaseItemStatus;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDateShow;

    @FXML
    private Label lblInventoryItemId;

    @FXML
    private Label lblInventoryItemName;

    @FXML
    private Label lblPurchaseID;

    @FXML
    private Label lblPurchaseItemId;

    @FXML
    private Label lblPurchaseItemPrice;

    @FXML
    private Label lblPurchaseItemQty;

    @FXML
    private Label lblPurchaseTotalPrice;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSupplierTele;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblTotalAmountShow;

    @FXML
    private TextField txtInventoryItemId;

    @FXML
    private TextField txtInventoryItemName;

    @FXML
    private TextField txtPurchaseID;

    @FXML
    private TextField txtPurchaseItemId;

    @FXML
    private TextField txtPurchaseItemPrice;

    @FXML
    private TextField txtPurchaseItemQty;

    @FXML
    private TextField txtPurchaseItemStatus;

    @FXML
    private TextField txtPurchaseTotalPrice;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierTele;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showDate();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colInventoryItemId.setCellValueFactory(new PropertyValueFactory<>("inventoryItemId"));
        colInventoryItemName.setCellValueFactory(new PropertyValueFactory<>("inventoryItemName"));
        colPurchaseId.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        colPurchaseItemStatus.setCellValueFactory(new PropertyValueFactory<>("purchaseItemStatus"));
        colPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        colPurchaseItemQty.setCellValueFactory(new PropertyValueFactory<>("PurchaseItemQty"));
        colPurchaseItemPrice.setCellValueFactory(new PropertyValueFactory<>("PurchaseItemPrice"));
        colPurchaseTotalPrice.setCellValueFactory(new PropertyValueFactory<>("PurchaseTotalPrice"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void refreshPage() throws SQLException {
        // Clear all text fields
        txtPurchaseID.clear();
        txtSupplierId.clear();
        txtInventoryItemId.clear();
        txtInventoryItemName.clear();
        txtPurchaseItemQty.clear();
        txtPurchaseItemPrice.clear();
        txtPurchaseTotalPrice.clear();

        // Reset ChoiceBox or ComboBox selection if used
        choiseBoxPurchaseItemStatus.getSelectionModel().clearSelection();

        // Auto-generate new IDs or reset fields to default
        try {
            txtPurchaseID.setText(purchasesDAO.getNextPurchaseId());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error generating next Purchase ID: " + e.getMessage()).show();
        }

        // Refresh the table to display updated data
        refreshTable();
    }
    private void refreshTable() {
        try {
            ArrayList<PurchaseTransactionTM> purchaseTransactions = quaryDAO.getAllPurchaseItems();
            ObservableList<PurchaseTransactionTM> purchaseTMS = FXCollections.observableArrayList();

            if (purchaseTransactions != null && !purchaseTransactions.isEmpty()) {
                for (PurchaseTransactionTM transaction : purchaseTransactions) {
                    purchaseTMS.add(new PurchaseTransactionTM(
                            transaction.getSupplierId(),
                            transaction.getInventoryItemId(),
                            transaction.getInventoryItemName(),
                            transaction.getPurchaseId(),
                            transaction.getPurchaseItemStatus(),
                            transaction.getPurchaseDate(),
                            transaction.getPurchaseItemQty(),
                            transaction.getPurchaseItemPrice(),
                            transaction.getPurchaseTotalPrice()
                    ));
                }
            }

            tblPurchaseItem.setItems(purchaseTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void btnAddPurchaseItemOnAction(ActionEvent event) {
        //not compulsory
    }

    @FXML
    void btnRemovePurchaseOnAction(ActionEvent event) {
        //not compulsory
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnInventorySearchOnAction(ActionEvent event) {
        try {
            String inventoryItemName = txtInventoryItemName.getText();
            InventoryItem inventoryItemDto = inventoryItemDAO.searchInventoryItemName(inventoryItemName);
            if (inventoryItemDto != null) {
                txtInventoryItemName.setText(inventoryItemDto.getName());
                txtInventoryItemId.setText(inventoryItemDto.getId());

            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPurchaseOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierSearchOnAction(ActionEvent event) {
        try {
            String supplierMobile = txtSupplierTele.getText();
            Supplier supplierEntity = supplierBO.searchSupplierMobile(supplierMobile);
            if (supplierEntity != null) {
                txtSupplierTele.setText(supplierEntity.getPhone());
                txtSupplierId.setText(supplierEntity.getId());

            } else {
                new Alert(Alert.AlertType.WARNING, "Customer not found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

    private void showDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Timeline dateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDate today = LocalDate.now(); // Get current date
            lblDateShow.setText(today.format(dateFormatter)); // Set it to the date label
        }));

        dateTimeline.setCycleCount(Animation.INDEFINITE); // Keep running indefinitely
        dateTimeline.play(); // Start the timeline for the date
    }

}
