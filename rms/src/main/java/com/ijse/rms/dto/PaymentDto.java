package com.ijse.rms.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class PaymentDto {
    private String id;
    private String method;
    private double amount;
    private LocalDate date;


}