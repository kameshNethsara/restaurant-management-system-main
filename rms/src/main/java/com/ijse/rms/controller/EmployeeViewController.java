package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.EmployeeBO;
import com.ijse.rms.bo.custom.impl.EmployeeBOImpl;
import com.ijse.rms.dto.EmployeeViewDto;
import com.ijse.rms.entity.Employee;
import com.ijse.rms.tdm.EmployeeTM;
//import com.ijse.rms.model.EmployeeViewModel;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Date; // Import java.sql.Date
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeViewController implements Initializable {

    //EmployeeViewModel employeeModel = new EmployeeViewModel();
    //EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);


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
    private TableColumn<EmployeeTM, String> colEmployeeEmail;

    @FXML
    private TableColumn<EmployeeTM, Date> colEmployeeHireDate; // Change to Date

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeName;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeePhone;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeePosition;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private AnchorPane employeeAccountsView;

    @FXML
    private Label lblEmployeeEmail;

    @FXML
    private Label lblEmployeeHireDate;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblEmployeePhone;

    @FXML
    private Label lblEmployeePosition;

    @FXML
    private TextField txtEmployeeEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeePhone;

    @FXML
    private TextField txtEmployeePosition;

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String employeeId = txtEmployeeId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this employee?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        // Check if the user clicked on the YES button
        if (buttonType.isPresent() && buttonType.get() == ButtonType.YES) {
            boolean isDeleted = employeeBO.deleteEmployee(employeeId);

            // Show appropriate message based on deletion success
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();
                refreshPage(); // Refresh the page after successful deletion
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete employee!").show();
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String employeeId = txtEmployeeId.getText(); // Retrieve employee ID from TextField
            EmployeeViewDto employeeDto = employeeBO.searchEmployee(employeeId);

            if (employeeDto != null) {
                // Set employee details to text fields
                txtEmployeeName.setText(employeeDto.getName());
                txtEmployeePosition.setText(employeeDto.getPosition());
                txtEmployeePhone.setText(employeeDto.getPhone());
                txtEmployeeEmail.setText(employeeDto.getEmail());
                lblEmployeeHireDate.setText(String.valueOf(employeeDto.getHireDate()));
            } else {
                // Show an alert if the employee is not found
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Employee Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No employee found with ID: " + employeeId);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Optional: Show an alert for unexpected errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while searching for the employee");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }


    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String phone = txtEmployeePhone.getText();
        String position = txtEmployeePosition.getText();
        String email = txtEmployeeEmail.getText();
        String hireDateStr = lblEmployeeHireDate.getText(); // Changed variable name for clarity

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)|((\\d+\\.)(\\d){2})$"; // Adjusted phone pattern
        String positionPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidPosition = position.matches(positionPattern);
        boolean isValidEmail = email.matches(emailPattern);

        // Resetting styles for borders
        resetBorderStyles();

        if (!isValidName) {
            txtEmployeeName.setStyle(txtEmployeeName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtEmployeePhone.setStyle(txtEmployeePhone.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPosition) {
            txtEmployeePosition.setStyle(txtEmployeePosition.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtEmployeeEmail.setStyle(txtEmployeeEmail.getStyle() + ";-fx-border-color: red;");
        }
        if (isValidName && isValidPhone && isValidPosition && isValidEmail) {
            Date hireDate = parseDate(hireDateStr); // Use java.sql.Date
            EmployeeViewDto employeeDto = new EmployeeViewDto(
                    id.trim(),
                    name.trim(),
                    position.trim(),
                    email.trim(),
                    phone.trim(),
                    hireDate
            );
            boolean isSaved = employeeBO.saveEmployee(employeeDto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save employee...!").show();
            }
        }
    }

    private Date parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return Date.valueOf(localDate); // Convert LocalDate to java.sql.Date
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String id = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String phone = txtEmployeePhone.getText();
        String position = txtEmployeePosition.getText();
        String email = txtEmployeeEmail.getText();
        String hireDateStr = lblEmployeeHireDate.getText(); // Changed variable name for clarity

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)|((\\d+\\.)(\\d){2})$"; // Adjusted phone pattern
        String positionPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidPosition = position.matches(positionPattern);
        boolean isValidEmail = email.matches(emailPattern);

        // Resetting styles for borders
        resetBorderStyles();

        if (!isValidName) {
            txtEmployeeName.setStyle(txtEmployeeName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtEmployeePhone.setStyle(txtEmployeePhone.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPosition) {
            txtEmployeePosition.setStyle(txtEmployeePosition.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtEmployeeEmail.setStyle(txtEmployeeEmail.getStyle() + ";-fx-border-color: red;");
        }
        if (isValidName && isValidPhone && isValidPosition && isValidEmail) {
            Date hireDate = parseDate(hireDateStr); // Use java.sql.Date
            EmployeeViewDto employeeDto = new EmployeeViewDto(
                    id.trim(),
                    name.trim(),
                    position.trim(),
                    email.trim(),
                    phone.trim(),
                    hireDate
            );
            boolean isUpdate = employeeBO.updateEmployee(employeeDto);
            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Employee updated...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update employee...!").show();
            }
        }
    }

    private void resetBorderStyles() {
        // Reset border styles to default
        txtEmployeeName.setStyle(txtEmployeeName.getStyle() + ";-fx-border-color: #0578;");
        txtEmployeePhone.setStyle(txtEmployeePhone.getStyle() + ";-fx-border-color: #0578;");
        txtEmployeePosition.setStyle(txtEmployeePosition.getStyle() + ";-fx-border-color: #0578;");
        txtEmployeeEmail.setStyle(txtEmployeeEmail.getStyle() + ";-fx-border-color: #0578;");
    }

    private void showDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Timeline dateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDate today = LocalDate.now(); // Get current date
            lblEmployeeHireDate.setText(today.format(dateFormatter)); // Set it to the date label
        }));

        dateTimeline.setCycleCount(Animation.INDEFINITE); // Keep running indefinitely
        dateTimeline.play(); // Start the timeline for the date
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeeHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        //showDate();

        try {
            String nextEmployeeId = employeeBO.getNextEmployeeId();
            System.out.println(nextEmployeeId);
            txtEmployeeId.setText(nextEmployeeId);
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        showDate();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        EmployeeTM selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtEmployeeId.setText(selectedItem.getId());
            txtEmployeeName.setText(selectedItem.getName());
            txtEmployeePosition.setText(selectedItem.getPosition());
            txtEmployeePhone.setText(selectedItem.getPhone());
            txtEmployeeEmail.setText(selectedItem.getEmail());
            lblEmployeeHireDate.setText(selectedItem.getHireDate().toString()); // Updated to display the date

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    private void refreshPage() throws SQLException {
        refreshTable();

        String nextEmployeeId = employeeBO.getNextEmployeeId();
        System.out.println(nextEmployeeId);
        txtEmployeeId.setText(nextEmployeeId);

        txtEmployeeName.setText("");
        txtEmployeePosition.setText("");
        txtEmployeePhone.setText("");
        txtEmployeeEmail.setText("");

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void refreshTable() throws SQLException {
        ArrayList<EmployeeViewDto> employees = employeeBO.getAllEmployee();
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeViewDto employeeDto : employees) {
            //EmployeeTM employeeTM = new EmployeeTM();

            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDto.getId(),
                    employeeDto.getName(),
                    employeeDto.getPosition(),
                    employeeDto.getPhone(),
                    employeeDto.getEmail(),
                    employeeDto.getHireDate()
            );

            employeeTMS.add(employeeTM);
        }
        tblEmployee.setItems(employeeTMS);
    }

