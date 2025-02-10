package com.ijse.rms.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PurchaseItemTM {
    private String purchaseItemId;
    private String inventoryItemId;
    private double price;
    private int quantity;
    private String status;
}
