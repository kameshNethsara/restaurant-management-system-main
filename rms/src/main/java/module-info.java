module com.ijse.rms {
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.controls;
    requires lombok;
    requires java.sql;
    requires java.desktop;
    requires javafx.base;
    requires jdk.jfr;
    requires net.sf.jasperreports.core;
    requires jbcrypt;
    requires java.mail;
    requires mysql.connector.j;

    // Export the package containing the Launcher class to JavaFX
    opens com.ijse.rms to javafx.graphics;

    // Correct this to open to javafx.base instead of java.base
    opens com.ijse.rms.dto to javafx.base;

    // You need this for the controllers if you're using FXML
    opens com.ijse.rms.controller to javafx.fxml;

    opens com.ijse.rms.entity to javafx.graphics;

    exports com.ijse.rms.controller;
    opens com.ijse.rms.tdm to javafx.base;
}
