package com.grungapda.backend.userMusician.command.application.dto.login;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogoutResponse {

    private Boolean refreshTokenIsDeleted;

    @Builder
    public LogoutResponse(Boolean refreshTokenIsDeleted) {
        this.refreshTokenIsDeleted = true;
    }
}
