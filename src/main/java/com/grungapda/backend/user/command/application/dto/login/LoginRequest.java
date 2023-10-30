package com.grungapda.backend.user.command.application.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {

    private String userEmail;
    private String userPwd;
}
