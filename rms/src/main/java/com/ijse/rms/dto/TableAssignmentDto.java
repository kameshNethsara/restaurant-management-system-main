package com.ijse.rms.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TableAssignmentDto {
    private String tableAssignmentID;
    private String tableID;
    private String reservationID;
    private LocalDateTime assignmentTime;
}
