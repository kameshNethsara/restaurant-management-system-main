package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.ReservationBO;
import com.ijse.rms.bo.custom.TableBO;
import com.ijse.rms.bo.custom.impl.CustomerBOImpl;
import com.ijse.rms.bo.custom.impl.ReservationBOImpl;
import com.ijse.rms.bo.custom.impl.TableBOImpl;
import com.ijse.rms.dao.custom.QuaryDAO;
import com.ijse.rms.dao.custom.TableAssignmentDAO;
import com.ijse.rms.dao.custom.impl.QuaryDAOImpl;
import com.ijse.rms.dao.custom.impl.TableAssignmentDAOImpl;
import com.ijse.rms.dto.*;
import com.ijse.rms.entity.*;
import com.ijse.rms.tdm.ReservationTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    private final ObservableList<ReservationTM> reservations = FXCollections.observableArrayList();
    //CustomerDAO customerDAO = new CustomerDAOImpl();
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    TableBO tableBO = (TableBO) BOFactory.getInstance().getBO(BOFactory.BOType.TABLE);
    ReservationBO reservationBO =(ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATIONS);
    QuaryDAO quaryDAO = new QuaryDAOImpl();
    TableAssignmentDAO tableAssignmentDAO = new TableAssignmentDAOImpl();

    @FXML
    private Button btnAddResavation;

    @FXML
    private Button btnRemoveReservation;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnCustomerSearch;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnResavationSearch;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnTableSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private ChoiceBox<String> choiseBoxResavationStatus;

    @FXML
    private ChoiceBox<String> choiseBoxTableStatus;

    @FXML
    private TableColumn<ReservationTM, LocalDate> colResavationDate;

    @FXML
    private TableColumn<ReservationTM, String> colResavationId;

    @FXML
    private TableColumn<ReservationTM, Integer> colResavationNumberOfGuest;

    @FXML
    private TableColumn<ReservationTM, String> colResavationSpecialRequest;

    @FXML
    private TableColumn<ReservationTM, Integer> colTableCapacity;

    @FXML
    private TableColumn<ReservationTM, String> colTableId;

    @FXML
    private TableColumn<ReservationTM, String> colTableLocation;

    @FXML
    private TableColumn<ReservationTM, String> colTableStatus;

    @FXML
    private DatePicker dpDatePicker;

    @FXML
    private Label lblAssignmentId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerTele;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDatePicker;

    @FXML
    private Label lblTimePicker;

    @FXML
    private Label lblDateShow;

    @FXML
    private Label lblNumberOfGuest;

    @FXML
    private Label lblResavationId;

    @FXML
    private Label lblResavationStatus;

    @FXML
    private Label lblSpecialRequest;

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
    private AnchorPane resavationAnchorePane;

    @FXML
    private TableView<ReservationTM> tblMenuItem;

    @FXML
    private TextField txtTimePicker;

    @FXML
    private Label txtAssignmentId;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerTele;

    @FXML
    private TextField txtNumberOfGuest;

    @FXML
    private TextField txtResavationId;

    @FXML
    private TextField txtSpecialRequest;

    @FXML
    private Label txtTableCapacity;

    @FXML
    private TextField txtTableId;

    @FXML
    private Label txtTableLocation;

    @FXML
    private Label txtTableNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDelete.setVisible(false);
        showDate();
        initialize();
        btnAddResavation.setVisible(false);
        btnRemoveReservation.setVisible(false);

        try {
            txtResavationId.setText(getNextReservationId());
            txtAssignmentId.setText(getNextAssignmentId());
            refreshPage();
        } catch (Exception e) { //-----------------------//
            throw new RuntimeException(e);
        }

        //btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

    }
    public void initialize() {
        colResavationId.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colResavationNumberOfGuest.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        colResavationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colResavationSpecialRequest.setCellValueFactory(new PropertyValueFactory<>("specialRequests"));
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colTableStatus.setCellValueFactory(new PropertyValueFactory<>("tableStatus"));
        colTableCapacity.setCellValueFactory(new PropertyValueFactory<>("tableCapacity"));
        colTableLocation.setCellValueFactory(new PropertyValueFactory<>("tableLocation"));

    }
    private String getNextAssignmentId() {
        try {
            return tableAssignmentDAO.getNextId();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    private String getNextReservationId() {
        try {
            return reservationBO.getNextReservationID();
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @FXML
    void btnAddResavationOnAction(ActionEvent event) throws SQLException {
        try {
            // Validate inputs
            if (txtResavationId.getText().isEmpty() || txtAssignmentId.getText().isEmpty() ||
                    txtNumberOfGuest.getText().isEmpty() || txtTableId.getText().isEmpty() ||
                    txtCustomerId.getText().isEmpty() || choiseBoxResavationStatus.getValue() == null ||
                    dpDatePicker.getValue() == null || txtTimePicker.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all required fields.").show();
                return;
            }

            // Validate number of guests
            int numberOfGuests;
            try {
                numberOfGuests = Integer.parseInt(txtNumberOfGuest.getText());
                if (numberOfGuests <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid positive number for guests.").show();
                return;
            }

            // Validate date and time
            LocalDateTime reservationDateTime = dateAndTime();
            if (reservationDateTime == null) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid date and time.").show();
                return;
            }

            // Collect data for reservation
            String reservationId = txtResavationId.getText();
            String numberGuests = txtNumberOfGuest.getText();
            LocalDate reservationDate = LocalDate.parse(lblDateShow.getText());
            String specialRequests = txtSpecialRequest.getText();
            String tableId = txtTableId.getText();
            String reservationStatus = choiseBoxResavationStatus.getValue();
            String tableCapacity = txtTableCapacity.getText();
            String tableLocation = txtTableLocation.getText();

           ReservationTM newCartBook = new ReservationTM(reservationId,numberGuests,reservationDate, specialRequests,tableId,reservationStatus,tableCapacity,tableLocation);

//
//            // Create DTO objects
//            ReservationDto reservationDto = new ReservationDto(
//                    reservationId,
//                    customerId,
//                    reservationDateTime.toLocalDate(),
//                    numberOfGuests,
//                    specialRequests,
//                    reservationStatus
//            );
//
//            TableAssignmentDto assignmentDto = new TableAssignmentDto(
//                    assignmentId,
//                    tableId,
//                    reservationId,
//                    reservationDateTime
//            );
//
//            // Save reservation using ReservationModel
//            boolean isSaved = reservationModel.saveReservation(reservationDto, assignmentDto);
//
//            if (isSaved) {
//                new Alert(Alert.AlertType.INFORMATION, "Reservation added successfully!").show();
//                refreshPage(); // Clear form fields and refresh
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Failed to add reservation!").show();
//            }
            //ReservationTM newCartBook = new ReservationTM();
            reservations.add(newCartBook);
            tblMenuItem.setItems(reservations);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
        refreshPage();
    }

//    @FXML
//    void btnAddResavationOnAction(ActionEvent event) throws SQLException {
//        try {
//            // Validate inputs
//            if (txtResavationId.getText().isEmpty() || txtAssignmentId.getText().isEmpty() ||
//                    txtNumberOfGuest.getText().isEmpty() || txtTableId.getText().isEmpty() ||
//                    txtCustomerId.getText().isEmpty() || choiseBoxResavationStatus.getValue() == null ||
//                    dpDatePicker.getValue() == null || txtTimePicker.getText().isEmpty()) {
//                new Alert(Alert.AlertType.WARNING, "Please fill in all required fields.").show();
//                return;
//            }
//
//            // Validate number of guests
//            int numberOfGuests;
//            try {
//                numberOfGuests = Integer.parseInt(txtNumberOfGuest.getText());
//                if (numberOfGuests <= 0) {
//                    throw new NumberFormatException();
//                }
//            } catch (NumberFormatException e) {
//                new Alert(Alert.AlertType.ERROR, "Please enter a valid positive number for guests.").show();
//                return;
//            }
//
//            // Validate date and time
//            LocalDateTime reservationDateTime = dateAndTime(); // Assuming you have a method to convert date and time
//            if (reservationDateTime == null) {
//                new Alert(Alert.AlertType.ERROR, "Please enter a valid date and time.").show();
//                return;
//            }
//
//            // Collect data for reservation
//            String reservationId = txtResavationId.getText();
//            String numberGuests = txtNumberOfGuest.getText();
//            LocalDate reservationDate = dpDatePicker.getValue(); // Using date picker for the reservation date
//            String specialRequests = txtSpecialRequest.getText();
//            String tableId = txtTableId.getText();
//            String reservationStatus = choiseBoxResavationStatus.getValue();
//            String tableCapacity = txtTableCapacity.getText();
//            String tableLocation = txtTableLocation.getText();
//
//            // Create a new ReservationTM object for the reservation
//            ReservationTM newReservation = new ReservationTM(
//                    reservationId, numberGuests, reservationDate, specialRequests,
//                    tableId, reservationStatus, tableCapacity, tableLocation
//            );
//
//            // Add to the list and update the table
//            reservations.add(newReservation);
//            tblMenuItem.setItems(FXCollections.observableArrayList(reservations));
//
//            // Optionally, you can create DTO objects for database operations if needed
//            // Example DTO for Reservation
//            ReservationDto reservationDto = new ReservationDto(
//                    reservationId,
//                    txtCustomerId.getText(),
//                    reservationDateTime.toLocalDate(),
//                    numberOfGuests,
//                    specialRequests,
//                    reservationStatus
//            );
//
//            // Create an assignment DTO if needed
//            TableAssignmentDto assignmentDto = new TableAssignmentDto(
//                    txtAssignmentId.getText(),
//                    tableId,
//                    reservationId,
//                    reservationDateTime
//            );
//
//            // Save reservation to the database using the model (you should implement this)
//            // reservationModel.saveReservation(reservationDto, assignmentDto);
//
//            // If successfully saved
//            new Alert(Alert.AlertType.INFORMATION, "Reservation added successfully!").show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
//        }
//
//        // Reset input fields after saving the reservation
//        refreshPage(); // This clears the form and refreshes the table.
//    }
//

    @FXML
    void btnRemoveReservationOnAction(ActionEvent event) throws SQLException {
        try {
            // Validate if a Reservation ID is selected
            String reservationId = txtResavationId.getText();
            if (reservationId.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please select a reservation to remove.").show();
                return;
            }

            // Confirm deletion
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to remove this reservation?");
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Call the model to delete the reservation
                boolean isDeleted = reservationBO.deleteReservation(reservationId);

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Reservation removed successfully!").show();
                    refreshPage(); // Clear input fields and refresh the table
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to remove the reservation!").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
        refreshPage();
    }

    @FXML
    void btnBookingOnAction(ActionEvent event) throws SQLException {
        try {
            // Validate the number of guests
            String numberOfGuestsText = txtNumberOfGuest.getText();
            if (numberOfGuestsText == null || numberOfGuestsText.isEmpty()) {
                throw new NumberFormatException("Number of guests is required.");
            }
            int numberOfGuests = Integer.parseInt(numberOfGuestsText);

            // Create ReservationDto
            ReservationDto reservationDto = new ReservationDto(
                    txtResavationId.getText(),
                    txtCustomerId.getText(),
                    LocalDate.parse(lblDateShow.getText()),
                    numberOfGuests,
                    txtSpecialRequest.getText(),
                    choiseBoxResavationStatus.getValue()
            );

            // Create TableAssignmentDto
            TableAssignmentDto tableAssignmentDto = new TableAssignmentDto(
                    txtAssignmentId.getText(),
                    txtTableId.getText(),
                    txtResavationId.getText(),
                    dateAndTime()
            );

            // Save reservation
            boolean isSaved = reservationBO.saveReservation(reservationDto, tableAssignmentDto);

            // Configure the alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Status");
            alert.setHeaderText(null); // No header text

            if (isSaved) {
                alert.setContentText("The Reservation was added successfully!");
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("The Reservation could not be added!");
            }

            // Show the alert
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Handle invalid number format
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Number of Guests");
            alert.setContentText("Please enter a valid number for guests.");
            alert.showAndWait();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An unexpected error occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        refreshPage();
    }

    public LocalDateTime dateAndTime() {
        LocalDateTime assignDateTime = null;

        try {
            if (dpDatePicker == null || dpDatePicker.getValue() == null) {
                throw new IllegalArgumentException("Date must not be null");
            }
            LocalDate inputDate = dpDatePicker.getValue();

            if (txtTimePicker == null || txtTimePicker.getText() == null || txtTimePicker.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Time must not be null or empty");
            }

            LocalTime time = LocalTime.parse(txtTimePicker.getText().trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));

            assignDateTime = LocalDateTime.of(inputDate, time);
        } catch (IllegalArgumentException e) {
            System.err.println("Input validation error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing date or time: " + e.getMessage());
        }

        return assignDateTime;
    }

    @FXML
    void btnCustomerSearchOnAction(ActionEvent event) {
        //tele eken set wenna ona customer ----> cus id eka
        try {

            String customerMobile = txtCustomerTele.getText();
            CustomerViewDto customerDto = customerBO.searchCustomerMobile(customerMobile);
            if (customerDto != null) {
                txtCustomerTele.setText(customerDto.getPhone());
                txtCustomerId.setText(customerDto.getId());

            } else {
                new Alert(Alert.AlertType.WARNING, "Customer not found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnResavationSearchOnAction(ActionEvent event) {
        String reservationId = txtResavationId.getText(); // Assuming reservation is entered in txtResavationId

        try {
            ReservationDto reservationDto = reservationBO.searchReservation (reservationId);

            if (reservationDto != null) {
                // Populate the text fields with order details
                txtNumberOfGuest.setText(String.valueOf(reservationDto.getNumberOfGuests()));
                choiseBoxResavationStatus.setValue(reservationDto.getStatus());
                txtSpecialRequest.setText(reservationDto.getSpecialRequests());
                lblDateShow.setText(String.valueOf(reservationDto.getReservationDate()));

//                btnUpdate.setDisable(false);
//                btnDelete.setDisable(false);
//                btnSave.setDisable(true);
            } else {
                // Create an alert of type INFORMATION or WARNING
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Order Not Found");
                alert.setHeaderText(null); // Optional: set this to null if no header is needed
                alert.setContentText("The Reservation you are looking for was not found.");

                // Show the alert and wait for user action
                alert.showAndWait();
                // new Alert(Alert.AlertType.WARNING, "Order Not Found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
       // refreshPage();
        try {
            // Reset all form fields to their default state
            txtResavationId.setText(getNextReservationId());
            txtAssignmentId.setText(getNextAssignmentId());
            txtNumberOfGuest.clear();
            txtTableId.clear();
            txtCustomerId.clear();
            txtSpecialRequest.clear();
            txtTimePicker.clear();
            choiseBoxResavationStatus.setValue(null);
            dpDatePicker.setValue(null);
            choiseBoxTableStatus.setValue(null);

            //this is lables
            txtTableNumber.setText("");
            txtTableLocation.setText("");
            txtTableCapacity.setText("");

            // Disable Update and Delete buttons
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            // Optionally refresh related data
            // refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while resetting the form: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnTableSearchOnAction(ActionEvent event) {
        String tableId = txtTableId.getText();
        try {
            Table tableEntity = tableBO.searchTableById(tableId);
            if (tableEntity != null) {
                txtTableNumber.setText(String.valueOf(tableEntity.getTableNumber()));
                txtTableCapacity.setText(String.valueOf(tableEntity.getTableCapacity()));
                txtTableLocation.setText(tableEntity.getTableLocation());
                choiseBoxTableStatus.setValue(tableEntity.getTableStatus());
            } else {
                // Create an alert of type INFORMATION or WARNING
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Order Not Found");
                alert.setHeaderText(null); // Optional: set this to null if no header is needed
                alert.setContentText("The Table you are looking for was not found.");

                // Show the alert and wait for user action
                alert.showAndWait();
                // new Alert(Alert.AlertType.WARNING, "Order Not Found!", ButtonType.OK).showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        try {
            // Validate the number of guests
            String numberOfGuestsText = txtNumberOfGuest.getText();
            if (numberOfGuestsText == null || numberOfGuestsText.isEmpty()) {
                throw new NumberFormatException("Number of guests is required.");
            }
            int numberOfGuests = Integer.parseInt(numberOfGuestsText);

            // Create ReservationDto
            ReservationDto reservationDto = new ReservationDto(
                    txtResavationId.getText(),
                    txtCustomerId.getText(),
                    LocalDate.parse(lblDateShow.getText()),
                    numberOfGuests,
                    txtSpecialRequest.getText(),
                    choiseBoxResavationStatus.getValue()
            );

            // Create TableAssignmentDto
            TableAssignmentDto tableAssignmentDto = new TableAssignmentDto(
                    txtAssignmentId.getText(),
                    txtTableId.getText(),
                    txtResavationId.getText(),
                    dateAndTime()
            );

            // Save reservation
            boolean isUpdate = reservationBO.updateReservation(reservationDto, tableAssignmentDto);

            // Configure the alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Booking Status");
            alert.setHeaderText(null); // No header text

            if (isUpdate) {
                alert.setContentText("The Reservation was added successfully!");
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("The Reservation could not be added!");
            }

            // Show the alert
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Handle invalid number format
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Number of Guests");
            alert.setContentText("Please enter a valid number for guests.");
            alert.showAndWait();
        } catch (Exception e) {
            // Handle other unexpected exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An unexpected error occurred");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        refreshPage();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        // Check if a row is selected
        ReservationTM selectedReservation = tblMenuItem.getSelectionModel().getSelectedItem();
       // CustomerTM selectReservation2 =  tblMenuItem.getSelectionModel().getSelectedItem();


        if (selectedReservation != null) {
            // Populate the input fields with the data from the selected reservation
            txtResavationId.setText(selectedReservation.getReservationID());
            txtTableId.setText(selectedReservation.getTableId());
            //txtAssignmentId.setText(selectedReservation.getAssignmentId());
            txtNumberOfGuest.setText(String.valueOf(selectedReservation.getNumberOfGuests()));
            txtTableId.setText(selectedReservation.getTableId());
            //txtCustomerId.setText(selectedReservation.getCustomerId());
            //choiseBoxResavationStatus.setValue(selectedReservation.getReservationStatus());
            dpDatePicker.setValue(selectedReservation.getReservationDate());
            //txtTimePicker.setText(selectedReservation.getTime()); // Assuming this field exists in your ReservationTM class
            txtSpecialRequest.setText(selectedReservation.getSpecialRequests());
            txtTableCapacity.setText(String.valueOf(selectedReservation.getTableCapacity()));
            txtTableLocation.setText(selectedReservation.getTableLocation());

            // Optionally, you can enable the 'Update' and 'Delete' buttons if a reservation is selected
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        } else {
            // If no row is selected, you can disable the 'Update' and 'Delete' buttons
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }


    @FXML
    void navigateToTable(ActionEvent event) {
        // resavationAnchorePane.setVisible(false);
        try {
            resavationAnchorePane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource("/view/Table.fxml"));
            resavationAnchorePane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

    @FXML
    void btnBackColorChange(MouseEvent event) {
        btnBack.getStyleClass().add("button-selecthover");
    }

    @FXML
    void btnBackColorChangeBack(MouseEvent event) {
        btnBack.getStyleClass().remove("button-selecthover");
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

    private void refreshPage() throws SQLException {
        // Clear all text fields
        refreshTable();
        txtResavationId.setText(reservationBO.getNextReservationID());
        txtAssignmentId.setText(getNextAssignmentId());
        txtNumberOfGuest.clear();
        txtSpecialRequest.clear();
        txtTableId.clear();
        txtCustomerId.clear();

        //this is lables
        txtTableNumber.setText("");
        txtTableLocation.setText("");
        txtTableCapacity.setText("");

        // Reset ChoiceBox selection
        choiseBoxResavationStatus.getSelectionModel().clearSelection();

        // Optionally, reset to default values (e.g., auto-generate new IDs)
        try {
            txtResavationId.setText(reservationBO.getNextReservationID());
            txtAssignmentId.setText(tableAssignmentDAO.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error generating next IDs: " + e.getMessage()).show();
        }
    }

    private void refreshTable(){
        try {
            ArrayList<CustomReservation> reservations =  quaryDAO.getAllReservations();
            ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList();

            if (reservations != null && !reservations.isEmpty()) {
                for (CustomReservation customReservationEntity : reservations) {
                    reservationTMS.add(new ReservationTM(
                            customReservationEntity.getReservationId(),
                           // reservationDTO.getCustomerID(),
                            customReservationEntity.getReservationDate(),
                            customReservationEntity.getNumberOfGuests(),
                            customReservationEntity.getSpecialRequests(),
                            customReservationEntity.getTableId(),
                            customReservationEntity.getTableStatus(),
                            customReservationEntity.getTableCapacity(),
                            customReservationEntity.getTableLocation()
                    ));
                }
            }
            tblMenuItem.setItems(reservationTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
