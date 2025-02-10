package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Payment {
    private String id;
    private String method;
    private double amount;
    private LocalDate date;
}
