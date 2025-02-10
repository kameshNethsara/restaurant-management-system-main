package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MenuItemIngredent {
    private String menuItemId;
    private String inventoryId;
    private double qtyNeeded;

    public String getInventoryName() {
        return null;
    }
}
