package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InventoryItem {
    private String id;
    private String name;
    private String description;
    private int qty;
    private String unit;

}
