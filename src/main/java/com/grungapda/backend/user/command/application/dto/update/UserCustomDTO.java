package com.grungapda.backend.user.command.application.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomDTO {

    private Long userNo;

    private Integer characterType;

    private String hexStringCloth;

    private String hexStringSkin;

    private String hexStringFace;

    private String hexStringRibbon;

    private Boolean isCrownOn;

    private Boolean isGlassOn;

    private Boolean isBagOn;

    private Boolean isCapOn;
}
