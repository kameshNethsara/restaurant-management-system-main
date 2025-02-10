package com.ijse.rms.controller;

import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.bo.custom.impl.CustomerBOImpl;
import com.ijse.rms.bo.custom.impl.UserBOImpl;
import com.ijse.rms.dto.CustomerViewDto;
import com.ijse.rms.entity.Customer;
import com.ijse.rms.tdm.CustomerTM;
//import com.ijse.rms.model.CustomerViewModel;
//import com.ijse.rms.model.UserViewModel;
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

public class CustomerViewController implements Initializable {

   // CustomerViewModel customerModel = new CustomerViewModel();
   // CustomerDAO customerDAO = new CustomerDAOImpl();
    CustomerBO customerBO = new CustomerBOImpl();
    UserBO userBO = new UserBOImpl();

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerAddress;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerEmail;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerName;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerPhone;

    @FXML
    private TableColumn<CustomerTM, String> colUserId;

    @FXML
    private TableView<CustomerTM> tblCustomer;


    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerPhone;

    @FXML
    private TextField txtUserId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));

        try {
            txtCustomerId.setText(getNextCustomerID());
            txtUserId.setText(getNextUserID());
            refreshTable();
        } catch (Exception e) { //-----------------------//
          throw new RuntimeException(e);
        }

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

//    @FXML
//    void btnSaveOnAction(ActionEvent event) {
//        CustomerViewDto customer = new CustomerViewDto(
//                txtCustomerId.getText(),
//                txtCustomerName.getText(),
//                txtCustomerPhone.getText(),
//                txtCustomerEmail.getText(),
//                txtCustomerAddress.getText(),
//                txtUserId.getText()
//        );
//
//        try {
//            boolean isSaved = customerModel.saveCustomer(customer);
//            if (isSaved) {
//                refreshPage();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    ///////////////////////////////////////////////////
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Validation for Customer ID (numeric and positive integer)
        String customerId = txtCustomerId.getText();
        if (!isValidCustomerId(customerId)) {
            showAlert("Invalid Customer ID", "Customer ID should be a valid positive number.");
            return;
        }

        // Validation for Customer Name (should not be empty and only alphabetic)
        String customerName = txtCustomerName.getText();
        if (!isValidName(customerName)) {
            showAlert("Invalid Name", "Customer Name should contain only alphabets and spaces.");
            return;
        }

        // Validation for Phone (should follow a specific phone format)
        String phone = txtCustomerPhone.getText();
        if (!isValidPhoneNumber(phone)) {
            showAlert("Invalid Phone Number", "Phone Number should be in the format: 077XXXXXX.");
            return;
        }

        // Validation for Email (basic email format)
        String email = txtCustomerEmail.getText();
        if (!isValidEmail(email)) {
            showAlert("Invalid Email", "Email should be in a valid format: example@domain.com.");
            return;
        }

        // Validation for Address (ensure it's not empty)
        String address = txtCustomerAddress.getText();
        if (address.trim().isEmpty()) {
            showAlert("Invalid Address", "Address should not be empty.");
            return;
        }

        // Validation for User ID (should be numeric and positive)
        String userId = txtUserId.getText();
        if (!isValidUserId(userId)) {
            showAlert("Invalid User ID", "User ID should be a valid positive number.");
            return;
        }

        // If all validations pass, create CustomerViewDto object and save it
        CustomerViewDto customerDto = new CustomerViewDto(
                customerId, customerName, phone, email, address, userId
        );

        try {
            boolean isSaved = customerBO.saveCustomer(customerDto);
            if (isSaved) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Regex methods for validation
    private boolean isValidCustomerId(String customerId) {
        return true; // Ensures customer ID is a positive number

    }

    private boolean isValidName(String name) {
        return name.matches("[A-Za-z ]+"); // Only alphabetic characters and spaces allowed
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^(07[01245678])[0-9]{7}$"); // Format xxx-xxx-xxxx
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"); // Basic email validation
    }

    private boolean isValidUserId(String userId) {
        return true; // Ensures user ID is a positive number
    }

    // Method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    ///////////////////////////////////////////////////

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerViewDto customerDto = new CustomerViewDto(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerPhone.getText(),
                txtCustomerEmail.getText(),
                txtCustomerAddress.getText(),
                txtUserId.getText()
        );

        try {
            boolean isUpdated = customerBO.updateCustomer(customerDto);
            if (isUpdated) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String customerId = txtCustomerId.getText();
            CustomerViewDto CustomerDto = customerBO.searchCustomer(customerId);

            if (CustomerDto != null) {
                txtCustomerName.setText(CustomerDto.getName());
                txtCustomerPhone.setText(CustomerDto.getPhone());
                txtCustomerEmail.setText(CustomerDto.getEmail());
                txtCustomerAddress.setText(CustomerDto.getAddress());
                txtUserId.setText(CustomerDto.getUserId());
            } else {
                // Show an alert if the customer is not found
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Customer Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No customer found with ID: " + customerId);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optional: Show an alert for unexpected errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while searching for the customer");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = customerBO.deleteCustomer(txtCustomerId.getText());
            if (isDeleted) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        refreshPage();
    }

    private void refreshPage() {
        refreshTable();
        txtCustomerId.setText(getNextCustomerID());
        txtCustomerName.clear();
        txtCustomerPhone.clear();
        txtCustomerEmail.clear();
        txtCustomerAddress.clear();
        txtUserId.setText(getNextUserID());
        //--passe meka data base eken logwena userge id eka karanna ona--//

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private String getNextCustomerID() {
        try {
            return customerBO.getNextCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getNextUserID() {
        try {
            return userBO.getNextUserId();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void refreshTable() {
        try {
            ArrayList<CustomerViewDto> customerDtos = customerBO.getAllCustomer();
            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

            for (CustomerViewDto CustomerDto : customerDtos) {
                customerTMS.add(new CustomerTM(
                        CustomerDto.getId(),
                        CustomerDto.getName(),
                        CustomerDto.getPhone(),
                        CustomerDto.getEmail(),
                        CustomerDto.getAddress(),
                        CustomerDto.getUserId()
                ));
            }
            tblCustomer.setItems(customerTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            txtCustomerId.setText(selectedCustomer.getId());
            txtCustomerName.setText(selectedCustomer.getName());
            txtCustomerPhone.setText(selectedCustomer.getPhone());
            txtCustomerEmail.setText(selectedCustomer.getEmail());
            txtCustomerAddress.setText(selectedCustomer.getAddress());
            txtUserId.setText(selectedCustomer.getUserId());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
