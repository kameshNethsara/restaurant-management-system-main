package com.ijse.rms.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDto {
    private String reservationID;
    private String customerID;
    private LocalDate reservationDate;
    private int numberOfGuests;
    private String specialRequests;
    private String status;
}
