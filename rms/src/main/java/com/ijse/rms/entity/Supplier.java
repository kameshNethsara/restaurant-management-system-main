package com.ijse.rms.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String id;
    private String name;
    private String contactInfo;
    private String phone;
    private String email;
    private String address;
    private String userId;
}
