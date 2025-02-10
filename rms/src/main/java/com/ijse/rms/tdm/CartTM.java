package com.ijse.rms.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private String menuItemId;
    private String menuItemName;
    private double unitPrice;
    private int addToCartMenuItemQty;
    private double orderItemPrice;
    private double amount; //(addToCartMenuItemQty * orderItemPrice)
    private double totalAmount;
}
