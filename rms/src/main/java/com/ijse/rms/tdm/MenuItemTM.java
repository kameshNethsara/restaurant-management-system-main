package com.ijse.rms.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuItemTM {
    private String id;
    private String name;
    private String description;
    private double price;
    //private int qty;
    private String category;
    private String kitchenSection;
}
