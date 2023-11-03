package com.grungapda.backend.user.command.application.dto.login;

import com.grungapda.backend.user.command.application.dto.auth.AuthResponse;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long userNo;

    private String userNickname;

    private Boolean musician;

    private int sessionType;

    private int genre;

    private int mood;

    private List<AuthResponse> authority;

    @Builder
    public LoginResponse(Long userNo, String userNickname, Boolean musician, int sessionType, int genre,
                         int mood, List<AuthResponse> authority) {
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.musician = musician;
        this.sessionType = sessionType;
        this.genre = genre;
        this.mood = mood;
        this.authority = authority;
    }
}
