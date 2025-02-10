package com.ijse.rms.dto;

import javafx.fxml.FXML;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderViewDto {
    private String orderId;
    private String customerId;
    private String userId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String orderStatus;
    private String orderType;
    private String reservationId;
    private String paymentId;

}
