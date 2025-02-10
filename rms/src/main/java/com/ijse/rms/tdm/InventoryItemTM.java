package com.ijse.rms.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InventoryItemTM {
    private String id;
    private String name;
    private String description;
    private int qty;
    private String unit;
}
