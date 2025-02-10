package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Table {
    private String tableId;
    private int tableNumber;
    private int tableCapacity;
    private String tableLocation;
    private String tableStatus;
}
