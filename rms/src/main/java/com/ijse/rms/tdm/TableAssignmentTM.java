package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TableAssignmentTM {
    private String tableAssignmentID;
    private String tableID;
    private String reservationID;
    private LocalDateTime assignmentTime;
}
