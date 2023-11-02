package com.grungapda.backend.userListener.command.application.dto.login;

import com.grungapda.backend.userMusician.command.application.dto.auth.AuthResponse;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long userNo;
    private String userNickname;
    private List<AuthResponse> authority;

    @Builder
    public LoginResponse(Long userNo, String userNickname, List<AuthResponse> authority) {
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.authority = authority;
    }
}
