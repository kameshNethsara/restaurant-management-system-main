package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MenuItemIngredentsDto {
    private String menuItemId;
    private String inventoryId;
    private double qtyNeeded;

    public String getInventoryName() {
        return null;
    }
    //private String inventoryName;
}
