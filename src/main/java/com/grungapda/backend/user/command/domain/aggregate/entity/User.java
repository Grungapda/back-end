package com.grungapda.backend.user.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public User(Long userNo, String userEmail, String userPwd, String userNickName, Boolean userIsDeleted) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void deleteUser(String userEmail, String userNickName, Boolean userIsDeleted) {
        this.userEmail = userEmail;
        this.userNickName = userNickName;
        this.userIsDeleted = userIsDeleted;
    }
}
