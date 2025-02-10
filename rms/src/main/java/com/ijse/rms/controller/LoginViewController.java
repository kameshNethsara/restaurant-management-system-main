package com.ijse.rms.controller;

import com.ijse.rms.bo.BOFactory;
import com.ijse.rms.bo.custom.CustomerBO;
import com.ijse.rms.bo.custom.UserBO;
import com.ijse.rms.bo.custom.impl.UserBOImpl;
import com.ijse.rms.dao.custom.QuaryDAO;
import com.ijse.rms.dao.custom.impl.QuaryDAOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    // LoginModel loginModel = new LoginModel();
    //LoginDAOImpl loginDAO = new LoginDAOImpl();
    //LoginDAO loginDAO = new LoginDAOImpl();
//    LoginBO loginBO = new LoginBOImpl();
    //QuaryDAOImpl quaryDAO = new QuaryDAOImpl();
    QuaryDAO quaryDAO = new QuaryDAOImpl();
    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USERS);

    @FXML
    private Pane btnPane;

    @FXML
    private ImageView btnImgPane;

    @FXML
    private JFXTextField txtPasswordVisible;

    @FXML
    private ImageView imgEye;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private ImageView imagePane;

    @FXML
    private Label lblForgetPassword;

    @FXML
    private Label lblSignUp;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Pane pane1;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void setPasswordVisible(MouseEvent event) {
        txtPasswordVisible.setVisible(true);
        txtPassword.setVisible(false);
        txtPasswordVisible.setText(txtPassword.getText());
        imgEye.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/visible-removebg.png")).toExternalForm()));
    }

    @FXML
    void setPasswordInvisible(MouseEvent event) {
        txtPasswordVisible.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(txtPasswordVisible.getText());
        imgEye.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/invisible-removebg.png")).toExternalForm()));
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if ( userBO.updateUserPassword(username, password)){
            loginPane.getChildren().clear();

            checkJobPosetion(username);

        }else {
            //new Alert(Alert.AlertType.ERROR,"somthing wrong").show();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid Username or Password");
            alert.setContentText("Please check your credentials and try again.");
            // Optional: Add a custom icon or graphic to the alert if needed
            // alert.setGraphic(new ImageView(new Image("your-image-path.png")));



            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    private void checkJobPosetion(String username) throws SQLException, ClassNotFoundException {

        //loginModel.isValidPosition(username);

        if(quaryDAO.validPosition(username).equals("Manager")){
            try {
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ManagerForm.fxml"));
                loginPane.getChildren().add(load);
            } catch (IOException e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }

        } else if (quaryDAO.validPosition(username).equals("Cashier")){
            try {
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CashierForm.fxml"));
                loginPane.getChildren().add(load);
            } catch (IOException e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Not Found");
            alert.setHeaderText("Invalid User");
            alert.setContentText("Please check and try again.");
        }
    }

    @FXML
    void onMouseClickedForgetPassword(MouseEvent event) throws IOException {
        String fxmlPath = ("/view/ForgetPassword.fxml");
        try {
            loginPane.getChildren().clear();
            Parent UserView = FXMLLoader.load(getClass().getResource(fxmlPath));
            loginPane.getChildren().add(UserView);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact the developer").show();
            e.printStackTrace();
        }
    }

}
