package com.ijse.rms.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderManageTM {
    private String itemId;
    private String name;
    private double unitPrice;
    private int quantity;
    private double price;
}
