package com.grungapda.backend.user.command.application.dto.sign;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignResponse {

    private Long userNo;
    private String userEmail;
    private String userNickname;

    @Builder
    public SignResponse(Long userNo, String userEmail, String userNickname) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
    }
}
