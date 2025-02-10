package com.ijse.rms.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuItems {
    private String id;
    private String name;
    private String description;
    private double price;
    //private int qty;
    private String category;
    private String kitchenSection;
}
