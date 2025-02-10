package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TableAssignment {

    private String tableAssignmentID;
    private String tableID;
    private String reservationID;
    private LocalDateTime assignmentTime;
}
