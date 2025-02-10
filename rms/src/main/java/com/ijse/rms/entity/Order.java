package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
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
