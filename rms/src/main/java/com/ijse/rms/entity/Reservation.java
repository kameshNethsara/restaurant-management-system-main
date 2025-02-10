package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    private String reservationID;
    private String customerID;
    private LocalDate reservationDate;
    private int numberOfGuests;
    private String specialRequests;
    private String status;

}