//    @FXML
//    private TableColumn<EmployeeTM, String> colEmployeeEmail;
//
//    @FXML
//    private TableColumn<EmployeeTM, Date> colEmployeeHireDate;
//
//    @FXML
//    private TableColumn<EmployeeTM, String> colEmployeeId;
//
//    @FXML
//    private TableColumn<EmployeeTM, String> colEmployeeName;
//
//    @FXML
//    private TableColumn<EmployeeTM, String> colEmployeePhone;
//
//    @FXML
//    private TableColumn<EmployeeTM, String> colEmployeePosition;
//
//    @FXML
//    private TableView<EmployeeTM> tblEmployee;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        colEmployeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
//        colEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        colEmployeeHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
//
//        try{
//            refreshPage();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private void refreshPage() throws SQLException {
//        refreshTable();
//
//    }
//
//    private void refreshTable() throws SQLException {
//        ArrayList<EmployeeViewDto> employeeDTOS = employeeModel.getAllEmployee();
//        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();
//
//        for (EmployeeViewDto employeeDTO : employeeDTOS) {
//            //EmployeeTM employeeTM = new EmployeeTM();
//
//            EmployeeTM employeeTM = new EmployeeTM(
//                    employeeDTO.getId(),
//                    employeeDTO.getName(),
//                    employeeDTO.getPosition(),
//                    employeeDTO.getPhone(),
//                    employeeDTO.getEmail(),
//                    employeeDTO.getHireDate()
//            );
//            employeeTMS.add(employeeTM);
//        }
//        tblEmployee.setItems(employeeTMS);
//    }

}

