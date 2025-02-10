package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Purchase {
    private String id;
    private String supplierId;
    private String totalAmount;
    private LocalDate date;
}
