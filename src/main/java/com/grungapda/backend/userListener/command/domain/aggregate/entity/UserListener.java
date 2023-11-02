package com.grungapda.backend.userListener.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import com.grungapda.backend.userMusician.command.domain.aggregate.entity.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserListener extends AuditingFields {

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
    private Integer mood;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Authority> authorities;

    @Builder
    public UserListener(Long userNo, String userEmail, String userPwd, String userNickName, Boolean userIsDeleted, Integer mood , List<Authority> authorities) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
        this.mood = mood;
        this.authorities = authorities;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void deleteUser(String userEmail, String userNickName, Boolean userIsDeleted, Integer mood ) {
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
        this.mood = mood;
    }
}