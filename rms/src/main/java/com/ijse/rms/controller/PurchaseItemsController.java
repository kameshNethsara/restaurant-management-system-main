package com.ijse.rms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PurchaseItemsController {

    @FXML
    private AnchorPane PurchaseItemsView;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colInventoryItemId;

    @FXML
    private TableColumn<?, ?> colPurchaseId;

    @FXML
    private TableColumn<?, ?> colPurchaseItemId;

    @FXML
    private TableColumn<?, ?> colPurchaseItemPrice;

    @FXML
    private TableColumn<?, ?> colPurchaseItemQty;

    @FXML
    private TableColumn<?, ?> colPurchaseItemStatus;

    @FXML
    private Label lblInventoryItemID;

    @FXML
    private Label lblPurchaseID;

    @FXML
    private Label lblPurchaseItemID;

    @FXML
    private Label lblPurchaseItemPrice;

    @FXML
    private Label lblPurchaseItemQty;

    @FXML
    private Label lblPurchaseItemStatus;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tblPurchaseItem;

    @FXML
    private TextField txtInventoryItemID;

    @FXML
    private TextField txtPurchaseID;

    @FXML
    private TextField txtPurchaseItemID;

    @FXML
    private TextField txtPurchaseItemPrice;

    @FXML
    private TextField txtPurchaseItemQty;

    @FXML
    private TextField txtPurchaseItemStatus;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

}

