package com.grungapda.backend.user.command.application.dto.auth;

import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponse {

    private String accessToken;
    private String refreshToken;

    @Builder
    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static AuthResponse fromEntity(Authority authority) {
        return AuthResponse.builder()
                .accessToken(authority.getAccessToken())
                .refreshToken(authority.getRefreshToken())
                .build();
    }
}
