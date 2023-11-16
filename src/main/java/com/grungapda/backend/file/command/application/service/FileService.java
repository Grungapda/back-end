package com.grungapda.backend.file.command.application.service;


import com.grungapda.backend.file.command.application.DTO.CreateFile;
import com.grungapda.backend.file.command.domain.aggregate.entity.File;
import com.grungapda.backend.file.command.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public void createFile(CreateFile createFile) throws Exception{
        try {
            File file = File.builder()
                    .songTitle(createFile.getSongTitle())
                    .songArtist(createFile.getSongArtist())
                    .needSession(createFile.getNeedSession())
                    .imageFileUrl(createFile.getImageFileUrl())
                    .musicFileUrl(createFile.getMusicFileUrl())
                    .midFileUrl(createFile.getMidFileUrl())
                    .participantList(createFile.getParticipantList())
                    .build();

            fileRepository.save(file);
        }catch (Exception e){
            throw new Exception("잘못된 요청입니다.");
        }
    }

    public List<CreateFile> findAllFiles(){
        List<File> files = fileRepository.findAll();
        List<CreateFile> responseFiles = new ArrayList<>();

        for (File file : files){
            CreateFile createFile = new CreateFile(file);
            responseFiles.add(createFile);
        }
        return responseFiles;
    }



}
