package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TableDto {
    private String tableId;
    private int tableNumber;
    private int tableCapacity;
    private String tableLocation;
    private String tableStatus;
}
