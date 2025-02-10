package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.InventoryItemBO;
import com.ijse.rms.bo.custom.impl.InventoryItemBOImpl;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.entity.InventoryItem;
import com.ijse.rms.tdm.InventoryItemTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InventoryItemController implements Initializable {

   InventoryItemBO inventoryItemBO = (InventoryItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.INVENTORY);

    @FXML
    private ChoiceBox<String> ChoiseBoxInventoryItemUnit;

    @FXML
    private AnchorPane inventoryItemAnchorPane;

    @FXML
    private Button btnBack;

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
    private TableColumn<InventoryItemDto, String> colInventoryItemDescription;

    @FXML
    private TableColumn<InventoryItemDto, String> colInventoryItemId;

    @FXML
    private TableColumn<InventoryItemDto, String> colInventoryItemName;

    @FXML
    private TableColumn<InventoryItemDto, Integer> colInventoryItemQty;

    @FXML
    private TableColumn<InventoryItemDto, String> colInventoryItemUnit;

    @FXML
    private Label lblInventoryItemDescription;

    @FXML
    private Label lblInventoryItemId;

    @FXML
    private Label lblInventoryItemName;

    @FXML
    private Label lblInventoryItemQty;

    @FXML
    private Label lblInventoryItemUnit;

    @FXML
    private TableView<InventoryItemTM> tblInventoryItem;

    @FXML
    private TextField txtInventoryItemDescription;

    @FXML
    private TextField txtInventoryItemId;

    @FXML
    private TextField txtInventoryItemName;

    @FXML
    private TextField txtInventoryItemQty;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String itemId = txtInventoryItemId.getText();
            boolean isDeleted = inventoryItemBO.deleteInventoryItem(itemId);
            if (isDeleted) {
                refreshTable();
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
        txtInventoryItemId.setText(inventoryItemBO.getNextInventoryItemId());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            InventoryItemDto inventoryItemDto = new InventoryItemDto(
                    txtInventoryItemId.getText(),
                    txtInventoryItemName.getText(),
                    txtInventoryItemDescription.getText(),
                    Integer.parseInt(txtInventoryItemQty.getText()),
                    ChoiseBoxInventoryItemUnit.getValue()
            );
            boolean isSaved = inventoryItemBO.saveInventoryItem(inventoryItemDto);
            if (isSaved) {
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String itemId = txtInventoryItemId.getText();
            InventoryItemDto itemDto = inventoryItemBO.searchInventoryItem(itemId);
            if (itemDto != null) {
                txtInventoryItemName.setText(itemDto.getName());
                txtInventoryItemDescription.setText(itemDto.getDescription());
                txtInventoryItemQty.setText(String.valueOf(itemDto.getQty()));
                ChoiseBoxInventoryItemUnit.setValue(itemDto.getUnit());
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Item not found!", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            InventoryItemDto inventoryItemDto = new InventoryItemDto(
                    txtInventoryItemId.getText(),
                    txtInventoryItemName.getText(),
                    txtInventoryItemDescription.getText(),
                    Integer.parseInt(txtInventoryItemQty.getText()),
                    ChoiseBoxInventoryItemUnit.getValue()
            );
            boolean isUpdated = inventoryItemBO.updateInventoryItem(inventoryItemDto);
            if (isUpdated) {
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        InventoryItemTM selectedItem = tblInventoryItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtInventoryItemId.setText(selectedItem.getId());
            txtInventoryItemName.setText(selectedItem.getName());
            txtInventoryItemDescription.setText(selectedItem.getDescription());
            txtInventoryItemQty.setText(String.valueOf(selectedItem.getQty()));
            ChoiseBoxInventoryItemUnit.setValue(selectedItem.getUnit());
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();
        txtInventoryItemId.clear();
        txtInventoryItemName.clear();
        txtInventoryItemDescription.clear();
        txtInventoryItemQty.clear();
        ChoiseBoxInventoryItemUnit.setValue(null);

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void refreshTable() throws SQLException {
//        ArrayList<InventoryItemDto> inventoryItemDTOS = InventoryItemModel.getAllInventoryItems();
//        ObservableList<InventoryItemTM> inventoryItemTMS = FXCollections.observableArrayList();
//
//        for (InventoryItemDto inventoryItemDTO : InventoryItemDTOS) {
//            inventoryItemTMS.add(new InventoryItemTM(
//                    inventoryItemDTO.getId(),
//                    inventoryItemDTO.getName(),
//                    inventoryItemDTO.getDescription(),
//                    inventoryItemDTO.getQty(),
//                    inventoryItemDTO.getUnit()
//            ));
//        }
        try {
            ArrayList<InventoryItemDto> inventoryItems = inventoryItemBO.getAllInventoryItems();
            ObservableList<InventoryItemTM> inventoryItemTMS = FXCollections.observableArrayList();

            for (InventoryItemDto inventoryItemDTO : inventoryItems) {
                inventoryItemTMS.add(new InventoryItemTM(
                        inventoryItemDTO.getId(),
                        inventoryItemDTO.getName(),
                        inventoryItemDTO.getDescription(),
                        inventoryItemDTO.getQty(),
                        inventoryItemDTO.getUnit()
                ));
            }
            tblInventoryItem.setItems(inventoryItemTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInventoryItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInventoryItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colInventoryItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colInventoryItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colInventoryItemUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        // Populate the unit choices (for example, "kg", "liters", "pcs")
        ChoiseBoxInventoryItemUnit.getItems().addAll("Unit(Pieces)");
        try {
            txtInventoryItemId.setText(inventoryItemBO.getNextInventoryItemId());
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void navigateToSupplier(ActionEvent event) {
        inventoryItemAnchorPane.setVisible(false);
    }
    @FXML
    void btnBackColorChange(MouseEvent event) { btnBack.getStyleClass().add("button-selecthover");}
    @FXML
    void btnBackColorChangeBack(MouseEvent event) {btnBack.getStyleClass().remove("button-selecthover");}


}
