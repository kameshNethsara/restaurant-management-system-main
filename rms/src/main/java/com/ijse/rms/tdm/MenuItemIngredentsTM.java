package com.ijse.rms.tdm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MenuItemIngredentsTM {
    private String menuItemId;
    private String inventoryId;
    private double qtyNeeded;
    private String inventoryName;
}
