package com.grungapda.backend.user.command.application.dto.sign;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class SignRequest {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;

    @NotBlank(message = "패스워드를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{7,20}",
            message = "비밀번호는 영문, 숫자, 특수문자가 적어도 1개 이상씩 포함된 7자 ~ 20자의 비밀번호여야 합니다.")
    private String userPwd;

    @NotBlank
    @Pattern(regexp ="^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$",
            message = "닉네임은 2자이상 16자이하, 영어 또는 숫자 또는 한글로 구성해야 합니다.")
    private String userNickname;


    private Boolean musician;
    
    private Integer sessionType;

    private Integer genre;

    private Integer mood;

}
