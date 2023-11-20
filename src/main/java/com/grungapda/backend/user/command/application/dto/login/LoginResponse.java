package com.grungapda.backend.user.command.application.dto.login;

import com.grungapda.backend.user.command.application.dto.auth.AuthResponse;
import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long userNo;

    private String userNickname;

    private Boolean musician;

    private Integer sessionType;

    private Integer genre;

    private Integer mood;

    private List<AuthResponse> authority;

    public LoginResponse(User user) {
        this.userNo = user.getUserNo();
        this.userNickname = user.getUserNickName();
        this.musician = user.getMusician();
        this.sessionType = user.getSessionType();
        this.genre = user.getGenre();
        this.mood = user.getMood();
        this.authority = user.getAuthorities().stream().map(AuthResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Builder
    public LoginResponse(Long userNo, String userNickname, Boolean musician, Integer sessionType, Integer genre,
                         Integer mood, List<AuthResponse> authority) {
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.musician = musician;
        this.sessionType = sessionType;
        this.genre = genre;
        this.mood = mood;
        this.authority = authority;
    }
}
