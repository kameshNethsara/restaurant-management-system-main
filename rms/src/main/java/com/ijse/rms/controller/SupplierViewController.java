package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.SupplierBO;
import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.bo.custom.impl.SupplierBOImpl;
import com.ijse.rms.bo.custom.impl.UserBOImpl;
import com.ijse.rms.dao.custom.SupplierDAO;
import com.ijse.rms.dto.SupplierViewDto;
import com.ijse.rms.entity.Supplier;
import com.ijse.rms.tdm.SupplierTM;
//import com.ijse.rms.model.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SupplierViewController implements Initializable {

    //private SupplierViewModel supplierModel = new SupplierViewModel();

    UserBO userBO =(UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USERS);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    private static final String SUPPLIER_NOT_FOUND_MSG = "No supplier found with ID: ";
    private static final String ERROR_TITLE = "Error";
    private static final String WARNING_TITLE = "Supplier Not Found";

    @FXML
    private AnchorPane SupplierAccountsView;

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
    private Button btnInventoryItem;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierAddress;
    @FXML
    private TableColumn<SupplierTM, String> colSupplierEmail;
    @FXML
    private TableColumn<SupplierTM, String> colSupplierId;
    @FXML
    private TableColumn<SupplierTM, String> colSupplierName;
    @FXML
    private TableColumn<SupplierTM, String> colSupplierPhone;
    @FXML
    private TableColumn<SupplierTM, String> colSupplierContactInfo;
    @FXML
    private TableColumn<SupplierTM, String> colUserId;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtSupplierAddress;
    @FXML
    private TextField txtSupplierEmail;
    @FXML
    private TextField txtSupplierId;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtSupplierPhone;
    @FXML
    private TextField txtUserId;
    @FXML
    private TextField txtContactInfo;

    @FXML
    void btnInventoryItemColorChange(MouseEvent event) { btnInventoryItem.getStyleClass().add("button-selecthover");}
    @FXML
    void btnInventoryItemColorChangeBack(MouseEvent event) {btnInventoryItem.getStyleClass().remove("button-selecthover");}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo")); // Corrected here
        colSupplierPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        try {
            txtSupplierId.setText(getNextSupplierID());
            txtUserId.setText(getNextUserID());
            refreshTable();
        } catch (Exception e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        SupplierViewDto supplierDto = new SupplierViewDto(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtContactInfo.getText(),
                txtSupplierPhone.getText(),
                txtSupplierEmail.getText(),
                txtSupplierAddress.getText(),
                txtUserId.getText()
        );

        try {
            if (supplierBO.saveSupplier(supplierDto)) {
                refreshPage();
            } else {
                showErrorDialog("Save Failed", "Could not save supplier.");
            }
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierViewDto supplierDto = new SupplierViewDto(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtContactInfo.getText(),
                txtSupplierPhone.getText(),
                txtSupplierEmail.getText(),
                txtSupplierAddress.getText(),
                txtUserId.getText()
        );

        try {
            if (supplierBO.updateSupplier(supplierDto)) {
                refreshPage();
            } else {
                showErrorDialog("Update Failed", "Could not update supplier.");
            }
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String supplierId = txtSupplierId.getText();
            SupplierViewDto supplierDto = supplierBO.searchSupplier(supplierId);

            if (supplierDto != null) {
                txtSupplierName.setText(supplierDto.getName());
                txtContactInfo.setText(supplierDto.getContactInfo());
                txtSupplierPhone.setText(supplierDto.getPhone());
                txtSupplierEmail.setText(supplierDto.getEmail());
                txtSupplierAddress.setText(supplierDto.getAddress());
                txtUserId.setText(supplierDto.getUserId());
            } else {
                showWarningDialog(WARNING_TITLE, SUPPLIER_NOT_FOUND_MSG + supplierId);
            }
        } catch (Exception e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if (supplierBO.deleteSupplier(txtSupplierId.getText())) {
                refreshPage();
            } else {
                showErrorDialog("Delete Failed", "Could not delete supplier.");
            }
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        refreshPage();
    }

    private void refreshPage() {
        refreshTable();
        txtSupplierId.setText(getNextSupplierID());
        txtSupplierName.clear();
        txtContactInfo.clear();
        txtSupplierPhone.clear();
        txtSupplierEmail.clear();
        txtSupplierAddress.clear();
        txtUserId.setText(getNextUserID());

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private String getNextSupplierID() {
        try {
            return supplierBO.getNextSupplierId();
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
            return "";
        }
    }

    private String getNextUserID() {
        try {
            return userBO.getNextUserId();
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
            return "";
        }
    }

    private void refreshTable() {
        try {
            ArrayList<SupplierViewDto> suppliers = supplierBO.getAllSuppliers();
            ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

            for (SupplierViewDto supplierDto : suppliers) {
                supplierTMS.add(new SupplierTM(
                        supplierDto.getId(),
                        supplierDto.getName(),
                        supplierDto.getContactInfo(),
                        supplierDto.getPhone(),
                        supplierDto.getEmail(),
                        supplierDto.getAddress(),
                        supplierDto.getUserId()
                ));
            }
            tblSupplier.setItems(supplierTMS);
        } catch (SQLException e) {
            showErrorDialog(ERROR_TITLE, e.getMessage());
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        SupplierTM selectedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            txtSupplierId.setText(selectedSupplier.getId());
            txtSupplierName.setText(selectedSupplier.getName());
            txtContactInfo.setText(selectedSupplier.getContactInfo());
            txtSupplierPhone.setText(selectedSupplier.getPhone());
            txtSupplierEmail.setText(selectedSupplier.getEmail());
            txtSupplierAddress.setText(selectedSupplier.getAddress());
            txtUserId.setText(selectedSupplier.getUserId());

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(title);
        alert.showAndWait(); // This will block until the user closes the alert
    }

    private void showWarningDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.setTitle(title);
        alert.showAndWait(); // This will also block until the user closes the alert
    }

//    @FXML
//    void navigateToInventoryItems(ActionEvent event) {
//        try {
//            SupplierAccountsView.getChildren().clear();
//            Parent inventoryItemAnchorPane = FXMLLoader.load(getClass().getResource("/view/InventoryItemView.fxml"));
//           // btnSearch.setVisible(false);
//            SupplierAccountsView.getChildren().add(inventoryItemAnchorPane);
//        } catch (IOException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
//        }
//    }
    @FXML
    void navigateToInventoryItems(ActionEvent event) throws IOException {
        SupplierAccountsView.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/InventoryItemView.fxml"))));
    }


}
