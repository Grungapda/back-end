package com.grungapda.backend.userMusician.command.application.dto.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdatePwdRequest {

    @NotBlank(message = "현재 패스워드를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{7,20}",
            message = "비밀번호는 영문, 숫자, 특수문자가 적어도 1개 이상씩 포함된 7자 ~ 20자의 비밀번호여야 합니다.")
    private String userPwd; // 기존 패스워드

    @NotBlank(message = "변경할 패스워드를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{7,20}",
            message = "비밀번호는 영문, 숫자, 특수문자가 적어도 1개 이상씩 포함된 7자 ~ 20자의 비밀번호여야 합니다.")
    private String changePassword; // 변경할 패스워드
}
