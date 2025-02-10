package com.ijse.rms.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchasesDto {
    private String id;
    private String supplierId;
    private String totalAmount;
    private LocalDate date;
}
