package com.grungapda.backend.file.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no")
    private Long fileNo;

    @Column
    private String originFileName;

    @Column
    private String fileName;

    @Column
    private Boolean filesIsDeleted;

    @Column
    private String imageFilePath;

    @Column
    private String musicFilePath;

    @Column
    private String midFilePath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public File(Long fileNo, String originFileName, String fileName, Boolean filesIsDeleted,
                String imageFilePath, String musicFilePath, String midFilePath,
                User user) {
        this.fileNo = fileNo;
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filesIsDeleted = filesIsDeleted;
        this.imageFilePath = imageFilePath;
        this.musicFilePath = musicFilePath;
        this.midFilePath = midFilePath;
        this.user = user;
    }

    public void setFileNo(Long fileNo) {
        this.fileNo = fileNo;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilesIsDeleted(Boolean filesIsDeleted) {
        this.filesIsDeleted = filesIsDeleted;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public void setMusicFilePath(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    public void setMidFilePath(String midFilePath) {
        this.midFilePath = midFilePath;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
