package com.grungapda.backend.user.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(length = 100, nullable = false, unique = true)
    private String userEmail;

    @Column(length = 100)
    private String userPwd;

    @Column
    private String userNickName;

    @Column
    private Boolean userIsDeleted;

    @Column
    private Boolean musician;

    @Column
    private Integer sessionType;

    @Column
    private Integer genre;

    @Column
    private Integer mood;

    @Column
    private Integer characterType;

    @Column
    private String hexStringCloth;

    @Column
    private String hexStringSkin;

    @Column
    private String hexStringFace;

    @Column
    private String hexStringRibbon;

    @Column
    private Boolean isCrownOn;

    @Column
    private Boolean isGlassOn;

    @Column
    private Boolean isBagOn;

    @Column
    private Boolean isCapOn;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Authority> authorities;

    @Builder
    public User(Long userNo, String userEmail, String userPwd, String userNickName, Boolean userIsDeleted,
                Boolean musician, Integer sessionType, Integer genre, Integer mood ,
                Integer characterType, String hexStringCloth, String hexStringSkin, String hexStringFace, String hexStringRibbon,
                Boolean isCrownOn, Boolean isGlassOn, Boolean isBagOn, Boolean isCapOn,
                List<Authority> authorities) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
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
        this.authorities = authorities;
    }

    public void setCharacterType(Integer characterType) {
        this.characterType = characterType;
    }

    public void setHexStringCloth(String hexStringCloth) {
        this.hexStringCloth = hexStringCloth;
    }

    public void setHexStringSkin(String hexStringSkin) {
        this.hexStringSkin = hexStringSkin;
    }

    public void setHexStringFace(String hexStringFace) {
        this.hexStringFace = hexStringFace;
    }

    public void setHexStringRibbon(String hexStringRibbon) {
        this.hexStringRibbon = hexStringRibbon;
    }

    public void setCrownOn(Boolean crownOn) {
        isCrownOn = crownOn;
    }

    public void setGlassOn(Boolean glassOn) {
        isGlassOn = glassOn;
    }

    public void setBagOn(Boolean bagOn) {
        isBagOn = bagOn;
    }

    public void setCapOn(Boolean capOn) {
        isCapOn = capOn;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void deleteUser(String userEmail, String userNickName, Boolean userIsDeleted,
                           Boolean musician,Integer sessionType, Integer genre, Integer mood,
                           Integer characterType, String hexStringCloth, String hexStringSkin, String hexStringFace, String hexStringRibbon,
                           Boolean isCrownOn, Boolean isGlassOn, Boolean isBagOn, Boolean isCapOn) {
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
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
    }
}
