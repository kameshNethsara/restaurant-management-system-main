package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.InventoryItemBO;
import com.ijse.rms.bo.custom.MenuBO;
import com.ijse.rms.bo.custom.impl.InventoryItemBOImpl;
import com.ijse.rms.bo.custom.impl.MenuBOImpl;
import com.ijse.rms.dao.custom.InventoryItemDAO;
import com.ijse.rms.dao.custom.MenuItemDAO;
import com.ijse.rms.dao.custom.MenuItemIngredentDAO;
import com.ijse.rms.dao.custom.impl.InventoryItemDAOImpl;
import com.ijse.rms.dao.custom.impl.MenuItemDAOImpl;
import com.ijse.rms.dao.custom.impl.MenuItemIngredentDAOImpl;
import com.ijse.rms.dto.InventoryItemDto;
import com.ijse.rms.dto.MenuItemDto;
import com.ijse.rms.dto.MenuItemIngredentsDto;
import com.ijse.rms.entity.InventoryItem;
import com.ijse.rms.entity.MenuItemIngredent;
import com.ijse.rms.entity.MenuItems;
import com.ijse.rms.tdm.MenuItemIngredentsTM;
import com.ijse.rms.tdm.MenuItemTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuItemController implements Initializable {

   // MenuItemModel menuItemModel = new MenuItemModel();
    //private final MenuItemIngredentsModel menuItemIngredentsModel = new MenuItemIngredentsModel();

//    MenuItemDAO menuItemDAO = new MenuItemDAOImpl();
//    MenuItemIngredentDAO menuItemIngredentDAO = new MenuItemIngredentDAOImpl();
//    InventoryItemDAO inventoryItemDAO = new InventoryItemDAOImpl();

    MenuBO menuBO = (MenuBO) BOFactory.getInstance().getBO(BOFactory.BOType.MENU);
//    MenuItemIngredentDAO menuItemIngredentDAO;
//    InventoryItemBO inventoryItemBO = new InventoryItemBOImpl();

    @FXML
    private Button btnInventorySearch;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInventoryDelete;

    @FXML
    private Button btnInventoryReset;

    @FXML
    private Button btnInventorySave;

    @FXML
    private Button btnInventoryUpdate;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<MenuItemTM, String> colKitchenSection;

    @FXML
    private TableColumn<MenuItemTM, String> colMenuItemCategory;

    @FXML
    private TableColumn<MenuItemTM, String> colMenuItemDescription;

    @FXML
    private TableColumn<MenuItemTM, String> colMenuItemId;

    @FXML
    private TableColumn<MenuItemTM, String> colMenuItemName;

    @FXML
    private TableColumn<MenuItemTM, String> colMenuItemPrice;

    @FXML
    private TableColumn<MenuItemIngredentsTM, String> colInventoryItemId;

    @FXML
    private TableColumn<MenuItemIngredentsTM, Double> colInventoryRemQty;

    @FXML
    private TableColumn<MenuItemIngredentsTM, String> colInventoryMenuItemId;

    @FXML
    private TableView<MenuItemIngredentsTM> tblInventoryItem;


    @FXML
    private TableView<MenuItemTM> tblMenuItem;

    @FXML
    private Label lblInventoryItemId;

    @FXML
    private Label lblInventoryItemName;

    @FXML
    private Label lblInventoryRemQty;

    @FXML
    private Label lblKitchenSection;

    @FXML
    private Label lblMenuItemCategory;

    @FXML
    private Label lblMenuItemDescription;

    @FXML
    private Label lblMenuItemId;

    @FXML
    private Label lblMenuItemName;

    @FXML
    private Label lblMenuItemPrice;

    @FXML
    private TextField txtInventoryItemId;

    @FXML
    private TextField txtInventoryItemName;

    @FXML
    private TextField txtInventoryRemQty;

    @FXML
    private TextField txtKitchenSection;

    @FXML
    private TextField txtMenuItemCategory;

    @FXML
    private TextField txtMenuItemDescription;

    @FXML
    private TextField txtMenuItemId;

    @FXML
    private TextField txtMenuItemName;

    @FXML
    private TextField txtMenuItemPrice;

    @FXML
    void btnInventoryDeleteOnAction(ActionEvent event) {
        try {
            String ingredientID = txtInventoryItemId.getText();
            String menuItemId = txtMenuItemId.getText();
            if (ingredientID.isEmpty() || menuItemId.isEmpty()) {
                // Handle empty field case (e.g., show error alert)
                new Alert(Alert.AlertType.WARNING, "InventoryItemId or MenuItemId Is Null ", ButtonType.OK).showAndWait();

                return;
            }
            boolean isDeleted = menuBO.deleteMenuItemIngredents(menuItemId,ingredientID);
            if (isDeleted) {
               refreshPage2();
                // Show success alert
                new Alert(Alert.AlertType.WARNING, "Delete Successfully ", ButtonType.OK).showAndWait();

            } else {
                // Show error alert
                new Alert(Alert.AlertType.WARNING, "Delete Unsuccessfully ", ButtonType.OK).showAndWait();

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    @FXML
    void btnInventoryResetOnAction(ActionEvent event) throws SQLException {
        refreshPage2();

    }

    @FXML
    void btnInventorySaveOnAction(ActionEvent event) {
        try {
            // Retrieve inputs
            String ingredientID = txtInventoryItemId.getText();
            String menuItemID = txtMenuItemId.getText();
            String ingredientName = txtInventoryItemName.getText();
            String quantityText = txtInventoryRemQty.getText();

            // Validate inputs
            if (ingredientID.isEmpty() || menuItemID.isEmpty() || ingredientName.isEmpty() || quantityText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields.", ButtonType.OK).showAndWait();
                return;
            }

            double quantity;
            try {
                quantity = Double.parseDouble(quantityText);
                if (quantity <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Quantity must be greater than zero.", ButtonType.OK).showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid quantity. Please enter a valid number.", ButtonType.OK).showAndWait();
                return;
            }

            // Create DTO
            MenuItemIngredentsDto dto = new MenuItemIngredentsDto(
                    ingredientID,
                    menuItemID,
                    quantity
            );

            // Save ingredient
            //boolean isSaved = menuBO.saveMenuItemIngredents(dto);
            boolean isSaved = menuBO.saveMenuItemIngredents(quantity,  menuItemID,   ingredientID);
            if (isSaved) {
                refreshPage2();
                new Alert(Alert.AlertType.INFORMATION, "Menu Item Ingredient saved successfully!", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Menu Item Ingredient.", ButtonType.OK).showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred.", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void btnInventoryUpdateOnAction(ActionEvent event) {
        try {
            // Retrieve inputs
            String ingredientID = txtInventoryItemId.getText();
            String menuItemID = txtMenuItemId.getText();
            String ingredientName = txtInventoryItemName.getText();
            String quantityText = txtInventoryRemQty.getText();

            // Validate inputs
            if (ingredientID.isEmpty() || menuItemID.isEmpty() || ingredientName.isEmpty() || quantityText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill in all fields.", ButtonType.OK).showAndWait();
                return;
            }

            double quantity;
            try {
                quantity = Double.parseDouble(quantityText);
                if (quantity <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Quantity must be greater than zero.", ButtonType.OK).showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid quantity. Please enter a valid number.", ButtonType.OK).showAndWait();
                return;
            }

            // Create DTO
            MenuItemIngredentsDto dto = new MenuItemIngredentsDto(
                    ingredientID,
                    menuItemID,
                    quantity
            );

            // Update ingredient
            //boolean isUpdated = menuBO.updateMenuItemIngredents(dto);
            boolean isUpdated = menuBO.updateMenuItemIngredents(quantity,  menuItemID,   ingredientID);
            if (isUpdated) {
                refreshPage2();
                new Alert(Alert.AlertType.INFORMATION, "Menu Item Ingredient updated successfully!", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Menu Item Ingredient.", ButtonType.OK).showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred.", ButtonType.OK).showAndWait();
        }
    }



//    @FXML
//    void btnInventoryUpdateOnAction(ActionEvent event) {
//        try {
//            String ingredientID = txtInventoryItemId.getText();
//            String menuItemID = txtMenuItemId.getText();
//            String ingredientName = txtInventoryItemName.getText();
//            double quantity = Double.parseDouble(txtInventoryRemQty.getText());
//
//            if (ingredientID.isEmpty() || menuItemID.isEmpty() ||quantity == 0) {
//                // Handle empty fields case (e.g., show error alert)
//                return;
//            }
//
//            MenuItemIngredentsDto dto = new MenuItemIngredentsDto(ingredientID, menuItemID,quantity);
//            boolean isUpdated = menuBO.updateMenuItemIngredents(dto);
//            if (isUpdated) {
//               refreshPage2();
//                new Alert(Alert.AlertType.INFORMATION, "Menu Item Ingredient Updated successfully!", ButtonType.OK).showAndWait();
//
//
//            } else {
//                // Show error alert
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle exceptions
//        }
//    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
        txtMenuItemId.setText(menuBO.getNextMenuItemId());
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
           MenuItemDto menuItemDto = new MenuItemDto(
                    txtMenuItemId.getText(),
                    txtMenuItemName.getText(),
                    txtMenuItemDescription.getText(),
                    Double.parseDouble(txtMenuItemPrice.getText()),
                    //Integer.parseInt(txtMenuItemQty.getText()),
                    txtMenuItemCategory.getText(),
                    txtKitchenSection.getText()

            );
            boolean isSaved = menuBO.saveMenuItem(menuItemDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String itemId = txtMenuItemId.getText();
            MenuItemDto itemDto = menuBO.searchMenuItem(itemId);
            if (itemDto != null) {
                txtMenuItemName.setText(itemDto.getName());
                txtMenuItemDescription.setText(itemDto.getDescription());
                txtMenuItemCategory.setText(itemDto.getCategory());
                txtKitchenSection.setText(itemDto.getKitchenSection());
                //txtMenuItemQty.setText(String.valueOf(itemDto.getQty()));
                txtMenuItemPrice.setText(String.valueOf(itemDto.getPrice()));
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            MenuItemDto menuItemDto = new MenuItemDto(
                    txtMenuItemId.getText(),
                    txtMenuItemName.getText(),
                    txtMenuItemDescription.getText(),
                    Double.parseDouble(txtMenuItemPrice.getText()),
                    //Integer.parseInt(txtMenuItemQty.getText()),
                    txtMenuItemCategory.getText(),
                    txtKitchenSection.getText()
            );
            boolean isUpdated = menuBO.updateMenuItem(menuItemDto);
            if (isUpdated) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String itemId = txtMenuItemId.getText();
            boolean isDeleted = menuBO.deleteMenuItem(itemId);
            if (isDeleted) {
                refreshTable();
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMenuItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMenuItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMenuItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMenuItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colKitchenSection.setCellValueFactory(new PropertyValueFactory<>("kitchenSection"));
        colMenuItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            refreshPage2();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colInventoryMenuItemId.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));
        colInventoryItemId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colInventoryRemQty.setCellValueFactory(new PropertyValueFactory<>("qtyNeeded"));

        try {
            txtMenuItemId.setText(menuBO.getNextMenuItemId());
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSave.setDisable(false);
        btnInventorySave.setDisable(false);
        btnUpdate.setDisable(true);
        btnInventoryUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnInventoryDelete.setDisable(true);
    }

    @FXML
    void btnInventorySearchOnAction(ActionEvent event) {

        try {
            String inventoryItemName = txtInventoryItemName.getText();
           // InventoryItemDto inventoryItemDto = menuBO.searchInventoryItemName(inventoryItemName);
          //InventoryItemDAO inventoryItemDAO= new InventoryItemDAOImpl();
          InventoryItemDto inventoryItemDto = menuBO.searchInventoryItemName(inventoryItemName);
           // InventoryItem inventoryItemDto = inventoryItemDAO.searchInventoryItemName(inventoryItemName);


            if (inventoryItemDto != null) {

                System.out.println("Found Inventory Item - ID: " + inventoryItemDto.getId() +
                        ", Name: " + inventoryItemDto.getName());

                txtInventoryItemName.setText(inventoryItemDto.getName());
                txtInventoryItemId.setText(inventoryItemDto.getId());
                //txtInventoryRemQty.setText(String.valueOf(inventoryItemDto.getQty()));

            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onClickTable(MouseEvent event) {
        MenuItemTM selectedItem = tblMenuItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtMenuItemId.setText(selectedItem.getId());
            txtMenuItemName.setText(selectedItem.getName());
            txtMenuItemDescription.setText(selectedItem.getDescription());
            txtMenuItemCategory.setText(selectedItem.getCategory());
            txtKitchenSection.setText(selectedItem.getKitchenSection());
            //txtMenuItemQty.setText(String.valueOf(selectedItem.getQty()));
            txtMenuItemPrice.setText(String.valueOf(selectedItem.getPrice()));
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
    @FXML
    void onClickTableIngredent(MouseEvent event) {
        MenuItemIngredentsTM selectedItem = tblInventoryItem.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtInventoryItemId.setText(selectedItem.getInventoryId());
            txtMenuItemId.setText(selectedItem.getMenuItemId());
            txtInventoryRemQty.setText(String.valueOf(selectedItem.getQtyNeeded()));
            txtInventoryItemName.setText(selectedItem.getInventoryName());

            btnInventoryUpdate.setDisable(false);
            btnInventoryDelete.setDisable(false);
        }
    }
    private void refreshPage() throws SQLException {
        refreshTable();
        txtMenuItemId.clear();
        txtMenuItemName.clear();
        txtMenuItemDescription.clear();
        txtMenuItemCategory.clear();
        txtKitchenSection.clear();
        //    txtMenuItemQty.clear();
        txtMenuItemPrice.clear();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void refreshPage2() throws SQLException {
        refreshTable2();
        txtInventoryItemName.clear();
        txtInventoryItemId.clear();
        txtInventoryRemQty.clear();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void refreshTable() {
        try {
            ArrayList<MenuItemDto> menuItemDTOS = menuBO.getAllMenuItems();
            ObservableList<MenuItemTM> menuItemTMS = FXCollections.observableArrayList();
            for (MenuItemDto menuItemDTO : menuItemDTOS) {
                menuItemTMS.add(new MenuItemTM(
                        menuItemDTO.getId(),
                        menuItemDTO.getName(),
                        menuItemDTO.getDescription(),
                        menuItemDTO.getPrice(),
                        // menuItemDTO.getQty(),
                        menuItemDTO.getCategory(),
                        menuItemDTO.getKitchenSection()
                ));
            }
            tblMenuItem.setItems(menuItemTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTable2() {
        try {
            ArrayList<MenuItemIngredent> menuItems = menuBO.getAllMenuItemIngredents();
            ObservableList<MenuItemIngredentsTM> menuItemTMS = FXCollections.observableArrayList();
            for (MenuItemIngredent menuItemDTO : menuItems) {
                menuItemTMS.add(new MenuItemIngredentsTM(
                        menuItemDTO.getMenuItemId(),
                        menuItemDTO.getInventoryId(),
                        menuItemDTO.getQtyNeeded(),
                        menuItemDTO.getInventoryName()

                ));
            }
            tblInventoryItem.setItems(menuItemTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

