package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchaseItem {
    private String purchaseItemId;
    private String inventoryItemId;
    private double price;
    private int quantity;
    private String status;
}
