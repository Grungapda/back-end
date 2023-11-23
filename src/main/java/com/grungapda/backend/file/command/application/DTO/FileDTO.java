package com.grungapda.backend.file.command.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDTO {
    private Long fileNo;

    private String songTitle;

    private String songArtist;

    private String needSession;

    private String imageFileUrl;

    private String musicFileUrl;

    private String midFileUrl;
}
