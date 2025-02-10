package com.ijse.rms.controller;

import com.ijse.rms.bo.custom.EmployeeBO;
import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.bo.custom.impl.EmployeeBOImpl;
import com.ijse.rms.bo.custom.impl.UserBOImpl;
import com.ijse.rms.dao.custom.QuaryDAO;
import com.ijse.rms.dao.custom.impl.QuaryDAOImpl;
import com.ijse.rms.dto.UserViewDto;

//import com.ijse.rms.model.UserViewModel;
import com.ijse.rms.util.SendMailUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgetPasswordController implements Initializable {

    @FXML
    private JFXButton btnConformOTP;

    @FXML
    private JFXButton btnConformUserName;

    @FXML
    private Pane btnPane;

    @FXML
    private JFXButton btnSendOTP;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private ImageView imagePane;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblSignUp;

    @FXML
    private Pane pane1;

    @FXML
    private JFXTextField txtOTP;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtPassword1;

    @FXML
    private JFXTextField txtPasswordVisible;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private AnchorPane fPAbchorPane;

    //UserViewModel userModel = new UserViewModel();
    UserBO userBO = new UserBOImpl();
    QuaryDAO quaryDAO = new QuaryDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.setVisible(false);
        txtPassword1.setVisible(false);
        btnSignin.setVisible(false);
    }

    @FXML
    void navigateToLogin(MouseEvent event) {
        try {
            fPAbchorPane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
            fPAbchorPane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnConformUserNameOnAction(ActionEvent event) throws SQLException {

        if(!txtUserName.getText().isEmpty() ) {
            lblEmail.setText(quaryDAO.getEmpEmail(txtUserName.getText()));
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name Not Found!", ButtonType.OK).showAndWait();
        }
    }
    static String otp;
    @FXML
    void btnSendOTPOnAction(ActionEvent event) {

        String Email = lblEmail.getText();
        otp = createOtp();
        SendMailUtil.sendEmailWithGmail(Email, "Your SpiceUp verification code is: ", otp);
    }

    private String createOtp() {
        Random rand = new Random();
        int randomNum = rand.nextInt(999999);
        return String.valueOf(randomNum);
    }

    @FXML
    void btnConformOTPOnAction(ActionEvent event) {
       if(txtOTP.getText().equals(otp)) {
           txtPassword.setVisible(true);
           txtPassword1.setVisible(true);
           btnSignin.setVisible(true);
       } else {
           new Alert(Alert.AlertType.WARNING, "OTP is invalid!", ButtonType.OK).showAndWait();
       }

    }

    @FXML
    void btnSigninOnAction(ActionEvent event) {

        if(!txtPassword.getText().equals(txtPassword1.getText())) {
            new Alert(Alert.AlertType.WARNING, "Password Mismatch , Different Password", ButtonType.OK).showAndWait();
        }else{

            try {
                boolean isUpdated = userBO.updateUserPassword(txtUserName.getText(),txtPassword.getText());
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Password changed successfully!", ButtonType.OK).showAndWait();
                }else{ new Alert(Alert.AlertType.ERROR, "Password change Failed!", ButtonType.OK).showAndWait();}
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

