package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InventoryItemDto {
    private String id;
    private String name;
    private String description;
    private int qty;
    private String unit;
}
