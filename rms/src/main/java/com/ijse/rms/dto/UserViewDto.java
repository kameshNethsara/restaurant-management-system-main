package com.ijse.rms.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor

@Getter
@Setter
@ToString

public class UserViewDto {
    private String id;
    private String name;
    private String password;
    private LocalDateTime loginTime;
    private String employeeId;

    public UserViewDto(String id, String name, String password, LocalDateTime loginTime, String employeeId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.loginTime = loginTime;
        this.employeeId = employeeId;
    }
}

