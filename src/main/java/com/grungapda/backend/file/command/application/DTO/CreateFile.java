package com.grungapda.backend.file.command.application.DTO;


import com.grungapda.backend.file.command.domain.aggregate.entity.File;
import com.grungapda.backend.file.command.domain.aggregate.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFile {

    private String songTitle;

    private String songArtist;

    private String needSession;

    private String imageFileUrl;

    private String musicFileUrl;

    private String midFileUrl;

    private List<Participant> participantList;

    public CreateFile(File file) {
        this.songTitle = file.getSongTitle();
        this.songArtist = file.getSongArtist();
        this.needSession = file.getNeedSession();
        this.imageFileUrl = file.getImageFileUrl();
        this.musicFileUrl = file.getMusicFileUrl();
        this.midFileUrl = file.getMidFileUrl();
        this.participantList = file.getParticipantList();
    }
}
