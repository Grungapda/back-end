package com.grungapda.backend.user.command.application.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String userEmail;
    private String userPwd;

    public LoginRequest(String userEmail, String userPwd) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }
}
