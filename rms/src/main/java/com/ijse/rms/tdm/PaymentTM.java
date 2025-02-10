package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class PaymentTM {
    private String id;
    private String method;
    private double amount;
    private LocalDate date;
}
