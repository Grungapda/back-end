package com.grungapda.backend.file.command.application.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grungapda.backend.file.command.application.DTO.CreateFile;
import com.grungapda.backend.file.command.domain.aggregate.entity.File;
import com.grungapda.backend.file.command.domain.aggregate.entity.Participant;
import com.grungapda.backend.file.command.domain.repository.FileRepository;
import com.grungapda.backend.file.command.domain.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final ParticipantRepository participantRepository;

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
                    .build();

            File newFile = fileRepository.save(file);

            for (int i = 0; i < createFile.getParticipantList().size(); i++){
                Participant participant = Participant.builder()
                        .participantNo(createFile.getParticipantList().get(i).getParticipantNo())
                        .userNo(createFile.getParticipantList().get(i).getUserNo())
                        .sessionType(createFile.getParticipantList().get(i).getSessionType())
                        .file(newFile)
                        .build();
                participantRepository.save(participant);
            }


        }catch (Exception e){
            throw new Exception("잘못된 요청입니다.");
        }
    }

//    public List<CreateFile> findAllFiles(){
//        List<File> files = fileRepository.findAll();
//        List<CreateFile> responseFiles = new ArrayList<>();
//
//        for (File file : files){
//            CreateFile createFile = new CreateFile(file);
//            responseFiles.add(createFile);
//        }
//        return responseFiles;
//    }



}
