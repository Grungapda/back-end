package com.grungapda.backend.user.command.application.dto.login;

import com.grungapda.backend.user.command.application.dto.auth.AuthResponse;
import com.grungapda.backend.user.command.domain.aggregate.entity.Authority;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import lombok.*;

import javax.persistence.Column;
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

    private Integer characterType;


    private String hexStringCloth;

    private String hexStringSkin;

    private String hexStringFace;

    private String hexStringRibbon;

    private Boolean isCrownOn;

    private Boolean isGlassOn;

    private Boolean isBagOn;

    private Boolean isCapOn;

    private List<AuthResponse> authority;

    public LoginResponse(User user) {
        this.userNo = user.getUserNo();
        this.userNickname = user.getUserNickName();
        this.musician = user.getMusician();
        this.sessionType = user.getSessionType();
        this.genre = user.getGenre();
        this.mood = user.getMood();
        this.characterType = user.getCharacterType();
        this.hexStringCloth = user.getHexStringCloth();
        this.hexStringSkin = user.getHexStringSkin();
        this.hexStringFace = user.getHexStringFace();
        this.hexStringRibbon = user.getHexStringRibbon();
        this.isCrownOn = user.getIsCrownOn();
        this.isGlassOn = user.getIsGlassOn();
        this.isBagOn = user.getIsBagOn();
        this.isCapOn = user.getIsCapOn();
        this.authority = user.getAuthorities().stream().map(AuthResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Builder
    public LoginResponse(Long userNo, String userNickname, Boolean musician, Integer sessionType, Integer genre, Integer mood, Integer characterType, String hexStringCloth,
                         String hexStringSkin, String hexStringFace, String hexStringRibbon, Boolean isCrownOn, Boolean isGlassOn, Boolean isBagOn, Boolean isCapOn, List<AuthResponse> authority) {
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.musician = musician;
        this.sessionType = sessionType;
        this.genre = genre;
        this.mood = mood;
        this.characterType = characterType;
        this.hexStringCloth = hexStringCloth;
        this.hexStringSkin = hexStringSkin;
        this.hexStringFace = hexStringFace;
        this.hexStringRibbon = hexStringRibbon;
        this.isCrownOn = isCrownOn;
        this.isGlassOn = isGlassOn;
        this.isBagOn = isBagOn;
        this.isCapOn = isCapOn;
        this.authority = authority;
    }

    public LoginResponse(Optional<User> user) {
    }
}
