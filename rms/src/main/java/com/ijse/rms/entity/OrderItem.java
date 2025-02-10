package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderItem {
    private String orderItemID;
    private String orderId;
    private String menuItemID;
    private int quantity;
    private double price;
}
