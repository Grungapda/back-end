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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Authority> authority;

    @Builder
    public User(Long userNo, String userEmail, String userPwd, String userNickName, Boolean userIsDeleted,Boolean musician,
                Integer sessionType, Integer genre, Integer mood , List<Authority> authority) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
        this.musician = musician;
        this.sessionType = sessionType;
        this.genre = genre;
        this.mood = mood;
        this.authority = authority;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }

    public void deleteUser(String userEmail, String userNickName, Boolean userIsDeleted, Boolean musician, Integer sessionType, Integer genre, Integer mood ) {
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
        this.musician = musician;
        this.sessionType = sessionType;
        this.genre = genre;
        this.mood = mood;
    }
}
