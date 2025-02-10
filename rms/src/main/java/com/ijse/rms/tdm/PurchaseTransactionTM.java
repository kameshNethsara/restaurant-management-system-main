package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PurchaseTransactionTM {

    private String supplierId;
    private String inventoryItemId;
    private String inventoryItemName;
    private String purchaseId;
    private String purchaseItemStatus;
    private LocalDate purchaseDate;
    private int PurchaseItemQty;
    private double PurchaseItemPrice;
    private double PurchaseTotalPrice;


}
