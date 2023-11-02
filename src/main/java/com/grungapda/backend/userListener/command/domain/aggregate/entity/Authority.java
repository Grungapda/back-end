package com.grungapda.backend.userListener.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import com.grungapda.backend.userMusician.command.domain.aggregate.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long authNo;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @Column
    private Boolean refreshTokenIsDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private com.grungapda.backend.userMusician.command.domain.aggregate.entity.User user;

    @Builder
    public Authority(Long authNo, String tokenType, String accessToken, String refreshToken, Boolean refreshTokenIsDeleted, com.grungapda.backend.userMusician.command.domain.aggregate.entity.User user) {
        this.authNo = authNo;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenIsDeleted = refreshTokenIsDeleted;
        this.user = user;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRefreshTokenIsDeleted(Boolean refreshTokenIsDeleted) {
        this.refreshTokenIsDeleted = refreshTokenIsDeleted;
    }

    //업데이트 토큰
    public void updateToken(String tokenType, String accessToken, String refreshToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void deleteRefreshToken(Boolean refreshTokenIsDeleted) {
        this.refreshTokenIsDeleted = true;
    }
}
