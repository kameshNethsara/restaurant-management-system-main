package com.ijse.rms.tdm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TableTM {
    private String tableId;
    private int tableNumber;
    private int tableCapacity;
    private String tableLocation;
    private String tableStatus;

}
