package com.grungapda.backend.userMusician.command.application.dto.login;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String userEmail;
    private String userPwd;
}
