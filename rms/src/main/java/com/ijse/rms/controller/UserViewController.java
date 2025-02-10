package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.bo.custom.impl.UserBOImpl;
import com.ijse.rms.dto.UserViewDto;
import com.ijse.rms.entity.User;
import com.ijse.rms.tdm.UserTM;

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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

   // private final UserViewModel userModel = new UserViewModel();
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USERS);

    @FXML
    private Label lblUserLoginTimeShow;

    @FXML
    private Button btnDelete, btnReset, btnSave, btnUpdate, btnSearch;

    @FXML
    private TableColumn<UserTM, String> colUserId, colUserName, colPassword, colEmployeeId;

    @FXML
    private TableColumn<UserTM, LocalDateTime> colLoginTime; // Changed type to LocalDateTime

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtEmployeeId, txtUserId, txtUserName, txtUserPassword;

    @FXML
    private AnchorPane userAccountsView;

    private void showTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline timeTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime now = LocalTime.now();
            lblUserLoginTimeShow.setText(now.format(timeFormatter));
        }));

        timeTimeline.setCycleCount(Animation.INDEFINITE);
        timeTimeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTime();
        initializeTable();

        try {
            txtUserId.setText(getNextUserID());
            refreshTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void initializeTable() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colLoginTime.setCellValueFactory(new PropertyValueFactory<>("loginTime"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        // Custom cell factory to format LocalDateTime values in the table
        colLoginTime.setCellFactory(column -> new TableCell<UserTM, LocalDateTime>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatter.format(item));
                }
            }
        });
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime loginTime = LocalDateTime.now().with(LocalTime.parse(lblUserLoginTimeShow.getText(), formatter));

        UserViewDto userDto = new UserViewDto(
                txtUserId.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                loginTime,
                txtEmployeeId.getText()
        );

        try {
            boolean isSaved = userBO.saveUser(userDto);
            if (isSaved) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime loginTime = LocalDateTime.now().with(LocalTime.parse(lblUserLoginTimeShow.getText(), formatter));

        UserViewDto userDto = new UserViewDto(
                txtUserId.getText(),
                txtUserName.getText(),
                txtUserPassword.getText(),
                loginTime,
                txtEmployeeId.getText()
        );

        try {
            boolean isUpdated = userBO.updateUser(userDto);
            if (isUpdated) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = userBO.deleteUser(txtUserId.getText());
            if (isDeleted) {
                refreshPage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            String userId = txtUserId.getText();
            User userEntity = userBO.searchUser(userId);

            if (userEntity != null) {
                txtUserName.setText(userEntity.getName());
                txtUserPassword.setText(userEntity.getPassword());
                lblUserLoginTimeShow.setText(userEntity.getLoginTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                txtEmployeeId.setText(userEntity.getEmployeeId());
                txtUserId.setText(userEntity.getId());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("User Not Found");
                alert.setContentText("No user found with ID: " + userId);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("An error occurred while searching for the user");
            errorAlert.showAndWait();
        }
    }

    private void refreshPage() {
        refreshTable();
        txtUserId.setText(getNextUserID());
        txtUserName.clear();
        txtUserPassword.clear();
        txtEmployeeId.clear();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
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
            ArrayList<UserViewDto> users = userBO.getAllUsers();
            ObservableList<UserTM> userTMS = FXCollections.observableArrayList();

            for (UserViewDto userDto : users) {
                userTMS.add(new UserTM(
                        userDto.getId(),
                        userDto.getName(),
                        userDto.getPassword(),
                        userDto.getLoginTime(),
                        userDto.getEmployeeId()
                ));
            }
            tblUser.setItems(userTMS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        UserTM selectedUser = tblUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtUserId.setText(selectedUser.getId());
            txtUserName.setText(selectedUser.getName());
            txtUserPassword.setText(selectedUser.getPassword());
            lblUserLoginTimeShow.setText(selectedUser.getLoginTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            txtEmployeeId.setText(selectedUser.getEmployeeId());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
