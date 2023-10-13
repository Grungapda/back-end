package com.grungapda.backend.user.command.application.dto.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponse {

    private String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
