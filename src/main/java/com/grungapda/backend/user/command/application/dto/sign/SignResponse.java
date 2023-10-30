package com.grungapda.backend.user.command.application.dto.sign;

import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignResponse {

    private Long userNo;
    private String userEmail;
    private String userNickname;

    @Builder
    public SignResponse(User user) {
        this.userNo = user.getUserNo();
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickName();
    }
}
