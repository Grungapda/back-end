package com.grungapda.backend.file.command.domain.aggregate.entity;

import com.grungapda.backend.common.AuditingFields;
import com.grungapda.backend.user.command.domain.aggregate.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no")
    private Long fileNo;

    @Column
    private String songTitle;
    @Column
    private String songArtist;
    @Column
    private String needSession;

    @Column
    private String imageFileUrl;

    @Column
    private String musicFileUrl;

    @Column
    private String midFileUrl;

    @OneToMany
    @Column
    private List<Participant> participantList;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_no")
//    private User user;

    @Builder
    public File(Long fileNo, String songTitle, String songArtist, String needSession,
                String imageFileUrl, String musicFileUrl, String midFileUrl,
                List<Participant> participantList
                ) {
        this.fileNo = fileNo;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.needSession = needSession;
        this.imageFileUrl = imageFileUrl;
        this.musicFileUrl = musicFileUrl;
        this.midFileUrl = midFileUrl;
        this.participantList = participantList;
    }


}
