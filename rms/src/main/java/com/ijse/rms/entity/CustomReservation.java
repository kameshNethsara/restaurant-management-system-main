package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CustomReservation {

    private String reservationId;
    private LocalDate reservationDate;
    private int numberOfGuests;
    private String specialRequests;
    private String tableId;
    private String tableStatus;
    private int TableCapacity;
    private String tableLocation;

}
