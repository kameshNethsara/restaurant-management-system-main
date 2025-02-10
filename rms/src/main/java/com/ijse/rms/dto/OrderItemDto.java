package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderItemDto {
    private String orderItemID;
    private String orderId;
    private String menuItemID;
    private int quantity;
    private double price;
}
