package com.grungapda.backend.userListener.command.application.dto.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponse {

    private String accessToken;
//    private String refreshToken;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
//        this.refreshToken = refreshToken
    }
}
