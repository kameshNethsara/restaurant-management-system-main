package com.ijse.rms.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierViewDto {
    private String id;
    private String name;
    private String contactInfo;
    private String phone;
    private String email;
    private String address;
    private String userId;
}
