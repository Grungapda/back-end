package com.grungapda.backend.file.command.application.DTO;

import com.grungapda.backend.file.command.domain.aggregate.entity.Participant;
import lombok.Getter;

import java.util.List;

@Getter
public class FileRequest {

    private String songTitle;

    private String songArtist;

    private String needSession;

    private List<Participant> participantList;
}
