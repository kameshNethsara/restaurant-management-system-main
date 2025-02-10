package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationTM {

    private String reservationID;
    //private String customerID;
    private LocalDate reservationDate;
    private int numberOfGuests;
    private String specialRequests;


    private String tableId;
    private String tableStatus;
    private int tableCapacity;
    private String tableLocation;

    public ReservationTM(String reservationId, String numberGuests, LocalDate reservationDate, String specialRequests, String tableId, String reservationStatus, String tableCapacity, String tableLocation) {
    }
}
