package com.ijse.rms.entity;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class User {

    private String id;
    private String name;
    private String password;
    private LocalDateTime loginTime;
    private String employeeId;
}
