package com.ijse.rms.tdm;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserTM {

        private String id;
        private String name;
        private String password;
        private LocalDateTime loginTime;
        private String employeeId;
}
