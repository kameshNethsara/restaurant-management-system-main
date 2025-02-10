package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchaseItemDto {
    private String purchaseItemId;
    private String inventoryItemId;
    private double price;
    private int quantity;
    private String status;
}
