package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderTM {
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
