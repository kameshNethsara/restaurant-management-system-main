package com.ijse.rms.dto;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuItemDto {

    private String id;
    private String name;
    private String description;
    private double price;
    //private int qty;
    private String category;
    private String kitchenSection;


}
