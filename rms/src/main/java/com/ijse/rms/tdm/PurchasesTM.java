package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchasesTM {
    private String id;
    private String supplierId;
    private String totalAmount;
    private LocalDate date;
}
