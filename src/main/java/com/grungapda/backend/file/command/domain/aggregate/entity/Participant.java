package com.grungapda.backend.file.command.domain.aggregate.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_no")
    private Long participantNo;

    @Column
    private Integer userNo;

    @Column
    private Integer sessionType;

    @ManyToOne
    @JoinColumn(name = "file_no")
    private File file;


    @Builder
    public Participant(Long participantNo, Integer userNo, Integer sessionType, File file) {
        this.participantNo = participantNo;
        this.userNo = userNo;
        this.sessionType = sessionType;
        this.file = file;
    }
}
